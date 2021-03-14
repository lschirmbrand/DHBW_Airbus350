import event.economy_class_seat.Passenger;
import factory.EconomyClassSeatFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEconomyClassSeat {
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
        componentPort = EconomyClassSeatFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method assignMethod = componentPort.getClass().getDeclaredMethod("assign", Passenger.class);
            assertNotNull(assignMethod);

            Method nonSmokingSignOnMethod = componentPort.getClass().getDeclaredMethod("nonSmokingSignOn");
            assertNotNull(nonSmokingSignOnMethod);

            Method nonSmokingSignOffMethod = componentPort.getClass().getDeclaredMethod("nonSmokingSignOff");
            assertNotNull(nonSmokingSignOffMethod);

            Method seatBeltSignOnMethod = componentPort.getClass().getDeclaredMethod("seatBeltSignOn");
            assertNotNull(seatBeltSignOnMethod);

            Method seatBeltSignOffMethod = componentPort.getClass().getDeclaredMethod("seatBeltSignOff");
            assertNotNull(seatBeltSignOffMethod);

            Method upRightMethod = componentPort.getClass().getDeclaredMethod("upRight");
            assertNotNull(upRightMethod);

            Method level1Method = componentPort.getClass().getDeclaredMethod("level1");
            assertNotNull(level1Method);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String isVersion = (String) versionMethod.invoke(componentPort);
            assertEquals("EconomyClassSeat // 5736465", isVersion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void assign() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method assignMethod = componentPort.getClass().getDeclaredMethod("assign", Passenger.class);
            int isPassengerAssigned = (int) assignMethod.invoke(componentPort, new Passenger());
            assertEquals(1, isPassengerAssigned);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(5)
    public void nonSmokingSignOn() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method nonSmokingSignOnMethod = componentPort.getClass().getDeclaredMethod("nonSmokingSignOn");
            boolean isOn = (boolean) nonSmokingSignOnMethod.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void nonSmokingSignOff() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method nonSmokingSignOffMethod = componentPort.getClass().getDeclaredMethod("nonSmokingSignOff");
            boolean isOn = (boolean) nonSmokingSignOffMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void seatBeltSignOn() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method seatBeltSignOnMethod = componentPort.getClass().getDeclaredMethod("seatBeltSignOn");
            boolean isOn = (boolean) seatBeltSignOnMethod.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void seatBeltSignOff() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method seatBeltSignOffMethod = componentPort.getClass().getDeclaredMethod("seatBeltSignOff");
            boolean isOn = (boolean) seatBeltSignOffMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void upRight() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method upRightMethod = componentPort.getClass().getDeclaredMethod("upRight");
            boolean isUpRight = (boolean) upRightMethod.invoke(componentPort);
            assertTrue(isUpRight);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(10)
    public void level1() {
        componentPort = EconomyClassSeatFactory.build();
        try {
            Method level1Method = componentPort.getClass().getDeclaredMethod("level1");
            int isLevel1 = (int) level1Method.invoke(componentPort);
            assertEquals(1,isLevel1);
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
