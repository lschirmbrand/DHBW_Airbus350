package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class CostOptimizerFactory {
    public static Object build() {
        Object costOptimizerPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToCostOptimizer).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, CostOptimizerFactory.class.getClassLoader());
            Class costOptimizerClass = Class.forName("CostOptimizer", true, urlClassLoader);
            FlightRecorder.instance.insert("CostOptimizerFactory", "costOptimizerFactory: " + costOptimizerClass.hashCode());

            Object costOptimizerInstance = costOptimizerClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("CostOptimizerFactory", "costOptimizerInstance: " + costOptimizerInstance.hashCode());

            costOptimizerPort = costOptimizerClass.getDeclaredField("port").get(costOptimizerInstance);
            FlightRecorder.instance.insert("CostOptimizerFactory", "costOptimizerPort: " + costOptimizerPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return costOptimizerPort;
    }
}
