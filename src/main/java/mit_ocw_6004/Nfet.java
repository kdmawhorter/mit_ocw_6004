package mit_ocw_6004;

public class Nfet extends Mosfet {

    public Nfet(String label) { super(label); }
    public Nfet(String label, CircuitNode drain) { super(label, drain, DigitalCircuit.GND); }
    public Nfet(String label, CircuitNode drain, CircuitNode source) {super(label, drain, source); }
    public Nfet(String label, CircuitNode drain, CircuitNode source, CircuitNode gate) {
        super(label, drain, source, gate);
    }

    @Override
    public boolean areDrainAndSourceConnected() {
        return getInput(0).getStatus().equals(ConnectionType.POWER);
    }

}
