package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.airflow_sensor.AirFlowSensorWingMeasure;
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
import event.deicing_system.DeIcingSystemActivate;
import event.deicing_system.DeIcingSystemDeIce;
import event.deicing_system.DeIcingSystemDeactivate;
import event.deicing_system.DeIcingSystemRefill;
import event.droop_nose.DroopNoseDown;
import event.droop_nose.DroopNoseFullDown;
import event.droop_nose.DroopNoseNeutral;
import event.droop_nose.DroopNoseUp;
import event.elevator.*;
import event.engine.EngineDecreaseRPM;
import event.engine.EngineIncreaseRPM;
import event.engine.EngineShutdown;
import event.engine.EngineStart;
import event.fire_detector.FireDetectorWingScan;
import event.hydraulicPump.HydraulicPumpWingCompress;
import event.hydraulicPump.HydraulicPumpWingDecompress;
import event.hydraulicPump.HydraulicPumpWingRefillOil;
import event.landing_light.LandingLightWingOff;
import event.landing_light.LandingLightWingOn;
import event.left_aileron.*;
import event.left_navigation_light.LeftNavigationLightOff;
import event.left_navigation_light.LeftNavigationLightOn;
import event.right_aileron.*;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import event.spoiler.SpoilerDown;
import event.spoiler.SpoilerFullUp;
import event.spoiler.SpoilerNeutral;
import event.spoiler.SpoilerUp;
import event.temperature_sensor.TemperatureSensorWingMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressWarnings({"UnstableApiUsage", "unused", "DuplicatedCode"})
public class Wing extends Subscriber {

    private final ArrayList<Object> enginePortList;
    private final ArrayList<Object> hydraulicPumpPortList;
    private final ArrayList<Object> elevatorPortList;
    private final ArrayList<Object> cameraWingPortList;
    private final ArrayList<Object> droopNosePortList;
    private final ArrayList<Object> turbulentAirFlowSensorPortList;
    private final ArrayList<Object> fireDetectorPortList;
    private final ArrayList<Object> airFlowSensorWingPortList;
    private final ArrayList<Object> temperatureSensorWingPortList;
    private final ArrayList<Object> deIcingSystemPortList;
    private final ArrayList<Object> slatPortList;
    private final ArrayList<Object> leftAileronPortList;
    private final ArrayList<Object> rightAileronPortList;
    private final ArrayList<Object> spoilerPortList;
    private final ArrayList<Object> leftNavigationLightPortList;
    private final ArrayList<Object> landingLightWingPortList;

    public Wing() {
        enginePortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        elevatorPortList = new ArrayList<>();
        cameraWingPortList = new ArrayList<>();
        droopNosePortList = new ArrayList<>();
        turbulentAirFlowSensorPortList = new ArrayList<>();
        fireDetectorPortList = new ArrayList<>();
        airFlowSensorWingPortList = new ArrayList<>();
        temperatureSensorWingPortList = new ArrayList<>();
        deIcingSystemPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
        leftAileronPortList = new ArrayList<>();
        rightAileronPortList = new ArrayList<>();
        spoilerPortList = new ArrayList<>();
        leftNavigationLightPortList = new ArrayList<>();
        landingLightWingPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
            enginePortList.add(EngineFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
            hydraulicPumpPortList.add(HydraulicPumpFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
            elevatorPortList.add(ElevatorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfCameraWing; i++) {
            cameraWingPortList.add(CameraFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
            droopNosePortList.add(DroopNoseFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfTurbulentAirFlowSensorWing; i++) {
            turbulentAirFlowSensorPortList.add(TurbulentAirFlowSensorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfFireDetectorWing; i++) {
            fireDetectorPortList.add(FireDetectorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorWing; i++) {
            airFlowSensorWingPortList.add(AirFlowSensorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorWing; i++) {
            temperatureSensorWingPortList.add(TemperatureSensorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorWing; i++) {
            deIcingSystemPortList.add(DeIcingSystemFactory.build());
        }

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
        for (int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++) {
            leftNavigationLightPortList.add(LeftNavigationLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++) {
            landingLightWingPortList.add(LandingLightFactory.build());
        }
    }

    // --- AirFlowSensor --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(AirFlowSensorWingMeasure airFlowSensorWingMeasure) {
        System.out.println(airFlowSensorWingMeasure);
        LogEngine.instance.write("+ Wing.receive(" + airFlowSensorWingMeasure.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive" + airFlowSensorWingMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorBody; i++) {
                Method measureMethod = airFlowSensorWingPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int airPressure = (int) measureMethod.invoke(airFlowSensorWingPortList.get(i), "a".repeat(1013));
                LogEngine.instance.write("airFlow = " + airPressure);

                FlightRecorder.instance.insert("Wing", "AirFlowSensor (airPressure): " + airPressure);

                LogEngine.instance.write("+");
                PrimaryFlightDisplay.instance.airPressure = airPressure;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isAirFlowWingAlarm): " + PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAirFlowWingAlarm: " + PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm);
    }

    // --- DeIcingSystem --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DeIcingSystemActivate deIcingSystemActivate) {
        System.out.println(deIcingSystemActivate);
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemActivate.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive" + deIcingSystemActivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method activateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("activate");
                LogEngine.instance.write("activateMethod = " + activateMethod);

                boolean isActivated = (boolean) activateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("isActivated = " + isActivated);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isActivated;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (isActivated): " + isActivated);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isDeIcingSystemActive): " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isDeIcingSystemActive: " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
    }

    @Subscribe
    public void receive(DeIcingSystemDeactivate deIcingSystemDeactivate) {
        System.out.println(deIcingSystemDeactivate);
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemDeactivate.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive" + deIcingSystemDeactivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deactivateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deactivate");
                LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

                boolean isDeactivated = (boolean) deactivateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("isActivated = " + isDeactivated);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isDeactivated;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (isDeactivated): " + isDeactivated);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isDeIcingSystemDeactive): " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isDeIcingSystemDeactive: " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
    }

    @Subscribe
    public void receive(DeIcingSystemDeIce deIcingSystemDeIce) {
        System.out.println(deIcingSystemDeIce);
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemDeIce.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + deIcingSystemDeIce.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deIceMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deIce", int.class);
                LogEngine.instance.write("deIceMethod = " + deIceMethod);

                int deIceAmount = (int) deIceMethod.invoke(deIcingSystemPortList.get(i), deIcingSystemDeIce.getValue());

                LogEngine.instance.write("deIceAmount = " + deIceAmount);
                PrimaryFlightDisplay.instance.amountDeIcingSystem = deIceAmount;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (deIceAmount): " + deIceAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogEngine.instance.write("PrimaryFlightDisplay (deIceAmount): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "deIceAmount: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
    }

    @Subscribe
    public void receive(DeIcingSystemRefill deIcingSystemRefill) {
        System.out.println(deIcingSystemRefill);
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemRefill.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + deIcingSystemRefill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method refillMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("refill");
                LogEngine.instance.write("refilleMethod = " + refillMethod);

                int deIceAmount = (int) refillMethod.invoke(deIcingSystemPortList.get(i));

                LogEngine.instance.write("deIceAmount = " + deIceAmount);
                PrimaryFlightDisplay.instance.amountDeIcingSystem = deIceAmount;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (refill): " + deIceAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingRefill): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingRefill: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
    }

    //DroopNose---------------------------------
    @Subscribe
    public void receive(DroopNoseDown droopNoseDown) {
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseDown.toString() + ")");
        LogEngine.instance.write("Wing.receive(" + droopNoseDown.toString() + ")");
        System.out.println(droopNoseDown);

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method downMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("down", int.class);
                LogEngine.instance.write("downMethod = " + downMethod);

                int degree = (int) downMethod.invoke(droopNosePortList.get(i), 50);
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "droopNoseDown (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }

    @Subscribe
    public void receive(DroopNoseFullDown droopNoseFullDown) {
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseFullDown.toString() + ")");
        LogEngine.instance.write("Wing.receive(" + droopNoseFullDown.toString() + ")");
        System.out.println(droopNoseFullDown);

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method fullDownMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("fullDownMethod = " + fullDownMethod);

                int degree = (int) fullDownMethod.invoke(droopNosePortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "droopNoseFullDown (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }

    @Subscribe
    public void receive(DroopNoseNeutral droopNoseNeutral) {
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseNeutral.toString() + ")");
        LogEngine.instance.write("Wing.receive(" + droopNoseNeutral.toString() + ")");
        System.out.println(droopNoseNeutral);

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method neutralMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("neutralMethod = " + neutralMethod);

                int degree = (int) neutralMethod.invoke(droopNosePortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "droopNoseNeutral (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }

    @Subscribe
    public void receive(DroopNoseUp droopNoseUp) {
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseUp.toString() + ")");
        LogEngine.instance.write("Wing.receive(" + droopNoseUp.toString() + ")");
        System.out.println(droopNoseUp);

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method upMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("up", int.class);
                LogEngine.instance.write("upMethod = " + upMethod);

                int degree = (int) upMethod.invoke(droopNosePortList.get(i), 50);
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "droopNoseUp (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }
    //------------------------------------

    // --- TemperatureSensor --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(TemperatureSensorWingMeasure temperatureSensorWingMeasure) {
        System.out.println(temperatureSensorWingMeasure);
        LogEngine.instance.write("+ Body.receive(" + temperatureSensorWingMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + temperatureSensorWingMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorWing; i++) {
                Method measureMethod = temperatureSensorWingPortList.get(i).getClass().getDeclaredMethod("measure");
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int temperature = (int) measureMethod.invoke(temperatureSensorWingPortList.get(i));
                LogEngine.instance.write("temperature = " + temperature);

                FlightRecorder.instance.insert("Body", "TemperatureSensor (temperature): " + temperature);

                LogEngine.instance.write("+");
                PrimaryFlightDisplay.instance.temperatureWing = temperature;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (temperature): " + PrimaryFlightDisplay.instance.temperatureBody);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "temperature: " + PrimaryFlightDisplay.instance.temperatureBody);
    }

    //TurbulentAirFlowSensor-------------------------
    @Subscribe
    public void receive(TurbulentAirFlowSensorWingMeasure turbulentAirFlowSensorWingMeasure) {
        FlightRecorder.instance.insert("Wing", "receive(" + turbulentAirFlowSensorWingMeasure.toString() + ")");
        LogEngine.instance.write("Wing.receive(" + turbulentAirFlowSensorWingMeasure.toString() + ")");
        System.out.println(turbulentAirFlowSensorWingMeasure);

        try {
            for (int i = 0; i < Configuration.instance.numberOfTurbulentAirFlowSensorWing; i++) {
                Method measureMethod = turbulentAirFlowSensorPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measured = (int) measureMethod.invoke(turbulentAirFlowSensorPortList.get(i), "88");
                LogEngine.instance.write("measured = " + measured);

                FlightRecorder.instance.insert("Wing", "turbulentAirFlowSensorWingMeasure (measured): " + measured);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //------------------------------------------

    //Camera-------------------------------------------
    @Subscribe
    public void receive(CameraWingOff cameraWingOff) {
        System.out.println(cameraWingOff);
        LogEngine.instance.write("+ Wing.receive(" + cameraWingOff.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + cameraWingOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCameraWing; i++) {
                Method offMethod = cameraWingPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(cameraWingPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCameraOn = isOn;
                FlightRecorder.instance.insert("Wing", "cameraOff (isCameraOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isCameraOn): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
    }

    @Subscribe
    public void receive(CameraWingOn cameraWingOn) {
        System.out.println(cameraWingOn);
        LogEngine.instance.write("+ Wing.receive(" + cameraWingOn.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + cameraWingOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCameraWing; i++) {
                Method onMethod = cameraWingPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(cameraWingPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCameraOn = isOn;
                FlightRecorder.instance.insert("Wing", "cameraOn (isCameraOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isCameraOn): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
    }
    //--------------------------------------------------------

    // --- Engine -----------------------------------------------------------------------------------------------


    @Subscribe
    public void receive(EngineStart engineStart) {
        LogEngine.instance.write("+ Wing.receive(" + engineStart.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineStart.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method startMethod = enginePortList.get(i).getClass().getDeclaredMethod("start");
                LogEngine.instance.write("startMethod = " + startMethod);

                boolean isStarted = (boolean) startMethod.invoke(enginePortList.get(i));
                LogEngine.instance.write("IsStarted = " + isStarted);

                PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
                FlightRecorder.instance.insert("Wing", "Engine (isStarted): " + isStarted);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isEngineStarted): " + PrimaryFlightDisplay.instance.isEngineStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isEngineStarted: " + PrimaryFlightDisplay.instance.isEngineStarted);
    }

    @Subscribe
    public void receive(EngineShutdown engineShutdown) {
        LogEngine.instance.write("+ Wing.receive(" + engineShutdown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method shutdownMethod = enginePortList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("shutdownMethod = " + shutdownMethod);

                boolean isStarted = (boolean) shutdownMethod.invoke(enginePortList.get(i));
                LogEngine.instance.write("isShutdown = " + isStarted);

                PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
                FlightRecorder.instance.insert("Wing", "Engine (isStarted): " + isStarted);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isEngineStarted): " + PrimaryFlightDisplay.instance.isEngineStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isEngineShutdown: " + PrimaryFlightDisplay.instance.isEngineStarted);

    }

    @Subscribe
    public void receive(EngineIncreaseRPM engineIncreaseRPM) {
        LogEngine.instance.write("+ Wing.receive(" + engineIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method increaseRpmMethod = enginePortList.get(i).getClass().getDeclaredMethod("increaseRPM", int.class);
                LogEngine.instance.write("rpmMethod = " + increaseRpmMethod);

                int currentRPMs = (int) increaseRpmMethod.invoke(enginePortList.get(i), engineIncreaseRPM.getValue());

                LogEngine.instance.write("currentRPMs = " + currentRPMs);

                PrimaryFlightDisplay.instance.rpmEngine = (currentRPMs);
                FlightRecorder.instance.insert("Wing", "Engine (currentRPMs): " + currentRPMs);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmEngine): " + PrimaryFlightDisplay.instance.rpmEngine);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmEngine: " + PrimaryFlightDisplay.instance.rpmEngine);
    }

    @Subscribe
    public void receive(EngineDecreaseRPM engineDecreaseRPM) {
        LogEngine.instance.write("+ Wing.receive(" + engineDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method decreaseRPMMethod = enginePortList.get(i).getClass().getDeclaredMethod("decreaseRPM", int.class);
                LogEngine.instance.write("decreaseRPMMethod = " + decreaseRPMMethod);

                int currentRPMs = (int) decreaseRPMMethod.invoke(enginePortList.get(i), engineDecreaseRPM.getValue());
                LogEngine.instance.write("currentRPMs = " + currentRPMs);

                PrimaryFlightDisplay.instance.rpmEngine = (currentRPMs);
                FlightRecorder.instance.insert("Wing", "Engine (currentRPMs): " + currentRPMs);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmEngine): " + PrimaryFlightDisplay.instance.rpmEngine);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmEngine: " + PrimaryFlightDisplay.instance.rpmEngine);
    }

    // --- Hydraulic Pump -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(HydraulicPumpWingCompress hydraulicPumpWingCompress) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingCompress.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method hydraulicPumpWingCompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("compress", int.class);
                LogEngine.instance.write("hydraulicPumpBodyCompressMethod = " + hydraulicPumpWingCompressMethod);

                int compressionAmount = (int) hydraulicPumpWingCompressMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpWingCompress.getValue());
                LogEngine.instance.write("hydraulicPumpWingCompressMethod = " + compressionAmount);

                FlightRecorder.instance.insert("Wing", "hydraulicPumpWingCompress (compressionAmount): " + compressionAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Subscribe
    public void receive(HydraulicPumpWingDecompress hydraulicPumpWingDecompress) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingDecompress.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingDecompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method hydraulicPumpWingDecompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("decompress");
                LogEngine.instance.write("hydraulicPumpWingDecompressMethod = " + hydraulicPumpWingDecompressMethod);

                int compressionAmount = (int) hydraulicPumpWingDecompressMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("hydraulicPumpWingDecompressMethod = " + compressionAmount);

                FlightRecorder.instance.insert("Wing", "hydraulicPumpWingDecompressMethod (compressionAmount): " + compressionAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Subscribe
    public void receive(HydraulicPumpWingRefillOil hydraulicPumpWingRefillOil) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method hydraulicPumpWingRefillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("refillOil", int.class);
                LogEngine.instance.write("hydraulicPumpWingRefillOilMethod = " + hydraulicPumpWingRefillOilMethod);

                int oilAmount = (int) hydraulicPumpWingRefillOilMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpWingRefillOil.getValue());
                LogEngine.instance.write("hydraulicPumpWingRefillOilMethod = " + oilAmount);

                PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount = oilAmount;
                FlightRecorder.instance.insert("Wing", "hydraulicPumpWingRefillOil (compressionAmount): " + oilAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(ElevatorDown elevatorDown) {
        LogEngine.instance.write("+ Wing.receive(" + elevatorDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + elevatorDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorDownMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("down", int.class);
                LogEngine.instance.write("elevatorDownMethod = " + elevatorDownMethod);

                int degrees = (int) elevatorDownMethod.invoke(elevatorPortList.get(i), elevatorDown.getValue());
                LogEngine.instance.write("elevatorDownMethod = " + degrees);

                PrimaryFlightDisplay.instance.degreeElevator = degrees;
                FlightRecorder.instance.insert("Wing", "elevatorDown (degrees): " + degrees);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degrees): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degrees: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorUp elevatorUp) {
        LogEngine.instance.write("+ Wing.receive(" + elevatorUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + elevatorUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorUpMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("up", int.class);
                LogEngine.instance.write("elevatorUpMethod = " + elevatorUpMethod);

                int degrees = (int) elevatorUpMethod.invoke(elevatorPortList.get(i), elevatorUp.getValue());
                LogEngine.instance.write("elevatorUpMethod = " + degrees);

                PrimaryFlightDisplay.instance.degreeElevator = degrees;
                FlightRecorder.instance.insert("Wing", "elevatorUp (degrees): " + degrees);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degrees): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degrees: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorFullUp elevatorFullUp) {
        LogEngine.instance.write("+ Wing.receive(" + elevatorFullUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + elevatorFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorFullUpMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("elevatorFullUpMethod = " + elevatorFullUpMethod);

                int degrees = (int) elevatorFullUpMethod.invoke(elevatorPortList.get(i));
                LogEngine.instance.write("elevatorFullUpMethod = " + degrees);

                PrimaryFlightDisplay.instance.degreeElevator = degrees;
                FlightRecorder.instance.insert("Wing", "elevatorFullUp (degrees): " + degrees);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degrees): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degrees: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorFullDown elevatorFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + elevatorFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + elevatorFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorFullDownMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("elevatorFullDownMethod = " + elevatorFullDownMethod);

                int degrees = (int) elevatorFullDownMethod.invoke(elevatorPortList.get(i));
                LogEngine.instance.write("elevatorFullDownMethod = " + degrees);

                PrimaryFlightDisplay.instance.degreeElevator = degrees;
                FlightRecorder.instance.insert("Wing", "elevatorFullDownMethod (degrees): " + degrees);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degrees): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degrees: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorNeutral elevatorNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + elevatorNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + elevatorNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorNeutralMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("elevatorNeutralMethod = " + elevatorNeutralMethod);

                int degrees = (int) elevatorNeutralMethod.invoke(elevatorPortList.get(i));
                LogEngine.instance.write("elevatorNeutralMethod = " + degrees);

                PrimaryFlightDisplay.instance.degreeElevator = degrees;
                FlightRecorder.instance.insert("Wing", "elevatorNeutralMethod (degrees): " + degrees);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degrees): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degrees: " + PrimaryFlightDisplay.instance.degreeElevator);
    }
    // --- FireDetector -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(FireDetectorWingScan fireDetectorWingScan) {
        LogEngine.instance.write("+ Wing.receive(" + fireDetectorWingScan.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + fireDetectorWingScan.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFireDetectorWing; i++) {
                Method scanMethod = fireDetectorPortList.get(i).getClass().getDeclaredMethod("scan");
                LogEngine.instance.write("scanMethod = " + scanMethod);

                boolean wingIsScanned = (boolean) scanMethod.invoke(fireDetectorPortList.get(i));
                LogEngine.instance.write("wingIsScanned = " + wingIsScanned);

                PrimaryFlightDisplay.instance.isFireDetectedWing = wingIsScanned;
                FlightRecorder.instance.insert("Wing", "FireDetector (wingIsScanned): " + wingIsScanned);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isFireDetectedWing): " + PrimaryFlightDisplay.instance.isFireDetectedWing);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isFireDetectedWing: " + PrimaryFlightDisplay.instance.isFireDetectedWing);
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
                LogEngine.instance.write("downMethod = " + downMethod);

                int degree = (int) downMethod.invoke(slatPortList.get(i), slatDown.getValue());
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