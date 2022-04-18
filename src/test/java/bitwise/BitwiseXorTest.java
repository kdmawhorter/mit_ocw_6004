package bitwise;

import core_architecture.CircuitNode;
import core_architecture.ConnectionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseXorTest extends BitwiseTest {

    private BitwiseXor xor1Bit;
    private BitwiseXor xor2Bit;
    private BitwiseXor xor1BitExternal;

    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        xor1Bit = new BitwiseXor("1 Bit Xor", 1);
        xor2Bit = new BitwiseXor("2 Bit Xor", 2);
        xor1BitExternal = new BitwiseXor("1 Bit External Xor", 1);

        externalOutput = new CircuitNode("External Output");
        xor1BitExternal.assignOutput(0, externalOutput);
    }

    @Test
    void bitwiseXorTest() {
        // Transistor Count Tests
        assertEquals(16, xor1Bit.getTransistorCount(), "1 Bit Bitwise Xor Transistor Count Testing");
        assertEquals(32, xor2Bit.getTransistorCount(), "2 Bit Bitwise Xor Transistor Count Testing");

        // 1 Bit Bitwise Xor Test
        xor1Bit.assignInputs(TEST_2_00);
        xor1Bit.evaluate();
        assertArrayEquals(mapTest(TEST_1_0), xor1Bit.readOutputs(), "1 Bit Bitwise Xor 00 Test");

        xor1Bit.assignInputs(TEST_2_01);
        xor1Bit.evaluate();
        assertArrayEquals(mapTest(TEST_1_1), xor1Bit.readOutputs(), "1 Bit Bitwise Xor 01 Test");

        xor1Bit.assignInputs(TEST_2_10);
        xor1Bit.evaluate();
        assertArrayEquals(mapTest(TEST_1_1), xor1Bit.readOutputs(), "1 Bit Bitwise Xor 10 Test");

        xor1Bit.assignInputs(TEST_2_11);
        xor1Bit.evaluate();
        assertArrayEquals(mapTest(TEST_1_0), xor1Bit.readOutputs(), "1 Bit Bitwise Xor 11 Test");

        // 2 Bit Bitwise Xor Test
        xor2Bit.assignInputs(TEST_4_0000);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_00), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0000 Test");

        xor2Bit.assignInputs(TEST_4_0001);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_01), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0001 Test");

        xor2Bit.assignInputs(TEST_4_0010);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_10), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0010 Test");

        xor2Bit.assignInputs(TEST_4_0011);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_11), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0011 Test");

        xor2Bit.assignInputs(TEST_4_0100);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_01), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0100 Test");

        xor2Bit.assignInputs(TEST_4_0101);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_00), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0101 Test");

        xor2Bit.assignInputs(TEST_4_0110);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_11), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0110 Test");

        xor2Bit.assignInputs(TEST_4_0111);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_10), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 0111 Test");

        xor2Bit.assignInputs(TEST_4_1000);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_10), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1000 Test");

        xor2Bit.assignInputs(TEST_4_1001);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_11), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1001 Test");

        xor2Bit.assignInputs(TEST_4_1010);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_00), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1010 Test");

        xor2Bit.assignInputs(TEST_4_1011);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_01), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1011 Test");

        xor2Bit.assignInputs(TEST_4_1100);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_11), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1100 Test");

        xor2Bit.assignInputs(TEST_4_1101);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_10), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1101 Test");

        xor2Bit.assignInputs(TEST_4_1110);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_01), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1110 Test");

        xor2Bit.assignInputs(TEST_4_1111);
        xor2Bit.evaluate();
        assertArrayEquals(mapTest(TEST_2_00), xor2Bit.readOutputs(), "2 Bit Bitwise Xor 1111 Test");

        // 1 Bit External Output Xor Test
        xor1BitExternal.assignInputs(TEST_2_00);
        xor1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External Xor 00 Test");

        xor1BitExternal.assignInputs(TEST_2_01);
        xor1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Xor 01 Test");

        xor1BitExternal.assignInputs(TEST_2_10);
        xor1BitExternal.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(), "1 Bit Bitwise External Xor 10 Test");

        xor1BitExternal.assignInputs(TEST_2_11);
        xor1BitExternal.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(), "1 Bit Bitwise External Xor 11 Test");
    }
}
