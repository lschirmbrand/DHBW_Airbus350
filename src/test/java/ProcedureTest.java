import base.Airplane;
import base.Cockpit;
import base.PrimaryFlightDisplay;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        // apu_oil_tank
        assertEquals(100, config.levelAPUOilTank);

        // battery
        assertEquals(100, config.percentageBattery);

        // bulk_cargo_door
        assertTrue(config.isBulkCargoDoorClosed);
        assertFalse(config.isBulkCargoDoorLocked);

        // business_class_seat
        assertFalse(config.isBusinessClassNonSmokingSignOn);
        assertFalse(config.isBusinessClassSeatBeltSignOn);
        assertEquals(0, config.businessClassLevelSeat);

        // camera
        assertTrue(config.isCameraOn);

        // cargo_compartment_light
        assertFalse(config.isCargoCompartmentLightOn);

        // cargo_system
        assertFalse(config.isCargoSystemLocked);
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
        assertFalse(config.isCrewSeatNonSmokingSignOn);
        assertFalse(config.isCrewSeatSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertFalse(config.isEconomyClassNonSmokingSignOn);
        assertFalse(config.isEconomyClassSeatBeltSignOn);
        assertEquals(0, config.economyClassLevelSeat);

        // elevator
        assertEquals(0, config.degreeElevator);

        // emergency_exit_door
        assertTrue(config.isEmergencyExitDoorClosed);
        assertFalse(config.isEmergencyExitDoorLocked);
        assertTrue(config.isEscapeSlideActivated);

        // engine
        assertTrue(config.isEngineStarted);
        assertEquals(2250, config.rpmEngine);
        assertFalse(config.isEngineFire);

        // engine_oil_tank
        assertEquals(100, config.levelEngineOilTank);

        // exhaust_gas_temperature_sensor
        assertEquals(420, config.temperatureExhaustGasTemperatureSensor);
        assertFalse(config.isAlarmMajorExhaustGasTemperatureSensor);
        assertFalse(config.isAlarmCriticalExhaustGasTemperatureSensor);

        // fire_detector
        assertFalse(config.isFireDetectedBody);
        assertFalse(config.isFireDetectedWing);

        // flap
        assertEquals(0, config.degreeFlap);

        // fuel_flow_sensor
        assertEquals(10,config.fuelFlow);

        // fuel_sensor
        assertEquals(20077, config.fuelSensorAmountOfFuel);
        assertFalse(config.isAlarmReserveFuelSensor);
        assertFalse(config.isAlarmMajorFuelSensor);
        assertFalse(config.isAlarmCriticalFuelSensor);

        // fuel_tank
        assertEquals(20077, config.fuelTankAmountOfFuel);

        // gear
        assertTrue(config.isGearDown);
        assertEquals(100, config.gearBrakePercentage);

        // gear_door
        assertTrue(config.isGearDoorClosed);
        assertFalse(config.isGearDoorLocked);

        // gps
        assertTrue(config.isGPSOn);
        assertTrue(config.isGPSConnected);

        // hydraulic_pump
        assertEquals(10000 ,config.hydraulicPumpBodyOilAmount);
        assertEquals(2500 ,config.hydraulicPumpWingOilAmount);

        // ice_detector_probe
        assertFalse(config.isIceDetectorProbeBodyActivated);
        assertFalse(config.isIceDetectorProbeWingActivated);
        assertFalse(config.isIceDetected);

        // kitchen
        assertFalse(config.isKitchenLocked);
        assertFalse(config.isKitchenFilled);
        assertEquals(100, config.numberOfTrolley);

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
        assertFalse(config.isOxgenSensorAlarm);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(0, config.velocity);

        // potable_watertank
        assertFalse(config.isPotableWaterTankLocked);
        assertEquals(10000, config.amountPotableWater);

        // premium_economy_class_seat
        assertFalse(config.isPremiumEconomyNonSmokingSignOn);
        assertFalse(config.isPremiumEconomySeatBeltSignOn);
        assertEquals(0, config.premiumEconomyLevelSeat);

        // radar
        assertTrue(config.isRadarOn);

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
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

        // rudder
        assertEquals(10, config.degreeRudder);

        // satcom
        assertTrue(config.isSatComConnected);

        // shock_sensor
        assertFalse(config.isShockSensorBodyShockDetected);
        assertTrue(config.isShockSensorBodyCalibrated);
        assertFalse(config.isShockSensorWingShockDetected);
        assertTrue(config.isShockSensorWingCalibrated);

        // slat
        assertEquals(10, config.degreeSlat);

        // spoiler
        assertEquals(10, config.degreeSpoiler);

        // stalling_sensorpotable water
        assertFalse(config.isStallingSensorAlarm);

        // tail_navigation_light
        assertTrue(config.isTailNavigationLightOn);

        // taxi_light
        assertTrue(config.isTaxiLightOn);

        // tcas
        assertTrue(config.isTCASOn);
        assertTrue(config.isTCASConnected);
        assertFalse(config.isTCASAlarm);
        assertEquals(0, config.altitudeTCAS);

        // temperature_sensor
        assertEquals(10, config.temperatureBody);
        assertFalse(config.isTemperatureSensorBodyAlarm);
        assertEquals(10, config.TemperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // vhf
        assertTrue(config.isVHFOn);
        assertEquals("125,625", config.selectedChannelVHF);

        // wastewater_tank
        assertFalse(config.isWasteWaterTankLocked);
        assertEquals(0, config.capacityWasteWater);

        // weather_radar
        assertTrue(config.isWeatherRadarOn);
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
        assertFalse(config.isBusinessClassNonSmokingSignOn);
        assertFalse(config.isBusinessClassSeatBeltSignOn);
        assertEquals(0, config.businessClassLevelSeat);

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
        assertFalse(config.isCrewSeatNonSmokingSignOn);
        assertFalse(config.isCrewSeatSeatBeltSignOn);

        // deicing_system
        assertFalse(config.isDeIcingSystemActivated);
        assertEquals(0, config.amountDeIcingSystem);

        // droop_nose
        assertEquals(0, config.degreeDroopNose);

        // economy_class_seat
        assertFalse(config.isEconomyClassNonSmokingSignOn);
        assertFalse(config.isEconomyClassSeatBeltSignOn);
        assertEquals(0, config.economyClassLevelSeat);

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
        assertEquals(0,config.fuelFlow);

        // fuel_sensor
        assertEquals(0, config.fuelSensorAmountOfFuel);
        assertFalse(config.isAlarmReserveFuelSensor);
        assertFalse(config.isAlarmMajorFuelSensor);
        assertFalse(config.isAlarmCriticalFuelSensor);

        // fuel_tank
        assertEquals(0, config.fuelTankAmountOfFuel);

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
        assertEquals(0 ,config.hydraulicPumpBodyOilAmount);
        assertEquals(0 ,config.hydraulicPumpWingOilAmount);

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
        assertFalse(config.isOxgenSensorAlarm);

        // pitot_tube
        assertTrue(config.isPitotTubeCleaned);
        assertEquals(0, config.velocity);

        // potable_watertank
        assertTrue(config.isPotableWaterTankLocked);
        assertEquals(0, config.amountPotableWater);

        // premium_economy_class_seat
        assertFalse(config.isPremiumEconomyNonSmokingSignOn);
        assertFalse(config.isPremiumEconomySeatBeltSignOn);
        assertEquals(0, config.premiumEconomyLevelSeat);

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
        assertEquals(0, config.numberOfCheckPointsRouteManagement);

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
        assertEquals(0, config.TemperatureWing);
        assertFalse(config.isTemperatureSensorWingAlarm);

        // turbulent_airflow_sensor
        assertFalse(config.isTurbulentAirFlowAlarm);

        // vhf
        assertFalse(config.isVHFOn);
        assertEquals("", config.selectedChannelVHF);

        // wastewater_tank
        assertTrue(config.isWasteWaterTankLocked);
        assertEquals(0, config.capacityWasteWater);

        // weather_radar
        assertFalse(config.isWeatherRadarOn);
    }
}