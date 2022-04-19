package core_architecture;

import java.util.HashMap;

import static core_architecture.ConnectionType.GROUND;

public class CircuitNode  {
    private final String label;
    private final HashMap<String, ConnectionType> statuses;

    public CircuitNode() {
        this("INVALID NODE");
    }

    public CircuitNode(String label) {
        this.label = label;
        statuses = new HashMap<>();
        statuses.put("init", GROUND);
    }

    public void updateStatus(String circuit, ConnectionType newStatus) {
        statuses.remove("init");
        statuses.put(circuit, newStatus);
    }

    public ConnectionType getStatus() {
         boolean powered = false, grounded = false;
         for (ConnectionType status : statuses.values()) {
             powered = powered || status.equals(ConnectionType.POWER);
             grounded = grounded || status.equals(ConnectionType.GROUND);
         }

         if (powered && grounded) {
             return ConnectionType.BOTH;
         } else if (powered) {
             return ConnectionType.POWER;
         } else if (grounded) {
             return ConnectionType.GROUND;
         }
         return ConnectionType.FLOATING;
    }
    public String getLabel() { return label; }
}
