package logic_gates;

import core_architecture.SingleOutputCircuit;

public class OrGate extends SingleOutputCircuit {

    private final NorGate nor;
    private final InverterGate inv;

    public OrGate(String label, int nBit) {
        super(label, nBit);

        nor = new NorGate(label + " Nor", nBit);
        nor.assignInputs(getInternalInputs());

        inv = new InverterGate(label + " Inverter");
        inv.assignInput(0, nor.getOutput(0));
        inv.assignOutput(0, getInternalOutput());

        transistorCount = inv.getTransistorCount() + nor.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        nor.evaluate();
        inv.evaluate();
    }
}
