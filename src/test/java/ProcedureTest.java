import base.Airplane;
import base.Cockpit;
import base.PrimaryFlightDisplay;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcedureTest {
    private Airplane airplane;
    private Cockpit cockpit;
    private PrimaryFlightDisplay config;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        airplane = new Airplane();
        airplane.build();

        cockpit = new Cockpit(airplane);
        this.config = PrimaryFlightDisplay.instance;
    }

    @Test
    @Order(1)
    public void startUpTest() {
        cockpit.startup();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(2)
    public void taxiTest() {
        cockpit.taxi();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(3)
    public void takeOffTest() {
        cockpit.takeoff();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(4)
    public void climbingTest() {
        cockpit.climbing();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(5)
    public void rightTurnTest() {
        cockpit.rightTurn();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(6)
    public void leftTurnTest() {
        cockpit.leftTurn();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(7)
    public void descentTest() {
        cockpit.descent();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(8)
    public void landingTest() {
        cockpit.landing();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }

    @Test
    @Order(9)
    public void shutdownTest() {
        cockpit.shutdown();

        // air_conditioning
        assertTrue(config.isAirConditioningOn);
        assertEquals(22, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(7500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(250, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2500, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(3000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(10000, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
    }
}