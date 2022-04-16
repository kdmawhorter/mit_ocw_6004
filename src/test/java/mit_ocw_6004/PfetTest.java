package mit_ocw_6004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PfetTest {

    private Pfet pfet;

    @BeforeEach
    void init() {
        CircuitNode outputNode = new CircuitNode("TestOutput");
        pfet = new Pfet("Test_Nfet", outputNode);

    }

    @Test
    void properGateSettingTest() {
        pfet.turnOff();
        pfet.evaluate();
        assertEquals(ConnectionType.POWER, pfet.getOutput().getStatus(),
                "Nfet output floating with gate power off");

        pfet.turnOn();
        pfet.evaluate();
        assertEquals(ConnectionType.FLOATING, pfet.getOutput().getStatus(),
                "Nfet output floating with gate power off");
    }
}
