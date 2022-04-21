package arithmetic;

import bitwise.Increment;
import bitwise.Inverter;
import core_architecture.DigitalCircuit;

/**
 * A class representing a Negation operation in 2s complement.<br>
 * <br>
 A Negate object consists of:<br>
 * <ul>
 * <li>Inverter Bitwise Operator</li>
 * <li>Incrementer Bitwise Operator</li></ul>
 *
 * Output = Increment(Negate(Input))<br>
 * <br>
 * <b>Inputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A to negate</li></ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the negative of A in 2s Complement</li></ul>
 */
public class Negate extends DigitalCircuit {

    final Inverter inverter;
    final Increment incrementer;

    /**
     * Negate constructor
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in the input.
     */
    public Negate(String label, int nBits) {
        super(label, nBits, nBits);

        inverter = new Inverter(label + " Inverter", nBits);
        inverter.assignInputs(getInternalInputs());

        incrementer = new Increment(label + " Incrementer", nBits);
        incrementer.assignInputs(inverter.getOutputs());
        for (int i = 0; i < nBits; i++) {
            incrementer.assignOutput(i, getInternalOutput(i));
        }

        transistorCount = inverter.getTransistorCount() + incrementer.getTransistorCount();
    }

    /**
     * First evaluates the inverter, then evaluates the incrementer.
     */
    @Override
    protected void evaluateCircuit() {
        inverter.evaluate();
        incrementer.evaluate();
    }
}
