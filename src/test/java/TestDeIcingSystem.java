import factory.AirFlowSensorFactory;
import factory.BatteryFactory;
import factory.DeIcingSystemFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDeIcingSystem {

    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    @Order(1)
    public void factory() {
        componentPort = DeIcingSystemFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = DeIcingSystemFactory.build();

        assertDoesNotThrow(() -> {
            Method activateMethod = componentPort.getClass().getDeclaredMethod("activate");
            assertNotNull(activateMethod);

            Method deactivateMethod = componentPort.getClass().getDeclaredMethod("deactivate");
            assertNotNull(deactivateMethod);

            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            assertNotNull(refillMethod);

            Method deIce = componentPort.getClass().getDeclaredMethod("deIce", int.class);
            assertNotNull(deIce);
        });
    }

    @Test
    @Order(3)
    public void activate() {
        componentPort = DeIcingSystemFactory.build();

        assertDoesNotThrow(() -> {
            Method activateMethod = componentPort.getClass().getDeclaredMethod("activate");
            boolean isActive = (boolean) activateMethod.invoke(componentPort);
            assertTrue(isActive);
        });
    }

    @Test
    @Order(4)
    public void deactivate() {
        componentPort = DeIcingSystemFactory.build();

        assertDoesNotThrow(() -> {
            Method deactivateMethod = componentPort.getClass().getDeclaredMethod("deactivate");
            boolean isActive = (boolean) deactivateMethod.invoke(componentPort);
            assertFalse(isActive);
        });
    }

    @Test
    @Order(4)
    public void refill() {
        componentPort = DeIcingSystemFactory.build();

        assertDoesNotThrow(() -> {
            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            int deIceAmount = (int) refillMethod.invoke(componentPort);
            assertEquals(1000, deIceAmount);
        });
    }

    @Test
    @Order(4)
    public void deIce() {
        componentPort = DeIcingSystemFactory.build();

        assertDoesNotThrow(() -> {
            Method deIceMethod = componentPort.getClass().getDeclaredMethod("deIce", int.class);
            int deIceAmount = (int) deIceMethod.invoke(componentPort, 100);
            assertEquals(900, deIceAmount);
        });
    }
}
