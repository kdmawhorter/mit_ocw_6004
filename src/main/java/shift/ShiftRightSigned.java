package shift;

import core_architecture.CircuitNode;

/**
 * A class representing a right signed shift.<br>
 * <br>
 * A ShiftRightSigned object consists of the components of a {@link Shift Shift} object.<br>
 * <br>
 * For a shift of K, O[i] is defined as:<ul>
 *     <li>if i >= k+1 , O[i] = A[i-k-1]</li>
 *     <li>else, O[i] = A[0] </li></ul>
 *
 * <b>Inputs</b>: nBits + {@link core_architecture.SelectionCircuit#getSelBitCnt()  selBitCnt} corresponding to:<ul>
 * <li>an nBit bit string representing an input A to be shifted</li>
 * <li>a selBitCnt bit string representing an unsigned integer to shift</li></ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the shifted output.</li></ul>
 */
public class ShiftRightSigned extends Shift{

    /**
     * ShiftRightSigned constructor.
     *
     * @param label The name of the circuit.
     * @param nBit The number of bits in the input.
     */
    public ShiftRightSigned(String label, int nBit) {
        super(label, nBit);
    }

    @Override
    public CircuitNode getKthMappingForInputI(int n, int i) {
        return (i-n-1)>=0 ? getInternalInput(i-n-1) : getInternalInput(0);
    }
}
