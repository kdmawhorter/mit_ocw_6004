package multiplexer;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;
import static org.junit.jupiter.api.Assertions.*;

public class MultiplexerTest {

    private Multiplexer mux8to1;
    private Multiplexer mux20to4;
    private Multiplexer mux15to5;
    private Multiplexer mux4to2;

    @BeforeEach
    void init() {
        mux8to1 = new Multiplexer("8 to 1 Mux", 8, 1);
        mux20to4 = new Multiplexer("20 to 4 Mux", 5, 4);
        mux15to5 = new Multiplexer("15 to 5 Mux", 3, 5);
        mux4to2 = new Multiplexer("4 to 2 Mux", 2, 2);
    }

    private static final boolean[] INCR_TEST_0x0 = {false, false, false, false};
    private static final boolean[] INCR_TEST_0x1 = {false, false, false, true};
    private static final boolean[] INCR_TEST_0x2 = {false, false, true, false};
    private static final boolean[] INCR_TEST_0xE = {true, true, true, false};
    private static final boolean[] INCR_TEST_0xF = {true, true, true, true};

    @Test
    void incrementTruthArrayTest() {
        assertArrayEquals(INCR_TEST_0x1, Multiplexer.incrementTruthArray(INCR_TEST_0x0), "Incr 0 Test");
        assertArrayEquals(INCR_TEST_0x2, Multiplexer.incrementTruthArray(INCR_TEST_0x1), "Incr 0 Test");
        assertArrayEquals(INCR_TEST_0xF, Multiplexer.incrementTruthArray(INCR_TEST_0xE), "Incr 0 Test");
        assertArrayEquals(INCR_TEST_0x0, Multiplexer.incrementTruthArray(INCR_TEST_0xF), "Incr 0 Test");
    }

    @Test
    void determineSelectorBitCountTest() {
        assertEquals(4, Multiplexer.determineSelectorBitCount(15), "15 input bit test");
        assertEquals(4, Multiplexer.determineSelectorBitCount(16), "16 input bit test");
        assertEquals(5, Multiplexer.determineSelectorBitCount(17), "17 input bit test");
        assertEquals(5, Multiplexer.determineSelectorBitCount(26), "26 input bit test");
        assertEquals(5, Multiplexer.determineSelectorBitCount(32), "32 input bit test");
        assertEquals(6, Multiplexer.determineSelectorBitCount(33), "33 input bit test");
    }

    @Test
    void mux8to1Test() {
        CircuitNode[] testArray = {GND, GND, GND, GND, GND, GND, GND, GND, GND, GND, GND};

        assertEquals(3, mux8to1.getSelBitCnt(), "8to1 Mux Proper Selector Bit Count");
        assertEquals(86, mux8to1.getTransistorCount(), "8to1 Mux Transistor Count");

        testArray[0] = VDD;
        mux8to1.assignInputs(testArray);
        mux8to1.evaluate();
        assertTrue(mux8to1.readOutput(0), "Reading First Bit");

        testArray[0] = GND;
        testArray[7] = VDD;
        testArray[8] = VDD;
        testArray[9] = VDD;
        testArray[10] = VDD;
        mux8to1.assignInputs(testArray);
        mux8to1.evaluate();
        assertTrue(mux8to1.readOutput(0), "Reading Last Bit");

        testArray[7] = GND;
        testArray[4] = VDD;
        testArray[9] = GND;
        testArray[10] = GND;
        mux8to1.assignInputs(testArray);
        mux8to1.evaluate();
        assertTrue(mux8to1.readOutput(0), "Reading 5th Bit");

        testArray[4] = GND;
        testArray[6] = VDD;
        testArray[9] = VDD;
        mux8to1.assignInputs(testArray);
        mux8to1.evaluate();
        assertTrue(mux8to1.readOutput(0), "Reading 7th Bit");
    }

    @Test
    void mux20to4Test() {
        CircuitNode[] testArray = {GND, GND, GND, GND,
                                   VDD, GND, VDD, VDD,
                                   VDD, GND, GND, VDD,
                                   GND, VDD, GND, VDD,
                                   VDD, VDD, VDD, VDD,
                                   GND, GND, GND};
        assertEquals(3, mux20to4.getSelBitCnt(), "20to4 Mux Proper Selector Bit Count");
        assertEquals(206, mux20to4.getTransistorCount(), "20to4 Mux Transistor Count");


        mux20to4.assignInputs(testArray);
        mux20to4.evaluate();
        assertArrayEquals(new Boolean[] { false, false, false, false }, mux20to4.readOutputs(),
                "20to4 Mux Choice 1");

        testArray[22]=VDD;
        mux20to4.assignInputs(testArray);
        mux20to4.evaluate();
        assertArrayEquals(new Boolean[] { true, false, true, true }, mux20to4.readOutputs(),
                "20to4 Mux Choice 2");

        testArray[21]=VDD;
        testArray[22]=GND;
        mux20to4.assignInputs(testArray);
        mux20to4.evaluate();
        assertArrayEquals(new Boolean[] { true, false, false, true }, mux20to4.readOutputs(),
                "20to4 Mux Choice 3");

        testArray[22]=VDD;
        mux20to4.assignInputs(testArray);
        mux20to4.evaluate();
        assertArrayEquals(new Boolean[] { false, true, false, true }, mux20to4.readOutputs(),
                "20to4 Mux Choice 4");

        testArray[20]=VDD;
        testArray[21]=GND;
        testArray[22]=GND;
        mux20to4.assignInputs(testArray);
        mux20to4.evaluate();
        assertArrayEquals(new Boolean[] { true, true, true, true }, mux20to4.readOutputs(),
                "20to4 Mux Choice 5");
    }

    @Test
    void mux15to5Test() {
        CircuitNode[] testArray = {
                GND, GND, GND, GND, VDD,
                VDD, GND, VDD, VDD, GND,
                VDD, VDD, VDD, VDD, VDD,
                GND, GND};

        assertEquals(2, mux15to5.getSelBitCnt(), "15to5 Mux Proper Selector Bit Count");
        assertEquals(124, mux15to5.getTransistorCount(), "15to5 Mux Transistor Count");


        mux15to5.assignInputs(testArray);
        mux15to5.evaluate();
        assertArrayEquals(new Boolean[] { false, false, false, false, true }, mux15to5.readOutputs(),
                "15to5 Mux Choice 1");

        testArray[16] = VDD;
        mux15to5.assignInputs(testArray);
        mux15to5.evaluate();
        assertArrayEquals(new Boolean[] { true, false, true, true, false }, mux15to5.readOutputs(),
                "15to5 Mux Choice 2");

        testArray[15] = VDD;
        testArray[16] = GND;
        mux15to5.assignInputs(testArray);
        mux15to5.evaluate();
        assertArrayEquals(new Boolean[] { true, true, true, true, true }, mux15to5.readOutputs(),
                "15to5 Mux Choice 3");

        testArray[16] = VDD;
        mux15to5.assignInputs(testArray);
        mux15to5.evaluate();
        assertArrayEquals(new Boolean[] { false, false, false, false, false }, mux15to5.readOutputs(),
                "15to5 Mux Invalid Choice");
    }

    @Test
    void mux4to2Test() {
        CircuitNode[] testArray = {
                GND, GND,
                VDD, VDD,
                GND };
        assertEquals(1, mux4to2.getSelBitCnt(), "20to4 Mux Proper Selector Bit Count");
        assertEquals(26, mux4to2.getTransistorCount(), "4to2 Mux Transistor Count");

        mux4to2.assignInputs(testArray);
        mux4to2.evaluate();
        assertArrayEquals(new Boolean[] { false, false }, mux4to2.readOutputs(),
                "4to2 Mux Choice 1");

        testArray[4] = VDD;
        mux4to2.assignInputs(testArray);
        mux4to2.evaluate();
        assertArrayEquals(new Boolean[] { true, true }, mux4to2.readOutputs(),
                "4to2 Mux Choice 1");
    }
}
