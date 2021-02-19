import factory.TurbulentAirFlowSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestTurbulentAirFlowSensor {
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
        componentPort = TurbulentAirFlowSensorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmMethod);

            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            assertNotNull(measureMethod);

            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            assertEquals(componentPort.getClass().getDeclaredMethods().length, 3);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = TurbulentAirFlowSensorFactory.build();
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
    public void alarm() {
        componentPort = TurbulentAirFlowSensorFactory.build();
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
    @Order(5)
    public void measure() {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            int result = (int) measureMethod.invoke(componentPort, "much");
            assertEquals(result, 0);
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
