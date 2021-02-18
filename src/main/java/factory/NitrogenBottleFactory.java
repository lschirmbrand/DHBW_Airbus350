package factory;

import configuration.Configuration;

public class NitrogenBottleFactory {
    public static Object build() {

        return Factory.build("NitrogenBottle", Configuration.instance.pathToNitrogenBottleJavaArchive);
    }
}
