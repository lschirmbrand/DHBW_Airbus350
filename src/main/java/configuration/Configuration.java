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
    public int numberOfRouteManagement  = 2;

}