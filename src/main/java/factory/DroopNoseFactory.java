package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DroopNoseFactory {
    public static Object build() {

            return Factory.build("DroopNose", Configuration.instance.pathToDroopNoseJavaArchive);
    }
}
