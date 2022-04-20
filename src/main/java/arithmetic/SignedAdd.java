package arithmetic;

import core_architecture.CircuitNode;
import logic_gates.InverterGate;
import logic_gates.NandGate;

public class SignedAdd extends UnsignedAdd {

    private final NandGate twoPosMakesNeg;
    private final NandGate twoNegMakesPos;
    private final NandGate overflowNand;

    private final InverterGate invertedASign;
    private final InverterGate invertedBSign;
    private final InverterGate invertedOutputSign;


    // Overflow if two negatives add to a positive or two positives add to a negative
    //      (!A_n-1 * !B_n-1 * O_n-1) + (A_n-1 * B_n-1 * !O_n-1)
    //          ==>
    //      NAND(NAND(!A!BO), NAND(AB!0);

    public SignedAdd(String label, int nBits) {
        super(label, nBits);

        invertedASign = new InverterGate(label + " Inverted A Sign Bit");
        invertedASign.assignInput(getInPortOutput(0));
        transistorCount += invertedASign.getTransistorCount();

        invertedBSign = new InverterGate(label + " Inverted B Sign Bit");
        invertedBSign.assignInput(0, getInPortOutput(nBits));
        transistorCount += invertedBSign.getTransistorCount();

        invertedOutputSign = new InverterGate(label + " Inverted Output Sign Bit");
        invertedOutputSign.assignInput(getOutPortInput(0));
        transistorCount += invertedOutputSign.getTransistorCount();

        twoPosMakesNeg = new NandGate(label + " Positive Overflow Nand", 3);
        twoPosMakesNeg.assignInput(0, invertedASign.getOutput(0));
        twoPosMakesNeg.assignInput(1, invertedBSign.getOutput(0));
        twoPosMakesNeg.assignInput(2, getOutPortInput(0));
        transistorCount += twoPosMakesNeg.getTransistorCount();

        twoNegMakesPos = new NandGate(label + " Negative Overflow And", 3);
        twoNegMakesPos.assignInput(0, getInPortOutput(0));
        twoNegMakesPos.assignInput(1, getInPortOutput(nBits));
        twoNegMakesPos.assignInput(2, invertedOutputSign.getOutput(0));
        transistorCount += twoNegMakesPos.getTransistorCount();

        overflowNand = new NandGate(label + "Overflow Or", 2);
        overflowNand.assignInput(0, twoPosMakesNeg.getOutput(0));
        overflowNand.assignInput(1, twoNegMakesPos.getOutput(0));
        overflowNand.assignOutput(getOutPortInput(nBits));
        transistorCount += overflowNand.getTransistorCount();

        carryNands[0].assignOutput(new CircuitNode(
                "Dummy Circuit Node to Prevent Unsigned Carry Overwrite of Signed Overflow Bit"));

    }

    @Override
    protected void evaluateCircuit() {
        super.evaluateCircuit();

        invertedASign.evaluate();
        invertedBSign.evaluate();
        invertedOutputSign.evaluate();

        twoPosMakesNeg.evaluate();
        twoNegMakesPos.evaluate();
        overflowNand.evaluate();
    }
}
