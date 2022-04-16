package mit_ocw_6004;

public abstract class Mosfet extends SisoCircuit {

    private final CircuitNode source;

    public Mosfet() {
        super();
        source = null;
    }

    public Mosfet(String label) {
        this(label, DigitalCircuit.GND, new CircuitNode(label + " Drain Output"), DigitalCircuit.GND);
    }

    public Mosfet(String label, CircuitNode drain) {
        this(label, DigitalCircuit.GND, drain, DigitalCircuit.GND);
    }

    public Mosfet(String label, CircuitNode drain, CircuitNode source) {
        this(label, drain, source, DigitalCircuit.GND);
    }

    public Mosfet(String label, CircuitNode drain, CircuitNode source, CircuitNode gate) {
        super(label);

        assignInput(gate);
        assignOutput(drain);
        this.source = source;
    }

    public abstract boolean areDrainAndSourceConnected();

    @Override
    public void evaluate() {
        ConnectionType newStatus = ConnectionType.FLOATING;
        if (areDrainAndSourceConnected()) {
            newStatus = source.getStatus();
        }
        getOutput(0).updateStatus(getLabel(), newStatus);
    }
}
