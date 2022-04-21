package arithmetic;

import bitwise.Increment;
import bitwise.Inverter;
import core_architecture.DigitalCircuit;

public class Negate extends DigitalCircuit {

    final Inverter inverter;
    final Increment incrementer;

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

    @Override
    protected void evaluateCircuit() {
        inverter.evaluate();
        incrementer.evaluate();
    }
}
