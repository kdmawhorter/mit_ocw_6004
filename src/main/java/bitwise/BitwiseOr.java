package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.OrGate;

public class BitwiseOr extends DigitalCircuit {

    protected final OrGate[] ors;

    public BitwiseOr(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        ors = new OrGate[nBit];

        for (int i = 0; i < nBit; i++) {
            ors[i] = new OrGate(label + " Or_" + i, 2);
            ors[i].assignInput(0, getInPortOutput(i));
            ors[i].assignInput(1, getInPortOutput(i+nBit));
            ors[i].assignOutput(getOutPortInput(i));
            transistorCount += ors[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(OrGate or : ors) {
            or.evaluate();
        }
    }
}
