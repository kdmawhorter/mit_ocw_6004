package core_architecture;

/**
 * An enumeration of the possible connection statuses of a node. A node can either be connected to power, ground, both,
 * or none.
 */
public enum ConnectionType {
    POWER,
    GROUND,
    BOTH,
    FLOATING
}
