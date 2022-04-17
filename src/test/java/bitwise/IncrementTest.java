package bitwise;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class IncrementTest {

    private Increment increment;

    private static final CircuitNode[] ZERO_INC = {GND, GND, GND, GND };
    private static final CircuitNode[] SEVEN_INC = {GND, VDD, VDD, VDD };
    private static final CircuitNode[] F_INC = {VDD, VDD, VDD, VDD };
    
    private static final Boolean[] ZERO_INC_ANS = {false, false, false, true, false};
    private static final Boolean[] SEVEN_INC_ANS = {true, false, false, false, false};
    private static final Boolean[] F_INC_ANS = {false, false, false, false, true};


    @BeforeEach
    void init() {
        increment = new Increment("4 Bit Increment Test", 4);
    }

    @Test
    void incTest() {
        increment.assignInputs(ZERO_INC);
        increment.evaluate();
        assertArrayEquals(ZERO_INC_ANS, increment.readOutputs(), "Increment 0");

        increment.assignInputs(SEVEN_INC);
        increment.evaluate();
        assertArrayEquals(SEVEN_INC_ANS, increment.readOutputs(), "Increment 7");

        increment.assignInputs(F_INC);
        increment.evaluate();
        assertArrayEquals(F_INC_ANS, increment.readOutputs(), "Increment 15");
    }
}
