package division;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import logic_gates.OrGate;

/**
 * A class representing the division operation. This is accomplished by determining the quotient and remainder for
 * each sub bit string of the divisor as if it was the substring with the most significant on bit as its MSB. The
 * circuit then masks out all circuit paths that do not have the highest on bit, allowing only the correct quotient and
 * remainder to populate the output.
 * <br>
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 *  <li>an nBit bit string representing an input dividend A</li>
 *  <li>an nBit bit string representing an input divisor B</li></ul>
 *  <b>Outputs</b>: 2*nBits corresponding to:<ul>
 *  <li>an nBit bit string representing the quotient of A/B</li>
 *  <li>an nBit bit string representing the remainder from A/B</li></ul>
 */
public class Division extends DigitalCircuit {

    private final DivisionDivisorMSBOn[] calcDivs;

    private final InverterGate[] invDivisors;
    private final AndGate[] isMSBOn;

    private final AndGate[][] calcDivAnds;
    private final AndGate[][] calcRemAnds;

    private final OrGate[] outputDivOrs;
    private final OrGate[] outputRemOrs;

    private final int nBits;

    /**
     * Division constructor.
     *
     * @param label The name of the circuit.
     * @param nBits The number of bits in each input.
     */
    Division(String label, int nBits) {
        super(label, 2*nBits, 2*nBits);

        this.nBits = nBits;

        calcDivs = new DivisionDivisorMSBOn[nBits];

        invDivisors = new InverterGate[nBits];
        isMSBOn = new AndGate[nBits];

        calcDivAnds = new AndGate[nBits][nBits];
        calcRemAnds = new AndGate[nBits][nBits];

        outputDivOrs = new OrGate[nBits];
        outputRemOrs = new OrGate[nBits];


        for (int i = 0; i < nBits; i++) {
            outputDivOrs[i] = new OrGate(label + " OutputDivOrs_" + i, nBits);
            outputRemOrs[i] = new OrGate(label + " OutputRemOrs_" + i, nBits);

            transistorCount += outputDivOrs[i].getTransistorCount() + outputRemOrs[i].getTransistorCount();
        }

        for (int i = 0; i < nBits; i++) {
            int divisorBits = nBits-i;

            generateCalcDiv(i, divisorBits);

            invDivisors[i] = new InverterGate(label + " InvDivisorBit_" + i);
            invDivisors[i].assignInput(getDivisorBit(i));

            isMSBOn[i] = new AndGate(label + " IsMSBOn_" + i, i+1);
            isMSBOn[i].assignInput(0, getDivisorBit(i));
            for (int j = 0; j < i; j++) {
                isMSBOn[i].assignInput(1+j, invDivisors[j].getOutput());
            }

            transistorCount += invDivisors[i].getTransistorCount() + isMSBOn[i].getTransistorCount();

            for (int j = 0; j < nBits; j++) {
                calcDivAnds[i][j] = new AndGate(label + " CalcDivNand_" + i + "_" + j, 2);
                calcDivAnds[i][j].assignInput(0, calcDivs[i].getOutput(j));
                calcDivAnds[i][j].assignInput(1, isMSBOn[i].getOutput());

                calcRemAnds[i][j] = new AndGate(label + " CalcRemNands" + i + "_" + j, 2);
                calcRemAnds[i][j].assignInput(0, calcDivs[i].getOutput(nBits+j));
                calcRemAnds[i][j].assignInput(1, isMSBOn[i].getOutput());

                transistorCount += calcDivAnds[i][j].getTransistorCount() + calcRemAnds[i][j].getTransistorCount();

                outputDivOrs[j].assignInput(i, calcDivAnds[i][j].getOutput());
                outputRemOrs[j].assignInput(i, calcRemAnds[i][j].getOutput());
            }
        }

        for (int i = 0; i < nBits; i++) {
            outputDivOrs[i].assignOutput(getInternalOutput(i));
            outputRemOrs[i].assignOutput(getInternalOutput(nBits+i));
        }


    }

    /**
     * Creates the ith division calculation helper with the last divisorBits of the divisor.
     * @param i The index of the division calculation helper to create.
     * @param divisorBits The number of bits of the divisor
     */
    private void generateCalcDiv(int i, int divisorBits) {
        calcDivs[i] = new DivisionDivisorMSBOn(getLabel() + " CalcDivMSB_" + i, nBits, divisorBits);

        transistorCount += calcDivs[i].getTransistorCount();

        for (int j = 0; j < nBits; j++) {
            calcDivs[i].assignInput(j, getInternalInput(j));
        }
        for (int k = 0; k < divisorBits; k++) {
            calcDivs[i].assignInput(k+nBits, getDivisorBit(k+nBits-divisorBits));
        }
    }

    /**
     * Gets the ith divisor bit.
     * @param i The index of the divisor bit to get.
     * @return The output of the ith divisor bit.
     */
    private CircuitNode getDivisorBit(int i) {
        return getInternalInput(nBits+i);
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = 0; i < nBits; i++) {
            calcDivs[i].evaluate();

            invDivisors[i].evaluate();
            isMSBOn[i].evaluate();

            for (int j = 0; j < nBits; j++) {
                calcDivAnds[i][j].evaluate();
                calcRemAnds[i][j].evaluate();
            }
        }
        for (int i = 0; i < nBits; i++) {
            outputDivOrs[i].evaluate();
            outputRemOrs[i].evaluate();
        }
    }

}
