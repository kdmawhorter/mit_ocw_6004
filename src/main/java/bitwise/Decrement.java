package bitwise;

import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import logic_gates.XorGate;

/**
 * A class representing a Decrement operation.<br>
 * <br>
 A Decrement object consists of:<br>
 * <ul>
 * <li>Inverted input ports</li>
 * <li>"Borrow" And gates to determine whether each bit index needs to borrow a bit from the next highest
 * significant bit to complete a decrement operation. B[i] = AND(B[i+1], !A[i])</li>
 * <li>"Output" Xor gates that determine the output bit given whether an input bit is on/off and whether it needs
 * to be borrowed from or not. O[i] = XOR(B[i+1], A[i]) </li> </ul>
 *
 * Underflow occurs when the most significant bit needs to borrow from a higher bit.<br>
 * <br>
 * The decrement begins by borrowing from the least significant bit.<br>
 * <br>
 * <b>Inputs</b>: nBits corresponding to:<ul>
 * <li>an nBit bit string representing an input A to decrement</li></ul>
 * <b>Outputs</b>: nBits+1 corresponding to:<ul>
 * <li>an nBit bit string representing the decremented bit string</li>
 * <li>an underflow bit</li></ul>
 */
public class Decrement extends DigitalCircuit {

    private final AndGate[] borrowAnds;
    private final XorGate[] outputXors;
    private final InverterGate[] invPorts;

    /**
     * Decrement constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in the input.
     */
    public Decrement(String label, int nBits) {
        super(label, nBits, nBits+1);

        borrowAnds = new AndGate[nBits];
        outputXors = new XorGate[nBits];
        invPorts = new InverterGate[nBits];

        for (int i = nBits - 1; i >= 0; i--) {
            invPorts[i] = new InverterGate(label + " InvPort_" + i);
            invPorts[i].assignInput(0, getInternalInput(i));
            transistorCount += invPorts[i].getTransistorCount();

            borrowAnds[i] = new AndGate(label + " BorrowAnd_" + i, 2);
            borrowAnds[i].assignInput(0, invPorts[i].getOutput(0));
            borrowAnds[i].assignInput(1, i<nBits-1 ? borrowAnds[i+1].getOutput(0) : VDD);
            transistorCount += borrowAnds[i].getTransistorCount();

            outputXors[i] = new XorGate(label + " OutputXor_" + i, 2);
            outputXors[i].assignInput(0, getInternalInput(i));
            outputXors[i].assignInput(1, i<nBits-1 ? borrowAnds[i+1].getOutput(0) : VDD);
            outputXors[i].assignOutput(getInternalOutput(i));
            transistorCount += outputXors[i].getTransistorCount();
        }
        borrowAnds[0].assignOutput(getInternalOutput(nBits));
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = getNumInputs() - 1; i >= 0; i--) {
            invPorts[i].evaluate();
            borrowAnds[i].evaluate();
            outputXors[i].evaluate();
        }
    }
}
