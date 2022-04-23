package comparison;

import logic_gates.InverterGate;

/**
 * A class representing a not equals operation.<br>
 * <br>
 A NotEquals object consists of:<ul>
 * <li>nBit {@link BitLessThan} objects, cascaded to carry forward whether A is less than or equal to B, from {@link
 * Comparator}</li>
 * <li>a 1-bit inverter to invert the A==B output from above.</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: 1 bit corresponding to:<ul>
 * <li>1 bit representing whether A!=B</li></ul>
 */
public class NotEquals extends Comparator {

    private final InverterGate notEqualsOutput;

    /**
     * NotEquals constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    NotEquals(String label, int nBits) {
        super(label, nBits);

        notEqualsOutput = new InverterGate(label + " NotEqualsOutput");
        notEqualsOutput.assignInput(getEqualsOutput());
        notEqualsOutput.assignOutput(getInternalOutput());

        transistorCount += notEqualsOutput.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();
        notEqualsOutput.evaluate();
    }
}
