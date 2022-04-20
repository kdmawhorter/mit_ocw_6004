package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.NandGate;

public class BitwiseNand extends DigitalCircuit {

    protected final NandGate[] nands;

    public BitwiseNand(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        nands = new NandGate[nBit];

        for (int i = 0; i < nBit; i++) {
            nands[i] = new NandGate(label + " Nand_" + i, 2);
            nands[i].assignInput(0, getInPortOutput(i));
            nands[i].assignInput(1, getInPortOutput(i+nBit));
            nands[i].assignOutput(getOutPortInput(i));
            transistorCount += nands[i].getTransistorCount();
        }
    }

    @Override
    protected void evaluateCircuit() {
        for(NandGate nand : nands) {
            nand.evaluate();
        }
    }
}
