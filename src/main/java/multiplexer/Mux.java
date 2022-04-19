package multiplexer;

import core_architecture.BitMuxShiftCore;
import core_architecture.CircuitNode;
import logic_gates.NandGate;

import org.jetbrains.annotations.NotNull;

public class Mux extends BitMuxShiftCore {

    private int numChoices;

    private NandGate[][] selectionNands;
    private NandGate[] outputNands;

    public Mux() { super(); }

    public Mux(String label, int numChoices, int wordWidth) {
        super(label, numChoices * wordWidth, wordWidth, determineSelectorBitCount(numChoices));

        this.numChoices = numChoices;

        selectionNands = new NandGate[wordWidth][numChoices];
        outputNands = new NandGate[wordWidth];

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

                currentSelectorBits = Mux.incrementTruthArray(currentSelectorBits);

            }
            assignOutput(outIdx, outputNands[outIdx].getOutput(0));
        }
    }

    private CircuitNode getNthIPortOutput(int n, int i) {
        return getPortOutput(n*getNumOutputs()+i);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        outputNands[i].assignOutput(0, output);

        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        for (int outIdx = 0; outIdx < getNumOutputs(); outIdx++) {
            for (int choiceIdx = 0; choiceIdx < numChoices; choiceIdx++) {
                selectionNands[outIdx][choiceIdx].evaluate();
            }
            outputNands[outIdx].evaluate();
        }

    }


}
