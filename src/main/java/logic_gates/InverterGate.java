package logic_gates;

import core_architecture.*;
import org.jetbrains.annotations.NotNull;

public class InverterGate extends DigitalCircuit {

    private Pfet[] pfets;
    private Nfet[] nfets;

    public InverterGate() {
        super();
    }

    public InverterGate(String label) {
        this(label, 1);
    }

    public InverterGate(String label, int nBit) {
        super(label, nBit, nBit);

        pfets = new Pfet[getNumInputs()];
        nfets = new Nfet[getNumInputs()];

        for (int i = 0; i < getNumInputs(); i++) {
            pfets[i] = new Pfet("PMOS circuit Pfet", getOutput(i), getPortOutput(i));
            nfets[i] = new Nfet("NMOS circuit Nfet", getOutput(i), getPortOutput(i));
        }
    }

    public InverterGate(String label, int nBit, CircuitNode[] outputs, CircuitNode[] inputs) {
        this(label, nBit);

        assignOutputs(outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        pfets[i].assignOutput(0, output);
        nfets[i].assignOutput(0, output);

        super.assignOutput(i, output);
    }

    @Override
    public void evaluate() {
        super.evaluate();

        for (int i = 0; i < getNumInputs(); i++) {
            pfets[i].evaluate();
            nfets[i].evaluate();
        }
    }


}
