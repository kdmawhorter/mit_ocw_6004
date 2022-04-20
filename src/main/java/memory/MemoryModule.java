package memory;

import core_architecture.BitMuxShiftCore;
import core_architecture.CircuitNode;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import multiplexer.Demux;
import multiplexer.Mux;

public class MemoryModule extends BitMuxShiftCore {

    private final Mux mux;
    private final Demux demux;

    private final AndGate[] demuxMaskAnds;
    private final AndGate[] muxMaskAnds;
    private final InverterGate invOpCode;

    // input is nBits for the word, 1 bit for opcode (0 is read, 1 is write), selBit for selector bits

    public MemoryModule(String label, int memSpacesIncZero, int wordWidth) {
        super(label, 1+wordWidth, wordWidth, determineSelectorBitCount(memSpacesIncZero));

        CircuitNode[] memory = new CircuitNode[wordWidth*memSpacesIncZero];

        mux = new Mux(label + " Mux", memSpacesIncZero, wordWidth);
        demux = new Demux(label + " Demux", memSpacesIncZero, wordWidth);

        invOpCode = new InverterGate(label + " InvertedOpCode");
        invOpCode.assignInput(getInPortOutput(wordWidth));
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
            demuxMaskAnds[s].assignInput(0, getInPortOutput(wordWidth));
            demuxMaskAnds[s].assignInput(1, getSelBitOut(s));
            demuxMaskAnds[s].assignOutput(demux.getInput(demux.getNumInputs()-getSelBitCnt()+s));
            transistorCount += demuxMaskAnds[s].getTransistorCount();
        }

        muxMaskAnds = new AndGate[wordWidth];
        for (int w = 0; w < wordWidth; w++) {
            muxMaskAnds[w] = new AndGate(label + " MuxOpCodeMask_" + w, 2);
            muxMaskAnds[w].assignInput(0, invOpCode.getOutput());
            muxMaskAnds[w].assignInput(1, mux.getOutput(w));
            muxMaskAnds[w].assignOutput(getOutPortInput(w));
            transistorCount += muxMaskAnds[w].getTransistorCount();

            demux.assignInput(w, getInPortOutput(w));
        }
    }

    @Override
    public void evaluateCircuit() {
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
