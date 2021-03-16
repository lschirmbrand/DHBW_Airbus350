package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DeIcingSystemFactory {

    public static Object build() {
        Object deIcingSystemPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToDeIcingSystemJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, DeIcingSystemFactory.class.getClassLoader());
            Class deIcingSystemClass = Class.forName("DeicingSystem", true, urlClassLoader);
            FlightRecorder.instance.insert("DeIcingSystemFactory", "deIcingSystemClass: " + deIcingSystemClass.hashCode());

            Object DeIcingSystemInstance = deIcingSystemClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("DeIcingSystemFactory", "DeIcingSystemInstance: " + DeIcingSystemInstance.hashCode());

            deIcingSystemPort = deIcingSystemClass.getDeclaredField("port").get(DeIcingSystemInstance);
            FlightRecorder.instance.insert("DeIcingSystemFactory", "deIcingSystemPort: " + deIcingSystemPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deIcingSystemPort;
    }
}

