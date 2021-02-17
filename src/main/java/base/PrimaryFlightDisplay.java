package base;

public enum PrimaryFlightDisplay {
    instance;

    // air_conditioning

    public boolean isAirConditioningOn;
    public int temperatureAirConditioning;

    // APU
    public boolean isAPUStarted;
    public int rpmAPU;

    // Gear
    public boolean isGearDown;
    public int gearBrakePercentage;

    // weather-radar
    public boolean isWeatherRadarOn;

}