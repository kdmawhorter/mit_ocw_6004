package bitwise;

import arithmetic.Negate;
import arithmetic.UnsignedAdd;
import comparison.GreaterThanOrEquals;
import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.InverterGate;

/**
 * A class representing a bit division helper. The intent of this class is to serve as a helper to the {@link
 * arithmetic.Division Division} class, by determining if the input divisor (B) divides the input dividend (A), and the
 * remainder after doing so. The output of this logic block only writes if B is valid, defined as the most significant
 * 1 in B is its second most significant bit. The output of this logic block is only valid if A < 2*B,
 * which is enforced in the Division class.<br>
 * <br>
 * A BitDivision object consists of:<ul>
 *     <li>an nBit {@link GreaterThanOrEquals} object used to determine if A>B and thus B divides A.</li>
 *     <li>an nBit {@link Negate} object used to negate B to subtract from A</li>
 *     <li>an nBit {@link BitwiseAnd} object used to only use the negated B above if B divides A.</li>
 *     <li>an nBit {@link UnsignedAdd} object used to calculate the remainder.</li>
 *     <li>a 1-bit {@link InverterGate} used to invert the highest bit of B</li>
 *     <li>a 2-bit {@link AndGate} used to determine if B is a valid divisor.</li>
 *     <li>nBit 2-bit AndGates to output the results if B is a valid divisor.</li>
 * </ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 *  <li>an nBit bit string representing an input dividend A</li>
 *  <li>an nBit bit string representing an input divisor B</li></ul>
 *  <b>Outputs</b>: 1+nBits corresponding to:<ul>
 *  <li>1 bit representing if B divides A, if B is a valid divisor</li>
 *  <li>an nBit bit string representing the remainder of A after removing B, if B is a valid divisor</li></ul>
 */
public class BitDivision extends DigitalCircuit {
    private final GreaterThanOrEquals dividesGTE;

    private final Negate negativeDivisor;
    private final BitwiseAnd negDivisorIfGTE;
    private final UnsignedAdd remainder;

    private final AndGate validDivisor;
    private final InverterGate invDivisorHighBit;
    private final AndGate[] outputAndDivsArentZero;

    private final int nBits;

    /**
     * BitDivision constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public BitDivision(String label, int nBits) {
        super(label, nBits*2, 1+nBits);

        this.nBits = nBits;

        dividesGTE = new GreaterThanOrEquals(label + " DividesGTE", nBits);
        negativeDivisor = new Negate(label + " NegativeDivisor", nBits);
        negDivisorIfGTE = new BitwiseAnd(label + " NegativeDivisorIfDivides", nBits);
        remainder = new UnsignedAdd(label + " RemainderCalc", nBits);

        transistorCount += dividesGTE.getTransistorCount() + negativeDivisor.getTransistorCount() +
                negDivisorIfGTE.getTransistorCount() + remainder.getTransistorCount();

        invDivisorHighBit = new InverterGate(label + " InvHighDivisorBit");
        invDivisorHighBit.assignInput(getDivisorBit(0));

        validDivisor = new AndGate(label + " ValidDivisor", 2);
        validDivisor.assignInput(0, invDivisorHighBit.getOutput());
        validDivisor.assignInput(1, getDivisorBit(1));

        transistorCount += invDivisorHighBit.getTransistorCount() + validDivisor.getTransistorCount();

        outputAndDivsArentZero = new AndGate[1+nBits];

        for (int i = 0; i < nBits; i++) {
            dividesGTE.assignInput(i, getDividendBit(i));
            dividesGTE.assignInput(i+nBits, getDivisorBit(i));

            negativeDivisor.assignInput(i, getDivisorBit(i));

            negDivisorIfGTE.assignInput(i, negativeDivisor.getOutput(i));
            negDivisorIfGTE.assignInput(i+nBits, dividesGTE.getOutput());

            remainder.assignInput(i, getDividendBit(i));
            remainder.assignInput(i+nBits, negDivisorIfGTE.getOutput(i));
            setBitDivisionOutput(i+1, remainder.getOutput(i));
        }

        setBitDivisionOutput(0, dividesGTE.getOutput());
    }

    /**
     * Assign the output to the product of output and whether the divisor is valid.
     * @param i The output index to set.
     * @param output The node to be the new output.
     */
    private void setBitDivisionOutput(int i, CircuitNode output) {
        outputAndDivsArentZero[i] = new AndGate(getLabel() + " OutputAnd_" + i, 2);
        outputAndDivsArentZero[i].assignInput(0, output);
        outputAndDivsArentZero[i].assignInput(1, validDivisor.getOutput());
        outputAndDivsArentZero[i].assignOutput(getInternalOutput(i));

        transistorCount += outputAndDivsArentZero[i].getTransistorCount();
    }

    /**
     * Gets the ith divisor bit.
     * @param i The index of the divisor bit to get.
     * @return The output of the ith divisor bit.
     */
    private CircuitNode getDivisorBit(int i) {
        return getInternalInput(i+nBits);
    }

    /**
     * Gets the ith dividend bit.
     * @param i The index of the dividend bit to get.
     * @return The output of the ith dividend bit.
     */
    private CircuitNode getDividendBit(int i) {
        return getInternalInput(i);
    }

    @Override
    protected void evaluateCircuit() {
        invDivisorHighBit.evaluate();
        validDivisor.evaluate();

        dividesGTE.evaluate();
        negativeDivisor.evaluate();
        negDivisorIfGTE.evaluate();
        remainder.evaluate();

        for (AndGate outputAnd : outputAndDivsArentZero) {
            outputAnd.evaluate();
        }
    }
}
