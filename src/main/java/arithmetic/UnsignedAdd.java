package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;

import logic_gates.OrGate;
import logic_gates.XorGate;
import org.jetbrains.annotations.NotNull;

public class UnsignedAdd extends DigitalCircuit {
    private AndGate[][] carryAnds;
    protected OrGate[] carryOrs;

    private AndGate[] outputAnds;
    private XorGate[] outputXors;
    private OrGate[] outputOrs;

    public UnsignedAdd() {
        super();
    }

    public UnsignedAdd(String label, int nBit) {
        super(label, nBit+nBit, nBit+1);

        // C_i = OR(AND(C_i-1,A), AND(A,B), AND(C_i-1, B)
        carryAnds = new AndGate[nBit][3];
        carryOrs = new OrGate[nBit];

        // O_i = OR(XOR(ABC), AND(ABC))
        outputAnds = new AndGate[nBit];
        outputXors = new XorGate[nBit];
        outputOrs = new OrGate[nBit];

        // Overflow = C_n-1
        CircuitNode[] outputNodes = new CircuitNode[nBit+1];

        for (int i = nBit-1; i >= 0; i--) {
            outputNodes[i] = new CircuitNode(label + " Output_" + i);

            carryAnds[i][0] = new AndGate(label + " CarryAnd_" + i + "_0", 2);
            carryAnds[i][0].assignInput(0, i<nBit-1 ? carryOrs[i+1].getOutput(0) : GND);
            carryAnds[i][0].assignInput(1, getPortOutput(i));
            transistorCount += carryAnds[i][0].getTransistorCount();

            carryAnds[i][1] = new AndGate(label + " CarryAnd_" + i + "_1", 2);
            carryAnds[i][1].assignInput(0, getPortOutput(i));
            carryAnds[i][1].assignInput(1, getPortOutput(i+nBit));
            transistorCount += carryAnds[i][1].getTransistorCount();

            carryAnds[i][2] = new AndGate(label + " CarryAnd_" + i + "_2", 2);
            carryAnds[i][2].assignInput(0, i<nBit-1 ? carryOrs[i+1].getOutput(0) : GND);
            carryAnds[i][2].assignInput(1, getPortOutput(i+nBit));
            transistorCount += carryAnds[i][2].getTransistorCount();

            carryOrs[i] = new OrGate(label + " CarryOr_" + i, 3);
            for (int j = 0; j < 3; j++) {
                carryOrs[i].assignInput(j, carryAnds[i][j].getOutput(0));
            }
            transistorCount += carryOrs[i].getTransistorCount();

            outputAnds[i] = new AndGate(label + " OutputAnd_" + i, 3);
            outputAnds[i].assignInput(0, getPortOutput(i));
            outputAnds[i].assignInput(1, getPortOutput(i+nBit));
            outputAnds[i].assignInput(2, i<nBit-1 ? carryOrs[i+1].getOutput(0) : GND);
            transistorCount += outputAnds[i].getTransistorCount();

            outputXors[i] = new XorGate(label + "OutputXor_" + i, 3);
            outputXors[i].assignInput(0, getPortOutput(i));
            outputXors[i].assignInput(1, getPortOutput(i+nBit));
            outputXors[i].assignInput(2, i<nBit-1 ? carryOrs[i+1].getOutput(0) : GND);
            transistorCount += outputXors[i].getTransistorCount();

            outputOrs[i] = new OrGate(label + " OutputOr_" + i, 2);
            outputOrs[i].assignInput(0, outputXors[i].getOutput(0));
            outputOrs[i].assignInput(1, outputAnds[i].getOutput(0));
            transistorCount += outputOrs[i].getTransistorCount();

            assignOutput(i, outputNodes[i]);
        }
        outputNodes[nBit] = new CircuitNode(label + " Output_" + nBit);
        assignOutput(nBit, outputNodes[nBit]);
    }

    public UnsignedAdd(String label, int nBit, @NotNull CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public UnsignedAdd(String label, int nBit, @NotNull CircuitNode[] outputs, @NotNull CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        if (i==getNumOutputs()-1) {
            carryOrs[0].assignOutput(0, output);
        } else if (i>=0 && i<getNumOutputs()-1) {
            outputOrs[i].assignOutput(0, output);
        }
        super.assignOutput(i, output);
    }

    public void evaluate() {
        super.evaluate();

        for (int i = getNumOutputs()-2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                carryAnds[i][j].evaluate();
            }
            carryOrs[i].evaluate();

            outputAnds[i].evaluate();
            outputXors[i].evaluate();
            outputOrs[i].evaluate();
        }
    }
}
