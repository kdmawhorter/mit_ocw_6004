package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.OrGate;
import org.jetbrains.annotations.NotNull;

public class BitwiseOr extends DigitalCircuit {

    protected OrGate[] ors;

    public BitwiseOr() { super(); }

    public BitwiseOr(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        ors = new OrGate[nBit];

        for (int i = 0; i < nBit; i++) {
            ors[i] = new OrGate(label + " Or_" + i, 2);
            ors[i].assignInput(0, getPortOutput(i));
            ors[i].assignInput(1, getPortOutput(i+nBit));

            transistorCount += ors[i].getTransistorCount();

            assignOutput(i, ors[i].getOutput(0));
        }
    }

    public BitwiseOr(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public BitwiseOr(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        ors[i].assignOutput(0, output);
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();
        for(OrGate or : ors) {
            or.evaluate();
        }
    }
}