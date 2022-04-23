package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;
import static org.junit.jupiter.api.Assertions.*;


public class MultiplicationTest extends MitOcwTest {

    private Multiplication mult;
    private Multiplication mult32;

    private static final Integer[] TEST_DADDA_HEIGHT_1 = {1};
    private static final Integer[] TEST_DADDA_HEIGHT_8 = {6, 4, 3, 2, 1};
    private static final Integer[] TEST_DADDA_HEIGHT_64 = {63, 42, 28, 19, 13, 9, 6, 4, 3, 2, 1};

    private static final CircuitNode[] TEST_0000 = {GND, GND, GND, GND};
    private static final CircuitNode[] TEST_0001 = {GND, GND, GND, VDD};
    private static final CircuitNode[] TEST_0010 = {GND, GND, VDD, GND};
    private static final CircuitNode[] TEST_0011 = {GND, GND, VDD, VDD};
    private static final CircuitNode[] TEST_0100 = {GND, VDD, GND, GND};
    private static final CircuitNode[] TEST_0101 = {GND, VDD, GND, VDD};
    private static final CircuitNode[] TEST_0110 = {GND, VDD, VDD, GND};
    private static final CircuitNode[] TEST_0111 = {GND, VDD, VDD, VDD};
    private static final CircuitNode[] TEST_1000 = {VDD, GND, GND, GND};
    private static final CircuitNode[] TEST_1001 = {VDD, GND, GND, VDD};
    private static final CircuitNode[] TEST_1010 = {VDD, GND, VDD, GND};
    private static final CircuitNode[] TEST_1011 = {VDD, GND, VDD, VDD};
    private static final CircuitNode[] TEST_1100 = {VDD, VDD, GND, GND};
    private static final CircuitNode[] TEST_1101 = {VDD, VDD, GND, VDD};
    private static final CircuitNode[] TEST_1110 = {VDD, VDD, VDD, GND};
    private static final CircuitNode[] TEST_1111 = {VDD, VDD, VDD, VDD};

    private static final Boolean[] ANS_0000 = {false, false, false, false};
    private static final Boolean[] ANS_0001 = {false, false, false, true};
    private static final Boolean[] ANS_0010 = {false, false, true, false};
    private static final Boolean[] ANS_0011 = {false, false, true, true};
    private static final Boolean[] ANS_0100 = {false, true, false, false};
    private static final Boolean[] ANS_0110 = {false, true, true, false};
    private static final Boolean[] ANS_1001 = {true, false, false, true};

    @BeforeEach
    void init() {
        mult = new Multiplication("Multiplication Test", 2);
        mult32 = new Multiplication("Multiplication 32 Bit Test", 32);
    }

    @Test
    void generateDaddaHeightTest() {
        assertArrayEquals(TEST_DADDA_HEIGHT_1, Multiplication.generateDaddaHeights(1).toArray(),
                "8 bit Dadda Height Test");
        assertArrayEquals(TEST_DADDA_HEIGHT_8, Multiplication.generateDaddaHeights(8).toArray(),
                "8 bit Dadda Height Test");
        assertArrayEquals(TEST_DADDA_HEIGHT_64, Multiplication.generateDaddaHeights(64).toArray(),
                "64 bit Dadda Height Test");
    }

    @Test
    void multiply2x2Test() {
        mult.assignInputs(TEST_0000);
        mult.evaluate();
        assertArrayEquals(ANS_0000, mult.readOutputs(), "Multiply 0000 Test");

        mult.assignInputs(TEST_0001);
        mult.evaluate();
        assertArrayEquals(ANS_0000, mult.readOutputs(), "Multiply 0001 Test");

        mult.assignInputs(TEST_0010);
        mult.evaluate();
        assertArrayEquals(ANS_0000, mult.readOutputs(), "Multiply 0010 Test");

        mult.assignInputs(TEST_0011);
        mult.evaluate();
        assertArrayEquals(ANS_0000, mult.readOutputs(), "Multiply 0011 Test");

        mult.assignInputs(TEST_0100);
        mult.evaluate();
        assertArrayEquals(ANS_0000, mult.readOutputs(), "Multiply 0100 Test");

        mult.assignInputs(TEST_0101);
        mult.evaluate();
        assertArrayEquals(ANS_0001, mult.readOutputs(), "Multiply 0101 Test");

        mult.assignInputs(TEST_0110);
        mult.evaluate();
        assertArrayEquals(ANS_0010, mult.readOutputs(), "Multiply 0110 Test");

        mult.assignInputs(TEST_0111);
        mult.evaluate();
        assertArrayEquals(ANS_0011, mult.readOutputs(), "Multiply 0111 Test");

        mult.assignInputs(TEST_1000);
        mult.evaluate();
        assertArrayEquals(ANS_0000, mult.readOutputs(), "Multiply 1000 Test");

        mult.assignInputs(TEST_1001);
        mult.evaluate();
        assertArrayEquals(ANS_0010, mult.readOutputs(), "Multiply 1001 Test");

        mult.assignInputs(TEST_1010);
        mult.evaluate();
        assertArrayEquals(ANS_0100, mult.readOutputs(), "Multiply 1010 Test");

        mult.assignInputs(TEST_1011);
        mult.evaluate();
        assertArrayEquals(ANS_0110, mult.readOutputs(), "Multiply 1011 Test");

        mult.assignInputs(TEST_1100);
        mult.evaluate();
        assertArrayEquals(ANS_0000, mult.readOutputs(), "Multiply 1100 Test");

        mult.assignInputs(TEST_1101);
        mult.evaluate();
        assertArrayEquals(ANS_0011, mult.readOutputs(), "Multiply 1101 Test");

        mult.assignInputs(TEST_1110);
        mult.evaluate();
        assertArrayEquals(ANS_0110, mult.readOutputs(), "Multiply 1110 Test");

        mult.assignInputs(TEST_1111);
        mult.evaluate();
        assertArrayEquals(ANS_1001, mult.readOutputs(), "Multiply 1111 Test");
    }

    @Test
    public void mult32Test() {
        mult32.assignInputs(generateCNArrayFromInts(400, 900));
        mult32.evaluate();
        assertArrayEquals(generateBooleanArrayFromLong(400*900), mult32.readOutputs(), "400*900 Test");

        mult32.assignInputs(generateCNArrayFromInts(Integer.MAX_VALUE, Integer.MAX_VALUE));
        mult32.evaluate();
        assertArrayEquals(generateBooleanArrayFromLong((long) Integer.MAX_VALUE * (long) Integer.MAX_VALUE),
                mult32.readOutputs(), "Integer Max Value Test");

        mult32.assignInputs(generateCNArrayFromInts(0, 0));
        mult32.evaluate();
        assertArrayEquals(generateBooleanArrayFromLong(0), mult32.readOutputs(), "0*0 Test");

        mult32.assignInputs(generateCNArrayFromInts(123456, 456789));
        mult32.evaluate();
        assertArrayEquals(generateBooleanArrayFromLong(123456L * 456789L), mult32.readOutputs(),
                "123456*456789 Test");
    }
}
