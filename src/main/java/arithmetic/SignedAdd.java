package arithmetic;

import core_architecture.CircuitNode;
import logic_gates.InverterGate;
import logic_gates.NandGate;

/**
 * A class representing a signed addition operation in 2s Complement.<br>
 * <br>
 A SignedAdd object consists of:<br>
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
 * <li>1 4-bit "Output" Nand for each bit, representing the outer Nand above.</li>
 * <br>
 * Overflow Logic:<br>
 * Overflow = NAND(NAND(A[0],B[0],!O[0]), NAND(!A[0],!B[0],O[0]))
 * <li>3 1-bit Inverters for sign bit of A, B, O</li>
 * <li>2 3-bit Nands for the two inner Nands above</li>
 * <li>1 2-bit Nand for outer Nand above</li></ul>
 *
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: nBits+1 corresponding to:<ul>
 * <li>an nBit bit string representing the signed addition of A and B in 2s Complement.</li>
 * <li>a 1 bit overflow bit</li></ul>
 */
public class SignedAdd extends UnsignedAdd {

    private final NandGate twoPosMakesNeg;
    private final NandGate twoNegMakesPos;
    private final NandGate overflowNand;

    private final InverterGate invertedASign;
    private final InverterGate invertedBSign;
    private final InverterGate invertedOutputSign;

    /**
     * UnsignedAdd constructor
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public SignedAdd(String label, int nBits) {
        super(label, nBits);

        invertedASign = new InverterGate(label + " Inverted A Sign Bit");
        invertedASign.assignInput(getInternalInput(0));
        transistorCount += invertedASign.getTransistorCount();

        invertedBSign = new InverterGate(label + " Inverted B Sign Bit");
        invertedBSign.assignInput(0, getInternalInput(nBits));
        transistorCount += invertedBSign.getTransistorCount();

        invertedOutputSign = new InverterGate(label + " Inverted Output Sign Bit");
        invertedOutputSign.assignInput(getInternalOutput(0));
        transistorCount += invertedOutputSign.getTransistorCount();

        twoPosMakesNeg = new NandGate(label + " Positive Overflow Nand", 3);
        twoPosMakesNeg.assignInput(0, invertedASign.getOutput(0));
        twoPosMakesNeg.assignInput(1, invertedBSign.getOutput(0));
        twoPosMakesNeg.assignInput(2, getInternalOutput(0));
        transistorCount += twoPosMakesNeg.getTransistorCount();

        twoNegMakesPos = new NandGate(label + " Negative Overflow And", 3);
        twoNegMakesPos.assignInput(0, getInternalInput(0));
        twoNegMakesPos.assignInput(1, getInternalInput(nBits));
        twoNegMakesPos.assignInput(2, invertedOutputSign.getOutput(0));
        transistorCount += twoNegMakesPos.getTransistorCount();

        overflowNand = new NandGate(label + "Overflow Or", 2);
        overflowNand.assignInput(0, twoPosMakesNeg.getOutput(0));
        overflowNand.assignInput(1, twoNegMakesPos.getOutput(0));
        overflowNand.assignOutput(getInternalOutput(nBits));
        transistorCount += overflowNand.getTransistorCount();

        carryNands[0].assignOutput(new CircuitNode(
                "Dummy Circuit Node to Prevent Unsigned Carry Overwrite of Signed Overflow Bit"));

    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();

        invertedASign.evaluate();
        invertedBSign.evaluate();
        invertedOutputSign.evaluate();

        twoPosMakesNeg.evaluate();
        twoNegMakesPos.evaluate();

        overflowNand.evaluate();
    }
}
