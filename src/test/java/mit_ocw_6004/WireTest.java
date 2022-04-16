package mit_ocw_6004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WireTest {

    private Wire wire;

    @BeforeEach
    void init() {
        wire = new Wire("Test Wire");
    }

    @Test
    void funcTest() {
        wire.turnOn();
        wire.evaluate();
        assertTrue(wire.readOutput(), "Hot Wire");

        wire.turnOff();
        wire.evaluate();
        assertFalse(wire.readOutput(), "Cold Wire");

        wire.turnOn();
        wire.evaluate();
        assertTrue(wire.readOutput(), "Hot Wire 2");
    }
}
