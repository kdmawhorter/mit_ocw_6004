package logic_gates;

import core_architecture.SingleOutputCircuit;

public class AndGate extends SingleOutputCircuit {
    private final NandGate nand;
    private final InverterGate inv;

    public AndGate(String label, int nBit) {
        super(label, nBit);

        nand = new NandGate(label + " Nand", nBit);
        nand.assignInputs(getInPortOutputs());

        inv = new InverterGate(label + " Inverter");
        inv.assignInput(0, nand.getOutput(0));
        inv.assignOutput(0, getOutPortInput());

        transistorCount = inv.getTransistorCount() + nand.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        nand.evaluate();
        inv.evaluate();
    }
}
