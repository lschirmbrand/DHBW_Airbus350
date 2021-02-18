import factory.APUFactory;
import factory.GearFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGear {

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
        componentPort = GearFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = GearFactory.build();

        assertDoesNotThrow(() -> {
            Method upMethod = componentPort.getClass().getDeclaredMethod("up");
            assertNotNull(upMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down");
            assertNotNull(downMethod);

            Method setBrakeMethod = componentPort.getClass().getDeclaredMethod("setBrake");
            assertNotNull(setBrakeMethod);

            Method setBrakePercentageMethod = componentPort.getClass().getDeclaredMethod("setBrake", int.class);
            assertNotNull(setBrakePercentageMethod);

            Method releaseBrakeMethod = componentPort.getClass().getDeclaredMethod("releaseBrake");
        });
    }

    @Test
    @Order(3)
    public void up() {
        componentPort = GearFactory.build();

        assertDoesNotThrow(() -> {
            Method upMethod = componentPort.getClass().getDeclaredMethod("up");
            boolean isDown = (boolean) upMethod.invoke(componentPort);
            assertFalse(isDown);
        });
    }

    @Test
    @Order(4)
    public void down() {
        componentPort = GearFactory.build();

        assertDoesNotThrow(() -> {
            Method downMethod = componentPort.getClass().getDeclaredMethod("down");
            boolean isDown = (boolean) downMethod.invoke(componentPort);
            assertTrue(isDown);
        });
    }

    @Test
    @Order(5)
    public void setBrake() {
        componentPort = GearFactory.build();

        assertDoesNotThrow(() -> {
            Method setBrakeMethod = componentPort.getClass().getDeclaredMethod("setBrake");
            int brakePercentage = (int) setBrakeMethod.invoke(componentPort);
            assertEquals(100, brakePercentage);
        });
    }

    @Test
    @Order(6)
    public void setBrakePercentage() {
        componentPort = GearFactory.build();

        assertDoesNotThrow(() -> {
            Method setBrakePercentageMethod = componentPort.getClass().getDeclaredMethod("setBrake", int.class);
            int brakePercentage = (int) setBrakePercentageMethod.invoke(componentPort, 30);
            assertEquals(30, brakePercentage);
        });
    }

    @Test
    @Order(7)
    public void releaseBrake() {
        componentPort = GearFactory.build();

        assertDoesNotThrow(() -> {
            Method releaseBrakeMethod = componentPort.getClass().getDeclaredMethod("releaseBrake");
            int brakePercentage = (int) releaseBrakeMethod.invoke(componentPort);
            assertEquals(0, brakePercentage);
        });
    }
}
