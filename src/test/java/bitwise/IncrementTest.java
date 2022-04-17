package bitwise;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class IncrementTest {

    private Increment inc2Bit;
    private Increment inc4Bit;

    private static final CircuitNode[] ZERO_INC = {GND, GND, GND, GND };
    private static final CircuitNode[] SEVEN_INC = {GND, VDD, VDD, VDD };
    private static final CircuitNode[] F_INC = {VDD, VDD, VDD, VDD };
    
    private static final Boolean[] ZERO_INC_ANS = {false, false, false, true, false};
    private static final Boolean[] SEVEN_INC_ANS = {true, false, false, false, false};
    private static final Boolean[] F_INC_ANS = {false, false, false, false, true};


    @BeforeEach
    void init() {
        inc2Bit = new Increment("2 Bit Increment Test", 2);
        inc4Bit = new Increment("4 Bit Increment Test", 4);
    }


    @Test
    void incTest() {
        // Transistor Count Tests
        assertEquals(56, inc2Bit.getTransistorCount(), "2 Bit Incrementer Transistor Count Test");
        assertEquals(112, inc4Bit.getTransistorCount(), "4 Bit Incrementer Transistor Count Test");

        // Increment Testing
        inc4Bit.assignInputs(ZERO_INC);
        inc4Bit.evaluate();
        assertArrayEquals(ZERO_INC_ANS, inc4Bit.readOutputs(), "Increment 0");

        inc4Bit.assignInputs(SEVEN_INC);
        inc4Bit.evaluate();
        assertArrayEquals(SEVEN_INC_ANS, inc4Bit.readOutputs(), "Increment 7");

        inc4Bit.assignInputs(F_INC);
        inc4Bit.evaluate();
        assertArrayEquals(F_INC_ANS, inc4Bit.readOutputs(), "Increment 15");
    }
}
