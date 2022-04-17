package core_architecture;

import org.jetbrains.annotations.NotNull;

public abstract class DigitalCircuit {

    public static final CircuitNode VDD = new PowerSource("Vdd");
    public static final CircuitNode GND = new Ground("GND");

    private final CircuitNode[] outputs;

    private final Port[] ports;

    private final String label;

    protected int transistorCount;

    public DigitalCircuit() {
        outputs = null;
        ports = null;
        label = "INVALID COMPONENT";
    }

    public DigitalCircuit(String label, int numInputs, int numOutputs) {
        this.label = label;

        outputs = new CircuitNode[Math.max(numOutputs, 1)];
        ports = new Port[numInputs];

        for (int i = 0; i < getNumInputs(); i++) {
            ports[i] = new Port(label + " Port_" + i);
            ports[i].setInput(DigitalCircuit.GND);
        }

        for (int i = 0; i < getNumOutputs(); i++) {
            outputs[i] = new CircuitNode(label + " Output_" + i);
        }

        transistorCount = 0;
    }

    // ASSIGN/GET INPUTS FUNCTIONALITY

    public void assignInputs(@NotNull CircuitNode[] newInputs)  {
        for (int i = 0; i < newInputs.length; i++) {
            assignInput(i, newInputs[i]);
        }
    }

    public void assignInputOn(int i)  { assignInput(i, DigitalCircuit.VDD); }

    public void assignInputOff(int i)  { assignInput(i, DigitalCircuit.GND); }

    public void assignInput(int i, CircuitNode input)  {
        if (ports!=null && i>=0 && i<ports.length) {
            ports[i].setInput(input);
        }
    }

    public CircuitNode[] getInputs() {
        CircuitNode[] returnInputs = new CircuitNode[getNumInputs()];
        for (int i = 0; i < getNumInputs(); i++) {
            returnInputs[i] = ports[i].getInput();
        }
        return returnInputs;
    }

    public CircuitNode getInput(int i)  {
        if (ports!=null && i>=0 && i<ports.length) { return ports[i].getInput(); }
        return null;
    }

    // ASSIGN/GET OUTPUTS

    public void assignOutputs(@NotNull CircuitNode[] newOutputs) {
        for (int i = 0; i < newOutputs.length; i++) {
            assignOutput(i, newOutputs[i]);
        }
    }

    public void assignOutput(int i, @NotNull CircuitNode output)  {
        if (outputs!=null && i>=0 && i<outputs.length) { outputs[i] = output; }
    }

    public CircuitNode[] getOutputs() { return outputs; }

    public CircuitNode getOutput(int i)  {
        if (outputs!=null && i>=0 && i<outputs.length) {
            return outputs[i];
        }
        return null;
    }

    // READ INPUTS/OUTPUTS

    public Boolean[] readInputs() {
        return readIo(getInputs());
    }

    public Boolean[] readOutputs() {
        return readIo(getOutputs());
    }

    private Boolean[] readIo(CircuitNode[] io) {
        if (io!=null) {
            Boolean[] boolIo = new Boolean[io.length];

            for (int i = 0; i < boolIo.length; i++) {
                if (io[i].getStatus().equals(ConnectionType.GROUND)) {
                    boolIo[i] = false;
                } else if (io[i].getStatus().equals(ConnectionType.POWER)) {
                    boolIo[i] = true;
                } else {
                    boolIo[i] = null;
                }
            }
            return boolIo;
        }
        return null;
    }

    public Boolean readInput(int i) {
        return readIo(i, getInputs());
    }

    public Boolean readOutput(int i) {
        return readIo(i, getOutputs());
    }

    private Boolean readIo(int i, CircuitNode[] io) {
        if (outputs!=null && i>=0 && i<outputs.length) {
            if (outputs[i].getStatus().equals(ConnectionType.GROUND)) {
                return false;
            } else if (outputs[i].getStatus().equals(ConnectionType.POWER)) {
                return true;
            }
        }
        return null;
    }


    // Evaluate circuitry logic

    public void evaluate() {
        for (int i = 0; i < getNumInputs(); i++) {
            ports[i].readPort();
        }
    }

    // Accessors

    public String getLabel() { return label; }

    public int getNumInputs() { return (ports!=null) ? ports.length : 0; }
    public int getNumOutputs() { return (outputs!=null) ? outputs.length : 0; }

    public CircuitNode getPortOutput(int i) { return ports[i].getOutput(); }
    public CircuitNode[] getPortOutputs() {
        CircuitNode[] returnOutputs = new CircuitNode[getNumInputs()];
        for (int i = 0; i < getNumInputs(); i++) {
            returnOutputs[i] = getPortOutput(i);
        }
        return returnOutputs;
    }

    public int getTransistorCount() {
        return transistorCount;
    }
}
