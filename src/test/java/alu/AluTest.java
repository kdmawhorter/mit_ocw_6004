package alu;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AluTest extends MitOcwTest {

    private static Alu alu;

    private static final int WORD_WIDTH = 8;


    @BeforeAll
    static void init() {
        alu = new Alu("ALU Test", WORD_WIDTH);
    }

    @Test
    void aluRegistersAndMemoryTest() {
        alu.assignInputs(generateThreeInputsArray(0x00, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 15, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read R15: 0");

        alu.assignInputs(generateThreeInputsArray(0x01, Alu.MAX_OP_CODES, 123, WORD_WIDTH, 15, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Write R15: 123");

        alu.assignInputs(generateThreeInputsArray(0x00, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 15, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(123, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read R15: 123");

        alu.assignInputs(generateThreeInputsArray(0x02, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 128, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read M128: 0");

        alu.assignInputs(generateThreeInputsArray(0x03, Alu.MAX_OP_CODES, 42, WORD_WIDTH, 128, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Write M128: 42");

        alu.assignInputs(generateThreeInputsArray(0x02, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 128, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(42, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read M128: 42");

        alu.assignInputs(generateThreeInputsArray(0x01, Alu.MAX_OP_CODES, 45, WORD_WIDTH, 31, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Write R31: 45");

        alu.assignInputs(generateThreeInputsArray(0x01, Alu.MAX_OP_CODES, 78, WORD_WIDTH, 1, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Write R01: 78");

        alu.assignInputs(generateThreeInputsArray(0x03, Alu.MAX_OP_CODES, 10, WORD_WIDTH, 182, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Write M182: 10");

        alu.assignInputs(generateThreeInputsArray(0x03, Alu.MAX_OP_CODES, 1, WORD_WIDTH, 1, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(), "ALU Write M001: 1");

        alu.assignInputs(generateThreeInputsArray(0x00, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 15, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(123, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read R15: 123");

        alu.assignInputs(generateThreeInputsArray(0x00, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 31, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(45, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read R31: 45");

        alu.assignInputs(generateThreeInputsArray(0x00, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 1, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(78, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read R01: 78");

        alu.assignInputs(generateThreeInputsArray(0x02, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 128, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(42, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read M128: 42");

        alu.assignInputs(generateThreeInputsArray(0x02, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 182, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(10, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read M182: 10");

        alu.assignInputs(generateThreeInputsArray(0x02, Alu.MAX_OP_CODES, 0, WORD_WIDTH, 1, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(), "ALU Read M001: 1");

    }

    @Test
    void aluArithmeticTest() {
        alu.assignInputs(generateThreeInputsArray(0x10, Alu.MAX_OP_CODES, 125, WORD_WIDTH, 46, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 125+46, WORD_WIDTH),
                alu.readOutputs(), "ALU Unsigned Add Test");


        alu.assignInputs(generateThreeInputsArray(0x11, Alu.MAX_OP_CODES, 25, WORD_WIDTH, 46, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 25+46, WORD_WIDTH),
                alu.readOutputs(), "ALU Signed Add Test Positive");

        alu.assignInputs(generateThreeInputsArray(0x11, Alu.MAX_OP_CODES, 25, WORD_WIDTH, -46, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 25-46, WORD_WIDTH),
                alu.readOutputs(), "ALU Signed Add Test Negative");

        alu.assignInputs(generateThreeInputsArray(0x12, Alu.MAX_OP_CODES, 120, WORD_WIDTH, 60, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(120*60, 2*WORD_WIDTH),
                alu.readOutputs(), "ALU Multiplication Positive");

        alu.assignInputs(generateThreeInputsArray(0x13, Alu.MAX_OP_CODES, -25, WORD_WIDTH, -53, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(25*53, 2*WORD_WIDTH),
                alu.readOutputs(), "ALU Signed Multiplication Positive");

        alu.assignInputs(generateThreeInputsArray(0x13, Alu.MAX_OP_CODES, -111, WORD_WIDTH, 38, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(-111*38, 2*WORD_WIDTH),
                alu.readOutputs(), "ALU Signed Multiplication Negative");

        alu.assignInputs(generateThreeInputsArray(0x14, Alu.MAX_OP_CODES, 110, WORD_WIDTH, 13, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(6, WORD_WIDTH, 8, WORD_WIDTH),
                alu.readOutputs(), "ALU Division");

        alu.assignInputs(generateThreeInputsArray(0x15, Alu.MAX_OP_CODES, -110, WORD_WIDTH, -13, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(-6, WORD_WIDTH, 8, WORD_WIDTH),
                alu.readOutputs(), "ALU Division Positive Q Negative R");

        alu.assignInputs(generateThreeInputsArray(0x15, Alu.MAX_OP_CODES, 110, WORD_WIDTH, -13, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(6, WORD_WIDTH, -8, WORD_WIDTH),
                alu.readOutputs(), "ALU Division Negative Q Positive R");

        alu.assignInputs(generateInputsArray(0x16, Alu.MAX_OP_CODES, 0xCD, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0xCE, 2*WORD_WIDTH),
                alu.readOutputs(), "ALU Increment");

        alu.assignInputs(generateInputsArray(0x17, Alu.MAX_OP_CODES, 0xCD, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0xCC, 2*WORD_WIDTH),
                alu.readOutputs(), "ALU Decrement");
    }

    @Test
    void bitwiseOpsTest() {
        alu.assignInputs(generateThreeInputsArray(0x20, Alu.MAX_OP_CODES, 0b00111000, WORD_WIDTH, 0b11011011, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0b00011000, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Bitwise And");

        alu.assignInputs(generateThreeInputsArray(0x21, Alu.MAX_OP_CODES, 0b00111000, WORD_WIDTH, 0b11011011, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0b11100111, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Bitwise Nand");

        alu.assignInputs(generateThreeInputsArray(0x22, Alu.MAX_OP_CODES, 0b00111000, WORD_WIDTH, 0b11011011, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0b00000100, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Bitwise Nor");

        alu.assignInputs(generateThreeInputsArray(0x23, Alu.MAX_OP_CODES, 0b00111000, WORD_WIDTH, 0b11011011, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0b11111011, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Bitwise Or");

        alu.assignInputs(generateThreeInputsArray(0x24, Alu.MAX_OP_CODES, 0b00111000, WORD_WIDTH, 0b11011011, WORD_WIDTH));
        alu.evaluate();

        assertArrayEquals(generateBooleanArray(0b11100011, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Bitwise Xor");

        alu.assignInputs(generateThreeInputsArray(0x25, Alu.MAX_OP_CODES, 0b00111000, WORD_WIDTH, 0, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0b11000111, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Bitwise Not");
    }

    @Test
    void comparisonTest() {
        alu.assignInputs(generateThreeInputsArray(0x26, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x88, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp Equals 0x88, 0x88");

        alu.assignInputs(generateThreeInputsArray(0x26, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x87, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp Equals 0x88, 0x87");

        alu.assignInputs(generateThreeInputsArray(0x26, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x89, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp Equals 0x88, 0x89");

        alu.assignInputs(generateThreeInputsArray(0x27, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x88, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp GT 0x88, 0x88");

        alu.assignInputs(generateThreeInputsArray(0x27, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x87, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp GT 0x88, 0x87");

        alu.assignInputs(generateThreeInputsArray(0x27, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x89, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp GT 0x88, 0x89");

        alu.assignInputs(generateThreeInputsArray(0x28, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x88, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp GTE 0x88, 0x88");

        alu.assignInputs(generateThreeInputsArray(0x28, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x87, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp GTE 0x88, 0x87");

        alu.assignInputs(generateThreeInputsArray(0x28, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x89, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp GTE 0x88, 0x89");

        alu.assignInputs(generateThreeInputsArray(0x29, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x88, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp LT 0x88, 0x88");

        alu.assignInputs(generateThreeInputsArray(0x29, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x87, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp LT 0x88, 0x87");

        alu.assignInputs(generateThreeInputsArray(0x29, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x89, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp LT 0x88, 0x89");

        alu.assignInputs(generateThreeInputsArray(0x2A, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x88, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp LTE 0x88, 0x88");

        alu.assignInputs(generateThreeInputsArray(0x2A, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x87, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp LTE 0x88, 0x87");

        alu.assignInputs(generateThreeInputsArray(0x2A, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x89, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp LTE 0x88, 0x89");

        alu.assignInputs(generateThreeInputsArray(0x2B, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x88, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(0, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp Not Equals 0x88, 0x88");

        alu.assignInputs(generateThreeInputsArray(0x2B, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x87, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp Not Equals 0x88, 0x87");

        alu.assignInputs(generateThreeInputsArray(0x2B, Alu.MAX_OP_CODES, 0x88, WORD_WIDTH, 0x89, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArray(1, 2*WORD_WIDTH), alu.readOutputs(),
                "ALU Comp Not Equals 0x88, 0x89");
    }

    @Test
    void aluShiftTest() {
        alu.assignInputs(generateThreeInputsArray(0x30, Alu.MAX_OP_CODES, 0b10011000, WORD_WIDTH, 0, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 0b00110000, WORD_WIDTH),
                alu.readOutputs(), "ALU Shift Left Unsigned");

        alu.assignInputs(generateThreeInputsArray(0x31, Alu.MAX_OP_CODES, 0b10011000, WORD_WIDTH, 0, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 0b10110000, WORD_WIDTH),
                alu.readOutputs(), "ALU Shift Left Signed");

        alu.assignInputs(generateThreeInputsArray(0x32, Alu.MAX_OP_CODES, 0b10011000, WORD_WIDTH, 0, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 0b00110001, WORD_WIDTH),
                alu.readOutputs(), "ALU Shift Left Circle");

        alu.assignInputs(generateThreeInputsArray(0x33, Alu.MAX_OP_CODES, 0b10011000, WORD_WIDTH, 0, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 0b01001100, WORD_WIDTH),
                alu.readOutputs(), "ALU Shift Right Unsigned");

        alu.assignInputs(generateThreeInputsArray(0x34, Alu.MAX_OP_CODES, 0b10011000, WORD_WIDTH, 0, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 0b11001100, WORD_WIDTH),
                alu.readOutputs(), "ALU Shift Right Signed");

        alu.assignInputs(generateThreeInputsArray(0x35, Alu.MAX_OP_CODES, 0b10011000, WORD_WIDTH, 0, WORD_WIDTH));
        alu.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(0, WORD_WIDTH, 0b01001100, WORD_WIDTH),
                alu.readOutputs(), "ALU Shift Right Circle");
    }
}
