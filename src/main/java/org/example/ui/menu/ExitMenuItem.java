package org.example.ui.menu;

public class ExitMenuItem extends BasicMenuItem {
    public ExitMenuItem(String name) {
        super(name);
    }
    @Override
    public void run() {

    }

    @Override
    public boolean exit() {
        return true;
    }
}
