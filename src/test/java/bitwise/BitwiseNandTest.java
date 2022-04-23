package bitwise;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseNandTest extends MitOcwTest {

    private BitwiseNand nand1Bit;
    private BitwiseNand nand2Bit;
    private BitwiseNand nand1BitExternal;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        nand1Bit = new BitwiseNand("1 Bit Nand", 1);
        nand2Bit = new BitwiseNand("2 Bit Nand", 2);
        nand1BitExternal = new BitwiseNand("1 Bit External Nand", 1);

        externalOutput = new CircuitNode("External Output");
        nand1BitExternal.assignOutput(0, externalOutput);
    }

    @Test
    void bitwiseNandTest() {
        // Transistor Count Tests
        assertEquals(4, nand1Bit.getTransistorCount(), "1 Bit Bitwise Nand Transistor Count Testing");
        assertEquals(8, nand2Bit.getTransistorCount(), "2 Bit Bitwise Nand Transistor Count Testing");

        // 1 Bit Nand Test
        nand1Bit.assignInputs(TEST_2_00);
        nand1Bit.evaluate();
        assertTrue(nand1Bit.readOutput(0), "1 Bit Bitwise Nand 00 Test");

        nand1Bit.assignInputs(TEST_2_01);
        nand1Bit.evaluate();
        assertTrue(nand1Bit.readOutput(0), "1 Bit Bitwise Nand 01 Test");

        nand1Bit.assignInputs(TEST_2_10);
        nand1Bit.evaluate();
        assertTrue(nand1Bit.readOutput(0), "1 Bit Bitwise Nand 10 Test");

        nand1Bit.assignInputs(TEST_2_11);
        nand1Bit.evaluate();
        assertFalse(nand1Bit.readOutput(0), "1 Bit Bitwise Nand 11 Test");

        // 2 Bit Nand Test
        nand2Bit.assignInputs(TEST_4_0000);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0000 Test");

        nand2Bit.assignInputs(TEST_4_0001);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0001 Test");

        nand2Bit.assignInputs(TEST_4_0010);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0010 Test");

        nand2Bit.assignInputs(TEST_4_0011);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0011 Test");

        nand2Bit.assignInputs(TEST_4_0100);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0100 Test");

        nand2Bit.assignInputs(TEST_4_0101);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_10, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0101 Test");

        nand2Bit.assignInputs(TEST_4_0110);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0110 Test");

        nand2Bit.assignInputs(TEST_4_0111);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_10, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 0111 Test");

        nand2Bit.assignInputs(TEST_4_1000);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1000 Test");

        nand2Bit.assignInputs(TEST_4_1001);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1001 Test");

        nand2Bit.assignInputs(TEST_4_1010);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_01, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1010 Test");

        nand2Bit.assignInputs(TEST_4_1011);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_01, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1011 Test");

        nand2Bit.assignInputs(TEST_4_1100);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_11, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1100 Test");

        nand2Bit.assignInputs(TEST_4_1101);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_10, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1101 Test");

        nand2Bit.assignInputs(TEST_4_1110);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_01, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1110 Test");

        nand2Bit.assignInputs(TEST_4_1111);
        nand2Bit.evaluate();
        assertArrayEquals(ANS_2_00, nand2Bit.readOutputs(), "2 Bit Bitwise Nand 1111 Test");

        // 1 Bit External Output Nand Test
        nand1BitExternal.assignInputs(TEST_2_00);
        nand1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Nand 00 Test");

        nand1BitExternal.assignInputs(TEST_2_01);
        nand1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Nand 01 Test");

        nand1BitExternal.assignInputs(TEST_2_10);
        nand1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Nand 10 Test");

        nand1BitExternal.assignInputs(TEST_2_11);
        nand1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External Nand 11 Test");
    }
}
