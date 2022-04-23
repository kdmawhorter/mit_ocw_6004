package comparison;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core_architecture.MitOcwTest;

import static org.junit.jupiter.api.Assertions.*;

public class EqualsTest extends MitOcwTest {

    private Equals equals;

    @BeforeEach
    void init() {
        equals = new Equals("Equals Test", 4);
    }

    @Test
    void equalsTest() {
        equals.assignInputs(TEST_0xFF);
        equals.evaluate();
        assertTrue(equals.readOutput(), "Equals 0xFF Test");

        equals.assignInputs(TEST_0xFE);
        equals.evaluate();
        assertFalse(equals.readOutput(), "Equals 0xFE Test");

        equals.assignInputs(TEST_0xEF);
        equals.evaluate();
        assertFalse(equals.readOutput(), "Equals 0xEF Test");

        equals.assignInputs(TEST_0x00);
        equals.evaluate();
        assertTrue(equals.readOutput(), "Equals 0x00 Test");

        equals.assignInputs(TEST_0x10);
        equals.evaluate();
        assertFalse(equals.readOutput(), "Equals 0x10 Test");

        equals.assignInputs(TEST_0x01);
        equals.evaluate();
        assertFalse(equals.readOutput(), "Equals 0x01 Test");
    }
}
