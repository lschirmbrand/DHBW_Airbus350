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
import event.elevator.*;
import event.engine.EngineDecreaseRPM;
import event.engine.EngineIncreaseRPM;
import event.engine.EngineShutdown;
import event.engine.EngineStart;
import event.gear.*;
import event.hydraulicPump.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

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


        // Elevator
        eventBus.post(new ElevatorNeutral());
    }

    public void taxi() {
        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearReleaseBrake());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));
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


        // Elevator
        eventBus.post(new ElevatorFullUp());

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


        // elevator
        eventBus.post(new ElevatorUp(10));

        // engine
        eventBus.post(new EngineIncreaseRPM(500));
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

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));

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

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 25));

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

        // Elevator
        eventBus.post(new ElevatorFullDown());
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

    }
}