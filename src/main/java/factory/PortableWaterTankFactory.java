package factory;

import configuration.Configuration;

public class PortableWaterTankFactory {
    public static Object build() {

        return Factory.build("PortableWaterTank", Configuration.instance.pathToPortableWaterTankJavaArchive);
    }
}
