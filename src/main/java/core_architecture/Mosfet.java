package core_architecture;

/**
 * A single-input, single output class representing a MOSFET.
 * <br><br>
 * All MOSFETS will share characteristics of having a drain, source, and gate terminal. Child classes (Pfets and Nfets
 * namely) will determine if source and drain are connected, and if so, the MOSFET will propagate source status to
 * drain.
 */
public abstract class Mosfet extends SisoCircuit {

    private final CircuitNode source;

    /**
     * Mosfet constructor. Maps the provided terminals.
     *
     * @param label A string representing the object
     * @param output A CircuitNode representing the drain terminal
     * @param input A CircuitNode representing the gate terminal
     * @param source A CircuitNode representing the source terminal
     */
    public Mosfet(String label, CircuitNode output, CircuitNode input, CircuitNode source) {
        super(label);

        transistorCount = 1;

        assignInput(input);
        assignOutput(output);
        this.source = source;
    }

    /**
     * Determines if the drain and source terminal are currently connected. Nfets are connected with gate status is
     * connected to power. Pfets are connected when gate status is connected to ground.
     *
     * @return A boolean indicating whether the drain and source terminal are connected
     */
    public abstract boolean areDrainAndSourceConnected();

    /**
     * If the drain and the source are connected, the source status is propagated to the drain, else the drain status is
     * set to floating.
     */
    @Override
    protected void evaluateCircuit() {
        ConnectionType newStatus = ConnectionType.FLOATING;
        if (areDrainAndSourceConnected()) {
            newStatus = source.getStatus();
        }
        getInternalOutput().updateStatus(getLabel(), newStatus);
    }
}
