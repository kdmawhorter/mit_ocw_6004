package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import logic_gates.XorGate;
import org.jetbrains.annotations.NotNull;

public class Decrement extends DigitalCircuit {

    private AndGate[] borrowAnds;
    private XorGate[] outputXors;
    private InverterGate[] invPorts;

    public Decrement() { super(); }

    public Decrement(String label, int nBit) {
        super(label, nBit, nBit+1);

        borrowAnds = new AndGate[nBit];
        outputXors = new XorGate[nBit];
        invPorts = new InverterGate[nBit];

        for (int i = nBit - 1; i >= 0; i--) {
            invPorts[i] = new InverterGate(label + " InvPort_" + i);
            invPorts[i].assignInput(0, getPortOutput(i));
            transistorCount += invPorts[i].getTransistorCount();

            borrowAnds[i] = new AndGate(label + " BorrowAnd_" + i, 2);
            borrowAnds[i].assignInput(0, invPorts[i].getOutput(0));
            borrowAnds[i].assignInput(1, i<nBit-1 ? borrowAnds[i+1].getOutput(0) : VDD);
            transistorCount += borrowAnds[i].getTransistorCount();

            outputXors[i] = new XorGate(label + " OutputXor_" + i, 2);
            outputXors[i].assignInput(0, getPortOutput(i));
            outputXors[i].assignInput(1, i<nBit-1 ? borrowAnds[i+1].getOutput(0) : VDD);
            transistorCount += outputXors[i].getTransistorCount();

            assignOutput(i, outputXors[i].getOutput(0));
        }

        assignOutput(nBit, borrowAnds[0].getOutput(0));
    }

    public Decrement(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public Decrement(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        if (i == getNumOutputs() - 1) {
            borrowAnds[0].assignOutput(0, output);
        } else {
            outputXors[i].assignOutput(0, output);
        }
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        for (int i = getNumInputs() - 1; i >= 0; i--) {
            invPorts[i].evaluate();
            borrowAnds[i].evaluate();
            outputXors[i].evaluate();
        }
    }
}
