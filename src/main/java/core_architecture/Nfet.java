package core_architecture;

public class Nfet extends Mosfet {

    public Nfet(String label) {
        this(label, new CircuitNode(label + " Output"), DigitalCircuit.GND, DigitalCircuit.GND);
    }

    public Nfet(String label, CircuitNode output) {
        super(label, output, DigitalCircuit.GND, DigitalCircuit.GND);
    }

    public Nfet(String label, CircuitNode output, CircuitNode input) {
        super(label, output, input, DigitalCircuit.GND);
    }

    public Nfet(String label, CircuitNode output, CircuitNode input, CircuitNode source) {
        super(label, output, input, source);
    }

    @Override
    public boolean areDrainAndSourceConnected() {
        return getInput().getStatus().equals(ConnectionType.POWER);
    }

}
