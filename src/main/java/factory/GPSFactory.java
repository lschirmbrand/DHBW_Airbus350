package factory;

import configuration.Configuration;

public class GPSFactory {
    public static Object build() {
        return Factory.build("GPS", Configuration.instance.pathToGPSJavaArchive);
    }
}
