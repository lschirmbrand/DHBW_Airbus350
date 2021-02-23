package base;

import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("unused")
public class PrimaryFlightDisplayEntry {
    private final SimpleStringProperty attribute;
    private final SimpleStringProperty value;

    public PrimaryFlightDisplayEntry(String attribute, String value) {
        this.attribute = new SimpleStringProperty(attribute);
        this.value = new SimpleStringProperty(value);
    }

    public String getAttribute() {
        return attribute.get();
    }

    public void setAttribute(String attribute) {
        this.attribute.set(attribute);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String toString() {
        return (attribute.get() + " - " + value.get());
    }
}