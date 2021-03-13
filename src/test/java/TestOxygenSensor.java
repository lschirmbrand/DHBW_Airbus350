import factory.OxygenSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOxygenSensor {
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
        componentPort = OxygenSensorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = OxygenSensorFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            assertNotNull(measureMethod);

            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = OxygenSensorFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String isVersion = (String) versionMethod.invoke(componentPort);
            assertEquals("OxygenSensor // 5736465", isVersion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(4)
    public void measure() {
        componentPort = OxygenSensorFactory.build();
        try {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
            int isResult = (int) measureMethod.invoke(componentPort);
            assertEquals(90 ,isResult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void alarm() {
        componentPort = OxygenSensorFactory.build();
        try {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            boolean isAlarm = (boolean) alarmMethod.invoke(componentPort);
            assertTrue(isAlarm);
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
