package bitwise;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public abstract class BitwiseTest {
    protected static final CircuitNode[] TEST_1_0 = {GND};
    protected static final CircuitNode[] TEST_1_1 = {VDD};

    protected static final CircuitNode[] TEST_2_00 = {GND, GND};
    protected static final CircuitNode[] TEST_2_01 = {GND, VDD};
    protected static final CircuitNode[] TEST_2_10 = {VDD, GND};
    protected static final CircuitNode[] TEST_2_11 = {VDD, VDD};

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


    protected static Boolean[] mapTest(CircuitNode[] test) {
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

