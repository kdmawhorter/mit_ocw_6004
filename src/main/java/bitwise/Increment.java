package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.XorGate;
import org.jetbrains.annotations.NotNull;

public class Increment extends DigitalCircuit {

    AndGate[] carryAnd;
    XorGate[] outputXor;

    public Increment() { super(); }

    public Increment(String label, int nBits) {
        super(label, nBits, nBits+1);

        carryAnd = new AndGate[nBits];
        outputXor = new XorGate[nBits];

        for (int i = nBits - 1; i >= 0; i--) {
            carryAnd[i] = new AndGate(label + " CarryAnd_" + i, 2);
            carryAnd[i].assignInput(0, getPortOutput(i));
            carryAnd[i].assignInput(1, i<nBits-1 ? carryAnd[i+1].getOutput(0) : VDD);
            transistorCount += carryAnd[i].getTransistorCount();

            outputXor[i] = new XorGate(label + " OutputXor_" + i, 2);
            outputXor[i].assignInput(0, getPortOutput(i));
            outputXor[i].assignInput(1, i<nBits-1 ? carryAnd[i+1].getOutput(0) : VDD);
            transistorCount += outputXor[i].getTransistorCount();

            assignOutput(i, outputXor[i].getOutput(0));
        }

        assignOutput(nBits, carryAnd[0].getOutput(0));
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        if (i==getNumOutputs()-1) {
            carryAnd[0].assignOutput(0, output);
        } else {
            outputXor[i].assignOutput(0, output);
        }
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        for (int i = getNumOutputs() - 2; i >= 0; i--) {
            carryAnd[i].evaluate();
            outputXor[i].evaluate();
        }
    }
}
