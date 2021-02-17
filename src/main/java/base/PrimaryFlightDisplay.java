package base;

public enum PrimaryFlightDisplay {
    instance;
    //WeatherRadar
    public boolean isWeatherRadarOn;

    //CargoCompartmentLight
    public boolean isCargoCompartmentLightOn;

    //CostOptimizer
    public boolean isCostOptimizerOn;
    public int indexCostOptimizer;
    public int numberOfCheckPointsCostOptimizer;

    //LandingLight
    public boolean isLandingLightBodyOn;
    public boolean isLandingLightWingOn;

    //LeftNavigationLight
    public boolean isLeftNavigationLightOn;

    //LogoLight
    public boolean isLogoLightOn;

    //RouteManagement
    public boolean isRouteManagementOn;
    public int indexRouteManagement;
    public int numberOfCheckPointsRouteManagement;
}