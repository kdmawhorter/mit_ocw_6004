package comparison;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core_architecture.MitOcwTest;

import static org.junit.jupiter.api.Assertions.*;

public class LessThanTest extends MitOcwTest {

    private LessThan lessThan;

    @BeforeEach
    void init() {
        lessThan = new LessThan("Less Than Test", 4);
    }

    @Test
    void lessThanTest() {
        lessThan.assignInputs(TEST_0xFF);
        lessThan.evaluate();
        assertFalse(lessThan.readOutput(), "Less Than 0xFF Test");

        lessThan.assignInputs(TEST_0xFE);
        lessThan.evaluate();
        assertFalse(lessThan.readOutput(), "Less Than 0xFE Test");

        lessThan.assignInputs(TEST_0xEF);
        lessThan.evaluate();
        assertTrue(lessThan.readOutput(), "Less Than 0xEF Test");

        lessThan.assignInputs(TEST_0x00);
        lessThan.evaluate();
        assertFalse(lessThan.readOutput(), "Less Than 0x00 Test");

        lessThan.assignInputs(TEST_0x10);
        lessThan.evaluate();
        assertFalse(lessThan.readOutput(), "Less Than 0x10 Test");

        lessThan.assignInputs(TEST_0x01);
        lessThan.evaluate();
        assertTrue(lessThan.readOutput(), "Less Than 0x01 Test");
    }
}
