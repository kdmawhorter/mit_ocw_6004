package core_architecture;

/**
 * A pull-down type {@link Mosfet MOSFET}.
 */
public class Nfet extends Mosfet {

    public Nfet(String label, CircuitNode output, CircuitNode input, CircuitNode source) {
        super(label, output, input, source);
    }

    @Override
    public boolean areDrainAndSourceConnected() {
        return getInput().getStatus().equals(ConnectionType.POWER);
    }

}
