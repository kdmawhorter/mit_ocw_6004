package comparison;

/**
 * A class representing a less than operation.<br>
 * <br>
 A LessThan object consists of:<ul>
 * <li>nBit {@link BitLessThan} objects, cascaded to carry forward whether A is less than or equal to B, from {@link
 * Comparator}</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: 1 bit corresponding to:<ul>
 * <li>1 bit representing whether A < B</li></ul>
 */
public class LessThan extends Comparator {

    /**
     * LessThan constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    LessThan(String label, int nBits) {
        super(label, nBits);

        setInternalOutput(getLessThanOutput());
    }
}
