package shift;

import core_architecture.CircuitNode;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class ShiftRightSignedTest extends MitOcwTest {

    private ShiftRightSigned shift8Bit;

    private static final CircuitNode[] TEST_0x80 = {VDD, GND, GND, GND, GND, GND, GND, GND, GND, VDD, VDD};
    private static final CircuitNode[] TEST_0x08 = {VDD, VDD, VDD, VDD, VDD, GND, GND, GND, GND, GND, GND};
    private static final CircuitNode[] TEST_0x04 = {VDD, VDD, VDD, VDD, VDD, VDD, GND, GND, GND, GND, VDD};
    private static final CircuitNode[] TEST_0x01 = {VDD, VDD, VDD, VDD, VDD, VDD, VDD, VDD, GND, GND, GND};

    private static final Boolean[] TEST_0x80_ANS = {true, true, true, true, true, false, false, false};
    private static final Boolean[] TEST_0x08_ANS = {true, true, true, true, true, true, false, false};
    private static final Boolean[] TEST_0x04_ANS = {true, true, true, true, true, true, true, true};
    private static final Boolean[] TEST_0x01_ANS = {true, true, true, true, true, true, true, true};

    @BeforeEach
    void init() {
        shift8Bit = new ShiftRightSigned("8 Bit Signed Right Shift Test", 8);
    }

    @Test
    void shiftRightSignedTest() {
        // Transistor Count Tests
        assertEquals(6+8*(6+2*8*4+16), shift8Bit.getTransistorCount(),
                "8 Bit Shift Right Signed Transistor Count Test");

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
