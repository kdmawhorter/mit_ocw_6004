package arithmetic;

import core_architecture.CircuitNode;
import logic_gates.InverterGate;
import logic_gates.NandGate;
import org.jetbrains.annotations.NotNull;

public class SignedAdd extends UnsignedAdd {

    private NandGate twoPosMakesNeg;
    private NandGate twoNegMakesPos;
    private NandGate overflowNand;

    private InverterGate invertedASign;
    private InverterGate invertedBSign;
    private InverterGate invertedOutputSign;


    // Overflow if two negatives add to a positive or two positives add to a negative
    //      (!A_n-1 * !B_n-1 * O_n-1) + (A_n-1 * B_n-1 * !O_n-1)
    //          ==>
    //      NAND(NAND(!A!BO), NAND(AB!0);

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

        twoPosMakesNeg = new NandGate(label + " Positive Overflow Nand", 3);
        twoPosMakesNeg.assignInput(0, invertedASign.getOutput(0));
        twoPosMakesNeg.assignInput(1, invertedBSign.getOutput(0));
        twoPosMakesNeg.assignInput(2, getOutput(0));
        transistorCount += twoPosMakesNeg.getTransistorCount();

        twoNegMakesPos = new NandGate(label + " Negative Overflow And", 3);
        twoNegMakesPos.assignInput(0, getPortOutput(0));
        twoNegMakesPos.assignInput(1, getPortOutput(nBit));
        twoNegMakesPos.assignInput(2, invertedOutputSign.getOutput(0));
        transistorCount += twoNegMakesPos.getTransistorCount();

        overflowNand = new NandGate(label + "Overflow Or", 2);
        overflowNand.assignInput(0, twoPosMakesNeg.getOutput(0));
        overflowNand.assignInput(1, twoNegMakesPos.getOutput(0));
        transistorCount += overflowNand.getTransistorCount();

        assignOutput(nBit, overflowNand.getOutput(0));

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
        if (overflowNand!=null && i==getNumOutputs()-1) {
            overflowNand.assignOutput(0, output);
            carryNands[0].assignOutput(0, new CircuitNode("Unused Carry Node"));
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
        overflowNand.evaluate();
    }
}
