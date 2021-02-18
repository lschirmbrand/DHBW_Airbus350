package configuration;

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
    public String pathToAirConditioningJavaArchive = commonPathToJavaArchive + "cabin" + fileSeparator + "air_conditioning" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "air_conditioning.jar";
    public int numberOfAirConditioning = 4;

    // apu
    public String pathToApuJavaArchive = commonPathToJavaArchive + "apu_engine_gear_pump" + fileSeparator + "apu" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "apu.jar";
    public int numberOfApu = 1;

    // gear
    public String pathToGearJavaArchive = commonPathToJavaArchive + "apu_engine_gear_pump" + fileSeparator + "gear" + fileSeparator  + "build" + fileSeparator + "libs" + fileSeparator + "gear.jar";
    public int numberOfGearFront = 1;
    public int numberOfGearRear = 2;

    // weather_radar
    public String pathToWeatherRadarJavaArchive = commonPathToJavaArchive + "weather_radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "weather_radar.jar";
    public int numberOfWeatherRadar = 2;

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
}