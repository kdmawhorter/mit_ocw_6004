package core_architecture;

import logic_gates.InverterGate;
import lombok.Getter;

public abstract class SelectionCircuit extends DigitalCircuit {

    /**
     * The number of bits required to select between the number of options.
     */
    @Getter
    protected final int selBitCnt;

    protected final InverterGate[] invSelBitPorts;

    /**
     * SelectionCircuit constructor.
     *
     * @param label The name of the circuit.
     * @param numInputs The number of inputs to the circuit (not including the selection bits).
     * @param numOutputs The number of outputs of the circuit.
     * @param selBitCnt The number of bits required to choose between the desired number of options.
     */
    public SelectionCircuit(String label, int numInputs, int numOutputs, int selBitCnt) {
        super(label, numInputs+selBitCnt, numOutputs);

        this.selBitCnt = selBitCnt;

        invSelBitPorts = new InverterGate[getSelBitCnt()];

        for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
            invSelBitPorts[selIdx] = new InverterGate(label + " InvertedSelBit_" + selIdx);
            invSelBitPorts[selIdx].assignInput(0, getSelBitOut(selIdx));
            transistorCount += invSelBitPorts[selIdx].getTransistorCount();
        }

    }

    /**
     * Gets the ith selector bit.
     * <br><br>
     * Selection bit data is stored little-endian but the accesses are designed big endian (so i=0 will yield the
     * highest significance bit of the selection). This is to facilitate ease of reading the inputs of logic gates
     * (i.e. the inputs to logic gates using the selection bits will have the selection bit input order also
     * little-endian).
     *
     * @param i The index of the selection bit.
     * @return The output of that selection bit.
     */
    protected CircuitNode getSelBitOut(int i) {
        return getInternalInput(getNumInputs()-getSelBitCnt()+i);
    }

    /**
     * Gets the inverted output of the {@link #getSelBitOut(int) ith selection bit}.
     * @param i The index of the selection bit.
     * @return The inverted output of that selection bit.
     */
    protected CircuitNode getInvSelBitOut(int i) {
        return invSelBitPorts[i].getOutput(0);
    }

    /**
     * Class wide function used to determine the number of bits needed to represent the choices available.
     *
     * @param numOptions The number of options to chose from
     * @return The number of bits necessary to encode the number of options.
     */
    public static int determineSelectorBitCount(int numOptions) {
        return (int) Math.ceil(Math.log(numOptions)/Math.log(2));
    }

    /**
     * Class wide function used to increment a binary string represented as a Boolean array.
     * @param inputTruthArray The binary string to increment.
     * @return The incremented binary string.
     */
    public static boolean[] incrementTruthArray(boolean[] inputTruthArray) {
        boolean carryTruth = true;
        boolean[] outputTruthArray = new boolean[inputTruthArray.length];
        for (int i = inputTruthArray.length - 1; i >= 0; i--) {
            outputTruthArray[i] = inputTruthArray[i] ^ carryTruth;
            carryTruth = carryTruth && inputTruthArray[i];
        }
        return outputTruthArray;
    }

    /**
     * Evaluates the base level of Mux/Shift/Memory circuitry by priming all the selection bit inverter ports.
     */
    @Override
    protected void evaluateCircuit() {
        for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
            invSelBitPorts[selIdx].evaluate();
        }
    }
}
