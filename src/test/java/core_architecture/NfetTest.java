package core_architecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NfetTest extends MitOcwTest{

    private Nfet nfet;

    private CircuitNode externalOutput;
    private Nfet nfetExternalOutput;

    @BeforeEach
    void init() {
        nfet = new Nfet("NFET Test", new CircuitNode("NFET Drain"), new CircuitNode("NFET Gate"),
                DigitalCircuit.GND);

        externalOutput = new CircuitNode("External Node");
        nfetExternalOutput = new Nfet("External Output NFET Test", externalOutput,
                new CircuitNode("NFET Gate"), DigitalCircuit.GND);
    }

    @Test
    void properGateSettingTest() {
        // Transistor Count Tests
        assertEquals(1, nfet.getTransistorCount(), "NFET Transistor Count Test");

        // NFET Testing
        nfet.turnOn();
        nfet.evaluate();
        assertEquals(ConnectionType.GROUND, nfet.getOutput().getStatus(),
                "NFET output ground with gate power on");

        nfet.turnOff();
        nfet.evaluate();
        assertEquals(ConnectionType.FLOATING, nfet.getOutput().getStatus(),
                "NFET output floating with gate power off");

        nfetExternalOutput.turnOn();
        nfetExternalOutput.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(),
                "External Output NFET output ground with gate power on");

        nfetExternalOutput.turnOff();
        nfetExternalOutput.evaluate();
        assertEquals(ConnectionType.FLOATING, externalOutput.getStatus(),
                "NFET output floating with gate power off");
    }
}
