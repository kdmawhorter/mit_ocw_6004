package shift;

import core_architecture.CircuitNode;

public class ShiftLeftSigned extends Shift{

    public ShiftLeftSigned(String label, int nBit) {
        super(label, nBit);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        if (i==0) {
            return getInternalInput(0);
        }
        return (i+n+1)<(getNumInputs()-getSelBitCnt()) ? getInternalInput(i+n+1) : GND;
    }
}
