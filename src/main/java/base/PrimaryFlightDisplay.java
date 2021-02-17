package base;

public enum PrimaryFlightDisplay {
    instance;

    // APU
    public boolean isAPUStarted;
    public int rpmAPU;

    // Gear
    public boolean isGearDown;
    public int gearBrakePercentage;

    // weather-radar
    public boolean isWeatherRadarOn;

}