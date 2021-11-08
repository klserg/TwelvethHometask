package org.example.ui.menu;

public interface MenuItem {
    String getName();
    void run();
    default boolean exit() {
        return false;
    }
}
