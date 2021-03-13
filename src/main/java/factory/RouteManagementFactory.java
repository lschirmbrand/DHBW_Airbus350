package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class RouteManagementFactory {
    public static Object build() {
        Object routeManagementPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToRouteManagement).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, RouteManagementFactory.class.getClassLoader());
            Class routeManagementClass = Class.forName("RouteManagement", true, urlClassLoader);
            FlightRecorder.instance.insert("RouteManagementFactory", "routeManagementClass: " + routeManagementClass.hashCode());

            Object routeManagementInstance = routeManagementClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("RouteManagementFactory", "routeManagementInstance: " + routeManagementInstance.hashCode());

            routeManagementPort = routeManagementClass.getDeclaredField("port").get(routeManagementInstance);
            FlightRecorder.instance.insert("RouteManagementFactory", "routeManagementPort: " + routeManagementPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return routeManagementPort;
    }
}
