package core_architecture;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public abstract class MitOcwTest {

    protected static final CircuitNode[] TEST_2_00 = {GND, GND};
    protected static final CircuitNode[] TEST_2_01 = {GND, VDD};
    protected static final CircuitNode[] TEST_2_10 = {VDD, GND};
    protected static final CircuitNode[] TEST_2_11 = {VDD, VDD};
    
    protected static final CircuitNode[] TEST_3_000 = {GND, GND, GND};
    protected static final CircuitNode[] TEST_3_001 = {GND, GND, VDD};
    protected static final CircuitNode[] TEST_3_010 = {GND, VDD, GND};
    protected static final CircuitNode[] TEST_3_011 = {GND, VDD, VDD};
    protected static final CircuitNode[] TEST_3_100 = {VDD, GND, GND};
    protected static final CircuitNode[] TEST_3_101 = {VDD, GND, VDD};
    protected static final CircuitNode[] TEST_3_110 = {VDD, VDD, GND};
    protected static final CircuitNode[] TEST_3_111 = {VDD, VDD, VDD};
    
    protected static final CircuitNode[] TEST_4_0000 = {GND, GND, GND, GND};
    protected static final CircuitNode[] TEST_4_0001 = {GND, GND, GND, VDD};
    protected static final CircuitNode[] TEST_4_0010 = {GND, GND, VDD, GND};
    protected static final CircuitNode[] TEST_4_0011 = {GND, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_4_0100 = {GND, VDD, GND, GND};
    protected static final CircuitNode[] TEST_4_0101 = {GND, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_4_0110 = {GND, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_4_0111 = {GND, VDD, VDD, VDD};
    protected static final CircuitNode[] TEST_4_1000 = {VDD, GND, GND, GND};
    protected static final CircuitNode[] TEST_4_1001 = {VDD, GND, GND, VDD};
    protected static final CircuitNode[] TEST_4_1010 = {VDD, GND, VDD, GND};
    protected static final CircuitNode[] TEST_4_1011 = {VDD, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_4_1100 = {VDD, VDD, GND, GND};
    protected static final CircuitNode[] TEST_4_1101 = {VDD, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_4_1110 = {VDD, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_4_1111 = {VDD, VDD, VDD, VDD};

    protected static final CircuitNode[] TEST_5_00000 = {GND, GND, GND, GND, GND};
    protected static final CircuitNode[] TEST_5_00001 = {GND, GND, GND, GND, VDD};
    protected static final CircuitNode[] TEST_5_00010 = {GND, GND, GND, VDD, GND};
    protected static final CircuitNode[] TEST_5_00011 = {GND, GND, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_5_00100 = {GND, GND, VDD, GND, GND};
    protected static final CircuitNode[] TEST_5_00101 = {GND, GND, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_5_00110 = {GND, GND, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_5_00111 = {GND, GND, VDD, VDD, VDD};
    protected static final CircuitNode[] TEST_5_01000 = {GND, VDD, GND, GND, GND};
    protected static final CircuitNode[] TEST_5_01001 = {GND, VDD, GND, GND, VDD};
    protected static final CircuitNode[] TEST_5_01010 = {GND, VDD, GND, VDD, GND};
    protected static final CircuitNode[] TEST_5_01011 = {GND, VDD, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_5_01100 = {GND, VDD, VDD, GND, GND};
    protected static final CircuitNode[] TEST_5_01101 = {GND, VDD, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_5_01110 = {GND, VDD, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_5_01111 = {GND, VDD, VDD, VDD, VDD};
    protected static final CircuitNode[] TEST_5_10000 = {VDD, GND, GND, GND, GND};
    protected static final CircuitNode[] TEST_5_10001 = {VDD, GND, GND, GND, VDD};
    protected static final CircuitNode[] TEST_5_10010 = {VDD, GND, GND, VDD, GND};
    protected static final CircuitNode[] TEST_5_10011 = {VDD, GND, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_5_10100 = {VDD, GND, VDD, GND, GND};
    protected static final CircuitNode[] TEST_5_10101 = {VDD, GND, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_5_10110 = {VDD, GND, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_5_10111 = {VDD, GND, VDD, VDD, VDD};
    protected static final CircuitNode[] TEST_5_11000 = {VDD, VDD, GND, GND, GND};
    protected static final CircuitNode[] TEST_5_11001 = {VDD, VDD, GND, GND, VDD};
    protected static final CircuitNode[] TEST_5_11010 = {VDD, VDD, GND, VDD, GND};
    protected static final CircuitNode[] TEST_5_11011 = {VDD, VDD, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_5_11100 = {VDD, VDD, VDD, GND, GND};
    protected static final CircuitNode[] TEST_5_11101 = {VDD, VDD, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_5_11110 = {VDD, VDD, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_5_11111 = {VDD, VDD, VDD, VDD, VDD};

    protected static final CircuitNode[] TEST_0x00 = {GND, GND, GND, GND, GND, GND, GND, GND};
    protected static final CircuitNode[] TEST_0x01 = {GND, GND, GND, GND, GND, GND, GND, VDD};
    protected static final CircuitNode[] TEST_0x10 = {GND, GND, GND, VDD, GND, GND, GND, GND};
    protected static final CircuitNode[] TEST_0xEF = {VDD, VDD, VDD, GND, VDD, VDD, VDD, VDD};
    protected static final CircuitNode[] TEST_0xFE = {VDD, VDD, VDD, VDD, VDD, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_0xFF = {VDD, VDD, VDD, VDD, VDD, VDD, VDD, VDD};



    protected static final Boolean[] ANS_2_00 = {false, false};
    protected static final Boolean[] ANS_2_01 = {false, true};
    protected static final Boolean[] ANS_2_10 = {true, false};
    protected static final Boolean[] ANS_2_11 = {true, true};
    
    protected static final Boolean[] ANS_3_000 = {false, false, false};
    protected static final Boolean[] ANS_3_001 = {false, false, true};
    protected static final Boolean[] ANS_3_010 = {false, true, false};
    protected static final Boolean[] ANS_3_011 = {false, true, true};
    protected static final Boolean[] ANS_3_100 = {true, false, false};
    protected static final Boolean[] ANS_3_101 = {true, false, true};
    protected static final Boolean[] ANS_3_110 = {true, true, false};
    protected static final Boolean[] ANS_3_111 = {true, true, true};
    
    protected static final Boolean[] ANS_4_0000 = {false, false, false, false};
    protected static final Boolean[] ANS_4_0001 = {false, false, false, true};
    protected static final Boolean[] ANS_4_0010 = {false, false, true, false};
    protected static final Boolean[] ANS_4_0011 = {false, false, true, true};
    protected static final Boolean[] ANS_4_0100 = {false, true, false, false};
    protected static final Boolean[] ANS_4_0101 = {false, true, false, true};
    protected static final Boolean[] ANS_4_0110 = {false, true, true, false};
    protected static final Boolean[] ANS_4_0111 = {false, true, true, true};
    protected static final Boolean[] ANS_4_1000 = {true, false, false, false};
    protected static final Boolean[] ANS_4_1001 = {true, false, false, true};
    protected static final Boolean[] ANS_4_1010 = {true, false, true, false};
    protected static final Boolean[] ANS_4_1011 = {true, false, true, true};
    protected static final Boolean[] ANS_4_1100 = {true, true, false, false};
    protected static final Boolean[] ANS_4_1101 = {true, true, false, true};
    protected static final Boolean[] ANS_4_1110 = {true, true, true, false};
    protected static final Boolean[] ANS_4_1111 = {true, true, true, true};

    protected CircuitNode[] generateCNArrayFromInts(int inputA, int inputB) {
        return generateInputsArray(inputA, inputB, 32);
    }

    protected CircuitNode[] generateInputsArray(int inputA, int inputB, int length) {
        CircuitNode[] returnCNArray = new CircuitNode[length*2];
        for (int i = 0; i < length; i++) {
            returnCNArray[2*length-1-i] = ((inputB>>i)&1)==1 ? VDD : GND;
            returnCNArray[length-1-i] = ((inputA>>i)&1)==1 ? VDD : GND;
        }
        return returnCNArray;
    }
    
    protected Boolean[] generateBooleanArrayFromLong(long input) {
        return generateBooleanArray(input, 64);
    }

    protected Boolean[] generateBooleanArray(long input, int length) {
        Boolean[] returnBoolArray = new Boolean[length];
        for (int i = 0; i < length; i++) {
            returnBoolArray[length-1-i] = ((input>>i)&1)==1;
        }
        return returnBoolArray;
    }

    protected static Boolean[] mapInputsToBooleanOutput(CircuitNode[] test) {
        Boolean[] booleanTest = new Boolean[test.length];
        for (int i = 0; i < test.length; i++) {
            if (test[i].getStatus() == ConnectionType.POWER) {
                booleanTest[i] = true;
            } else if (test[i].getStatus() == ConnectionType.GROUND) {
                booleanTest[i] = false;
            } else {
                booleanTest[i] = null;
            }
        }
        return booleanTest;
    }
}
