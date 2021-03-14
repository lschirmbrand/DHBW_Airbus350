package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class SlatFactory {
    public static Object build() {
        Object slatPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToSlatJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, SlatFactory.class.getClassLoader());
            Class slatClass = Class.forName("Slat", true, urlClassLoader);
            FlightRecorder.instance.insert("SlatFactory", "slatClass: " + slatClass.hashCode());

            Object slatInstance = slatClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("SlatFactory", "slatInstance: " + slatInstance.hashCode());

            slatPort = slatClass.getDeclaredField("port").get(slatInstance);
            FlightRecorder.instance.insert("SlatFactory", "slatPort: " + slatPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return slatPort;
    }
}