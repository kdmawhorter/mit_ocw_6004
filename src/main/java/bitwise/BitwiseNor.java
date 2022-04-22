package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.NorGate;

/**
 * A class representing a Bitwise Nor operation.<br>
 * <br>
 A BitwiseNor object consists of:<br>
 * <ul>
 * <li>"Output" Nor gates tying together each pair of input bits. O[i] = Nor(A[i], B[i]).</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li>
 * </ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the bitwise Nor output of A and B</li>
 * </ul>
 * <br>
 */
public class BitwiseNor extends DigitalCircuit {

    private final NorGate[] nors;

    /**
     * BitwiseNor constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public BitwiseNor(String label, int nBits) {
        super(label, nBits+nBits, nBits);

        nors = new NorGate[nBits];

        for (int i = 0; i < nBits; i++) {
            nors[i] = new NorGate(label + " Nor_" + i, 2);
            nors[i].assignInput(0, getInternalInput(i));
            nors[i].assignInput(1, getInternalInput(i+nBits));
            nors[i].assignOutput(getInternalOutput(i));
            transistorCount += nors[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(NorGate nor : nors) {
            nor.evaluate();
        }
    }
}