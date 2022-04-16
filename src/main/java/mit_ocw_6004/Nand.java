package mit_ocw_6004;

public class Nand extends DigitalCircuit {
    private Pfet[] pfets;
    private Nfet[] nfets;

    public Nand() {
        super();
    }

    public Nand(String label, int nBits) {
        this(label, nBits, new CircuitNode(label + " Output"));
    }

    public Nand(String label, int nBits, CircuitNode output) {
        super(label, nBits>0 ? nBits : 1, 1);

        CircuitNode[] pullDownNodes = new CircuitNode[getNumInputs()];
        pfets = new Pfet[getNumInputs()];
        nfets = new Nfet[getNumInputs()];

        assignOutput(0, output);

        for (int i = 0; i < getNumInputs(); i++) {
            if (i!=getNumInputs()-1) {
                pullDownNodes[i] = new CircuitNode(label + " NMOS node " + i + "-" + (i+1));
            } else {
                pullDownNodes[i] = getOutput(0);
            }
            nfets[i] = new Nfet(label + " Pfet_" + i, pullDownNodes[i],
                    (i-1>=0) ? pullDownNodes[i-1] : DigitalCircuit.GND);

            pfets[i] = new Pfet(label + " Nfet_" + i, getOutput(0));
        }
    }

    public Nand(String label, int nBits, CircuitNode[] inputs, CircuitNode output) {
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
