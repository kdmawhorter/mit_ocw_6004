package logic_gates;

import core_architecture.*;

public class NandGate extends SingleOutputCircuit {
    private final Pfet[] pfets;
    private final Nfet[] nfets;

    public NandGate(String label, int nBits) {
        super(label, nBits);

        CircuitNode[] pullDownNodes = new CircuitNode[nBits];
        pfets = new Pfet[nBits];
        nfets = new Nfet[nBits];

        for (int i = 0; i < nBits; i++) {
            pullDownNodes[i] = (i==nBits-1) ?
                    getInternalOutput() :
                    new CircuitNode(label + " NMOS node " + i + "-" + (i+1));

            nfets[i] = new Nfet(label + " Nfet_" + i, pullDownNodes[i], getInternalInput(i),
                    (i-1>=0) ? pullDownNodes[i-1] : GND);

            pfets[i] = new Pfet(label + " Pfet_" + i, getInternalOutput(), getInternalInput(i), VDD);

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
