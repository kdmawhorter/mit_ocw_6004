package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.*;

import org.jetbrains.annotations.NotNull;

public class UnsignedAdd extends DigitalCircuit {
    private NandGate[][] carryCalcNands;
    protected NandGate[] carryNands;

    private InverterGate[][] invPortsCarrys;
    private NandGate[][] outputCalcNands;
    private NandGate[] outputNands;
    

    public UnsignedAdd() {
        super();
    }

    public UnsignedAdd(String label, int nBit) {
        super(label, nBit+nBit, nBit+1);

        invPortsCarrys = new InverterGate[nBit][3];
        
        // C_i = OR(AND(C_i-1,A), AND(A,B), AND(C_i-1, B)
        //      ==>
        // C_i = NAND((NAND(C_i-1,A), NAND(A,B), NAND(C_i-1, B)
        
        carryCalcNands = new NandGate[nBit][3];
        carryNands = new NandGate[nBit];
        
        /* 
        O_i = OR(XOR(ABC), AND(ABC))
                ==>
        O_i = NAND(NAND(AB!C), NAND(A!BC), NAND(!ABC), NAND(ABC))
        */
        
        
        outputCalcNands = new NandGate[nBit][4];
        outputNands = new NandGate[nBit];

        // Overflow = C_n-1
        CircuitNode[] outputNodes = new CircuitNode[nBit+1];

        for (int i = nBit-1; i >= 0; i--) {
            CircuitNode[] carryCalcNandSrcArray = {i<nBit-1 ? carryNands[i+1].getOutput(0) : GND,
                                                 getPortOutput(i),
                                                 getPortOutput(i+nBit)};
            
            int[][] carryCalcNandSrcInds = {{0, 1}, {1, 2}, {0, 2}};
                    
            
            outputNodes[i] = new CircuitNode(label + " Output_" + i);

            carryNands[i] = new NandGate(label + " CarryNand_" + i, 3);
            transistorCount += carryNands[i].getTransistorCount();
            
            for (int j = 0; j < 3; j++) {
                carryCalcNands[i][j] = new NandGate(label + " CarryCalcNand_" + i + "_" + j, 2);
                carryCalcNands[i][j].assignInput(0, carryCalcNandSrcArray[carryCalcNandSrcInds[j][0]]);
                carryCalcNands[i][j].assignInput(1, carryCalcNandSrcArray[carryCalcNandSrcInds[j][1]]);
                transistorCount += carryCalcNands[i][j].getTransistorCount();

                carryNands[i].assignInput(j, carryCalcNands[i][j].getOutput(0));
            }

            invPortsCarrys[i][0] = new InverterGate(label + " Inverted Carry_" + i, 1);
            invPortsCarrys[i][0].assignInput(0, i<nBit-1 ? carryNands[i+1].getOutput(0) : GND);
            transistorCount += invPortsCarrys[i][0].getTransistorCount();

            invPortsCarrys[i][1] = new InverterGate(label + " Inverted Port_" + i, 1);
            invPortsCarrys[i][1].assignInput(0, getPortOutput(i));
            transistorCount += invPortsCarrys[i][1].getTransistorCount();

            invPortsCarrys[i][2] = new InverterGate(label + " Inverted Port_" + i+nBit, 1);
            invPortsCarrys[i][2].assignInput(0, getPortOutput(i+nBit));
            transistorCount += invPortsCarrys[i][2].getTransistorCount();
            
            CircuitNode[][] outputCalcNandSrcArray = {
                    { i<nBit-1 ? carryNands[i+1].getOutput(0) : GND, getPortOutput(i),
                            getPortOutput(i+nBit) },

                    { invPortsCarrys[i][0].getOutput(0), invPortsCarrys[i][1].getOutput(0),
                            invPortsCarrys[i][2].getOutput(0) } };
                                                    

            int[][] outputCalcNandSrcInds = { {0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 0} };

            outputNands[i] = new NandGate(label + " OutputNand_" + i, 4);
            transistorCount += outputNands[i].getTransistorCount();

            for (int j = 0; j < 4; j++) {
                outputCalcNands[i][j] = new NandGate(label + " OutputCalcNand_" + i + "_" + j, 3);
                outputCalcNands[i][j].assignInput(0, outputCalcNandSrcArray[outputCalcNandSrcInds[j][0]][0] );
                outputCalcNands[i][j].assignInput(1, outputCalcNandSrcArray[outputCalcNandSrcInds[j][1]][1] );
                outputCalcNands[i][j].assignInput(2, outputCalcNandSrcArray[outputCalcNandSrcInds[j][2]][2] );
                transistorCount += outputCalcNands[i][j].getTransistorCount();

                outputNands[i].assignInput(j, outputCalcNands[i][j].getOutput(0));
            }

            assignOutput(i, outputNodes[i]);
        }
        outputNodes[nBit] = new CircuitNode(label + " Output_" + nBit);
        assignOutput(nBit, outputNodes[nBit]);
    }

    public UnsignedAdd(String label, int nBit, @NotNull CircuitNode[] outputs) {
        this(label, nBit);
        assignOutputs(outputs);
    }

    public UnsignedAdd(String label, int nBit, @NotNull CircuitNode[] outputs, @NotNull CircuitNode[] inputs) {
        this(label, nBit, outputs);
        assignInputs(inputs);
    }

    @Override
    public void assignOutput(int i, @NotNull CircuitNode output) {
        if (i==getNumOutputs()-1) {
            carryNands[0].assignOutput(0, output);
        } else if (i>=0 && i<getNumOutputs()-1) {
            outputNands[i].assignOutput(0, output);
        }
        super.assignOutput(i, output);
    }

    public void evaluate() {
        super.evaluate();

        for (int i = getNumOutputs()-2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                carryCalcNands[i][j].evaluate();
            }
            carryNands[i].evaluate();

            for (int j = 0; j < 3; j++) {
                invPortsCarrys[i][j].evaluate();
            }

            for (int j = 0; j < 4; j++) {
                outputCalcNands[i][j].evaluate();
            }
            outputNands[i].evaluate();
        }
    }
}
