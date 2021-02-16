import base.Airplane;
import base.Cockpit;
import base.PrimaryFlightDisplay;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcedureTest {
    private Airplane airplane;
    private Cockpit cockpit;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        airplane = new Airplane();
        airplane.build();

        cockpit = new Cockpit(airplane);
    }

    @Test
    @Order(1)
    public void startUpTest() {
        cockpit.startup();

        // air_conditioning
        // airflow_sensor
        // anti_collision_light
        // apu
        // apu_oil_tank
        // battery
        // bulk_cargo_door
        // business_class_seat
        // camera
        // cargo_compartment_light
        // cargo_system
        // cost_optimizer
        // crew_seat
        // deicing_system
        // droop_nose
        // economy_class_seat
        // elevator
        // emergency_exit_door
        // engine
        // engine_oil_tank
        // exhaust_gas_temperature_sensor
        // fire_detector
        // flap
        // fuel_flow_sensor
        // fuel_sensor
        // fuel_tank
        // gear
        // gear_door
        // gps
        // hydraulic_pump
        // ice_detector_probe
        // kitchen
        // landing_light
        // left_aileron
        // left_navigation_light
        // logo_light
        // nitrogen_bottle
        // oxygen_bottle
        // oxygen_sensor
        // pitot_tube
        // potable_watertank
        // premium_economy_class_seat
        // radar
        // radar_altimeter
        // right_aileron
        // right_navigation_light
        // route_management
        // rudder
        // satcom
        // shock_sensor
        // slat
        // spoiler
        // stalling_sensor
        // tail_navigation_light
        // taxi_light
        // tcas
        // temperature_sensor
        // turbulent_airflow_sensor
        // vhf
        // wastewater_tank

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(2)
    public void taxiTest() {
        cockpit.taxi();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(3)
    public void takeOffTest() {
        cockpit.takeoff();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(4)
    public void climbingTest() {
        cockpit.climbing();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(5)
    public void rightTurnTest() {
        cockpit.rightTurn();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(6)
    public void leftTurnTest() {
        cockpit.leftTurn();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(7)
    public void descentTest() {
        cockpit.descent();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(8)
    public void landingTest() {
        cockpit.landing();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(9)
    public void shutdownTest() {
        cockpit.shutdown();

        // weather_radar
        assertFalse(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }
}