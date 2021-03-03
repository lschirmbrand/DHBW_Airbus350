package base;

public enum PrimaryFlightDisplay {
    instance;

    // air_conditioning
    public boolean isAirConditioningOn;
    public int temperatureAirConditioning;

    // apu
    public boolean isAPUStarted;
    public int rpmAPU;

    // elevator
    public int degreeElevator;

    // engine
    public boolean isEngineStarted;
    public int rpmEngine;
    public boolean isEngineFire;

    // gear
    public boolean isGearDown;
    public int gearBrakePercentage;

    // hydraulic_pump
    public int hydraulicPumpBodyOilAmount;
    public int hydraulicPumpWingOilAmount;

    // weather_radar
    public boolean isWeatherRadarOn;

}