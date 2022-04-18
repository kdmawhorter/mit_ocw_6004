package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.XorGate;
import org.jetbrains.annotations.NotNull;

public class BitwiseXor extends DigitalCircuit {

    protected XorGate[] xors;

    public BitwiseXor() { super(); }

    public BitwiseXor(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        xors = new XorGate[nBit];

        for (int i = 0; i < nBit; i++) {
            xors[i] = new XorGate(label + " Xor_" + i, 2);
            xors[i].assignInput(0, getPortOutput(i));
            xors[i].assignInput(1, getPortOutput(i+nBit));

            transistorCount += xors[i].getTransistorCount();

            assignOutput(i, xors[i].getOutput(0));
        }
    }

    public BitwiseXor(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public BitwiseXor(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        xors[i].assignOutput(0, output);
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();
        for(XorGate xor : xors) {
            xor.evaluate();
        }
    }
}
