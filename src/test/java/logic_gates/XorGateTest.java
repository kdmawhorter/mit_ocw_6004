package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class XorGateTest extends MitOcwTest {

    private XorGate xor1Bit;
    private XorGate xor2Bit;
    private XorGate xor4Bit;
    private XorGate externalOutputXor2Bit;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        xor1Bit = new XorGate("1 Bit Xor", 1);
        xor2Bit = new XorGate("2 Bit Xor", 2);
        xor4Bit = new XorGate("4 Bit Xor", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputXor2Bit = new XorGate("External Output 2 Bit Xor", 2);
        externalOutputXor2Bit.assignOutput(externalOutput);
    }

    @Test
    void xorFuncTest() {
        // Transistor Count Tests
        assertEquals(6, xor1Bit.getTransistorCount(), "1 Bit Xor Transistor Count Testing");
        assertEquals(16, xor2Bit.getTransistorCount(), "2 Bit Xor Transistor Count Testing");
        assertEquals(48, xor4Bit.getTransistorCount(), "4 Bit Xor Transistor Count Testing");

        //Xor 1 Tests
        xor1Bit.assignInputOff(0);
        xor1Bit.evaluate();
        assertFalse(xor1Bit.readOutput(0), "1 Bit Xor - 0");

        xor1Bit.assignInputOn(0);
        xor1Bit.evaluate();
        assertTrue(xor1Bit.readOutput(0), "1 Bit Xor - 1");

        //Xor 2 Tests
        xor2Bit.assignInputs(TEST_2_00);
        xor2Bit.evaluate();
        assertFalse(xor2Bit.readOutput(0), "2 Bit Xor - 00");

        xor2Bit.assignInputs(TEST_2_01);
        xor2Bit.evaluate();
        assertTrue(xor2Bit.readOutput(0), "2 Bit Xor - 01");

        xor2Bit.assignInputs(TEST_2_10);
        xor2Bit.evaluate();
        assertTrue(xor2Bit.readOutput(0), "2 Bit Xor - 10");

        xor2Bit.assignInputs(TEST_2_11);
        xor2Bit.evaluate();
        assertFalse(xor2Bit.readOutput(0), "2 Bit Xor - 11");

        //Xor 4 Tests
        xor4Bit.assignInputs(TEST_4_0000);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0000");

        xor4Bit.assignInputs(TEST_4_0001);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 0001");

        xor4Bit.assignInputs(TEST_4_0010);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 0010");

        xor4Bit.assignInputs(TEST_4_0011);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0011");

        xor4Bit.assignInputs(TEST_4_0100);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 0100");

        xor4Bit.assignInputs(TEST_4_0101);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0101");

        xor4Bit.assignInputs(TEST_4_0110);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0110");

        xor4Bit.assignInputs(TEST_4_0111);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0111");

        xor4Bit.assignInputs(TEST_4_1000);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 1000");

        xor4Bit.assignInputs(TEST_4_1001);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1001");

        xor4Bit.assignInputs(TEST_4_1010);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1010");

        xor4Bit.assignInputs(TEST_4_1011);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1011");

        xor4Bit.assignInputs(TEST_4_1100);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1100");

        xor4Bit.assignInputs(TEST_4_1101);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1101");

        xor4Bit.assignInputs(TEST_4_1110);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1110");

        xor4Bit.assignInputs(TEST_4_1111);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1111");

        //External Output Xor 2 Tests
        externalOutputXor2Bit.assignInputs(TEST_2_00);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Xor - 00");

        externalOutputXor2Bit.assignInputs(TEST_2_01);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Xor - 01");

        externalOutputXor2Bit.assignInputs(TEST_2_10);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Xor - 10");

        externalOutputXor2Bit.assignInputs(TEST_2_11);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Xor - 11");
    }
}
