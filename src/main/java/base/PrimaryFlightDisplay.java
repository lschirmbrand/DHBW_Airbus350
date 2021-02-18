package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    public int degreeSlat;
    public int degreeLeftAileron;
    public int degreeRightAileron;
    public int degreeRudder;
    public int degreeSpoiler;
    public boolean isAntiCollisionLightOn;

    //CargoCompartmentLight
    public boolean isCargoCompartmentLightOn;

    //CostOptimizer
    public boolean isCostOptimizerOn;
    public int indexCostOptimizer = 0;
    public int numberOfCheckPointsCostOptimizer = 0;

    //LandingLight
    public boolean isLandingLightBodyOn;
    public boolean isLandingLightWingOn;

    //LeftNavigationLight
    public boolean isLeftNavigationLightOn;

    //LogoLight
    public boolean isLogoLightOn;

    //RouteManagement
    public boolean isRouteManagementOn;
    public int indexRouteManagement = 0;
    public int numberOfCheckPointsRouteManagement = 0;
}