package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class RudderFactory {
    public static Object build() {
        Object rudderPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToRudderJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, RudderFactory.class.getClassLoader());
            Class rudderClass = Class.forName("Rudder", true, urlClassLoader);
            FlightRecorder.instance.insert("RudderFactory", "rudderClass: " + rudderClass.hashCode());

            Object rudderInstance = rudderClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("RudderFactory", "rudderInstance: " + rudderInstance.hashCode());

            rudderPort = rudderClass.getDeclaredField("port").get(rudderInstance);
            FlightRecorder.instance.insert("RudderFactory", "rudderPort: " + rudderPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rudderPort;
    }
}
