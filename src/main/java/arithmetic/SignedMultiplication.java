package arithmetic;

import core_architecture.DigitalCircuit;
import logic_gates.XorGate;
import multiplexer.Mux;


/**
 * A class representing the signed multiplication operation. This is accomplished by performing multiplication on the
 * positive version of the inputs A and B, then negating if needed to produce the negative result.
 * <br>
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 *  <li>an nBit bit string representing a signed input A</li>
 *  <li>an nBit bit string representing a signed input B</li></ul>
 *  <b>Outputs</b>: 2*nBits corresponding to:<ul>
 *  <li>a 2*nBit bit string representing the signed product of A and B</li>
 */
public class SignedMultiplication extends DigitalCircuit {

    private final Multiplication mult;

    private final Negate negativeA;
    private final Negate negativeB;

    private final Mux aSelector;
    private final Mux bSelector;

    private final Negate negativeMult;

    private final Mux outSelector;

    private final XorGate multSignBit;

    /**
     * SignedMultiplication constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each circuit.
     */
    public SignedMultiplication(String label, int nBits) {
        super(label, nBits*2, nBits*2);

        multSignBit = new XorGate(label + " MultSignBit", 2);
        multSignBit.assignInput(0, getInternalInput(0));
        multSignBit.assignInput(1, getInternalInput(nBits));

        negativeA = new Negate(label + " NegativeA", nBits);
        negativeB = new Negate(label + " NegativeB", nBits);

        aSelector = new Mux(label + " MuxA", 2, nBits);
        bSelector = new Mux(label + " MuxB", 2, nBits);

        mult = new Multiplication(label + " SignLessMult", nBits);

        negativeMult = new Negate(label + " NegativeMult", nBits*2);

        outSelector = new Mux(label + " OutSelector", 2, nBits*2);

        transistorCount += multSignBit.getTransistorCount() + mult.getTransistorCount() +
                negativeA.getTransistorCount() + aSelector.getTransistorCount() +
                negativeB.getTransistorCount() + bSelector.getTransistorCount() +
                negativeMult.getTransistorCount() + outSelector.getTransistorCount();

        aSelector.assignInput(2*nBits, getInternalInput(0));
        bSelector.assignInput(2*nBits, getInternalInput(nBits));
        outSelector.assignInput(4*nBits, multSignBit.getOutput());
        for (int i = 0; i < nBits; i++) {
            negativeA.assignInput(i, getInternalInput(i));
            aSelector.assignInput(i, getInternalInput(i));
            aSelector.assignInput(i + nBits, negativeA.getOutput(i));

            negativeB.assignInput(i, getInternalInput(nBits + i));
            bSelector.assignInput(i, getInternalInput(i + nBits));
            bSelector.assignInput(i + nBits, negativeB.getOutput(i));

            mult.assignInput(i, aSelector.getOutput(i));
            mult.assignInput(i + nBits, bSelector.getOutput(i));
        }
        for (int i = 0; i < nBits*2; i++) {
            negativeMult.assignInput(i, mult.getOutput(i));
            outSelector.assignInput(i, mult.getOutput(i));
            outSelector.assignInput(i+2*nBits, negativeMult.getOutput(i));
            outSelector.assignOutput(i, getInternalOutput(i));
        }
    }

    @Override
    public void evaluateCircuit() {
        multSignBit.evaluate();

        negativeA.evaluate();
        negativeB.evaluate();
        aSelector.evaluate();
        bSelector.evaluate();

        mult.evaluate();
        negativeMult.evaluate();

        outSelector.evaluate();
    }
}
