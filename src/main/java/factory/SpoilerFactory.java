package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class SpoilerFactory {
    public static Object build() {
        Object spoilerPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToSpoilerJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, SpoilerFactory.class.getClassLoader());
            Class spoilerClass = Class.forName("Spoiler", true, urlClassLoader);
            FlightRecorder.instance.insert("SpoilerFactory", "spoilerClass: " + spoilerClass.hashCode());

            Object spoilerInstance = spoilerClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("SpoilerFactory", "spoilerInstance: " + spoilerInstance.hashCode());

            spoilerPort = spoilerClass.getDeclaredField("port").get(spoilerInstance);
            FlightRecorder.instance.insert("SpoilerFactory", "spoilerPort: " + spoilerPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spoilerPort;
    }
}
