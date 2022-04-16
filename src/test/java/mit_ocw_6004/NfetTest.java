package mit_ocw_6004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NfetTest {

    private Nfet nfet;

    @BeforeEach
    void init() {
        CircuitNode outputNode = new CircuitNode("TestOutput");
        nfet = new Nfet("Test_Nfet", outputNode);

    }

    @Test
    void properGateSettingTest() {
        nfet.turnOn();
        nfet.evaluate();
        assertEquals(ConnectionType.GROUND, nfet.getOutput().getStatus(),
                "Nfet output floating with gate power off");

        nfet.turnOff();
        nfet.evaluate();
        assertEquals(ConnectionType.FLOATING, nfet.getOutput().getStatus(),
                "Nfet output floating with gate power off");
    }
}
