package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.XorGate;

/**
 * A class representing an Increment operation.<br>
 * <br>
 * An Increment object consists of:<br>
 * <ul>
 * <li>"Carry" And gates to determine whether each bit index needs to carry the result to the next highest significant
 * bit. C[i] = AND(C[i+1], A[i]).</li>
 * <li>"Output" Xor gates that determine the output bit given whether an input bit is on/off and whether it has a carry
 * in bit. O[i] = XOR(C[i+1], A[i]) </li> </ul>
 *
 * Overflow occurs when the most significant bit needs to carry to a higher bit.<br>
 * <br>
 * The increment begins by carrying into the least significant bit.<br>
 * <br>
 * <b>Inputs</b>: nBits corresponding to:<ul>
 *  <li>an nBit bit string representing an input A to increment</li></ul>
 *  <b>Outputs</b>: nBits+1 corresponding to:<ul>
 *  <li>an nBit bit string representing the incremented bit string</li>
 *  <li>an overflow bit</li></ul>
 */
public class Increment extends DigitalCircuit {

    final AndGate[] carryAnd;
    final XorGate[] outputXor;

    /**
     * Increment constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in the input.
     */
    public Increment(String label, int nBits) {
        super(label, nBits, nBits+1);

        carryAnd = new AndGate[nBits];
        outputXor = new XorGate[nBits];

        for (int i = nBits - 1; i >= 0; i--) {
            carryAnd[i] = new AndGate(label + " CarryAnd_" + i, 2);
            carryAnd[i].assignInput(0, getInternalInput(i));
            carryAnd[i].assignInput(1, i<nBits-1 ? carryAnd[i+1].getOutput(0) : VDD);
            transistorCount += carryAnd[i].getTransistorCount();

            outputXor[i] = new XorGate(label + " OutputXor_" + i, 2);
            outputXor[i].assignInput(0, getInternalInput(i));
            outputXor[i].assignInput(1, i<nBits-1 ? carryAnd[i+1].getOutput(0) : VDD);
            outputXor[i].assignOutput(getInternalOutput(i));
            transistorCount += outputXor[i].getTransistorCount();
        }

        carryAnd[0].assignOutput(getInternalOutput(nBits));
    }

    /**
     * For each bit of the input starting from the least significant bit (nBit-1):
     * <ul>
     * <li>Evaluates the Carry And gate for that bit.</li>
     * <li>Evaluates the Output Xor Gate for that bit.</li>
     */
    @Override
    protected void evaluateCircuit() {
        for (int i = getNumOutputs() - 2; i >= 0; i--) {
            carryAnd[i].evaluate();
            outputXor[i].evaluate();
        }
    }
}
