package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class AndTest {

    private AndGate and1Bit;
    private AndGate and2Bit;
    private AndGate and4Bit;
    private AndGate externalOutputAnd2Bit;

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
        and1Bit = new AndGate("1 Bit And", 1);
        and2Bit = new AndGate("2 Bit And", 2);
        and4Bit = new AndGate("4 Bit And", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputAnd2Bit = new AndGate("External Output 2 Bit And", 2, externalOutput);
    }

    @Test
    void andFuncTest() {
        // Transistor Count Tests
        assertEquals(4, and1Bit.getTransistorCount(), "1 Bit And Transistor Count Testing");
        assertEquals(6, and2Bit.getTransistorCount(), "2 Bit And Transistor Count Testing");
        assertEquals(10, and4Bit.getTransistorCount(), "4 Bit And Transistor Count Testing");

        //And 1 Tests
        and1Bit.assignInputs(INPUT_1_0);
        and1Bit.evaluate();
        assertFalse(and1Bit.readOutput(0), "1 Bit And - 0");

        and1Bit.assignInputs(INPUT_1_1);
        and1Bit.evaluate();
        assertTrue(and1Bit.readOutput(0), "1 Bit And - 1");

        //And 2 Tests
        and2Bit.assignInputs(INPUT_2_00);
        and2Bit.evaluate();
        assertFalse(and2Bit.readOutput(0), "2 Bit And - 00");

        and2Bit.assignInputs(INPUT_2_01);
        and2Bit.evaluate();
        assertFalse(and2Bit.readOutput(0), "2 Bit And - 01");

        and2Bit.assignInputs(INPUT_2_10);
        and2Bit.evaluate();
        assertFalse(and2Bit.readOutput(0), "2 Bit And - 10");

        and2Bit.assignInputs(INPUT_2_11);
        and2Bit.evaluate();
        assertTrue(and2Bit.readOutput(0), "2 Bit And - 11");

        //And 4 Tests
        and4Bit.assignInputs(INPUT_3_0000);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0000");

        and4Bit.assignInputs(INPUT_3_0001);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0001");

        and4Bit.assignInputs(INPUT_3_0010);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0010");

        and4Bit.assignInputs(INPUT_3_0011);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0011");

        and4Bit.assignInputs(INPUT_3_0100);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0100");

        and4Bit.assignInputs(INPUT_3_0101);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0101");

        and4Bit.assignInputs(INPUT_3_0110);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0110");

        and4Bit.assignInputs(INPUT_3_0111);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 0111");

        and4Bit.assignInputs(INPUT_3_1000);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1000");

        and4Bit.assignInputs(INPUT_3_1001);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1001");

        and4Bit.assignInputs(INPUT_3_1010);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1010");

        and4Bit.assignInputs(INPUT_3_1011);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1011");

        and4Bit.assignInputs(INPUT_3_1100);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1100");

        and4Bit.assignInputs(INPUT_3_1101);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1101");

        and4Bit.assignInputs(INPUT_3_1110);
        and4Bit.evaluate();
        assertFalse(and4Bit.readOutput(0), "4 Bit And - 1110");

        and4Bit.assignInputs(INPUT_3_1111);
        and4Bit.evaluate();
        assertTrue(and4Bit.readOutput(0), "4 Bit And - 1111");

        //External Output And 2 Tests
        externalOutputAnd2Bit.assignInputs(INPUT_2_00);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit And - 00");

        externalOutputAnd2Bit.assignInputs(INPUT_2_01);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit And - 01");

        externalOutputAnd2Bit.assignInputs(INPUT_2_10);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit And - 10");

        externalOutputAnd2Bit.assignInputs(INPUT_2_11);
        externalOutputAnd2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit And - 11");
    }
}
