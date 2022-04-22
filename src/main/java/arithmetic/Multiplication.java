package arithmetic;

import bitwise.FullAdder;
import bitwise.HalfAdder;
import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A class representing <a href="https://en.wikipedia.org/wiki/Dadda_multiplier">Dadda bit multiplication</a>.
 * 
 * A Multiplication object consists of:<br>
 * <br>
 * Dadda Tree Generator: <br>
 * Multiply all A[i] by B[j] assign them weight i+j.<ul>
 *     <li>nBit*nBit 2-bit Weighted Input Ands.</li>
 * </ul>
 *
 * <b>Inputs</b>: 2*nBits corresponding to:<ul>
 *  <li>an nBit bit string representing an input A</li>
 *  <li>an nBit bit string representing an input B</li></ul>
 *  <b>Outputs</b>: nBits corresponding to:<ul>
 *  <li>a 2*nBit string representing the multiplication of A and B</li></ul>
 */
public class Multiplication extends DigitalCircuit {

    private AndGate[][] weightedInputAnds;
    private List<Queue<CircuitNode>> weightedBitLists;

    private final List<DigitalCircuit> adderChain;

    private final int nBits;

    public Multiplication(String label, int nBits) {
        super(label, nBits+nBits, nBits+nBits);

        this.nBits = nBits;

        initWeightedInputAnds();

        Queue<Integer> daddaHeights = generateDaddaHeights(nBits);

        adderChain = new ArrayList<>();
        while(!daddaHeights.isEmpty()) {
            performDaddaReduction(daddaHeights.poll());
        }

        for (int i = 0; i < nBits * 2; i++) {
            setInternalOutput(i, weightedBitLists.get(i).poll());
        }

    }

    private void initWeightedInputAnds() {
        weightedBitLists = new ArrayList<>();
        weightedInputAnds = new AndGate[nBits][nBits];

        for (int i = 0; i < 2*nBits; i++) {
            weightedBitLists.add(new LinkedList<>());
        }

        for (int i = 0; i < nBits; i++) {
            for (int j = 0; j < nBits; j++) {
                weightedInputAnds[i][j] = new AndGate(getLabel() + " WeightedInputAnd_" + i + "_" + j, 2);
                weightedInputAnds[i][j].assignInput(0, getInternalInput(i));
                weightedInputAnds[i][j].assignInput(1, getInternalInput(j+nBits));
                transistorCount += weightedInputAnds[i][j].getTransistorCount();

                weightedBitLists.get(i+j+1).add(weightedInputAnds[i][j].getOutput());
            }
        }
    }

    private void performDaddaReduction(int daddaHeight) {
        for (int i = 2*nBits-1; i > 0; i--) {
            Queue<CircuitNode> thisList = weightedBitLists.get(i);
            Queue<CircuitNode> nextList = weightedBitLists.get(i-1);
            while(thisList.size()>daddaHeight) {
                DigitalCircuit adder;
                if (thisList.size() == daddaHeight+1) {
                    adder = new HalfAdder(getLabel() + " HalfAdder", thisList.poll(), thisList.poll());
                } else {
                    adder = new FullAdder(getLabel() + " FullAdder", thisList.poll(), thisList.poll(),
                            thisList.poll());
                }
                thisList.add(adder.getOutput(1));
                nextList.add(adder.getOutput(0));
                adderChain.add(adder);
            }
        }
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = 0; i < nBits; i++) {
            for (int j = 0; j < nBits; j++) {
                weightedInputAnds[i][j].evaluate();
            }
        }
        for (DigitalCircuit adder : adderChain) {
            adder.evaluate();
        }
    }

    public static Queue<Integer> generateDaddaHeights(int nBit) {
        LinkedList<Integer> daddaHeights = new LinkedList<>();
        daddaHeights.add(1);
        for (int i = 2; i < nBit; i = Math.floorDiv(3*i, 2)) {
            daddaHeights.add(0, i);
        }
        return daddaHeights;
    }
}
