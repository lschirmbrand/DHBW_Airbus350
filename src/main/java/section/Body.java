package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.elevator.*;
import event.hydraulicPump.HydraulicPumpBodyCompress;
import event.hydraulicPump.HydraulicPumpBodyRefillOil;
import event.hydraulicPump.HydraulicPumpWingDecompress;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.EngineFactory;
import factory.HydraulicPumpFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> hydraulicPumpPortList;
    private ArrayList<Object> elevatorPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        elevatorPortList = new ArrayList<>();

        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
            hydraulicPumpPortList.add(HydraulicPumpFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
            elevatorPortList.add(EngineFactory.build());
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

    // --- HydraulicPump -------------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(HydraulicPumpBodyCompress hydraulicPumpBodyCompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyCompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method hydraulicPumpBodyCompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("innerCompress", int.class);
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
                Method hydraulicPumpBodyDecompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("innerDecompress");
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
                Method hydraulicPumpBodyRefillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("innerRefillOil", int.class);
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

    // --- Elevator -------------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(ElevatorNeutral elevatorNeutral) {
        LogEngine.instance.write("+ Body.receive(" + elevatorNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + elevatorNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorNeutralMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("innerNeutral");
                LogEngine.instance.write("elevatorNeutralMethod = " + elevatorNeutralMethod);

                int angle = (int) elevatorNeutralMethod.invoke(elevatorPortList.get(i));
                LogEngine.instance.write("elevatorNeutralMethod = " + angle);

                PrimaryFlightDisplay.instance.degreeElevator = angle;
                FlightRecorder.instance.insert("Body", "elevatorNeutral (angle): " + angle);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeElevator): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeElevator: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorFullUp elevatorFullUp) {
        LogEngine.instance.write("+ Body.receive(" + elevatorFullUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + elevatorFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorFullUpMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("innerFullUp");
                LogEngine.instance.write("elevatorFullUpMethod = " + elevatorFullUpMethod);

                int angle = (int) elevatorFullUpMethod.invoke(elevatorPortList.get(i));
                LogEngine.instance.write("elevatorFullUpMethod = " + angle);

                PrimaryFlightDisplay.instance.degreeElevator = angle;
                FlightRecorder.instance.insert("Body", "elevatorFullUp (angle): " + angle);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeElevator): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeElevator: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorFullDown elevatorFullDown) {
        LogEngine.instance.write("+ Body.receive(" + elevatorFullDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + elevatorFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorFullDownMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("innerFullDown");
                LogEngine.instance.write("elevatorFullDownMethod = " + elevatorFullDownMethod);

                int angle = (int) elevatorFullDownMethod.invoke(elevatorPortList.get(i));
                LogEngine.instance.write("elevatorFullDownMethod = " + angle);

                PrimaryFlightDisplay.instance.degreeElevator = angle;
                FlightRecorder.instance.insert("Body", "elevatorFullDown (angle): " + angle);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeElevator): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeElevator: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorUp elevatorUp) {
        LogEngine.instance.write("+ Body.receive(" + elevatorUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + elevatorUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorUpMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("innerUp", int.class);
                LogEngine.instance.write("elevatorUpMethod = " + elevatorUpMethod);

                int angle = (int) elevatorUpMethod.invoke(elevatorPortList.get(i), elevatorUp.getValue());
                LogEngine.instance.write("elevatorUpMethod = " + angle);

                PrimaryFlightDisplay.instance.degreeElevator = angle;
                FlightRecorder.instance.insert("Body", "elevatorUp (angle): " + angle);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeElevator): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeElevator: " + PrimaryFlightDisplay.instance.degreeElevator);
    }

    @Subscribe
    public void receive(ElevatorDown elevatorDown) {
        LogEngine.instance.write("+ Body.receive(" + elevatorDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + elevatorDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfElevator; i++) {
                Method elevatorDownMethod = elevatorPortList.get(i).getClass().getDeclaredMethod("innerDown", int.class);
                LogEngine.instance.write("elevatorDownMethod = " + elevatorDownMethod);

                int angle = (int) elevatorDownMethod.invoke(elevatorPortList.get(i), elevatorDown.getValue());
                LogEngine.instance.write("elevatorDownMethod = " + angle);

                PrimaryFlightDisplay.instance.degreeElevator = angle;
                FlightRecorder.instance.insert("Body", "elevatorDown (angle): " + angle);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeElevator): " + PrimaryFlightDisplay.instance.degreeElevator);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeElevator: " + PrimaryFlightDisplay.instance.degreeElevator);
    }


}