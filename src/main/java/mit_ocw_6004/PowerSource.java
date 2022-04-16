package mit_ocw_6004;

public class PowerSource extends CircuitNode {

    public PowerSource(String label) {
        super(label);
    }

    @Override
    public void updateStatus(String circuit, ConnectionType newStatus) {
        // Power sources are always connected to power and cannot be updated
    }

    @Override
    public ConnectionType getStatus() {
        return ConnectionType.POWER;
    }
}
