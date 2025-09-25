package edu.ccrm.service;

// Interface example
public interface Persistable {
    void save();
    void load();
    default void backup() {
        System.out.println("Backing up data...");
    }
}