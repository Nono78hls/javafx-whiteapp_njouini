package fr.an.tests.javafxwhiteapp.ui;

public class TextDrawingElementVisitor extends DrawingElementVisitor{
    String result;
    @Override
    public void caseText(BaseDrawingElement.TextDrawingElement p) {
        result = "Text(" + p.pos + ",'" + p.text + "')";
    }

    @Override
    public void caseLine(BaseDrawingElement.LineDrawingElement p) {
        result = "Line(" + p.start + ", " + p.end + ")";
    }

    @Override
    public void caseRect(BaseDrawingElement.RectangleDrawingElement p) {
        result = "Rect(" + p.upLeft + ", " + p.downRight + ")";
    }

    @Override
    public void caseCircle(BaseDrawingElement.CircleDrawingElement p) {
        result = "Circle(" + p.center + ", " + p.radius + ")";
    }

    @Override
    public void caseGroup(BaseDrawingElement.GroupDrawingElement p) {
        StringBuilder sb = new StringBuilder();
        sb.append("Group[\n");
        for(DrawingElement child: p.elements) {

            child.accept(this);
            sb.append(result);
            sb.append("\n");
        }
        sb.append("]");
        result = sb.toString();
    }

    @Override
    public void caseOther(DrawingElement p) {
        result = "not implemented/recognized drawingElement " + p.getClass().getName();
    }
}
