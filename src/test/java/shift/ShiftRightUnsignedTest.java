package shift;

import core_architecture.CircuitNode;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class ShiftRightUnsignedTest extends MitOcwTest {

    private ShiftRightUnsigned shift8Bit;

    private static final CircuitNode[] TEST_0x80 = {VDD, GND, GND, GND, GND, GND, GND, GND, GND, VDD, VDD};
    private static final CircuitNode[] TEST_0x08 = {GND, GND, GND, GND, VDD, GND, GND, GND, GND, GND, GND};
    private static final CircuitNode[] TEST_0x04 = {GND, GND, GND, GND, GND, VDD, GND, GND, GND, GND, VDD};
    private static final CircuitNode[] TEST_0x01 = {GND, GND, GND, GND, GND, GND, GND, VDD, GND, GND, GND};

    private static final Boolean[] TEST_0x80_ANS = {false, false, false, false, true, false, false, false};
    private static final Boolean[] TEST_0x08_ANS = {false, false, false, false, false, true, false, false};
    private static final Boolean[] TEST_0x04_ANS = {false, false, false, false, false, false, false, true};
    private static final Boolean[] TEST_0x01_ANS = {false, false, false, false, false, false, false, false};

    @BeforeEach
    void init() {
        shift8Bit = new ShiftRightUnsigned("8 Bit Unsigned Right Shift Test", 8);
    }

    @Test
    void shiftRightUnsignedTest() {
        // Transistor Count Tests
        assertEquals(6+8*(6+2*8*4+16), shift8Bit.getTransistorCount(),
                "8 Bit Shift Right Unsigned Transistor Count Test");

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
