package base;

public enum PrimaryFlightDisplay {
    instance;

    //WeatherRadar
    public boolean isWeatherRadarOn;
    public boolean isEngineStarted;

    //Engine
    public int rpmEngine;
    public boolean isEngineFire;

    //HydraulicPump
    public int hydraulicPumpBodyOilAmount;
    public int hydraulicPumpWingOilAmount;

    //Elevator
    public int degreeElevator;
}