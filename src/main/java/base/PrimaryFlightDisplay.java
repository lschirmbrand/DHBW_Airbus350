package base;

public enum PrimaryFlightDisplay {
    instance;

    // APU
    public boolean isAPUStarted;
    public int rpmAPU;

    // weather-radar
    public boolean isWeatherRadarOn;

}