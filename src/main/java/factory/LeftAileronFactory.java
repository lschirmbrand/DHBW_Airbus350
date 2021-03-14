package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class LeftAileronFactory {
    public static Object build() {
        Object leftAileronPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToLeftAileronJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, LeftAileronFactory.class.getClassLoader());
            Class leftAileronClass = Class.forName("LeftAileron", true, urlClassLoader);
            FlightRecorder.instance.insert("LeftAileronFactory", "leftAileronClass: " + leftAileronClass.hashCode());

            Object leftAileronInstance = leftAileronClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("LeftAileronFactory", "leftAileronInstance: " + leftAileronInstance.hashCode());

            leftAileronPort = leftAileronClass.getDeclaredField("port").get(leftAileronInstance);
            FlightRecorder.instance.insert("LeftAileronFactory", "leftAileronPort: " + leftAileronPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return leftAileronPort;
    }
}