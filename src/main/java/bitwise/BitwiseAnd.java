package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.AndGate;

/**
 * A class representing a Bitwise And operation.<br>
 * <br>
 A BitwiseAnd object consists of:<br>
 * <ul>
 * <li>"Output" And gates tying together each pair of input bits. O[i] = And(A[i], B[i]).</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li>
 * </ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the bitwise and output of A and B</li>
 * </ul>
 * <br>
 */
public class BitwiseAnd extends DigitalCircuit {

    private final AndGate[] ands;

    /**
     * BitwiseAnd constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public BitwiseAnd(String label, int nBits) {
        super(label, nBits+nBits, nBits);

        ands = new AndGate[nBits];

        for (int i = 0; i < nBits; i++) {
            ands[i] = new AndGate(label + " And_" + i, 2);
            ands[i].assignInput(0, getInternalInput(i));
            ands[i].assignInput(1, getInternalInput(i+nBits));
            ands[i].assignOutput(getInternalOutput(i));
            transistorCount += ands[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(AndGate and : ands) {
            and.evaluate();
        }
    }
}
