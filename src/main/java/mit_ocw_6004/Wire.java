package mit_ocw_6004;

public class Wire extends SisoCircuit {
    public Wire() {
        super();
    }

    public Wire(String label) {
        super(label);
    }

    public void evaluate() {
        getOutput().updateStatus(getLabel(), getInput().getStatus());
    }
}
