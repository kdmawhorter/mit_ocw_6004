package logic_gates;

import core_architecture.DigitalCircuit;
import core_architecture.SingleOutputCircuit;

/**
 * A class representing the logic gate Xor.
 * <br><br>
 * Given nBits of input, the output is {@link DigitalCircuit#VDD VDD} if one and only one of those inputs is VDD, else
 * it is {@link DigitalCircuit#GND GND}.
 */
public class XorGate extends SingleOutputCircuit {
    private final InverterGate[] invPorts;
    private final NandGate[] calcNands;
    private final NandGate outputNand;


    /**
     * XorGate constructor
     * @param label The name of the circuit.
     * @param nBits The number of inputs.
     */
    public XorGate(String label, int nBits) {
        super(label, nBits);

        invPorts = new InverterGate[nBits];
        calcNands = new NandGate[nBits];

        outputNand = new NandGate(label + " Or", nBits);
        outputNand.assignOutput(getInternalOutput());
        transistorCount += outputNand.getTransistorCount();

        for (int invIdx = 0; invIdx < nBits; invIdx++) {
            invPorts[invIdx] = new InverterGate(label + " Inv_" + invIdx);
            invPorts[invIdx].assignInput(0, getInternalInput(invIdx));
            transistorCount += invPorts[invIdx].getTransistorCount();
        }

        for (int nandIdx = 0; nandIdx < nBits; nandIdx++) {
            calcNands[nandIdx] = new NandGate(label + " And_" + nandIdx, nBits);
            transistorCount += calcNands[nandIdx].getTransistorCount();
            for (int inputIdx = 0; inputIdx < nBits; inputIdx++) {
                calcNands[nandIdx].assignInput(inputIdx,
                        inputIdx!=nandIdx ? invPorts[inputIdx].getOutput(0) : getInternalInput(inputIdx));
            }
            outputNand.assignInput(nandIdx, calcNands[nandIdx].getOutput(0));
        }
    }

    /**
     * First evaluates all the inverters tied to each input port.<br>
     * Then evaluates each "only one input true" Nand (e.g. Nand(A(!B)CD)).<br>
     * Then evaluates the Nand tying all those "only one input true" Nands together.
     */
    protected void evaluateCircuit() {
        for (InverterGate invPort : invPorts) {
            invPort.evaluate();
        }
        for (NandGate calcNand : calcNands) {
            calcNand.evaluate();
        }
        outputNand.evaluate();
    }
}
