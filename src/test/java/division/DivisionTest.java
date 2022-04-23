package division;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest extends MitOcwTest {

    private Division division5Bit;
    private Division division8Bit;

    @BeforeEach
    void init() {
        division5Bit = new Division("Division Test 5 Bit", 5);
        division8Bit = new Division("Division Test 8 Bit", 8);
    }

    @Test
    void divTest() {
        //Transistor Count Test
        assertEquals(21080, division8Bit.getTransistorCount(),"Division Transistor Count Test");

        //Division Test

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                division5Bit.assignInputs(generateInputsArray(i, 5, j, 5));
                division5Bit.evaluate();
                int quotient = (j==0) ? 0 : Math.floorDiv(i, j);
                int remainder = (j==0) ? 0 : i-quotient*j;
                assertArrayEquals(generateBooleanArrayTwoInputs(quotient, 5, remainder, 5),
                        division5Bit.readOutputs(),"Division Test " + i + "/" + j);
            }
        }
    }
}
