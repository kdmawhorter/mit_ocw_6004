package bitwise;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class InverterTest {

    private Inverter inv1Bit;
    private Inverter inv2Bit;
    private Inverter inv4Bit;
    private Inverter inv2BitExternalOutput;

    private CircuitNode[] externalOutputs;

    private static final CircuitNode[] TEST_1_0 = {GND};
    private static final CircuitNode[] TEST_1_1 = {VDD};

    private static final CircuitNode[] TEST_2_00 = {GND, GND};
    private static final CircuitNode[] TEST_2_01 = {GND, VDD};
    private static final CircuitNode[] TEST_2_10 = {VDD, GND};
    private static final CircuitNode[] TEST_2_11 = {VDD, VDD};

    private static final CircuitNode[] TEST_4_0000 = {GND, GND, GND, GND};
    private static final CircuitNode[] TEST_4_0001 = {GND, GND, GND, VDD};
    private static final CircuitNode[] TEST_4_0010 = {GND, GND, VDD, GND};
    private static final CircuitNode[] TEST_4_0011 = {GND, GND, VDD, VDD};
    private static final CircuitNode[] TEST_4_0100 = {GND, VDD, GND, GND};
    private static final CircuitNode[] TEST_4_0101 = {GND, VDD, GND, VDD};
    private static final CircuitNode[] TEST_4_0110 = {GND, VDD, VDD, GND};
    private static final CircuitNode[] TEST_4_0111 = {GND, VDD, VDD, VDD};
    private static final CircuitNode[] TEST_4_1000 = {VDD, GND, GND, GND};
    private static final CircuitNode[] TEST_4_1001 = {VDD, GND, GND, VDD};
    private static final CircuitNode[] TEST_4_1010 = {VDD, GND, VDD, GND};
    private static final CircuitNode[] TEST_4_1011 = {VDD, GND, VDD, VDD};
    private static final CircuitNode[] TEST_4_1100 = {VDD, VDD, GND, GND};
    private static final CircuitNode[] TEST_4_1101 = {VDD, VDD, GND, VDD};
    private static final CircuitNode[] TEST_4_1110 = {VDD, VDD, VDD, GND};
    private static final CircuitNode[] TEST_4_1111 = {VDD, VDD, VDD, VDD};


    @BeforeEach
    void init() {
        inv1Bit = new Inverter("Inverter 1 Bit", 1);
        inv2Bit = new Inverter("Inverter 2 Bit", 2);
        inv4Bit = new Inverter("Inverter 4 Bit", 4);

        externalOutputs = new CircuitNode[2];
        externalOutputs[0] = new CircuitNode("External Output 0");
        externalOutputs[1] = new CircuitNode("External Output 1");
        inv2BitExternalOutput= new Inverter("Test External Output Inverter", 2, externalOutputs);
    }

    @Test
    void inverterEvaluationTest() {
        // Transistor Count Tests
        assertEquals(2, inv1Bit.getTransistorCount(), "1 Bit Inverter Transistor Count Testing");
        assertEquals(4, inv2Bit.getTransistorCount(), "2 Bit Inverter Transistor Count Testing");
        assertEquals(8, inv4Bit.getTransistorCount(), "4 Bit Inverter Transistor Count Testing");

        // 1 Bit Inverter Test
        inv1Bit.assignInputs(TEST_1_0);
        inv1Bit.evaluate();
        assertArrayEquals(mapTest(TEST_1_1), inv1Bit.readOutputs(), "1 Bit Inverter 0 Test");

        inv1Bit.assignInputs(TEST_1_1);
        inv1Bit.evaluate();
        assertArrayEquals(mapTest(TEST_1_0), inv1Bit.readOutputs(), "1 Bit Inverter 1 Test");

        // 2 Bit Inverter Test
        inv2Bit.assignInputs(TEST_2_00);
        inv2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_11), inv2Bit.readOutputs(), "2 Bit Inverter 00 Test");

        inv2Bit.assignInputs(TEST_2_01);
        inv2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_10), inv2Bit.readOutputs(), "2 Bit Inverter 01 Test");

        inv2Bit.assignInputs(TEST_2_10);
        inv2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_01), inv2Bit.readOutputs(), "2 Bit Inverter 10 Test");

        inv2Bit.assignInputs(TEST_2_11);
        inv2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_00), inv2Bit.readOutputs(), "2 Bit Inverter 11 Test");

        // 4 Bit Inverter Test
        inv4Bit.assignInputs(TEST_4_0000);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1111), inv4Bit.readOutputs(), "4 Bit Inverter 0000 Test");

        inv4Bit.assignInputs(TEST_4_0001);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1110), inv4Bit.readOutputs(), "4 Bit Inverter 0001 Test");

        inv4Bit.assignInputs(TEST_4_0010);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1101), inv4Bit.readOutputs(), "4 Bit Inverter 0010 Test");

        inv4Bit.assignInputs(TEST_4_0011);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1100), inv4Bit.readOutputs(), "4 Bit Inverter 0011 Test");

        inv4Bit.assignInputs(TEST_4_0100);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1011), inv4Bit.readOutputs(), "4 Bit Inverter 0100 Test");

        inv4Bit.assignInputs(TEST_4_0101);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1010), inv4Bit.readOutputs(), "4 Bit Inverter 0101 Test");

        inv4Bit.assignInputs(TEST_4_0110);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1001), inv4Bit.readOutputs(), "4 Bit Inverter 0110 Test");

        inv4Bit.assignInputs(TEST_4_0111);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_1000), inv4Bit.readOutputs(), "4 Bit Inverter 1000 Test");

        inv4Bit.assignInputs(TEST_4_1000);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0111), inv4Bit.readOutputs(), "4 Bit Inverter 1000 Test");

        inv4Bit.assignInputs(TEST_4_1001);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0110), inv4Bit.readOutputs(), "4 Bit Inverter 1001 Test");

        inv4Bit.assignInputs(TEST_4_1010);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0101), inv4Bit.readOutputs(), "4 Bit Inverter 1010 Test");

        inv4Bit.assignInputs(TEST_4_1011);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0100), inv4Bit.readOutputs(), "4 Bit Inverter 1011 Test");

        inv4Bit.assignInputs(TEST_4_1100);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0011), inv4Bit.readOutputs(), "4 Bit Inverter 1100 Test");

        inv4Bit.assignInputs(TEST_4_1101);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0010), inv4Bit.readOutputs(), "4 Bit Inverter 1101 Test");

        inv4Bit.assignInputs(TEST_4_1110);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0001), inv4Bit.readOutputs(), "4 Bit Inverter 1110 Test");

        inv4Bit.assignInputs(TEST_4_1111);
        inv4Bit.evaluate();
        assertArrayEquals(mapTest(TEST_4_0000), inv4Bit.readOutputs(), "4 Bit Inverter 1111 Test");

        // 2 Bit External Output Inverter
        inv2BitExternalOutput.assignInputs(TEST_2_00);
        inv2BitExternalOutput.evaluate();
        assertArrayEquals(mapTest(TEST_2_11), mapTest(externalOutputs), "2 Bit External Out Inverter 00 Test");

        inv2BitExternalOutput.assignInputs(TEST_2_01);
        inv2BitExternalOutput.evaluate();
        assertArrayEquals(mapTest(TEST_2_10), mapTest(externalOutputs), "2 Bit External Out Inverter 01 Test");

        inv2BitExternalOutput.assignInputs(TEST_2_10);
        inv2BitExternalOutput.evaluate();
        assertArrayEquals(mapTest(TEST_2_01), mapTest(externalOutputs), "2 Bit External Out Inverter 10 Test");

        inv2BitExternalOutput.assignInputs(TEST_2_11);
        inv2BitExternalOutput.evaluate();
        assertArrayEquals(mapTest(TEST_2_00), mapTest(externalOutputs), "2 Bit External Out Inverter 11 Test");
    }

    private Boolean[] mapTest(CircuitNode[] test) {
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
