package factory;

import configuration.Configuration;

public class AirFlowSensorFactory {

    public static Object build() {
        return Factory.build("AirFlowSensorFactory", Configuration.instance.pathToAirFlowSensorJavaArchive);
    }
}

