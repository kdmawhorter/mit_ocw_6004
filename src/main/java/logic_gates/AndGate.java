package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import org.jetbrains.annotations.NotNull;

public class AndGate extends DigitalCircuit {
    private NandGate nand;
    private InverterGate inv;

    public AndGate() {
        super();
    }

    public AndGate(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }

    public AndGate(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        nand = new NandGate(label + " Nand", nBit);
        nand.assignInputs(getPortOutputs());

        inv = new InverterGate(label + " Inverter", 1);
        inv.assignInput(0, nand.getOutput(0));

        assignOutput(0, output);
    }

    public AndGate(String label, int nBit, CircuitNode output, CircuitNode[] inputs) {
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
