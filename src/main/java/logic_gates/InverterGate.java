package logic_gates;

import core_architecture.*;

public class InverterGate extends SisoCircuit {

    private final Pfet pfet;
    private final Nfet nfet;

    public InverterGate(String label) {
        super(label);

        pfet = new Pfet(label + " PMOS circuit Pfet", getOutPortInput(0), getInPortOutput(0));
        nfet = new Nfet(label + " NMOS circuit Nfet", getOutPortInput(0), getInPortOutput(0));

        transistorCount += pfet.getTransistorCount() + nfet.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        pfet.evaluate();
        nfet.evaluate();
    }
}
