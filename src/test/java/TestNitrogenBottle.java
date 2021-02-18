import factory.NitrogenBottleFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestNitrogenBottle {

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
        componentPort = NitrogenBottleFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = NitrogenBottleFactory.build();

        assertDoesNotThrow(() -> {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", int.class);
            assertNotNull(takeOutMethod);

            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            assertNotNull(refillMethod);

            Method refillMethod2 = componentPort.getClass().getDeclaredMethod("refill", int.class);
            assertNotNull(refillMethod2);
        });
    }

    @Test
    @Order(3)
    public void takeOut() {
        componentPort = NitrogenBottleFactory.build();

        assertDoesNotThrow(() -> {
            Method upMethod = componentPort.getClass().getDeclaredMethod("takeOut", int.class);
            int amount = (int) upMethod.invoke(componentPort, 150);
            assertEquals(100, amount);
        });
    }

    @Test
    @Order(4)
    public void refill() {
        componentPort = NitrogenBottleFactory.build();

        assertDoesNotThrow(() -> {
            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill", int.class);
            int amount = (int) refillMethod.invoke(componentPort, 50);
            assertEquals(300, amount);
        });
        assertDoesNotThrow(() -> {
            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            int amount = (int) refillMethod.invoke(componentPort);
            assertEquals(250, amount);
        });
    }
}
