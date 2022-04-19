package shift;

import core_architecture.CircuitNode;

public class ShiftRightCircle extends Shift{
    public ShiftRightCircle() { super(); }

    public ShiftRightCircle(String label, int nBit) {
        super(label, nBit);
    }

    public ShiftRightCircle(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public ShiftRightCircle(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i-n-1)>=0 ? getPortOutput(i-n-1) : getPortOutput(getNumInputs()-getSelBitCnt()+i-n-1);
    }
}
