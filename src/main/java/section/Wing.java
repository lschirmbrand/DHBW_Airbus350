package section;

import event.Subscriber;
import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.engine.*;
import event.hydraulicPump.*;
import factory.EngineFactory;
import factory.HydraulicPumpFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> enginePortList;
    private ArrayList<Object> hydraulicPumpPortList;

    public Wing() {
        enginePortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
            enginePortList.add(EngineFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
            hydraulicPumpPortList.add(HydraulicPumpFactory.build());
        }
    }


    // --- Engine -----------------------------------------------------------------------------------------------


    @Subscribe
    public void receive(EngineStart engineStart){
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
    public void receive(EngineShutdown engineShutdown){
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
    public void receive(EngineIncreaseRPM engineIncreaseRPM){
        LogEngine.instance.write("+ Wing.receive(" + engineIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method increaseRpmMethod = enginePortList.get(i).getClass().getDeclaredMethod("innerIncreaseRPM", int.class);
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
    public void receive(EngineDecreaseRPM engineDecreaseRPM){
        LogEngine.instance.write("+ Wing.receive(" + engineDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method decreaseRPMMethod = enginePortList.get(i).getClass().getDeclaredMethod("innerDecreaseRPM", int.class);
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
                Method hydraulicPumpWingCompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("innerCompress", int.class);
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
                Method hydraulicPumpWingDecompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("innerDecompress", int.class);
                LogEngine.instance.write("hydraulicPumpWingDecompressMethod = " + hydraulicPumpWingDecompressMethod);

                int compressionAmount = (int) hydraulicPumpWingDecompressMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpWingDecompress.getValue());
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
                Method hydraulicPumpWingRefillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("innerRefillOil", int.class);
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
}