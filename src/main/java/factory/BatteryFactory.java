package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class BatteryFactory {

    public static Object build() {
        Object batteryPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToBatteryJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, BatteryFactory.class.getClassLoader());
            Class batteryClass = Class.forName("Battery", true, urlClassLoader);
            FlightRecorder.instance.insert("BatteryFactory", "batteryClass: " + batteryClass.hashCode());

            Object batteryInstance = batteryClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("BatteryFactory", "batteryInstance: " + batteryInstance.hashCode());

            batteryPort = batteryClass.getDeclaredField("port").get(batteryInstance);
            FlightRecorder.instance.insert("BatteryFactory", "batteryPort: " + batteryPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batteryPort;
    }
}

