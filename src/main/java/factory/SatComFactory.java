package factory;

import configuration.Configuration;

public class SatComFactory {
    public static Object build() {

        return Factory.build("SatCom", Configuration.instance.pathToSatComJavaArchive);
    }
}
