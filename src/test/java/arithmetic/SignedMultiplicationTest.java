package arithmetic;

import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SignedMultiplicationTest extends MitOcwTest {

    private SignedMultiplication signedMultiplication;

    @BeforeEach
    void init() {
        signedMultiplication = new SignedMultiplication("Signed Mult Test", 6);
    }

    @Test
    void signedMultiplicationTest() {
        for (int i = -31; i < 32; i++) {
            for (int j = -31; j < 32; j++) {
                signedMultiplication.assignInputs(generateInputsArray(i, 6, j, 6));
                signedMultiplication.evaluate();
                assertArrayEquals(generateBooleanArray(i*j, 12), signedMultiplication.readOutputs(),
                        "Signed Multiplication " + i + "*" + j);
            }
        }
    }
}
