package shift;

import core_architecture.BitMuxShiftCore;
import core_architecture.CircuitNode;
import multiplexer.Multiplexer;
import org.jetbrains.annotations.NotNull;

public abstract class Shift extends BitMuxShiftCore {

    private Multiplexer[] muxes;

    public Shift() { super(); }

    public Shift(String label, int nBit) {
        super(label, nBit, nBit, determineSelectorBitCount(nBit));

        muxes = new Multiplexer[nBit];
        for (int inputIdx = 0; inputIdx < nBit; inputIdx++) {
            muxes[inputIdx] = new Multiplexer(label + " Multiplexer_" + inputIdx, nBit, 1);
            transistorCount += muxes[inputIdx].getTransistorCount();

            for (int assignIdx = 0; assignIdx < nBit; assignIdx++) {
                muxes[inputIdx].assignInput(assignIdx, getNthMappingForInputI(assignIdx, inputIdx));
                for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
                    muxes[inputIdx].assignInput(nBit+selIdx, getSelBitOut(selIdx));
                }
            }

            assignOutput(inputIdx, muxes[inputIdx].getOutput(0));
        }
    }

    public abstract CircuitNode getNthMappingForInputI(int n, int i);

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        for (Multiplexer mux : muxes) {
            mux.evaluate();
        }
    }
}
