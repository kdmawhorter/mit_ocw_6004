package alu;

import arithmetic.*;
import bitwise.*;
import comparison.*;
import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import core_architecture.SingleOutputCircuit;
import division.Division;
import division.SignedDivision;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import memory.MemoryModule;
import multiplexer.Mux;
import shift.*;

/**
 * A class representing an Arithmetic Logic Unit. This class contains all submodules developed under digital circuit,
 * evaluates each with the inputs provided, and arbitrates the output given the op code input.<br>
 * <br>
 * <b>Inputs</b>: opCodeBits + 2*nBits corresponding to:<ul>
 * <li>an opCodeBit bit string representing an opcode to arbitrate which module should output its answer</li>
 * <li>an nBit bit string representing the input A</li>
 * <li>an nBit bit string representing the input B</li></ul>
 * <b>Outputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string corresponding to the hi bit output register (upper bits for multiplication, remainder in division, overflow)</li>
 * <li>an nBit bit string corresponding to the low bit output register (the output for nBit output modules, the lower bits for multiplication, the quotient in division)</li></ul>
 */
public class Alu extends DigitalCircuit {

    public static final int MAX_OP_CODES = 6;

    public static final int REG_CNT = 32;
    public static final int MEM_CNT = 256;


    private final int nBits;

    // Registers and Memory
    private MemoryModule registers;
    private MemoryModule memory;

    private final InverterGate[] invertedOpCode;
    private AndGate registerWrite;
    private AndGate memoryWrite;

    // Arithmetic
    private UnsignedAdd unsignedAdd_0x10;
    private SignedAdd signedAdd_0x11;
    private Multiplication mult_0x12;
    private SignedMultiplication signedMult_0x13;
    private Division div_0x14;
    private SignedDivision signedDiv_0x15;
    private Increment increment_0x16;
    private Decrement decrement_0x17;

    // Bitwise and Comparison Operations
    private BitwiseAnd bwAnd_0x20;
    private BitwiseNand bwNand_0x21;
    private BitwiseNor bwNor_0x22;
    private BitwiseOr bwOr_0x23;
    private BitwiseXor bwXor_0x24;
    private Inverter bwNot_0x25;
    private Equals equals_0x26;
    private GreaterThan gt_0x27;
    private GreaterThanOrEquals gte_0x28;
    private LessThan lt_0x29;
    private LessThanOrEquals lte_0x2A;
    private NotEquals ne_0x2B;

    // Shift Operations
    private ShiftLeftUnsigned slu_0x30;
    private ShiftLeftSigned sls_0x31;
    private ShiftLeftCircle slc_0x32;
    private ShiftRightUnsigned sru_0x33;
    private ShiftRightSigned srs_0x34;
    private ShiftRightCircle src_0x35;

    private final Mux outputHiArbitration;
    private final Mux outputLoArbitration;

    public Alu(String label, int nBits) {
        super(label, MAX_OP_CODES+2*nBits, 2*nBits);

        this.nBits = nBits;

        outputHiArbitration = new Mux(label + "OutputHiArbitration", (int) Math.pow(2, MAX_OP_CODES), nBits);
        outputHiArbitration.assignOutputs(getRegHi());

        outputLoArbitration = new Mux(label + "OutputLoArbitration", (int) Math.pow(2, MAX_OP_CODES), nBits);
        outputLoArbitration.assignOutputs(getRegLo());

        invertedOpCode = new InverterGate[MAX_OP_CODES];

        for (int i = 0; i < MAX_OP_CODES; i++) {
            outputLoArbitration.assignInput(outputLoArbitration.getNumInputs()-MAX_OP_CODES+i, getInternalInput(i));
            outputHiArbitration.assignInput(outputHiArbitration.getNumInputs()-MAX_OP_CODES+i, getInternalInput(i));

            invertedOpCode[i] = new InverterGate(label + " InvertedOpCode_" + i);
            invertedOpCode[i].assignInput(getInternalInput(i));
            transistorCount += invertedOpCode[i].getTransistorCount();
        }

        transistorCount += outputHiArbitration.getTransistorCount() + outputLoArbitration.getTransistorCount();

        initRegistersAndMemory();
        initArithmeticOps();
        initBitwiseOps();
        initComparison();
        initShift();
    }

    /**
     * Get the first input register, regA (internalInputs[opCode:opCode+nBits]
     * @return regB
     */
    private CircuitNode[] getRegA() {
        CircuitNode[] regA = new CircuitNode[nBits];
        for (int i = 0; i < nBits; i++) {
            regA[i] = getInternalInput(MAX_OP_CODES+i);
        }
        return regA;
    }

    /**
     * Get the second input register, regB (internalInputs[opCode+nBits:end]
     * @return regB
     */
    private CircuitNode[] getRegB() {
        CircuitNode[] regB = new CircuitNode[nBits];
        for (int i = 0; i < nBits; i++) {
            regB[i] = getInternalInput(MAX_OP_CODES+i+nBits);
        }
        return regB;
    }

    /**
     * Get the high output register bits (internalOutputs[0:nBits])
     * @return regHi
     */
    private CircuitNode[] getRegHi() {
        CircuitNode[] regHi = new CircuitNode[nBits];
        for (int i = 0; i < nBits; i++) {
            regHi[i] = getInternalOutput(i);
        }
        return regHi;
    }

    /**
     * Get the low output register bits (internalOutputs[nBit:end])
     * @return regLo
     */
    private CircuitNode[] getRegLo() {
        CircuitNode[] regLo = new CircuitNode[nBits];
        for (int i = 0; i < nBits; i++) {
            regLo[i] = getInternalOutput(i+nBits);
        }
        return regLo;
    }

    /**
     * Standard connection of a circuit's input to a passed in reg (A or B).
     * @param circuit The circuit to connect.
     * @param startInputIdx The index of the circuit's input where the regX will start to connect.
     * @param regX Some register (A or B)
     */
    private void assignRegInput(DigitalCircuit circuit, int startInputIdx, CircuitNode[] regX) {
        for (int i = 0; i < nBits; i++) {
            circuit.assignInput(startInputIdx+i, regX[i]);
        }
    }

    /**
     * Standard connection of a circuit's output to the regHi arbitrator.
     * @param circuit The circuit to connect.
     * @param opCode The opcode for that circuit with the output arbitrator.
     * @param hiStartIdx The index of the circuit's output where the nBit output to connect starts.
     */
    private void assignHiOutput(DigitalCircuit circuit, int opCode, int hiStartIdx) {
        for (int i = 0; i < nBits; i++) {
            outputHiArbitration.assignInput(opCode*nBits+i, circuit.getOutput(hiStartIdx+i));
        }
    }

    /**
     * Standard connection of a node (representing a 1-bit output circuit's output) to the regHi output.
     * @param opCode The opcode for the circuit.
     * @param bit The 1-bit output node to connect to regHi.
     */
    private void assignHiOutputOneBit(int opCode, CircuitNode bit) {
        outputHiArbitration.assignInput((opCode+1)*nBits - 1, bit);
    }

    /**
     * Standard connection of a node (representing a 1-bit output circuit's output) to the regLo output.
     * @param opCode The opcode for the circuit.
     * @param bit The 1-bit output node to connect to regLo.
     */
    private void assignLoOutputOneBit(int opCode, CircuitNode bit) {
        outputLoArbitration.assignInput((opCode+1)*nBits - 1, bit);
    }

    /**
     * Standard connection of a circuit's output to the regLo arbitrator.
     * @param circuit The circuit to connect.
     * @param opCode The opcode for that circuit with the output arbitrator.
     * @param loStartIdx The index of the circuit's output where the nBit output to connect starts.
     */
    private void assignLoOutput(DigitalCircuit circuit, int opCode, int loStartIdx) {
        for (int i = 0; i < nBits; i++) {
            outputLoArbitration.assignInput(opCode*nBits+i, circuit.getOutput(loStartIdx+i));
        }
    }

    /**
     * Standard circuit connections of a single nBit input, single nBit output circuit to regA and regLo
      * @param circuit The circuit to connect.
     * @param opCode The opcode for that circuit with the output arbitrator.
     */
    private void assignCircuitSISO(DigitalCircuit circuit, int opCode) {
        assignRegInput(circuit, 0, getRegA());
        assignLoOutput(circuit, opCode, 0);
    }

    /**
     * Standard circuit connections of a double nBit input, single bit output circuit to regA, regB, and regLo
     * @param circuit The circuit to connect.
     * @param opCode The opcode for that circuit with the output arbitrator.
     */
    private void assignCircuitTISB(SingleOutputCircuit circuit, int opCode) {
        assignRegInput(circuit, 0, getRegA());
        assignRegInput(circuit, nBits, getRegB());
        assignLoOutputOneBit(opCode, circuit.getOutput());
    }

    /**
     * Standard circuit connections of a double nBit input, single nBit output circuit to regA, regB, and regLo
     * @param circuit The circuit to connect.
     * @param opCode The opcode for that circuit with the output arbitrator.
     * @param loStartIdx The index of the circuit's output where the nBit output to connect starts.
     */
    private void assignCircuitTISO(DigitalCircuit circuit, int opCode, int loStartIdx) {
        assignRegInput(circuit, 0, getRegA());
        assignRegInput(circuit, nBits, getRegB());
        assignLoOutput(circuit, opCode, loStartIdx);
    }

    /**
     * Standard circuit connections of a double nBit input, double nBit output circuit to regA, regB, regHi, and regLo
     * @param circuit The circuit to connect.
     * @param opCode The opcode for that circuit with the output arbitrator.
     * @param loStartIdx The index of the circuit's output where the nBit output to connect to regLo starts.
     * @param hiStartIdx The index of the circuit's output where the nBit output to connect to regHi starts.
     */
    private void assignCircuitTITO(DigitalCircuit circuit, int opCode, int loStartIdx, int hiStartIdx) {
        assignCircuitTISO(circuit, opCode, loStartIdx);
        assignHiOutput(circuit, opCode, hiStartIdx);

    }

    /**
     * Creates the register and memory objects for the ALU. First maps all of regA to the inputs of both, then creates
     * an AndGate to set whether the opcode calls for a register write or memory write (0x01 and 0x03 respectively),
     * and sets that output to the 'opcode' of the mux (which is 1 for write, 0 for read). Then moves the contents of
     * regB into the selector bits (which are not necessarily size nBits) of the mux. The output of register and
     * memory are connected to the lo output arbitrator with opcodes of 0x00 and 0x02 respectively.
     */
    private void initRegistersAndMemory() {
        registers = new MemoryModule(getLabel() + " Registers", REG_CNT, nBits);
        memory = new MemoryModule(getLabel() + " Memory", MEM_CNT, nBits);


        registers.assignInputs(getRegA());
        memory.assignInputs(getRegA());

        registerWrite = new AndGate(getLabel() + " RegisterWrite", MAX_OP_CODES);
        memoryWrite = new AndGate(getLabel() + " MemoryWrite", MAX_OP_CODES);
        for (int i = 0; i < MAX_OP_CODES; i++) {
            registerWrite.assignInput(i, (i==MAX_OP_CODES-1) ? getInternalInput(i) : invertedOpCode[i].getOutput());
            memoryWrite.assignInput(i, (i>=MAX_OP_CODES-2) ? getInternalInput(i) : invertedOpCode[i].getOutput());
        }
        registers.assignInput(nBits, registerWrite.getOutput());
        memory.assignInput(nBits, memoryWrite.getOutput());

        for (int i = 0; i < registers.getSelBitCnt(); i++) {
            registers.assignInput(nBits+1+i, getInternalInput(MAX_OP_CODES+2*nBits-registers.getSelBitCnt()+i));
        }
        for (int i = 0; i < memory.getSelBitCnt(); i++) {
            memory.assignInput(nBits+1+i, getInternalInput(MAX_OP_CODES+2*nBits-memory.getSelBitCnt()+i));
        }

        assignLoOutput(registers, 0x00, 0);
        assignLoOutput(memory, 0x02, 0);

        transistorCount += registers.getTransistorCount() + memory.getTransistorCount() +
                memoryWrite.getTransistorCount() + registerWrite.getTransistorCount();
    }

    private void initArithmeticOps() {
        unsignedAdd_0x10 = new UnsignedAdd(getLabel() + " UnsignedAdd", nBits);
        signedAdd_0x11 = new SignedAdd(getLabel() + " SignedAdd", nBits);
        mult_0x12 = new Multiplication(getLabel() + " Multiplication", nBits);
        signedMult_0x13 = new SignedMultiplication(getLabel() + " SignedMultiplication", nBits);
        div_0x14 = new Division(getLabel() + " Division", nBits);
        signedDiv_0x15 = new SignedDivision(getLabel() + " SignedDivision", nBits);
        increment_0x16 = new Increment(getLabel() + " Increment", nBits);
        decrement_0x17 = new Decrement(getLabel() + " Decrement", nBits);

        assignCircuitTISO(unsignedAdd_0x10, 0x10, 0);
        assignCircuitTISO(signedAdd_0x11, 0x11, 0);
        assignCircuitTITO(mult_0x12, 0x12, nBits, 0);
        assignCircuitTITO(signedMult_0x13, 0x13, nBits, 0);
        assignCircuitTITO(div_0x14, 0x14, 0, nBits);
        assignCircuitTITO(signedDiv_0x15, 0x15, 0, nBits);
        assignCircuitSISO(increment_0x16, 0x16);
        assignCircuitSISO(decrement_0x17, 0x17);

        assignHiOutputOneBit(0x16, increment_0x16.getOutput(nBits));
        assignHiOutputOneBit(0x17, decrement_0x17.getOutput(nBits));

        transistorCount += unsignedAdd_0x10.getTransistorCount() + signedAdd_0x11.getTransistorCount() +
                mult_0x12.getTransistorCount() + signedMult_0x13.getTransistorCount() +
                div_0x14.getTransistorCount() + signedDiv_0x15.getTransistorCount() +
                increment_0x16.getTransistorCount() + decrement_0x17.getTransistorCount();
    }

    private void initBitwiseOps() {
        bwAnd_0x20 = new BitwiseAnd(getLabel() + " BitwiseAnd", nBits);
        bwNand_0x21 = new BitwiseNand(getLabel() + " BitwiseNand", nBits);
        bwNor_0x22 = new BitwiseNor(getLabel() + " BitwiseNor", nBits);
        bwOr_0x23 = new BitwiseOr(getLabel() + " BitwiseOr", nBits);
        bwXor_0x24 = new BitwiseXor(getLabel() + " BitwiseXor", nBits);
        bwNot_0x25 = new Inverter(getLabel() + " BitwiseNot", nBits);

        assignCircuitTISO(bwAnd_0x20, 0x20, 0);
        assignCircuitTISO(bwNand_0x21, 0x21, 0);
        assignCircuitTISO(bwNor_0x22, 0x22, 0);
        assignCircuitTISO(bwOr_0x23, 0x23, 0);
        assignCircuitTISO(bwXor_0x24, 0x24, 0);
        assignCircuitSISO(bwNot_0x25, 0x25);

        transistorCount += bwAnd_0x20.getTransistorCount() + bwNand_0x21.getTransistorCount() +
                bwNor_0x22.getTransistorCount() + bwOr_0x23.getTransistorCount() +
                bwXor_0x24.getTransistorCount() + bwNot_0x25.getTransistorCount();
    }

    public void initComparison() {
        equals_0x26 = new Equals(getLabel() + "Equals", nBits);
        gt_0x27 = new GreaterThan(getLabel() + "GreaterThan", nBits);
        gte_0x28 = new GreaterThanOrEquals(getLabel() + "GreaterThanOrEquals", nBits);
        lt_0x29 = new LessThan(getLabel() + "LessThan", nBits);
        lte_0x2A = new LessThanOrEquals(getLabel() + "LessThanOrEquals", nBits);
        ne_0x2B = new NotEquals(getLabel() + "NotEquals", nBits);

        assignCircuitTISB(equals_0x26, 0x26);
        assignCircuitTISB(gt_0x27, 0x27);
        assignCircuitTISB(gte_0x28, 0x28);
        assignCircuitTISB(lt_0x29, 0x29);
        assignCircuitTISB(lte_0x2A, 0x2A);
        assignCircuitTISB(ne_0x2B, 0x2B);

        transistorCount += equals_0x26.getTransistorCount() + ne_0x2B.getTransistorCount() +
                gt_0x27.getTransistorCount() + gte_0x28.getTransistorCount() +
                lt_0x29.getTransistorCount() + lte_0x2A.getTransistorCount();
    }

    private void initShift() {
        slu_0x30 = new ShiftLeftUnsigned(getLabel() + "Shift Left Unsigned", nBits);
        sls_0x31 = new ShiftLeftSigned(getLabel() + "Shift Left Signed", nBits);
        slc_0x32 = new ShiftLeftCircle(getLabel() + "Shift Left Circle", nBits);
        sru_0x33 = new ShiftRightUnsigned(getLabel() + "Shift Right Unsigned", nBits);
        srs_0x34 = new ShiftRightSigned(getLabel() + "Shift Right Signed", nBits);
        src_0x35 = new ShiftRightCircle(getLabel() + "Shift Right Circle", nBits);

        assignCircuitSISO(slu_0x30, 0x30);
        assignCircuitSISO(sls_0x31, 0x31);
        assignCircuitSISO(slc_0x32, 0x32);
        assignCircuitSISO(sru_0x33, 0x33);
        assignCircuitSISO(srs_0x34, 0x34);
        assignCircuitSISO(src_0x35, 0x35);

        transistorCount += slu_0x30.getTransistorCount() + sru_0x33.getTransistorCount() +
                sls_0x31.getTransistorCount() + srs_0x34.getTransistorCount() +
                slc_0x32.getTransistorCount() + src_0x35.getTransistorCount();
    }

    @Override
    public void evaluateCircuit() {

        // Registers/Memory Evaluation
        for(InverterGate invOpCode : invertedOpCode) {
            invOpCode.evaluate();
        }
        registerWrite.evaluate();
        memoryWrite.evaluate();

        registers.evaluate();
        memory.evaluate();

        // Arithmetic Evaluation
        unsignedAdd_0x10.evaluate();
        signedAdd_0x11.evaluate();
        mult_0x12.evaluate();
        signedMult_0x13.evaluate();
        div_0x14.evaluate();
        signedDiv_0x15.evaluate();
        increment_0x16.evaluate();
        decrement_0x17.evaluate();

        // Bitwise Evaluation
        bwAnd_0x20.evaluate();
        bwNand_0x21.evaluate();
        bwNor_0x22.evaluate();
        bwOr_0x23.evaluate();
        bwXor_0x24.evaluate();
        bwNot_0x25.evaluate();

        // Comparison Evaluation
        equals_0x26.evaluate();
        gt_0x27.evaluate();
        gte_0x28.evaluate();
        lt_0x29.evaluate();
        lte_0x2A.evaluate();
        ne_0x2B.evaluate();

        // Shift Evaluation
        slu_0x30.evaluate();
        sls_0x31.evaluate();
        slc_0x32.evaluate();
        sru_0x33.evaluate();
        srs_0x34.evaluate();
        src_0x35.evaluate();

        outputHiArbitration.evaluate();
        outputLoArbitration.evaluate();
    }
}
