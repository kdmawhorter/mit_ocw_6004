package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AndGateTest extends MitOcwTest {

    private AndGate and1Bit;
    private AndGate and2Bit;
    private AndGate and4Bit;
    private AndGate externalOutputAnd2Bit;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        and1Bit = new AndGate("1 Bit And", 1);
        and2Bit = new AndGate("2 Bit And", 2);
        and4Bit = new AndGate("4 Bit And", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputAnd2Bit = new AndGate("External Output 2 Bit And", 2);
        externalOutputAnd2Bit.assignOutput(externalOutput);
    }

    @Test
    void andFuncTest() {
        // Transistor Count Tests
        assertEquals(4, and1Bit.getTransistorCount(), "1 Bit And Transistor Count Testing");
        assertEquals(6, and2Bit.getTransistorCount(), "2 Bit And Transistor Count Testing");
        assertEquals(10, and4Bit.getTransistorCount(), "4 Bit And Transistor Count Testing");

        //And 1 Tests
        and1Bit.assignInputOff(0);
        and1Bit.evaluate();
        assertFalse(and1Bit.readOutput(0), "1 Bit And - 0");

        and1Bit.assignInputOn(0);
        and1Bit.evaluate();
        assertTrue(and1Bit.readOutput(0), "1 Bit And - 1");

        //And 2 Tests
        and2Bit.assignInputs(TEST_2_00);
        and2Bit.evaluate();
        assertFalse(and2Bit.readOutput(0), "2 Bit And - 00");

        and2Bit.assignInputs(TEST_2_01);
        and2Bit.evaluate();
        assertFalse(and2Bit.readOutput(0), "2 Bit And - 01");

        and2Bit.assignInputs(TEST_2_10);
        and2Bit.evaluate();
        assertFalse(and2Bit.readOutput(0), "2 Bit And - 10");

        and2Bit.assignInputs(TEST_2_11);
        and2Bit.evaluate();
        assertTrue(and2Bit.readOutput(0), "2 Bit And - 11");

        //And 4 Tests
        and4Bit.assignInputs(TEST_4_0000);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0000");

        and4Bit.assignInputs(TEST_4_0001);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0001");

        and4Bit.assignInputs(TEST_4_0010);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0010");

        and4Bit.assignInputs(TEST_4_0011);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0011");

        and4Bit.assignInputs(TEST_4_0100);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0100");

        and4Bit.assignInputs(TEST_4_0101);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0101");

        and4Bit.assignInputs(TEST_4_0110);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0110");

        and4Bit.assignInputs(TEST_4_0111);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0111");

        and4Bit.assignInputs(TEST_4_1000);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1000");

        and4Bit.assignInputs(TEST_4_1001);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1001");

        and4Bit.assignInputs(TEST_4_1010);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1010");

        and4Bit.assignInputs(TEST_4_1011);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1011");

        and4Bit.assignInputs(TEST_4_1100);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1100");

        and4Bit.assignInputs(TEST_4_1101);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1101");

        and4Bit.assignInputs(TEST_4_1110);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1110");

        and4Bit.assignInputs(TEST_4_1111);
        and4Bit.evaluate();
        assertTrue(and4Bit.readOutput(0), "4 Bit And - 1111");

        //External Output And 2 Tests
        externalOutputAnd2Bit.assignInputs(TEST_2_00);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit And - 00");

        externalOutputAnd2Bit.assignInputs(TEST_2_01);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit And - 01");

        externalOutputAnd2Bit.assignInputs(TEST_2_10);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit And - 10");

        externalOutputAnd2Bit.assignInputs(TEST_2_11);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit And - 11");
    }
}
