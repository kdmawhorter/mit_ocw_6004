package core_architecture;

public abstract class Mosfet extends SisoCircuit {

    private final CircuitNode source;

    public Mosfet(String label, CircuitNode output, CircuitNode input, CircuitNode source) {
        super(label);

        transistorCount = 1;

        assignInput(input);
        setOutPortInput(0, output);
        this.source = source;
    }

    public abstract boolean areDrainAndSourceConnected();

    @Override
    protected void evaluateCircuit() {
        ConnectionType newStatus = ConnectionType.FLOATING;
        if (areDrainAndSourceConnected()) {
            newStatus = source.getStatus();
        }
        getOutPortInput().updateStatus(getLabel(), newStatus);
    }
}
