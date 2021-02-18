package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.left_aileron.*;
import event.right_aileron.*;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import event.spoiler.SpoilerDown;
import event.spoiler.SpoilerFullUp;
import event.spoiler.SpoilerNeutral;
import event.spoiler.SpoilerUp;
import factory.LeftAileronFactory;
import factory.RightAileronFactory;
import factory.SlatFactory;
import factory.SpoilerFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
    private ArrayList<Object> slatPortList;
    private ArrayList<Object> leftAileronPortList;
    private ArrayList<Object> rightAileronPortList;
    private ArrayList<Object> spoilerPortList;
    private ArrayList<Object> leftNavigationLightPortList;
    private ArrayList<Object> landingLightWingPortList;

    public Wing() {
        slatPortList = new ArrayList<>();
        leftAileronPortList = new ArrayList<>();
        rightAileronPortList = new ArrayList<>();
        spoilerPortList = new ArrayList<>();
        leftNavigationLightPortList = new ArrayList<>();
        landingLightWingPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList.add(SlatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
            leftAileronPortList.add(LeftAileronFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
            rightAileronPortList.add(RightAileronFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
            spoilerPortList.add(SpoilerFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++){
            leftNavigationLightPortList.add(LeftNavigationLightFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++){
            landingLightWingPortList.add(LandingLightFactory.build());
        }
    }

    // --- Slat -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatNeutral slatNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + slatNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method neutralMethod = slatPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("neutralMethod = " + neutralMethod);

                int degree = (int) neutralMethod.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    @Subscribe
    public void receive(SlatFullDown slatFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + slatFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method fullDownMethod = slatPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("fullDownMethod = " + fullDownMethod);

                int degree = (int) fullDownMethod.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    @Subscribe
    public void receive(SlatDown slatDown) {
        LogEngine.instance.write("+ Wing.receive(" + slatDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method downMethod = slatPortList.get(i).getClass().getDeclaredMethod("down", Integer.TYPE);
                System.out.println(0.5);
                LogEngine.instance.write("downMethod = " + downMethod);

                System.out.println(1);
                int degree = (int) downMethod.invoke(slatPortList.get(i), slatDown.getValue());
                System.out.println(1.5);
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                System.out.println(2.5);
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    @Subscribe
    public void receive(SlatUp slatUp) {
        LogEngine.instance.write("+ Wing.receive(" + slatUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method upMethod = slatPortList.get(i).getClass().getDeclaredMethod("up", Integer.TYPE);
                LogEngine.instance.write("upMethod = " + upMethod);

                int degree = (int) upMethod.invoke(slatPortList.get(i), slatUp.getValue());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    // --- LeftAileron -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LeftAileronNeutral leftAileronNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method neutralMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("neutralMethod = " + neutralMethod);

                int degree = (int) neutralMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronFullUp leftAileronFullUp) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronFullUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method fullUpMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("fullUpMethod = " + fullUpMethod);

                int degree = (int) fullUpMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronFullDown leftAileronFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method fullDownMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("fullDownMethod = " + fullDownMethod);

                int degree = (int) fullDownMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronDown leftAileronDown) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method downMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("down", Integer.TYPE);
                LogEngine.instance.write("downMethod = " + downMethod);

                int degree = (int) downMethod.invoke(leftAileronPortList.get(i), leftAileronDown.getValue());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronUp leftAileronUp) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method upMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("up", Integer.TYPE);
                LogEngine.instance.write("upMethod = " + upMethod);

                int degree = (int) upMethod.invoke(leftAileronPortList.get(i), leftAileronUp.getValue());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    // --- RightAileron -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RightAileronNeutral rightAileronNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method neutralMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("neutralMethod = " + neutralMethod);

                int degree = (int) neutralMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronFullUp rightAileronFullUp) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronFullUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method fullUpMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("fullUpMethod = " + fullUpMethod);

                int degree = (int) fullUpMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronFullDown rightAileronFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method fullDownMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("fullDownMethod = " + fullDownMethod);

                int degree = (int) fullDownMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronDown rightAileronDown) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method downMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("down", Integer.TYPE);
                LogEngine.instance.write("downMethod = " + downMethod);

                int degree = (int) downMethod.invoke(rightAileronPortList.get(i), rightAileronDown.getValue());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronUp rightAileronUp) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method upMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("up", Integer.TYPE);
                LogEngine.instance.write("upMethod = " + upMethod);

                int degree = (int) upMethod.invoke(rightAileronPortList.get(i), rightAileronUp.getValue());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    // --- Spoiler -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SpoilerNeutral spoilerNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method neutralMethod = spoilerPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("neutralMethod = " + neutralMethod);

                int degree = (int) neutralMethod.invoke(spoilerPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
    }

    @Subscribe
    public void receive(SpoilerFullUp spoilerFullUp) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerFullUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method fullUpMethod = spoilerPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("fullUpMethod = " + fullUpMethod);

                int degree = (int) fullUpMethod.invoke(spoilerPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
    }

    @Subscribe
    public void receive(SpoilerDown spoilerDown) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method downMethod = spoilerPortList.get(i).getClass().getDeclaredMethod("down", Integer.TYPE);
                LogEngine.instance.write("downMethod = " + downMethod);

                int degree = (int) downMethod.invoke(spoilerPortList.get(i), spoilerDown.getValue());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
    }

    @Subscribe
    public void receive(SpoilerUp spoilerUp) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method upMethod = spoilerPortList.get(i).getClass().getDeclaredMethod("up", Integer.TYPE);
                LogEngine.instance.write("upMethod = " + upMethod);

                int degree = (int) upMethod.invoke(spoilerPortList.get(i), spoilerUp.getValue());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
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