public interface IGear {
    GearType setGearType(String type);
    boolean down();
    boolean up();
    int setBrake();
    int setBrake(int percentage);
    int releaseBrake();

}
