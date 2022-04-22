package arithmetic;

import bitwise.FullAdder;
import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;

/**
 * A class representing an unsigned addition operation.<br>
 * <br>
 An UnsignedAdd object consists of: <ul>
 * <li>nBit {@link FullAdder FullAdders}, whose low bit corresponds to output, and whose high bit corresponds to the carry
 * bit.</li></ul>
 * Overflow is said to occur if the most significant bit carrys over.<br>
 * <br>
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li></ul>
 * <b>Outputs</b>: nBits+1 corresponding to:<ul>
 * <li>an nBit bit string representing the unsigned addition of A and B.</li>
 * <li>a 1 bit overflow bit</li></ul>
 */

public class UnsignedAdd extends DigitalCircuit {
    private final FullAdder[] fullAdders;

    /**
     * UnsignedAdd constructor
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public UnsignedAdd(String label, int nBits) {
        super(label, nBits + nBits, nBits + 1);

        fullAdders = new FullAdder[nBits];

        for (int i = nBits-1; i >= 0; i--) {
            fullAdders[i] = new FullAdder(label + " FullAdder_" + i,
                    i<nBits-1 ? fullAdders[i+1].getOutput(0) : GND,
                    getInternalInput(i),
                    getInternalInput(i+nBits));
            fullAdders[i].assignOutput(1, getInternalOutput(i));
            transistorCount += fullAdders[i].getTransistorCount();
        }

        fullAdders[0].assignOutput(0, getInternalOutput(nBits));
    }

    /**
     * Decouples the unsigned addition overflow logic to prevent conflicting with other overflow logic.
     */
    protected void decoupleUnsignedOverflow() {
        fullAdders[0].assignOutput(0, new CircuitNode("Uncoupled Overflow Node"));
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = getNumOutputs()-2; i >= 0; i--) {
            fullAdders[i].evaluate();
        }
    }
}
