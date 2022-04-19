package shift;

import core_architecture.CircuitNode;

public class ShiftLeftUnsigned extends Shift{
    public ShiftLeftUnsigned() { super(); }

    public ShiftLeftUnsigned(String label, int nBit) {
        super(label, nBit);
    }

    public ShiftLeftUnsigned(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public ShiftLeftUnsigned(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i+n+1)<(getNumInputs()-getSelBitCnt()) ? getPortOutput(i+n+1) : GND;
    }
}
