package bitwise;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class FullAdderTest {

    private FullAdder fullAdder;
    private FullAdder fullAdder111;

    private static final CircuitNode[] TEST_000 = {GND, GND, GND};
    private static final CircuitNode[] TEST_001 = {GND, GND, VDD};
    private static final CircuitNode[] TEST_010 = {GND, VDD, GND};
    private static final CircuitNode[] TEST_011 = {GND, VDD, VDD};
    private static final CircuitNode[] TEST_100 = {VDD, GND, GND};
    private static final CircuitNode[] TEST_101 = {VDD, GND, VDD};
    private static final CircuitNode[] TEST_110 = {VDD, VDD, GND};
    private static final CircuitNode[] TEST_111 = {VDD, VDD, VDD};

    private static final Boolean[] ANS_00 = {false, false};
    private static final Boolean[] ANS_01 = {false, true};
    private static final Boolean[] ANS_10 = {true, false};
    private static final Boolean[] ANS_11 = {true, true};

    @BeforeEach
    void init() {
        fullAdder = new FullAdder("Full Adder Test");
        fullAdder111 = new FullAdder("Full Adder 111 Test", VDD, VDD, VDD);
    }

    @Test
    void fullAdderTest() {
        //Transistor Count Test
        assertEquals(56, fullAdder.getTransistorCount());

        //Full Adder Test
        fullAdder.assignInputs(TEST_000);
        fullAdder.evaluate();
        assertArrayEquals(ANS_00, fullAdder.readOutputs(), "Full Adder 000 Test");

        fullAdder.assignInputs(TEST_001);
        fullAdder.evaluate();
        assertArrayEquals(ANS_01, fullAdder.readOutputs(), "Full Adder 001 Test");

        fullAdder.assignInputs(TEST_010);
        fullAdder.evaluate();
        assertArrayEquals(ANS_01, fullAdder.readOutputs(), "Full Adder 010 Test");

        fullAdder.assignInputs(TEST_011);
        fullAdder.evaluate();
        assertArrayEquals(ANS_10, fullAdder.readOutputs(), "Full Adder 011 Test");

        fullAdder.assignInputs(TEST_100);
        fullAdder.evaluate();
        assertArrayEquals(ANS_01, fullAdder.readOutputs(), "Full Adder 100 Test");

        fullAdder.assignInputs(TEST_101);
        fullAdder.evaluate();
        assertArrayEquals(ANS_10, fullAdder.readOutputs(), "Full Adder 101 Test");

        fullAdder.assignInputs(TEST_110);
        fullAdder.evaluate();
        assertArrayEquals(ANS_10, fullAdder.readOutputs(), "Full Adder 110 Test");

        fullAdder.assignInputs(TEST_111);
        fullAdder.evaluate();
        assertArrayEquals(ANS_11, fullAdder.readOutputs(), "Full Adder 111 Test");

        fullAdder111.evaluate();
        assertArrayEquals(ANS_11, fullAdder111.readOutputs(), "Full Adder 111 External Test");
    }
}
