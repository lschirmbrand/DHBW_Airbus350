package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.landing_light.LandingLightWingOff;
import event.landing_light.LandingLightWingOn;
import event.left_navigation_light.LeftNavigationLightOff;
import event.left_navigation_light.LeftNavigationLightOn;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.LandingLightFactory;
import factory.LeftNavigationLightFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {
    private ArrayList<Object> leftNavigationLightPortList;
    private ArrayList<Object> landingLightWingPortList;

    public Wing(){
        leftNavigationLightPortList = new ArrayList<>();
        landingLightWingPortList = new ArrayList<>();
        build();
    }

    public void build(){
        for(int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++){
            leftNavigationLightPortList.add(LeftNavigationLightFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++){
            landingLightWingPortList.add(LandingLightFactory.build());
        }
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LeftNavigationLightOn leftNavigationLightOn) {
        LogEngine.instance.write("+ Wing.receive(" + leftNavigationLightOn.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftNavigationLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++) {
                Method onMethod = leftNavigationLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(leftNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLeftNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Wing", "LeftNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLeftNavigationLightOn): " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLeftNavigationLightOn: " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
    }

    @Subscribe
    public void receive(LeftNavigationLightOff leftNavigationLightOff) {
        LogEngine.instance.write("+ Wing.receive(" + leftNavigationLightOff.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftNavigationLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++) {
                Method offMethod = leftNavigationLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(leftNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLeftNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Wing", "LeftNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLeftNavigationLightOn): " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLeftNavigationLightOn: " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LandingLightWingOn landingLightWingOn) {
        LogEngine.instance.write("+ Wing.receive(" + landingLightWingOn.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + landingLightWingOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++) {
                Method onMethod = landingLightWingPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(landingLightWingPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightWingOn = isOn;
                FlightRecorder.instance.insert("Wing", "LandingLightWing (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightWingOn): " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightWingOn: " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
    }

    @Subscribe
    public void receive(LandingLightWingOff landingLightWingOff) {
        LogEngine.instance.write("+ Wing.receive(" + landingLightWingOff.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + landingLightWingOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++) {
                Method offMethod = landingLightWingPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(landingLightWingPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightWingOn = isOn;
                FlightRecorder.instance.insert("Wing", "LandingLightWing (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightWingOn): " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightWingOn: " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

}