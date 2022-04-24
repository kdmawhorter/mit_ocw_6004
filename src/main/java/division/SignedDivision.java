package division;

import arithmetic.Negate;
import core_architecture.DigitalCircuit;
import logic_gates.XorGate;
import multiplexer.Mux;


/**
 * A class representing the signed division operation. This is accomplished by performing division on the positive
 * version of the input dividend and divisor, then negating if needed to produce the negative result.
 * <br>
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 *  <li>an nBit bit string representing an input signed dividend A</li>
 *  <li>an nBit bit string representing an input signed divisor B</li></ul>
 *  <b>Outputs</b>: 2*nBits corresponding to:<ul>
 *  <li>an nBit bit string representing the signed quotient of A/B</li>
 *  <li>an nBit bit string representing the signed remainder from A/B</li></ul>
 */
public class SignedDivision extends DigitalCircuit {

    private final Division div;

    private final Negate negativeA;
    private final Negate negativeB;

    private final Mux aSelector;
    private final Mux bSelector;

    private final Negate negativeDiv;
    private final Negate negativeRem;

    private final Mux outDivSelector;
    private final Mux outRemSelector;

    private final XorGate divSignBit;

    /**
     * SignedDivision constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each circuit.
     */
    public SignedDivision(String label, int nBits) {
        super(label, nBits*2, nBits*2);

        divSignBit = new XorGate(label + " DivSignBit", 2);
        divSignBit.assignInput(0, getInternalInput(0));
        divSignBit.assignInput(1, getInternalInput(nBits));

        negativeA = new Negate(label + " NegativeA", nBits);
        negativeB = new Negate(label + " NegativeB", nBits);

        aSelector = new Mux(label + " MuxA", 2, nBits);
        bSelector = new Mux(label + " MuxB", 2, nBits);

        div = new Division(label + " SignLessDiv", nBits);

        negativeDiv = new Negate(label + " NegativeDiv", nBits);
        negativeRem = new Negate(label + " NegativeRem", nBits);

        outDivSelector = new Mux(label + " MuxDiv", 2, nBits);
        outRemSelector = new Mux(label + " MuxRem", 2, nBits);

        aSelector.assignInput(2*nBits, getInternalInput(0));
        bSelector.assignInput(2*nBits, getInternalInput(nBits));
        outDivSelector.assignInput(2*nBits, divSignBit.getOutput());
        outRemSelector.assignInput(2*nBits, getInternalInput(0));
        for (int i = 0; i < nBits; i++) {
            negativeA.assignInput(i, getInternalInput(i));
            aSelector.assignInput(i, getInternalInput(i));
            aSelector.assignInput(i+nBits, negativeA.getOutput(i));

            negativeB.assignInput(i, getInternalInput(nBits+i));
            bSelector.assignInput(i, getInternalInput(i+nBits));
            bSelector.assignInput(i+nBits, negativeB.getOutput(i));

            div.assignInput(i, aSelector.getOutput(i));
            div.assignInput(i+nBits, bSelector.getOutput(i));

            negativeDiv.assignInput(i, div.getOutput(i));
            outDivSelector.assignInput(i, div.getOutput(i));
            outDivSelector.assignInput(i+nBits, negativeDiv.getOutput(i));
            outDivSelector.assignOutput(i, getInternalOutput(i));

            negativeRem.assignInput(i, div.getOutput(i+nBits));
            outRemSelector.assignInput(i, div.getOutput(i+nBits));
            outRemSelector.assignInput(i+nBits, negativeRem.getOutput(i));
            outRemSelector.assignOutput(i, getInternalOutput(i+nBits));
        }

        transistorCount += divSignBit.getTransistorCount() + div.getTransistorCount();
    }

    @Override
    public void evaluateCircuit() {
        negativeA.evaluate();
        negativeB.evaluate();
        aSelector.evaluate();
        bSelector.evaluate();

        divSignBit.evaluate();
        div.evaluate();
        divSignBit.evaluate();

        negativeDiv.evaluate();
        negativeRem.evaluate();

        outDivSelector.evaluate();
        outRemSelector.evaluate();
    }
}
