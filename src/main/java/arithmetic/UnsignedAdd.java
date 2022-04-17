package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.AndGate;

import logic_gates.OrGate;
import logic_gates.XorGate;
import org.jetbrains.annotations.NotNull;

public class UnsignedAdd extends DigitalCircuit {
    private AndGate[][] carryAnds;
    private OrGate[] carryOrs;

    private AndGate[] outputAnds;
    private XorGate[] outputXors;
    private OrGate[] outputOrs;

    public UnsignedAdd() {
        super();
    }

    public UnsignedAdd(String label, int nBit) {
        super(label, nBit+nBit, nBit+1);

        // C_i = OR(AND(C_i-1,A), AND(A,B), AND(C_i-1, B)
        carryAnds = new AndGate[nBit][3];
        carryOrs = new OrGate[nBit];

        // O_i = OR(XOR(ABC), AND(ABC))
        outputAnds = new AndGate[nBit];
        outputXors = new XorGate[nBit];
        outputOrs = new OrGate[nBit];

        // Overflow = C_n-1
        CircuitNode[] outputNodes = new CircuitNode[nBit+1];

        for (int i = 0; i < nBit; i++) {
            outputNodes[i] = new CircuitNode(label + " Output_" + i);

            carryAnds[i][0] = new AndGate(label + " CarryCalcAnd_" + i + "0", 2);
            carryAnds[i][0].assignInput(0, i>0 ? carryOrs[i-1].getOutput(0) : GND);
            carryAnds[i][0].assignInput(1, getPortOutput(i));

            carryAnds[i][1] = new AndGate(label + " CarryCalcAnd_" + i + "1", 2);
            carryAnds[i][1].assignInput(0, getPortOutput(i));
            carryAnds[i][1].assignInput(1, getPortOutput(i+nBit));

            carryAnds[i][2] = new AndGate(label + " CarryCalcAnd_" + i + "2", 2);
            carryAnds[i][2].assignInput(0, i>0 ? carryOrs[i-1].getOutput(0) : GND);
            carryAnds[i][2].assignInput(1, getPortOutput(i+nBit));

            carryOrs[i] = new OrGate(label + " CarryOr_" + i, 3);
            for (int j = 0; j < 3; j++) {
                carryOrs[i].assignInput(j, carryAnds[i][j].getOutput(0));
            }

            outputAnds[i] = new AndGate(label + " OutputCalcAnd_" + i, 3);
            outputAnds[i].assignInput(0, getPortOutput(i));
            outputAnds[i].assignInput(1, getPortOutput(i+nBit));
            outputAnds[i].assignInput(2, i>0 ? carryOrs[i-1].getOutput(0) : GND);

            outputXors[i] = new XorGate(label + "OutputXor_" + i, 3);
            outputXors[i].assignInput(0, getPortOutput(i));
            outputXors[i].assignInput(1, getPortOutput(i+nBit));
            outputXors[i].assignInput(2, i>0 ? carryOrs[i-1].getOutput(0) : GND);

            outputOrs[i] = new OrGate(label + " OutputAnd_" + i, 2);
            outputOrs[i].assignInput(0, outputXors[i].getOutput(0));
            outputOrs[i].assignInput(1, outputAnds[i].getOutput(0));

            assignOutput(i, outputNodes[i]);
        }
        outputNodes[nBit] = new CircuitNode(label + " Output_" + nBit);
        assignOutput(nBit, outputNodes[nBit]);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        if (i==getNumOutputs()-1) {
            carryOrs[carryOrs.length-1].assignOutput(0, output);
        } else if (i>=0 && i<getNumOutputs()-1) {
            outputOrs[i].assignOutput(0, output);
        }
        super.assignOutput(i, output);
    }

    public void evaluate() {
        super.evaluate();

        for (int i = 0; i < getNumOutputs()-1; i++) {
            for (int j = 0; j < 3; j++) {
                carryAnds[i][j].evaluate();
            }
            carryOrs[i].evaluate();

            outputAnds[i].evaluate();
            outputXors[i].evaluate();
            outputOrs[i].evaluate();
        }
    }
}
