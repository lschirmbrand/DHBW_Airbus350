import factory.APUFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAPU {

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
        componentPort = APUFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = APUFactory.build();

        assertDoesNotThrow(() -> {
            Method startMethod = componentPort.getClass().getDeclaredMethod("start");
            assertNotNull(startMethod);

            Method shutdownMethod = componentPort.getClass().getDeclaredMethod("shutdown");
            assertNotNull(shutdownMethod);

            Method increaseRPMMethod = componentPort.getClass().getDeclaredMethod("increaseRPM", int.class);
            assertNotNull(increaseRPMMethod);

            Method decreaseRPMMethod = componentPort.getClass().getDeclaredMethod("decreaseRPM", int.class);
            assertNotNull(decreaseRPMMethod);
        });
    }

    @Test
    @Order(3)
    public void start() {
        componentPort = APUFactory.build();
        try {
            Method startMethod = componentPort.getClass().getDeclaredMethod("start");
            boolean isStarted = (boolean) startMethod.invoke(componentPort);
            assertTrue(isStarted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void shutdown() {
        componentPort = APUFactory.build();
        try {
            Method shutdownMethod = componentPort.getClass().getDeclaredMethod("shutdown");
            shutdownMethod.invoke(componentPort);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void increaseRPM() {
        componentPort = APUFactory.build();
        try {
            Method increaseRPMMethod = componentPort.getClass().getDeclaredMethod("increaseRPM", int.class);
            int rpm = (int) increaseRPMMethod.invoke(componentPort, 1000);
            assertEquals(1000, rpm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void decreaseRPM() {
        componentPort = APUFactory.build();
        try {
            Method increaseRPMMethod = componentPort.getClass().getDeclaredMethod("increaseRPM", int.class);
            Method decreaseRPMMethod = componentPort.getClass().getDeclaredMethod("decreaseRPM", int.class);

            // before we can decrease we have to increase
            increaseRPMMethod.invoke(componentPort, 1000);

            int rpm = (int) decreaseRPMMethod.invoke(componentPort, 500);
            assertEquals(500, rpm);

            // should not go under 0
            rpm = (int) decreaseRPMMethod.invoke(componentPort, 1000);
            assertEquals(0, rpm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
