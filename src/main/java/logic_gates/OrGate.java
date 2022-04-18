package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import org.jetbrains.annotations.NotNull;

public class OrGate extends DigitalCircuit {

    private NorGate nor;
    private InverterGate inv;

    public OrGate() {
        super();
    }

    public OrGate(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }


    public OrGate(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        nor = new NorGate(label + " Nor", nBit);
        nor.assignInputs(getPortOutputs());

        inv = new InverterGate(label + " Inverter", 1);
        inv.assignInput(0, nor.getOutput(0));

        assignOutput(0, output);

        transistorCount = inv.getTransistorCount() + nor.getTransistorCount();
    }

    public OrGate(String label, int nBit, CircuitNode[] inputs) {
        this(label, nBit);
        assignInputs(inputs);
    }

    public OrGate(String label, int nBit, CircuitNode output, CircuitNode[] inputs) {
        this(label, nBit, output);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        inv.assignOutput(0, output);
        super.assignOutput(i, output);
    }

    public void evaluate() {
        super.evaluate();

        nor.evaluate();
        inv.evaluate();
    }
}
