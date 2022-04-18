package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.NandGate;
import org.jetbrains.annotations.NotNull;

public class BitwiseNand extends DigitalCircuit {

    protected NandGate[] nands;

    public BitwiseNand() { super(); }

    public BitwiseNand(String label, int nBit) {
        super(label, nBit+nBit, nBit);

        nands = new NandGate[nBit];

        for (int i = 0; i < nBit; i++) {
            nands[i] = new NandGate(label + " Nand_" + i, 2);
            nands[i].assignInput(0, getPortOutput(i));
            nands[i].assignInput(1, getPortOutput(i+nBit));

            transistorCount += nands[i].getTransistorCount();

            assignOutput(i, nands[i].getOutput(0));
        }
    }

    public BitwiseNand(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public BitwiseNand(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        nands[i].assignOutput(0, output);
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();
        for(NandGate nand : nands) {
            nand.evaluate();
        }
    }
}
