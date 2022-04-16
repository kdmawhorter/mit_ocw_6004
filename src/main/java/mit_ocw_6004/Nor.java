package mit_ocw_6004;

public class Nor extends DigitalCircuit{
    private Pfet[] pfets;
    private Nfet[] nfets;

    public Nor() {
        super();
    }

    public Nor(String label, int nBits) {
        this(label, nBits, new CircuitNode(label + " Output"));
    }

    public Nor(String label, int nBits, CircuitNode output) {
        super(label, nBits>0 ? nBits : 1, 1);

        CircuitNode[] pullUpNodes = new CircuitNode[getNumInputs()];
        pfets = new Pfet[getNumInputs()];
        nfets = new Nfet[getNumInputs()];

        assignOutput(0, output);

        for (int i = 0; i < getNumInputs(); i++) {
            if (i!=getNumInputs()-1) {
                pullUpNodes[i] = new CircuitNode(label + " PMOS node " + i + "-" + (i+1));
            } else {
                pullUpNodes[i] = getOutput(0);
            }
            pfets[i] = new Pfet(label + " Pfet_" + i, pullUpNodes[i],
                    (i-1>=0) ? pullUpNodes[i-1] : DigitalCircuit.VDD);

            nfets[i] = new Nfet(label + " Nfet_" + i, getOutput(0));
        }
    }

    public Nor(String label, int nBits, CircuitNode[] inputs, CircuitNode output) {
        this(label, nBits, output);

        assignInputs(inputs);
    }

    public void evaluate() {
        for (int i = 0; i < getNumInputs(); i++) {
            pfets[i].assignInput(getInput(i));
            pfets[i].evaluate();
            nfets[i].assignInput(getInput(i));
            nfets[i].evaluate();
        }
    }

}
