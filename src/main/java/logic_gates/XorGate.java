package logic_gates;

import core_architecture.SingleOutputCircuit;

public class XorGate extends SingleOutputCircuit {
    private final InverterGate[] invPorts;
    private final NandGate[] calcNands;
    private final NandGate outputNand;
    

    public XorGate(String label, int nBits) {
        super(label, nBits);

        invPorts = new InverterGate[nBits];
        calcNands = new NandGate[nBits];

        outputNand = new NandGate(label + " Or", nBits);
        outputNand.assignOutput(getOutPortInput());
        transistorCount += outputNand.getTransistorCount();

        for (int invIdx = 0; invIdx < nBits; invIdx++) {
            invPorts[invIdx] = new InverterGate(label + " Inv_" + invIdx);
            invPorts[invIdx].assignInput(0, getInPortOutput(invIdx));
            transistorCount += invPorts[invIdx].getTransistorCount();
        }

        for (int nandIdx = 0; nandIdx < nBits; nandIdx++) {
            calcNands[nandIdx] = new NandGate(label + " And_" + nandIdx, nBits);
            transistorCount += calcNands[nandIdx].getTransistorCount();
            for (int inputIdx = 0; inputIdx < nBits; inputIdx++) {
                calcNands[nandIdx].assignInput(inputIdx,
                        inputIdx!=nandIdx ? invPorts[inputIdx].getOutput(0) : getInPortOutput(inputIdx));
            }
            outputNand.assignInput(nandIdx, calcNands[nandIdx].getOutput(0));
        }
    }

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
