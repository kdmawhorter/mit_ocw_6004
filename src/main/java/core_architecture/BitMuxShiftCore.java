package core_architecture;

import logic_gates.InverterGate;

public class BitMuxShiftCore extends DigitalCircuit {

    protected int selBitCnt;

    protected InverterGate[] invSelBitPorts;

    public BitMuxShiftCore() { super(); }

    public BitMuxShiftCore(String label, int numInputs, int numOutputs, int selBitCnt) {
        super(label, numInputs+selBitCnt, numOutputs);

        this.selBitCnt = selBitCnt;

        invSelBitPorts = new InverterGate[getSelBitCnt()];

        for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
            invSelBitPorts[selIdx] = new InverterGate(label + " InvertedSelBit_" + selIdx);
            invSelBitPorts[selIdx].assignInput(0, getSelBitOut(selIdx));
            transistorCount += invSelBitPorts[selIdx].getTransistorCount();
        }

    }

    protected CircuitNode getSelBitOut(int i) {
        return getPortOutput(getNumInputs()-getSelBitCnt()+i);
    }

    protected CircuitNode getInvSelBitOut(int i) {
        return invSelBitPorts[i].getOutput(0);
    }

    public int getSelBitCnt() {
        return selBitCnt;
    }

    public static int determineSelectorBitCount(int nBit) {
        return (int) Math.ceil(Math.log(nBit)/Math.log(2));
    }

    public static boolean[] incrementTruthArray(boolean[] inputTruthArray) {
        boolean carryTruth = true;
        boolean[] outputTruthArray = new boolean[inputTruthArray.length];
        for (int i = inputTruthArray.length - 1; i >= 0; i--) {
            outputTruthArray[i] = inputTruthArray[i] ^ carryTruth;
            carryTruth = carryTruth && inputTruthArray[i];
        }
        return outputTruthArray;
    }
}
