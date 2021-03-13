public interface ILeftNavigationLight {
    String version();

    boolean on();

    boolean off();

    LightType setLightType(String type);

    Position setPosition(String position);
}