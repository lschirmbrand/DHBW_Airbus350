package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ElevatorFactory {
    public static Object build() {
        Object elevatorPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToElevatorJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, ElevatorFactory.class.getClassLoader());
            Class elevatorClass = Class.forName("Elevator", true, urlClassLoader);
            FlightRecorder.instance.insert("ElevatorFactory", "elevatorClass: " + elevatorClass.hashCode());

            Object elevatorInstance = elevatorClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("ElevatorFactory", "elevatorInstance: " + elevatorInstance.hashCode());

            elevatorPort = elevatorClass.getDeclaredField("port").get(elevatorInstance);
            FlightRecorder.instance.insert("ElevatorFactory", "elevatorPort: " + elevatorPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return elevatorPort;
    }
}