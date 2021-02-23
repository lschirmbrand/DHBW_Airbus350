package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.air_conditioning.*;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.gear.*;
import event.gps.*;
import event.hydraulicPump.HydraulicPumpBodyCompress;
import event.hydraulicPump.HydraulicPumpBodyRefillOil;
import event.hydraulicPump.HydraulicPumpWingDecompress;
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.radar.RadarOff;
import event.radar.RadarOn;
import event.radar.RadarScan;
import event.tcas.*;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressWarnings("UnstableApiUsage")
public class Body extends Subscriber {
    private final ArrayList<Object> airConditioningPortList;
    private final ArrayList<Object> apuPortList;
    private final ArrayList<Object> gearPortList;
    private final ArrayList<Object> weatherRadarPortList;
    private final ArrayList<Object> hydraulicPumpPortList;
    private final ArrayList<Object> elevatorPortList;
    private final ArrayList<Object> radarPortList = new ArrayList<>();

    public Body() {
        airConditioningPortList = new ArrayList<>();
        apuPortList = new ArrayList<>();
        gearPortList = new ArrayList<>();
        weatherRadarPortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        elevatorPortList = new ArrayList<>();

        build();
    }

    public void build() {

        for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
            airConditioningPortList.add(AirConditioningFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
            apuPortList.add(APUFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfGearFront; i++) {
            Object gearPort = GearFactory.build();
            try {
                gearPort.getClass().getDeclaredMethod("setGearType", String.class).invoke(gearPort, "front");
            } catch (Exception e) {
                e.printStackTrace();
            }
            gearPortList.add(gearPort);
        }
        for (int i = 0; i < Configuration.instance.numberOfGearRear; i++) {
            Object gear = GearFactory.build();
            try {
                gear.getClass().getDeclaredMethod("setGearType", String.class).invoke(gear, "rear");
            } catch (Exception e) {
                e.printStackTrace();
            }
            gearPortList.add(gear);
        }


        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
            hydraulicPumpPortList.add(HydraulicPumpFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
            elevatorPortList.add(EngineFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRadar; i++) {
            radarPortList.add(RadarFactory.build());
        }
    }


    // --- AirConditioning --------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AirConditioningOn airConditioningOn) {
        LogEngine.instance.write("+ Body.receive(" + airConditioningOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + airConditioningOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
                Method onMethod = airConditioningPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(airConditioningPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAirConditioningOn = isOn;
                FlightRecorder.instance.insert("Body", "AirConditioning (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAirConditioningOn): " + PrimaryFlightDisplay.instance.isAirConditioningOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAirConditioningOn: " + PrimaryFlightDisplay.instance.isAirConditioningOn);
    }

    @Subscribe
    public void receive(AirConditioningOff airConditioningOff) {
        LogEngine.instance.write("+ Body.receive(" + airConditioningOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + airConditioningOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
                Method offMethod = airConditioningPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(airConditioningPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAirConditioningOn = isOn;
                FlightRecorder.instance.insert("Body", "AirConditioning (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAirConditioningOn): " + PrimaryFlightDisplay.instance.isAirConditioningOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAirConditioningOn: " + PrimaryFlightDisplay.instance.isAirConditioningOn);
    }

    @Subscribe
    public void receive(AirConditioningClean airConditioningClean) {
        LogEngine.instance.write("+ Body.receive(" + airConditioningClean.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + airConditioningClean.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
                Method cleanMethod = airConditioningPortList.get(i).getClass().getDeclaredMethod("clean", String.class);
                LogEngine.instance.write("cleanMethod = " + cleanMethod);

                String cleanResult = (String) cleanMethod.invoke(airConditioningPortList.get(i), airConditioningClean.getAirFlow());
                LogEngine.instance.write("cleanResult = " + cleanResult);

                FlightRecorder.instance.insert("Body", "AirConditioning (cleanResult): " + cleanResult);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(AirConditioningHeat airConditioningHeat) {
        LogEngine.instance.write("+ Body.receive(" + airConditioningHeat.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + airConditioningHeat.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
                Method heatMethod = airConditioningPortList.get(i).getClass().getDeclaredMethod("heat", String.class, int.class);
                LogEngine.instance.write("heatMethod = " + heatMethod);

                int temperature = (int) heatMethod.invoke(airConditioningPortList.get(i), airConditioningHeat.getAirFlow(), airConditioningHeat.getTemperature());
                LogEngine.instance.write("temperature = " + temperature);

                PrimaryFlightDisplay.instance.temperatureAirConditioning = temperature;
                FlightRecorder.instance.insert("Body", "AirConditioning (temperature): " + temperature);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (temperatureAirConditioning): " + PrimaryFlightDisplay.instance.temperatureAirConditioning);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "temperatureAirConditioning: " + PrimaryFlightDisplay.instance.temperatureAirConditioning);
    }

    @Subscribe
    public void receive(AirConditioningCool airConditioningCool) {
        LogEngine.instance.write("+ Body.receive(" + airConditioningCool.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + airConditioningCool.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
                Method coolMethod = airConditioningPortList.get(i).getClass().getDeclaredMethod("cool", String.class, int.class);
                LogEngine.instance.write("coolMethod = " + coolMethod);

                int temperature = (int) coolMethod.invoke(airConditioningPortList.get(i), airConditioningCool.getAirFlow(), airConditioningCool.getTemperature());
                LogEngine.instance.write("temperature = " + temperature);

                PrimaryFlightDisplay.instance.temperatureAirConditioning = temperature;
                FlightRecorder.instance.insert("Body", "AirConditioning (temperature): " + temperature);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (temperatureAirConditioning): " + PrimaryFlightDisplay.instance.temperatureAirConditioning);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "temperatureAirConditioning: " + PrimaryFlightDisplay.instance.temperatureAirConditioning);
    }

    // --- APU --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(APUStart apuStart) {
        LogEngine.instance.write("+ Body.receive(" + apuStart.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + apuStart.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
                Method startMethod = apuPortList.get(i).getClass().getDeclaredMethod("start");
                LogEngine.instance.write("startMethod = " + startMethod);

                boolean isStarted = (boolean) startMethod.invoke(apuPortList.get(i));
                LogEngine.instance.write("isStarted = " + isStarted);

                PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
                FlightRecorder.instance.insert("Body", "APU (isStarted): " + isStarted);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);
    }

    @Subscribe
    public void receive(APUShutdown apuShutdown) {
        LogEngine.instance.write("+ Body.receive(" + apuShutdown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + apuShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
                Method shutdownMethod = apuPortList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("shutdownMethod = " + shutdownMethod);

                shutdownMethod.invoke(apuPortList.get(i));

                LogEngine.instance.write("isStarted = " + false);
                PrimaryFlightDisplay.instance.isAPUStarted = false;
                FlightRecorder.instance.insert("Body", "APU (isStarted): " + false);

                LogEngine.instance.write("rpm = " + 0);
                PrimaryFlightDisplay.instance.rpmAPU = 0;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + 0);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    @Subscribe
    public void receive(APUIncreaseRPM apuIncreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + apuIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + apuIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
                Method increaseRPMMethod = apuPortList.get(i).getClass().getDeclaredMethod("increaseRPM", int.class);
                LogEngine.instance.write("increaseRPMMethod = " + increaseRPMMethod);

                int rpm = (int) increaseRPMMethod.invoke(apuPortList.get(i), apuIncreaseRPM.getValue());

                LogEngine.instance.write("rpm = " + rpm);
                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + 0);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    @Subscribe
    public void receive(APUDecreaseRPM apuDecreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + apuDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + apuDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
                Method decreaseRPMMethod = apuPortList.get(i).getClass().getDeclaredMethod("decreaseRPM", int.class);
                LogEngine.instance.write("decreaseRPMMethod = " + decreaseRPMMethod);

                int rpm = (int) decreaseRPMMethod.invoke(apuPortList.get(i), apuDecreaseRPM.getValue());

                LogEngine.instance.write("rpm = " + rpm);
                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + 0);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    // --- Gear -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(GearUp gearUp) {
        LogEngine.instance.write("+ Body.receive(" + gearUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGearRear + Configuration.instance.numberOfGearFront; i++) {
                Method upMethod = gearPortList.get(i).getClass().getDeclaredMethod("up");
                LogEngine.instance.write("upMethod = " + upMethod);

                boolean isGearDown = (boolean) upMethod.invoke(gearPortList.get(i));

                LogEngine.instance.write("isGearDown = " + isGearDown);
                PrimaryFlightDisplay.instance.isGearDown = isGearDown;
                FlightRecorder.instance.insert("Body", "Gear (isGearDown): " + isGearDown);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }

    @Subscribe
    public void receive(GearDown gearDown) {
        LogEngine.instance.write("+ Body.receive(" + gearDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGearRear + Configuration.instance.numberOfGearFront; i++) {
                Method downMethod = gearPortList.get(i).getClass().getDeclaredMethod("down");
                LogEngine.instance.write("downMethod = " + downMethod);

                boolean isGearDown = (boolean) downMethod.invoke(gearPortList.get(i));

                LogEngine.instance.write("isGearDown = " + isGearDown);
                PrimaryFlightDisplay.instance.isGearDown = isGearDown;
                FlightRecorder.instance.insert("Body", "Gear (isGearDown): " + isGearDown);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }

    @Subscribe
    public void receive(GearSetBrake gearSetBrake) {
        LogEngine.instance.write("+ Body.receive(" + gearSetBrake.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearSetBrake.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGearRear + Configuration.instance.numberOfGearFront; i++) {
                Method setBrakeMethod = gearPortList.get(i).getClass().getDeclaredMethod("setBrake");
                LogEngine.instance.write("setBrakeMethod = " + setBrakeMethod);

                int gearBrakePercentage = (int) setBrakeMethod.invoke(gearPortList.get(i));

                LogEngine.instance.write("gearBrakePercentage = " + gearBrakePercentage);
                PrimaryFlightDisplay.instance.gearBrakePercentage = gearBrakePercentage;
                FlightRecorder.instance.insert("Body", "Gear (gearBrakePercentage): " + gearBrakePercentage);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }

    @Subscribe
    public void receive(GearSetBrakePercentage gearSetBrakePercentage) {
        LogEngine.instance.write("+ Body.receive(" + gearSetBrakePercentage.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearSetBrakePercentage.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGearRear + Configuration.instance.numberOfGearFront; i++) {
                Method setBrakePercentageMethod = gearPortList.get(i).getClass().getDeclaredMethod("setBrake", int.class);
                LogEngine.instance.write("setBrakePercentageMethod = " + setBrakePercentageMethod);

                int gearBrakePercentage = (int) setBrakePercentageMethod.invoke(gearPortList.get(i), gearSetBrakePercentage.getValue());

                LogEngine.instance.write("gearBrakePercentage = " + gearBrakePercentage);
                PrimaryFlightDisplay.instance.gearBrakePercentage = gearBrakePercentage;
                FlightRecorder.instance.insert("Body", "Gear (gearBrakePercentage): " + gearBrakePercentage);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }

    @Subscribe
    public void receive(GearReleaseBrake gearReleaseBrake) {
        LogEngine.instance.write("+ Body.receive(" + gearReleaseBrake.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearReleaseBrake.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGearRear + Configuration.instance.numberOfGearFront; i++) {
                Method releaseBrakeMethod = gearPortList.get(i).getClass().getDeclaredMethod("releaseBrake");
                LogEngine.instance.write("releaseBrakeMethod = " + releaseBrakeMethod);

                int gearBrakePercentage = (int) releaseBrakeMethod.invoke(gearPortList.get(i));

                LogEngine.instance.write("gearBrakePercentage = " + gearBrakePercentage);
                PrimaryFlightDisplay.instance.gearBrakePercentage = gearBrakePercentage;
                FlightRecorder.instance.insert("Body", "Gear (gearBrakePercentage): " + gearBrakePercentage);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
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

    // ----------------------------------------------------------------------------------------------------------------

    //TCAS---------------------------------------------------------
    @Subscribe
    public void receive(TCASOn tcason) {
        System.out.println(tcason);
    }

    @Subscribe
    public void receive(TCASOff tcasOff) {
        System.out.println(tcasOff);
    }

    @Subscribe
    public void receive(TCASConnect tcasConnect) {
        System.out.println(tcasConnect);
    }

    @Subscribe
    public void receive(TCASScan tcasScan) {
        System.out.println(tcasScan);
    }

    @Subscribe
    public void receive(TCASDetermineAltitude tcasDetermineAltitude) {
        System.out.println(tcasDetermineAltitude);
    }
    //-----------------------------------------------------

    //TurbulentAirFlowSensor-------------------------
    @Subscribe
    public void receive(TurbulentAirFlowSensorBodyMeasure turbulentAirFlowSensorBodyMeasure) {
        System.out.println(turbulentAirFlowSensorBodyMeasure);
    }
    //------------------------------------------

    //Camera-------------------------------------------
    @Subscribe
    public void receive(CameraBodyOff cameraBodyOff) {
        System.out.println(cameraBodyOff);
    }

    @Subscribe
    public void receive(CameraBodyOn cameraBodyOn) {
        System.out.println(cameraBodyOn);
    }
    //--------------------------------------------------------
    //NitrogenBottle-------------------------------------------
    @Subscribe
    public void receive(NitrogenBottleRefill nitrogenBottleRefill) {
        System.out.println(nitrogenBottleRefill);
    }

    @Subscribe
    public void receive(NitrogenBottleTakeOut nitrogenBottleTakeOut) {
        System.out.println(nitrogenBottleTakeOut);
    }
    //--------------------------------------------------------
    //OxygenBottle-------------------------------------------
    @Subscribe
    public void receive(OxygenBottleRefill oxygenBottleRefill) {
        System.out.println(oxygenBottleRefill);
    }

    @Subscribe
    public void receive(OxygenBottleTakeOut oxygenBottleTakeOut) {
        System.out.println(oxygenBottleTakeOut);
    }

    //GPS-------------------------------------------------------
    @Subscribe
    public void receive(GPSOn gpsOn) {
        System.out.println(gpsOn);
    }

    @Subscribe
    public void receive(GPSOff gpsOff) {
        System.out.println(gpsOff);
    }

    @Subscribe
    public void receive(GPSReceive gpsReceive) {
        System.out.println(gpsReceive);
    }

    @Subscribe
    public void receive(GPSConnect gpsConnect) {
        System.out.println(gpsConnect);
    }

    @Subscribe
    public void receive(GPSSend gpsSend) {
        System.out.println(gpsSend);
    }
    //----------------------------------------------------------

    //Radar------------------------------------------------------

    @Subscribe
    public void receive(RadarOn radarOn) {
        System.out.println(radarOn);
    }

    @Subscribe
    public void receive(RadarOff radarOff) {
        LogEngine.instance.write("+ Body.receive(" + radarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadar; i++) {
                Method offMethod = radarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(radarPortList.get(i));
                LogEngine.instance.write("offMethod = " + isOn);

                FlightRecorder.instance.insert("Body", "radarOff (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(RadarScan radarScan) {
        LogEngine.instance.write("+ Body.receive(" + radarScan.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarScan.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadar; i++) {
                Method radarScanMethod = radarPortList.get(i).getClass().getDeclaredMethod("scan", String.class);
                LogEngine.instance.write("radarScanMethod = " + radarScanMethod);

                boolean environment = (boolean) radarScanMethod.invoke(radarPortList.get(i), radarScan.getValue());
                LogEngine.instance.write("radarScanMethod = " + environment);

                FlightRecorder.instance.insert("Body", "radarScan (environment): " + environment);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //------------------------------------------------------------
    // --- HydraulicPump -------------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(HydraulicPumpBodyCompress hydraulicPumpBodyCompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyCompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method hydraulicPumpBodyCompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("compress", int.class);
                LogEngine.instance.write("hydraulicPumpBodyCompressMethod = " + hydraulicPumpBodyCompressMethod);

                int compressionAmount = (int) hydraulicPumpBodyCompressMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpBodyCompress.getValue());
                LogEngine.instance.write("hydraulicPumpBodyCompressMethod = " + compressionAmount);

                FlightRecorder.instance.insert("Body", "hydraulicPumpBodyCompress (compressionAmount): " + compressionAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Subscribe
    public void receive(HydraulicPumpWingDecompress hydraulicPumpWingDecompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpWingDecompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpWingDecompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method hydraulicPumpBodyDecompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("decompress");
                LogEngine.instance.write("hydraulicPumpBodyDecompressMethod = " + hydraulicPumpBodyDecompressMethod);

                int compressionAmount = (int) hydraulicPumpBodyDecompressMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("hydraulicPumpBodyDecompressMethod = " + compressionAmount);

                FlightRecorder.instance.insert("Body", "hydraulicPumpBodyDecompress (compressionAmount): " + compressionAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(HydraulicPumpBodyRefillOil hydraulicPumpBodyRefillOil) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method hydraulicPumpBodyRefillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("refillOil", int.class);
                LogEngine.instance.write("hydraulicPumpBodyRefillOilMethod = " + hydraulicPumpBodyRefillOilMethod);

                int oilAmount = (int) hydraulicPumpBodyRefillOilMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpBodyRefillOil.getValue());
                LogEngine.instance.write("hydraulicPumpBodyDecompressMethod = " + oilAmount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = oilAmount;
                FlightRecorder.instance.insert("Body", "hydraulicPumpBodyDecompress (compressionAmount): " + oilAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}