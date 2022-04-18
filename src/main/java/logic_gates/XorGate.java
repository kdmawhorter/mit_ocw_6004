package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import org.jetbrains.annotations.NotNull;

public class XorGate extends DigitalCircuit {
    private InverterGate[] inv;

    private NandGate[] calcNands;
    private NandGate outputNand;

    public XorGate() {
        super();
    }

    public XorGate(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }

    public XorGate(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        inv = new InverterGate[getNumInputs()];
        calcNands = new NandGate[getNumInputs()];

        outputNand = new NandGate(label + " Or", getNumInputs(), output);
        transistorCount += outputNand.getTransistorCount();

        for (int invIdx = 0; invIdx < getNumInputs(); invIdx++) {
            inv[invIdx] = new InverterGate(label + " Inv_" + invIdx);
            inv[invIdx].assignInput(0, getPortOutput(invIdx));
            transistorCount += inv[invIdx].getTransistorCount();
        }

        for (int nandIdx = 0; nandIdx < getNumInputs(); nandIdx++) {
            calcNands[nandIdx] = new NandGate(label + " And_" + nandIdx, getNumInputs());
            transistorCount += calcNands[nandIdx].getTransistorCount();
            for (int inputIdx = 0; inputIdx < getNumInputs(); inputIdx++) {
                calcNands[nandIdx].assignInput(inputIdx,
                        inputIdx!=nandIdx ? inv[inputIdx].getOutput(0) : getPortOutput(inputIdx));
            }
            outputNand.assignInput(nandIdx, calcNands[nandIdx].getOutput(0));
        }

        assignOutput(0, output);
    }

    public XorGate(String label, int nBit, CircuitNode output, CircuitNode[] inputs) {
        this(label, nBit, output);

        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        outputNand.assignOutput(0, output);
        super.assignOutput(i, output);
    }

    public void evaluate() {
        super.evaluate();

        for (int portIdx = 0; portIdx < getNumInputs(); portIdx++) {
            inv[portIdx].evaluate();
        }
        for (int nandIdx = 0; nandIdx < getNumInputs(); nandIdx++) {
            calcNands[nandIdx].evaluate();
        }
        outputNand.evaluate();
    }
}
