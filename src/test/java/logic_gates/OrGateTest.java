package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class OrGateTest extends MitOcwTest {

    private OrGate or1Bit;
    private OrGate or2Bit;
    private OrGate or4Bit;
    private OrGate externalOutputOr2Bit;
    
    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        or1Bit = new OrGate("1 Bit Or", 1);
        or2Bit = new OrGate("2 Bit Or", 2);
        or4Bit = new OrGate("4 Bit Or", 4);
        
        externalOutput = new CircuitNode("External Output");
        externalOutputOr2Bit = new OrGate("External Output 2 Bit Or", 2);
        externalOutputOr2Bit.assignOutput(externalOutput);
    }

    @Test
    void orFuncTest() {
        // Transistor Count Tests
        assertEquals(4, or1Bit.getTransistorCount(), "1 Bit Or Transistor Count Testing");
        assertEquals(6, or2Bit.getTransistorCount(), "2 Bit Or Transistor Count Testing");
        assertEquals(10, or4Bit.getTransistorCount(), "4 Bit Or Transistor Count Testing");

        //Or 1 Tests
        or1Bit.assignInputOff(0);
        or1Bit.evaluate();
        assertFalse(or1Bit.readOutput(0), "1 Bit Or - 0");

        or1Bit.assignInputOn(0);
        or1Bit.evaluate();
        assertTrue(or1Bit.readOutput(0), "1 Bit Or - 1");

        //Or 2 Tests
        or2Bit.assignInputs(TEST_2_00);
        or2Bit.evaluate();
        assertFalse(or2Bit.readOutput(0), "2 Bit Or - 00");

        or2Bit.assignInputs(TEST_2_01);
        or2Bit.evaluate();
        assertTrue(or2Bit.readOutput(0), "2 Bit Or - 01");

        or2Bit.assignInputs(TEST_2_10);
        or2Bit.evaluate();
        assertTrue(or2Bit.readOutput(0), "2 Bit Or - 10");

        or2Bit.assignInputs(TEST_2_11);
        or2Bit.evaluate();
        assertTrue(or2Bit.readOutput(0), "2 Bit Or - 11");

        //Or 4 Tests
        or4Bit.assignInputs(TEST_4_0000);
        or4Bit.evaluate();
        assertFalse(or4Bit.readOutput(0), "4 Bit Or - 0000");

        or4Bit.assignInputs(TEST_4_0001);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0001");

        or4Bit.assignInputs(TEST_4_0010);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0010");

        or4Bit.assignInputs(TEST_4_0011);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0011");

        or4Bit.assignInputs(TEST_4_0100);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0100");

        or4Bit.assignInputs(TEST_4_0101);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0101");

        or4Bit.assignInputs(TEST_4_0110);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0110");

        or4Bit.assignInputs(TEST_4_0111);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0111");

        or4Bit.assignInputs(TEST_4_1000);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1000");

        or4Bit.assignInputs(TEST_4_1001);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1001");

        or4Bit.assignInputs(TEST_4_1010);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1010");

        or4Bit.assignInputs(TEST_4_1011);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1011");

        or4Bit.assignInputs(TEST_4_1100);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1100");

        or4Bit.assignInputs(TEST_4_1101);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1101");

        or4Bit.assignInputs(TEST_4_1110);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1110");

        or4Bit.assignInputs(TEST_4_1111);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1111");

        //External Output Or 2 Tests
        externalOutputOr2Bit.assignInputs(TEST_2_00);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Or - 00");

        externalOutputOr2Bit.assignInputs(TEST_2_01);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Or - 01");

        externalOutputOr2Bit.assignInputs(TEST_2_10);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Or - 10");

        externalOutputOr2Bit.assignInputs(TEST_2_11);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Or - 11");
    }
}
