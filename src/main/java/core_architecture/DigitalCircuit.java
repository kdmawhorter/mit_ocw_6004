package core_architecture;

import org.jetbrains.annotations.NotNull;

public abstract class DigitalCircuit {

    public static final CircuitNode VDD = new PowerSource("Vdd");
    public static final CircuitNode GND = new Ground("GND");

    private final Port[] inPorts;
    private final Port[] outPorts;

    private final String label;

    protected int transistorCount;

    public DigitalCircuit(String label, int numInputs, int numOutputs) {
        this.label = label;

        inPorts = new Port[Math.max(numInputs, 0)];
        outPorts = new Port[Math.max(numOutputs, 0)];

        for (int i = 0; i < getNumInputs(); i++) {
            inPorts[i] = new Port(label + " InPort_" + i);
            inPorts[i].setInput(new CircuitNode(label + " InPort_" + i));
        }

        for (int i = 0; i < getNumOutputs(); i++) {
            outPorts[i] = new Port(label + " OutPort_" + i);
            outPorts[i].setInput(new CircuitNode(label + " OutPort_" + i));
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
        if (inPorts!=null && i>=0 && i<inPorts.length) {
            inPorts[i].setInput(input);
        }
    }

    public CircuitNode[] getInputs() {
        CircuitNode[] returnInputs = new CircuitNode[getNumInputs()];
        for (int i = 0; i < getNumInputs(); i++) {
            returnInputs[i] = inPorts[i].getInput();
        }
        return returnInputs;
    }

    public CircuitNode getInput(int i)  {
        return (inPorts!=null && i>=0 && i<inPorts.length) ? inPorts[i].getInput() : null;
    }

    // ASSIGN/GET OUTPUTS

    public void assignOutputs(@NotNull CircuitNode[] newOutputs) {
        for (int i = 0; i < newOutputs.length; i++) {
            assignOutput(i, newOutputs[i]);
        }
    }

    public void assignOutput(int i, @NotNull CircuitNode output)  {
        if (outPorts!=null && i>=0 && i<outPorts.length) {
            outPorts[i].setOutput(output);
        }
    }

    public CircuitNode[] getOutputs() {
        CircuitNode[] returnOutputs = new CircuitNode[getNumOutputs()];
        for (int i = 0; i < getNumOutputs(); i++) {
            returnOutputs[i] = getOutput(i);
        }
        return returnOutputs;
    }

    public CircuitNode getOutput(int i)  {
        return (outPorts!=null && i>=0 && i<outPorts.length) ? outPorts[i].getOutput() : null;
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
        if (outPorts!=null && i>=0 && i<outPorts.length) {
            if (outPorts[i].getOutput().getStatus().equals(ConnectionType.GROUND)) {
                return false;
            } else if (outPorts[i].getOutput().getStatus().equals(ConnectionType.POWER)) {
                return true;
            }
        }
        return null;
    }


    // Evaluate circuitry logic

    public void evaluate() {
        for (Port inPort : inPorts) {
            inPort.readPort();
        }

        evaluateCircuit();

        for (Port outPort : outPorts) {
            outPort.readPort();
        }
    }

    protected abstract void evaluateCircuit();

    // Accessors

    public String getLabel() { return label; }

    public int getNumInputs() { return (inPorts!=null) ? inPorts.length : 0; }
    public int getNumOutputs() { return (outPorts!=null) ? outPorts.length : 0; }

    public CircuitNode getInPortOutput(int i) { return inPorts[i].getOutput(); }
    public CircuitNode[] getInPortOutputs() {
        CircuitNode[] returnOutputs = new CircuitNode[getNumInputs()];
        for (int i = 0; i < getNumInputs(); i++) {
            returnOutputs[i] = getInPortOutput(i);
        }
        return returnOutputs;
    }

    public CircuitNode getOutPortInput(int i) { return outPorts[i]. getInput(); }
    public CircuitNode[] getOutPortInputs() {
        CircuitNode[] returnInputs = new CircuitNode[getNumOutputs()];
        for (int i = 0; i < getNumOutputs(); i++) {
            returnInputs[i] = getOutPortInput(i);
        }
        return returnInputs;
    }

    public void setOutPortInput(int i, @NotNull CircuitNode input) {
        if (outPorts!=null && i>=0 && i<outPorts.length) {
            outPorts[i].setInput(input);
        }
    }

    public int getTransistorCount() {
        return transistorCount;
    }
}
