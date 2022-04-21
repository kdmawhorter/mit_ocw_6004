package logic_gates;

import core_architecture.*;

public class NorGate extends SingleOutputCircuit {
    private final Pfet[] pfets;
    private final Nfet[] nfets;

    public NorGate(String label, int nBits) {
        super(label, nBits);

        CircuitNode[] pullUpNodes = new CircuitNode[nBits];
        pfets = new Pfet[nBits];
        nfets = new Nfet[nBits];

        for (int i = 0; i < nBits; i++) {
            pullUpNodes[i] = (i==nBits-1) ?
                    getInternalOutput() :
                    new CircuitNode(label + " PMOS node " + i + "-" + (i+1));

            pfets[i] = new Pfet(label + " Pfet_" + i, pullUpNodes[i], getInternalInput(i),
                    (i-1>=0) ? pullUpNodes[i-1] : VDD);

            nfets[i] = new Nfet(label + " Nfet_" + i, getInternalOutput(), getInternalInput(i), GND);

            transistorCount += pfets[i].getTransistorCount() + nfets[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = 0; i < getNumInputs(); i++) {
            pfets[i].evaluate();
            nfets[i].evaluate();
        }
    }
}
