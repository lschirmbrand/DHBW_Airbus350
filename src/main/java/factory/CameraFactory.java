package factory;

import configuration.Configuration;

public class CameraFactory {
    public static Object build() {
        return Factory.build("Camera", Configuration.instance.pathToCameraJavaArchive);
    }
}
