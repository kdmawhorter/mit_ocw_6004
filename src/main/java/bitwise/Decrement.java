package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import logic_gates.XorGate;

public class Decrement extends DigitalCircuit {

    private final AndGate[] borrowAnds;
    private final XorGate[] outputXors;
    private final InverterGate[] invPorts;

    public Decrement(String label, int nBits) {
        super(label, nBits, nBits+1);

        borrowAnds = new AndGate[nBits];
        outputXors = new XorGate[nBits];
        invPorts = new InverterGate[nBits];

        for (int i = nBits - 1; i >= 0; i--) {
            invPorts[i] = new InverterGate(label + " InvPort_" + i);
            invPorts[i].assignInput(0, getInPortOutput(i));
            transistorCount += invPorts[i].getTransistorCount();

            borrowAnds[i] = new AndGate(label + " BorrowAnd_" + i, 2);
            borrowAnds[i].assignInput(0, invPorts[i].getOutput(0));
            borrowAnds[i].assignInput(1, i<nBits-1 ? borrowAnds[i+1].getOutput(0) : VDD);
            transistorCount += borrowAnds[i].getTransistorCount();

            outputXors[i] = new XorGate(label + " OutputXor_" + i, 2);
            outputXors[i].assignInput(0, getInPortOutput(i));
            outputXors[i].assignInput(1, i<nBits-1 ? borrowAnds[i+1].getOutput(0) : VDD);
            outputXors[i].assignOutput(getOutPortInput(i));
            transistorCount += outputXors[i].getTransistorCount();
        }
        borrowAnds[0].assignOutput(getOutPortInput(nBits));
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = getNumInputs() - 1; i >= 0; i--) {
            invPorts[i].evaluate();
            borrowAnds[i].evaluate();
            outputXors[i].evaluate();
        }
    }
}
