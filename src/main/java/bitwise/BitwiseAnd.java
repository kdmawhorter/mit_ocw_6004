package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.AndGate;

public class BitwiseAnd extends DigitalCircuit {

    protected final AndGate[] ands;

    public BitwiseAnd(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        ands = new AndGate[nBit];

        for (int i = 0; i < nBit; i++) {
            ands[i] = new AndGate(label + " And_" + i, 2);
            ands[i].assignInput(0, getInPortOutput(i));
            ands[i].assignInput(1, getInPortOutput(i+nBit));
            ands[i].assignOutput(getOutPortInput(i));
            transistorCount += ands[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(AndGate and : ands) {
            and.evaluate();
        }
    }
}
