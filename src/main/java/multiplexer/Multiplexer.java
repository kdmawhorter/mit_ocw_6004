package multiplexer;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.InverterGate;
import logic_gates.NandGate;
import org.jetbrains.annotations.NotNull;

public class Multiplexer extends DigitalCircuit {

    private int numChoices;
    private int selBitCnt;

    private InverterGate[] invSelBitPorts;
    private NandGate[][] selectionNands;
    private NandGate[] outputNands;

    public Multiplexer() { super(); }

    public Multiplexer(String label, int numChoices, int wordWidth) {
        super(label, numChoices * wordWidth + determineSelectorBitCount(numChoices), wordWidth);

        this.numChoices = numChoices;
        selBitCnt = determineSelectorBitCount(numChoices);

        invSelBitPorts = new InverterGate[getSelBitCnt()];
        selectionNands = new NandGate[wordWidth][numChoices];
        outputNands = new NandGate[wordWidth];

        for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
            invSelBitPorts[selIdx] = new InverterGate(label + " InvertedSelBit_" + selIdx);
            invSelBitPorts[selIdx].assignInput(0, getSelBitOut(selIdx));
            transistorCount += invSelBitPorts[selIdx].getTransistorCount();
        }

        for (int outIdx = 0; outIdx < getNumOutputs(); outIdx++) {
            outputNands[outIdx] = new NandGate(label + " OutputNand_" + outIdx, numChoices);
            transistorCount += outputNands[outIdx].getTransistorCount();

            boolean[] currentSelectorBits = new boolean[getSelBitCnt()];

            for (int choiceIdx = 0; choiceIdx < numChoices; choiceIdx++) {
                selectionNands[outIdx][choiceIdx] = new NandGate(
                        label + " SelectionNand_" + outIdx + "_" + choiceIdx,
                        1 + getSelBitCnt());

                selectionNands[outIdx][choiceIdx].assignInput(0, getNthIPortOutput(choiceIdx, outIdx));

                for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
                    selectionNands[outIdx][choiceIdx].assignInput(
                            1 + selIdx,
                            currentSelectorBits[selIdx] ? getSelBitOut(selIdx) : getInvSelBitOut(selIdx));
                }
                transistorCount += selectionNands[outIdx][choiceIdx].getTransistorCount();

                outputNands[outIdx].assignInput(choiceIdx, selectionNands[outIdx][choiceIdx].getOutput(0));

                currentSelectorBits = Multiplexer.incrementTruthArray(currentSelectorBits);

            }
            assignOutput(outIdx, outputNands[outIdx].getOutput(0));
        }
    }

    private CircuitNode getSelBitOut(int i) {
        return getPortOutput(getNumInputs()-getSelBitCnt()+i);
    }

    private CircuitNode getInvSelBitOut(int i) {
        return invSelBitPorts[i].getOutput(0);
    }

    private CircuitNode getNthIPortOutput(int n, int i) {
        return getPortOutput(n*getNumOutputs()+i);
    }

    public int getSelBitCnt() {
        return selBitCnt;
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        outputNands[i].assignOutput(0, output);

        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();
        for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
            invSelBitPorts[selIdx].evaluate();
        }

        for (int outIdx = 0; outIdx < getNumOutputs(); outIdx++) {
            for (int choiceIdx = 0; choiceIdx < numChoices; choiceIdx++) {
                selectionNands[outIdx][choiceIdx].evaluate();
            }
            outputNands[outIdx].evaluate();
        }

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
