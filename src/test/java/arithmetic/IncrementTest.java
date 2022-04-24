package arithmetic;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IncrementTest extends MitOcwTest {

    private Increment inc2Bit;
    private Increment inc4Bit;

    private static final Boolean[] ZERO_INC_ANS = {false, false, false, true, false};
    private static final Boolean[] SEVEN_INC_ANS = {true, false, false, false, false};
    private static final Boolean[] F_INC_ANS = {false, false, false, false, true};


    @BeforeEach
    void init() {
        inc2Bit = new Increment("2 Bit Increment Test", 2);
        inc4Bit = new Increment("4 Bit Increment Test", 4);
    }


    @Test
    void incTest() {
        // Transistor Count Tests
        assertEquals(22*2, inc2Bit.getTransistorCount(), "2 Bit Incrementer Transistor Count Test");
        assertEquals(22*4, inc4Bit.getTransistorCount(), "4 Bit Incrementer Transistor Count Test");

        // Increment Testing
        inc4Bit.assignInputs(TEST_4_0000);
        inc4Bit.evaluate();
        assertArrayEquals(ZERO_INC_ANS, inc4Bit.readOutputs(), "Increment 0");

        inc4Bit.assignInputs(TEST_4_0111);
        inc4Bit.evaluate();
        assertArrayEquals(SEVEN_INC_ANS, inc4Bit.readOutputs(), "Increment 7");

        inc4Bit.assignInputs(TEST_4_1111);
        inc4Bit.evaluate();
        assertArrayEquals(F_INC_ANS, inc4Bit.readOutputs(), "Increment 15");
    }
}
