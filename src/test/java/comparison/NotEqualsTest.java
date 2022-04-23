package comparison;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core_architecture.MitOcwTest;

import static org.junit.jupiter.api.Assertions.*;

public class NotEqualsTest extends MitOcwTest {

    private NotEquals notEquals;

    @BeforeEach
    void init() {
        notEquals = new NotEquals("Not Equals Test", 4);
    }

    @Test
    void notEqualsTest() {
        notEquals.assignInputs(TEST_0xFF);
        notEquals.evaluate();
        assertFalse(notEquals.readOutput(), "Not Equals 0xFF Test");

        notEquals.assignInputs(TEST_0xFE);
        notEquals.evaluate();
        assertTrue(notEquals.readOutput(), "Not Equals 0xFE Test");

        notEquals.assignInputs(TEST_0xEF);
        notEquals.evaluate();
        assertTrue(notEquals.readOutput(), "Not Equals 0xEF Test");

        notEquals.assignInputs(TEST_0x00);
        notEquals.evaluate();
        assertFalse(notEquals.readOutput(), "Not Equals 0x00 Test");

        notEquals.assignInputs(TEST_0x10);
        notEquals.evaluate();
        assertTrue(notEquals.readOutput(), "Not Equals 0x10 Test");

        notEquals.assignInputs(TEST_0x01);
        notEquals.evaluate();
        assertTrue(notEquals.readOutput(), "Not Equals 0x01 Test");
    }
}
