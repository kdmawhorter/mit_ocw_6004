package mit_ocw_6004;

public class Xor extends DigitalCircuit {
    private And[] and;
    private Inverter[] inv;
    private Or or;
    private Wire[] ports;

    public Xor() {
        super();
    }

    public Xor(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }
    public Xor(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        assignOutput(0, output);

        ports = new Wire[getNumInputs()];
        inv = new Inverter[getNumInputs()];
        and = new And[getNumInputs()];

        or = new Or(label + " Or", getNumInputs(), output);

        for (int i = 0; i < getNumInputs(); i++) {
            ports[i] = new Wire(label + " InputWire_" + i);
            inv[i] = new Inverter(label + " Inv_" + i);
            inv[i].assignInput(0, ports[i].getOutput());
        }

        for (int andIdx = 0; andIdx < getNumInputs(); andIdx++) {
            and[andIdx] = new And(label + " And_" + andIdx, getNumInputs());
            for (int inputIdx = 0; inputIdx < getNumInputs(); inputIdx++) {
                and[andIdx].assignInput(inputIdx, (inputIdx!=andIdx ? inv[inputIdx] : ports[inputIdx]).getOutput());
            }
            or.assignInput(andIdx, and[andIdx].getOutput(0));
        }

    }

    public Xor(String label, int nBit, CircuitNode[] inputs) {
        this(label, nBit);
        assignInputs(inputs);
    }

    public Xor(String label, int nBit, CircuitNode[] inputs, CircuitNode output) {
        this(label, nBit, output);
        assignInputs(inputs);
    }

    public void evaluate() {
        for (int portIdx = 0; portIdx < getNumInputs(); portIdx++) {
            ports[portIdx].assignInput(0, getInput(portIdx));
            ports[portIdx].evaluate();
            inv[portIdx].evaluate();
        }
        for (int andIdx = 0; andIdx < getNumInputs(); andIdx++) {
            and[andIdx].evaluate();
        }
        or.evaluate();
    }
}
