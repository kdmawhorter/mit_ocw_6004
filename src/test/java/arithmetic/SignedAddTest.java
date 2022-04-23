package arithmetic;

import core_architecture.CircuitNode;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;
import static org.junit.jupiter.api.Assertions.*;

public class SignedAddTest extends MitOcwTest {
    private SignedAdd signedAdd3Bit;
    private SignedAdd signedAdd5Bit;

    private static final CircuitNode[] POS_POS_POS = { GND, VDD, GND, GND, VDD, GND, GND, GND, VDD, GND };
    private static final CircuitNode[] POS_NEG_POS = { GND, GND, GND, VDD, VDD, VDD, VDD, VDD, VDD, GND };
    private static final CircuitNode[] POS_POS_NEG = { GND, VDD, VDD, VDD, VDD, GND, GND, VDD, GND, GND };
    private static final CircuitNode[] POS_NEG_ZER = { GND, VDD, GND, GND, GND, VDD, VDD, GND, GND, GND };
    private static final CircuitNode[] POS_NEG_NEG = { GND, GND, VDD, VDD, VDD, VDD, GND, VDD, VDD, GND };
    private static final CircuitNode[] NEG_NEG_NEG = { VDD, VDD, VDD, GND, GND, VDD, VDD, GND, VDD, VDD };
    private static final CircuitNode[] NEG_NEG_POS = { VDD, GND, VDD, GND, GND, VDD, GND, VDD, VDD, GND };

    private static final Boolean[] POS_POS_POS_ANS = {false, true, false, true, true, false};
    private static final Boolean[] POS_NEG_POS_ANS = {false, false, false, false, true, false};
    private static final Boolean[] POS_POS_NEG_ANS = {true, false, false, true, true, true};
    private static final Boolean[] POS_NEG_ZER_ANS = {false, false, false, false, false, false};
    private static final Boolean[] POS_NEG_NEG_ANS = {true, true, true, false, true, false};
    private static final Boolean[] NEG_NEG_NEG_ANS = {true, false, true, true, true, false};
    private static final Boolean[] NEG_NEG_POS_ANS = {false, true, false, true, false, true};

    @BeforeEach
    void init() {
        signedAdd3Bit = new SignedAdd("3 Bit Signed Add", 3);
        signedAdd5Bit = new SignedAdd("5 Bit Signed Add", 5);
    }

    @Test
    void signedAddTest() {
        // Transistor Count Tests
        assertEquals(56*3+22, signedAdd3Bit.getTransistorCount(),
                "3 Bit Signed Add Transistor Count Test");
        assertEquals(56*5+22, signedAdd5Bit.getTransistorCount(),
                "5 Bit Signed Add Transistor Count Test");

        // Signed Addition Test
        signedAdd5Bit.assignInputs(POS_POS_POS);
        signedAdd5Bit.evaluate();
        assertArrayEquals(POS_POS_POS_ANS, signedAdd5Bit.readOutputs(), "9+2=11");

        signedAdd5Bit.assignInputs(POS_NEG_POS);
        signedAdd5Bit.evaluate();
        assertArrayEquals(POS_NEG_POS_ANS, signedAdd5Bit.readOutputs(), "3+-2=1");

        signedAdd5Bit.assignInputs(POS_POS_NEG);
        signedAdd5Bit.evaluate();
        assertArrayEquals(POS_POS_NEG_ANS, signedAdd5Bit.readOutputs(), "15+4=-13");

        signedAdd5Bit.assignInputs(POS_NEG_ZER);
        signedAdd5Bit.evaluate();
        assertArrayEquals(POS_NEG_ZER_ANS, signedAdd5Bit.readOutputs(), "8+-8=0");

        signedAdd5Bit.assignInputs(POS_NEG_NEG);
        signedAdd5Bit.evaluate();
        assertArrayEquals(POS_NEG_NEG_ANS, signedAdd5Bit.readOutputs(), "7+-9=-2");

        signedAdd5Bit.assignInputs(NEG_NEG_NEG);
        signedAdd5Bit.evaluate();
        assertArrayEquals(NEG_NEG_NEG_ANS, signedAdd5Bit.readOutputs(), "-4+-5=-9");

        signedAdd5Bit.assignInputs(NEG_NEG_POS);
        signedAdd5Bit.evaluate();
        assertArrayEquals(NEG_NEG_POS_ANS, signedAdd5Bit.readOutputs(), "-12+-10=10");
    }
}
