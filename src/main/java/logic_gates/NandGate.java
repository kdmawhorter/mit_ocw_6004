package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import core_architecture.Nfet;
import core_architecture.Pfet;
import org.jetbrains.annotations.NotNull;

public class NandGate extends DigitalCircuit {
    private Pfet[] pfets;
    private Nfet[] nfets;

    public NandGate() {
        super();
    }

    public NandGate(String label, int nBits) {
        this(label, nBits, new CircuitNode(label + " Output"));
    }

    public NandGate(String label, int nBits, CircuitNode output) {
        super(label, Math.max(nBits, 1), 1);

        CircuitNode[] pullDownNodes = new CircuitNode[getNumInputs()];
        pfets = new Pfet[getNumInputs()];
        nfets = new Nfet[getNumInputs()];


        for (int i = 0; i < getNumInputs(); i++) {
            pullDownNodes[i] = new CircuitNode(label + " NMOS node " + i + "-" + (i+1));

            nfets[i] = new Nfet(label + " Nfet_" + i, pullDownNodes[i], getPortOutput(i),
                    (i-1>=0) ? pullDownNodes[i-1] : DigitalCircuit.GND);

            pfets[i] = new Pfet(label + " Pfet_" + i, output, getPortOutput(i), DigitalCircuit.VDD);

            transistorCount += pfets[i].getTransistorCount() + nfets[i].getTransistorCount();

        }
        assignOutput(0, output);
    }

    public NandGate(String label, int nBits, CircuitNode output, CircuitNode[] inputs) {
        this(label, nBits, output);

        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        for (Pfet pfet : pfets) {
            pfet.assignOutput(output);
        }
        nfets[nfets.length-1].assignOutput(0, output);

        super.assignOutput(0, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        for (int i = 0; i < getNumInputs(); i++) {
            pfets[i].evaluate();
            nfets[i].evaluate();
        }
    }
}
