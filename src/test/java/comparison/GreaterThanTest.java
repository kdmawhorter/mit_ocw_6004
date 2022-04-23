package comparison;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core_architecture.MitOcwTest;

import static org.junit.jupiter.api.Assertions.*;

public class GreaterThanTest extends MitOcwTest {

    private GreaterThan greaterThan;

    @BeforeEach
    void init() {
        greaterThan = new GreaterThan("Greater Than Test", 4);
    }

    @Test
    void greaterThanTest() {
        greaterThan.assignInputs(TEST_0xFF);
        greaterThan.evaluate();
        assertFalse(greaterThan.readOutput(), "Greater Than 0xFF Test");

        greaterThan.assignInputs(TEST_0xFE);
        greaterThan.evaluate();
        assertTrue(greaterThan.readOutput(), "Greater Than 0xFE Test");

        greaterThan.assignInputs(TEST_0xEF);
        greaterThan.evaluate();
        assertFalse(greaterThan.readOutput(), "Greater Than 0xEF Test");

        greaterThan.assignInputs(TEST_0x00);
        greaterThan.evaluate();
        assertFalse(greaterThan.readOutput(), "Greater Than 0x00 Test");

        greaterThan.assignInputs(TEST_0x10);
        greaterThan.evaluate();
        assertTrue(greaterThan.readOutput(), "Greater Than 0x10 Test");

        greaterThan.assignInputs(TEST_0x01);
        greaterThan.evaluate();
        assertFalse(greaterThan.readOutput(), "Greater Than 0x01 Test");
    }
}
