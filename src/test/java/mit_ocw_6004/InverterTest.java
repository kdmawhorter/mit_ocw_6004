package mit_ocw_6004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InverterTest {

    private Inverter inverter;
    private Inverter externalOutputInverter;
    private CircuitNode externalOutput;

    @BeforeEach
    void init() {
        inverter = new Inverter("Test Inverter");
        externalOutput = new CircuitNode("External Output");
        externalOutputInverter= new Inverter("Test External Output Inverter", DigitalCircuit.GND, externalOutput);
    }

    @Test
    void inverterEvaluationTest() {
        inverter.turnOff();
        inverter.evaluate();
        assertTrue(inverter.readOutput(), "Inverter input off, output on");

        inverter.turnOn();
        inverter.evaluate();
        assertFalse(inverter.readOutput(), "Inverter input on, output off");

        inverter.turnOff();
        inverter.evaluate();
        assertTrue(inverter.readOutput(), "Inverter input off, output on again");

        externalOutputInverter.turnOff();
        externalOutputInverter.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(),
                "External output inverter off, external output on");

        externalOutputInverter.turnOn();
        externalOutputInverter.evaluate();
        assertEquals(ConnectionType.GROUND, externalOutput.getStatus(),
                "External output inverter on, external output off");

        externalOutputInverter.turnOff();
        externalOutputInverter.evaluate();
        assertEquals(ConnectionType.POWER, externalOutput.getStatus(),
                "External output inverter off, external output on");
    }
}
