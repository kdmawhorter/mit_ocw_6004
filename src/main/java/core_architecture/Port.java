package core_architecture;

import lombok.Getter;
import lombok.Setter;

/**
 * A class that serves as a decoupling between a {@link DigitalCircuit DigitalCircuit's} internal and external
 * connections to an input or output
 * <br><br>
 * A port simply maps an input node to an output node, allowing separation between the two.
 */
@Getter
public class Port {

    private final String label;

    @Setter private CircuitNode input;
    @Setter private CircuitNode output;

    /**
     * Port constructor.
     *
     * @param label A string representing the name of the port
     */
    public Port(String label) {
        this.label = label;
        input = new CircuitNode(label + " Input");
        output = new CircuitNode(label + " Output");
    }

    /**
     * Queries the status from the input node of the port and propagates it to the
     * output node of the port.
     */
    public void readPort() {
        getOutput().updateStatus(getLabel(), getInput().getStatus());
    }
}
