package multiplexer;

import core_architecture.SelectionCircuit;
import core_architecture.CircuitNode;
import logic_gates.NandGate;

/**
 * A class representing a multiplexer.<br>
 * <br>
 * A Mux object consists of:<br>
 * <ul>
 *  Output Logic:<br>
 *  O[i] = 1 if the A[j, i] and word j is selected, else 0.<br>
 *  O[i] = NAND(NAND(A[i],!S0,!S1,...,!Sk), NAND(A[i+nBit],!S0, !S1,...Sk), ...)<br>
 *  <li>numberOfWords * (1+{@link #getSelBitCnt() selBitCnt})-bit Nands for each bit representing the inner Nands
 *  above.</li>
 *  <li>1 numberOfWords-bit Nand representing the outer Nand above.</li></ul>
 *
 * <b>Inputs</b>: numWords * nBits + selBitCnt corresponding to:<ul>
 * <li>numWords contiguous entries of nBit bit strings, representing some memory input A</li>
 * <li>a selBitCnt bit string representing which input word to select.</li></ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the selected word from memory</li></ul>
 */
public class Mux extends SelectionCircuit {

    private final NandGate[][] selectionNands;
    private final NandGate[] outputNands;

    /**
     * Mux constructor.
     *
     * @param label The name of the circuit.
     * @param numChoices The number of words to choose from.
     * @param wordWidth The width of each word in bits.
     */
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

    /**
     * Gets the ith bit of the nth word from the input ports.
     *
     * @param n The word index to retrieve
     * @param i The bit index of the input word to retrieve
     * @return The {@link core_architecture.DigitalCircuit#getInternalInput(int) internal input} of the ith bit of the
     * nth word
     */
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
