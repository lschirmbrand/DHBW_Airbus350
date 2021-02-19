import factory.HydraulicPumpFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHydraulicPump {

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
        componentPort = HydraulicPumpFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = HydraulicPumpFactory.build();

        assertDoesNotThrow(() -> {
            Method compressedMethod = componentPort.getClass().getDeclaredMethod("compress");
            assertNotNull(compressedMethod);

            Method decompressedMethod = componentPort.getClass().getDeclaredMethod("decompress");
            assertNotNull(decompressedMethod);

            Method refillOilMethod = componentPort.getClass().getDeclaredMethod("refillOil");
            assertNotNull(refillOilMethod);

            Method compressedAmountMethod = componentPort.getClass().getDeclaredMethod("compress", int.class);
            assertNotNull(compressedAmountMethod);

            Method refillOilAmountMethod = componentPort.getClass().getDeclaredMethod("compress", int.class);
            assertNotNull(refillOilAmountMethod);
        });
    }

    @Test
    @Order(3)
    public void compressed() {
        componentPort = HydraulicPumpFactory.build();

        assertDoesNotThrow(() -> {
            Method compressMethod = componentPort.getClass().getDeclaredMethod("compress");
            int compressed = (int) compressMethod.invoke(componentPort);
            assertEquals(compressed, 5000);
        });
    }

    @Test
    @Order(4)
    public void compressedAmount() {
        componentPort = HydraulicPumpFactory.build();

        assertDoesNotThrow(() -> {
            Method compressMethod = componentPort.getClass().getDeclaredMethod("compress", int.class);
            int compressed = (int) compressMethod.invoke(componentPort, 5000);
            assertEquals(compressed, 5000);
        });
    }

    @Test
    @Order(5)
    public void decompressed() {
        componentPort = HydraulicPumpFactory.build();

        assertDoesNotThrow(() -> {
            Method decompressMethod = componentPort.getClass().getDeclaredMethod("decompress");
            int decompressed = (int) decompressMethod.invoke(componentPort);
            assertEquals(decompressed, 0);
        });
    }

    @Test
    @Order(6)
    public void refillOil() {
        componentPort = HydraulicPumpFactory.build();

        assertDoesNotThrow(() -> {
            Method refillOilMethod = componentPort.getClass().getDeclaredMethod("refillOil");
            int oilAmount = (int) refillOilMethod.invoke(componentPort);
            assertEquals(5000, oilAmount);
        });
    }

    @Test
    @Order(7)
    public void refillOilAmount() {
        componentPort = HydraulicPumpFactory.build();

        assertDoesNotThrow(() -> {
            Method refillOilMethodAmount = componentPort.getClass().getDeclaredMethod("refillOil", int.class);
            int oilAmount = (int) refillOilMethodAmount.invoke(componentPort, 1000);
            assertEquals(2000, oilAmount);
        });
    }
}
