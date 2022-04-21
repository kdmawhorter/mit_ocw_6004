package logic_gates;

import core_architecture.*;

/**
 * A class representing the logic gate Nor.
 * <br><br>
 * Given nBits of input, the output is {@link DigitalCircuit#VDD VDD} if all
 * those input bits are {@link DigitalCircuit#GND GND}, else it is GND.
 */
public class NorGate extends SingleOutputCircuit {
    private final Pfet[] pfets;
    private final Nfet[] nfets;

    /**
     * NorGate constructor
     * @param label The name of the circuit.
     * @param nBits The number of inputs.
     */
    public NorGate(String label, int nBits) {
        super(label, nBits);

        CircuitNode[] pullUpNodes = new CircuitNode[nBits];
        pfets = new Pfet[nBits];
        nfets = new Nfet[nBits];

        for (int i = 0; i < nBits; i++) {
            pullUpNodes[i] = (i==nBits-1) ?
                    getInternalOutput() :
                    new CircuitNode(label + " PMOS node " + i + "-" + (i+1));

            pfets[i] = new Pfet(label + " PFET_" + i, pullUpNodes[i], getInternalInput(i),
                    (i-1>=0) ? pullUpNodes[i-1] : VDD);

            nfets[i] = new Nfet(label + " NFET_" + i, getInternalOutput(), getInternalInput(i), GND);

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
