package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import core_architecture.Nfet;
import core_architecture.Pfet;
import org.jetbrains.annotations.NotNull;

public class NorGate extends DigitalCircuit {
    private Pfet[] pfets;
    private Nfet[] nfets;

    public NorGate() {
        super();
    }

    public NorGate(String label, int nBits) {
        this(label, nBits, new CircuitNode(label + " Output"));
    }

    public NorGate(String label, int nBits, CircuitNode output) {
        super(label, nBits>0 ? nBits : 1, 1);

        CircuitNode[] pullUpNodes = new CircuitNode[getNumInputs()];
        pfets = new Pfet[getNumInputs()];
        nfets = new Nfet[getNumInputs()];

        for (int i = 0; i < getNumInputs(); i++) {
            pullUpNodes[i] = new CircuitNode(label + " PMOS node " + i + "-" + (i+1));

            pfets[i] = new Pfet(label + " Pfet_" + i, pullUpNodes[i], getPortOutput(i),
                    (i-1>=0) ? pullUpNodes[i-1] : DigitalCircuit.VDD);

            nfets[i] = new Nfet(label + " Nfet_" + i, output, getPortOutput(i), DigitalCircuit.GND);
        }

        assignOutput(0, output);
    }

    public NorGate(String label, int nBits, CircuitNode output, CircuitNode[] inputs) {
        this(label, nBits, output);

        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        for (Nfet nfet : nfets) {
            nfet.assignOutput(output);
        }
        pfets[pfets.length-1].assignOutput(output);

        super.assignOutput(0, output);
    }

    public void evaluate() {
        super.evaluate();

        for (int i = 0; i < getNumInputs(); i++) {
            pfets[i].evaluate();
            nfets[i].evaluate();
        }
    }

}
