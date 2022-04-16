package core_architecture;

public class Pfet extends Mosfet {

    public Pfet(String label) {
        this(label, new CircuitNode(label + " Output"), DigitalCircuit.GND, DigitalCircuit.VDD);
    }

    public Pfet(String label, CircuitNode output) {
        super(label, output, DigitalCircuit.GND, DigitalCircuit.VDD);
    }

    public Pfet(String label, CircuitNode output, CircuitNode input) {
        super(label, output, input, DigitalCircuit.VDD);
    }

    public Pfet(String label, CircuitNode output, CircuitNode input, CircuitNode source) {
        super(label, output, input, source);
    }

    @Override
    public boolean areDrainAndSourceConnected() {
        return getInput(0).getStatus().equals(ConnectionType.GROUND);
    }

}

