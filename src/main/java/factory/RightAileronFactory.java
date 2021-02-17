package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class RightAileronFactory {
    public static Object build() {
        Object rightAileronPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToRightAileronJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, RightAileronFactory.class.getClassLoader());
            Class rightAileronClass = Class.forName("RightAileron", true, urlClassLoader);
            FlightRecorder.instance.insert("RightAileronFactory", "rightAileronClass: " + rightAileronClass.hashCode());

            Object rightAileronInstance = rightAileronClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("RightAileronFactory", "rightAileronInstance: " + rightAileronInstance.hashCode());

            rightAileronPort = rightAileronClass.getDeclaredField("port").get(rightAileronInstance);
            FlightRecorder.instance.insert("RightAileronFactory", "rightAileronPort: " + rightAileronPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rightAileronPort;
    }
}
