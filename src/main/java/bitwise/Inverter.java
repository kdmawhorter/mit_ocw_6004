package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.InverterGate;
import org.jetbrains.annotations.NotNull;

public class Inverter extends DigitalCircuit {

    private InverterGate[] inverters;

    public Inverter() {
        super();
    }

    public Inverter(String label, int nBit) {
        super(label, nBit, nBit);

        inverters = new InverterGate[getNumInputs()];

        for (int i = 0; i < getNumInputs(); i++) {
            inverters[i] = new InverterGate(label + " InverterGate_" + i, getOutput(i), getPortOutput(i));
            transistorCount += inverters[i].getTransistorCount();
            assignOutput(i, inverters[i].getOutput(0));
        }
    }

    public Inverter(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public Inverter(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);

        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        inverters[i].assignOutput(0, output);

        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        for (int i = 0; i < getNumInputs(); i++) {
            inverters[i].evaluate();
        }
    }

}
