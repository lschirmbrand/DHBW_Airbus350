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

    /**
     * Notiz für Prof. Dr. Müller:
     *
     * Da die einzelnen Tests / Phasen die Werte im PrimaryFlightDisplay ändern, dieses aber nicht vor jedem Test von
     * Java zurückgesetzt wird, hängen die Tests vom jeweilig vorherigen ab.
     * Also haben wir die Tests so konzipiert, dass sie alle durchlaufen, wenn ALLE Tests gestartet werden.
     *
     * Beispiel: Die Oxygen Bottle hat nur Events zum komplett neu auffüllen oder zur Entnahme einer gewissen Menge.
     * Beim startUp wird die Bottle gefüllt und in anderen Phasen dann ggf. eine kleine Menge verbraucht. Startet man
     * nur den Test einer späteren Flugphase, schlägt er fehl, da die OxygenBottle dann den Füllstand 0 hat, da die
     * startUp Phase übersprungen wurde.
     *
     * Da wird die gegebene Aufgabenstellung nicht ändern wollten, haben wir uns für die oben beschriebene Konzeption
     * entschieden.
     */

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
        assertEquals(0, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(5000, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(0, config.indexCostOptimizer);
        assertEquals(0  , config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(800, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(90, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(1000, config.rpmEngine);
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
        assertEquals(6000, config.hydraulicPumpBodyOilAmount);
        assertEquals(6000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(250, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(100, config.oxygenBottleAmount);

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
        assertEquals(0, config.degreeRudder);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertEquals(20, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(5000, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(99, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertTrue(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(0, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(800, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(90, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(1000, config.rpmEngine);
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
        assertEquals(6000, config.hydraulicPumpBodyOilAmount);
        assertEquals(6000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(250, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(100, config.oxygenBottleAmount);

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
        assertEquals(0, config.degreeRudder);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertEquals(24, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(5000, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(99, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(850, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(-50, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(180, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(15000, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(0, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(1000, config.hydraulicPumpBodyOilAmount);
        assertEquals(1000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(225, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(90, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(30, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(0, config.degreeRudder);

        // slat
        assertEquals(-20, config.degreeSlat);

        // spoiler
        assertEquals(20, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertEquals(24, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(2500, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(99, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(850, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(100, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(4000, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertFalse(config.isGearDown);
        assertEquals(0, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(1000, config.hydraulicPumpBodyOilAmount);
        assertEquals(1000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(200, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(80, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(30, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(0, config.degreeRudder);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertEquals(24, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(1000, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(99, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(850, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(100, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(2500, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertFalse(config.isGearDown);
        assertEquals(0, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(1000, config.hydraulicPumpBodyOilAmount);
        assertEquals(1000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(-30, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(175, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(70, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(30, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(30, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(15, config.degreeRudder);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertEquals(24, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(1000, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(99, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(850, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(100, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(2500, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertFalse(config.isGearDown);
        assertEquals(0, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(1000, config.hydraulicPumpBodyOilAmount);
        assertEquals(1000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(15, config.degreeLeftAileron);

        // left_navigation_light
        assertTrue(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(150, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(60, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(-15, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(30, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(-30, config.degreeRudder);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertEquals(24, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(1000, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(99, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(850, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(80, config.degreeElevator);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(500, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertFalse(config.isGearDown);
        assertEquals(0, config.gearBrakePercentage);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(1000, config.hydraulicPumpBodyOilAmount);
        assertEquals(1000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(125, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(50, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(30, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(0, config.degreeRudder);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(15, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertEquals(24, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertTrue(config.isAntiCollisionLightOn);

        // apu
        assertEquals(250, config.rpmAPU);
        assertTrue(config.isAPUStarted);

        // battery
        assertEquals(99, config.percentageBattery);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertTrue(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(850, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(-90, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(45, config.degreeElevator);

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
        assertEquals(6000, config.hydraulicPumpBodyOilAmount);
        assertEquals(6000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertTrue(config.isLandingLightBodyOn);
        assertTrue(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertTrue(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(100, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(40, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertTrue(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(30, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(0, config.degreeRudder);

        // slat
        assertEquals(-15, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
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
        assertFalse(config.isAirConditioningOn);
        assertEquals(0, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(1013, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertFalse(config.isAntiCollisionLightOn);

        // apu
        assertEquals(0, config.rpmAPU);
        assertFalse(config.isAPUStarted);

        // battery
        assertEquals(100, config.percentageBattery);

        // camera
        assertFalse(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cost_optimizer
        assertFalse(config.isCostOptimizerOn);
        assertEquals(30, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(1000, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(90, config.degreeElevator);

        // engine
        assertFalse(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gps
        assertFalse(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(2000, config.hydraulicPumpBodyOilAmount);
        assertEquals(2000, config.hydraulicPumpWingOilAmount);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // left_navigation_light
        assertFalse(config.isLeftNavigationLightOn);

        // logo_light
        assertFalse(config.isLogoLightOn);

        // nitrogen_bottle
        assertEquals(0, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(0, config.oxygenBottleAmount);

        // oxygen_sensor
        assertFalse(config.isOxygenSensorAlarm);

        // radar
        assertFalse(config.isRadarOn);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertFalse(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(0, config.degreeRudder);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // tcas
        assertFalse(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(20, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(20, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // weather_radar
        assertFalse(config.isWeatherRadarOn);
    }
}