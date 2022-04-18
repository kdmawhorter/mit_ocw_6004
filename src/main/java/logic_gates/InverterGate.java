package logic_gates;

import core_architecture.*;
import org.jetbrains.annotations.NotNull;

public class InverterGate extends SisoCircuit {

    private Pfet pfet;
    private Nfet nfet;

    public InverterGate() {
        super();
    }

    public InverterGate(String label) {
        super(label);

        pfet = new Pfet(label + " PMOS circuit Pfet", getOutput(0), getPortOutput(0));
        nfet = new Nfet(label + " NMOS circuit Nfet", getOutput(0), getPortOutput(0));

        transistorCount += pfet.getTransistorCount() + nfet.getTransistorCount();
    }

    public InverterGate(String label, CircuitNode output) {
        this(label);

        assignOutput(0, output);
    }

    public InverterGate(String label, CircuitNode output, CircuitNode input) {
        this(label, output);

        assignInput(input);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        pfet.assignOutput(output);
        nfet.assignOutput(output);

        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        pfet.evaluate();
        nfet.evaluate();
    }
}
