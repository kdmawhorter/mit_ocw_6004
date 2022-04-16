package mit_ocw_6004;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class DigitalCircuit {

    public static final CircuitNode VDD = new PowerSource("Vdd");
    public static final CircuitNode GND = new Ground("GND");

    private final CircuitNode[] inputs;
    private final CircuitNode[] outputs;
    private final String label;

    public DigitalCircuit() {
        inputs = null;
        outputs = null;
        label = "INVALID COMPONENT";    }

    public DigitalCircuit(String label, int numInputs, int numOutputs) {
        this.label = label;
        inputs = new CircuitNode[numInputs];
        outputs = new CircuitNode[numOutputs];

        for (int i = 0; i < numInputs; i++) {
            inputs[i] = DigitalCircuit.GND;
        }

        for (int i = 0; i < numOutputs; i++) {
            outputs[i] = new CircuitNode(label + " Output_" + i);
        }
    }

    // GET/SET INPUTS FUNCTIONALITY

    public void assignInputs(@NotNull Boolean[] newInputs)  {
        assignInputs(Arrays.stream(newInputs)
                        .map(input->input?DigitalCircuit.VDD:DigitalCircuit.GND)
                        .toArray(CircuitNode[]::new));
    }

    public void assignInputs(@NotNull CircuitNode[] newInputs)  {
        if (inputs!= null && newInputs.length == inputs.length) {
            System.arraycopy(newInputs, 0, inputs, 0, newInputs.length);
        }
    }

    public void assignInputOn(int i)  {
        assignInput(i, true);
    }

    public void assignInputOff(int i)  {
        assignInput(i, false);
    }

    private void assignInput(int i, boolean on) {
        if (inputs!=null && i>=0 && i<inputs.length) { inputs[i] = on ? DigitalCircuit.VDD : DigitalCircuit.GND; }
    }

    public void assignInput(int i, CircuitNode input)  {
        if (inputs!=null && i>=0 && i<inputs.length) { inputs[i] = input; }
    }

    public CircuitNode[] getInputs() {
        return inputs;
    }

    public CircuitNode getInput(int i)  {
        if (inputs!=null && i>=0 && i<inputs.length) { return inputs[i]; }
        return null;
    }

    // READ OUTPUTS
    public Boolean[] readOutputs() {
        if (outputs!=null) {
            Boolean[] boolOutputs = new Boolean[outputs.length];

            for (int i = 0; i < boolOutputs.length; i++) {
                if (outputs[i].getStatus().equals(ConnectionType.GROUND)) {
                    boolOutputs[i] = false;
                } else if (outputs[i].getStatus().equals(ConnectionType.POWER)) {
                    boolOutputs[i] = true;
                } else {
                    boolOutputs[i] = null;
                }
            }
            return boolOutputs;
        }
        return null;
    }

    public Boolean readOutput(int i)  {
        if (outputs!=null && i>=0 && i<outputs.length) {
            if (outputs[i].getStatus().equals(ConnectionType.GROUND)) {
                return false;
            } else if (outputs[i].getStatus().equals(ConnectionType.POWER)) {
                return true;
            }
        }
        return null;
    }

    public CircuitNode[] getOutputs() { return outputs; }

    public CircuitNode getOutput(int i)  {
        if (outputs!=null && i>=0 && i<outputs.length) {
            return outputs[i];
        }
        return null;
    }

    public void assignOutputs(@NotNull CircuitNode[] newOutputs) {
        if (outputs!=null) {
            System.arraycopy(newOutputs, 0, outputs, 0, newOutputs.length);
        }
    }

    public void assignOutput(int i, @NotNull CircuitNode output)  {
        if (outputs!=null && i>=0 && i<outputs.length) { outputs[i] = output; }
    }

    // Evaluate circuitry logic

    public abstract void evaluate();

    // Accessors

    public String getLabel() { return label; }
    public int getNumInputs() { return (inputs!=null) ? inputs.length : 0; }
    public int getNumOutputs() { return (outputs!=null) ? outputs.length : 0; }

}
