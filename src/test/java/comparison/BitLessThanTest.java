package comparison;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitLessThanTest extends MitOcwTest {
    
    private BitLessThan bitLessThan;
    
    @BeforeEach
    void init() {
        bitLessThan = new BitLessThan("BitLessThanTest");
    }
    
    @Test
    void bitLessThanTest() {
        assertEquals(32, bitLessThan.getTransistorCount(), "Bit Less Than Transistor Count Test");

        bitLessThan.assignInputs(TEST_4_0000);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_00, bitLessThan.readOutputs(), "Bit Less Than 0000 Test");

        bitLessThan.assignInputs(TEST_4_0001);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_01, bitLessThan.readOutputs(), "Bit Less Than 0001 Test");

        bitLessThan.assignInputs(TEST_4_0010);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_10, bitLessThan.readOutputs(), "Bit Less Than 0010 Test");

        bitLessThan.assignInputs(TEST_4_0011);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_11, bitLessThan.readOutputs(), "Bit Less Than 0011 Test");

        bitLessThan.assignInputs(TEST_4_0100);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_00, bitLessThan.readOutputs(), "Bit Less Than 0100 Test");

        bitLessThan.assignInputs(TEST_4_0101);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_10, bitLessThan.readOutputs(), "Bit Less Than 0101 Test");

        bitLessThan.assignInputs(TEST_4_0110);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_10, bitLessThan.readOutputs(), "Bit Less Than 0110 Test");

        bitLessThan.assignInputs(TEST_4_0111);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_10, bitLessThan.readOutputs(), "Bit Less Than 0111 Test");

        bitLessThan.assignInputs(TEST_4_1000);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_00, bitLessThan.readOutputs(), "Bit Less Than 1000 Test");

        bitLessThan.assignInputs(TEST_4_1001);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_00, bitLessThan.readOutputs(), "Bit Less Than 1001 Test");

        bitLessThan.assignInputs(TEST_4_1010);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_10, bitLessThan.readOutputs(), "Bit Less Than 1010 Test");

        bitLessThan.assignInputs(TEST_4_1011);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_10, bitLessThan.readOutputs(), "Bit Less Than 1011 Test");

        bitLessThan.assignInputs(TEST_4_1100);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_00, bitLessThan.readOutputs(), "Bit Less Than 1100 Test");

        bitLessThan.assignInputs(TEST_4_1101);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_01, bitLessThan.readOutputs(), "Bit Less Than 1101 Test");

        bitLessThan.assignInputs(TEST_4_1110);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_10, bitLessThan.readOutputs(), "Bit Less Than 1110 Test");

        bitLessThan.assignInputs(TEST_4_1111);
        bitLessThan.evaluate();
        assertArrayEquals(ANS_2_11, bitLessThan.readOutputs(), "Bit Less Than 1111 Test");
    }
}
