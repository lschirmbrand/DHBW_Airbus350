import factory.AirFlowSensorFactory;
import factory.BatteryFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBattery {

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
        componentPort = BatteryFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = BatteryFactory.build();

        assertDoesNotThrow(() -> {
            Method chargeMethod = componentPort.getClass().getDeclaredMethod("charge");
            assertNotNull(chargeMethod);

            Method dischargeMethod = componentPort.getClass().getDeclaredMethod("discharge");
            assertNotNull(dischargeMethod);
        });
    }

    @Test
    @Order(3)
    public void charge() {
        componentPort = BatteryFactory.build();

        assertDoesNotThrow(() -> {
            Method chargeMethod = componentPort.getClass().getDeclaredMethod("charge");
            int percentage = (int) chargeMethod.invoke(componentPort);
            assertEquals(100, percentage);
        });
    }

    @Test
    @Order(4)
    public void discharge() {
        componentPort = BatteryFactory.build();

        assertDoesNotThrow(() -> {
            Method dischargeMethod = componentPort.getClass().getDeclaredMethod("discharge");
            int percentage = (int) dischargeMethod.invoke(componentPort);
            assertEquals(99, percentage);

            int percentage2 = (int) dischargeMethod.invoke(componentPort);
            assertEquals(98, percentage2);
        });
    }
}
