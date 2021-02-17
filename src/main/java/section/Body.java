package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.APUFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> apuPortList;
    private ArrayList<Object> weatherRadarPortList;

    public Body() {
        apuPortList = new ArrayList<>();
        weatherRadarPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
            apuPortList.add(APUFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
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
    }

    @Subscribe
    public void receive(APUShutdown apuShutdown) {
        LogEngine.instance.write("+ Body.receive(" + apuShutdown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive" + apuShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfApu; i++) {
                Method shutdownMethod = apuPortList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("shutdownMethod = " + shutdownMethod);

                LogEngine.instance.write("isStarted = " + false);

                PrimaryFlightDisplay.instance.isAPUStarted = false;
                FlightRecorder.instance.insert("Body", "APU (isStarted): " + false);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
}