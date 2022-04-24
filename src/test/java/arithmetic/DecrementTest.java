package arithmetic;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecrementTest extends MitOcwTest {
    
    private Decrement decrement3Bit;
    private Decrement decrement4Bit;

    private static final Boolean[] F_DEC_ANS = {true, true, true, false, false};
    private static final Boolean[] EIGHT_DEC_ANS = {false, true, true, true, false};
    private static final Boolean[] ZERO_DEC_ANS = {true, true, true, true, true};

    @BeforeEach
    void init() {
        decrement3Bit = new Decrement("3 Bit Decrement Test", 3);
        decrement4Bit = new Decrement("5 Bit Decrement Test", 4);
    }
    
    @Test
    void decrementTest() {
        // Transistor Count Tests
        assertEquals(24*3, decrement3Bit.getTransistorCount(), "2 Bit Incrementer Transistor Count Test");
        assertEquals(24*4, decrement4Bit.getTransistorCount(), "4 Bit Incrementer Transistor Count Test");

        // Increment Testing
        decrement4Bit.assignInputs(TEST_4_1111);
        decrement4Bit.evaluate();
        assertArrayEquals(F_DEC_ANS, decrement4Bit.readOutputs(), "Decrement 15");

        decrement4Bit.assignInputs(TEST_4_1000);
        decrement4Bit.evaluate();
        assertArrayEquals(EIGHT_DEC_ANS, decrement4Bit.readOutputs(), "Decrement 7");

        decrement4Bit.assignInputs(TEST_4_0000);
        decrement4Bit.evaluate();
        assertArrayEquals(ZERO_DEC_ANS, decrement4Bit.readOutputs(), "Decrement 0");
    }
}
