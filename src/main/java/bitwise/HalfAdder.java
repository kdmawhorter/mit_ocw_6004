package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.XorGate;

/**
 * A class representing a bit half adder.<br>
 * <br>
 A HalfAdder object consists of:<br>
 * <ul>
 * <li>2-bit Low Bit Xor</li>
 * <li>2-bit High Bit And</li></ul>
 *
 * <b>Inputs</b>: 2 bits corresponding to:<ul>
 * <li>1 input bit representing A</li>
 * <li>1 input bit representing B</li></ul>
 * <b>Outputs</b>: 2 bits corresponding to:<ul>
 * <li>a 2-Bit bit string representing the A+B</li></ul>
 */
public class HalfAdder extends DigitalCircuit {

    private final XorGate lowBitGate;
    private final AndGate highBitGate;

    /**
     * HalfAdder constructor.
     *
     * @param label The name of the circuit.
     */
    public HalfAdder(String label) {
        super(label, 2, 2);

        lowBitGate = new XorGate(label + " LowBitGate", 2);
        lowBitGate.assignInput(0, getInternalInput(0));
        lowBitGate.assignInput(1, getInternalInput(1));
        lowBitGate.assignOutput(getInternalOutput(1));
        transistorCount += lowBitGate.getTransistorCount();

        highBitGate = new AndGate(label + " HighBitGate", 2);
        highBitGate.assignInput(0, getInternalInput(0));
        highBitGate.assignInput(1, getInternalInput(1));
        highBitGate.assignOutput(getInternalOutput(0));
        transistorCount += highBitGate.getTransistorCount();
    }

    /**
     * HalfAdder constructor.
     *
     * @param label The name of the circuit.
     * @param inputA The first bit/node.
     * @param inputB The second bit/node.
     */
    public HalfAdder(String label, CircuitNode inputA, CircuitNode inputB) {
        this(label);

        assignInput(0, inputA);
        assignInput(1, inputB);
    }

    @Override
    protected void evaluateCircuit() {
        lowBitGate.evaluate();
        highBitGate.evaluate();
    }

}
