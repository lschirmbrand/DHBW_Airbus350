package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class LeftNavigationLightFactory {
    public static Object build() {
        Object leftNavigationLightPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToLeftNavigationLight).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, LeftNavigationLightFactory.class.getClassLoader());
            Class leftNavigationLightClass = Class.forName("LeftNavigationLight", true, urlClassLoader);
            FlightRecorder.instance.insert("LeftNavigationLightFactory", "leftNavigationLightClass: " + leftNavigationLightClass.hashCode());

            Object leftNavigationLightInstance = leftNavigationLightClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("LeftNavigationFactory", "leftNavigationLightInstance: " + leftNavigationLightInstance.hashCode());

            leftNavigationLightPort = leftNavigationLightClass.getDeclaredField("port").get(leftNavigationLightInstance);
            FlightRecorder.instance.insert("LeftNavigationLightFactory", "leftNavigationLightPort: " + leftNavigationLightPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return leftNavigationLightPort;
    }
}
