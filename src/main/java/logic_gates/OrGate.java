package logic_gates;

import core_architecture.DigitalCircuit;
import core_architecture.SingleOutputCircuit;

/**
 * A class representing the logic gate Or.
 * <br><br>
 * Given nBits of input, the output is {@link DigitalCircuit#GND GND} if all
 * those input bits are GND, else it is {@link DigitalCircuit#VDD VDD}.
 */
public class OrGate extends SingleOutputCircuit {

    private final NorGate nor;
    private final InverterGate inv;

    /**
     * OrGate constructor
     * @param label The name of the circuit.
     * @param nBits The number of inputs.
     */
    public OrGate(String label, int nBits) {
        super(label, nBits);

        nor = new NorGate(label + " Nor", nBits);
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
