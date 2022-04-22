package bitwise;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class HalfAdderTest {

    private HalfAdder halfAdder;
    private HalfAdder halfAdder01;

    private final static CircuitNode[] TEST_00 = {GND, GND};
    private final static CircuitNode[] TEST_01 = {GND, VDD};
    private final static CircuitNode[] TEST_10 = {VDD, GND};
    private final static CircuitNode[] TEST_11 = {VDD, VDD};

    private final static Boolean[] ANS_00 = {false, false};
    private final static Boolean[] ANS_01 = {false, true};
    private final static Boolean[] ANS_10 = {true, false};

    @BeforeEach
    void init() {
        halfAdder = new HalfAdder("Half Adder Test 00");
        halfAdder01 = new HalfAdder("Half Adder Test 01", GND, VDD);
    }

    @Test
    void halfAdderTest() {
        //Transistor Count Test
        assertEquals(22, halfAdder.getTransistorCount());

        //Half Adder Test
        halfAdder.assignInputs(TEST_00);
        halfAdder.evaluate();
        assertArrayEquals(ANS_00, halfAdder.readOutputs(), "Half Adder 00 Test");

        halfAdder.assignInputs(TEST_01);
        halfAdder.evaluate();
        assertArrayEquals(ANS_01, halfAdder.readOutputs(), "Half Adder 01 Test");

        halfAdder.assignInputs(TEST_10);
        halfAdder.evaluate();
        assertArrayEquals(ANS_01, halfAdder.readOutputs(), "Half Adder 10 Test");

        halfAdder.assignInputs(TEST_11);
        halfAdder.evaluate();
        assertArrayEquals(ANS_10, halfAdder.readOutputs(), "Half Adder 11 Test");

        halfAdder01.evaluate();
        assertArrayEquals(ANS_01, halfAdder01.readOutputs(), "Half Adder 01 External Test");
    }
}
