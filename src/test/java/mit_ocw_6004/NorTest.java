package mit_ocw_6004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static mit_ocw_6004.DigitalCircuit.GND;
import static mit_ocw_6004.DigitalCircuit.VDD;

public class NorTest {

    private Nor nor1Bit;
    private Nor nor2Bit;
    private Nor nor4Bit;
    private Nor externalOutputNor2Bit;

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
        nor1Bit = new Nor("1 Bit Nor", 1);
        nor2Bit = new Nor("2 Bit Nor", 2);
        nor4Bit = new Nor("4 Bit Nor", 4);

        externalOutput = new CircuitNode("External Output");
        externalOutputNor2Bit = new Nor("External Output 2 Bit Nor", 2, externalOutput);
    }

    @Test
    void norFuncTest() {
        //Nor 1 Tests
        nor1Bit.assignInputs(INPUT_1_0);
        nor1Bit.evaluate();
        assertTrue(nor1Bit.readOutput(0), "1 Bit Nor - 0");

        nor1Bit.assignInputs(INPUT_1_1);
        nor1Bit.evaluate();
        assertFalse(nor1Bit.readOutput(0), "1 Bit Nor - 1");

        //Nor 2 Tests
        nor2Bit.assignInputs(INPUT_2_00);
        nor2Bit.evaluate();
        assertTrue(nor2Bit.readOutput(0), "2 Bit Nor - 00");

        nor2Bit.assignInputs(INPUT_2_01);
        nor2Bit.evaluate();
        assertFalse(nor2Bit.readOutput(0), "2 Bit Nor - 01");

        nor2Bit.assignInputs(INPUT_2_10);
        nor2Bit.evaluate();
        assertFalse(nor2Bit.readOutput(0), "2 Bit Nor - 10");

        nor2Bit.assignInputs(INPUT_2_11);
        nor2Bit.evaluate();
        assertFalse(nor2Bit.readOutput(0), "2 Bit Nor - 11");

        //Nor 4 Tests
        nor4Bit.assignInputs(INPUT_3_0000);
        nor4Bit.evaluate();
        assertTrue(nor4Bit.readOutput(0), "4 Bit Nor - 0000");

        nor4Bit.assignInputs(INPUT_3_0001);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0001");

        nor4Bit.assignInputs(INPUT_3_0010);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0010");

        nor4Bit.assignInputs(INPUT_3_0011);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0011");

        nor4Bit.assignInputs(INPUT_3_0100);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0100");

        nor4Bit.assignInputs(INPUT_3_0101);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0101");

        nor4Bit.assignInputs(INPUT_3_0110);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0110");

        nor4Bit.assignInputs(INPUT_3_0111);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 0111");

        nor4Bit.assignInputs(INPUT_3_1000);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1000");

        nor4Bit.assignInputs(INPUT_3_1001);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1001");

        nor4Bit.assignInputs(INPUT_3_1010);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1010");

        nor4Bit.assignInputs(INPUT_3_1011);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1011");

        nor4Bit.assignInputs(INPUT_3_1100);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1100");

        nor4Bit.assignInputs(INPUT_3_1101);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1101");

        nor4Bit.assignInputs(INPUT_3_1110);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1110");

        nor4Bit.assignInputs(INPUT_3_1111);
        nor4Bit.evaluate();
        assertFalse(nor4Bit.readOutput(0), "4 Bit Nor - 1111");

        //External Output Nor 2 Tests
        externalOutputNor2Bit.assignInputs(INPUT_2_00);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "External Output 2 Bit Nor - 00");

        externalOutputNor2Bit.assignInputs(INPUT_2_01);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nor - 01");

        externalOutputNor2Bit.assignInputs(INPUT_2_10);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nor - 10");

        externalOutputNor2Bit.assignInputs(INPUT_2_11);
        externalOutputNor2Bit.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "External Output 2 Bit Nor - 11");
    }
}
