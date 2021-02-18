package base;

public enum PrimaryFlightDisplay {
    instance;

    // air_conditioning
    public boolean isAirConditioningOn;
    public int temperatureAirConditioning;

    // apu
    public boolean isAPUStarted;
    public int rpmAPU;

    // gear
    public boolean isGearDown;
    public int gearBrakePercentage;

    // weather-radar
    public boolean isWeatherRadarOn;


    //Engine
    public boolean isEngineStarted;
    public int rpmEngine;
    public boolean isEngineFire;

    //HydraulicPump
    public int hydraulicPumpBodyOilAmount;
    public int hydraulicPumpWingOilAmount;

    //Elevator
    public int degreeElevator;
}