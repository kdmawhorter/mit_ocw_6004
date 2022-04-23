package division;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitDivisionTest extends MitOcwTest {

    private BitDivision bitDivision;

    @BeforeEach
    void init() {
        bitDivision = new BitDivision("Bit Division Test", 4);
    }

    @Test
    void bitDivisionTest() {

        //Transistor Count Test
        assertEquals(124*4+16, bitDivision.getTransistorCount(),"Bit Division Transistor Cnt Test");

        //Divisor 4
        bitDivision.assignInputs(generateInputsArray(0x7, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x74");

        bitDivision.assignInputs(generateInputsArray(0x6, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x64");

        bitDivision.assignInputs(generateInputsArray(0x5, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x54");

        bitDivision.assignInputs(generateInputsArray(0x4, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x44");

        bitDivision.assignInputs(generateInputsArray(0x3, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x34");

        bitDivision.assignInputs(generateInputsArray(0x2, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x24");

        bitDivision.assignInputs(generateInputsArray(0x1, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x14");

        bitDivision.assignInputs(generateInputsArray(0x0, 0x4, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x04");

        // Divisor 5

        bitDivision.assignInputs(generateInputsArray(0x9, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10100, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x95");

        bitDivision.assignInputs(generateInputsArray(0x8, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x85");

        bitDivision.assignInputs(generateInputsArray(0x7, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x75");

        bitDivision.assignInputs(generateInputsArray(0x6, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x65");

        bitDivision.assignInputs(generateInputsArray(0x5, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x55");

        bitDivision.assignInputs(generateInputsArray(0x4, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00100, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x45");

        bitDivision.assignInputs(generateInputsArray(0x3, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x35");

        bitDivision.assignInputs(generateInputsArray(0x2, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x25");

        bitDivision.assignInputs(generateInputsArray(0x1, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x15");

        bitDivision.assignInputs(generateInputsArray(0x0, 0x5, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x05");

        // Divisor 6

        bitDivision.assignInputs(generateInputsArray(0xB, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10101, 5), bitDivision.readOutputs(),
                "Bit Div Test 0xB6");

        bitDivision.assignInputs(generateInputsArray(0xA, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10100, 5), bitDivision.readOutputs(),
                "Bit Div Test 0xA6");

        bitDivision.assignInputs(generateInputsArray(0x9, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x96");

        bitDivision.assignInputs(generateInputsArray(0x8, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x86");

        bitDivision.assignInputs(generateInputsArray(0x7, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x76");

        bitDivision.assignInputs(generateInputsArray(0x6, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x66");

        bitDivision.assignInputs(generateInputsArray(0x5, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00101, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x56");

        bitDivision.assignInputs(generateInputsArray(0x4, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00100, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x46");

        bitDivision.assignInputs(generateInputsArray(0x3, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x36");

        bitDivision.assignInputs(generateInputsArray(0x2, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x26");

        bitDivision.assignInputs(generateInputsArray(0x1, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x16");

        bitDivision.assignInputs(generateInputsArray(0x0, 0x6, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x06");

        // Divisor 7

        bitDivision.assignInputs(generateInputsArray(0xD, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10110, 5), bitDivision.readOutputs(),
                "Bit Div Test 0xD7");

        bitDivision.assignInputs(generateInputsArray(0xC, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10101, 5), bitDivision.readOutputs(),
                "Bit Div Test 0xC7");

        bitDivision.assignInputs(generateInputsArray(0xB, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10100, 5), bitDivision.readOutputs(),
                "Bit Div Test 0xB7");

        bitDivision.assignInputs(generateInputsArray(0xA, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0xA7");

        bitDivision.assignInputs(generateInputsArray(0x9, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x97");

        bitDivision.assignInputs(generateInputsArray(0x8, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x87");

        bitDivision.assignInputs(generateInputsArray(0x7, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b10000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x77");

        bitDivision.assignInputs(generateInputsArray(0x6, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00110, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x67");

        bitDivision.assignInputs(generateInputsArray(0x5, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00101, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x57");

        bitDivision.assignInputs(generateInputsArray(0x4, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00100, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x47");

        bitDivision.assignInputs(generateInputsArray(0x3, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00011, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x37");

        bitDivision.assignInputs(generateInputsArray(0x2, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00010, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x27");

        bitDivision.assignInputs(generateInputsArray(0x1, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00001, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x17");

        bitDivision.assignInputs(generateInputsArray(0x0, 0x7, 4));
        bitDivision.evaluate();
        assertArrayEquals(generateBooleanArray(0b00000, 5), bitDivision.readOutputs(),
                "Bit Div Test 0x07");

        for (int dividend = 0; dividend < 16; dividend++) {
            for (int divisor = 0; divisor < 16; divisor++) {
                if (divisor>7 || divisor<4) {
                    bitDivision.assignInputs(generateInputsArray(dividend, divisor, 4));
                    bitDivision.evaluate();
                    assertArrayEquals(generateBooleanArray(0b00000, 5), bitDivision.readOutputs(),
                            "Invalid Divisor Test");
                }
            }
        }
    }
}
