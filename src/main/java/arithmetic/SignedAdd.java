package arithmetic;

import core_architecture.CircuitNode;
import logic_gates.AndGate;
import logic_gates.InverterGate;
import logic_gates.OrGate;
import org.jetbrains.annotations.NotNull;

public class SignedAdd extends UnsignedAdd {

    private AndGate twoPosMakesNeg;
    private AndGate twoNegMakesPos;
    private OrGate overflowOr;

    private InverterGate invertedASign;
    private InverterGate invertedBSign;
    private InverterGate invertedOutputSign;


    // Overflow if two negatives add to a positive or two positives add to a negative
    //      (!A_n-1 * !B_n-1 * O_n-1) + (A_n-1 * B_n-1 * !O_n-1)

    public SignedAdd() {
        super();
    }

    public SignedAdd(String label, int nBit) {
        super(label, nBit);

        invertedASign = new InverterGate(label + " Inverted A Sign Bit");
        invertedASign.assignInput(0, getPortOutput(0));
        transistorCount += invertedASign.getTransistorCount();

        invertedBSign = new InverterGate(label + " Inverted B Sign Bit");
        invertedBSign.assignInput(0, getPortOutput(nBit));
        transistorCount += invertedBSign.getTransistorCount();

        invertedOutputSign = new InverterGate(label + " Inverted Output Sign Bit");
        invertedOutputSign.assignInput(0,getOutput(0));
        transistorCount += invertedOutputSign.getTransistorCount();

        twoPosMakesNeg = new AndGate(label + " Positive Overflow And", 3);
        twoPosMakesNeg.assignInput(0, invertedASign.getOutput(0));
        twoPosMakesNeg.assignInput(1, invertedBSign.getOutput(0));
        twoPosMakesNeg.assignInput(2, getOutput(0));
        transistorCount += twoPosMakesNeg.getTransistorCount();

        twoNegMakesPos = new AndGate(label + " Negative Overflow And", 3);
        twoNegMakesPos.assignInput(0, getPortOutput(0));
        twoNegMakesPos.assignInput(1, getPortOutput(nBit));
        twoNegMakesPos.assignInput(2, invertedOutputSign.getOutput(0));
        transistorCount += twoNegMakesPos.getTransistorCount();

        overflowOr = new OrGate(label + "Overflow Or", 2);
        overflowOr.assignInput(0, twoPosMakesNeg.getOutput(0));
        overflowOr.assignInput(1, twoNegMakesPos.getOutput(0));
        transistorCount += overflowOr.getTransistorCount();

        assignOutput(nBit, overflowOr.getOutput(0));

    }

    public SignedAdd(String label, int nBit, @NotNull CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public SignedAdd(String label, int nBit, @NotNull CircuitNode[] outputs, @NotNull CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        super.assignOutput(i, output);
        if (overflowOr!=null && i==getNumOutputs()-1) {
            overflowOr.assignOutput(0, output);
            carryOrs[0].assignOutput(0, new CircuitNode("Unused Carry Node"));
        }
    }

    @Override
    public void evaluate() {
        super.evaluate();

        invertedASign.evaluate();
        invertedBSign.evaluate();
        invertedOutputSign.evaluate();

        twoPosMakesNeg.evaluate();
        twoNegMakesPos.evaluate();
        overflowOr.evaluate();
    }
}
