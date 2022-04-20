package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;
import logic_gates.*;

public class UnsignedAdd extends DigitalCircuit {
    private final NandGate[][] carryCalcNands;
    protected final NandGate[] carryNands;

    private final InverterGate[][] invPortsCarrys;
    private final NandGate[][] outputCalcNands;
    private final NandGate[] outputNands;

    public UnsignedAdd(String label, int nBits) {
        super(label, nBits + nBits, nBits + 1);

        invPortsCarrys = new InverterGate[nBits][3];
        carryCalcNands = new NandGate[nBits][3];
        carryNands = new NandGate[nBits];
        outputCalcNands = new NandGate[nBits][4];
        outputNands = new NandGate[nBits];
        
        initCarryGates(nBits);
        initOutGates(nBits);
    }
    
    private void initCarryGates(int nBits) {

        // C_i = OR(AND(C_i-1,A), AND(A,B), AND(C_i-1, B)
        //      ==>
        // C_i = NAND((NAND(C_i-1,A), NAND(A,B), NAND(C_i-1, B)
        // carryNand_i(carryCalcNand_i0(carryNand_i+1,A_i), carryCalcNand_i1(A_i,B_i), carryCalcNand_i2(carryNand_i+1,B_i)

        for (int i = nBits - 1; i >= 0; i--) {
            CircuitNode[] carryCalcNandSrcArray = {i < nBits - 1 ? carryNands[i + 1].getOutput() : GND,
                    getInPortOutput(i),
                    getInPortOutput(i + nBits)};


            int[][] carryCalcNandSrcInds = {{0, 1}, {1, 2}, {0, 2}};


            carryNands[i] = new NandGate(getLabel() + " CarryNand_" + i, 3);
            transistorCount += carryNands[i].getTransistorCount();

            for (int j = 0; j < 3; j++) {
                carryCalcNands[i][j] = new NandGate(getLabel() + " CarryCalcNand_" + i + "_" + j, 2);
                carryCalcNands[i][j].assignInput(0, carryCalcNandSrcArray[carryCalcNandSrcInds[j][0]]);
                carryCalcNands[i][j].assignInput(1, carryCalcNandSrcArray[carryCalcNandSrcInds[j][1]]);

                invPortsCarrys[i][j] = new InverterGate(getLabel() + " Inverted_" + i + "_" + j);
                invPortsCarrys[i][j].assignInput(carryCalcNandSrcArray[j]);

                transistorCount += carryCalcNands[i][j].getTransistorCount() + invPortsCarrys[i][j].getTransistorCount();
                carryNands[i].assignInput(j, carryCalcNands[i][j].getOutput());
            }
        }
        carryNands[0].assignOutput(getOutPortInput(nBits));
    }

    private void initOutGates(int nBits) {
        /* 
        O_i = OR(XOR(ABC), AND(ABC))
                ==>
        O_i = NAND(NAND(A!B!C), NAND(!A!BC), NAND(!AB!C), NAND(ABC))
        */

        for (int i = nBits - 1; i >= 0; i--) {
            CircuitNode[][] outputCalcNandSrcArray = {
                    { i<nBits-1 ? carryNands[i+1].getOutput() : GND,
                      getInPortOutput(i),
                      getInPortOutput(i+nBits) },

                    { invPortsCarrys[i][0].getOutput(),
                      invPortsCarrys[i][1].getOutput(),
                      invPortsCarrys[i][2].getOutput() } };


            int[][] outputCalcNandSrcInds = { {0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 0} };

            outputNands[i] = new NandGate(getLabel() + " OutputNand_" + i, 4);
            transistorCount += outputNands[i].getTransistorCount();

            for (int j = 0; j < 4; j++) {
                outputCalcNands[i][j] = new NandGate(getLabel() + " OutputCalcNand_" + i + "_" + j, 3);
                outputCalcNands[i][j].assignInput(0, outputCalcNandSrcArray[outputCalcNandSrcInds[j][0]][0] );
                outputCalcNands[i][j].assignInput(1, outputCalcNandSrcArray[outputCalcNandSrcInds[j][1]][1] );
                outputCalcNands[i][j].assignInput(2, outputCalcNandSrcArray[outputCalcNandSrcInds[j][2]][2] );
                outputCalcNands[i][j].assignOutput(outputNands[i].getInput(j));

                transistorCount += outputCalcNands[i][j].getTransistorCount();
            }
            outputNands[i].assignOutput(getOutPortInput(i));
        }
    }

    @Override
    protected void evaluateCircuit() {
        for (int i = getNumOutputs()-2; i >= 0; i--) {
            for (NandGate carryCalcNand : carryCalcNands[i]) {
                carryCalcNand.evaluate();
            }
            carryNands[i].evaluate();

            for (InverterGate invPort : invPortsCarrys[i]) {
                invPort.evaluate();
            }

            for (NandGate outputCalcNand : outputCalcNands[i]) {
                outputCalcNand.evaluate();
            }
            outputNands[i].evaluate();
        }
    }
}
