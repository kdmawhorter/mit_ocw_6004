package comparison;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core_architecture.MitOcwTest;

import static org.junit.jupiter.api.Assertions.*;

public class GreaterThanOrEqualsTest extends MitOcwTest {

    private GreaterThanOrEquals greaterThanOrEquals;

    @BeforeEach
    void init() {
        greaterThanOrEquals = new GreaterThanOrEquals("Greater Than Or Equals Test", 4);
    }

    @Test
    void greaterThanOrEqualsTest() {
        greaterThanOrEquals.assignInputs(TEST_0xFF);
        greaterThanOrEquals.evaluate();
        assertTrue(greaterThanOrEquals.readOutput(), "Greater Than Or Equals 0xFF Test");

        greaterThanOrEquals.assignInputs(TEST_0xFE);
        greaterThanOrEquals.evaluate();
        assertTrue(greaterThanOrEquals.readOutput(), "Greater Than Or Equals 0xFE Test");

        greaterThanOrEquals.assignInputs(TEST_0xEF);
        greaterThanOrEquals.evaluate();
        assertFalse(greaterThanOrEquals.readOutput(), "Greater Than Or Equals 0xEF Test");

        greaterThanOrEquals.assignInputs(TEST_0x00);
        greaterThanOrEquals.evaluate();
        assertTrue(greaterThanOrEquals.readOutput(), "Greater Than Or Equals 0x00 Test");

        greaterThanOrEquals.assignInputs(TEST_0x10);
        greaterThanOrEquals.evaluate();
        assertTrue(greaterThanOrEquals.readOutput(), "Greater Than Or Equals 0x10 Test");

        greaterThanOrEquals.assignInputs(TEST_0x01);
        greaterThanOrEquals.evaluate();
        assertFalse(greaterThanOrEquals.readOutput(), "Greater Than Or Equals 0x01 Test");
    }
}
