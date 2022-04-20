package core_architecture;

import org.jetbrains.annotations.NotNull;

public class Port {

    private final String label;
    private CircuitNode input;
    private CircuitNode output;

    public Port(String label) {
        this.label = label;
        input = new CircuitNode(label + " Input");
        output = new CircuitNode(label + " Output");
    }

    public String getLabel() { return label; }
    public CircuitNode getInput() { return input; }
    public CircuitNode getOutput() { return output; }

    public void setInput(@NotNull CircuitNode input) {
        this.input = input;
    }
    public void setOutput(@NotNull CircuitNode output) { this.output = output; }

    public void readPort() {
        getOutput().updateStatus(getLabel(), getInput().getStatus());
    }
}
