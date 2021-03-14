package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.air_conditioning.*;
import event.airflow_sensor.AirFlowSensorBodyMeasure;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.battery.BatteryCharge;
import event.battery.BatteryDischarge;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.cargo_compartment_light.CargoCompartmentLightDim;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.*;
import event.crew_seat.SeatAssignCrewMember;
import event.deicing_system.DeIcingSystemActivate;
import event.deicing_system.DeIcingSystemDeIce;
import event.deicing_system.DeIcingSystemDeactivate;
import event.deicing_system.DeIcingSystemRefill;
import event.economy_class_seat.*;
import event.fire_detector.FireDetectorBodyScan;
import event.gear.*;
import event.gps.*;
import event.hydraulicPump.HydraulicPumpBodyCompress;
import event.hydraulicPump.HydraulicPumpBodyRefillOil;
import event.hydraulicPump.HydraulicPumpWingDecompress;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.logo_light.LogoLightOff;
import event.logo_light.LogoLightOn;
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.oxygen_sensor.OxygenSensorMeasure;
import event.radar.RadarOff;
import event.radar.RadarOn;
import event.radar.RadarScan;
import event.route_management.*;
import event.rudder.*;
import event.tcas.*;
import event.temperature_sensor.TemperatureSensorBodyMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressWarnings({"UnstableApiUsage", "unused", "MismatchedQueryAndUpdateOfCollection", "DuplicatedCode"})
public class Body extends Subscriber {
    private final ArrayList<Object> airConditioningPortList;
    private final ArrayList<Object> airFlowSensorBodyPortList;
    private final ArrayList<Object> apuPortList;
    private final ArrayList<Object> batteryPortList;
    private final ArrayList<Object> deIcingSystemPortList;
    private final ArrayList<Object> gearPortList;
    private final ArrayList<Object> weatherRadarPortList;
    private final ArrayList<Object> hydraulicPumpPortList;
    private final ArrayList<Object> elevatorPortList;
    private final ArrayList<Object> radarPortList;
    private final ArrayList<Object> gpsPortList;
    private final ArrayList<Object> cameraBodyPortList;
    private final ArrayList<Object> tcasPortList;
    private final ArrayList<Object> temperatureSensorPortList;
    private final ArrayList<Object> turbulentAirFlowSensorBodyPortList;
    private final ArrayList<Object> droopNosePortList;
    private final ArrayList<Object> crewSeatPortList;
    private final ArrayList<Object> economyClassSeatPortList;
    private final ArrayList<Object> fireDetectorPortList;
    private final ArrayList<Object> oxygenSensorPortList;
    private final ArrayList<Object> rudderPortList;
    private final ArrayList<Object> antiCollisionLightPortList;
    private final ArrayList<Object> cargoCompartmentLightPortList;
    private final ArrayList<Object> costOptimizerPortList;
    private final ArrayList<Object> landingLightBodyPortList;
    private final ArrayList<Object> logoLightPortList;
    private final ArrayList<Object> routeManagementPortList;

    public Body() {
        airConditioningPortList = new ArrayList<>();
        airFlowSensorBodyPortList = new ArrayList<>();
        apuPortList = new ArrayList<>();
        batteryPortList = new ArrayList<>();
        deIcingSystemPortList = new ArrayList<>();
        gearPortList = new ArrayList<>();
        weatherRadarPortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        elevatorPortList = new ArrayList<>();
        droopNosePortList = new ArrayList<>();
        turbulentAirFlowSensorBodyPortList = new ArrayList<>();
        tcasPortList = new ArrayList<>();
        temperatureSensorPortList = new ArrayList<>();
        cameraBodyPortList = new ArrayList<>();
        gpsPortList = new ArrayList<>();
        radarPortList = new ArrayList<>();
        crewSeatPortList = new ArrayList<>();
        economyClassSeatPortList = new ArrayList<>();
        fireDetectorPortList = new ArrayList<>();
        oxygenSensorPortList = new ArrayList<>();
        rudderPortList = new ArrayList<>();
        antiCollisionLightPortList = new ArrayList<>();
        cargoCompartmentLightPortList = new ArrayList<>();
        costOptimizerPortList = new ArrayList<>();
        landingLightBodyPortList = new ArrayList<>();
        logoLightPortList = new ArrayList<>();
        routeManagementPortList = new ArrayList<>();

        build();
    }

    public void build() {

        for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
            airConditioningPortList.add(AirConditioningFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorBody; i++) {
            airFlowSensorBodyPortList.add(AirFlowSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
            apuPortList.add(APUFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfBattery; i++) {
            batteryPortList.add(BatteryFactory.build());
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
        for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
            tcasPortList.add(TCASFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfCameraBody; i++) {
            cameraBodyPortList.add(CameraFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfGPS; i++) {
            gpsPortList.add(GPSFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody; i++) {
            turbulentAirFlowSensorBodyPortList.add(TurbulentAirFlowSensorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
            droopNosePortList.add(DroopNoseFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
            crewSeatPortList.add(CrewSeatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
            economyClassSeatPortList.add(EconomyClassSeatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfFireDetectorBody; i++) {
            fireDetectorPortList.add(FireDetectorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfOxygenSensor; i++) {
            oxygenSensorPortList.add(OxygenSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
            deIcingSystemPortList.add(DeIcingSystemFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorBody; i++) {
            temperatureSensorPortList.add(TemperatureSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
            rudderPortList.add(RudderFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
            antiCollisionLightPortList.add(AntiCollisionLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfCargoCompartmentLight; i++) {
            cargoCompartmentLightPortList.add(CargoCompartmentLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
            costOptimizerPortList.add(CostOptimizerFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
            landingLightBodyPortList.add(LandingLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
            logoLightPortList.add(LogoLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
            routeManagementPortList.add(RouteManagementFactory.build());
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

    // --- AirFlowSensor --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(AirFlowSensorBodyMeasure airFlowSensorBodyMeasure) {
        System.out.println(airFlowSensorBodyMeasure);
        LogEngine.instance.write("+ Body.receive(" + airFlowSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + airFlowSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorBody; i++) {
                Method measureMethod = airFlowSensorBodyPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int airPressure = (int) measureMethod.invoke(airFlowSensorBodyPortList.get(i), "FLOWFLOWFLOW");
                LogEngine.instance.write("airFlow = " + airPressure);

                FlightRecorder.instance.insert("Body", "AirFlowSensor (airPressure): " + airPressure);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isAirFlowBodyAlarm): " + PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAirFlowBodyAlarm: " + PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm);
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

    // --- Battery --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(BatteryCharge batteryCharge) {
        System.out.println(batteryCharge);
        LogEngine.instance.write("+ Body.receive(" + batteryCharge.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + batteryCharge.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBattery; i++) {
                Method chargeMethod = batteryPortList.get(i).getClass().getDeclaredMethod("charge");
                LogEngine.instance.write("chargeMethod = " + chargeMethod);

                int stateOfCharge = (int) chargeMethod.invoke(batteryPortList.get(i));
                LogEngine.instance.write("stateOfCharge = " + stateOfCharge);

                FlightRecorder.instance.insert("Body", "Battery (charge): " + stateOfCharge);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (charge): " + PrimaryFlightDisplay.instance.percentageBattery);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "charge: " + PrimaryFlightDisplay.instance.percentageBattery);
    }

    @Subscribe
    public void receive(BatteryDischarge batteryDischarge) {
        System.out.println(batteryDischarge);
        LogEngine.instance.write("+ Body.receive(" + batteryDischarge.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + batteryDischarge.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBattery; i++) {
                Method dischargeMethod = batteryPortList.get(i).getClass().getDeclaredMethod("discharge");
                LogEngine.instance.write("dischargeMethod = " + dischargeMethod);

                int stateOfCharge = (int) dischargeMethod.invoke(batteryPortList.get(i));
                LogEngine.instance.write("stateOfCharge = " + stateOfCharge);

                FlightRecorder.instance.insert("Body", "Battery (discharge): " + stateOfCharge);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (discharge): " + PrimaryFlightDisplay.instance.percentageBattery);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "discharge: " + PrimaryFlightDisplay.instance.percentageBattery);
    }

    // --- DeIcingSystem --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DeIcingSystemActivate deIcingSystemActivate) {
        System.out.println(deIcingSystemActivate);
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemActivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + deIcingSystemActivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method activateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("activate");
                LogEngine.instance.write("activateMethod = " + activateMethod);

                boolean isActivated = (boolean) activateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("isActivated = " + isActivated);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isActivated;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (isActivated): " + isActivated);

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
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemDeactivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + deIcingSystemDeactivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deactivateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deactivate");
                LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

                boolean isDeactivated = (boolean) deactivateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("isActivated = " + isDeactivated);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isDeactivated;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (isDeactivated): " + isDeactivated);

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
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemDeIce.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemDeIce.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deIceMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deIce", int.class);
                LogEngine.instance.write("deIceMethod = " + deIceMethod);

                int deIceAmount = (int) deIceMethod.invoke(deIcingSystemPortList.get(i), deIcingSystemDeIce.getValue());

                LogEngine.instance.write("deIceAmount = " + deIceAmount);
                PrimaryFlightDisplay.instance.amountDeIcingSystem = deIceAmount;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (deIceAmount): " + deIceAmount);

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
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemRefill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemRefill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method refillMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("refill");
                LogEngine.instance.write("refilleMethod = " + refillMethod);

                int deIceAmount = (int) refillMethod.invoke(deIcingSystemPortList.get(i));

                LogEngine.instance.write("deIceAmount = " + deIceAmount);
                PrimaryFlightDisplay.instance.amountDeIcingSystem = deIceAmount;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (refill): " + deIceAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingRefill): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingRefill: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
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
    public void receive(TCASOn tcasOn) {
        System.out.println(tcasOn);
        LogEngine.instance.write("+ Body.receive(" + tcasOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method onMethod = tcasPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTCASOn = isOn;
                FlightRecorder.instance.insert("Body", "tcasOn (isTCASOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isTCASOn): " + PrimaryFlightDisplay.instance.isTCASOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASOn: " + PrimaryFlightDisplay.instance.isTCASOn);
    }

    @Subscribe
    public void receive(TCASOff tcasOff) {
        System.out.println(tcasOff);
        LogEngine.instance.write("+ Body.receive(" + tcasOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method offMethod = tcasPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTCASOn = isOn;
                FlightRecorder.instance.insert("Body", "tcasOff (isTCASOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isTCASOn): " + PrimaryFlightDisplay.instance.isTCASOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASOn: " + PrimaryFlightDisplay.instance.isTCASOn);
    }

    @Subscribe
    public void receive(TCASConnect tcasConnect) {
        System.out.println(tcasConnect);
        LogEngine.instance.write("+ Body.receive(" + tcasConnect.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasConnect.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method connectMethod = tcasPortList.get(i).getClass().getDeclaredMethod("connect", String.class);
                LogEngine.instance.write("connectMethod = " + connectMethod);

                boolean isConnected = (boolean) connectMethod.invoke(tcasPortList.get(i), "Astra-8");
                LogEngine.instance.write("isConnected = " + isConnected);

                PrimaryFlightDisplay.instance.isTCASConnected = isConnected;
                FlightRecorder.instance.insert("Body", "tcasConnect (isTCASConnected): " + isConnected);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isTCASConnected): " + PrimaryFlightDisplay.instance.isTCASConnected);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASConnected: " + PrimaryFlightDisplay.instance.isTCASConnected);
    }

    @Subscribe
    public void receive(TCASScan tcasScan) {
        FlightRecorder.instance.insert("Body", "receive(" + tcasScan.toString() + ")");
        LogEngine.instance.write("Body.receive(" + tcasScan.toString() + ")");
        System.out.println(tcasScan);
        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method scanMethod = tcasPortList.get(i).getClass().getDeclaredMethod("scan", String.class);
                LogEngine.instance.write("scanMethod = " + scanMethod);

                boolean scanned = (boolean) scanMethod.invoke(tcasPortList.get(i), "Cloud");
                LogEngine.instance.write("scanned = " + scanned);

                FlightRecorder.instance.insert("Body", "tcasScan (scanned): " + scanned);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(TCASDetermineAltitude tcasDetermineAltitude) {
        FlightRecorder.instance.insert("Body", "receive(" + tcasDetermineAltitude.toString() + ")");
        LogEngine.instance.write("Body.receive(" + tcasDetermineAltitude.toString() + ")");
        System.out.println(tcasDetermineAltitude);

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method determineAltitudeMethod = tcasPortList.get(i).getClass().getDeclaredMethod("determineAltitude", String.class);
                LogEngine.instance.write("determineAltitudeMethod = " + determineAltitudeMethod);

                int altitude = (int) determineAltitudeMethod.invoke(tcasPortList.get(i), "Earth");
                LogEngine.instance.write("altitude = " + altitude);

                PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
                FlightRecorder.instance.insert("Body", "tcasDetermineAltitude (altitudeTCAS): " + altitude);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (altitudeTCAS): " + PrimaryFlightDisplay.instance.altitudeTCAS);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "altitudeTCAS: " + PrimaryFlightDisplay.instance.altitudeTCAS);
    }
    // ----------------------------------------------------------------------------------------------------------------

    // --- TemperatureSensor --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(TemperatureSensorBodyMeasure temperatureSensorBodyMeasure) {
        System.out.println(temperatureSensorBodyMeasure);
        LogEngine.instance.write("+ Body.receive(" + temperatureSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + temperatureSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorBody; i++) {
                Method measureMethod = temperatureSensorPortList.get(i).getClass().getDeclaredMethod("measure");
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int temperature = (int) measureMethod.invoke(temperatureSensorPortList.get(i));
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
    public void receive(TurbulentAirFlowSensorBodyMeasure turbulentAirFlowSensorBodyMeasure) {
        FlightRecorder.instance.insert("Body", "receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");
        LogEngine.instance.write("Body.receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");
        System.out.println(turbulentAirFlowSensorBodyMeasure);

        try {
            for (int i = 0; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody; i++) {
                Method measureMethod = turbulentAirFlowSensorBodyPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int airFlow = (int) measureMethod.invoke(turbulentAirFlowSensorBodyPortList.get(i), "88");
                LogEngine.instance.write("airFlow = " + airFlow);

                FlightRecorder.instance.insert("Body", "turbulentAirFlowSensorMeasure (airFlowTurbulentAirFlowSensor): " + airFlow);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ----------------------------------------------------------------------------------------------------------------

    //Camera-------------------------------------------
    @Subscribe
    public void receive(CameraBodyOff cameraBodyOff) {
        System.out.println(cameraBodyOff);
        LogEngine.instance.write("+ Body.receive(" + cameraBodyOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cameraBodyOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCameraBody; i++) {
                Method offMethod = cameraBodyPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(cameraBodyPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCameraOn = isOn;
                FlightRecorder.instance.insert("Body", "cameraBodyOff (isCameraOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isCameraOn): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
    }

    @Subscribe
    public void receive(CameraBodyOn cameraBodyOn) {
        System.out.println(cameraBodyOn);
        LogEngine.instance.write("+ Body.receive(" + cameraBodyOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cameraBodyOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCameraBody; i++) {
                Method onMethod = cameraBodyPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(cameraBodyPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCameraOn = isOn;
                FlightRecorder.instance.insert("Body", "cameraBodyOn (isCameraOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isCameraOn): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
    }

    // ----------------------------------------------------------------------------------------------------------------
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
        LogEngine.instance.write("+ Body.receive(" + gpsOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGPS; i++) {
                Method onMethod = gpsPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(gpsPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isGPSOn = isOn;
                FlightRecorder.instance.insert("Body", "gpsOn (isGPSOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isGPSOn): " + PrimaryFlightDisplay.instance.isGPSOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGPSOn: " + PrimaryFlightDisplay.instance.isGPSOn);
    }

    @Subscribe
    public void receive(GPSOff gpsOff) {
        System.out.println(gpsOff);
        LogEngine.instance.write("+ Body.receive(" + gpsOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGPS; i++) {
                Method offMethod = gpsPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(gpsPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isGPSOn = isOn;
                FlightRecorder.instance.insert("Body", "gpsOff (isGPSOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isGPSOn): " + PrimaryFlightDisplay.instance.isGPSOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGPSOn: " + PrimaryFlightDisplay.instance.isGPSOn);
    }

    @Subscribe
    public void receive(GPSReceive gpsReceive) {
        FlightRecorder.instance.insert("Body", "receive(" + gpsReceive.toString() + ")");
        LogEngine.instance.write("Body.receive(" + gpsReceive.toString() + ")");
        System.out.println(gpsReceive);

        try {
            for (int i = 0; i < Configuration.instance.numberOfGPS; i++) {
                Method receiveMethod = gpsPortList.get(i).getClass().getDeclaredMethod("receive");
                LogEngine.instance.write("receiveMethod = " + receiveMethod);

                String received = (String) receiveMethod.invoke(gpsPortList.get(i));
                LogEngine.instance.write("received = " + received);

                FlightRecorder.instance.insert("Body", "gpsReceived (received): " + received);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(GPSConnect gpsConnect) {
        System.out.println(gpsConnect);
        LogEngine.instance.write("+ Body.receive(" + gpsConnect.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsConnect.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGPS; i++) {
                Method connectMethod = gpsPortList.get(i).getClass().getDeclaredMethod("connect", String.class);
                LogEngine.instance.write("connectMethod = " + connectMethod);

                boolean isConnected = (boolean) connectMethod.invoke(gpsPortList.get(i), "Astra-8");
                LogEngine.instance.write("isConnected = " + isConnected);

                PrimaryFlightDisplay.instance.isGPSConnected = isConnected;
                FlightRecorder.instance.insert("Body", "gpsConnect (isGPSConnected): " + isConnected);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isGPSConnected): " + PrimaryFlightDisplay.instance.isGPSConnected);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGPSConnected: " + PrimaryFlightDisplay.instance.isGPSConnected);
    }

    @Subscribe
    public void receive(GPSSend gpsSend) {
        FlightRecorder.instance.insert("Body", "receive(" + gpsSend.toString() + ")");
        LogEngine.instance.write("+ Body.receive(" + gpsSend.toString() + ")");
        System.out.println(gpsSend);
    }
    // ----------------------------------------------------------------------------------------------------------------

    //Radar------------------------------------------------------

    @Subscribe
    public void receive(RadarOn radarOn) {
        System.out.println(radarOn);
        LogEngine.instance.write("+ Body.receive(" + radarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadar; i++) {
                Method onMethod = radarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(radarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "radarOn (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isRadarOn): " + PrimaryFlightDisplay.instance.isRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRadarOn: " + PrimaryFlightDisplay.instance.isRadarOn);
    }

    @Subscribe
    public void receive(RadarOff radarOff) {
        System.out.println(radarOff);
        LogEngine.instance.write("+ Body.receive(" + radarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadar; i++) {
                Method offMethod = radarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(radarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "radarOff (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isRadarOn): " + PrimaryFlightDisplay.instance.isRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRadarOn: " + PrimaryFlightDisplay.instance.isRadarOn);
    }

    @Subscribe
    public void receive(RadarScan radarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + radarScan.toString() + ")");
        LogEngine.instance.write("+ Body.receive(" + radarScan.toString() + ")");
        System.out.println(radarScan);

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadar; i++) {
                Method scanMethod = radarPortList.get(i).getClass().getDeclaredMethod("scan", String.class);
                LogEngine.instance.write("scanMethod = " + scanMethod);

                boolean scanned = (boolean) scanMethod.invoke(radarPortList.get(i), "Cloud");
                LogEngine.instance.write("scanned = " + scanned);

                FlightRecorder.instance.insert("Body", "radarScan (scanned): " + scanned);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ----------------------------------------------------------------------------------------------------------------

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

    // --- CrewSeat ---------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SeatAssignCrewMember seatAssignCrewMember) {
        FlightRecorder.instance.insert("Body", "receive(" + seatAssignCrewMember.toString() + ")");
    }

    @Subscribe
    public void receive(event.crew_seat.NonSmokingSignOn nonSmokingSignOn) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method nonSmokingSignOnMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOn");
                LogEngine.instance.write("nonSmokingSignOnMethod = " + nonSmokingSignOnMethod);

                boolean nonSmokingSignIsOn = (boolean) nonSmokingSignOnMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("nonSmokingSignIsOn = " + nonSmokingSignIsOn);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = nonSmokingSignIsOn;
                FlightRecorder.instance.insert("Body", "CrewSeat (nonSmokingSignIsOn): " + nonSmokingSignIsOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(event.crew_seat.NonSmokingSignOff nonSmokingSignOff) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method nonSmokingSignOffMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOff");
                LogEngine.instance.write("nonSmokingSignOffMethod = " + nonSmokingSignOffMethod);

                boolean nonSmokingSignIsOff = (boolean) nonSmokingSignOffMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("nonSmokingSignIsOff = " + nonSmokingSignIsOff);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = nonSmokingSignIsOff;
                FlightRecorder.instance.insert("Body", "CrewSeat (nonSmokingSignIsOff): " + nonSmokingSignIsOff);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(event.crew_seat.SeatBeltSignOn seatBeltSignOn) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method seatBeltSignOnMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOn");
                LogEngine.instance.write("seatBeltSignOnMethod = " + seatBeltSignOnMethod);

                boolean seatBeltSignIsOn = (boolean) seatBeltSignOnMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("seatBeltSignIsOn = " + seatBeltSignIsOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = seatBeltSignIsOn;
                FlightRecorder.instance.insert("Body", "CrewSeat (seatBeltSignIsOn): " + seatBeltSignIsOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(event.crew_seat.SeatBeltSignOff seatBeltSignOff) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method seatBeltSignOffMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOff");
                LogEngine.instance.write("seatBeltSignOffMethod = " + seatBeltSignOffMethod);

                boolean seatBeltSignIsOff = (boolean) seatBeltSignOffMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("seatBeltSignIsOff = " + seatBeltSignIsOff);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = seatBeltSignIsOff;
                FlightRecorder.instance.insert("Body", "CrewSeat (seatBeltSignIsOff): " + seatBeltSignIsOff);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- EconomyClassSeat -------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(event.economy_class_seat.SeatAssignPassenger seatAssignPassenger) {
        FlightRecorder.instance.insert("Body", "receive(" + seatAssignPassenger.toString() + ")");
    }

    @Subscribe
    public void receive(NonSmokingSignOn nonSmokingSignOn) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method nonSmokingSignOnMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOn");
                LogEngine.instance.write("nonSmokingSignOnMethod = " + nonSmokingSignOnMethod);

                boolean nonSmokingSignIsOn = (boolean) nonSmokingSignOnMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("nonSmokingSignIsOn = " + nonSmokingSignIsOn);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = nonSmokingSignIsOn;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (nonSmokingSignIsOn): " + nonSmokingSignIsOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(NonSmokingSignOff nonSmokingSignOff) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method nonSmokingSignOffMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOff");
                LogEngine.instance.write("nonSmokingSignOffMethod = " + nonSmokingSignOffMethod);

                boolean nonSmokingSignIsOff = (boolean) nonSmokingSignOffMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("nonSmokingSignIsOff = " + nonSmokingSignIsOff);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = nonSmokingSignIsOff;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (nonSmokingSignIsOff): " + nonSmokingSignIsOff);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(SeatBeltSignOn seatBeltSignOn) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method seatBeltSignOnMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOn");
                LogEngine.instance.write("seatBeltSignOnMethod = " + seatBeltSignOnMethod);

                boolean seatBeltSignIsOn = (boolean) seatBeltSignOnMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("seatBeltSignIsOn = " + seatBeltSignIsOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = seatBeltSignIsOn;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (seatBeltSignIsOn): " + seatBeltSignIsOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(SeatBeltSignOff seatBeltSignOff) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method seatBeltSignOffMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOff");
                LogEngine.instance.write("seatBeltSignOffMethod = " + seatBeltSignOffMethod);

                boolean seatBeltSignIsOff = (boolean) seatBeltSignOffMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("seatBeltSignIsOff = " + seatBeltSignIsOff);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = seatBeltSignIsOff;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (seatBeltSignIsOff): " + seatBeltSignIsOff);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(SeatLevelOne seatLevelOne) {
        LogEngine.instance.write("+ Body.receive(" + seatLevelOne.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatLevelOne.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method level1Method = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("level1");
                LogEngine.instance.write("level1Method = " + level1Method);

                int isLevel1 = (int) level1Method.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("isLevel1 = " + isLevel1);

                PrimaryFlightDisplay.instance.levelSeat = isLevel1;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (isLevel1): " + isLevel1);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelOneSeat): " + PrimaryFlightDisplay.instance.levelSeat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelOneSeat: " + PrimaryFlightDisplay.instance.levelSeat);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- FireDetector -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(FireDetectorBodyScan fireDetectorBodyScan) {
        LogEngine.instance.write("+ Body.receive(" + fireDetectorBodyScan.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fireDetectorBodyScan.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFireDetectorBody; i++) {
                Method scanMethod = fireDetectorPortList.get(i).getClass().getDeclaredMethod("scan");
                LogEngine.instance.write("scanMethod = " + scanMethod);

                boolean isScanned = (boolean) scanMethod.invoke(fireDetectorPortList.get(i));
                LogEngine.instance.write("isScanned = " + isScanned);

                PrimaryFlightDisplay.instance.isFireDetectedBody = isScanned;
                FlightRecorder.instance.insert("Body", "FireDetector (isScanned): " + isScanned);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isFireDetectedBody): " + PrimaryFlightDisplay.instance.isFireDetectedBody);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isFireDetectedBody: " + PrimaryFlightDisplay.instance.isFireDetectedBody);
    }


    // ----------------------------------------------------------------------------------------------------------------

    // --- OxygenSensor -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(OxygenSensorMeasure oxygenSensorMeasure) {
        LogEngine.instance.write("+ Body.receive(" + oxygenSensorMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + oxygenSensorMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfOxygenSensor; i++) {
                Method measureMethod = oxygenSensorPortList.get(i).getClass().getDeclaredMethod("measure");
                LogEngine.instance.write("measureMethod = " + measureMethod);

                boolean measureResult = (boolean) measureMethod.invoke(oxygenSensorPortList.get(i));
                LogEngine.instance.write("measureResult = " + measureResult);

                PrimaryFlightDisplay.instance.isOxygenSensorAlarm = measureResult;
                FlightRecorder.instance.insert("Body", "OxygenSensor (measureResult): " + measureResult);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isOxygenSensorAlarm): " + PrimaryFlightDisplay.instance.isOxygenSensorAlarm);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isOxygenSensorAlarm: " + PrimaryFlightDisplay.instance.isOxygenSensorAlarm);
    }

    // --- Rudder -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RudderNeutral rudderNeutral) {
        LogEngine.instance.write("+ Body.receive(" + rudderNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method neutralMethod = rudderPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("neutralMethod = " + neutralMethod);

                int degree = (int) neutralMethod.invoke(rudderPortList.get(i));
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
    public void receive(RudderFullLeft rudderFullLeft) {
        LogEngine.instance.write("+ Body.receive(" + rudderFullLeft.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderFullLeft.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method fullLeftMethod = rudderPortList.get(i).getClass().getDeclaredMethod("fullLeft");
                LogEngine.instance.write("fullLeftMethod = " + fullLeftMethod);

                int degree = (int) fullLeftMethod.invoke(rudderPortList.get(i));
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
    public void receive(RudderFullRight rudderFullRight) {
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
    public void receive(RudderRight rudderRight) {
        LogEngine.instance.write("+ Body.receive(" + rudderRight.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderRight.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method rightMethod = rudderPortList.get(i).getClass().getDeclaredMethod("right", Integer.TYPE);
                LogEngine.instance.write("rightMethod = " + rightMethod);

                int degree = (int) rightMethod.invoke(rudderPortList.get(i), rudderRight.getValue());
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
    public void receive(RudderLeft rudderLeft) {
        LogEngine.instance.write("+ Body.receive(" + rudderLeft.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderLeft.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method leftMethod = rudderPortList.get(i).getClass().getDeclaredMethod("left", Integer.TYPE);
                LogEngine.instance.write("leftMethod = " + leftMethod);

                int degree = (int) leftMethod.invoke(rudderPortList.get(i), rudderLeft.getValue());
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

    // --- CargoCompartmentLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CargoCompartmentLightOn cargoCompartmentLightOn) {
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCargoCompartmentLight; i++) {
                Method onMethod = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightOff cargoCompartmentLightOff) {
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCargoCompartmentLight; i++) {
                Method offMethod = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightDim cargoCompartmentLightDim) {
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightDim.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- CostOptimizer -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CostOptimizerOn costOptimizerOn) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method onMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "CostOptimizerOn (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }

    @Subscribe
    public void receive(CostOptimizerOff costOptimizerOff) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method offMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "CostOptimizerOff (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }

    @Subscribe
    public void receive(CostOptimizerAddCheckPoint costOptimizerAddCheckPoint) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerAddCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerAddCheckPoint.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method addMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("add");//, CheckPoint.class);
                LogEngine.instance.write("addMethod = " + addMethod);

                int numberOfCheckPoints = (int) addMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("numberOfCheckPoints = " + numberOfCheckPoints);

                PrimaryFlightDisplay.instance.indexCostOptimizer = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "CostOptimizerAddCheckPoint (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + PrimaryFlightDisplay.instance.indexCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + PrimaryFlightDisplay.instance.indexCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerRemoveCheckPoint costOptimizerRemoveCheckPoint) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerRemoveCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerRemoveCheckPoint.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method removeMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("remove", Integer.class);
                LogEngine.instance.write("removeMethod = " + removeMethod);

                int numberOfCheckPoints = (int) removeMethod.invoke(costOptimizerPortList.get(i), 1);
                LogEngine.instance.write("numberOfCheckPoints = " + numberOfCheckPoints);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "CostOptimizerRemoveCheckPoint (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerValidate costOptimizerValidate) {
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerValidate.toString() + ")");
    }

    @Subscribe
    public void receive(CostOptimizerOptimize costOptimizerOptimize) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOptimize.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOptimize.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method optimizeMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("optimize");
                LogEngine.instance.write("optimizeMethod" + optimizeMethod);

                int costIndex = (int) optimizeMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("costIndex" + costIndex);

                PrimaryFlightDisplay.instance.indexCostOptimizer = costIndex;
                FlightRecorder.instance.insert("Body", "CostOptimizerOptimize (costIndex): " + costIndex);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + PrimaryFlightDisplay.instance.indexCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + PrimaryFlightDisplay.instance.indexCostOptimizer);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LandingLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LandingLightBodyOn landingLightBodyOn) {
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method onMethod = landingLightBodyPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(landingLightBodyPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLightBody (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    @Subscribe
    public void receive(LandingLightBodyOff landingLightBodyOff) {
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method offMethod = landingLightBodyPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(landingLightBodyPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLightBody (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightBodyOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("isLandingLightBodyOn", "isLandingLightOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LeftNavigationLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LogoLightOn logoLightOn) {
        LogEngine.instance.write("+ Body.receive(" + logoLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method onMethod = logoLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LogoLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    @Subscribe
    public void receive(LogoLightOff logoLightOff) {
        LogEngine.instance.write("+ Body.receive(" + logoLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method offMethod = logoLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LogoLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- RouteManagement -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RouteManagementOn routeManagementOn) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method onMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementOff routeManagementOff) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method offMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementAdd routeManagementAdd) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementAdd.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementAdd.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method addMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("add");//, CheckPoint.class);
                LogEngine.instance.write("addMethod = " + addMethod);

                int numberOfCheckPoints = (int) addMethod.invoke(routeManagementPortList.get(i));//, new CheckPoint(PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement, "London", "51° 31′ N", "0° 7′ W"));
                FlightRecorder.instance.insert("Body", "RouteManagement (addMethod): " + addMethod);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "RouteManagement (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementRemove routeManagementRemove) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementRemove.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementRemove.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method removeMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("remove", Integer.class);
                LogEngine.instance.write("removeMethod = " + removeMethod);

                int numberOfCheckPoints = (int) removeMethod.invoke(routeManagementPortList.get(i), PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement / 2);
                FlightRecorder.instance.insert("Body", "RouteManagement (removeMethod): " + removeMethod);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "RouteManagement (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementSetCostIndex routeManagementSetCostIndex) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementSetCostIndex.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementSetCostIndex.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method setCostIndexMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("setCostIndex", Integer.class);
                LogEngine.instance.write("setCostIndexMethod" + setCostIndexMethod);

                double costIndex = (double) setCostIndexMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("costIndex" + costIndex);

                PrimaryFlightDisplay.instance.indexRouteManagement = (int) costIndex;
                FlightRecorder.instance.insert("Body", "RouteManagerSetCostIndex (costIndex): " + (int) costIndex);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexRouteManagement): " + PrimaryFlightDisplay.instance.indexRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexRouteManagement: " + PrimaryFlightDisplay.instance.indexRouteManagement);
    }

    // ----------------------------------------------------------------------------------------------------------------
}