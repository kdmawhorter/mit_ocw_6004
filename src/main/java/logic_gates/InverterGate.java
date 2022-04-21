package logic_gates;

import core_architecture.*;

public class InverterGate extends SisoCircuit {

    private final Pfet pfet;
    private final Nfet nfet;

    public InverterGate(String label) {
        super(label);

        pfet = new Pfet(label + " PMOS circuit PFET", getInternalOutput(0), getInternalInput(0), VDD);
        nfet = new Nfet(label + " NMOS circuit NFET", getInternalOutput(0), getInternalInput(0), GND);

        transistorCount += pfet.getTransistorCount() + nfet.getTransistorCount();
    }

    @Override
    protected void evaluateCircuit() {
        pfet.evaluate();
        nfet.evaluate();
    }
}
