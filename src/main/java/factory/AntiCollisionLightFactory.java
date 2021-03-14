package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class AntiCollisionLightFactory {
    public static Object build() {
        Object antiCollisionLightPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToAntiCollisionLightJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, AntiCollisionLightFactory.class.getClassLoader());
            Class antiCollisionLightClass = Class.forName("AntiCollisionLight", true, urlClassLoader);
            FlightRecorder.instance.insert("AntiCollisionLightFactory", "antiCollisionLightClass: " + antiCollisionLightClass.hashCode());

            Object antiCollisionLightInstance = antiCollisionLightClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("AntiCollisionLightFactory", "antiCollisionLightInstance: " + antiCollisionLightInstance.hashCode());

            antiCollisionLightPort = antiCollisionLightClass.getDeclaredField("port").get(antiCollisionLightInstance);
            FlightRecorder.instance.insert("AntiCollisionLightFactory", "antiCollisionLightPort: " + antiCollisionLightPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return antiCollisionLightPort;
    }
}
