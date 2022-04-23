package division;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;

/**
 * A class representing a division helper. The intent of this class is to serve as a helper to the {@link
 * Division Division} class, by determining the quotient and remainder from an input dividend (A) and divisor (B).
 * This is achieved by cascading {@link BitDivision} objects to determine the remainder and quotient at each bit
 * position. The output of this logic block is only valid if the most significant bit in B is 1.<br>
 * <br>
 * A DivisionDivisorMSBOn object consists of:<ul>
 *     <li> (dividendBits-divisorBits+1) BitDivision objects with input sizes of (divisorBits+1)
 * </ul>
 *
 * <b>Inputs</b>: dividendBits+divisorBits corresponding to:<ul>
 *  <li>a dividendBits bit string representing an input dividend A</li>
 *  <li>a divisorBits bit string representing an input divisor B</li></ul>
 *  <b>Outputs</b>: 2*dividendBits corresponding to:<ul>
 *  <li>a dividendBits bit string representing the quotient of A/B</li>
 *  <li>a dividendBits bit string representing the remainder from A/B</li></ul>
 */
public class DivisionDivisorMSBOn extends DigitalCircuit {
    private final BitDivision[] bitDivisions;

    private final int dividendBits;

    /**
     * DivisionDivisorMSBOn constructor.
     *
     * @param label The name of the circuits.
     * @param dividendBits The number of bits in the dividend.
     * @param divisorBits The number of bits in the divisor.
     */
    public DivisionDivisorMSBOn(String label, int dividendBits, int divisorBits) {
        super(label, dividendBits+divisorBits, 2*dividendBits);

        this.dividendBits = dividendBits;

        int bitDelta = dividendBits-divisorBits+1;
        bitDivisions = new BitDivision[bitDelta];

        for (int i = 0; i < bitDelta; i++) {
            bitDivisions[i] = new BitDivision(label + " DivBit_"+i, divisorBits+1);
            transistorCount += bitDivisions[i].getTransistorCount();

            bitDivisions[i].assignInput(divisorBits+1, GND);
            if (i==0) {
                bitDivisions[i].assignInput(0, GND);
                for (int j = 0; j < divisorBits; j++) {
                    bitDivisions[i].assignInput(1+j, getDividendBit(j));
                    bitDivisions[i].assignInput(divisorBits+2+j, getDivisorBit(j));
                }
            } else {
                for (int j = 0; j < divisorBits; j++) {
                    bitDivisions[i].assignInput(j, bitDivisions[i-1].getOutput(2+j));
                    bitDivisions[i].assignInput(divisorBits+2+j, getDivisorBit(j));
                }
                bitDivisions[i].assignInput(divisorBits, getDividendBit(i+divisorBits-1));
            }
            bitDivisions[i].assignOutput(0, getInternalOutput(divisorBits+i-1));
        }

        for (int i = 0; i < divisorBits; i++) {
            bitDivisions[bitDelta-1].assignOutput(i+2, getInternalOutput(2*dividendBits-divisorBits+i));
        }
    }

    /**
     * Gets the ith divisor bit.
     * @param i The index of the divisor bit to get.
     * @return The output of the ith divisor bit.
     */
    private CircuitNode getDivisorBit(int i) {
        return getInternalInput(i+dividendBits);
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
        for (BitDivision bitDiv : bitDivisions) {
            bitDiv.evaluate();
        }
    }
}
