package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.XorGate;

public class BitwiseXor extends DigitalCircuit {

    protected final XorGate[] xors;

    public BitwiseXor(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        xors = new XorGate[nBit];

        for (int i = 0; i < nBit; i++) {
            xors[i] = new XorGate(label + " Xor_" + i, 2);
            xors[i].assignInput(0, getInternalInput(i));
            xors[i].assignInput(1, getInternalInput(i+nBit));
            xors[i].assignOutput(getInternalOutput(i));
            transistorCount += xors[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(XorGate xor : xors) {
            xor.evaluate();
        }
    }
}
