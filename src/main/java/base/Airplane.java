package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.elevator.*;
import event.engine.*;
import event.hydraulicPump.*;
import event.weather_radar.*;
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
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //Engine
        eventBus.post(new EngineStart());

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyRefillOil());
        eventBus.post(new HydraulicPumpWingRefillOil());


        // Elevator
        eventBus.post(new ElevatorNeutral());
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // engine
        eventBus.post(new EngineIncreaseRPM(5000));

        // hydraulic pump
        eventBus.post(new HydraulicPumpBodyDecompress());
        eventBus.post(new HydraulicPumpWingDecompress());

        // Elevator
        eventBus.post(new ElevatorFullUp());

    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // elevator
        eventBus.post(new ElevatorUp(20));
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // elevator
        eventBus.post(new ElevatorDown(-20));
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // engine
        eventBus.post(new EngineDecreaseRPM(5000));

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyCompress());
        eventBus.post(new HydraulicPumpWingCompress());

        // Elevator
        eventBus.post(new ElevatorFullDown());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());

        //Engine
        eventBus.post(new EngineShutdown());

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyRefillOil());
        eventBus.post(new HydraulicPumpWingRefillOil());

        //Elevator

        eventBus.post(new ElevatorNeutral());

    }
}