package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.XorGate;

public class Increment extends DigitalCircuit {

    final AndGate[] carryAnd;
    final XorGate[] outputXor;

    public Increment(String label, int nBits) {
        super(label, nBits, nBits+1);

        carryAnd = new AndGate[nBits];
        outputXor = new XorGate[nBits];

        for (int i = nBits - 1; i >= 0; i--) {
            carryAnd[i] = new AndGate(label + " CarryAnd_" + i, 2);
            carryAnd[i].assignInput(0, getInternalInput(i));
            carryAnd[i].assignInput(1, i<nBits-1 ? carryAnd[i+1].getOutput(0) : VDD);
            transistorCount += carryAnd[i].getTransistorCount();

            outputXor[i] = new XorGate(label + " OutputXor_" + i, 2);
            outputXor[i].assignInput(0, getInternalInput(i));
            outputXor[i].assignInput(1, i<nBits-1 ? carryAnd[i+1].getOutput(0) : VDD);
            outputXor[i].assignOutput(getInternalOutput(i));
            transistorCount += outputXor[i].getTransistorCount();
        }

        carryAnd[0].assignOutput(getInternalOutput(nBits));
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = getNumOutputs() - 2; i >= 0; i--) {
            carryAnd[i].evaluate();
            outputXor[i].evaluate();
        }
    }
}
