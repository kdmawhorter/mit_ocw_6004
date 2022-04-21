package core_architecture;

/**
 * A {@link CircuitNode CircuitNode} representing the ground connection terminal.
 */
public class Ground extends CircuitNode {

    public Ground(String label) {
        super(label);
    }

    @Override
    public void updateStatus(String circuit, ConnectionType newStatus) {
        // Power sources are always connected to power and cannot be updated
    }

    @Override
    public ConnectionType getStatus() {
        return ConnectionType.GROUND;
    }
}
