import factory.GPSFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestGPS {
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
        componentPort = GPSFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = GPSFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            assertNotNull(connectMethod);

            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method receiveMethod = componentPort.getClass().getDeclaredMethod("receive");
            assertNotNull(receiveMethod);

            Method sendMethod = componentPort.getClass().getDeclaredMethod("send", String.class);
            assertNotNull(sendMethod);

            assertEquals(6, componentPort.getClass().getDeclaredMethods().length);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = GPSFactory.build();
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
    public void connect() {
        componentPort = GPSFactory.build();
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
    @Order(5)
    public void send() {
        componentPort = GPSFactory.build();
        try {
            Method sendMethod = componentPort.getClass().getDeclaredMethod("send", String.class);
            sendMethod.invoke(componentPort, "5874");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(6)
    public void off() {
        componentPort = GPSFactory.build();
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
    @Order(7)
    public void receive() {
        componentPort = GPSFactory.build();
        try {
            Method receiveMethod = componentPort.getClass().getDeclaredMethod("receive");
            String result = (String) receiveMethod.invoke(componentPort);
            assertFalse(result.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(8)
    public void version() {
        componentPort = GPSFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String result = (String) versionMethod.invoke(componentPort);
            assertFalse(result.isEmpty());
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
