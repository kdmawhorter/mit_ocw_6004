package memory;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class MemoryModuleTest {

    private MemoryModule memoryModule;

    private static final CircuitNode[] TEST_0_READ_4 = {VDD, GND, GND, VDD, GND, VDD, GND, GND};
    
    private static final CircuitNode[] TEST_1_READ_4 = {VDD, GND, GND, VDD, GND, VDD, GND, GND};
    private static final CircuitNode[] TEST_1_WRITE_4_9 = {VDD, GND, GND, VDD, VDD, VDD, GND, GND};
    
    private static final CircuitNode[] TEST_2_READ_7 = {GND, GND, GND, GND, GND, VDD, VDD, VDD};
    private static final CircuitNode[] TEST_2_WRITE_7_A = {VDD, GND, VDD, GND, VDD, VDD, VDD, VDD};
    private static final CircuitNode[] TEST_2_WRITE_7_4 = {GND, VDD, GND, GND, VDD, VDD, VDD, VDD};
    
    private static final CircuitNode[] TEST_3_READ_4 = {VDD, GND, GND, VDD, GND, VDD, GND, GND};
    private static final CircuitNode[] TEST_3_WRITE_4_0 = {GND, GND, GND, GND, VDD, VDD, GND, GND};

    private static final CircuitNode[] TEST_4_READ_7 = {GND, GND, GND, GND, GND, VDD, VDD, VDD};

    private static final CircuitNode[] TEST_5_READ_0 = {GND, GND, GND, GND, GND, GND, GND, GND};
    private static final CircuitNode[] TEST_5_WRITE_0_F = {VDD, VDD, VDD, VDD, VDD, GND, GND, GND};

    private static final Boolean[] OUTPUT_0 = {false, false, false, false};
    private static final Boolean[] OUTPUT_4 = {false, true, false, false};
    private static final Boolean[] OUTPUT_9 = {true, false, false, true};
    private static final Boolean[] OUTPUT_A = {true, false, true, false};

    @BeforeEach
    void init() {
        memoryModule = new MemoryModule("Memory Module Test", 8, 4);
    }

    @Test
    void memModuleTestTest() {
        // Memory Module Transistor Count Test
        assertEquals(872, memoryModule.getTransistorCount(),
                "8 Choice, 4 Bit Memory Module Transistor Count Test");

        memoryModule.assignInputs(TEST_0_READ_4);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Read 4: 0");

        memoryModule.assignInputs(TEST_1_WRITE_4_9);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Write 4: 9");

        memoryModule.assignInputs(TEST_1_READ_4);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_9, memoryModule.readOutputs(), "Read 4: 9");

        memoryModule.assignInputs(TEST_2_READ_7);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Read 7: 0");

        memoryModule.assignInputs(TEST_2_WRITE_7_4);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Write 7: 4");

        memoryModule.assignInputs(TEST_2_READ_7);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_4, memoryModule.readOutputs(), "Read 7: 4");

        memoryModule.assignInputs(TEST_2_WRITE_7_A);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Write 7: A");

        memoryModule.assignInputs(TEST_2_READ_7);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_A, memoryModule.readOutputs(), "Read 7: A");

        memoryModule.assignInputs(TEST_3_READ_4);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_9, memoryModule.readOutputs(), "Read 4: 9");

        memoryModule.assignInputs(TEST_3_WRITE_4_0);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Read 4: 9");

        memoryModule.assignInputs(TEST_3_READ_4);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Read 4: 9");

        memoryModule.assignInputs(TEST_4_READ_7);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_A, memoryModule.readOutputs(), "Read 7: A");

        memoryModule.assignInputs(TEST_5_READ_0);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Read 0: 0");

        memoryModule.assignInputs(TEST_5_WRITE_0_F);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Write 0: F");

        memoryModule.assignInputs(TEST_5_READ_0);
        memoryModule.evaluate();
        assertArrayEquals(OUTPUT_0, memoryModule.readOutputs(), "Read 0: 0");
    }
}
