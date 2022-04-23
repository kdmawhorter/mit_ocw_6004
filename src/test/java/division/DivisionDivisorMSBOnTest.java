package division;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionDivisorMSBOnTest extends MitOcwTest {

    private DivisionDivisorMSBOn divisionDivisorMsbOn;
    private DivisionDivisorMSBOn divisionEquivalentBits;

    @BeforeEach
    void init() {
        divisionDivisorMsbOn = new DivisionDivisorMSBOn("Divisor MSB On Test", 8, 4);
        divisionEquivalentBits = new DivisionDivisorMSBOn("Divisor MSB On Test", 4, 4);
    }

    @Test
    void divMSBOnTest() {

        //Transistor Count Test
        assertEquals(3180, divisionDivisorMsbOn.getTransistorCount(),
                "8x4 Division MSB On Transistor Count Test");
        assertEquals(636, divisionEquivalentBits.getTransistorCount(),
                "4x4 Division MSB On Transistor Count Test");

        //8 Bit Division Tests
        for (int i = 0; i < 256; i++) {
            for (int j = 8; j < 16; j++) {
                divisionDivisorMsbOn.assignInputs(generateInputsArray(i, 8, j, 4));
                divisionDivisorMsbOn.evaluate();
                assertArrayEquals(generateBooleanArrayTwoInputs(Math.floorDiv(i, j), 8,
                        i-Math.floorDiv(i,j)*j, 8), divisionDivisorMsbOn.readOutputs(),
                        "8Bit Div MSB On " + i + "/" + j);
            }
        }

        //4 Bit Division Tests
        for (int i = 0; i < 16; i++) {
            for (int j = 8; j < 16; j++) {
                divisionEquivalentBits.assignInputs(generateInputsArray(i, 4, j, 4));
                divisionEquivalentBits.evaluate();
                assertArrayEquals(generateBooleanArrayTwoInputs(Math.floorDiv(i, j), 4,
                                i-Math.floorDiv(i,j)*j, 4), divisionEquivalentBits.readOutputs(),
                        "4Bit Div MSB On " + i + "/" + j);
            }
        }
    }
}
