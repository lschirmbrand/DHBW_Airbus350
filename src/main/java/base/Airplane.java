package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.left_aileron.*;
import event.right_aileron.RightAileronDown;
import event.right_aileron.RightAileronFullUp;
import event.right_aileron.RightAileronNeutral;
import event.rudder.RudderFullLeft;
import event.rudder.RudderNeutral;
import event.rudder.RudderRight;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import event.spoiler.SpoilerFullUp;
import event.spoiler.SpoilerNeutral;
import event.spoiler.SpoilerUp;
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
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatNeutral());
        //left_aileron
        eventBus.post(new LeftAileronNeutral());
        //right_aileron
        eventBus.post(new RightAileronNeutral());
        //rudder
        eventBus.post(new RudderNeutral());
        //spoiler
        eventBus.post(new SpoilerNeutral());
        // anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatNeutral());
        //left_aileron
        eventBus.post(new LeftAileronNeutral());
        //right_aileron
        eventBus.post(new RightAileronNeutral());
        //rudder
        eventBus.post(new RudderNeutral());
        //spoiler
        eventBus.post(new SpoilerNeutral());
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatFullDown());
        //left_aileron
        eventBus.post(new LeftAileronNeutral());
        //right_aileron
        eventBus.post(new RightAileronNeutral());
        //rudder
        eventBus.post(new RudderNeutral());
        //spoiler
        eventBus.post(new SpoilerFullUp());
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatNeutral());
        //left_aileron
        eventBus.post(new LeftAileronNeutral());
        //right_aileron
        eventBus.post(new RightAileronNeutral());
        //rudder
        eventBus.post(new RudderNeutral());
        //spoiler
        eventBus.post(new SpoilerNeutral());
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatNeutral());
        //left_aileron
        eventBus.post(new LeftAileronFullDown());
        //right_aileron
        eventBus.post(new RightAileronFullUp());
        //rudder
        eventBus.post(new RudderRight(15));
        //spoiler
        eventBus.post(new SpoilerNeutral());
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatNeutral());
        //left_aileron
        eventBus.post(new LeftAileronUp(15));
        //right_aileron
        eventBus.post(new RightAileronDown(15));
        //rudder
        eventBus.post(new RudderFullLeft());
        //spoiler
        eventBus.post(new SpoilerNeutral());
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatNeutral());
        //left_aileron
        eventBus.post(new LeftAileronNeutral());
        //right_aileron
        eventBus.post(new RightAileronNeutral());
        //rudder
        eventBus.post(new RudderNeutral());
        //spoiler
        eventBus.post(new SpoilerUp(15));
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        // slat
        eventBus.post(new SlatDown(15));
        //left_aileron
        eventBus.post(new LeftAileronNeutral());
        //right_aileron
        eventBus.post(new RightAileronNeutral());
        //rudder
        eventBus.post(new RudderNeutral());
        //spoiler
        eventBus.post(new SpoilerNeutral());
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOn());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());
        // slat
        eventBus.post(new SlatUp(15));
        //left_aileron
        eventBus.post(new LeftAileronNeutral());
        //right_aileron
        eventBus.post(new RightAileronNeutral());
        //rudder
        eventBus.post(new RudderNeutral());
        //spoiler
        eventBus.post(new SpoilerNeutral());
        //anti_collision_light
        eventBus.post(new AntiCollisionLightOff());
    }
}