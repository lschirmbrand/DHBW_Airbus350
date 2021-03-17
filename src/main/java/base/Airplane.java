package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.air_conditioning.*;
import event.airflow_sensor.AirFlowSensorBodyMeasure;
import event.airflow_sensor.AirFlowSensorWingMeasure;
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
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.*;
import event.crew_seat.NonSmokingSignOff;
import event.crew_seat.NonSmokingSignOn;
import event.crew_seat.SeatBeltSignOff;
import event.crew_seat.SeatBeltSignOn;
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
import event.fire_detector.FireDetectorBodyScan;
import event.fire_detector.FireDetectorWingScan;
import event.gear.*;
import event.gps.*;
import event.hydraulicPump.*;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.landing_light.LandingLightWingOff;
import event.landing_light.LandingLightWingOn;
import event.left_aileron.LeftAileronFullDown;
import event.left_aileron.LeftAileronNeutral;
import event.left_aileron.LeftAileronUp;
import event.left_navigation_light.LeftNavigationLightOff;
import event.left_navigation_light.LeftNavigationLightOn;
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
import event.right_aileron.RightAileronDown;
import event.right_aileron.RightAileronFullUp;
import event.right_aileron.RightAileronNeutral;
import event.route_management.RouteManagementAdd;
import event.route_management.RouteManagementOff;
import event.route_management.RouteManagementOn;
import event.route_management.RouteManagementSetCostIndex;
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
import event.tcas.*;
import event.temperature_sensor.TemperatureSensorBodyMeasure;
import event.temperature_sensor.TemperatureSensorWingMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

@SuppressWarnings({"UnstableApiUsage", "FieldCanBeLocal"})
public class Airplane implements IAirplane {
    private final EventBus eventBus;
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
        // air_conditioning
        eventBus.post(new AirConditioningOn());
        eventBus.post(new AirConditioningClean("wusch"));

        // airflow_sensor
        eventBus.post(new AirFlowSensorBodyMeasure());

        // anti_collision_light
        eventBus.post(new AntiCollisionLightOn());

        // apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(5000));

        // battery
        eventBus.post(new BatteryCharge());

        // camera
        eventBus.post(new CameraBodyOn());
        eventBus.post(new CameraWingOn());

        // crew_seat
        eventBus.post(new NonSmokingSignOff());
        eventBus.post(new SeatBeltSignOff());

        // deicing_system
        eventBus.post(new DeIcingSystemActivate());
        eventBus.post(new DeIcingSystemDeIce(200));

        // droop_nose
        eventBus.post(new DroopNoseNeutral());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOff());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // elevator
        eventBus.post(new ElevatorNeutral());

        // engine
        eventBus.post(new EngineStart());
        eventBus.post(new EngineIncreaseRPM(0));

        // fire_detector no action in startup

        // gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());

        // gps
        eventBus.post(new GPSOn());
        eventBus.post(new GPSConnect("Astra-8"));

        // hydraulic_pump
        eventBus.post(new HydraulicPumpBodyCompress());
        eventBus.post(new HydraulicPumpWingCompress());
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Radar
        eventBus.post(new RadarOn());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASOn());
        eventBus.post(new TCASConnect("15000"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        //OxygenTank
        eventBus.post(new OxygenBottleRefill());

        //NitrogenTank
        eventBus.post(new NitrogenBottleRefill());

        // oxygenSensor no action in startup

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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

        //CostOptimizer
        eventBus.post(new CostOptimizerOn());

        //RouteManagement
        eventBus.post(new RouteManagementOn());

        // LogoLight
        eventBus.post(new LogoLightOn());
    }

    public void taxi() {
        // Radar
        eventBus.post(new RadarOn());
        // LogoLight
        eventBus.post(new LogoLightOn());
        // hydraulic_pump
        eventBus.post(new HydraulicPumpBodyCompress());
        eventBus.post(new HydraulicPumpWingCompress());
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));
        // gps
        eventBus.post(new GPSOn());
        eventBus.post(new GPSConnect("Astra-8"));
        // engine
        eventBus.post(new EngineStart());
        eventBus.post(new EngineIncreaseRPM(0));
        // camera
        eventBus.post(new CameraBodyOn());
        eventBus.post(new CameraWingOn());
        // air_conditioning
        eventBus.post(new AirConditioningOn());
        eventBus.post(new AirConditioningHeat("wusch", 20));
        // apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(5000));
        //CostOptimizer
        eventBus.post(new CostOptimizerOn());
        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        //Droop Nose
        eventBus.post(new DroopNoseNeutral());
        //GPS
        eventBus.post(new GPSSend("Taxi"));
        eventBus.post(new GPSReceive());
        // deicing_system
        eventBus.post(new DeIcingSystemActivate());
        eventBus.post(new DeIcingSystemDeIce(200));
        //Radar
        eventBus.post(new RadarScan("Erde"));
        //TCAS
        eventBus.post(new TCASScan("Erde"));
        // elevator
        eventBus.post(new ElevatorNeutral());
        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());
        // crew_seat
        eventBus.post(new NonSmokingSignOff());
        eventBus.post(new SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOff());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // fireDetector no action in taxi

        // oxygenSensor no action in taxi

        //Battery
        eventBus.post(new BatteryDischarge());

        //AirFlowSensor
        eventBus.post(new AirFlowSensorBodyMeasure());
        eventBus.post(new AirFlowSensorWingMeasure());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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

        // cargo_compartment_light
        eventBus.post(new CargoCompartmentLightOn());

    }

    public void takeoff() {
        // apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(5000));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningOn());
        eventBus.post(new AirConditioningHeat("wusch", 24));

        // cost_optimizer
        eventBus.post(new CostOptimizerOn());
        eventBus.post(new CostOptimizerOptimize());

        // deicing_system
        eventBus.post(new DeIcingSystemActivate());
        eventBus.post(new DeIcingSystemDeIce(150));

        // engine
        eventBus.post(new EngineIncreaseRPM(15000));

        // hydraulic pump
        eventBus.post(new HydraulicPumpBodyDecompress());
        eventBus.post(new HydraulicPumpWingDecompress());
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Droop Nose
        eventBus.post(new DroopNoseDown(10));

        // Elevator
        eventBus.post(new ElevatorFullUp());

        //GPS
        eventBus.post(new GPSOn());
        eventBus.post(new GPSConnect("Astra-8"));
        eventBus.post(new GPSSend("TakeoOff"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // crew_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // fireDetector
        eventBus.post(new FireDetectorBodyScan("air"));
        eventBus.post(new FireDetectorWingScan("air"));

        // oxygenSensor
        eventBus.post(new OxygenSensorMeasure());
        //AirFlowSensor
        eventBus.post(new AirFlowSensorBodyMeasure());
        eventBus.post(new AirFlowSensorWingMeasure());

        // battery
        eventBus.post(new BatteryDischarge());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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

        // logo_light
        eventBus.post(new LogoLightOn());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(25));

        // oxygen_bottle
        eventBus.post(new OxygenBottleTakeOut(10));

        // cargo_compartment_light
        eventBus.post(new CargoCompartmentLightOff());

        // gear
        eventBus.post(new GearReleaseBrake());

        // route_management
        eventBus.post(new RouteManagementSetCostIndex(30));
    }

    public void climbing() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(2500));

        // gear
        eventBus.post(new GearUp());
        eventBus.post(new GearReleaseBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 24));

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        // elevator
        eventBus.post(new ElevatorUp(10));

        // engine
        eventBus.post(new EngineIncreaseRPM(4000));

        //GPS
        eventBus.post(new GPSSend("Climbing"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // crew_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // fireDetector
        eventBus.post(new FireDetectorBodyScan("air"));
        eventBus.post(new FireDetectorWingScan("air"));

        // oxygenSensor
        eventBus.post(new OxygenSensorMeasure());
        // battery
        eventBus.post(new BatteryDischarge());

        //AirFlowSensor
        eventBus.post(new AirFlowSensorBodyMeasure());
        eventBus.post(new AirFlowSensorWingMeasure());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(1000));

        // gear
        eventBus.post(new GearUp());
        eventBus.post(new GearReleaseBrake());

        // engine
        eventBus.post(new EngineIncreaseRPM(2500));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 24));

        //GPS
        eventBus.post(new GPSSend("rightTurn"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // crew_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // fireDetector
        eventBus.post(new FireDetectorBodyScan("air"));
        eventBus.post(new FireDetectorWingScan("air"));

        // oxygenSensor
        eventBus.post(new OxygenSensorMeasure());
        // battery
        eventBus.post(new BatteryDischarge());

        //AirFlowSensor
        eventBus.post(new AirFlowSensorBodyMeasure());
        eventBus.post(new AirFlowSensorWingMeasure());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(1000));

        // gear
        eventBus.post(new GearUp());
        eventBus.post(new GearReleaseBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 24));

        //GPS
        eventBus.post(new GPSSend("leftTurn"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // crew_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // fireDetector
        eventBus.post(new FireDetectorBodyScan("air"));
        eventBus.post(new FireDetectorWingScan("air"));

        // oxygenSensor
        eventBus.post(new OxygenSensorMeasure());
        // battery
        eventBus.post(new BatteryDischarge());

        //AirFlowSensor
        eventBus.post(new AirFlowSensorBodyMeasure());
        eventBus.post(new AirFlowSensorWingMeasure());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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

        //LeftNavigationLight
        eventBus.post(new LeftNavigationLightOn());
    }

    public void descent() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(1000));

        //gear
        eventBus.post(new GearUp());
        eventBus.post(new GearSetBrakePercentage(0));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 24));

        // elevator
        eventBus.post(new ElevatorDown(-10));

        // engine
        eventBus.post(new EngineDecreaseRPM(500));

        //Droop Nose

        //GPS
        eventBus.post(new GPSSend("descent"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // crew_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // fireDetector
        eventBus.post(new FireDetectorBodyScan("air"));
        eventBus.post(new FireDetectorWingScan("air"));

        // oxygenSensor
        eventBus.post(new OxygenSensorMeasure());
        // battery
        eventBus.post(new BatteryDischarge());

        //AirFlowSensor
        eventBus.post(new AirFlowSensorBodyMeasure());
        eventBus.post(new AirFlowSensorWingMeasure());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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

        // left_navigation_light
        eventBus.post(new LeftNavigationLightOff());
    }

    public void landing() {
        // apu
        eventBus.post(new APUDecreaseRPM(5000));
        eventBus.post(new APUIncreaseRPM(250));

        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // air_conditioning
        eventBus.post(new AirConditioningHeat("wusch", 24));


        // engine
        eventBus.post(new EngineDecreaseRPM(-10000));

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyCompress());
        eventBus.post(new HydraulicPumpWingCompress());
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Droop Nose
        eventBus.post(new DroopNoseFullDown());

        // Elevator
        eventBus.post(new ElevatorFullDown());

        //GPS
        eventBus.post(new GPSSend("landing"));
        eventBus.post(new GPSReceive());

        //Radar
        eventBus.post(new RadarScan("Erde"));

        //TCAS
        eventBus.post(new TCASScan("Erde"));
        eventBus.post(new TCASDetermineAltitude("Erde"));

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // crew_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // fireDetector
        eventBus.post(new FireDetectorBodyScan("air"));
        eventBus.post(new FireDetectorWingScan("air"));

        // oxygenSensor
        eventBus.post(new OxygenSensorMeasure());
        // battery
        eventBus.post(new BatteryDischarge());

        //AirFlowSensor
        eventBus.post(new AirFlowSensorBodyMeasure());
        eventBus.post(new AirFlowSensorWingMeasure());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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

        //LandingLight
        eventBus.post(new LandingLightBodyOn());
        eventBus.post(new LandingLightWingOn());
    }

    public void shutdown() {
        // apu
        eventBus.post(new APUShutdown());

        //gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrake());

        // weather_radar
        eventBus.post(new WeatherRadarOff());

        // air_conditioning
        eventBus.post(new AirConditioningCool("", 0));
        eventBus.post(new AirConditioningOff());

        //Engine
        eventBus.post(new EngineShutdown());
        eventBus.post(new EngineDecreaseRPM(-15000));

        // hydraulic pumps
        eventBus.post(new HydraulicPumpBodyRefillOil(0));
        eventBus.post(new HydraulicPumpWingRefillOil(0));

        //Elevator
        eventBus.post(new ElevatorNeutral());

        //Droop Nose
        eventBus.post(new DroopNoseNeutral());

        //Camera
        eventBus.post(new CameraBodyOff());
        eventBus.post(new CameraWingOff());

        //GPS
        eventBus.post(new GPSOff());

        //Radar
        eventBus.post(new RadarOff());

        //TCAS
        eventBus.post(new TCASOff());

        //Turbulent Airflow Sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        //OxygenTank
        eventBus.post(new OxygenBottleTakeOut(100));

        //NitrogenTank
        eventBus.post(new NitrogenBottleTakeOut(250));

        // crew_seat
        eventBus.post(new NonSmokingSignOff());
        eventBus.post(new SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOff());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // fireDetector no action in shutdown

        // oxygenSensor no action in shutdown
        // battery
        eventBus.post(new BatteryCharge());

        //TemperatureSensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());

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

        //cargoCompartmentLight
        eventBus.post(new CargoCompartmentLightOff());

        //costOptimizer
        eventBus.post(new CostOptimizerOff());

        //LandingLight
        eventBus.post(new LandingLightBodyOff());
        eventBus.post(new LandingLightWingOff());

        //RouteManagement
        eventBus.post(new RouteManagementOff());
        eventBus.post(new RouteManagementSetCostIndex(0));

        // deicing_system
        eventBus.post(new DeIcingSystemDeactivate());
        eventBus.post(new DeIcingSystemRefill());

        // logo_light
        eventBus.post(new LogoLightOff());
    }
}