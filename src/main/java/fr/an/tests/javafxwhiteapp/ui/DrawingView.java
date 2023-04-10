package fr.an.tests.javafxwhiteapp.ui;

public abstract class DrawingView {
    protected DrawingDocMode1 model;
    public DrawingView(DrawingDocMode1 model) {
        this.model = model;
    }
    public abstract javafx.scene.Node getComponent();

}
