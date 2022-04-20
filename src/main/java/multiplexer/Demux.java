package multiplexer;

import core_architecture.BitMuxShiftCore;
import core_architecture.CircuitNode;
import logic_gates.NandGate;

public class Demux extends BitMuxShiftCore {
    
    private final int wordWidth;
    private final int numChoices;
    
    private final NandGate[][] inputSelNands;
    private final NandGate[][] memSelNands;
    private final NandGate[][] selNands;
    private final NandGate[][] outputNands;
    
    // For any memory address, the ith bit of that memory address is the following:
         
    //     NAND(NAND(SEL, INPUT_i),NAND(NAND(SEL), MEM_i)  )
    //          ==>
    //     outputNand(inputSelNand(sel, inp_i), memSelNand(selNand(sel), mem_i)
    
    //     selCombo, inp_i -> inputSelNand
    //     selCombo -> selNand
    //     selNand, mem_i -> memSelNand
    //     inputSelNand, memSelNand -> outputNand

    public Demux(String label, int numChoicesIncZero, int wordWidth) {
        super(label, wordWidth, numChoicesIncZero*wordWidth, determineSelectorBitCount(numChoicesIncZero));
        
        this.wordWidth = wordWidth;
        this.numChoices = numChoicesIncZero-1;

        inputSelNands = new NandGate[numChoices][wordWidth];
        memSelNands = new NandGate[numChoices][wordWidth];
        selNands = new NandGate[numChoices][wordWidth];
        outputNands = new NandGate[numChoices][wordWidth];

        boolean[] currSelBits = new boolean[getSelBitCnt()];
        
        for (int outWord = 0; outWord < numChoices; outWord++) {
            currSelBits = incrementTruthArray(currSelBits);

            for (int outBit = 0; outBit < wordWidth; outBit++) {
                inputSelNands[outWord][outBit] = new NandGate(
                        label + "InputSelNand_" + outWord + "_" + outBit, 1 + getSelBitCnt());
                transistorCount += inputSelNands[outWord][outBit].getTransistorCount();

                selNands[outWord][outBit] = new NandGate(
                        label + "SelNand_" + outWord + "_" + outBit, getSelBitCnt());
                transistorCount += selNands[outWord][outBit].getTransistorCount();

                memSelNands[outWord][outBit] = new NandGate(
                        label + "MemSelNand_" + outWord + "_" + outBit, 2);
                transistorCount += memSelNands[outWord][outBit].getTransistorCount();

                outputNands[outWord][outBit] = new NandGate(
                        label + "OutputNand_" + outWord + "_" + outBit, 2);
                transistorCount += outputNands[outWord][outBit].getTransistorCount();

                inputSelNands[outWord][outBit].assignInput(0, getInPortOutput(outBit));
                for (int selBit = 0; selBit < getSelBitCnt(); selBit++) {
                    CircuitNode selOut = currSelBits[selBit] ? getSelBitOut(selBit) : getInvSelBitOut(selBit);
                    
                    inputSelNands[outWord][outBit].assignInput(1+selBit, selOut);
                    selNands[outWord][outBit].assignInput(selBit, selOut);
                }
                
                memSelNands[outWord][outBit].assignInput(0, selNands[outWord][outBit].getOutput(0));
                memSelNands[outWord][outBit].assignInput(1, getNthIOutput(outWord, outBit));
                
                outputNands[outWord][outBit].assignInput(0, memSelNands[outWord][outBit].getOutput(0));
                outputNands[outWord][outBit].assignInput(1, inputSelNands[outWord][outBit].getOutput(0));

                outputNands[outWord][outBit].assignOutput(getOutPortInput((outWord+1)*wordWidth+outBit));
            }

            for (int zeroRegisterBit = 0; zeroRegisterBit < wordWidth; zeroRegisterBit++) {
                super.assignOutput(zeroRegisterBit, GND);
            }
        }
    }
    
    public CircuitNode getNthIOutput(int n, int i) {
        return getOutput((n+1)*wordWidth + i);
    }
    
    @Override
    public void evaluateCircuit() {
        super.evaluateCircuit();

        for (int outWord = 0; outWord < numChoices; outWord++) {
            for (int outBit = 0; outBit < wordWidth; outBit++) {
                inputSelNands[outWord][outBit].evaluate();
                selNands[outWord][outBit].evaluate();
                memSelNands[outWord][outBit].evaluate();
                outputNands[outWord][outBit].evaluate();
            }
        }
    }
}
