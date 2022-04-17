package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class XorTest {

    private XorGate xor1Bit;
    private XorGate xor2Bit;
    private XorGate xor4Bit;
    private XorGate externalOutputXor2Bit;

    private CircuitNode externalOutput;

    private static final CircuitNode[] INPUT_1_0 = {GND};
    private static final CircuitNode[] INPUT_1_1 = {VDD};

    private static final CircuitNode[] INPUT_2_00 = {GND, GND};
    private static final CircuitNode[] INPUT_2_01 = {GND, VDD};
    private static final CircuitNode[] INPUT_2_10 = {VDD, GND};
    private static final CircuitNode[] INPUT_2_11 = {VDD, VDD};

    private static final CircuitNode[] INPUT_3_0000 = {GND, GND, GND, GND};
    private static final CircuitNode[] INPUT_3_0001 = {GND, GND, GND, VDD};
    private static final CircuitNode[] INPUT_3_0010 = {GND, GND, VDD, GND};
    private static final CircuitNode[] INPUT_3_0011 = {GND, GND, VDD, VDD};
    private static final CircuitNode[] INPUT_3_0100 = {GND, VDD, GND, GND};
    private static final CircuitNode[] INPUT_3_0101 = {GND, VDD, GND, VDD};
    private static final CircuitNode[] INPUT_3_0110 = {GND, VDD, VDD, GND};
    private static final CircuitNode[] INPUT_3_0111 = {GND, VDD, VDD, VDD};
    private static final CircuitNode[] INPUT_3_1000 = {VDD, GND, GND, GND};
    private static final CircuitNode[] INPUT_3_1001 = {VDD, GND, GND, VDD};
    private static final CircuitNode[] INPUT_3_1010 = {VDD, GND, VDD, GND};
    private static final CircuitNode[] INPUT_3_1011 = {VDD, GND, VDD, VDD};
    private static final CircuitNode[] INPUT_3_1100 = {VDD, VDD, GND, GND};
    private static final CircuitNode[] INPUT_3_1101 = {VDD, VDD, GND, VDD};
    private static final CircuitNode[] INPUT_3_1110 = {VDD, VDD, VDD, GND};
    private static final CircuitNode[] INPUT_3_1111 = {VDD, VDD, VDD, VDD};


    @BeforeEach
    void init() {
        xor1Bit = new XorGate("1 Bit Xor", 1);
        xor2Bit = new XorGate("2 Bit Xor", 2);
        xor4Bit = new XorGate("4 Bit Xor", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputXor2Bit = new XorGate("External Output 2 Bit Xor", 2, externalOutput);
    }

    @Test
    void xorFuncTest() {
        // Transistor Count Tests
        assertEquals(10, xor1Bit.getTransistorCount(), "1 Bit Xor Transistor Count Testing");
        assertEquals(22, xor2Bit.getTransistorCount(), "2 Bit Xor Transistor Count Testing");
        assertEquals(58, xor4Bit.getTransistorCount(), "4 Bit Xor Transistor Count Testing");

        //Xor 1 Tests
        xor1Bit.assignInputs(INPUT_1_0);
        xor1Bit.evaluate();
        assertFalse(xor1Bit.readOutput(0), "1 Bit Xor - 0");

        xor1Bit.assignInputs(INPUT_1_1);
        xor1Bit.evaluate();
        assertTrue(xor1Bit.readOutput(0), "1 Bit Xor - 1");

        //Xor 2 Tests
        xor2Bit.assignInputs(INPUT_2_00);
        xor2Bit.evaluate();
        assertFalse(xor2Bit.readOutput(0), "2 Bit Xor - 00");

        xor2Bit.assignInputs(INPUT_2_01);
        xor2Bit.evaluate();
        assertTrue(xor2Bit.readOutput(0), "2 Bit Xor - 01");

        xor2Bit.assignInputs(INPUT_2_10);
        xor2Bit.evaluate();
        assertTrue(xor2Bit.readOutput(0), "2 Bit Xor - 10");

        xor2Bit.assignInputs(INPUT_2_11);
        xor2Bit.evaluate();
        assertFalse(xor2Bit.readOutput(0), "2 Bit Xor - 11");

        //Xor 4 Tests
        xor4Bit.assignInputs(INPUT_3_0000);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0000");

        xor4Bit.assignInputs(INPUT_3_0001);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 0001");

        xor4Bit.assignInputs(INPUT_3_0010);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 0010");

        xor4Bit.assignInputs(INPUT_3_0011);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0011");

        xor4Bit.assignInputs(INPUT_3_0100);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 0100");

        xor4Bit.assignInputs(INPUT_3_0101);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0101");

        xor4Bit.assignInputs(INPUT_3_0110);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0110");

        xor4Bit.assignInputs(INPUT_3_0111);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 0111");

        xor4Bit.assignInputs(INPUT_3_1000);
        xor4Bit.evaluate();
        assertTrue(xor4Bit.readOutput(0), "4 Bit Xor - 1000");

        xor4Bit.assignInputs(INPUT_3_1001);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1001");

        xor4Bit.assignInputs(INPUT_3_1010);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1010");

        xor4Bit.assignInputs(INPUT_3_1011);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1011");

        xor4Bit.assignInputs(INPUT_3_1100);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1100");

        xor4Bit.assignInputs(INPUT_3_1101);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1101");

        xor4Bit.assignInputs(INPUT_3_1110);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1110");

        xor4Bit.assignInputs(INPUT_3_1111);
        xor4Bit.evaluate();
        assertFalse(xor4Bit.readOutput(0), "4 Bit Xor - 1111");

        //External Output Xor 2 Tests
        externalOutputXor2Bit.assignInputs(INPUT_2_00);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Xor - 00");

        externalOutputXor2Bit.assignInputs(INPUT_2_01);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Xor - 01");

        externalOutputXor2Bit.assignInputs(INPUT_2_10);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Xor - 10");

        externalOutputXor2Bit.assignInputs(INPUT_2_11);
        externalOutputXor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Xor - 11");
    }
}
