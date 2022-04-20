package core_architecture;

import org.jetbrains.annotations.NotNull;

public abstract class SisoCircuit extends SingleOutputCircuit {
    public SisoCircuit(String label) { super(label, 1); }

    public void turnOn() {
        assignInputOn(0);
    }

    public void turnOff() {
        assignInputOff(0);
    }

    public void assignInput(@NotNull CircuitNode input) {
        assignInput(0, input);
    }

    public CircuitNode getInput() {
        return getInput(0);
    }

    public CircuitNode getInPortOutput() { return getInPortOutput(0); }
}
