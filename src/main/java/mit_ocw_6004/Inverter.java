package mit_ocw_6004;

public class Inverter extends SisoCircuit {

    private Pfet pfet;
    private Nfet nfet;

    public Inverter() {
        super();
    }

    public Inverter(String label) {
        super(label);

        pfet = new Pfet("PMOS circuit Pfet", getOutput());
        nfet = new Nfet("NMOS circuit Nfet", getOutput());
    }

    public Inverter(String label, CircuitNode input, CircuitNode output) {
        this(label);

        pfet.assignOutput(output);
        nfet.assignOutput(output);
        assignOutput(output);

        assignInput(input);
    }

    public void evaluate() {
        pfet.assignInput(getInput());
        nfet.assignInput(getInput());
        pfet.evaluate();
        nfet.evaluate();
    }


}
