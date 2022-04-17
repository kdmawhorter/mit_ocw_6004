package core_architecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NfetTest {

    private Nfet nfet;

    private CircuitNode externalOutput;
    private Nfet nfetExternalOutput;

    @BeforeEach
    void init() {
        nfet = new Nfet("NFET Test");

        externalOutput = new CircuitNode("External Node");
        nfetExternalOutput = new Nfet("External Output NFET Test", externalOutput);
    }

    @Test
    void properGateSettingTest() {
        // Transistor Count Tests
        assertEquals(1, nfet.getTransistorCount(), "NFET Transistor Count Test");

        // NFET Testing
        nfet.turnOn();
        nfet.evaluate();
        assertEquals(ConnectionType.GROUND, nfet.getOutput().getStatus(),
                "Nfet output ground with gate power on");

        nfet.turnOff();
        nfet.evaluate();
        assertEquals(ConnectionType.FLOATING, nfet.getOutput().getStatus(),
                "Nfet output floating with gate power off");

        nfetExternalOutput.turnOn();
        nfetExternalOutput.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(),
                "External Output Nfet output ground with gate power on");

        nfetExternalOutput.turnOff();
        nfetExternalOutput.evaluate();
        assertEquals(ConnectionType.FLOATING, externalOutput.getStatus(),
                "Nfet output floating with gate power off");
    }
}
