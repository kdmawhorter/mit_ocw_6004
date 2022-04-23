package bitwise;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class HalfAdderTest extends MitOcwTest {

    private HalfAdder halfAdder;
    private HalfAdder halfAdder01;

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
        halfAdder.assignInputs(TEST_2_00);
        halfAdder.evaluate();
        assertArrayEquals(ANS_2_00, halfAdder.readOutputs(), "Half Adder 00 Test");

        halfAdder.assignInputs(TEST_2_01);
        halfAdder.evaluate();
        assertArrayEquals(ANS_2_01, halfAdder.readOutputs(), "Half Adder 01 Test");

        halfAdder.assignInputs(TEST_2_10);
        halfAdder.evaluate();
        assertArrayEquals(ANS_2_01, halfAdder.readOutputs(), "Half Adder 10 Test");

        halfAdder.assignInputs(TEST_2_11);
        halfAdder.evaluate();
        assertArrayEquals(ANS_2_10, halfAdder.readOutputs(), "Half Adder 11 Test");

        halfAdder01.evaluate();
        assertArrayEquals(ANS_2_01, halfAdder01.readOutputs(), "Half Adder 01 External Test");
    }
}
