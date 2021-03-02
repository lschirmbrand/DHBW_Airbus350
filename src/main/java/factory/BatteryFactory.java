package factory;

import configuration.Configuration;

public class BatteryFactory {

    public static Object build() {
        return Factory.build("BatteryFactory", Configuration.instance.pathToBatteryJavaArchive);
    }
}

