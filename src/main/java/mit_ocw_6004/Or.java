package mit_ocw_6004;

public class Or extends DigitalCircuit {

    private Nor nor;
    private Inverter inv;

    public Or() {
        super();
    }

    public Or(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }
    public Or(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        nor = new Nor(label + " Nor", nBit);
        inv = new Inverter(label + " Inverter", nor.getOutput(0), output);

        assignOutput(0, inv.getOutput());
    }

    public Or(String label, int nBit, CircuitNode[] inputs) {
        this(label, nBit);
        assignInputs(inputs);
    }

    public Or(String label, int nBit, CircuitNode[] inputs, CircuitNode output) {
        this(label, nBit, output);
        assignInputs(inputs);
    }



    public void evaluate() {
        nor.assignInputs(getInputs());
        nor.evaluate();
        inv.evaluate();
    }
}
