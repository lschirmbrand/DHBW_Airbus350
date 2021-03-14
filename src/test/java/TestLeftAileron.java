import factory.LeftAileronFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLeftAileron {
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
        componentPort = LeftAileronFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = LeftAileronFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            assertNotNull(neutralMethod);

            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            assertNotNull(fullDownMethod);

            Method fullUpMethod = componentPort.getClass().getDeclaredMethod("fullUp");
            assertNotNull(fullUpMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down");
            assertNotNull(downMethod);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up");
            assertNotNull(upMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void neutral() {
        componentPort = LeftAileronFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, degree);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void fullDown() {
        componentPort = LeftAileronFactory.build();
        try {
            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            int degree = (int) fullDownMethod.invoke(componentPort);
            assertEquals(-1, Math.signum(degree));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void fullUp() {
        componentPort = LeftAileronFactory.build();
        try {
            Method fullUpMethod = componentPort.getClass().getDeclaredMethod("fullUp");
            int degree = (int) fullUpMethod.invoke(componentPort);
            assertEquals(1, Math.signum(degree));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void down() {
        componentPort = LeftAileronFactory.build();
        try {
            Method downMethod = componentPort.getClass().getDeclaredMethod("down");
            int degree = (int) downMethod.invoke(componentPort);
            assertEquals(-1, Math.signum(degree));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void up() {
        componentPort = LeftAileronFactory.build();
        try {
            Method upMethod = componentPort.getClass().getDeclaredMethod("up");
            int degree = (int) upMethod.invoke(componentPort);
            assertEquals(1, Math.signum(degree));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}