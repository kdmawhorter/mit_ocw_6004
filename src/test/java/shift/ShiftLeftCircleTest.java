package shift;

import core_architecture.CircuitNode;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class ShiftLeftCircleTest extends MitOcwTest {

    private ShiftLeftCircle shift8Bit;

    private static final CircuitNode[] TEST_0x80 = {VDD, GND, GND, VDD, GND, GND, GND, VDD, GND, VDD, VDD};
    private static final CircuitNode[] TEST_0x08 = {GND, GND, GND, VDD, VDD, GND, GND, VDD, GND, GND, GND};
    private static final CircuitNode[] TEST_0x04 = {GND, GND, VDD, VDD, GND, GND, VDD, GND, GND, GND, VDD};
    private static final CircuitNode[] TEST_0x01 = {VDD, VDD, GND, GND, VDD, GND, GND, GND, GND, GND, GND};

    private static final Boolean[] TEST_0x80_ANS = {false, false, false, true, true, false, false, true};
    private static final Boolean[] TEST_0x08_ANS = {false, false, true, true, false, false, true, false};
    private static final Boolean[] TEST_0x04_ANS = {true, true, false, false, true, false, false, false};
    private static final Boolean[] TEST_0x01_ANS = {true, false, false, true, false, false, false, true};

    @BeforeEach
    void init() {
        shift8Bit = new ShiftLeftCircle("8 Bit Circle Left Shift Test", 8);
    }

    @Test
    void shiftLeftCircleTest() {
        // Transistor Count Tests
        assertEquals(6+8*(6+2*8*4+16), shift8Bit.getTransistorCount(),
                "8 Bit Shift Left Circle Transistor Count Test");

        //Shift Testing
        shift8Bit.assignInputs(TEST_0x80);
        shift8Bit.evaluate();
        assertArrayEquals(TEST_0x80_ANS, shift8Bit.readOutputs(), "0x80 Shift Test");

        shift8Bit.assignInputs(TEST_0x08);
        shift8Bit.evaluate();
        assertArrayEquals(TEST_0x08_ANS, shift8Bit.readOutputs(), "0x08 Shift Test");

        shift8Bit.assignInputs(TEST_0x04);
        shift8Bit.evaluate();
        assertArrayEquals(TEST_0x04_ANS, shift8Bit.readOutputs(), "0x04 Shift Test");

        shift8Bit.assignInputs(TEST_0x01);
        shift8Bit.evaluate();
        assertArrayEquals(TEST_0x01_ANS, shift8Bit.readOutputs(), "0x01 Shift Test");

    }
}
