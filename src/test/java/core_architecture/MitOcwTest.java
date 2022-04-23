package core_architecture;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public abstract class MitOcwTest {

    protected static final CircuitNode[] TEST_00 = {GND, GND};
    protected static final CircuitNode[] TEST_01 = {GND, VDD};
    protected static final CircuitNode[] TEST_10 = {VDD, GND};
    protected static final CircuitNode[] TEST_11 = {VDD, VDD};
    
    protected static final CircuitNode[] TEST_000 = {GND, GND, GND};
    protected static final CircuitNode[] TEST_001 = {GND, GND, VDD};
    protected static final CircuitNode[] TEST_010 = {GND, VDD, GND};
    protected static final CircuitNode[] TEST_011 = {GND, VDD, VDD};
    protected static final CircuitNode[] TEST_100 = {VDD, GND, GND};
    protected static final CircuitNode[] TEST_101 = {VDD, GND, VDD};
    protected static final CircuitNode[] TEST_110 = {VDD, VDD, GND};
    protected static final CircuitNode[] TEST_111 = {VDD, VDD, VDD};
    
    protected static final CircuitNode[] TEST_0000 = {GND, GND, GND, GND};
    protected static final CircuitNode[] TEST_0001 = {GND, GND, GND, VDD};
    protected static final CircuitNode[] TEST_0010 = {GND, GND, VDD, GND};
    protected static final CircuitNode[] TEST_0011 = {GND, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_0100 = {GND, VDD, GND, GND};
    protected static final CircuitNode[] TEST_0101 = {GND, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_0110 = {GND, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_0111 = {GND, VDD, VDD, VDD};
    protected static final CircuitNode[] TEST_1000 = {VDD, GND, GND, GND};
    protected static final CircuitNode[] TEST_1001 = {VDD, GND, GND, VDD};
    protected static final CircuitNode[] TEST_1010 = {VDD, GND, VDD, GND};
    protected static final CircuitNode[] TEST_1011 = {VDD, GND, VDD, VDD};
    protected static final CircuitNode[] TEST_1100 = {VDD, VDD, GND, GND};
    protected static final CircuitNode[] TEST_1101 = {VDD, VDD, GND, VDD};
    protected static final CircuitNode[] TEST_1110 = {VDD, VDD, VDD, GND};
    protected static final CircuitNode[] TEST_1111 = {VDD, VDD, VDD, VDD};

    protected static final Boolean[] ANS_00 = {false, false};
    protected static final Boolean[] ANS_01 = {false, true};
    protected static final Boolean[] ANS_10 = {true, false};
    protected static final Boolean[] ANS_11 = {true, true};
    
    protected static final Boolean[] ANS_000 = {false, false, false};
    protected static final Boolean[] ANS_001 = {false, false, true};
    protected static final Boolean[] ANS_010 = {false, true, false};
    protected static final Boolean[] ANS_011 = {false, true, true};
    protected static final Boolean[] ANS_100 = {true, false, false};
    protected static final Boolean[] ANS_101 = {true, false, true};
    protected static final Boolean[] ANS_110 = {true, true, false};
    protected static final Boolean[] ANS_111 = {true, true, true};
    
    protected static final Boolean[] ANS_0000 = {false, false, false, false};
    protected static final Boolean[] ANS_0001 = {false, false, false, true};
    protected static final Boolean[] ANS_0010 = {false, false, true, false};
    protected static final Boolean[] ANS_0011 = {false, false, true, true};
    protected static final Boolean[] ANS_0100 = {false, true, false, false};
    protected static final Boolean[] ANS_0101 = {false, true, false, true};
    protected static final Boolean[] ANS_0110 = {false, true, true, false};
    protected static final Boolean[] ANS_0111 = {false, true, true, true};
    protected static final Boolean[] ANS_1000 = {true, false, false, false};
    protected static final Boolean[] ANS_1001 = {true, false, false, true};
    protected static final Boolean[] ANS_1010 = {true, false, true, false};
    protected static final Boolean[] ANS_1011 = {true, false, true, true};
    protected static final Boolean[] ANS_1100 = {true, true, false, false};
    protected static final Boolean[] ANS_1101 = {true, true, false, true};
    protected static final Boolean[] ANS_1110 = {true, true, true, false};
    protected static final Boolean[] ANS_1111 = {true, true, true, true};
}
