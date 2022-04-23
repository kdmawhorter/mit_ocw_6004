package logic_gates;

import core_architecture.CircuitNode;

import core_architecture.ConnectionType;
import core_architecture.MitOcwTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static core_architecture.DigitalCircuit.GND;
import static core_architecture.DigitalCircuit.VDD;

public class InverterGateTest extends MitOcwTest {

    private InverterGate inv;
    private InverterGate invExternalNode;

    private CircuitNode externalNode;


    @BeforeEach
    void init() {
        inv = new InverterGate("Inverter Gate");

        externalNode = new CircuitNode("External Node");
        invExternalNode = new InverterGate("Inverter Gate External Node");
        invExternalNode.assignOutput(externalNode);
    }

    @Test
    void inverterEvaluationTest() {
        // Transistor Count Tests
        assertEquals(2, inv.getTransistorCount(), "1 Bit Inverter Transistor Count Testing");

        // 1 Bit Inverter Test
        inv.assignInput(VDD);
        inv.evaluate();
        assertFalse(inv.readOutput(), "Invert VDD Test");

        inv.assignInput(GND);
        inv.evaluate();
        assertTrue(inv.readOutput(), "Invert GND Test");

        // 1 Bit External Inverter Test
        invExternalNode.turnOn();
        invExternalNode.evaluate();
        assertEquals(ConnectionType.GROUND, externalNode.getStatus(), "Invert VDD External Test");

        invExternalNode.turnOff();
        invExternalNode.evaluate();
        assertEquals(ConnectionType.POWER, externalNode.getStatus(), "Invert GND External Test");
    }
}
