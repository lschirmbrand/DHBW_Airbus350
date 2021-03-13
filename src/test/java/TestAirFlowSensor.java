import factory.AirFlowSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAirFlowSensor {

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
        componentPort = AirFlowSensorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = AirFlowSensorFactory.build();

        assertDoesNotThrow(() -> {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            assertNotNull(measureMethod);

            Method alarmnMethod = componentPort.getClass().getDeclaredMethod("alarm", int.class);
            assertNotNull(alarmnMethod);
        });
    }

    @Test
    @Order(3)
    public void measure() {
        componentPort = AirFlowSensorFactory.build();

        assertDoesNotThrow(() -> {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            int airPressure = (int) measureMethod.invoke(componentPort, "AIRFLOWFLOW");
            assertEquals(11, airPressure);
        });
    }

    @Test
    @Order(4)
    public void alarm() {
        componentPort = AirFlowSensorFactory.build();

        assertDoesNotThrow(() -> {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            measureMethod.invoke(componentPort, "AIRFLOWFLOW");

            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm", int.class);
            boolean alarm = (boolean) alarmMethod.invoke(componentPort, 9);
            assertTrue(alarm);

            boolean alarm2 = (boolean) alarmMethod.invoke(componentPort, 17);
            assertFalse(alarm2);
        });
    }
}
