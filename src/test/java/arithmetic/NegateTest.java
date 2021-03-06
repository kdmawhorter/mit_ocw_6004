package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class NegateTest extends MitOcwTest {

    private Negate neg3Bit;
    private Negate neg5Bit;

    private static final CircuitNode[] NEGATE_FOUR_TEST = { GND, GND, VDD, GND, GND };
    private static final CircuitNode[] NEGATE_NFOUR_TEST = { VDD, VDD, VDD, GND, GND };
    private static final CircuitNode[] NEGATE_ZERO_TEST = { GND, GND, GND, GND, GND };
    private static final CircuitNode[] NEGATE_MIN_TEST = { VDD, GND, GND, GND, GND };
    private static final CircuitNode[] NEGATE_MAX_TEST = { GND, VDD, VDD, VDD, VDD };


    private static final Boolean[] NEGATE_FOUR_TEST_ANS = { true, true, true, false, false };
    private static final Boolean[] NEGATE_NFOUR_TEST_ANS = { false, false, true, false, false };
    private static final Boolean[] NEGATE_ZERO_TEST_ANS = { false, false, false, false, false };
    private static final Boolean[] NEGATE_MIN_TEST_ANS = { true, false, false, false, false };
    private static final Boolean[] NEGATE_MAX_TEST_ANS = { true, false, false, false, true };


    @BeforeEach
    void init() {
        neg3Bit = new Negate("Negate Test 3 Bit", 3);
        neg5Bit = new Negate("Negate Test 5 Bit", 5);
    }

    @Test
    void negateTest() {
        // Transistor Count Tests
        assertEquals(24*3, neg3Bit.getTransistorCount(), "2 Bit Incrementer Transistor Count Test");
        assertEquals(24*5, neg5Bit.getTransistorCount(), "4 Bit Incrementer Transistor Count Test");

        // Negate test
        neg5Bit.assignInputs(NEGATE_FOUR_TEST);
        neg5Bit.evaluate();
        assertArrayEquals(NEGATE_FOUR_TEST_ANS, neg5Bit.readOutputs(), "Negate 4 = -4 Test");

        neg5Bit.assignInputs(NEGATE_NFOUR_TEST);
        neg5Bit.evaluate();
        assertArrayEquals(NEGATE_NFOUR_TEST_ANS, neg5Bit.readOutputs(), "Negate -4 = 4 Test");

        neg5Bit.assignInputs(NEGATE_ZERO_TEST);
        neg5Bit.evaluate();
        assertArrayEquals(NEGATE_ZERO_TEST_ANS, neg5Bit.readOutputs(), "Negate 0 = 0 Test");

        neg5Bit.assignInputs(NEGATE_MIN_TEST);
        neg5Bit.evaluate();
        assertArrayEquals(NEGATE_MIN_TEST_ANS, neg5Bit.readOutputs(), "Negate -16 = -16 Test");

        neg5Bit.assignInputs(NEGATE_MAX_TEST);
        neg5Bit.evaluate();
        assertArrayEquals(NEGATE_MAX_TEST_ANS, neg5Bit.readOutputs(), "Negate 15 = -15 Test");



    }
}
