package configuration;

@SuppressWarnings("unused")
public enum Configuration {
    instance;

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;

    public String lineSeparator = System.getProperty("line.separator");
    public String logFileDirectory = userDirectory + fileSeparator + "log" + fileSeparator;
    public String logFile = logFileDirectory + "airbus_a350.log";
    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String databaseFile = dataDirectory + "flight_recorder_a350.db";


    // air_conditioning
    public String pathToAirConditioningJavaArchive = commonPathToJavaArchive + "air_conditioning" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "air_conditioning.jar";
    public int numberOfAirConditioning = 4;

    // airflow_sensor
    public String pathToAirFlowSensorJavaArchive = commonPathToJavaArchive + "airflow_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "airflow_sensor.jar";
    public int numberOfAirFlowSensorBody = 2;
    public int numberOfAirFlowSensorWing = 2;

    // apu
    public String pathToApuJavaArchive = commonPathToJavaArchive + "apu" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "apu.jar";
    public int numberOfApu = 1;

    // battery
    public String pathToBatteryJavaArchive = commonPathToJavaArchive + "battery" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "battery.jar";
    public int numberOfBattery = 24;

    // deicing_system
    public String pathToDeIcingSystemJavaArchive = commonPathToJavaArchive + "deicing_system" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "deicing_system.jar";
    public int numberOfDeIcingSystemBody = 2;
    public int numberOfDeIcingSystemWing = 2;

    // gear
    public String pathToGearJavaArchive = commonPathToJavaArchive + fileSeparator + "gear" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "gear.jar";
    public int numberOfGearFront = 1;
    public int numberOfGearRear = 2;

    // weather_radar
    public String pathToWeatherRadarJavaArchive = commonPathToJavaArchive + "weather_radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "weather_radar.jar";
    public int numberOfWeatherRadar = 2;

    //droop_nose
    public String pathToDroopNoseJavaArchive = commonPathToJavaArchive + "droop_nose" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "droop_nose.jar";
    public int numberOfDroopNose = 2;

    //nitrogen_bottle
    public String pathToNitrogenBottleJavaArchive = commonPathToJavaArchive + "nitrogen_bottle" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "nitrogen_bottle.jar";
    public int numberOfNitrogenBottle = 6;

    //oxygen_bottle
    public String pathToOxygenBottleJavaArchive = commonPathToJavaArchive + "oxygen_bottle" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "oxygen_bottle.jar";
    public int numberOfOxygenBottle = 10;

    //portable_watertank
    public String pathToPortableWaterTankJavaArchive = commonPathToJavaArchive + "portable_watertank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "potable_water_tank.jar";
    public int numberOfPotableWaterTank = 8;

    //satcom
    public String pathToSatComJavaArchive = commonPathToJavaArchive + "satcom" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "sat_com.jar";
    public int numberOfSatCom = 2;

    //vhf
    public String pathToVHFJavaArchive = commonPathToJavaArchive + "vhf" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "vhf.jar";
    public int numberOfVHF = 2;

    //wastewater_tank
    public String pathToWasteWaterTankJavaArchive = commonPathToJavaArchive + "wastewater_tank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "waste_water_tank.jar";
    public int numberOfWasteWaterTank = 4;

    //tcas
    public String pathToTCASJavaArchive = commonPathToJavaArchive + "tcas" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "tcas.jar";
    public int numberOfTCAS = 2;

    //temperature_sensor
    public String pathToTemperatureSensorJavaArchive = commonPathToJavaArchive + "temperature_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "temperature_sensor.jar";
    public int numberOfTemperatureSensorBody = 2;
    public int numberOfTemperatureSensorWing = 4;

    //turbulent_air_flow_sensor
    public String pathToTurbulentAirFlowSensorJavaArchive = commonPathToJavaArchive + "turbulent_airflow_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "turbulent_air_flow_sensor.jar";
    public int numberOfTurbulentAirFlowSensorBody = 2;
    public int numberOfTurbulentAirFlowSensorWing = 4;

    //Camera
    public String pathToCameraJavaArchive = commonPathToJavaArchive + "camera" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "camera.jar";
    public int numberOfCameraBody = 2;
    public int numberOfCameraWing = 1;

    //GPS
    public String pathToGPSJavaArchive = commonPathToJavaArchive + "gps" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "gps.jar";
    public int numberOfGPS = 2;

    //Radar
    public String pathToRadarJavaArchive = commonPathToJavaArchive + "radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "radar.jar";
    public int numberOfRadar = 2;

    // Engine
    public String pathToEngineJavaArchive = commonPathToJavaArchive + "engine" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "engine.jar";
    public int numberOfEngine = 1;

    // Hydraulic_Pump
    public String pathToHydraulicPumpJavaArchive = commonPathToJavaArchive + "hydraulic_pump" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "hydraulic_pump.jar";
    public int numberOfHydraulicPumpBody = 6;
    public int numberOfHydraulicPumpWing = 4;

    //Elevator
    public String pathToElevatorJavaArchive = commonPathToJavaArchive + "elevator" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "elevator.jar";
    public int numberOfElevator = 4;

    // crew_seat
    public String pathToCrewSeatJavaArchive = commonPathToJavaArchive + "crew_seat" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "crew_seat.jar";
    public int numberOfCrewSeat = 8;

    // economy_class_seat
    public String pathToEconomyClassSeatJavaArchive = commonPathToJavaArchive + "economy_class_seat" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "economy_class_seat.jar";
    public int numberOfEconomyClassSeat = 262;

    // fire_detector
    public String pathToFireDetectorJavaArchive = commonPathToJavaArchive + "fire_detector" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "fire_detector.jar";
    public int numberOfFireDetectorBody = 14;
    public int numberOfFireDetectorWing = 4;

    // oxygen_sensor
    public String pathToOxygenSensorJavaArchive = commonPathToJavaArchive + "oxygen_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "oxygen_sensor.jar";
    public int numberOfOxygenSensor = 8;

    // slat
    public String pathToSlatJavaArchive = commonPathToJavaArchive + "flight_controls_01" + fileSeparator + "slat" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "slat.jar";
    public int numberOfSlat = 6;

    // left_Aileron
    public String pathToLeftAileronJavaArchive = commonPathToJavaArchive + "flight_controls_02" + fileSeparator + "left_aileron" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "left_aileron.jar";
    public int numberOfLeftAileron = 3;

    // right_Aileron
    public String pathToRightAileronJavaArchive = commonPathToJavaArchive + "flight_controls_02" + fileSeparator + "right_aileron" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "right_aileron.jar";
    public int numberOfRightAileron = 3;

    // rudder
    public String pathToRudderJavaArchive = commonPathToJavaArchive + "flight_controls_02" + fileSeparator + "rudder" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "rudder.jar";
    public int numberOfRudder = 2;

    // spoiler
    public String pathToSpoilerJavaArchive = commonPathToJavaArchive + "flight_controls_02" + fileSeparator + "spoiler" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "spoiler.jar";
    public int numberOfSpoiler = 8;

    // anti_collision_light
    public String pathToAntiCollisionLightJavaArchive = commonPathToJavaArchive + "light" + fileSeparator + "anti_collision_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "anti_collision_light.jar";
    public int numberOfAntiCollisionLight = 2;


    //cargo_compartment_light
    public String pathToCargoCompartmentLight = commonPathToJavaArchive + "cargo_compartment_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "cargo_compartment_light.jar";
    public int numberOfCargoCompartmentLight = 4;

    //landing_light
    public String pathToLandingLight = commonPathToJavaArchive + "landing_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "landing_light.jar";
    public int numberOfLandingLightBody = 2;
    public int numberOfLandingLightWing = 2;

    //left_navigation_light
    public String pathToLeftNavigationLight = commonPathToJavaArchive + "left_navigation_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "left_navigation_light.jar";
    public int numberOfLeftNavigationLight = 1;

    //logo_light
    public String pathToLogoLight = commonPathToJavaArchive + "logo_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "logo_light.jar";
    public int numberOfLogoLight = 2;

    //cost_optimizer
    public String pathToCostOptimizer = commonPathToJavaArchive + "cost_optimizer" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "cost_optimizer.jar";
    public int numberOfCostOptimizer = 2;

    //route_management
    public String pathToRouteManagement = commonPathToJavaArchive + "route_management" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "route_management.jar";
    public int numberOfRouteManagement = 2;
}