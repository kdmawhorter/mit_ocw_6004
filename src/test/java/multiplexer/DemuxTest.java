package multiplexer;

import core_architecture.CircuitNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.VDD;
import static core_architecture.DigitalCircuit.GND;

public class DemuxTest {

    private Demux demux4To16;

    private static final CircuitNode[] TEST_416_WRITE_F_0  = { VDD, VDD, VDD, VDD, GND, GND };
    private static final CircuitNode[] TEST_416_WRITE_F_1  = { VDD, VDD, VDD, VDD, GND, VDD };
    private static final CircuitNode[] TEST_416_WRITE_F_2  = { VDD, VDD, VDD, VDD, VDD, GND };
    private static final CircuitNode[] TEST_416_WRITE_F_3  = { VDD, VDD, VDD, VDD, VDD, VDD };

    private static final CircuitNode[] TEST_416_WRITE_0_0  = { GND, GND, GND, GND, GND, GND };
    private static final CircuitNode[] TEST_416_WRITE_0_1  = { GND, GND, GND, GND, GND, VDD };
    private static final CircuitNode[] TEST_416_WRITE_0_2  = { GND, GND, GND, GND, VDD, GND };
    private static final CircuitNode[] TEST_416_WRITE_0_3  = { GND, GND, GND, GND, VDD, VDD };


    private static final Boolean[] TEST_416_A_0 = { false, false, false, false, false, false, false, false,
                                                    false, false, false, false, false, false, false, false };
    private static final Boolean[] TEST_416_1_F = { false, false, false, false, true, true, true, true,
                                                    false, false, false, false, false, false, false, false };
    private static final Boolean[] TEST_416_2_F = { false, false, false, false, false, false, false, false,
                                                    true, true, true, true, false, false, false, false };
    private static final Boolean[] TEST_416_3_F = { false, false, false, false, false, false, false, false,
                                                    false, false, false, false, true, true, true, true };

    @BeforeEach
    void init() {
        demux4To16 = new Demux("Demux 4 to 16 Test", 4, 4);
    }

    @Test
    void demux4To16Test() {
        // Transistor Count Tests
        assertEquals((4*2+10)*(4-1)*4+2*2, demux4To16.getTransistorCount(),
                "4 Choice, 4 Bit Mux Transistor Count Test");

        demux4To16.evaluate();
        assertArrayEquals(TEST_416_A_0, demux4To16.readOutputs());

        // Test Write 0
        demux4To16.assignInputs(TEST_416_WRITE_0_0);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_A_0, demux4To16.readOutputs(), "Test Write 0: F");

        demux4To16.assignInputs(TEST_416_WRITE_F_0);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_A_0, demux4To16.readOutputs(), "Test Write 0: 0");

        //Test Write 1
        demux4To16.assignInputs(TEST_416_WRITE_F_1);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_1_F, demux4To16.readOutputs(), "Test Write 1: F");

        demux4To16.assignInputs(TEST_416_WRITE_0_1);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_A_0, demux4To16.readOutputs(), "Test Write 1: 0");

        //Test Write 2
        demux4To16.assignInputs(TEST_416_WRITE_F_2);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_2_F, demux4To16.readOutputs(), "Test Write 2: F");

        demux4To16.assignInputs(TEST_416_WRITE_0_2);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_A_0, demux4To16.readOutputs(), "Test Write 2: 0");

        //Test Write 3
        demux4To16.assignInputs(TEST_416_WRITE_F_3);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_3_F, demux4To16.readOutputs(), "Test Write 3: F");

        demux4To16.assignInputs(TEST_416_WRITE_0_3);
        demux4To16.evaluate();
        assertArrayEquals(TEST_416_A_0, demux4To16.readOutputs(), "Test Write 3: 0");
    }
}
