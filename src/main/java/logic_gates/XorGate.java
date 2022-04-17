package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import org.jetbrains.annotations.NotNull;

public class XorGate extends DigitalCircuit {
    private AndGate[] and;
    private InverterGate[] inv;
    private OrGate or;

    public XorGate() {
        super();
    }

    public XorGate(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }

    public XorGate(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        inv = new InverterGate[getNumInputs()];
        and = new AndGate[getNumInputs()];

        or = new OrGate(label + " Or", getNumInputs(), output);
        transistorCount += or.getTransistorCount();

        for (int i = 0; i < getNumInputs(); i++) {
            inv[i] = new InverterGate(label + " Inv_" + i);
            inv[i].assignInput(0, getPortOutput(i));
            transistorCount += inv[i].getTransistorCount();
        }

        for (int andIdx = 0; andIdx < getNumInputs(); andIdx++) {
            and[andIdx] = new AndGate(label + " And_" + andIdx, getNumInputs());
            transistorCount += and[andIdx].getTransistorCount();
            for (int inputIdx = 0; inputIdx < getNumInputs(); inputIdx++) {
                and[andIdx].assignInput(inputIdx,
                        inputIdx!=andIdx ? inv[inputIdx].getOutput(0) : getPortOutput(inputIdx));
            }
            or.assignInput(andIdx, and[andIdx].getOutput(0));
        }

        assignOutput(0, output);
    }

    public XorGate(String label, int nBit, CircuitNode output, CircuitNode[] inputs) {
        this(label, nBit, output);

        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        or.assignOutput(0, output);
        super.assignOutput(i, output);
    }

    public void evaluate() {
        super.evaluate();

        for (int portIdx = 0; portIdx < getNumInputs(); portIdx++) {
            inv[portIdx].evaluate();
        }
        for (int andIdx = 0; andIdx < getNumInputs(); andIdx++) {
            and[andIdx].evaluate();
        }
        or.evaluate();
    }
}
