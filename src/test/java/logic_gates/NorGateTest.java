package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class NorGateTest extends MitOcwTest {

    private NorGate nor1Bit;
    private NorGate nor2Bit;
    private NorGate nor4Bit;
    private NorGate externalOutputNor2Bit;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        nor1Bit = new NorGate("1 Bit Nor", 1);
        nor2Bit = new NorGate("2 Bit Nor", 2);
        nor4Bit = new NorGate("4 Bit Nor", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputNor2Bit = new NorGate("External Output 2 Bit Nor", 2);
        externalOutputNor2Bit.assignOutput(externalOutput);
    }

    @Test
    void norFuncTest() {
        // Transistor Count Tests
        assertEquals(2, nor1Bit.getTransistorCount(), "1 Bit Nor Transistor Count Testing");
        assertEquals(4, nor2Bit.getTransistorCount(), "2 Bit Nor Transistor Count Testing");
        assertEquals(8, nor4Bit.getTransistorCount(), "4 Bit Nor Transistor Count Testing");

        //Nor 1 Tests
        nor1Bit.assignInputOff(0);
        nor1Bit.evaluate();
        assertTrue(nor1Bit.readOutput(0), "1 Bit Nor - 0");

        nor1Bit.assignInputOn(0);
        nor1Bit.evaluate();
        assertFalse(nor1Bit.readOutput(0), "1 Bit Nor - 1");

        //Nor 2 Tests
        nor2Bit.assignInputs(TEST_2_00);
        nor2Bit.evaluate();
        assertTrue(nor2Bit.readOutput(0), "2 Bit Nor - 00");

        nor2Bit.assignInputs(TEST_2_01);
        nor2Bit.evaluate();
        assertFalse(nor2Bit.readOutput(0), "2 Bit Nor - 01");

        nor2Bit.assignInputs(TEST_2_10);
        nor2Bit.evaluate();
        assertFalse(nor2Bit.readOutput(0), "2 Bit Nor - 10");

        nor2Bit.assignInputs(TEST_2_11);
        nor2Bit.evaluate();
        assertFalse(nor2Bit.readOutput(0), "2 Bit Nor - 11");

        //Nor 4 Tests
        nor4Bit.assignInputs(TEST_4_0000);
        nor4Bit.evaluate();
        assertTrue(nor4Bit.readOutput(0), "4 Bit Nor - 0000");

        nor4Bit.assignInputs(TEST_4_0001);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0001");

        nor4Bit.assignInputs(TEST_4_0010);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0010");

        nor4Bit.assignInputs(TEST_4_0011);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0011");

        nor4Bit.assignInputs(TEST_4_0100);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0100");

        nor4Bit.assignInputs(TEST_4_0101);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0101");

        nor4Bit.assignInputs(TEST_4_0110);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0110");

        nor4Bit.assignInputs(TEST_4_0111);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0111");

        nor4Bit.assignInputs(TEST_4_1000);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1000");

        nor4Bit.assignInputs(TEST_4_1001);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1001");

        nor4Bit.assignInputs(TEST_4_1010);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1010");

        nor4Bit.assignInputs(TEST_4_1011);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1011");

        nor4Bit.assignInputs(TEST_4_1100);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1100");

        nor4Bit.assignInputs(TEST_4_1101);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1101");

        nor4Bit.assignInputs(TEST_4_1110);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1110");

        nor4Bit.assignInputs(TEST_4_1111);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1111");

        //External Output Nor 2 Tests
        externalOutputNor2Bit.assignInputs(TEST_2_00);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nor - 00");

        externalOutputNor2Bit.assignInputs(TEST_2_01);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nor - 01");

        externalOutputNor2Bit.assignInputs(TEST_2_10);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nor - 10");

        externalOutputNor2Bit.assignInputs(TEST_2_11);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nor - 11");
    }
}
