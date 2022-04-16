package mit_ocw_6004;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class SisoCircuit extends DigitalCircuit {
    public SisoCircuit() {
        super();
    }

    public SisoCircuit(String label) {
        super(label, 1, 1);
    }

    public void turnOn() {
        assignInputOn(0);
    }

    public void turnOff() {
        assignInputOff(0);
    }

    public void assignInput(CircuitNode input) {
        assignInput(0, input);
    }

    public CircuitNode getInput() {
        return getInput(0);
    }

    public boolean readOutput() {
        return readOutput(0);
    }

    public void assignOutput(CircuitNode output) {
        assignOutput(0, output);
    }

    public CircuitNode getOutput() {
        return getOutput(0);
    }




}
