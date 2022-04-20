package core_architecture;

import org.jetbrains.annotations.NotNull;

public abstract class SingleOutputCircuit extends DigitalCircuit {
    public SingleOutputCircuit(String label, int nBits) {
        super(label, nBits, 1);
    }

    public boolean readOutput() {
        return readOutput(0);
    }

    public void assignOutput(@NotNull CircuitNode output) {
        assignOutput(0, output);
    }

    public CircuitNode getOutput() {
        return getOutput(0);
    }

    public CircuitNode getOutPortInput() { return getOutPortInput(0); }
}
