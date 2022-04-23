package core_architecture;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link DigitalCircuit} with only one output.
 */
public abstract class SingleOutputCircuit extends DigitalCircuit {

    /**
     * SingleOutputCircuit constructor.
     * @param label The name of the circuit.
     * @param nBits The number of inputs to the circuit.
     */
    public SingleOutputCircuit(String label, int nBits) {
        super(label, nBits, 1);
    }

    /**
     * Reads the sole output of the circuit.
     * @return The output reading.
     */
    public boolean readOutput() {
        return readOutput(0);
    }

    /**
     * Assigns the sole output of the circuit.
     * @param output The new output of the circuit.
     */
    public void assignOutput(@NotNull CircuitNode output) {
        assignOutput(0, output);
    }

    /**
     * Gets the sole output of the circuit
     * @return The output of the circuit.
     */
    public CircuitNode getOutput() {
        return getOutput(0);
    }

    /**
     * Gets the sole internal connection to the output.
     * @return The internal connection to output.
     */
    protected CircuitNode getInternalOutput() { return getInternalOutput(0); }

    /**
     * Sets the sole internal output of the circuit.
     * @param output The node to be output.
     */
    protected void setInternalOutput(@NotNull CircuitNode output) {
        setInternalOutput(0, output);
    }
}
