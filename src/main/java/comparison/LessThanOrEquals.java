package comparison;

import logic_gates.OrGate;

/**
 * A class representing a less than or equals operation.<br>
 * <br>
 A LessThanEquals object consists of:<ul>
 * <li>nBit {@link BitLessThan} objects, cascaded to carry forward whether A is less than or equal to B, from {@link
 * Comparator}</li>
 * <li>1 2-bit Or to tie together the BitLessThan outputs of Less than and Equal</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: 1 bit corresponding to:<ul>
 * <li>1 bit representing whether A<=B</li></ul>
 */
public class LessThanOrEquals extends Comparator {

    private final OrGate outputOr;

    /**
     * LessThanOrEquals constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    LessThanOrEquals(String label, int nBits) {
        super(label, nBits);

        outputOr = new OrGate(label + " OutputOr", 2);
        outputOr.assignInput(0, getLessThanOutput());
        outputOr.assignInput(1, getEqualsOutput());
        outputOr.assignOutput(getInternalOutput());

        transistorCount += outputOr.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();
        outputOr.evaluate();
    }
}
