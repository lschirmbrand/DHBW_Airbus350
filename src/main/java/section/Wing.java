package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.airflow_sensor.AirFlowSensorBodyMeasure;
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
import event.temperature_sensor.TemperatureSensorBodyMeasure;
import event.temperature_sensor.TemperatureSensorWingMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
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

                int airPressure = (int) measureMethod.invoke(airFlowSensorWingPortList.get(i), "FLOWFLOWFLOW");
                LogEngine.instance.write("airFlow = " + airPressure);

                FlightRecorder.instance.insert("Wing", "AirFlowSensor (airPressure): " + airPressure);

                LogEngine.instance.write("+");
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

    // ----------------------------------------------------------------------------------------------------------------

}