package gribanov.lab2.utils;

public enum EventType {
    INSERT_TYPE("insert"), UPDATE_TYPE("update"), DELETE_TYPE("delete"), SELECT_TYPE("select");

    private String label;

    EventType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
