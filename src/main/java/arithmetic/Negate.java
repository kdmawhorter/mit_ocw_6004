package arithmetic;

import bitwise.Increment;
import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.InverterGate;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Negate extends DigitalCircuit {

    InverterGate inverter;
    Increment incrementer;

    public Negate() { super(); }

    public Negate(String label, int nBits) {
        super(label, nBits, nBits);

        inverter = new InverterGate(label + " Inverter", nBits);
        inverter.assignInputs(getPortOutputs());

        incrementer = new Increment(label + " Incrementer", nBits);
        incrementer.assignInputs(inverter.getOutputs());

        assignOutputs(Arrays.copyOf(incrementer.getOutputs(), nBits));

        transistorCount = inverter.getTransistorCount() + incrementer.getTransistorCount();
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        incrementer.assignOutput(i, output);
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();
        inverter.evaluate();
        incrementer.evaluate();
    }
}
