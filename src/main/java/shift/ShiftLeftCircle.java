package shift;

import core_architecture.CircuitNode;
import core_architecture.SelectionCircuit;

/**
 * A class representing a left circle shift.<br>
 * <br>
 * A ShiftLeftCircle object consists of the components of a {@link Shift Shift} object.<br>
 * <br>
 * For a shift of K, O[i] is defined as:<ul>
 *     <li>if i+k+1<nBit, O[i] = A[i+k+1]</li>
 *     <li>else, O[i] = A[i+k+1-nBit]</li></ul>
 *
 * <b>Inputs</b>: nBits + {@link SelectionCircuit#getSelBitCnt()  selBitCnt} corresponding to:<ul>
 * <li>an nBit bit string representing an input A to be shifted</li>
 * <li>a selBitCnt bit string representing an unsigned integer to shift</li></ul>
 * <b>Outputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing the shifted output.</li></ul>
 */
public class ShiftLeftCircle extends Shift{

    /**
     * ShiftLeftCircle constructor.
     *
     * @param label The name of the circuit.
     * @param nBit The number of bits in the input.
     */
    public ShiftLeftCircle(String label, int nBit) {
        super(label, nBit);
    }

    @Override
    public CircuitNode getKthMappingForInputI(int n, int i) {
        return (i+n+1)<(getNumInputs()-getSelBitCnt()) ?
                getInternalInput(i+n+1) :
                getInternalInput(i+n+1-getNumInputs()+getSelBitCnt());
    }
}
