package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.gps.*;
import event.radar.RadarOff;
import event.radar.RadarOn;
import event.radar.RadarScan;
import event.tcas.*;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
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
        System.out.println(radarOff);
    }

    @Subscribe
    public void receive(RadarScan radarScan) {
        System.out.println(radarScan);
    }
    //------------------------------------------------------------
}