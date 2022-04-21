package shift;

import core_architecture.SelectionCircuit;
import core_architecture.CircuitNode;
import multiplexer.Mux;

/**
 * An abstract class representing a shift operation.<br>
 * <br>
 A Shift object consists of:<br>
 * <ul>
 * <li>All {@link SelectionCircuit SelectionCircuit} internals</li>
 * <li>An nBit mux for each bit, which will map each bit to each possible shift value from [0, nBit)</li></ul>
 * 
 * Each inheriting class of Shift will populate the method for which {@link CircuitNode node} should be returned for 
 * each shift.<br>
 * <br>
 * Shifts are 0-indexed, meaning a shift of 0 will shift one space.
 */
public abstract class Shift extends SelectionCircuit {

    private final Mux[] muxes;

    /**
     * Shift constructor.
     * 
     * @param label The name of the circuit.
     * @param nBit The number of bits in the input.
     */
    public Shift(String label, int nBit) {
        super(label, nBit, nBit, determineSelectorBitCount(nBit));

        muxes = new Mux[nBit];
        for (int inputIdx = 0; inputIdx < nBit; inputIdx++) {
            muxes[inputIdx] = new Mux(label + " Multiplexer_" + inputIdx, nBit, 1);
            transistorCount += muxes[inputIdx].getTransistorCount();

            for (int assignIdx = 0; assignIdx < nBit; assignIdx++) {
                muxes[inputIdx].assignInput(assignIdx, getKthMappingForInputI(assignIdx, inputIdx));
                for (int selIdx = 0; selIdx < getSelBitCnt(); selIdx++) {
                    muxes[inputIdx].assignInput(nBit+selIdx, getSelBitOut(selIdx));
                }
            }
            muxes[inputIdx].assignOutput(0, getInternalOutput(inputIdx));
        }
    }

    /**
     * Gets the node which should returned when index i is shifted by k.
     * 
     * @param k The amount to be shifted.
     * @param i The index that is to be shifted.
     * @return The node that will be in index i after a shift of k.
     */
    public abstract CircuitNode getKthMappingForInputI(int k, int i);

    /**
     * Evaluate the mux for each input bit.
     */
    @Override
    protected void evaluateCircuit() {
        for (Mux mux : muxes) {
            mux.evaluate();
        }
    }
}
