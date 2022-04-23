package core_architecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PortTest extends MitOcwTest{

    private Port port;

    @BeforeEach
    void init() {
        port = new Port("Test Port");
    }

    @Test
    void funcTest() {
        port.setInput(DigitalCircuit.VDD);
        port.readPort();
        assertEquals(ConnectionType.POWER, port.getOutput().getStatus(), "Hot Port");

        port.setInput(DigitalCircuit.GND);
        port.readPort();
        assertEquals(ConnectionType.GROUND, port.getOutput().getStatus(), "Cold Port");

        port.setInput(DigitalCircuit.VDD);
        port.readPort();
        assertEquals(ConnectionType.POWER, port.getOutput().getStatus(), "Hot Port 2");
    }
}
