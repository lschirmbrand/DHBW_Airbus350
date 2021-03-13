package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class LandingLightFactory {
    public static Object build() {
        Object landingLightPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToLandingLight).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, LandingLightFactory.class.getClassLoader());
            Class landingLightClass = Class.forName("LandingLight", true, urlClassLoader);
            FlightRecorder.instance.insert("LandingLightFactory", "landingLightFactory: " + landingLightClass.hashCode());

            Object landingLightInstance = landingLightClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("LandingLightFactory", "landingLightInstance: " + landingLightInstance.hashCode());

            landingLightPort = landingLightClass.getDeclaredField("port").get(landingLightInstance);
            FlightRecorder.instance.insert("LandingLightFactory", "landingLightPort: " + landingLightPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return landingLightPort;
    }
}
