package base;

@SuppressWarnings("unused")
public enum PrimaryFlightDisplay {
    instance;

    // air_conditioning
    public boolean isAirConditioningOn;
    public int temperatureAirConditioning;

    // airflow_sensor
    public int airPressure;
    public boolean isAirFlowSensorBodyAlarm;
    public boolean isAirFlowSensorWingAlarm;

    // anti_collision_light
    public boolean isAntiCollisionLightOn;

    // apu
    public boolean isAPUStarted;
    public int rpmAPU;

    // apu_oil_tank
    public int levelAPUOilTank;

    // battery
    public int percentageBattery;

    // bulk_cargo_door
    public boolean isBulkCargoDoorClosed;
    public boolean isBulkCargoDoorLocked;

    /* business_class_seat
     * original (with name conflicts):
     * public boolean isNonSmokingSignOn;
     * public boolean isSeatBeltSignOn;
     * public int levelSeat;
     */
    public boolean isBusinessClassNonSmokingSignOn;
    public boolean isBusinessClassSeatBeltSignOn;
    public int businessClassLevelSeat;

    // camera
    public boolean isCameraOn;

    // cargo_compartment_light
    public boolean isCargoCompartmentLightOn;

    // cargo_system
    public boolean isCargoSystemLocked;
    public boolean isCargoSystemSecured;
    public int numberOfContainerFrontStowage;
    public int numberOfContainerRearStowage;
    public double totalWeightContainerFrontStowage;
    public double totalWeightContainerRearStowage;

    // cost_optimizer
    public boolean isCostOptimizerOn;
    public int indexCostOptimizer;
    public int numberOfCheckPointsCostOptimizer;

    /* crew_seat
     * original (with name conflicts):
     * public boolean isNonSmokingSignOn;
     * public boolean isSeatBeltSignOn;
     */
    public boolean isCrewSeatNonSmokingSignOn;
    public boolean isCrewSeatSeatBeltSignOn;

    // deicing_system
    public boolean isDeIcingSystemActivated;
    public int amountDeIcingSystem;

    // droop_nose
    public int degreeDroopNose;

    /* economy_class_seat
     * original (with name conflicts):
     * public boolean isNonSmokingSignOn;
     * public boolean isSeatBeltSignOn;
     * public int levelSeat;
     */
    public boolean isEconomyClassNonSmokingSignOn;
    public boolean isEconomyClassSeatBeltSignOn;
    public int economyClassLevelSeat;

    // elevator
    public int degreeElevator;

    // emergency_exit_door
    public boolean isEmergencyExitDoorClosed;
    public boolean isEmergencyExitDoorLocked;
    public boolean isEscapeSlideActivated;

    // engine
    public boolean isEngineStarted;
    public int rpmEngine;
    public boolean isEngineFire;

    // engine_oil_tank
    public int levelEngineOilTank;

    // exhaust_gas_temperature_sensor
    public int temperatureExhaustGasTemperatureSensor;
    public boolean isAlarmMajorExhaustGasTemperatureSensor;
    public boolean isAlarmCriticalExhaustGasTemperatureSensor;

    // fire_detector
    public boolean isFireDetectedBody;
    public boolean isFireDetectedWing;

    // flap
    public int degreeFlap;

    // fuel_flow_sensor
    public int fuelFlow;

    // fuel_sensor
    // original (with name conflicts):
    // public double amountOfFuel;
    public double fuelSensorAmountOfFuel;
    public boolean isAlarmReserveFuelSensor;
    public boolean isAlarmMajorFuelSensor;
    public boolean isAlarmCriticalFuelSensor;

    // fuel_tank
    // original (with name conflicts):
    // public int amountOfFuel;
    public int fuelTankAmountOfFuel;

    // gear
    public boolean isGearDown;
    public int gearBrakePercentage;

    // gear_door
    public boolean isGearDoorClosed;
    public boolean isGearDoorLocked;

    // gps
    public boolean isGPSOn;
    public boolean isGPSConnected;

    // hydraulic_pump
    public int hydraulicPumpBodyOilAmount;
    public int hydraulicPumpWingOilAmount;

    // ice_detector_probe
    public boolean isIceDetectorProbeBodyActivated;
    public boolean isIceDetectorProbeWingActivated;
    public boolean isIceDetected;

    // kitchen
    public boolean isKitchenLocked;
    public boolean isKitchenFilled;
    public int numberOfTrolley;

    // landing_light
    public boolean isLandingLightBodyOn;
    public boolean isLandingLightWingOn;

    // left_aileron
    public int degreeLeftAileron;

    // left_navigation_light
    public boolean isLeftNavigationLightOn;

    // logo_light
    public boolean isLogoLightOn;

    // nitrogen_bottle
    public int amountOfNitrogen;

    // oxygen_bottle
    public int oxygenBottleAmount;

    // oxygen_sensor
    public boolean isOxygenSensorAlarm;

    // pitot_tube
    public boolean isPitotTubeCleaned;
    public int velocity;

    // potable_watertank
    public boolean isPotableWaterTankLocked;
    public int amountPotableWater;

    /* premium_economy_class_seat
     * original (with name conflicts):
     * public boolean isNonSmokingSignOn;
     * public boolean isSeatBeltSignOn;
     * public int levelSeat;
     */
    public boolean isPremiumEconomyNonSmokingSignOn;
    public boolean isPremiumEconomySeatBeltSignOn;
    public int premiumEconomyLevelSeat;

    // radar
    public boolean isRadarOn;

    // radar_altimeter
    public boolean isRadarAltimeterOn;
    public int altitudeRadarAltimeter;

    // right_aileron
    public int degreeRightAileron;

    // right_navigation_light
    public boolean isRightNavigationLightOn;

    // route_management
    public boolean isRouteManagementOn;
    public int indexRouteManagement;
    public int numberOfCheckPointsRouteManagement;

    // rudder
    public int degreeRudder;

    // satcom
    public boolean isSatComConnected;

    // shock_sensor
    public boolean isShockSensorBodyShockDetected;
    public boolean isShockSensorBodyCalibrated;
    public boolean isShockSensorWingShockDetected;
    public boolean isShockSensorWingCalibrated;

    // slat
    public int degreeSlat;

    // spoiler
    public int degreeSpoiler;

    // stalling_sensor
    public boolean isStallingSensorAlarm;

    // tail_navigation_light
    public boolean isTailNavigationLightOn;

    // taxi_light
    public boolean isTaxiLightOn;

    // tcas
    public boolean isTCASOn;
    public boolean isTCASConnected;
    public boolean isTCASAlarm;
    public int altitudeTCAS;

    // temperature_sensor
    public int temperatureBody;
    public boolean isTemperatureSensorBodyAlarm;
    public int temperatureWing;
    public boolean isTemperatureSensorWingAlarm;

    // turbulent_airflow_sensor
    public boolean isTurbulentAirFlowAlarm;

    // vhf
    public boolean isVHFOn;
    public String selectedChannelVHF;

    // wastewater_tank
    public boolean isWasteWaterTankLocked;
    public int capacityWasteWater;

    // weather_radar
    public boolean isWeatherRadarOn;

    //oxygenBottle
    public int amountOxygenBottle;

    //nitrogenBottle
    public int amountNitrogenBottle;

    //crew_seat & economy_class_seat
    public boolean isNonSmokingSignOn;
    public boolean isSeatBeltSignOn;

    //economy_class_seat
    public int levelSeat;
    
}