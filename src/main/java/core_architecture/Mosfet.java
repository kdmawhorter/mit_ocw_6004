package core_architecture;

public abstract class Mosfet extends SisoCircuit {

    private final CircuitNode source;

    public Mosfet() {
        super();
        source = null;
    }

    public Mosfet(String label, CircuitNode output, CircuitNode input, CircuitNode source) {
        super(label);

        assignInput(input);
        assignOutput(output);
        this.source = source;
    }

    public abstract boolean areDrainAndSourceConnected();

    @Override
    public void evaluate() {
        super.evaluate();

        ConnectionType newStatus = ConnectionType.FLOATING;
        if (areDrainAndSourceConnected()) {
            newStatus = source.getStatus();
        }
        getOutput().updateStatus(getLabel(), newStatus);
    }
}
