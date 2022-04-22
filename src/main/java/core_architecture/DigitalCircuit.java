package core_architecture;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * A class representing the fundamental digital circuit abstraction for the MIT
 * OCW 6.004 computation machine. <br><br>
 * All DigitalCircuits have some inputs and some outputs
 * that divide between internal and external connections using input and
 * output ports. All DigitalCircuits will evaluate the internals of the circuit and
 * propagate the input into a driven output.
 */
public abstract class DigitalCircuit {

    /**
     * The {@link PowerSource} to be used for all digital circuits. 
     */
    public static final CircuitNode VDD = new PowerSource("Vdd");

    /**
     * The {@link Ground} to be used for all digital circuits. 
     */
    public static final CircuitNode GND = new Ground("GND");

    private final Port[] inPorts;
    private final Port[] outPorts;

    /**
     * The name of the DigitalCircuit.
     */
    @Getter
    private final String label;

    /**
     * The total internal transistor count of the circuit.
     */
    @Getter
    protected int transistorCount;

    /**
     * DigitalCircuit constructor.
     * 
     * @param label The name of the circuit.
     * @param numInputs The number of inputs to the circuit.
     * @param numOutputs The number of outputs from the circuit.
     */
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

    /**
     * Assigns the contents of a {@link CircuitNode}[] array to the inputs of the
     * circuit.
     * 
     * @param newInputs The new array of input connections.
     */
    public void assignInputs(@NotNull CircuitNode[] newInputs)  {
        for (int i = 0; i < newInputs.length; i++) {
            assignInput(i, newInputs[i]);
        }
    }

    /**
     * Assign a specific input to power.
     * @param i The index of the input to set to power.
     */
    public void assignInputOn(int i)  { assignInput(i, DigitalCircuit.VDD); }

    /**
     * Assign a specific input to ground.
     * @param i The index of the input to set to ground.
     */
    public void assignInputOff(int i)  { assignInput(i, DigitalCircuit.GND); }

    /**
     * Assign a specific input to some node.
     * @param i The index of the input to set to the input node.
     * @param input The node to serve as the new ith input.         
     */
    public void assignInput(int i, CircuitNode input)  {
        if (inPorts!=null && i>=0 && i<inPorts.length) {
            inPorts[i].setInput(input);
        }
    }

    /**
     * Gets the input nodes to the circuit.
     * @return The nodes connected to the input terminals of the DigitalCircuit's input {@link Port Ports} as an array.
     */
    public CircuitNode[] getInputs() {
        CircuitNode[] returnInputs = new CircuitNode[getNumInputs()];
        for (int i = 0; i < getNumInputs(); i++) {
            returnInputs[i] = inPorts[i].getInput();
        }
        return returnInputs;
    }

    /**
     * Gets a specified input node to the digital circuit.
     * @param i The input node index.
     * @return The ith input node.
     */
    public CircuitNode getInput(int i)  {
        return (inPorts!=null && i>=0 && i<inPorts.length) ? inPorts[i].getInput() : null;
    }

    // ASSIGN/GET OUTPUTS

    /**
     * Assigns the contents of a {@link CircuitNode}[] array to the outputs of the
     * circuit.
     * 
     * @param newOutputs The new array of output connections.
     */
    public void assignOutputs(@NotNull CircuitNode[] newOutputs) {
        for (int i = 0; i < newOutputs.length; i++) {
            assignOutput(i, newOutputs[i]);
        }
    }

    /**
     * Assign a specific output to some node.
     * @param i The index of the output to set to the output node.
     * @param output The node to serve as the new ith output.      
     */
    public void assignOutput(int i, @NotNull CircuitNode output)  {
        if (outPorts!=null && i>=0 && i<outPorts.length) {
            outPorts[i].setOutput(output);
        }
    }

    /**
     * Gets the output nodes to the circuit.
     * @return The nodes connected to the output terminals of the DigitalCircuit's output {@link Port Ports} as an 
     * array.
     */
    public CircuitNode[] getOutputs() {
        CircuitNode[] returnOutputs = new CircuitNode[getNumOutputs()];
        for (int i = 0; i < getNumOutputs(); i++) {
            returnOutputs[i] = getOutput(i);
        }
        return returnOutputs;
    }

    /**
     * Gets a specified output node to the digital circuit.
     * @param i The output node index.
     * @return The ith output node.
     */
    public CircuitNode getOutput(int i)  {
        return (outPorts!=null && i>=0 && i<outPorts.length) ? outPorts[i].getOutput() : null;
    }

    // READ INPUTS/OUTPUTS

    /**
     * Reads the statuses of the inputs to the circuit.
     * <br><br>
     * True represents a powered connection.<br>
     * False represents a ground connection.<br>
     * null represents a floating connection or a node connected to both power and ground. 
     * @return The status of the inputs, expressed as Booleans.
     */
    public Boolean[] readInputs() {
        return readIo(getInputs());
    }

    /**
     * Reads the statuses of the outputs of the circuit.
     * <br><br>
     * <b>True</b> represents a powered connection.<br>
     * <b>False</b> represents a ground connection.<br>
     * <b>null</b> represents a floating connection or a node connected to both power and ground. 
     * @return The status of the outputs, expressed as Booleans.
     */
    public Boolean[] readOutputs() {
        return readIo(getOutputs());
    }

    /**
     * Reads the statuses of some array of nodes.
     * <br><br>
     * True represents a powered connection.<br>
     * False represents a ground connection.<br>
     * null represents a floating connection or a node connected to both power and ground. 
     * @param io The nodes to read.
     * @return The status of the supplied nodes, expressed as Booleans.
     */
    private Boolean[] readIo(CircuitNode[] io) {
        if (io!=null) {
            Boolean[] boolIo = new Boolean[io.length];

            for (int i = 0; i < boolIo.length; i++) {
                boolIo[i] = readIo(i, io);
            }
            
            return boolIo;
        }
        return null;
    }

    /**
     * Reads the status of some specific input to the circuit.
     * <br><br>
     * True represents a powered connection.<br>
     * False represents a ground connection.<br>
     * null represents a floating connection or a node connected to both power and ground.
     * @param i The input node index to read.
     * @return The status of the ith input, expressed as a Boolean.
     */
    public Boolean readInput(int i) {
        return readIo(i, getInputs());
    }

    /**
     * Reads the status of some specific output to the circuit.
     * <br><br>
     * True represents a powered connection.<br>
     * False represents a ground connection.<br>
     * null represents a floating connection or a node connected to both power and ground. 
     * @param i The output node index to read.
     * @return The status of the ith output, expressed as a Boolean.
     */
    public Boolean readOutput(int i) {
        return readIo(i, getOutputs());
    }

    /**
     * Reads the status of some specific node within a supplied array of nodes.
     * <br><br>
     * True represents a powered connection.<br>
     * False represents a ground connection.<br>
     * null represents a floating connection or a node connected to both power and ground. 
     * @param i The node index to read.
     * @param io The supplied nodes.
     * @return The status of the ith input, expressed as a Boolean.
     */
    private Boolean readIo(int i, CircuitNode[] io) {
        if (io!=null && i>=0 && i<io.length) {
            if (io[i].getStatus().equals(ConnectionType.GROUND)) {
                return false;
            } else if (io[i].getStatus().equals(ConnectionType.POWER)) {
                return true;
            }
        }
        return null;
    }


    // Evaluate circuitry logic

    /**
     * Evaluates the internal circuitry of the circuit.<br>
     * First the input ports propagate the input status of the port to the output of the port for consumption by the
     * internals of the circuit.<br>
     * Secondly, the circuit evaluates its internal circuitry.<br>
     * Lastly, the output ports propagate the input status of the port to the output of the port.
     */
    public void evaluate() {
        for (Port inPort : inPorts) {
            inPort.readPort();
        }

        evaluateCircuit();

        for (Port outPort : outPorts) {
            outPort.readPort();
        }
    }

    /**
     * The evaluation of the internal components of the circuit. 
     */
    protected abstract void evaluateCircuit();

    // Accessors

    /**
     * Gets the number of inputs to the circuit.
     * @return The number of inputs to the circuit.
     */
    public int getNumInputs() { return (inPorts!=null) ? inPorts.length : 0; }

    /**
     * Gets the number of outputs to the circuit.
     * @return The number of outputs to the circuit.
     */
    public int getNumOutputs() { return (outPorts!=null) ? outPorts.length : 0; }

    /**
     * Gets a specific output of the input ports of the circuit.
     * @param i The index of the input port
     * @return The output of the ith input port
     */
    protected CircuitNode getInternalInput(int i) { return inPorts[i].getOutput(); }

    /**
     * Gets the outputs of the input ports.
     * @return The outputs of the input ports.
     */
    protected CircuitNode[] getInternalInputs() {
        CircuitNode[] returnOutputs = new CircuitNode[getNumInputs()];
        for (int i = 0; i < getNumInputs(); i++) {
            returnOutputs[i] = getInternalInput(i);
        }
        return returnOutputs;
    }

    /**
     * Gets a specific input to the output ports of the circuit.
     * @param i The index of the output port
     * @return The input of the ith output port
     */
    protected CircuitNode getInternalOutput(int i) { return outPorts[i]. getInput(); }

    /**
     * Gets the inputs of the output ports.
     * @return The inputs of the output ports.
     */
    protected CircuitNode[] getInternalOutputs() {
        CircuitNode[] returnInputs = new CircuitNode[getNumOutputs()];
        for (int i = 0; i < getNumOutputs(); i++) {
            returnInputs[i] = getInternalOutput(i);
        }
        return returnInputs;
    }

    /**
     * Sets the input node of the output port
     * @param i The output port to set.
     * @param output The node to be the new output.
     */
    protected void setInternalOutput(int i, CircuitNode output) {
        if (i>=0 && i<getNumOutputs()) {
            outPorts[i].setInput(output);
        }
    }
}
