package memory;

import core_architecture.SelectionCircuit;
import core_architecture.CircuitNode;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import multiplexer.Demux;
import multiplexer.Mux;

/**
 * A class representing a memory module.<br>
 * <br>
 * A MemoryModule object consists of:<ul>
 *     <li>numWords * nBits contiguous {@link CircuitNode CircuitNodes} representing physical memory</li>
 *     <li>A multiplexer with the memory from above on the input</li>
 *     <li>A demultiplexer with the memory from above on the output</li>
 *     <li>An op code inverter</li>
 *     <li>{@link #getSelBitCnt() selBitCnt} 2-bit demuxMaskAnds which Ands the input selection with the op code to only
 *     push the selection into the demux if the op code is 1 </li>
 *     <li>nBit 2-bit muxMaskAnds which only allows the mux to output its values if the op code is zero</li>
 * </ul>
 *
 * <b>Inputs</b>: nBits + 1 + selBitCnt corresponding to:<ul>
 * <li>an nBit bit string representing some value to write to memory</li>
 * <li>a 1-bit op code representing whether the selection should be read (0) or written to (1)</li>
 * <li>a selBitCnt bit string representing which word in memory to read or write.</li></ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string corresponding to the contents of the selected memory address if a read operation</li></ul>
 */
public class MemoryModule extends SelectionCircuit {

    private final Mux mux;
    private final Demux demux;

    private final InverterGate invOpCode;
    private final AndGate[] demuxMaskAnds;
    private final AndGate[] muxMaskAnds;

    // input is nBits for the word, 1 bit for opcode (0 is read, 1 is write), selBit for selector bits

    public MemoryModule(String label, int memSpacesIncZero, int wordWidth) {
        super(label, 1+wordWidth, wordWidth, determineSelectorBitCount(memSpacesIncZero));

        CircuitNode[] memory = new CircuitNode[wordWidth*memSpacesIncZero];

        mux = new Mux(label + " Mux", memSpacesIncZero, wordWidth);
        demux = new Demux(label + " Demux", memSpacesIncZero, wordWidth);

        invOpCode = new InverterGate(label + " InvertedOpCode");
        invOpCode.assignInput(getInternalInput(wordWidth));

        transistorCount += invOpCode.getTransistorCount() + mux.getTransistorCount() + demux.getTransistorCount();

        for (int m = 0; m < wordWidth*memSpacesIncZero; m++) {
            memory[m] = new CircuitNode(label + " Memory_" + m);
            mux.assignInput(m, memory[m]);
            demux.assignOutput(m, memory[m]);
        }

        demuxMaskAnds = new AndGate[getSelBitCnt()];
        for (int s = 0; s < getSelBitCnt(); s++) {
            mux.assignInput(mux.getNumInputs()-getSelBitCnt()+s, getSelBitOut(s));

            demuxMaskAnds[s] = new AndGate(label + "DemuxOpCodeMask_" + s, 2);
            demuxMaskAnds[s].assignInput(0, getInternalInput(wordWidth));
            demuxMaskAnds[s].assignInput(1, getSelBitOut(s));
            demuxMaskAnds[s].assignOutput(demux.getInput(demux.getNumInputs()-getSelBitCnt()+s));
            transistorCount += demuxMaskAnds[s].getTransistorCount();
        }

        muxMaskAnds = new AndGate[wordWidth];
        for (int w = 0; w < wordWidth; w++) {
            muxMaskAnds[w] = new AndGate(label + " MuxOpCodeMask_" + w, 2);
            muxMaskAnds[w].assignInput(0, invOpCode.getOutput());
            muxMaskAnds[w].assignInput(1, mux.getOutput(w));
            muxMaskAnds[w].assignOutput(getInternalOutput(w));
            transistorCount += muxMaskAnds[w].getTransistorCount();

            demux.assignInput(w, getInternalInput(w));
        }
    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();

        invOpCode.evaluate();

        for (AndGate demuxMaskAnd : demuxMaskAnds) {
            demuxMaskAnd.evaluate();
        }

        demux.evaluate();

        mux.evaluate();

        for (AndGate muxMaskAnd : muxMaskAnds) {
            muxMaskAnd.evaluate();
        }
    }
}
