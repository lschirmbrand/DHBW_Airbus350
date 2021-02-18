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
    public int degreeDroopNose;
    public int amountOfNitrogen;
    public int amountPotableWater;
    public int capacityWasteWater;
    public String selectedChannelVHF;
    public boolean isVHFOn;
    public boolean isSatComConnected;
    public int oxygenBottleAmount;
    public boolean isTCASOn;
    public boolean isTCASConnected;
    public boolean isTCASAlarm;
    public int altitudeTCAS;
    public boolean isTurbulentAirFlowAlarm;
    public boolean isCameraOn;
    public boolean isGPSOn;
    public boolean isGPSConnected;
    public boolean isRadarOn;


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