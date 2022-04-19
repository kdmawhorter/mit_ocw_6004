package shift;

import core_architecture.CircuitNode;

public class ShiftLeftCircle extends Shift{
    public ShiftLeftCircle() { super(); }

    public ShiftLeftCircle(String label, int nBit) {
        super(label, nBit);
    }

    public ShiftLeftCircle(String label, int nBit, CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public ShiftLeftCircle(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public CircuitNode getNthMappingForInputI(int n, int i) {
        return (i+n+1)<(getNumInputs()-getSelBitCnt()) ?
                getPortOutput(i+n+1) :
                getPortOutput(i+n+1-getNumInputs()+getSelBitCnt());
    }
}
