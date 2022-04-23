package comparison;

import core_architecture.CircuitNode;
import core_architecture.SingleOutputCircuit;

/**
 * An abstract class used to build comparison objects. This class chains together {@link BitLessThan} objects for each
 * input pair of bits from A and B to carry forward whether a bit string is less than or equals. The LT and EQ
 * output can then be used by child classes for any of the comparisons.
 */
public abstract class Comparator extends SingleOutputCircuit {


    private final BitLessThan[] bitLessThans;

    /**
     * LessThan constructor
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    public Comparator(String label, int nBits) {
        super(label, 2*nBits);

        bitLessThans = new BitLessThan[nBits];

        for (int i = 0; i < nBits; i++) {
            bitLessThans[i] = new BitLessThan(label + " BLT_" + i);
            bitLessThans[i].assignInput(0, getInternalInput(i));
            bitLessThans[i].assignInput(1, getInternalInput(i+nBits));
            bitLessThans[i].assignInput(2, (i==0) ? GND : bitLessThans[i-1].getOutput(0));
            bitLessThans[i].assignInput(3, (i==0) ? VDD : bitLessThans[i-1].getOutput(1));
            transistorCount += bitLessThans[i].getTransistorCount();
        }
    }

    /**
     * Gets the output indicating whether A is less than B.
     * @return The output node with A < B status.
     */
    protected CircuitNode getLessThanOutput() {
        return bitLessThans[getNumInputs()/2-1].getOutput(0);
    }

    /**
     * Gets the output indicating whether A is equal to B.
     * @return The output node with A == B status.
     */
    protected CircuitNode getEqualsOutput() {
        return bitLessThans[getNumInputs()/2-1].getOutput(1);
    }

    @Override
    protected void evaluateCircuit() {
        for (BitLessThan blt : bitLessThans) {
            blt.evaluate();
        }
    }
}
