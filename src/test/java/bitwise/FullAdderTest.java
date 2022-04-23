package bitwise;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;

public class FullAdderTest extends MitOcwTest {

    private FullAdder fullAdder;
    private FullAdder fullAdder111;

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
        fullAdder.assignInputs(TEST_3_000);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_00, fullAdder.readOutputs(), "Full Adder 000 Test");

        fullAdder.assignInputs(TEST_3_001);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_01, fullAdder.readOutputs(), "Full Adder 001 Test");

        fullAdder.assignInputs(TEST_3_010);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_01, fullAdder.readOutputs(), "Full Adder 010 Test");

        fullAdder.assignInputs(TEST_3_011);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_10, fullAdder.readOutputs(), "Full Adder 011 Test");

        fullAdder.assignInputs(TEST_3_100);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_01, fullAdder.readOutputs(), "Full Adder 100 Test");

        fullAdder.assignInputs(TEST_3_101);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_10, fullAdder.readOutputs(), "Full Adder 101 Test");

        fullAdder.assignInputs(TEST_3_110);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_10, fullAdder.readOutputs(), "Full Adder 110 Test");

        fullAdder.assignInputs(TEST_3_111);
        fullAdder.evaluate();
        assertArrayEquals(ANS_2_11, fullAdder.readOutputs(), "Full Adder 111 Test");

        fullAdder111.evaluate();
        assertArrayEquals(ANS_2_11, fullAdder111.readOutputs(), "Full Adder 111 External Test");
    }
}
