package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class NandGateTest extends MitOcwTest {

    private NandGate nand1Bit;
    private NandGate nand2Bit;
    private NandGate nand4Bit;
    private NandGate externalOutputNand2Bit;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        nand1Bit = new NandGate("1 Bit Nand", 1);
        nand2Bit = new NandGate("2 Bit Nand", 2);
        nand4Bit = new NandGate("4 Bit Nand", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputNand2Bit = new NandGate("External Output 2 Bit Nand", 2);
        externalOutputNand2Bit.assignOutput(externalOutput);
    }

    @Test
    void nandFuncTest() {
        // Transistor Count Tests
        assertEquals(2, nand1Bit.getTransistorCount(), "1 Bit Nand Transistor Count Testing");
        assertEquals(4, nand2Bit.getTransistorCount(), "2 Bit Nand Transistor Count Testing");
        assertEquals(8, nand4Bit.getTransistorCount(), "4 Bit Nand Transistor Count Testing");

        //Nand 1 Tests
        nand1Bit.assignInputOff(0);
        nand1Bit.evaluate();
        assertTrue(nand1Bit.readOutput(0), "1 Bit Nand - 0");

        nand1Bit.assignInputOn(0);
        nand1Bit.evaluate();
        assertFalse(nand1Bit.readOutput(0), "1 Bit Nand - 1");

        //Nand 2 Tests
        nand2Bit.assignInputs(TEST_2_00);
        nand2Bit.evaluate();
        assertTrue(nand2Bit.readOutput(0), "2 Bit Nand - 00");

        nand2Bit.assignInputs(TEST_2_01);
        nand2Bit.evaluate();
        assertTrue(nand2Bit.readOutput(0), "2 Bit Nand - 01");

        nand2Bit.assignInputs(TEST_2_10);
        nand2Bit.evaluate();
        assertTrue(nand2Bit.readOutput(0), "2 Bit Nand - 10");

        nand2Bit.assignInputs(TEST_2_11);
        nand2Bit.evaluate();
        assertFalse(nand2Bit.readOutput(0), "2 Bit Nand - 11");

        //Nand 4 Tests
        nand4Bit.assignInputs(TEST_4_0000);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0000");

        nand4Bit.assignInputs(TEST_4_0001);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0001");

        nand4Bit.assignInputs(TEST_4_0010);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0010");

        nand4Bit.assignInputs(TEST_4_0011);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0011");

        nand4Bit.assignInputs(TEST_4_0100);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0100");

        nand4Bit.assignInputs(TEST_4_0101);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0101");

        nand4Bit.assignInputs(TEST_4_0110);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0110");

        nand4Bit.assignInputs(TEST_4_0111);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0111");

        nand4Bit.assignInputs(TEST_4_1000);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1000");

        nand4Bit.assignInputs(TEST_4_1001);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1001");

        nand4Bit.assignInputs(TEST_4_1010);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1010");

        nand4Bit.assignInputs(TEST_4_1011);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1011");

        nand4Bit.assignInputs(TEST_4_1100);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1100");

        nand4Bit.assignInputs(TEST_4_1101);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1101");

        nand4Bit.assignInputs(TEST_4_1110);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1110");

        nand4Bit.assignInputs(TEST_4_1111);
        nand4Bit.evaluate();
        assertFalse(nand4Bit.readOutput(0), "4 Bit Nand - 1111");

        //External Output Nand 2 Tests
        externalOutputNand2Bit.assignInputs(TEST_2_00);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nand - 00");

        externalOutputNand2Bit.assignInputs(TEST_2_01);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nand - 01");

        externalOutputNand2Bit.assignInputs(TEST_2_10);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nand - 10");

        externalOutputNand2Bit.assignInputs(TEST_2_11);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nand - 11");
    }
}
