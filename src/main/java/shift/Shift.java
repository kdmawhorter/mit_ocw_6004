package shift;

import core_architecture.BitMuxShiftCore;
import core_architecture.CircuitNode;
import multiplexer.Mux;

public abstract class Shift extends BitMuxShiftCore {

    private final Mux[] muxes;

    public Shift(String label, int nBit) {
        super(label, nBit, nBit, determineSelectorBitCount(nBit));

        muxes = new Mux[nBit];
        for (int inputIdx = 0; inputIdx < nBit; inputIdx++) {
            muxes[inputIdx] = new Mux(label + " Multiplexer_" + inputIdx, nBit, 1);
            transistorCount += muxes[inputIdx].getTransistorCount();

            for (int assignIdx = 0; assignIdx < nBit; assignIdx++) {
                muxes[inputIdx].assignInput(assignIdx, getNthMappingForInputI(assignIdx, inputIdx));
                for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
                    muxes[inputIdx].assignInput(nBit+selIdx, getSelBitOut(selIdx));
                }
            }
            muxes[inputIdx].assignOutput(0, getInternalOutput(inputIdx));
        }
    }

    public abstract CircuitNode getNthMappingForInputI(int n, int i);

    @Override
    protected void evaluateCircuit() {
        for (Mux mux : muxes) {
            mux.evaluate();
        }
    }
}
