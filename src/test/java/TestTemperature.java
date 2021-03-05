import factory.AirFlowSensorFactory;
import factory.TemperatureSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTemperature {

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
        componentPort = TemperatureSensorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = TemperatureSensorFactory.build();

        assertDoesNotThrow(() -> {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
            assertNotNull(measureMethod);

            Method alarmnMethod = componentPort.getClass().getDeclaredMethod("alarm", int.class);
            assertNotNull(alarmnMethod);
        });
    }

    @Test
    @Order(3)
    public void measure() {
        componentPort = TemperatureSensorFactory.build();

        assertDoesNotThrow(() -> {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(componentPort);
            assertEquals(20, temperature);
        });
    }

    @Test
    @Order(4)
    public void alarm() {
        componentPort = TemperatureSensorFactory.build();

        assertDoesNotThrow(() -> {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm", int.class);
            boolean alarm = (boolean) alarmMethod.invoke(componentPort, 31);
            assertTrue(alarm);

            boolean alarm2 = (boolean) alarmMethod.invoke(componentPort, 17);
            assertFalse(alarm2);
        });
    }
}
