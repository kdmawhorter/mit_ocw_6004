package arithmetic;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class UnsignedAddTest {

    private UnsignedAdd unsignedAdd;

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
        unsignedAdd = new UnsignedAdd("4 Bit Add", 4);
    }

    @Test
    void unsignedAddTest() {
        unsignedAdd.assignInputs(NO_OVERFLOW_4_BIT);
        unsignedAdd.evaluate();
        assertArrayEquals(NO_OVERFLOW_4_BIT_ANS, unsignedAdd.readOutputs(), "4 Bit Add No Overflow");

        unsignedAdd.assignInputs(OVERFLOW_4_BIT);
        unsignedAdd.evaluate();
        assertArrayEquals(OVERFLOW_4_BIT_ANS, unsignedAdd.readOutputs(), "4 Bit Add With Overflow");

        unsignedAdd.assignInputs(ADD_ZEROES);
        unsignedAdd.evaluate();
        assertArrayEquals(ADD_ZEROES_ANS, unsignedAdd.readOutputs(), "4 Bit Add Zeroes");

        unsignedAdd.assignInputs(ADD_FFS);
        unsignedAdd.evaluate();
        assertArrayEquals(ADD_FFS_ANS, unsignedAdd.readOutputs(), "4 Bit Add 0xFF and 0xFF");
    }
}
