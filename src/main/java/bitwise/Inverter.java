package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.InverterGate;

public class Inverter extends DigitalCircuit {

    private final InverterGate[] inverters;

    public Inverter(String label, int nBit) {
        super(label, nBit, nBit);

        inverters = new InverterGate[getNumInputs()];

        for (int i = 0; i < getNumInputs(); i++) {
            inverters[i] = new InverterGate(label + " InverterGate_" + i);
            inverters[i].assignOutput(getOutPortInput(i));
            inverters[i].assignInput(getInPortOutput(i));
            transistorCount += inverters[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for (InverterGate inverter : inverters) {
            inverter.evaluate();
        }
    }

}
