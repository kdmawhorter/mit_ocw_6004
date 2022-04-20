package shift;

import core_architecture.CircuitNode;

public class ShiftLeftUnsigned extends Shift{
    public ShiftLeftUnsigned(String label, int nBit) {
        super(label, nBit);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i+n+1)<(getNumInputs()-getSelBitCnt()) ? getInPortOutput(i+n+1) : GND;
    }
}
