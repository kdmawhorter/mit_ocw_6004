package division;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SignedDivisionTest extends MitOcwTest {

    private SignedDivision signedDivision;
    private SignedDivision signedDivision16Bit;

    @BeforeEach
    void init() {
        signedDivision = new SignedDivision("5 Bit signed division", 5);
        signedDivision16Bit = new SignedDivision("16 Bit signed division", 16);
    }

    @Test
    void signedDivisionTest() {

        for (int i = -15; i < 16; i++) {
            for (int j = -15; j < 16; j++) {
                int quotient = (j==0) ? 0 : (int) (Math.signum(i) * Math.signum(j) *
                        Math.floorDiv(Math.abs(i), Math.abs(j)));
                int remainder = (j==0) ? 0 : i-quotient*j;

                signedDivision.assignInputs(generateInputsArray(i, 5, j, 5));
                signedDivision.evaluate();
                assertArrayEquals(generateBooleanArrayTwoInputs(quotient, 5, remainder, 5),
                        signedDivision.readOutputs(), "Signed Division " + i + "/" + j);
            }
        }
    }

    @Test
    void signed16BitDivisionTest() {
        signedDivision16Bit.assignInputs(generateInputsArray(-28000, 16, 3489, 16));
        signedDivision16Bit.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(-8, 16, -88, 16),
                signedDivision16Bit.readOutputs(), "16 Bit Signed Division Test");


        signedDivision16Bit.assignInputs(generateInputsArray(-10238, 16, -5, 16));
        signedDivision16Bit.evaluate();
        assertArrayEquals(generateBooleanArrayTwoInputs(2047, 16, -3, 16),
                signedDivision16Bit.readOutputs(), "16 Bit Signed Division Test");
    }
}
