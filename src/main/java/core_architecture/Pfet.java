package core_architecture;

/**
 * A pull-up type {@link Mosfet MOSFET}.
 */
public class Pfet extends Mosfet {

    public Pfet(String label, CircuitNode output, CircuitNode input, CircuitNode source) {
        super(label, output, input, source);
    }

    @Override
    public boolean areDrainAndSourceConnected() {
        return getInput(0).getStatus().equals(ConnectionType.GROUND);
    }

}

