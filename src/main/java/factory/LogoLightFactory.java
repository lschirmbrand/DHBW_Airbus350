package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class LogoLightFactory {
    public static Object build() {
        Object logoLightPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToLogoLight).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, LogoLightFactory.class.getClassLoader());
            Class logoLightClass = Class.forName("LogoLight", true, urlClassLoader);
            FlightRecorder.instance.insert("LogoLightFactory", "logoLightClass: " + logoLightClass.hashCode());

            Object logoLightInstance = logoLightClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("LogoLightFactory", "logoLightInstance: " + logoLightInstance.hashCode());

            logoLightPort = logoLightClass.getDeclaredField("port").get(logoLightInstance);
            FlightRecorder.instance.insert("LogoLightFactory", "logoLightPort: " + logoLightPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return logoLightPort;
    }
}
