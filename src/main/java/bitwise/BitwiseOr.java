package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.OrGate;

/**
 * A class representing a Bitwise Or operation.<br>
 * <br>
 A BitwiseOr object consists of:<br>
 * <ul>
 * <li>"Output" Or gates tying together each pair of input bits. O[i] = Or(A[i], B[i]).</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li>
 * </ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the bitwise or output of A and B</li>
 * </ul>
 * <br>
 */
public class BitwiseOr extends DigitalCircuit {

    private final OrGate[] ors;

    /**
     * BitwiseOr constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public BitwiseOr(String label, int nBits) {
        super(label, nBits+nBits, nBits);

        ors = new OrGate[nBits];

        for (int i = 0; i < nBits; i++) {
            ors[i] = new OrGate(label + " Or_" + i, 2);
            ors[i].assignInput(0, getInternalInput(i));
            ors[i].assignInput(1, getInternalInput(i+nBits));
            ors[i].assignOutput(getInternalOutput(i));
            transistorCount += ors[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(OrGate or : ors) {
            or.evaluate();
        }
    }
}
