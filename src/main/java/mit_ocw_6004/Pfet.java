package mit_ocw_6004;

public class Pfet extends Mosfet {

    public Pfet(String label) { super(label); }
    public Pfet(String label, CircuitNode drain) { super(label, drain, DigitalCircuit.VDD); }
    public Pfet(String label, CircuitNode drain, CircuitNode source) {super(label, drain, source); }
    public Pfet(String label, CircuitNode drain, CircuitNode source, CircuitNode gate) {
        super(label, drain, source, gate);
    }

    @Override
    public boolean areDrainAndSourceConnected() {
        return getInput(0).getStatus().equals(ConnectionType.GROUND);
    }

}

