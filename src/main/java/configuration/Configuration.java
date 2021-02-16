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
}