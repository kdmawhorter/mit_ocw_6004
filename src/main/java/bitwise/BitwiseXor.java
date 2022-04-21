package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.XorGate;

/**
 * A class representing a Bitwise Xor operation.<br>
 * <br>
 A BitwiseXor object consists of:<br>
 * <ul>
 * <li>"Output" Xor gates tying together each pair of input bits. O[i] = Xor(A[i], B[i]).</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li>
 * </ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the bitwise xor output of A and B</li>
 * </ul>
 * <br>
 */
public class BitwiseXor extends DigitalCircuit {

    private final XorGate[] xors;

    /**
     * BitwiseXor constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public BitwiseXor(String label, int nBits) {
        super(label, nBits+nBits, nBits);

        xors = new XorGate[nBits];

        for (int i = 0; i < nBits; i++) {
            xors[i] = new XorGate(label + " Xor_" + i, 2);
            xors[i].assignInput(0, getInternalInput(i));
            xors[i].assignInput(1, getInternalInput(i+nBits));
            xors[i].assignOutput(getInternalOutput(i));
            transistorCount += xors[i].getTransistorCount();
        }
    }

    /**
     * Evaluates the Xor gate corresponding to each bit of A and B.
     */
    @Override
    protected void evaluateCircuit() {
        for(XorGate xor : xors) {
            xor.evaluate();
        }
    }
}
