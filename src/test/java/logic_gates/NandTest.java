package logic_gates;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class NandTest {

    private Nand nand1Bit;
    private Nand nand2Bit;
    private Nand nand4Bit;
    private Nand externalOutputNand2Bit;

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
        nand1Bit = new Nand("1 Bit Nand", 1);
        nand2Bit = new Nand("2 Bit Nand", 2);
        nand4Bit = new Nand("4 Bit Nand", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputNand2Bit = new Nand("External Output 2 Bit Nand", 2, externalOutput);
    }

    @Test
    void nandFuncTest() {
        //Nand 1 Tests
        nand1Bit.assignInputs(INPUT_1_0);
        nand1Bit.evaluate();
        assertTrue(nand1Bit.readOutput(0), "1 Bit Nand - 0");

        nand1Bit.assignInputs(INPUT_1_1);
        nand1Bit.evaluate();
        assertFalse(nand1Bit.readOutput(0), "1 Bit Nand - 1");

        //Nand 2 Tests
        nand2Bit.assignInputs(INPUT_2_00);
        nand2Bit.evaluate();
        assertTrue(nand2Bit.readOutput(0), "2 Bit Nand - 00");

        nand2Bit.assignInputs(INPUT_2_01);
        nand2Bit.evaluate();
        assertTrue(nand2Bit.readOutput(0), "2 Bit Nand - 01");

        nand2Bit.assignInputs(INPUT_2_10);
        nand2Bit.evaluate();
        assertTrue(nand2Bit.readOutput(0), "2 Bit Nand - 10");

        nand2Bit.assignInputs(INPUT_2_11);
        nand2Bit.evaluate();
        assertFalse(nand2Bit.readOutput(0), "2 Bit Nand - 11");

        //Nand 4 Tests
        nand4Bit.assignInputs(INPUT_3_0000);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0000");

        nand4Bit.assignInputs(INPUT_3_0001);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0001");

        nand4Bit.assignInputs(INPUT_3_0010);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0010");

        nand4Bit.assignInputs(INPUT_3_0011);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0011");

        nand4Bit.assignInputs(INPUT_3_0100);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0100");

        nand4Bit.assignInputs(INPUT_3_0101);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0101");

        nand4Bit.assignInputs(INPUT_3_0110);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0110");

        nand4Bit.assignInputs(INPUT_3_0111);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 0111");

        nand4Bit.assignInputs(INPUT_3_1000);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1000");

        nand4Bit.assignInputs(INPUT_3_1001);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1001");

        nand4Bit.assignInputs(INPUT_3_1010);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1010");

        nand4Bit.assignInputs(INPUT_3_1011);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1011");

        nand4Bit.assignInputs(INPUT_3_1100);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1100");

        nand4Bit.assignInputs(INPUT_3_1101);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1101");

        nand4Bit.assignInputs(INPUT_3_1110);
        nand4Bit.evaluate();
        assertTrue(nand4Bit.readOutput(0), "4 Bit Nand - 1110");

        nand4Bit.assignInputs(INPUT_3_1111);
        nand4Bit.evaluate();
        assertFalse(nand4Bit.readOutput(0), "4 Bit Nand - 1111");

        //External Output Nand 2 Tests
        externalOutputNand2Bit.assignInputs(INPUT_2_00);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nand - 00");

        externalOutputNand2Bit.assignInputs(INPUT_2_01);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nand - 01");

        externalOutputNand2Bit.assignInputs(INPUT_2_10);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nand - 10");

        externalOutputNand2Bit.assignInputs(INPUT_2_11);
        externalOutputNand2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nand - 11");
    }
}
