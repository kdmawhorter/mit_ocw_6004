package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import org.jetbrains.annotations.NotNull;

public class Or extends DigitalCircuit {

    private Nor nor;
    private Inverter inv;

    public Or() {
        super();
    }

    public Or(String label, int nBit) {
        this(label, nBit, new CircuitNode(label + " Output"));
    }


    public Or(String label, int nBit, CircuitNode output) {
        super(label, nBit, 1);

        nor = new Nor(label + " Nor", nBit);
        nor.assignInputs(getPortOutputs());

        inv = new Inverter(label + " Inverter", 1);
        inv.assignInput(0, nor.getOutput(0));

        assignOutput(0, output);
    }

    public Or(String label, int nBit, CircuitNode[] inputs) {
        this(label, nBit);
        assignInputs(inputs);
    }

    public Or(String label, int nBit, CircuitNode output, CircuitNode[] inputs) {
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
