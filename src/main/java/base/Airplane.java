package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.gear.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

public class Airplane implements IAirplane {
    private EventBus eventBus;
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
        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void taxi() {
        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearReleaseBrake());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        // apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(5000));

        // weather_radar
        eventBus.post(new WeatherRadarOn());
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
    }

    public void descent() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));

        //gear
        eventBus.post(new GearUp());
        eventBus.post(new GearSetBrakePercentage(50));

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void landing() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));

        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearReleaseBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void shutdown() {
        // apu
        eventBus.post(new APUShutdown());

        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOff());
    }
}