package bitwise;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class HalfAdderTest {

    private HalfAdder halfAdder00;
    private HalfAdder halfAdder01;
    private HalfAdder halfAdder10;
    private HalfAdder halfAdder11;

    private final static CircuitNode[] TEST_00 = {GND, GND};
    private final static CircuitNode[] TEST_01 = {GND, VDD};
    private final static CircuitNode[] TEST_10 = {VDD, GND};
    private final static CircuitNode[] TEST_11 = {VDD, VDD};


    @BeforeEach
    void init() {
        halfAdder00 = new HalfAdder("Half Adder Test 00", GND, GND);
        halfAdder01 = new HalfAdder("Half Adder Test 01", GND, VDD);
        halfAdder10 = new HalfAdder("Half Adder Test 10", VDD, GND);
        halfAdder11 = new HalfAdder("Half Adder Test 11", VDD, VDD);

    }

    @Test
    void funcTest() {
        halfAdder00.evaluate();
        assertFalse(halfAdder00.readOutput(0), "Half Adder 00 High Bit");
        assertFalse(halfAdder00.readOutput(1), "Half Adder 00 Low Bit");

        halfAdder01.evaluate();
        assertFalse(halfAdder01.readOutput(0), "Half Adder 01 High Bit");
        assertTrue(halfAdder01.readOutput(1), "Half Adder 01 Low Bit");

        halfAdder10.evaluate();
        assertFalse(halfAdder10.readOutput(0), "Half Adder 10 High Bit");
        assertTrue(halfAdder10.readOutput(1), "Half Adder 10 Low Bit");

        halfAdder11.evaluate();
        assertTrue(halfAdder11.readOutput(0), "Half Adder 11 High Bit");
        assertFalse(halfAdder11.readOutput(1), "Half Adder 11 Low Bit");
    }
}
