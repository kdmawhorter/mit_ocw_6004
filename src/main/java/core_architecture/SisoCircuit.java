package core_architecture;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link DigitalCircuit} with only one output and one input.
 */
public abstract class SisoCircuit extends SingleOutputCircuit {
    /**
     * SisoCircuit constructor.
     * @param label The name of the circuit.
     */
    public SisoCircuit(String label) { super(label, 1); }

    /**
     * Assigns the sole input of the circuit to power.
     */
    public void turnOn() {
        assignInputOn(0);
    }

    /**
     * Assigns the sole input of the circuit to ground.
     */
    public void turnOff() {
        assignInputOff(0);
    }

    /**
     * Assigns the sole input of the circuit to some node.
     * @param input The new input to the circuit.
     */
    public void assignInput(@NotNull CircuitNode input) {
        assignInput(0, input);
    }

    /**
     * Gets the sole input to the circuit.
     * @return The input to the circuit.
     */
    public CircuitNode getInput() {
        return getInput(0);
    }

    /**
     * Gets the sole internal input of the circuit.
     * @return The internal input of the circuit.
     */
    protected CircuitNode getInternalInput() { return getInternalInput(0); }
}
