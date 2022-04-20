package shift;

import core_architecture.CircuitNode;

public class ShiftLeftCircle extends Shift{

    public ShiftLeftCircle(String label, int nBit) {
        super(label, nBit);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i+n+1)<(getNumInputs()-getSelBitCnt()) ?
                getInPortOutput(i+n+1) :
                getInPortOutput(i+n+1-getNumInputs()+getSelBitCnt());
    }
}
