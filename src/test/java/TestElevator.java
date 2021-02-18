import factory.ElevatorFactory;
import factory.HydraulicPumpFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestElevator {

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
        componentPort = ElevatorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = ElevatorFactory.build();

        assertDoesNotThrow(() -> {
            Method fullUpMethod = componentPort.getClass().getDeclaredMethod("fullUp");
            assertNotNull(fullUpMethod);

            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            assertNotNull(fullDownMethod);

            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            assertNotNull(neutralMethod);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            assertNotNull(upMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            assertNotNull(downMethod);
        });
    }

    @Test
    @Order(3)
    public void fullUp() {
        componentPort = ElevatorFactory.build();

        assertDoesNotThrow(() -> {
            Method fullUpMethod = componentPort.getClass().getDeclaredMethod("fullUp");
            int degree = (int) fullUpMethod.invoke(componentPort);
            assertEquals(degree, 180);
        });
    }

    @Test
    @Order(4)
    public void fullDown() {
        componentPort = ElevatorFactory.build();

        assertDoesNotThrow(() -> {
            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            int degree = (int) fullDownMethod.invoke(componentPort);
            assertEquals(degree, 45);
        });
    }

    @Test
    @Order(5)
    public void up() {
        componentPort = ElevatorFactory.build();

        assertDoesNotThrow(() -> {
            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            int degree = (int) upMethod.invoke(componentPort, 10);
            assertEquals(degree, 100);
        });
    }

    @Test
    @Order(6)
    public void down() {
        componentPort = ElevatorFactory.build();

        assertDoesNotThrow(() -> {
            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            int degree = (int) downMethod.invoke(componentPort, 10);
            assertEquals(degree, 80);
        });
    }

    @Test
    @Order(7)
    public void neutral() {
        componentPort = ElevatorFactory.build();

        assertDoesNotThrow(() -> {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(componentPort);
            assertEquals(90, degree);
        });
    }
}
