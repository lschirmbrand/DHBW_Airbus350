package factory;

import configuration.Configuration;

public class FireDetectorFactory {
    public static Object build() {
        return Factory.build("FireDetector", Configuration.instance.pathToFireDetectorJavaArchive);
    }
}
