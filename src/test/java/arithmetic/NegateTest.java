package arithmetic;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class NegateTest {

    private Negate negate;

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
        negate = new Negate("Negate Test 5 Bit", 5);
    }

    @Test
    void negateTest() {
        negate.assignInputs(NEGATE_FOUR_TEST);
        negate.evaluate();
        assertArrayEquals(NEGATE_FOUR_TEST_ANS, negate.readOutputs(), "Negate 4 = -4 Test");

        negate.assignInputs(NEGATE_NFOUR_TEST);
        negate.evaluate();
        assertArrayEquals(NEGATE_NFOUR_TEST_ANS, negate.readOutputs(), "Negate -4 = 4 Test");

        negate.assignInputs(NEGATE_ZERO_TEST);
        negate.evaluate();
        assertArrayEquals(NEGATE_ZERO_TEST_ANS, negate.readOutputs(), "Negate 0 = 0 Test");

        negate.assignInputs(NEGATE_MIN_TEST);
        negate.evaluate();
        assertArrayEquals(NEGATE_MIN_TEST_ANS, negate.readOutputs(), "Negate -16 = -16 Test");

        negate.assignInputs(NEGATE_MAX_TEST);
        negate.evaluate();
        assertArrayEquals(NEGATE_MAX_TEST_ANS, negate.readOutputs(), "Negate 15 = -15 Test");



    }
}
