import factory.TCASFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestTCAS {
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
        componentPort = TCASFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = TCASFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method sendMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(sendMethod);

            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmMethod);

            Method determineAltitudeMethod = componentPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            assertNotNull(determineAltitudeMethod);

            Method setAltitudeMethod = componentPort.getClass().getDeclaredMethod("setAltitude", int.class);
            assertNotNull(setAltitudeMethod);

            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            assertNotNull(connectMethod);

            assertEquals(componentPort.getClass().getDeclaredMethods().length, 8);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = TCASFactory.build();
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
        componentPort = TCASFactory.build();
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
        componentPort = TCASFactory.build();
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
        componentPort = TCASFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "Enemy Ship");
            assertTrue(result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(7)
    public void connect() {
        componentPort = TCASFactory.build();
        try {
            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            boolean result = (boolean) connectMethod.invoke(componentPort, "Astra-8");
            assertTrue(result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(8)
    public void alarm() {
        componentPort = TCASFactory.build();
        try {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            boolean result = (boolean) alarmMethod.invoke(componentPort);
            assertTrue(result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(9)
    public void determineAltitude() {
        componentPort = TCASFactory.build();
        try {
            Method determineAltitudeMethod = componentPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            int result = (int) determineAltitudeMethod.invoke(componentPort, "Earth");
            assertEquals(result, 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(10)
    public void setAltitude() {
        componentPort = TCASFactory.build();
        try {
            Method setAltitudeMethod = componentPort.getClass().getDeclaredMethod("setAltitude", int.class);
            int result = (int) setAltitudeMethod.invoke(componentPort, 5);
            assertEquals(result, 5);
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
