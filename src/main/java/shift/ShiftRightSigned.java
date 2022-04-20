package shift;

import core_architecture.CircuitNode;

public class ShiftRightSigned extends Shift{

    public ShiftRightSigned(String label, int nBit) {
        super(label, nBit);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i-n-1)>=0 ? getInPortOutput(i-n-1) : getInPortOutput(0);
    }
}
