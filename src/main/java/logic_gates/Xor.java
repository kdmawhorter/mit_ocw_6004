package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import org.jetbrains.annotations.NotNull;

public class Xor extends DigitalCircuit {
    private And[] and;
    private Inverter[] inv;
    private Or or;

    public Xor() {
        super();
    }

    public Xor(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }

    public Xor(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        inv = new Inverter[getNumInputs()];
        and = new And[getNumInputs()];

        or = new Or(label + " Or", getNumInputs(), output);

        for (int i = 0; i < getNumInputs(); i++) {
            inv[i] = new Inverter(label + " Inv_" + i);
            inv[i].assignInput(0, getPortOutput(i));
        }

        for (int andIdx = 0; andIdx < getNumInputs(); andIdx++) {
            and[andIdx] = new And(label + " And_" + andIdx, getNumInputs());
            for (int inputIdx = 0; inputIdx < getNumInputs(); inputIdx++) {
                and[andIdx].assignInput(inputIdx,
                        inputIdx!=andIdx ? inv[inputIdx].getOutput(0) : getPortOutput(inputIdx));
            }
            or.assignInput(andIdx, and[andIdx].getOutput(0));
        }

        assignOutput(0, output);
    }

    public Xor(String label, int nBit, CircuitNode output, CircuitNode[] inputs) {
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
