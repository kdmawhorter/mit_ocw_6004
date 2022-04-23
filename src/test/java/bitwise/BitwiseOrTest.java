package bitwise;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseOrTest extends MitOcwTest {

    private BitwiseOr or1Bit;
    private BitwiseOr or2Bit;
    private BitwiseOr or1BitExternal;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        or1Bit = new BitwiseOr("1 Bit Or", 1);
        or2Bit = new BitwiseOr("2 Bit Or", 2);
        or1BitExternal = new BitwiseOr("1 Bit External Or", 1);

        externalOutput = new CircuitNode("External Output");
        or1BitExternal.assignOutput(0, externalOutput);
    }

    @Test
    void bitwiseOrTest() {
        // Transistor Count Tests
        assertEquals(6, or1Bit.getTransistorCount(), "1 Bit Bitwise Or Transistor Count Testing");
        assertEquals(12, or2Bit.getTransistorCount(), "2 Bit Bitwise Or Transistor Count Testing");

        // 1 Bit Or Test
        or1Bit.assignInputs(TEST_2_00);
        or1Bit.evaluate();
        assertFalse(or1Bit.readOutput(0), "1 Bit Bitwise Or 00 Test");

        or1Bit.assignInputs(TEST_2_01);
        or1Bit.evaluate();
        assertTrue(or1Bit.readOutput(0), "1 Bit Bitwise Or 01 Test");

        or1Bit.assignInputs(TEST_2_10);
        or1Bit.evaluate();
        assertTrue(or1Bit.readOutput(0), "1 Bit Bitwise Or 10 Test");

        or1Bit.assignInputs(TEST_2_11);
        or1Bit.evaluate();
        assertTrue(or1Bit.readOutput(0), "1 Bit Bitwise Or 11 Test");

        // 2 Bit Or Test
        or2Bit.assignInputs(TEST_4_0000);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_00, or2Bit.readOutputs(), "2 Bit Bitwise Or 0000 Test");

        or2Bit.assignInputs(TEST_4_0001);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_01, or2Bit.readOutputs(), "2 Bit Bitwise Or 0001 Test");

        or2Bit.assignInputs(TEST_4_0010);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_10, or2Bit.readOutputs(), "2 Bit Bitwise Or 0010 Test");

        or2Bit.assignInputs(TEST_4_0011);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 0011 Test");

        or2Bit.assignInputs(TEST_4_0100);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_01, or2Bit.readOutputs(), "2 Bit Bitwise Or 0100 Test");

        or2Bit.assignInputs(TEST_4_0101);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_01, or2Bit.readOutputs(), "2 Bit Bitwise Or 0101 Test");

        or2Bit.assignInputs(TEST_4_0110);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 0110 Test");

        or2Bit.assignInputs(TEST_4_0111);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 0111 Test");

        or2Bit.assignInputs(TEST_4_1000);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_10, or2Bit.readOutputs(), "2 Bit Bitwise Or 1000 Test");

        or2Bit.assignInputs(TEST_4_1001);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 1001 Test");

        or2Bit.assignInputs(TEST_4_1010);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_10, or2Bit.readOutputs(), "2 Bit Bitwise Or 1010 Test");

        or2Bit.assignInputs(TEST_4_1011);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 1011 Test");

        or2Bit.assignInputs(TEST_4_1100);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 1100 Test");

        or2Bit.assignInputs(TEST_4_1101);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 1101 Test");

        or2Bit.assignInputs(TEST_4_1110);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 1110 Test");

        or2Bit.assignInputs(TEST_4_1111);
        or2Bit.evaluate();
        assertArrayEquals(ANS_2_11, or2Bit.readOutputs(), "2 Bit Bitwise Or 1111 Test");

        // 1 Bit External Output Or Test
        or1BitExternal.assignInputs(TEST_2_00);
        or1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External Or 00 Test");

        or1BitExternal.assignInputs(TEST_2_01);
        or1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Or 01 Test");

        or1BitExternal.assignInputs(TEST_2_10);
        or1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Or 10 Test");

        or1BitExternal.assignInputs(TEST_2_11);
        or1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Or 11 Test");
    }
}
