package multiplexer;

import core_architecture.SelectionCircuit;
import core_architecture.CircuitNode;
import logic_gates.NandGate;

/**
 * A class representing a demultiplexer/memory writer.<br>
 * <br>
 * A Demux object consists of:<br>
 * <br>
 * Overall Output Logic:<br>
 * O[j,i] = 1 if (A[i] and word j is selected) or (O[j,i] and word j is not selected), else 0<br>
 * <br>
 * Input Write Logic:<br>
 * INPUT_SEL_NAND[j][i] = NAND(A[i],S0j,S1j,...,Skj)<ul>
 *     <li>1 (1+{@link #getSelBitCnt() selBitCnt})-bit Nand indicating whether the bit is on and the word is selected,
 *     for each bit of each word<br></li>
 * </ul>
 * Memory Write Logic:<br>
 * MEM_SEL_NAND[j][i] = NAND(NAND(S0j, S1j, ..., Skj), O[j][i])<ul>
 *     <li>1 selBitCnt-bit Nand indicating whether the word is selected, for each word, representing the inner nand
 *     from above.</li>
 *     <li>1 2-bit Nand representing the outer Nand from above.</li>
 * </ul>
 * Output Logic:<ul>
 *     <li>1 2-bit Nand putting together the output from the Input Write and Memory Write sections above.</li>
 * </ul>
 *
 * <b>Inputs</b>: nBits + selBitCnt corresponding to:<ul>
 * <li>an nBit bit string representing some value to write to memory</li>
 * <li>a selBitCnt bit string representing which word in memory to overwrite.</li></ul>
 * <b>Outputs</b>: numChoices * nBits corresponding to:<ul>
 * <li>numWords contiguous entries of nBit bit strings, representing some memory input A</li></ul>
 */

public class Demux extends SelectionCircuit {
    
    private final int wordWidth;
    private final int numChoices;
    
    private final NandGate[][] inputSelNands;
    private final NandGate[][] memSelNands;
    private final NandGate[] selNands;
    private final NandGate[][] outputNands;

    /**
     * Demux constructor.
     *
     * @param label The name of the circuit.
     * @param numChoicesIncZero The number of words in output.
     * @param wordWidth The number of bits in a word.
     */
    public Demux(String label, int numChoicesIncZero, int wordWidth) {
        super(label, wordWidth, numChoicesIncZero*wordWidth, determineSelectorBitCount(numChoicesIncZero));
        
        this.wordWidth = wordWidth;
        this.numChoices = numChoicesIncZero-1;

        inputSelNands = new NandGate[numChoices][wordWidth];
        memSelNands = new NandGate[numChoices][wordWidth];
        selNands = new NandGate[numChoices];
        outputNands = new NandGate[numChoices][wordWidth];

        boolean[] currSelBits = new boolean[getSelBitCnt()];
        
        for (int outWord = 0; outWord < numChoices; outWord++) {
            currSelBits = incrementTruthArray(currSelBits);

            selNands[outWord] = new NandGate(
                    label + "SelNand_" + outWord + "_", getSelBitCnt());
            transistorCount += selNands[outWord].getTransistorCount();

            for (int selBit = 0; selBit < getSelBitCnt(); selBit++) {
                selNands[outWord].assignInput(selBit, currSelBits[selBit] ?
                        getSelBitOut(selBit) : getInvSelBitOut(selBit));
            }

            for (int outBit = 0; outBit < wordWidth; outBit++) {
                inputSelNands[outWord][outBit] = new NandGate(
                        label + "InputSelNand_" + outWord + "_" + outBit, 1 + getSelBitCnt());
                transistorCount += inputSelNands[outWord][outBit].getTransistorCount();

                memSelNands[outWord][outBit] = new NandGate(
                        label + "MemSelNand_" + outWord + "_" + outBit, 2);
                transistorCount += memSelNands[outWord][outBit].getTransistorCount();

                outputNands[outWord][outBit] = new NandGate(
                        label + "OutputNand_" + outWord + "_" + outBit, 2);
                transistorCount += outputNands[outWord][outBit].getTransistorCount();

                inputSelNands[outWord][outBit].assignInput(0, getInternalInput(outBit));
                for (int selBit = 0; selBit < getSelBitCnt(); selBit++) {
                    inputSelNands[outWord][outBit].assignInput(1+selBit, currSelBits[selBit] ?
                            getSelBitOut(selBit) : getInvSelBitOut(selBit));
                }
                
                memSelNands[outWord][outBit].assignInput(0, selNands[outWord].getOutput(0));
                memSelNands[outWord][outBit].assignInput(1, getNthIOutput(outWord, outBit));
                
                outputNands[outWord][outBit].assignInput(0, memSelNands[outWord][outBit].getOutput(0));
                outputNands[outWord][outBit].assignInput(1, inputSelNands[outWord][outBit].getOutput(0));

                outputNands[outWord][outBit].assignOutput(getInternalOutput((outWord+1)*wordWidth+outBit));
            }

            for (int zeroRegisterBit = 0; zeroRegisterBit < wordWidth; zeroRegisterBit++) {
                super.assignOutput(zeroRegisterBit, GND);
            }
        }
    }

    /**
     * Gets the ith bit of the nth word of the output.
     *
     * @param n The word index to retrieve.
     * @param i The bit index of the word to retrieve.
     * @return The node representing the ith bit of the nth word in the output.
     */
    private CircuitNode getNthIOutput(int n, int i) {
        return getInternalOutput((n+1)*wordWidth + i);
    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();

        for (int outWord = 0; outWord < numChoices; outWord++) {
            selNands[outWord].evaluate();
            for (int outBit = 0; outBit < wordWidth; outBit++) {
                inputSelNands[outWord][outBit].evaluate();
                memSelNands[outWord][outBit].evaluate();
                outputNands[outWord][outBit].evaluate();
            }
        }
    }
}
