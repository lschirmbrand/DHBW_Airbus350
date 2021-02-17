package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.right_aileron.*;
import event.rudder.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.AntiCollisionLightFactory;
import factory.RudderFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> rudderPortList;
    private ArrayList<Object> antiCollisionLightPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        rudderPortList = new ArrayList<>();
        antiCollisionLightPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
            rudderPortList.add(RudderFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
            antiCollisionLightPortList.add(AntiCollisionLightFactory.build());
        }
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method onMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarOff weatherRadarOff) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method offMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarScan weatherRadarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarScan.toString() + ")");
    }


    // --- Rudder -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RudderNeutral rudderNeutral) { //TODO
        FlightRecorder.instance.insert("Body", "receive(" + rudderNeutral.toString() + ")");
    }

    @Subscribe
    public void receive(RudderFullLeft rudderFullLeft) {//TODO
        FlightRecorder.instance.insert("Body", "receive(" + rudderFullLeft.toString() + ")");
    }

    @Subscribe
    public void receive(RudderFullRight rudderFullRight) {//TODO
        LogEngine.instance.write("+ Body.receive(" + rudderFullRight.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderFullRight.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method fullRightMethod = rudderPortList.get(i).getClass().getDeclaredMethod("fullRight");
                LogEngine.instance.write("fullRightMethod = " + fullRightMethod);

                int degree = (int) fullRightMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderRight rudderRight) {//TODO
        LogEngine.instance.write("+ Body.receive(" + rudderRight.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderRight.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method rightMethod = rudderPortList.get(i).getClass().getDeclaredMethod("right");
                LogEngine.instance.write("rightMethod = " + rightMethod);

                int degree = (int) rightMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderLeft rudderLeft) {//TODO
        LogEngine.instance.write("+ Body.receive(" + rudderLeft.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderLeft.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method leftMethod = rudderPortList.get(i).getClass().getDeclaredMethod("left");
                LogEngine.instance.write("leftMethod = " + leftMethod);

                int degree = (int) leftMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    // --- AntiCollisionLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AntiCollisionLightOn antiCollisionLightOn) {
        LogEngine.instance.write("+ Body.receive(" + antiCollisionLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + antiCollisionLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
                Method onMethod = antiCollisionLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(antiCollisionLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
                FlightRecorder.instance.insert("Body", "AntiCollisionLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAntiCollisionLightOn): " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAntiCollisionLightOn: " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
    }

    @Subscribe
    public void receive(AntiCollisionLightOff antiCollisionLightOff) {
        LogEngine.instance.write("+ Body.receive(" + antiCollisionLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + antiCollisionLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
                Method offMethod = antiCollisionLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(antiCollisionLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
                FlightRecorder.instance.insert("Body", "AntiCollisionLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAntiCollisionLightOn): " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAntiCollisionLightOn: " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
    }
    // ----------------------------------------------------------------------------------------------------------------
}