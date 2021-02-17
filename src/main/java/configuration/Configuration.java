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

    // weather_radar
    public String pathToWeatherRadarJavaArchive = commonPathToJavaArchive + "weather_radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "weather_radar.jar";
    public int numberOfWeatherRadar = 2;

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
    public String pathToAntiCollisionLightJavaArchive = commonPathToJavaArchive + "flight_controls_02" + fileSeparator + "anti_collision_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "anti_collision_light.jar";
    public int numberOfAntiCollisionLight = 2;

}