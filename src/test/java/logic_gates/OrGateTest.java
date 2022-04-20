package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class OrGateTest {

    private OrGate or1Bit;
    private OrGate or2Bit;
    private OrGate or4Bit;
    private OrGate externalOutputOr2Bit;
    
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
        or1Bit.assignInputs(INPUT_1_0);
        or1Bit.evaluate();
        assertFalse(or1Bit.readOutput(0), "1 Bit Or - 0");

        or1Bit.assignInputs(INPUT_1_1);
        or1Bit.evaluate();
        assertTrue(or1Bit.readOutput(0), "1 Bit Or - 1");

        //Or 2 Tests
        or2Bit.assignInputs(INPUT_2_00);
        or2Bit.evaluate();
        assertFalse(or2Bit.readOutput(0), "2 Bit Or - 00");

        or2Bit.assignInputs(INPUT_2_01);
        or2Bit.evaluate();
        assertTrue(or2Bit.readOutput(0), "2 Bit Or - 01");

        or2Bit.assignInputs(INPUT_2_10);
        or2Bit.evaluate();
        assertTrue(or2Bit.readOutput(0), "2 Bit Or - 10");

        or2Bit.assignInputs(INPUT_2_11);
        or2Bit.evaluate();
        assertTrue(or2Bit.readOutput(0), "2 Bit Or - 11");

        //Or 4 Tests
        or4Bit.assignInputs(INPUT_3_0000);
        or4Bit.evaluate();
        assertFalse(or4Bit.readOutput(0), "4 Bit Or - 0000");

        or4Bit.assignInputs(INPUT_3_0001);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0001");

        or4Bit.assignInputs(INPUT_3_0010);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0010");

        or4Bit.assignInputs(INPUT_3_0011);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0011");

        or4Bit.assignInputs(INPUT_3_0100);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0100");

        or4Bit.assignInputs(INPUT_3_0101);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0101");

        or4Bit.assignInputs(INPUT_3_0110);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0110");

        or4Bit.assignInputs(INPUT_3_0111);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 0111");

        or4Bit.assignInputs(INPUT_3_1000);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1000");

        or4Bit.assignInputs(INPUT_3_1001);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1001");

        or4Bit.assignInputs(INPUT_3_1010);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1010");

        or4Bit.assignInputs(INPUT_3_1011);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1011");

        or4Bit.assignInputs(INPUT_3_1100);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1100");

        or4Bit.assignInputs(INPUT_3_1101);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1101");

        or4Bit.assignInputs(INPUT_3_1110);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1110");

        or4Bit.assignInputs(INPUT_3_1111);
        or4Bit.evaluate();
        assertTrue(or4Bit.readOutput(0), "4 Bit Or - 1111");

        //External Output Or 2 Tests
        externalOutputOr2Bit.assignInputs(INPUT_2_00);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Or - 00");

        externalOutputOr2Bit.assignInputs(INPUT_2_01);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Or - 01");

        externalOutputOr2Bit.assignInputs(INPUT_2_10);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Or - 10");

        externalOutputOr2Bit.assignInputs(INPUT_2_11);
        externalOutputOr2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Or - 11");
    }
}
