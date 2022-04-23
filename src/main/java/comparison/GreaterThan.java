package comparison;

import logic_gates.InverterGate;
import logic_gates.OrGate;

/**
 * A class representing a greater than operation.<br>
 * <br>
 A GreaterThan object consists of:<ul>
 * <li>nBit {@link BitLessThan} objects, cascaded to carry forward whether A is less than or equal to B, from {@link
 * Comparator}</li>
 * <li>a 2-bit or to determine whether A<=B </li>
 * <li>a 1-bit inverter to invert A<=B to produce if A>B.</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: 1 bit corresponding to:<ul>
 * <li>1 bit representing whether A>B</li></ul>
 */
public class GreaterThan extends Comparator {
    private final InverterGate outputInverter;
    private final OrGate lessThanOrEquals;

    /**
     * GreaterThan constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public GreaterThan(String label, int nBits) {
        super(label, nBits);

        lessThanOrEquals = new OrGate(label + " LTE_Or", 2);
        lessThanOrEquals.assignInput(0, getLessThanOutput());
        lessThanOrEquals.assignInput(1, getEqualsOutput());

        outputInverter = new InverterGate(label + " OutputInverter");
        outputInverter.assignInput(lessThanOrEquals.getOutput());
        outputInverter.assignOutput(getInternalOutput());

        transistorCount += lessThanOrEquals.getTransistorCount() + outputInverter.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();

        lessThanOrEquals.evaluate();
        outputInverter.evaluate();
    }
}
