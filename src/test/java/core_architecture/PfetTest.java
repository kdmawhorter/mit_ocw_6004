package core_architecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PfetTest {

    private Pfet pfet;

    private CircuitNode externalOutput;
    private Pfet pfetExternalOutput;

    @BeforeEach
    void init() {
        pfet = new Pfet("NFET Test", new CircuitNode("Pfet Drain"), new CircuitNode("Pfet Gate"),
                DigitalCircuit.VDD);

        externalOutput = new CircuitNode("External Node");
        pfetExternalOutput = new Pfet("External Output NFET Test", externalOutput,
                new CircuitNode("Pfet Gate"), DigitalCircuit.VDD);
    }

    @Test
    void properGateSettingTest() {
        // Transistor Count Tests
        assertEquals(1, pfet.getTransistorCount(), "PFET Transistor Count Test");

        // PFET Testing
        pfet.turnOn();
        pfet.evaluate();
        assertEquals(ConnectionType.FLOATING, pfet.getOutput().getStatus(),
                "Pfet output ground with gate power on");

        pfet.turnOff();
        pfet.evaluate();
        assertEquals(ConnectionType.POWER, pfet.getOutput().getStatus(),
                "Pfet output floating with gate power off");

        pfetExternalOutput.turnOn();
        pfetExternalOutput.evaluate();
        assertEquals(ConnectionType.FLOATING, externalOutput.getStatus(),
                "External Output Pfet output ground with gate power on");

        pfetExternalOutput.turnOff();
        pfetExternalOutput.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(),
                "Pfet output floating with gate power off");
    }
}
