package shift;

import core_architecture.CircuitNode;

public class ShiftRightSigned extends Shift{
    public ShiftRightSigned() { super(); }

    public ShiftRightSigned(String label, int nBit) {
        super(label, nBit);
    }

    public ShiftRightSigned(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public ShiftRightSigned(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i-n-1)>=0 ? getPortOutput(i-n-1) : getPortOutput(0);
    }
}
