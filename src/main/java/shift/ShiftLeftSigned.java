package shift;

import core_architecture.CircuitNode;

public class ShiftLeftSigned extends Shift{
    public ShiftLeftSigned() { super(); }

    public ShiftLeftSigned(String label, int nBit) {
        super(label, nBit);
    }

    public ShiftLeftSigned(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public ShiftLeftSigned(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        if (i==0) {
            return getPortOutput(0);
        }
        return (i+n+1)<(getNumInputs()-getSelBitCnt()) ? getPortOutput(i+n+1) : GND;
    }
}
