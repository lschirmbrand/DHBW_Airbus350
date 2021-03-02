package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class TemperatureSensorFactory {

    public static Object build() {
        Object temperatureSensorPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToTemperatureSensorJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, TemperatureSensorFactory.class.getClassLoader());
            Class temperatureSensorClass = Class.forName("TemperatureSensor", true, urlClassLoader);
            FlightRecorder.instance.insert("TemperatureSensorFactory", "TemperatureSensor: " + temperatureSensorClass.hashCode());

            Object temperatureSensorInstance = temperatureSensorClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("TemperatureSensorFactory", "temperatureSensorInstance: " + temperatureSensorInstance.hashCode());

            temperatureSensorPort = temperatureSensorClass.getDeclaredField("port").get(temperatureSensorInstance);
            FlightRecorder.instance.insert("TemperatureSensoryFactory", "TemperatureSensorPort: " + temperatureSensorPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temperatureSensorPort;    }
}

