package fr.an.tests.javafxwhiteapp.ui;

import javafx.scene.input.MouseEvent;

public abstract class ToolStateHandler {
    public abstract void onMouseEntered();
    public abstract void onMouseMove(MouseEvent event);
    public abstract void onMouseClick(MouseEvent event);

    public void onMouseEnter() {
    }

    public void onMouseMoved(MouseEvent e) {
    }

    public void onMouseClicked(MouseEvent e) {
    }
}
