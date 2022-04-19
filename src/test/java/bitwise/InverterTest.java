package bitwise;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InverterTest extends BitwiseTest{

    private Inverter inv1Bit;
    private Inverter inv2Bit;
    private Inverter inv4Bit;
    private Inverter inv2BitExternalOutput;

    private CircuitNode[] externalOutputs;

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
}
