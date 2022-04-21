package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.InverterGate;

/**
 * A class representing an Invert operation.<br>
 * <br>
 An Inverter object consists of:<br>
 * <ul>
 * <li>"Output" Inverters for each input bit. O[i] = Not(A[i])</li></ul>
 *
 * <b>Inputs</b>: nBits corresponding to:<ul>
 *  <li>an nBit bit string representing an input A to invert</li></ul>
 *  <b>Outputs</b>: nBits corresponding to:<ul>
 *  <li>an nBit bit string representing the inverted bit string</li></ul>
 */
public class Inverter extends DigitalCircuit {

    private final InverterGate[] inverters;

    /**
     * Decrement constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in the input.
     */
    public Inverter(String label, int nBits) {
        super(label, nBits, nBits);

        inverters = new InverterGate[getNumInputs()];

        for (int i = 0; i < getNumInputs(); i++) {
            inverters[i] = new InverterGate(label + " InverterGate_" + i);
            inverters[i].assignOutput(getInternalOutput(i));
            inverters[i].assignInput(getInternalInput(i));
            transistorCount += inverters[i].getTransistorCount();
        }
    }

    /**
     * Evaluates the Inverter gate corresponding to each bit of A.
     */
    @Override
    protected void evaluateCircuit() {
        for (InverterGate inverter : inverters) {
            inverter.evaluate();
        }
    }

}
