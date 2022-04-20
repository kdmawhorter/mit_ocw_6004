package shift;

import core_architecture.CircuitNode;

public class ShiftRightCircle extends Shift{

    public ShiftRightCircle(String label, int nBit) {
        super(label, nBit);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i-n-1)>=0 ? getInPortOutput(i-n-1) : getInPortOutput(getNumInputs()-getSelBitCnt()+i-n-1);
    }
}
