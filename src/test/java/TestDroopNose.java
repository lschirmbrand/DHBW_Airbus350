import factory.DroopNoseFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestDroopNose {
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
        componentPort = DroopNoseFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = DroopNoseFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            assertNotNull(neutralMethod);

            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            assertNotNull(fullDownMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            assertNotNull(downMethod);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            assertNotNull(upMethod);

            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            assertEquals(5, componentPort.getClass().getDeclaredMethods().length);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = DroopNoseFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String result = (String) versionMethod.invoke(componentPort);
            assertFalse(result.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(4)
    public void neutral() {
        componentPort = DroopNoseFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int result = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(5)
    public void fullDown() {
        componentPort = DroopNoseFactory.build();
        try {
            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            int result = (int) fullDownMethod.invoke(componentPort);
            assertEquals(-90, result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(6)
    public void down() {
        componentPort = DroopNoseFactory.build();
        try {
            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            int result = (int) downMethod.invoke(componentPort, 20);
            assertEquals(-20, result);
            result = (int) downMethod.invoke(componentPort, 70);
            assertEquals(-90, result);
            result = (int) downMethod.invoke(componentPort, 20);
            assertEquals(-90, result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(7)
    public void up() {
        componentPort = DroopNoseFactory.build();
        try {
            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            int result = (int) upMethod.invoke(componentPort, 20);
            assertEquals(20, result);
            result = (int) upMethod.invoke(componentPort, 70);
            assertEquals(90, result);
            result = (int) upMethod.invoke(componentPort, 20);
            assertEquals(90, result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}
