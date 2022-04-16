package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import org.jetbrains.annotations.NotNull;

public class And extends DigitalCircuit {
    private Nand nand;
    private Inverter inv;

    public And() {
        super();
    }

    public And(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }

    public And(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        nand = new Nand(label + " Nand", nBit);
        nand.assignInputs(getPortOutputs());

        inv = new Inverter(label + " Inverter", 1);
        inv.assignInput(0, nand.getOutput(0));

        assignOutput(0, output);
    }

    public And(String label, int nBit, CircuitNode output, CircuitNode[] inputs) {
        this(label, nBit, output);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        inv.assignOutput(0, output);
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        nand.evaluate();
        inv.evaluate();
    }
}
