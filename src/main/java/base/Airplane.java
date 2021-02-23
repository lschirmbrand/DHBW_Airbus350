package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.air_conditioning.AirConditioningClean;
import event.air_conditioning.AirConditioningHeat;
import event.air_conditioning.AirConditioningOff;
import event.air_conditioning.AirConditioningOn;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
import event.droop_nose.DroopNoseDown;
import event.droop_nose.DroopNoseFullDown;
import event.droop_nose.DroopNoseNeutral;
import event.droop_nose.DroopNoseUp;
import event.elevator.*;
import event.engine.EngineDecreaseRPM;
import event.engine.EngineIncreaseRPM;
import event.engine.EngineShutdown;
import event.engine.EngineStart;
import event.gear.*;
import event.gps.*;
import event.hydraulicPump.*;
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.radar.RadarOff;
import event.radar.RadarOn;
import event.radar.RadarScan;
import event.tcas.*;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

@SuppressWarnings({"UnstableApiUsage", "FieldCanBeLocal"})
public class Airplane implements IAirplane {
    private final EventBus eventBus;
    private Body body;
    private Wing leftWing;
    private Wing rightWing;

    public Airplane() {
        eventBus = new EventBus("EB-A350");
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void build() {
        body = new Body();
        addSubscriber(body);

        leftWing = new Wing();
        addSubscriber(leftWing);

        rightWing = new Wing();
        addSubscriber(rightWing);
    }

    public void startup() {
        // air_conditioning
        eventBus.post(new AirConditioningOn());
        eventBus.post(new AirConditioningClean("wusch"));

        //Radar
        eventBus.post(new RadarOn());

        // gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //Engine
        eventBus.post(new EngineStart());
        eventBus.post(new EngineIncreaseRPM(0));

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyCompress());
        eventBus.post(new HydraulicPumpWingCompress());
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        // Elevator
        eventBus.post(new ElevatorNeutral());

        //Camera
        eventBus.post(new CameraBodyOn());
        eventBus.post(new CameraWingOn());

        //GPS
        eventBus.post(new GPSOn());
        eventBus.post(new GPSConnect("Astra-8"));

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASOn());
        eventBus.post(new TCASConnect("15000"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        //OxygenTank
        eventBus.post(new OxygenBottleRefill());

        //NitrogenTank
        eventBus.post(new NitrogenBottleRefill());
    }

    public void taxi() {
        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearReleaseBrake());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));
        //Droop Nose
        eventBus.post(new DroopNoseNeutral());
        //GPS
        eventBus.post(new GPSSend("Taxi"));
        eventBus.post(new GPSReceive());
        //Radar
        eventBus.post(new RadarScan("Erde"));
        //TCAS
        eventBus.post(new TCASScan("Erde"));
        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
    }

    public void takeoff() {
        // apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(5000));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));


        // engine
        eventBus.post(new EngineIncreaseRPM(15000));

        // hydraulic pump
        eventBus.post(new HydraulicPumpBodyDecompress());
        eventBus.post(new HydraulicPumpWingDecompress());
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Droop Nose
        eventBus.post(new DroopNoseDown(10));

        // Elevator
        eventBus.post(new ElevatorFullUp());

        //GPS
        eventBus.post(new GPSSend("TakeoOff"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
    }

    public void climbing() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(2500));

        // gear
        eventBus.post(new GearUp());
        eventBus.post(new GearSetBrakePercentage(50));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        // elevator
        eventBus.post(new ElevatorUp(10));

        // engine
        eventBus.post(new EngineIncreaseRPM(500));

        //GPS
        eventBus.post(new GPSSend("Climbing"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
    }

    public void rightTurn() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(1000));

        // gear
        eventBus.post(new GearUp());
        eventBus.post(new GearSetBrakePercentage(50));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));

        //GPS
        eventBus.post(new GPSSend("rightTurn"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
    }

    public void leftTurn() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(1000));

        // gear
        eventBus.post(new GearUp());
        eventBus.post(new GearSetBrakePercentage(50));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));

        //GPS
        eventBus.post(new GPSSend("leftTurn"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
    }

    public void descent() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));

        //gear
        eventBus.post(new GearUp());
        eventBus.post(new GearSetBrakePercentage(50));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));


        // elevator
        eventBus.post(new ElevatorDown(-10));

        // engine
        eventBus.post(new EngineDecreaseRPM(-500));

        //Droop Nose
        eventBus.post(new DroopNoseUp(20));

        //GPS
        eventBus.post(new GPSSend("descent"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
    }

    public void landing() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));

        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearReleaseBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));


        // engine
        eventBus.post(new EngineDecreaseRPM(-10000));

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyCompress());
        eventBus.post(new HydraulicPumpWingCompress());
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Droop Nose
        eventBus.post(new DroopNoseFullDown());

        // Elevator
        eventBus.post(new ElevatorFullDown());

        //GPS
        eventBus.post(new GPSSend("landing"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
    }

    public void shutdown() {
        // apu
        eventBus.post(new APUShutdown());

        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOff());

        // air_conditioning
        eventBus.post(new AirConditioningOff());


        //Engine
        eventBus.post(new EngineShutdown());
        eventBus.post(new EngineDecreaseRPM(-15000));

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Elevator

        eventBus.post(new ElevatorNeutral());

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        //Camera
        eventBus.post(new CameraBodyOff());
        eventBus.post(new CameraWingOff());

        //GPS
        eventBus.post(new GPSOff());

        //Radar
        eventBus.post(new RadarOff());

        //TCAS
        eventBus.post(new TCASOff());

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        //OxygenTank
        eventBus.post(new OxygenBottleTakeOut(100));

        //NitrogenTank
        eventBus.post(new NitrogenBottleTakeOut(250));
    }
}