import factory.RadarFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestRadar {
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
        componentPort = RadarFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = RadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method sendMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(sendMethod);

            assertEquals(4, componentPort.getClass().getDeclaredMethods().length);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = RadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(4)
    public void off() {
        componentPort = RadarFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(5)
    public void version() {
        componentPort = RadarFactory.build();
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
    @Order(6)
    public void scan() {
        componentPort = RadarFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "Air");
            assertFalse(result);
            result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
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
