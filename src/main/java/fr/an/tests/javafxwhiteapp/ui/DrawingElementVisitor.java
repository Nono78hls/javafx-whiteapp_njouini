package fr.an.tests.javafxwhiteapp.ui;

public abstract class DrawingElementVisitor {
    public abstract void caseText(BaseDrawingElement.TextDrawingElement p);
    public abstract void caseLine(BaseDrawingElement.LineDrawingElement p);
    public abstract void caseRect(BaseDrawingElement.RectangleDrawingElement p);
    public abstract void caseCircle(BaseDrawingElement.CircleDrawingElement p);
    public abstract void caseGroup(BaseDrawingElement.GroupDrawingElement p);
    public abstract void caseOther(DrawingElement p);
}
