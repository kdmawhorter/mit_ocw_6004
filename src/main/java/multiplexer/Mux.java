package multiplexer;

import core_architecture.BitMuxShiftCore;
import core_architecture.CircuitNode;
import logic_gates.NandGate;

public class Mux extends BitMuxShiftCore {

    private final NandGate[][] selectionNands;
    private final NandGate[] outputNands;

    public Mux(String label, int numChoices, int wordWidth) {
        super(label, numChoices * wordWidth, wordWidth, determineSelectorBitCount(numChoices));

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
            outputNands[outIdx].assignOutput(getInternalOutput(outIdx));
        }
    }

    private CircuitNode getNthIPortOutput(int n, int i) {
        return getInternalInput(n*getNumOutputs()+i);
    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();

        for (int outIdx = 0; outIdx < getNumOutputs(); outIdx++) {
            for (NandGate selectionNand : selectionNands[outIdx]) {
                selectionNand.evaluate();
            }
            outputNands[outIdx].evaluate();
        }

    }


}
