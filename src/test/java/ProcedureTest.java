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

        // apu_oil_tank
        assertEquals(95, config.levelAPUOilTank);

        // battery
        assertEquals(95, config.percentageBattery);

        // bulk_cargo_door
        assertFalse(config.isBulkCargoDoorClosed);
        assertFalse(config.isBulkCargoDoorLocked);

        // business_class_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);
        assertEquals(1, config.levelSeat);

        // cargo_compartment_light
        assertTrue(config.isCargoCompartmentLightOn);

        // cargo_system
        assertFalse(config.isCargoSystemLocked);
        assertFalse(config.isCargoSystemSecured);
        assertEquals(80, config.numberOfContainerFrontStowage);
        assertEquals(200, config.numberOfContainerRearStowage);
        assertEquals(1600, config.totalWeightContainerFrontStowage);
        assertEquals(4000, config.totalWeightContainerRearStowage);

        // crew_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);

        // economy_class_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);
        assertEquals(1, config.levelSeat);

        // emergency_exit_door
        assertFalse(config.isEmergencyExitDoorClosed);

        // engine_oil_tank
        assertEquals(95, config.levelEngineOilTank);

        // fuel_sensor
        assertEquals(20000, config.amountOfFuel);

        // fuel_tank
        assertEquals(20000, config.amountOfFuel);

        // hydraulic_pump
        assertEquals(9900, config.hydraulicPumpBodyOilAmount);
        assertEquals(2400, config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertTrue(config.isIceDetectorProbeBodyActivated);
        assertTrue(config.isIceDetectorProbeWingActivated);
        assertFalse(config.isIceDetected);

        // kitchen
        assertTrue(config.isKitchenLocked);
        assertTrue(config.isKitchenFilled);
        assertEquals(100, config.numberOfTrolley);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // nitrogen_bottle
        assertEquals(2800, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(9700, config.oxygenBottleAmount);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(0, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(10000, config.amountPotableWater);

        // premium_economy_class_seat
        assertTrue(config.isNonSmokingSignOn);
        assertTrue(config.isSeatBeltSignOn);
        assertEquals(1, config.levelSeat);

        // radar_altimeter
        assertTrue(config.isRadarAltimeterOn);
        assertEquals(0, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(1, config.indexRouteManagement);
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("126,7", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(0, config.capacityWasteWater);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(3)
    public void takeOffTest() {
        cockpit.takeoff();

        // airflow_sensor
        assertEquals(550, config.airPressure);

        // apu_oil_tank
        assertEquals(85, config.levelAPUOilTank);

        // battery
        assertEquals(85, config.percentageBattery);

        // bulk_cargo_door
        assertTrue(config.isBulkCargoDoorClosed);
        assertTrue(config.isBulkCargoDoorLocked);

        // business_class_seat
        assertEquals(2, config.levelSeat);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cargo_system
        assertTrue(config.isCargoSystemLocked);
        assertTrue(config.isCargoSystemSecured);

        // deicing_system
        assertTrue(config.isDeIcingSystemActivated);
        assertEquals(40, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(20, config.degreeDroopNose);

        // economy_class_seat
        assertEquals(2, config.levelSeat);

        // elevator
        assertEquals(75, config.degreeElevator);

        // emergency_exit_door
        assertTrue(config.isEmergencyExitDoorClosed);

        // engine
        assertEquals(5000, config.rpmEngine);

        // engine_oil_tank
        assertEquals(85, config.levelEngineOilTank);

        // exhaust_gas_temperature_sensor
        assertEquals(420, config.temperatureExhaustGasTemperatureSensor);

        // flap
        assertEquals(75, config.degreeFlap);

        // fuel_flow_sensor
        assertEquals(40, config.fuelFlow);

        // fuel_sensor
        assertEquals(17500, config.amountOfFuel);

        // fuel_tank
        assertEquals(17500, config.amountOfFuel);

        // gear
        assertFalse(config.isGearDown);
        assertEquals(0, config.gearBrakePercentage);

        // gear_door
        assertTrue(config.isGearDoorClosed);
        assertTrue(config.isGearDoorLocked);

        // hydraulic_pump
        assertEquals(9800, config.hydraulicPumpBodyOilAmount);

        // ice_detector_probe
        assertTrue(config.isIceDetectorProbeBodyActivated);
        assertTrue(config.isIceDetectorProbeWingActivated);
        assertFalse(config.isIceDetected);

        // kitchen
        assertTrue(config.isKitchenLocked);
        assertTrue(config.isKitchenFilled);
        assertEquals(100, config.numberOfTrolley);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // nitrogen_bottle
        assertEquals(2700, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(9500, config.oxygenBottleAmount);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(300, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(9000, config.amountPotableWater);

        // radar_altimeter
        assertTrue(config.isRadarAltimeterOn);
        assertEquals(0, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(1, config.indexRouteManagement);
        assertEquals(1, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(20, config.degreeRudder);

        // slat
        assertEquals(20, config.degreeSlat);

        // spoiler
        assertEquals(20, config.degreeSpoiler);

        // tcas
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("124,3", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(10, config.capacityWasteWater);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(4)
    public void climbingTest() {
        cockpit.climbing();

        // airflow_sensor
        assertEquals(400, config.airPressure);

        // apu_oil_tank
        assertEquals(75, config.levelAPUOilTank);

        // battery
        assertEquals(75, config.percentageBattery);

        // deicing_system
        assertEquals(80, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(20, config.degreeDroopNose);

        // elevator
        assertEquals(65, config.degreeElevator);

        // engine
        assertEquals(3000, config.rpmEngine);

        // engine_oil_tank
        assertEquals(75, config.levelEngineOilTank);

        // flap
        assertEquals(65, config.degreeFlap);

        // fuel_flow_sensor
        assertEquals(20, config.fuelFlow);

        // fuel_sensor
        assertEquals(15000, config.amountOfFuel);

        // fuel_tank
        assertEquals(15000, config.amountOfFuel);

        // hydraulic_pump
        assertEquals(9700, config.hydraulicPumpBodyOilAmount);
        assertEquals(2200, config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertTrue(config.isIceDetectorProbeBodyActivated);
        assertTrue(config.isIceDetectorProbeWingActivated);
        assertFalse(config.isIceDetected);

        // kitchen
        assertFalse(config.isKitchenLocked);
        assertTrue(config.isKitchenFilled);
        assertEquals(80, config.numberOfTrolley);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // nitrogen_bottle
        assertEquals(2500, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(9000, config.oxygenBottleAmount);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(500, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(7000, config.amountPotableWater);

        // radar_altimeter
        assertTrue(config.isRadarAltimeterOn);
        assertEquals(5000, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(1, config.indexRouteManagement);
        assertEquals(3, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(20, config.degreeRudder);

        // slat
        assertEquals(20, config.degreeSlat);

        // spoiler
        assertEquals(20, config.degreeSpoiler);

        // tcas
        assertEquals(5000, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(5, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(5, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("122,43", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(20, config.capacityWasteWater);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(5)
    public void rightTurnTest() {
        cockpit.rightTurn();

        // apu_oil_tank
        assertEquals(65, config.levelAPUOilTank);

        // battery
        assertEquals(65, config.percentageBattery);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine_oil_tank
        assertEquals(65, config.levelEngineOilTank);

        // flap
        assertEquals(30, config.degreeFlap);

        // fuel_sensor
        assertEquals(12500, config.amountOfFuel);

        // fuel_tank
        assertEquals(12500, config.amountOfFuel);

        // hydraulic_pump
        assertEquals(9600, config.hydraulicPumpBodyOilAmount);
        assertEquals(2100, config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertTrue(config.isIceDetectorProbeBodyActivated);
        assertTrue(config.isIceDetectorProbeWingActivated);
        assertTrue(config.isIceDetected);

        // kitchen
        assertFalse(config.isKitchenLocked);
        assertTrue(config.isKitchenFilled);
        assertEquals(60, config.numberOfTrolley);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(-10, config.degreeLeftAileron);

        // nitrogen_bottle
        assertEquals(2400, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(8000, config.oxygenBottleAmount);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(800, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(5000, config.amountPotableWater);

        // radar_altimeter
        assertTrue(config.isRadarAltimeterOn);
        assertEquals(10000, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(10, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(1, config.indexRouteManagement);
        assertEquals(5, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertEquals(10000, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(-10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(-10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("124,43", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(30, config.capacityWasteWater);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(6)
    public void leftTurnTest() {
        cockpit.leftTurn();

        // apu_oil_tank
        assertEquals(55, config.levelAPUOilTank);

        // battery
        assertEquals(55, config.percentageBattery);

        // elevator
        assertEquals(0, config.degreeElevator);

        // engine_oil_tank
        assertEquals(55, config.levelEngineOilTank);

        // fuel_sensor
        assertEquals(10000, config.amountOfFuel);

        // fuel_tank
        assertEquals(10000, config.amountOfFuel);

        // hydraulic_pump
        assertEquals(9500, config.hydraulicPumpBodyOilAmount);
        assertEquals(2000, config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertTrue(config.isIceDetectorProbeBodyActivated);
        assertTrue(config.isIceDetectorProbeWingActivated);
        assertTrue(config.isIceDetected);

        // kitchen
        assertFalse(config.isKitchenLocked);
        assertTrue(config.isKitchenFilled);
        assertEquals(30, config.numberOfTrolley);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(10, config.degreeLeftAileron);

        // nitrogen_bottle
        assertEquals(2200, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(6000, config.oxygenBottleAmount);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(800, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(4000, config.amountPotableWater);

        // radar_altimeter
        assertTrue(config.isRadarAltimeterOn);
        assertEquals(10000, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(-10, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(1, config.indexRouteManagement);
        assertEquals(8, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // tcas
        assertEquals(10000, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(-10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(-10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("127,6", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(40, config.capacityWasteWater);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(7)
    public void descentTest() {
        cockpit.descent();

        // apu_oil_tank
        assertEquals(45, config.levelAPUOilTank);

        // battery
        assertEquals(45, config.percentageBattery);

        // elevator
        assertEquals(-45, config.degreeElevator);

        // engine_oil_tank
        assertEquals(45, config.levelEngineOilTank);

        // flap
        assertEquals(0, config.degreeFlap);

        // fuel_sensor
        assertEquals(7500, config.amountOfFuel);

        // fuel_tank
        assertEquals(7500, config.amountOfFuel);

        // hydraulic_pump
        assertEquals(9400, config.hydraulicPumpBodyOilAmount);
        assertEquals(1900, config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertTrue(config.isIceDetectorProbeBodyActivated);
        assertTrue(config.isIceDetectorProbeWingActivated);
        assertTrue(config.isIceDetected);

        // kitchen
        assertFalse(config.isKitchenLocked);
        assertFalse(config.isKitchenFilled);
        assertEquals(0, config.numberOfTrolley);

        // landing_light
        assertFalse(config.isLandingLightBodyOn);
        assertFalse(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // nitrogen_bottle
        assertEquals(2100, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(4000, config.oxygenBottleAmount);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(600, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(2000, config.amountPotableWater);

        // radar_altimeter
        assertTrue(config.isRadarAltimeterOn);
        assertEquals(5000, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(1, config.indexRouteManagement);
        assertEquals(13, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(5, config.degreeRudder);

        // slat
        assertEquals(5, config.degreeSlat);

        // spoiler
        assertEquals(5, config.degreeSpoiler);

        // tcas
        assertEquals(5000, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(-5, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(-5, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("121,63", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(50, config.capacityWasteWater);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(8)
    public void landingTest() {
        cockpit.landing();

        // apu_oil_tank
        assertEquals(35, config.levelAPUOilTank);

        // battery
        assertEquals(35, config.percentageBattery);

        // elevator
        assertEquals(-30, config.degreeElevator);

        // engine_oil_tank
        assertEquals(35, config.levelEngineOilTank);

        // flap
        assertEquals(20, config.degreeFlap);

        // fuel_sensor
        assertEquals(5000, config.amountOfFuel);

        // fuel_tank
        assertEquals(5000, config.amountOfFuel);

        // hydraulic_pump
        assertEquals(9300, config.hydraulicPumpBodyOilAmount);
        assertEquals(1800, config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertTrue(config.isIceDetectorProbeBodyActivated);
        assertTrue(config.isIceDetectorProbeWingActivated);
        assertFalse(config.isIceDetected);

        // kitchen
        assertTrue(config.isKitchenLocked);
        assertFalse(config.isKitchenFilled);
        assertEquals(0, config.numberOfTrolley);

        // landing_light
        assertTrue(config.isLandingLightBodyOn);
        assertTrue(config.isLandingLightWingOn);

        // left_aileron
        assertEquals(0, config.degreeLeftAileron);

        // nitrogen_bottle
        assertEquals(2000, config.amountOfNitrogen);

        // oxygen_bottle
        assertEquals(3000, config.oxygenBottleAmount);

        // pitot_tube
        assertFalse(config.isPitotTubeCleaned);
        assertEquals(200, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(1000, config.amountPotableWater);

        // radar_altimeter
        assertTrue(config.isRadarAltimeterOn);
        assertEquals(50, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // route_management
        assertTrue(config.isRouteManagementOn);
        assertEquals(1, config.indexRouteManagement);
        assertEquals(17, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(5, config.degreeRudder);

        // slat
        assertEquals(5, config.degreeSlat);

        // spoiler
        assertEquals(5, config.degreeSpoiler);

        // tcas
        assertEquals(50, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("128,625", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(60, config.capacityWasteWater);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(9)
    public void shutdownTest() {
        cockpit.shutdown();

        // air_conditioning
        assertFalse(config.isAirConditioningOn);
        assertEquals(0, config.temperatureAirConditioning);

        // airflow_sensor
        assertEquals(0, config.airPressure);
        assertFalse(config.isAirFlowSensorBodyAlarm);
        assertFalse(config.isAirFlowSensorWingAlarm);

        // anti_collision_light
        assertFalse(config.isAntiCollisionLightOn);

        // apu
        assertEquals(0, config.rpmAPU);
        assertFalse(config.isAPUStarted);

        // apu_oil_tank
        assertEquals(0, config.levelAPUOilTank);

        // battery
        assertEquals(0, config.percentageBattery);

        // bulk_cargo_door
        assertTrue(config.isBulkCargoDoorClosed);
        assertTrue(config.isBulkCargoDoorLocked);

        // business_class_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);
        assertEquals(0, config.levelSeat);

        // camera
        assertFalse(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cargo_system
        assertTrue(config.isCargoSystemLocked);
        assertTrue(config.isCargoSystemSecured);
        assertEquals(0, config.numberOfContainerFrontStowage);
        assertEquals(0, config.numberOfContainerRearStowage);
        assertEquals(0, config.totalWeightContainerFrontStowage);
        assertEquals(0, config.totalWeightContainerRearStowage);

        // cost_optimizer
        assertFalse(config.isCostOptimizerOn);
        assertEquals(0, config.indexCostOptimizer);
        assertEquals(0, config.numberOfCheckPointsCostOptimizer);

        // crew_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);
        assertEquals(0, config.levelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // emergency_exit_door
        assertTrue(config.isEmergencyExitDoorClosed);
        assertTrue(config.isEmergencyExitDoorLocked);
        assertFalse(config.isEscapeSlideActivated);

        // engine
        assertFalse(config.isEngineStarted);
        assertEquals(0, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // engine_oil_tank
        assertEquals(0, config.levelEngineOilTank);

        // exhaust_gas_temperature_sensor
        assertEquals(0, config.temperatureExhaustGasTemperatureSensor);
        assertFalse(config.isAlarmMajorExhaustGasTemperatureSensor);
        assertFalse(config.isAlarmCriticalExhaustGasTemperatureSensor);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // flap
        assertEquals(0, config.degreeFlap);

        // fuel_flow_sensor
        assertEquals(0, config.fuelFlow);

        // fuel_sensor
        assertEquals(0, config.amountOfFuel);
        assertFalse(config.isAlarmReserveFuelSensor);
        assertFalse(config.isAlarmMajorFuelSensor);
        assertFalse(config.isAlarmCriticalFuelSensor);

        // fuel_tank
        assertEquals(0, config.amountOfFuel);

        // gear
        assertFalse(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gear_door
        assertTrue(config.isGearDoorClosed);
        assertTrue(config.isGearDoorLocked);

        // gps
        assertFalse(config.isGPSOn);
        assertFalse(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(9200, config.hydraulicPumpBodyOilAmount);
        assertEquals(1700, config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertFalse(config.isIceDetectorProbeBodyActivated);
        assertFalse(config.isIceDetectorProbeWingActivated);
        assertFalse(config.isIceDetected);

        // kitchen
        assertTrue(config.isKitchenLocked);
        assertFalse(config.isKitchenFilled);
        assertEquals(0, config.numberOfTrolley);

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

        // pitot_tube
        assertFalse(config.isPitotTubeCleaned);
        assertEquals(0, config.velocity);

        // potable_watertank
        assertTrue(config.isPotableWaterTankLocked);
        assertEquals(0, config.amountPotableWater);

        // premium_economy_class_seat
        assertFalse(config.isNonSmokingSignOn);
        assertFalse(config.isSeatBeltSignOn);
        assertEquals(0, config.levelSeat);

        // radar
        assertFalse(config.isRadarOn);

        // radar_altimeter
        assertFalse(config.isRadarAltimeterOn);
        assertEquals(0, config.altitudeRadarAltimeter);

        // right_aileron
        assertEquals(0, config.degreeRightAileron);

        // right_navigation_light
        assertFalse(config.isRightNavigationLightOn);

        // route_management
        assertFalse(config.isRouteManagementOn);
        assertEquals(0, config.indexRouteManagement);
        assertEquals(20, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(0, config.degreeRudder);

        // satcom
        assertFalse(config.isSatComConnected);

        // shock_sensor
        assertFalse(config.isShockSensorBodyShockDetected);
        assertFalse(config.isShockSensorBodyCalibrated);
        assertFalse(config.isShockSensorWingShockDetected);
        assertFalse(config.isShockSensorWingCalibrated);

        // slat
        assertEquals(0, config.degreeSlat);

        // spoiler
        assertEquals(0, config.degreeSpoiler);

        // stalling_sensorpotable water
        assertFalse(config.isStallingSensorAlarm);

        // tail_navigation_light
        assertFalse(config.isTailNavigationLightOn);

        // taxi_light
        assertFalse(config.isTaxiLightOn);

        // tcas
        assertFalse(config.isTCASOn);
        assertFalse(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(0, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(0, config.temperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // vhf
        assertFalse(config.isVHFOn);
        assertEquals("", config.selectedChannelVHF);

        // wastewater_tank
        assertTrue(config.isWasteWaterTankLocked);
        assertEquals(80, config.capacityWasteWater);

        // weather_radar
        assertFalse(config.isWeatherRadarOn);
    }
}