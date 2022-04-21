package logic_gates;

import core_architecture.*;

/**
 * A class representing the logic gate Nand.
 * <br><br>
 * Given nBits of input, the output is {@link DigitalCircuit#GND GND} if all
 * those input bits are {@link DigitalCircuit#VDD VDD}, else it is VDD.
 */
public class NandGate extends SingleOutputCircuit {
    private final Pfet[] pfets;
    private final Nfet[] nfets;

    /**
     * NandGate constructor
     * @param label The name of the circuit.
     * @param nBits The number of inputs.
     */
    public NandGate(String label, int nBits) {
        super(label, nBits);

        CircuitNode[] pullDownNodes = new CircuitNode[nBits];
        pfets = new Pfet[nBits];
        nfets = new Nfet[nBits];

        for (int i = 0; i < nBits; i++) {
            pullDownNodes[i] = (i==nBits-1) ?
                    getInternalOutput() :
                    new CircuitNode(label + " NMOS node " + i + "-" + (i+1));

            nfets[i] = new Nfet(label + " NFET_" + i, pullDownNodes[i], getInternalInput(i),
                    (i-1>=0) ? pullDownNodes[i-1] : GND);

            pfets[i] = new Pfet(label + " PFET_" + i, getInternalOutput(), getInternalInput(i), VDD);

            transistorCount += pfets[i].getTransistorCount() + nfets[i].getTransistorCount();
        }
    }

    /**
     * Evaluates the corresponding PFET and NFET for each input.
     */
    @Override
    protected void evaluateCircuit() {
        for (int i = 0; i < getNumInputs(); i++) {
            pfets[i].evaluate();
            nfets[i].evaluate();
        }
    }
}
