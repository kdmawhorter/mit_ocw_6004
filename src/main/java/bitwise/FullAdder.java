package bitwise;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.InverterGate;
import logic_gates.NandGate;

/**
 * A class representing a bit full adder.<br>
 * <br>
 A HalfAdder object consists of:<br>
 * <ul>
 * <li>2-bit Low Bit Xor</li>
 * <li>2-bit High Bit And</li></ul>
 *
 * <b>Inputs</b>: 2 bits corresponding to:<ul>
 * <li>1 input bit representing A</li>
 * <li>1 input bit representing B</li></ul>
 * <b>Outputs</b>: 2 bits corresponding to:<ul>
 * <li>a 2-Bit bit string representing the A+B</li></ul>
 */
public class FullAdder extends DigitalCircuit {

    private final InverterGate invA;
    private final InverterGate invB;
    private final InverterGate invC;

    private NandGate[] highBitCalcNands;
    private NandGate highBitOutputNand;

    private NandGate[] lowBitCalcNands;
    private NandGate lowBitOutputNand;

    /**
     * FullAdder constructor.
     *
     * @param label The name of the circuit.
     */
    public FullAdder(String label) {
        super(label, 3, 2);

        invA = new InverterGate(label + " InverterA");
        invA.assignInput(getInternalInput(0));
        transistorCount += invA.getTransistorCount();

        invB = new InverterGate(label + " InverterB");
        invB.assignInput(getInternalInput(1));
        transistorCount += invB.getTransistorCount();

        invC = new InverterGate(label + " InverterC");
        invC.assignInput(getInternalInput(2));
        transistorCount += invC.getTransistorCount();

        initLowBitOutput();
        initHighBitOutput();
    }

    /**
     * FullAdder constructor.
     * @param label The name of the circuit.
     * @param inputA The first bit/node.
     * @param inputB The second bit/node.
     * @param inputC The third bit/node.
     */
    public FullAdder(String label, CircuitNode inputA, CircuitNode inputB, CircuitNode inputC) {
        this(label);

        assignInput(0, inputA);
        assignInput(1, inputB);
        assignInput(2, inputC);
    }

    private void initHighBitOutput() {
        highBitOutputNand = new NandGate(getLabel() + " HighBitOutputNand", 3);
        transistorCount += highBitOutputNand.getTransistorCount();

        CircuitNode[][] highBitCalcNandSrcArray = {
                {getInternalInput(2), getInternalInput(0)},
                {getInternalInput(0), getInternalInput(1)},
                {getInternalInput(2), getInternalInput(1)}};

        highBitCalcNands = new NandGate[3];
        for (int i = 0; i < 3; i++) {
            highBitCalcNands[i] = new NandGate(getLabel() + " HighBitCalcNand_" + i, 2);
            highBitCalcNands[i].assignInput(0, highBitCalcNandSrcArray[i][0]);
            highBitCalcNands[i].assignInput(1, highBitCalcNandSrcArray[i][1]);
            highBitCalcNands[i].assignOutput(highBitOutputNand.getInput(i));
            transistorCount += highBitCalcNands[i].getTransistorCount();
        }
        highBitOutputNand.assignOutput(getInternalOutput(0));
    }

    private void initLowBitOutput() {
        lowBitCalcNands = new NandGate[4];

        CircuitNode[][] lowBitCalcNandSrcArray = {
                {invA.getOutput(), invB.getOutput(), invC.getOutput()},
                {getInternalInput(0), getInternalInput(1), getInternalInput(2)}};
        int[][] lowBitCalcNandSrcIdxs = { {1, 1, 1}, {1, 0, 0}, {0, 1, 0}, {0, 0, 1} };

        lowBitOutputNand = new NandGate(getLabel() + " LowBitOutputNand", 4);
        transistorCount += lowBitOutputNand.getTransistorCount();
        for (int i = 0; i < 4; i++) {
            lowBitCalcNands[i] = new NandGate(getLabel() + " LowBitCalcNand_" + i, 3);
            lowBitCalcNands[i].assignInput(0, lowBitCalcNandSrcArray[lowBitCalcNandSrcIdxs[i][0]][0] );
            lowBitCalcNands[i].assignInput(1, lowBitCalcNandSrcArray[lowBitCalcNandSrcIdxs[i][1]][1] );
            lowBitCalcNands[i].assignInput(2, lowBitCalcNandSrcArray[lowBitCalcNandSrcIdxs[i][2]][2] );
            lowBitCalcNands[i].assignOutput(lowBitOutputNand.getInput(i));

            transistorCount += lowBitCalcNands[i].getTransistorCount();
        }
        lowBitOutputNand.assignOutput(getInternalOutput(1));
    }

    @Override
    protected void evaluateCircuit() {
        invA.evaluate();
        invB.evaluate();
        invC.evaluate();

        for (NandGate lowBitCalcNand : lowBitCalcNands) {
            lowBitCalcNand.evaluate();
        }
        lowBitOutputNand.evaluate();

        for (NandGate highBitCalcNand : highBitCalcNands) {
            highBitCalcNand.evaluate();
        }
        highBitOutputNand.evaluate();
    }
}
