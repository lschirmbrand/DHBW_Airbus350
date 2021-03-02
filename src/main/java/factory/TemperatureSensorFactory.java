package factory;

import configuration.Configuration;

public class TemperatureSensorFactory {

    public static Object build() {
        return Factory.build("TemperatureSensorFactory", Configuration.instance.pathToTemperatureSensorJavaArchive);
    }
}

