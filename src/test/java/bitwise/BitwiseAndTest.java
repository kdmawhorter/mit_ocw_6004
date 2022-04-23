package bitwise;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseAndTest extends MitOcwTest {

    private BitwiseAnd and1Bit;
    private BitwiseAnd and2Bit;
    private BitwiseAnd and4Bit;
    private BitwiseAnd and1BitExternal;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        and1Bit = new BitwiseAnd("1 Bit And", 1);
        and2Bit = new BitwiseAnd("2 Bit And", 2);
        and4Bit = new BitwiseAnd("4 Bit And", 4);
        and1BitExternal = new BitwiseAnd("1 Bit External And", 1);

        externalOutput = new CircuitNode("External Output");
        and1BitExternal.assignOutput(0, externalOutput);
    }

    @Test
    void bitwiseAndTest() {
        // Transistor Count Tests
        assertEquals(6, and1Bit.getTransistorCount(), "1 Bit Bitwise And Transistor Count Testing");
        assertEquals(12, and2Bit.getTransistorCount(), "2 Bit Bitwise And Transistor Count Testing");

        // 1 Bit Bitwise And Test
        and1Bit.assignInputs(TEST_2_00);
        and1Bit.evaluate();
        assertFalse(and1Bit.readOutput(0), "1 Bit Bitwise And 00 Test");

        and1Bit.assignInputs(TEST_2_01);
        and1Bit.evaluate();
        assertFalse(and1Bit.readOutput(0), "1 Bit Bitwise And 01 Test");

        and1Bit.assignInputs(TEST_2_10);
        and1Bit.evaluate();
        assertFalse(and1Bit.readOutput(0), "1 Bit Bitwise And 10 Test");

        and1Bit.assignInputs(TEST_2_11);
        and1Bit.evaluate();
        assertTrue(and1Bit.readOutput(0), "1 Bit Bitwise And 11 Test");

        // 2 Bit Bitwise And Test
        and2Bit.assignInputs(TEST_4_0000);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 0000 Test");

        and2Bit.assignInputs(TEST_4_0001);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 0001 Test");

        and2Bit.assignInputs(TEST_4_0010);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 0010 Test");

        and2Bit.assignInputs(TEST_4_0011);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 0011 Test");

        and2Bit.assignInputs(TEST_4_0100);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 0100 Test");

        and2Bit.assignInputs(TEST_4_0101);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_01, and2Bit.readOutputs(), "2 Bit Bitwise And 0101 Test");

        and2Bit.assignInputs(TEST_4_0110);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 0110 Test");

        and2Bit.assignInputs(TEST_4_0111);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_01, and2Bit.readOutputs(), "2 Bit Bitwise And 0111 Test");

        and2Bit.assignInputs(TEST_4_1000);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 1000 Test");

        and2Bit.assignInputs(TEST_4_1001);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 1001 Test");

        and2Bit.assignInputs(TEST_4_1010);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_10, and2Bit.readOutputs(), "2 Bit Bitwise And 1010 Test");

        and2Bit.assignInputs(TEST_4_1011);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_10, and2Bit.readOutputs(), "2 Bit Bitwise And 1011 Test");

        and2Bit.assignInputs(TEST_4_1100);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_00, and2Bit.readOutputs(), "2 Bit Bitwise And 1100 Test");

        and2Bit.assignInputs(TEST_4_1101);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_01, and2Bit.readOutputs(), "2 Bit Bitwise And 1101 Test");

        and2Bit.assignInputs(TEST_4_1110);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_10, and2Bit.readOutputs(), "2 Bit Bitwise And 1110 Test");

        and2Bit.assignInputs(TEST_4_1111);
        and2Bit.evaluate();
        assertArrayEquals(ANS_2_11, and2Bit.readOutputs(), "2 Bit Bitwise And 1111 Test");

        // 1 Bit External Output And Test
        and1BitExternal.assignInputs(TEST_2_00);
        and1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External And 00 Test");

        and1BitExternal.assignInputs(TEST_2_01);
        and1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External And 01 Test");

        and1BitExternal.assignInputs(TEST_2_10);
        and1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External And 10 Test");

        and1BitExternal.assignInputs(TEST_2_11);
        and1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External And 11 Test");
        
        // 4 Bit And Test
        and4Bit.assignInputs(generateInputsArray(0xF, 0xC, 4));
        and4Bit.evaluate();
        assertArrayEquals(generateBooleanArray(0xC, 4), and4Bit.readOutputs(), "4 Bit 0x74 Test");

        and4Bit.assignInputs(generateInputsArray(0xC, 0xF, 4));
        and4Bit.evaluate();
        assertArrayEquals(generateBooleanArray(0xC, 4), and4Bit.readOutputs(), "4 Bit 0x74 Test");
    }
}
