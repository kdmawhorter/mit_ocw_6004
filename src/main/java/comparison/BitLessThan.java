package comparison;

import core_architecture.DigitalCircuit;
import logic_gates.InverterGate;
import logic_gates.NandGate;

/**
 * A class representing a helper circuit for performing less than (or equal) operations on bit strings. It determines
 * whether an input bit is part of a bit string A that is less than a bit string B.
 * <br>
 * A BitLessThan object consists of:<br>
 * <br>
 * Inverter logic:<ul>
 * <li>3 InverterGates which invert the three inputs (A, B, Li)</li>
 * </ul>
 * Upstream Matches logic:<br>
 * Including this bit, all bits match upstream if all bits match upstream and these bits match<br>
 * Uo = UiAB + Ui!A!B = Nand(Nand(Ui,A,B), Nand(Ui,!A,!B)):<ul>
 * <li>2 3-bit Nands representing the inner Nands above</li>
 * <li>1 2-bit Nands representing the outer Nand above</li></ul>
 * Less Than Logic:<br>
 * This bit string is less if upstream the bitstring was less, or if the upstream bits matched and this A bit is less than B.<br>
 * Lo = Li + Ui!AB = Nand(!Li, Nand(Ui,!A, B))<ul>
 * <li>1 3-bit Nand representing the inner Nand above</li>
 * <li>1 2-bit Nand representing the outer Nand above</li>
 * </ul>
 *
 * <b>Inputs</b>: 4 bits corresponding to:<ul>
 * <li>1 bit representing the input A</li>
 * <li>1 bit representing the input B</li>
 * <li>1 bit representing the input Bit String Less Than (Li)</li>
 * <li>1 bit representing the input Upstream Matches (Ui)</li></ul>
 * <b>Outputs</b>: 2 bits corresponding to:<ul>
 * <li>1 bit representing if the bit string A (including this bit) is less than B (Lo)</li>
 * <li>1 bit representing if the bits upstream (including this bit) all match (Uo)</li>
 */
public class BitLessThan extends DigitalCircuit {

    private NandGate aAndB;
    private NandGate naAndNb;
    private NandGate upstreamMatchesOutputNand;

    private NandGate naAndB;
    private NandGate lessThanOutputNand;

    private InverterGate invInputA;
    private InverterGate invInputB;
    private InverterGate invInputLessThan;


    /**
     * BitLessThanEqual constructor
     *
     * @param label The name of the circuit.
     */
    public BitLessThan(String label) {
        super(label, 4, 2);

        initInverters();
        initLessThanLogic();
        initUpstreamMatchesLogic();
    }
    
    private void initInverters() {
        invInputA = new InverterGate(getLabel() + " InvertedAInput");
        invInputA.assignInput(getInternalInput(0));

        invInputB = new InverterGate(getLabel() + " InvertedAInput");
        invInputB.assignInput(getInternalInput(1));

        invInputLessThan = new InverterGate(getLabel() + " InvertedAInput");
        invInputLessThan.assignInput(getInternalInput(2));

        transistorCount += invInputA.getTransistorCount() + invInputB.getTransistorCount() +
                invInputLessThan.getTransistorCount();
    }

    private void initLessThanLogic() {
        naAndB = new NandGate(getLabel() + " !AB_Nand", 3);
        naAndB.assignInput(0, invInputA.getOutput());
        naAndB.assignInput(1, getInternalInput(1));
        naAndB.assignInput(2, getInternalInput(3));

        lessThanOutputNand = new NandGate(getLabel() + " LT_OutputNand", 2);
        lessThanOutputNand.assignInput(0, invInputLessThan.getOutput());
        lessThanOutputNand.assignInput(1, naAndB.getOutput());

        lessThanOutputNand.assignOutput(getInternalOutput(0));

        transistorCount += lessThanOutputNand.getTransistorCount() + naAndB.getTransistorCount();
    }

    private void initUpstreamMatchesLogic() {
        naAndNb = new NandGate(getLabel() + " !A!B_Nand", 3);
        naAndNb.assignInput(0, invInputA.getOutput());
        naAndNb.assignInput(1, invInputB.getOutput());
        naAndNb.assignInput(2, getInternalInput(3));

        aAndB = new NandGate(getLabel() + " AB_Nand", 3);
        aAndB.assignInput(0, getInternalInput(0));
        aAndB.assignInput(1, getInternalInput(1));
        aAndB.assignInput(2, getInternalInput(3));


        upstreamMatchesOutputNand = new NandGate(getLabel() + " UpstreamMatchesAnd", 2);
        upstreamMatchesOutputNand.assignInput(0, naAndNb.getOutput());
        upstreamMatchesOutputNand.assignInput(1, aAndB.getOutput());

        upstreamMatchesOutputNand.assignOutput(getInternalOutput(1));

        transistorCount += naAndNb.getTransistorCount() + aAndB.getTransistorCount() +
                upstreamMatchesOutputNand.getTransistorCount();
    }

    @Override
    public void evaluateCircuit() {
        invInputA.evaluate();
        invInputB.evaluate();
        invInputLessThan.evaluate();

        naAndB.evaluate();
        naAndNb.evaluate();
        aAndB.evaluate();

        lessThanOutputNand.evaluate();
        upstreamMatchesOutputNand.evaluate();
    }
}
