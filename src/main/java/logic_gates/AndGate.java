package logic_gates;

import core_architecture.SingleOutputCircuit;

/**
 * A class representing the logic gate And. 
 * <br><br>
 * Given nBits of input, the output is {@link core_architecture.DigitalCircuit#VDD VDD} if all
 * those input bits are VDD, else it is {@link core_architecture.DigitalCircuit#GND GND}.
 */
public class AndGate extends SingleOutputCircuit {
    private final NandGate nand;
    private final InverterGate inv;

    /**
     * AndGate constructor
     * @param label The name of the circuit.
     * @param nBits The number of inputs.
     */
    public AndGate(String label, int nBits) {
        super(label, nBits);

        nand = new NandGate(label + " Nand", nBits);
        nand.assignInputs(getInternalInputs());

        inv = new InverterGate(label + " Inverter");
        inv.assignInput(0, nand.getOutput(0));
        inv.assignOutput(0, getInternalOutput());

        transistorCount = inv.getTransistorCount() + nand.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        nand.evaluate();
        inv.evaluate();
    }
}
