import factory.CameraFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCamera {
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
        componentPort = CameraFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = CameraFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("setType", String.class);
            assertNotNull(scanMethod);

            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method zoomInMethod = componentPort.getClass().getDeclaredMethod("zoomIn", double.class);
            assertNotNull(zoomInMethod);

            Method zoomOutMethod = componentPort.getClass().getDeclaredMethod("zoomOut", double.class);
            assertNotNull(zoomOutMethod);

            Method recordMethod = componentPort.getClass().getDeclaredMethod("record");
            assertNotNull(recordMethod);

            assertEquals(7, componentPort.getClass().getDeclaredMethods().length);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = CameraFactory.build();
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
    public void record() {
        componentPort = CameraFactory.build();
        try {
            Method recordMethod = componentPort.getClass().getDeclaredMethod("record");
            String result = (String) recordMethod.invoke(componentPort);
            assertTrue(result != null && !result.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(5)
    public void zoomIn() {
        componentPort = CameraFactory.build();
        try {
            Method zoomInMethod = componentPort.getClass().getDeclaredMethod("zoomIn", double.class);
            String result = (String) zoomInMethod.invoke(componentPort, 5.8d);
            assertEquals("5.8", result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(6)
    public void off() {
        componentPort = CameraFactory.build();
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
    public void zoomOut() {
        componentPort = CameraFactory.build();
        try {
            Method zoomOutMethod = componentPort.getClass().getDeclaredMethod("zoomOut", double.class);
            String result = (String) zoomOutMethod.invoke(componentPort, 5.8d);
            assertEquals("5.8", result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(8)
    public void version() {
        componentPort = CameraFactory.build();
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
    @Order(9)
    public void setType() {
        componentPort = CameraFactory.build();
        try {
            Method setTypeMethod = componentPort.getClass().getDeclaredMethod("setType", String.class);
            Object result = setTypeMethod.invoke(componentPort, "Tail");
            assertNotNull(result);
            try {
                result = setTypeMethod.invoke(componentPort, "Tailer");
                fail();
            } catch(Exception ex) {

            }
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
