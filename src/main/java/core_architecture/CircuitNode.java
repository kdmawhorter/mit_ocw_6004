package core_architecture;

import lombok.Getter;

import java.util.HashMap;

import static core_architecture.ConnectionType.GROUND;

/**
 * A class representing a circuit node or wire.
 * <br><br>
 * Many {@link DigitalCircuit DigitalCircuits} may be connected to a node and may transfer their statuses to the node
 * as an output.
 * <br><br>
 * DigitalCircuits can also query the status of the node as an input.
 *
 */

public class CircuitNode  {

    /**
     * The name of the node.
     */
    @Getter
    private final String label;
    private final HashMap<String, ConnectionType> statuses;

    /**
     * CircuitNode constructor.
     *
     * @param label A string representing the node.
     */
    public CircuitNode(String label) {
        this.label = label;
        statuses = new HashMap<>();
        statuses.put("init", GROUND);
    }

    /**
     * Given a newStatus from some circuit, updates the current status of the node.
     *
     * @param circuit The name of the circuit providing a status.
     * @param newStatus The new status to update.
     */
    public void updateStatus(String circuit, ConnectionType newStatus) {
        statuses.remove("init");
        statuses.put(circuit, newStatus);
    }

    /**
     * Determines the {@link ConnectionType} of the current node. This is defined as:
     * <ul>Power: if all updated statuses are either power or floating</ul>
     * <ul>Ground: if all updated statuses are either ground or floating</ul>
     * <ul>Both: if updated statuses contain a connection to power and ground</ul>
     * <ul>Floating: else all updated statuses are floating</ul>
     *
     * @return A ConnectionType representing the current node connection status
     */
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
}
