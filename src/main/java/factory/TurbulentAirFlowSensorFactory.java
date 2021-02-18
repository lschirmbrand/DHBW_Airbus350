package factory;

import configuration.Configuration;

public class TurbulentAirFlowSensorFactory {
    public static Object build() {
        return Factory.build("TurbulentAirFlowSensor", Configuration.instance.pathToTurbulentAirFlowSensorJavaArchive);
    }
}
