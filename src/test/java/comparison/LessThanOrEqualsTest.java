package comparison;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core_architecture.MitOcwTest;

import static org.junit.jupiter.api.Assertions.*;

public class LessThanOrEqualsTest extends MitOcwTest {

    private LessThanOrEquals lessThanOrEquals;

    @BeforeEach
    void init() {
        lessThanOrEquals = new LessThanOrEquals("Less Than Or Equals Test", 4);
    }

    @Test
    void lessThanOrEqualsTest() {
        assertEquals(32*4 + 6, lessThanOrEquals.getTransistorCount(),
                "Less Than Or Equals Transistor Count Test");

        lessThanOrEquals.assignInputs(TEST_0xFF);
        lessThanOrEquals.evaluate();
        assertTrue(lessThanOrEquals.readOutput(), "Less Than Or Equals 0xFF Test");

        lessThanOrEquals.assignInputs(TEST_0xFE);
        lessThanOrEquals.evaluate();
        assertFalse(lessThanOrEquals.readOutput(), "Less Than Or Equals 0xFE Test");

        lessThanOrEquals.assignInputs(TEST_0xEF);
        lessThanOrEquals.evaluate();
        assertTrue(lessThanOrEquals.readOutput(), "Less Than Or Equals 0xEF Test");

        lessThanOrEquals.assignInputs(TEST_0x00);
        lessThanOrEquals.evaluate();
        assertTrue(lessThanOrEquals.readOutput(), "Less Than Or Equals 0x00 Test");

        lessThanOrEquals.assignInputs(TEST_0x10);
        lessThanOrEquals.evaluate();
        assertFalse(lessThanOrEquals.readOutput(), "Less Than Or Equals 0x10 Test");

        lessThanOrEquals.assignInputs(TEST_0x01);
        lessThanOrEquals.evaluate();
        assertTrue(lessThanOrEquals.readOutput(), "Less Than Or Equals 0x01 Test");
    }
}
