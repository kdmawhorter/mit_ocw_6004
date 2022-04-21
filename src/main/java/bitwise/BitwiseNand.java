package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.NandGate;

/**
 * A class representing a Bitwise Nand operation.<br>
 * <br>
 A BitwiseNand object consists of:<br>
 * <ul>
 * <li>"Output" Nand gates tying together each pair of input bits. O[i] = Nand(A[i], B[i]).</li></ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A</li>
 * <li>an nBit bit string representing an input B</li>
 * </ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the bitwise nand output of A and B</li>
 * </ul>
 * <br>
 */
public class BitwiseNand extends DigitalCircuit {

    private final NandGate[] nands;

    /**
     * BitwiseNand constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public BitwiseNand(String label, int nBits) {
        super(label, nBits+nBits, nBits);

        nands = new NandGate[nBits];

        for (int i = 0; i < nBits; i++) {
            nands[i] = new NandGate(label + " Nand_" + i, 2);
            nands[i].assignInput(0, getInternalInput(i));
            nands[i].assignInput(1, getInternalInput(i+nBits));
            nands[i].assignOutput(getInternalOutput(i));
            transistorCount += nands[i].getTransistorCount();
        }
    }

    /**
     * Evaluates the Nand gate corresponding to each bit of A and B.
     */
    @Override
    protected void evaluateCircuit() {
        for(NandGate nand : nands) {
            nand.evaluate();
        }
    }
}
