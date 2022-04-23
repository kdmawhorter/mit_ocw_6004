package bitwise;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseNorTest extends MitOcwTest {

    private BitwiseNor nor1Bit;
    private BitwiseNor nor2Bit;
    private BitwiseNor nor1BitExternal;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        nor1Bit = new BitwiseNor("1 Bit Nor", 1);
        nor2Bit = new BitwiseNor("2 Bit Nor", 2);
        nor1BitExternal = new BitwiseNor("1 Bit External Nor", 1);

        externalOutput = new CircuitNode("External Output");
        nor1BitExternal.assignOutput(0, externalOutput);
    }

    @Test
    void bitwiseNorTest() {
        // Transistor Count Tests
        assertEquals(4, nor1Bit.getTransistorCount(), "1 Bit Bitwise Nor Transistor Count Testing");
        assertEquals(8, nor2Bit.getTransistorCount(), "2 Bit Bitwise Nor Transistor Count Testing");

        // 1 Bit Bitwise Nor Test
        nor1Bit.assignInputs(TEST_2_00);
        nor1Bit.evaluate();
        assertTrue(nor1Bit.readOutput(0), "1 Bit Bitwise Nor 00 Test");

        nor1Bit.assignInputs(TEST_2_01);
        nor1Bit.evaluate();
        assertFalse(nor1Bit.readOutput(0), "1 Bit Bitwise Nor 01 Test");

        nor1Bit.assignInputs(TEST_2_10);
        nor1Bit.evaluate();
        assertFalse(nor1Bit.readOutput(0), "1 Bit Bitwise Nor 10 Test");

        nor1Bit.assignInputs(TEST_2_11);
        nor1Bit.evaluate();
        assertFalse(nor1Bit.readOutput(0), "1 Bit Bitwise Nor 11 Test");

        // 2 Bit Bitwise Nor Test
        nor2Bit.assignInputs(TEST_4_0000);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0000 Test");

        nor2Bit.assignInputs(TEST_4_0001);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_10, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0001 Test");

        nor2Bit.assignInputs(TEST_4_0010);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_01, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0010 Test");

        nor2Bit.assignInputs(TEST_4_0011);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0011 Test");

        nor2Bit.assignInputs(TEST_4_0100);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_10, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0100 Test");

        nor2Bit.assignInputs(TEST_4_0101);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_10, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0101 Test");

        nor2Bit.assignInputs(TEST_4_0110);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0110 Test");

        nor2Bit.assignInputs(TEST_4_0111);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 0111 Test");

        nor2Bit.assignInputs(TEST_4_1000);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_01, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1000 Test");

        nor2Bit.assignInputs(TEST_4_1001);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1001 Test");

        nor2Bit.assignInputs(TEST_4_1010);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_01, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1010 Test");

        nor2Bit.assignInputs(TEST_4_1011);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1011 Test");

        nor2Bit.assignInputs(TEST_4_1100);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1100 Test");

        nor2Bit.assignInputs(TEST_4_1101);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1101 Test");

        nor2Bit.assignInputs(TEST_4_1110);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1110 Test");

        nor2Bit.assignInputs(TEST_4_1111);
        nor2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nor2Bit.readOutputs(), "2 Bit Bitwise Nor 1111 Test");

        // 1 Bit External Output Nor Test
        nor1BitExternal.assignInputs(TEST_2_00);
        nor1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Nor 00 Test");

        nor1BitExternal.assignInputs(TEST_2_01);
        nor1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External Nor 01 Test");

        nor1BitExternal.assignInputs(TEST_2_10);
        nor1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External Nor 10 Test");

        nor1BitExternal.assignInputs(TEST_2_11);
        nor1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External Nor 11 Test");
    }
}
