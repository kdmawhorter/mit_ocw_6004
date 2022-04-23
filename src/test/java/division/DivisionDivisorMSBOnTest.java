package division;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionDivisorMSBOnTest extends MitOcwTest {

    private DivisionDivisorMSBOn divisionDivisorMsbOn;

    @BeforeEach
    void init() {
        divisionDivisorMsbOn = new DivisionDivisorMSBOn("Diviser MSB On Test", 8, 4);
    }

    @Test
    void divMSBOnTest() {

        //Transistor Count Test
        assertEquals((8-4+1)*(124*(8-4+1)+16), divisionDivisorMsbOn.getTransistorCount(),
                "Division MSB On Transistor Count Test");

        //Division Tests
        for (int i = 0; i < 256; i++) {
            for (int j = 8; j < 16; j++) {
                divisionDivisorMsbOn.assignInputs(generateInputsArray(i, 8, j, 4));
                divisionDivisorMsbOn.evaluate();
                assertArrayEquals(generateBooleanArrayTwoInputs(Math.floorDiv(i, j), 5,
                        i-Math.floorDiv(i,j)*j, 4), divisionDivisorMsbOn.readOutputs(),
                        "Div MSB On " + i + "/" + j);
            }
        }
    }
}
