package factory;

import configuration.Configuration;

public class OxygenSensorFactory {
    public static Object build() {
        return Factory.build("OxygenSensor", Configuration.instance.pathToOxygenSensorJavaArchive);
    }
}
