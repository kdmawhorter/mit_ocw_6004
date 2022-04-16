package mit_ocw_6004;

public class And extends DigitalCircuit {
    private Nand nand;
    private Inverter inv;

    public And() {
        super();
    }

    public And(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }
    public And(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        nand = new Nand(label + " Nand", nBit);
        inv = new Inverter(label + " Inverter", nand.getOutput(0), output);

        assignOutput(0, inv.getOutput());
    }

    public And(String label, int nBit, CircuitNode[] inputs) {
        this(label, nBit);
        assignInputs(inputs);
    }

    public And(String label, int nBit, CircuitNode[] inputs, CircuitNode output) {
        this(label, nBit, output);
        assignInputs(inputs);
    }



    public void evaluate() {
        nand.assignInputs(getInputs());
        nand.evaluate();
        inv.evaluate();
    }
}
