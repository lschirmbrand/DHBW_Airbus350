import factory.RudderFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRudder {
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
        componentPort = RudderFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = RudderFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            assertNotNull(neutralMethod);

            Method fullLeftMethod = componentPort.getClass().getDeclaredMethod("fullLeft");
            assertNotNull(fullLeftMethod);

            Method fullRightMethod = componentPort.getClass().getDeclaredMethod("fullRight");
            assertNotNull(fullRightMethod);

            Method leftMethod = componentPort.getClass().getDeclaredMethod("left");
            assertNotNull(leftMethod);

            Method rightMethod = componentPort.getClass().getDeclaredMethod("right");
            assertNotNull(rightMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void neutral() {
        componentPort = RudderFactory.build();
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
    public void fullLeft() {
        componentPort = RudderFactory.build();
        try {
            Method fullLeftMethod = componentPort.getClass().getDeclaredMethod("fullLeft");
            int degree = (int) fullLeftMethod.invoke(componentPort);
            assertEquals(-1, Math.signum(degree));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void fullRight() {
        componentPort = RudderFactory.build();
        try {
            Method fullRightMethod = componentPort.getClass().getDeclaredMethod("fullRight");
            int degree = (int) fullRightMethod.invoke(componentPort);
            assertEquals(1, Math.signum(degree));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void left() {
        componentPort = RudderFactory.build();
        try {
            Method leftMethod = componentPort.getClass().getDeclaredMethod("left");
            int degree = (int) leftMethod.invoke(componentPort);
            assertEquals(-1, Math.signum(degree));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void right() {
        componentPort = RudderFactory.build();
        try {
            Method rightMethod = componentPort.getClass().getDeclaredMethod("right");
            int degree = (int) rightMethod.invoke(componentPort);
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