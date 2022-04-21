package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.NorGate;

public class BitwiseNor extends DigitalCircuit {

    protected final NorGate[] nors;

    public BitwiseNor(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        nors = new NorGate[nBit];

        for (int i = 0; i < nBit; i++) {
            nors[i] = new NorGate(label + " Nor_" + i, 2);
            nors[i].assignInput(0, getInternalInput(i));
            nors[i].assignInput(1, getInternalInput(i+nBit));
            nors[i].assignOutput(getInternalOutput(i));
            transistorCount += nors[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(NorGate nor : nors) {
            nor.evaluate();
        }
    }
}