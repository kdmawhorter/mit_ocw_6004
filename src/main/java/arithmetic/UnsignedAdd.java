package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.*;

/**
 * A class representing an unsigned addition operation.<br>
 * <br>
 An UnsignedAdd object consists of:<br>
 * <ul>
 * Carry Logic:<br>
 * C[i] = NAND(NAND(C[i+1], A[i]), NAND(A[i], B[i]), NAND(C[i+1])<br>
 * <li>3 2-bit "Carry Calc" Nands for each bit, representing the three internal Nands above. </li>
 * <li>1 3-bit "Carry" Nand for each bit, representing the outer Nand above.</li>
 * <br>
 * Inverter Logic:<br>
 * <li> 3 1-bit inverters for each bit of A, B, and the "Carry" Nands above</li>
 * <br>
 * Output Logic:<br>
 * O[i] = NAND(NAND(A[i],B[i],C[i+1]), NAND(A[i],B[i],!C[i+1]), NAND(A[i],!B[i],C[i+1]), NAND(!A[i],B[i],C[i+1]))
 * <li>4 3-bit "Output Calc" Nands for each bit, representing the four internal Nands above.</li>
 * <li>1 4-bit "Output" Nand for each bit, representing the outer Nand above.</li></ul>
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: nBits+1 corresponding to:<ul>
 * <li>an nBit bit string representing the unsigned addition of A and B.</li>
 * <li>a 1 bit overflow bit</li></ul>
 */

public class UnsignedAdd extends DigitalCircuit {
    private final NandGate[][] carryCalcNands;
    protected final NandGate[] carryNands;

    private final InverterGate[][] invPortsCarrys;
    private final NandGate[][] outputCalcNands;
    private final NandGate[] outputNands;

    /**
     * UnsignedAdd constructor
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public UnsignedAdd(String label, int nBits) {
        super(label, nBits + nBits, nBits + 1);

        invPortsCarrys = new InverterGate[nBits][3];
        carryCalcNands = new NandGate[nBits][3];
        carryNands = new NandGate[nBits];
        outputCalcNands = new NandGate[nBits][4];
        outputNands = new NandGate[nBits];
        
        initCarryGates(nBits);
        initOutGates(nBits);
    }

    /**
     * Construct the "Carry Calc" and "Carry" Nand logic described in the documentation for this class.
     * @param nBits The number of bits in each input.
     */
    private void initCarryGates(int nBits) {
        for (int i = nBits - 1; i >= 0; i--) {
            CircuitNode[] carryCalcNandSrcArray = {i < nBits - 1 ? carryNands[i + 1].getOutput() : GND,
                    getInternalInput(i),
                    getInternalInput(i + nBits)};

            int[][] carryCalcNandSrcIdxs = {{0, 1}, {1, 2}, {0, 2}};

            carryNands[i] = new NandGate(getLabel() + " CarryNand_" + i, 3);
            transistorCount += carryNands[i].getTransistorCount();

            for (int j = 0; j < 3; j++) {
                carryCalcNands[i][j] = new NandGate(getLabel() + " CarryCalcNand_" + i + "_" + j, 2);
                carryCalcNands[i][j].assignInput(0, carryCalcNandSrcArray[carryCalcNandSrcIdxs[j][0]]);
                carryCalcNands[i][j].assignInput(1, carryCalcNandSrcArray[carryCalcNandSrcIdxs[j][1]]);

                invPortsCarrys[i][j] = new InverterGate(getLabel() + " Inverted_" + i + "_" + j);
                invPortsCarrys[i][j].assignInput(carryCalcNandSrcArray[j]);

                transistorCount += carryCalcNands[i][j].getTransistorCount() + invPortsCarrys[i][j].getTransistorCount();
                carryNands[i].assignInput(j, carryCalcNands[i][j].getOutput());
            }
        }
        carryNands[0].assignOutput(getInternalOutput(nBits));
    }

    /**
     * Construct the Inverter, "Output Calc," and "Output" Nand logic described in the documentation for this class.
     * @param nBits The number of bits in each input.
     */
    private void initOutGates(int nBits) {
        for (int i = nBits - 1; i >= 0; i--) {
            CircuitNode[][] outputCalcNandSrcArray = {
                    { i<nBits-1 ? carryNands[i+1].getOutput() : GND,
                      getInternalInput(i),
                      getInternalInput(i+nBits) },

                    { invPortsCarrys[i][0].getOutput(),
                      invPortsCarrys[i][1].getOutput(),
                      invPortsCarrys[i][2].getOutput() } };

            int[][] outputCalcNandSrcIdxs = { {0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 0} };

            outputNands[i] = new NandGate(getLabel() + " OutputNand_" + i, 4);
            transistorCount += outputNands[i].getTransistorCount();

            for (int j = 0; j < 4; j++) {
                outputCalcNands[i][j] = new NandGate(getLabel() + " OutputCalcNand_" + i + "_" + j, 3);
                outputCalcNands[i][j].assignInput(0, outputCalcNandSrcArray[outputCalcNandSrcIdxs[j][0]][0] );
                outputCalcNands[i][j].assignInput(1, outputCalcNandSrcArray[outputCalcNandSrcIdxs[j][1]][1] );
                outputCalcNands[i][j].assignInput(2, outputCalcNandSrcArray[outputCalcNandSrcIdxs[j][2]][2] );
                outputCalcNands[i][j].assignOutput(outputNands[i].getInput(j));

                transistorCount += outputCalcNands[i][j].getTransistorCount();
            }
            outputNands[i].assignOutput(getInternalOutput(i));
        }
    }

    /**
     * For each bit of the output nBit bit string, starting from the least significant bit (nBit-1):<ul>
     *     <li>Evaluate the "Carry Calc" Nands</li>
     *     <li>Evaluate the "Carry" Nands</li>
     *     <li>Evaluate the Inverters</li>
     *     <li>Evaluate the "Output Calc" Nands</li>
     *     <li>Evaluate the "Output" Nands</li>
     * </ul>
     */
    @Override
    protected void evaluateCircuit() {
        for (int i = getNumOutputs()-2; i >= 0; i--) {
            for (NandGate carryCalcNand : carryCalcNands[i]) {
                carryCalcNand.evaluate();
            }
            carryNands[i].evaluate();

            for (InverterGate invPort : invPortsCarrys[i]) {
                invPort.evaluate();
            }

            for (NandGate outputCalcNand : outputCalcNands[i]) {
                outputCalcNand.evaluate();
            }
            outputNands[i].evaluate();
        }
    }
}
