package arithmetic;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class UnsignedAddTest {

    private UnsignedAdd unsignedAdd3Bit;
    private UnsignedAdd unsignedAdd4Bit;

    private static final CircuitNode[] NO_OVERFLOW_4_BIT = { VDD, GND, GND, VDD, GND, GND, VDD, GND };
    private static final CircuitNode[] OVERFLOW_4_BIT = { VDD, GND, GND, GND, VDD, VDD, VDD, GND };
    private static final CircuitNode[] ADD_ZEROES = { GND, GND, GND, GND, GND, GND, GND, GND };
    private static final CircuitNode[] ADD_FFS = { VDD, VDD, VDD, VDD, VDD, VDD, VDD, VDD };

    private static final Boolean[] NO_OVERFLOW_4_BIT_ANS = {true, false, true, true, false};
    private static final Boolean[] OVERFLOW_4_BIT_ANS = {false, true, true, false, true};
    private static final Boolean[] ADD_ZEROES_ANS = {false, false, false, false, false};
    private static final Boolean[] ADD_FFS_ANS = {true, true, true, false, true};



    @BeforeEach
    void init() {
        unsignedAdd3Bit = new UnsignedAdd("3 Bit Add", 3);
        unsignedAdd4Bit = new UnsignedAdd("4 Bit Add", 4);
    }

    @Test
    void unsignedAddTest() {
        // Transistor Count Tests
        assertEquals(56*3, unsignedAdd3Bit.getTransistorCount(),
                "3 Bit Unsigned Add Transistor Count Test");
        assertEquals(56*4, unsignedAdd4Bit.getTransistorCount(),
                "4 Bit Unsigned Add Transistor Count Test");

        // Unsigned addition test
        unsignedAdd4Bit.assignInputs(NO_OVERFLOW_4_BIT);
        unsignedAdd4Bit.evaluate();
        assertArrayEquals(NO_OVERFLOW_4_BIT_ANS, unsignedAdd4Bit.readOutputs(), "4 Bit Add No Overflow");

        unsignedAdd4Bit.assignInputs(OVERFLOW_4_BIT);
        unsignedAdd4Bit.evaluate();
        assertArrayEquals(OVERFLOW_4_BIT_ANS, unsignedAdd4Bit.readOutputs(), "4 Bit Add With Overflow");

        unsignedAdd4Bit.assignInputs(ADD_ZEROES);
        unsignedAdd4Bit.evaluate();
        assertArrayEquals(ADD_ZEROES_ANS, unsignedAdd4Bit.readOutputs(), "4 Bit Add Zeroes");

        unsignedAdd4Bit.assignInputs(ADD_FFS);
        unsignedAdd4Bit.evaluate();
        assertArrayEquals(ADD_FFS_ANS, unsignedAdd4Bit.readOutputs(), "4 Bit Add 0xFF and 0xFF");
    }
}
