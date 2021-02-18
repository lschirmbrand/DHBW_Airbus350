package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cost_optimizer.CostOptimizerOff;
import event.cost_optimizer.CostOptimizerOn;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.landing_light.LandingLightWingOff;
import event.landing_light.LandingLightWingOn;
import event.left_navigation_light.LeftNavigationLightOn;
import event.logo_light.LogoLightOn;
import event.route_management.RouteManagementOff;
import event.route_management.RouteManagementOn;
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

        //CostOptimizer
        eventBus.post(new CostOptimizerOn());

        //RouteManagement
        eventBus.post(new RouteManagementOn());

        //LogoLight
        eventBus.post(new LogoLightOn());
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //LeftNavigationLight
        eventBus.post(new LeftNavigationLightOn());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //LandingLight
        eventBus.post(new LandingLightBodyOn());
        eventBus.post(new LandingLightWingOn());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());

        //cargoCompartmentLight
        eventBus.post(new CargoCompartmentLightOff());

        //costOptimizer
        eventBus.post(new CostOptimizerOff());

        //LandingLight
        eventBus.post(new LandingLightBodyOff());
        eventBus.post(new LandingLightWingOff());

        //RouteManagement
        eventBus.post(new RouteManagementOff());
    }
}