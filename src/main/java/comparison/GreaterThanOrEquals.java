package comparison;

import logic_gates.InverterGate;

/**
 * A class representing a greater than or equals operation.<br>
 * <br>
 A GreaterThanOrEquals object consists of:<ul>
 * <li>nBit {@link BitLessThan} objects, cascaded to carry forward whether A is less than or equal to B, from {@link
 * Comparator}</li>
 * <li>a 1-bit inverter on the less than output to produce A>=B.</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: 1 bit corresponding to:<ul>
 * <li>1 bit representing whether A>=B</li></ul>
 */
public class GreaterThanOrEquals extends Comparator {
    private final InverterGate outputInverter;

    /**
     * GreaterThanOrEquals constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    GreaterThanOrEquals(String label, int nBits) {
        super(label, nBits);

        outputInverter = new InverterGate(label + " OutputInverter");
        outputInverter.assignInput(getLessThanOutput());
        outputInverter.assignOutput(getInternalOutput());

        transistorCount += outputInverter.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();

        outputInverter.evaluate();
    }
}
