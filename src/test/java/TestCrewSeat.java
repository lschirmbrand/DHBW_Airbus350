import event.economy_class_seat.Passenger;
import factory.CrewSeatFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestCrewSeat {
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
        componentPort = CrewSeatFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = CrewSeatFactory.build();
        try {

            Method nonSmokingSignOff = componentPort.getClass().getDeclaredMethod("nonSmokingSignOff");
            assertNotNull(nonSmokingSignOff);

            Method nonSmokingSignOn = componentPort.getClass().getDeclaredMethod("nonSmokingSignOn");
            assertNotNull(nonSmokingSignOn);

            Method seatAssignCrewMember = componentPort.getClass().getDeclaredMethod("assign", Passenger.class);
            assertNotNull(seatAssignCrewMember);

            Method seatBeltSignOff = componentPort.getClass().getDeclaredMethod("seatBeltSignOff");
            assertNotNull(seatBeltSignOff);

            Method seatBeltSignOn = componentPort.getClass().getDeclaredMethod("seatBeltSignOn");
            assertNotNull(seatBeltSignOn);

            Method readingLightOff = componentPort.getClass().getDeclaredMethod("readingLightOff");
            assertNotNull(readingLightOff);

            Method readingLightOn = componentPort.getClass().getDeclaredMethod("readingLightOn");
            assertNotNull(readingLightOn);

            Method testVersion = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(testVersion);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void isSmokingSignOn() {
        componentPort = CrewSeatFactory.build();
        try {
            Method nonSmokingSignOn = componentPort.getClass().getDeclaredMethod("nonSmokingSignOn");
            boolean isOn = (boolean) nonSmokingSignOn.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void isSeatBeltSignOn() {
        componentPort = CrewSeatFactory.build();
        try {
            Method seatBeltSignOn = componentPort.getClass().getDeclaredMethod("seatBeltSignOn");
            boolean isOn = (boolean) seatBeltSignOn.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void isCrewMemberAssign(){
        componentPort = CrewSeatFactory.build();
        try {
            Method crewMemberAssign = componentPort.getClass().getDeclaredMethod("assign", Passenger.class);
            int isAssign = (int) crewMemberAssign.invoke(componentPort);
            assertEquals(1, isAssign);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(6)
    public void isSmokingSignOff() {
        componentPort = CrewSeatFactory.build();
        try {
            Method nonSmokingSignOff = componentPort.getClass().getDeclaredMethod("nonSmokingSignOff");
            boolean isOn = (boolean) nonSmokingSignOff.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void isSeatBeltSignOff() {
        componentPort = CrewSeatFactory.build();
        try {
            Method seatBeltSignOff = componentPort.getClass().getDeclaredMethod("seatBeltSignOff");
            boolean isOn = (boolean) seatBeltSignOff.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void isReadingLightOff() {
        componentPort = CrewSeatFactory.build();
        try {
            Method readingLightOff = componentPort.getClass().getDeclaredMethod("readingLightOff");
            boolean isOn = (boolean) readingLightOff.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void isReadingLightOn() {
        componentPort = CrewSeatFactory.build();
        try {
            Method readingLightOn = componentPort.getClass().getDeclaredMethod("readingLightOn");
            boolean isOn = (boolean) readingLightOn.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(10)
    public void testVersion() {
        componentPort = CrewSeatFactory.build();
        try {
            Method versionTest = componentPort.getClass().getDeclaredMethod("version");
            String isVersion = (String) versionTest.invoke(componentPort);
            assertEquals("CrewSeat // 3186523", isVersion);
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