package factory;

import configuration.Configuration;

public class DroopNoseFactory {
    public static Object build() {

        return Factory.build("DroopNose", Configuration.instance.pathToDroopNoseJavaArchive);
    }
}
