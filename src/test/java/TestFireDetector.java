import factory.FireDetectorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestFireDetector {
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
        componentPort = FireDetectorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = FireDetectorFactory.build();
        try {
            Method scanBody = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(scanBody);

            Method scanWing = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(scanWing);

            Method alarmIsOn = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmIsOn);

            Method testVersion = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(testVersion);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void testBodyScan() {
        componentPort = FireDetectorFactory.build();
        try {
            Method scanBody = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(scanBody);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void testWingScan() {
        componentPort = FireDetectorFactory.build();
        try {
            Method scanWing = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(scanWing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void testAlarm() {
        componentPort = FireDetectorFactory.build();
        try {
            Method isAlarm = componentPort.getClass().getDeclaredMethod("alarm");
            boolean isOn = (boolean) isAlarm.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void testVersion() {
        componentPort = FireDetectorFactory.build();
        try {
            Method versionTest = componentPort.getClass().getDeclaredMethod("version");
            String isVersion = (String) versionTest.invoke(componentPort);
            assertEquals("FireDetector // 3186523", isVersion);
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
