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
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

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
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // elevator
        eventBus.post(new ElevatorUp(10));

        // engine
        eventBus.post(new EngineIncreaseRPM(500));
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
        eventBus.post(new ElevatorDown(-10));

        // engine
        eventBus.post(new EngineDecreaseRPM(-500));
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

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
        // weather_radar
        eventBus.post(new WeatherRadarOff());

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