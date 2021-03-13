package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class CargoCompartmentLightFactory {
    public static Object build() {
        Object cargoCompartmentLightPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToCargoCompartmentLight).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, CargoCompartmentLightFactory.class.getClassLoader());
            Class cargoCompartmentLightClass = Class.forName("CargoCompartmentLight", true, urlClassLoader);
            FlightRecorder.instance.insert("CargoCompartmentLightFactory", "cargoCompartmentLightClass: " + cargoCompartmentLightClass.hashCode());

            Object cargoCompartmentLightInstance = cargoCompartmentLightClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("CargoCompartmentLightFactory", "cargoCompartmentLightInstance: " + cargoCompartmentLightInstance.hashCode());

            cargoCompartmentLightPort = cargoCompartmentLightClass.getDeclaredField("port").get(cargoCompartmentLightInstance);
            FlightRecorder.instance.insert("CargoCompartmentLightFactory", "cargoCompartmentLightPort: " + cargoCompartmentLightPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cargoCompartmentLightPort;
    }
}
