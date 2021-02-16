package factory;

import configuration.Configuration;

public class OxygenBottleFactory {
    public static Object build() {

        return Factory.build("OxygenBottle", Configuration.instance.pathToOxygenBottleJavaArchive);
    }
}
