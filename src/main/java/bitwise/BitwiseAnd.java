package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import org.jetbrains.annotations.NotNull;

public class BitwiseAnd extends DigitalCircuit {

    protected AndGate[] ands;

    public BitwiseAnd() { super(); }

    public BitwiseAnd(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        ands = new AndGate[nBit];

        for (int i = 0; i < nBit; i++) {
            ands[i] = new AndGate(label + " And_" + i, 2);
            ands[i].assignInput(0, getPortOutput(i));
            ands[i].assignInput(1, getPortOutput(i+nBit));

            transistorCount += ands[i].getTransistorCount();

            assignOutput(i, ands[i].getOutput(0));
        }
    }

    public BitwiseAnd(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public BitwiseAnd(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        ands[i].assignOutput(0, output);
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();
        for(AndGate and : ands) {
            and.evaluate();
        }
    }
}
