package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Factory {
    public static Object build(String name, String path) {
        Object port = null;

        try {
            URL[] urls = {new File(path).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, Factory.class.getClassLoader());
            Class clazz = Class.forName(name, true, urlClassLoader);
            FlightRecorder.instance.insert(name + "Factory", name + "Class: " + clazz.hashCode());

            Object instance = clazz.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert(name + "Factory", name + "Instance: " + instance.hashCode());

            port = clazz.getDeclaredField("port").get(instance);
            FlightRecorder.instance.insert(name + "Factory", name + "Port: " + port.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return port;
    }
}
