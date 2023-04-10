package fr.an.tests.javafxwhiteapp.ui;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CanvasDrawingView extends DrawingView {

    protected BorderPane component;
    // to add javafx.scene.shape.* objects converted from model
    protected Pane drawingPane;
    protected ToolStateHandler currToolStateHandler = new DefaultSelectToolStateHandler();
    public CanvasDrawingView(DrawingDocMode1 model) {
        super(model);
        component = new BorderPane();
        ToolBar toolBar = new ToolBar();
        component.setTop(toolBar);
        Button resetToolButton = new Button("Reset");
        resetToolButton.setOnAction(e -> onClickToolReset());
        toolBar.getItems().add(resetToolButton);
        Button newLineButton = new Button("+Line");
        newLineButton.setOnAction(e -> onClickToolNewLine());
        toolBar.getItems().add(newLineButton);
        drawingPane = new Pane();
        component.setCenter(drawingPane);
        refreshModelToView();
        drawingPane.setOnMouseEntered(e -> currToolStateHandler.onMouseEnter());
        drawingPane.setOnMouseMoved(e -> currToolStateHandler.onMouseMoved(e));
        drawingPane.setOnMouseClicked(e -> currToolStateHandler.onMouseClicked(e));
    }

    private void onClickToolReset() {
    }
    private void onClickToolNewLine() {
    }

    @Override
    public Node getComponent() {
        return component;
    }

    protected void refreshModelToView() {
        DrawingElement content = model.getContent();
        drawingPane.getChildren().clear();
        JavafxDrawingElementVisitor visitor = new JavafxDrawingElementVisitor();
        content.accept(visitor);
    }

    protected class JavafxDrawingElementVisitor extends DrawingElementVisitor {
        protected void add(Node node) {
            drawingPane.getChildren().add(node);
        }

        @Override
        public void caseText(BaseDrawingElement.TextDrawingElement p) {
            add(new javafx.scene.text.Text(p.pos.x, p.pos.y, p.text));
        }

        @Override
        public void caseLine(BaseDrawingElement.LineDrawingElement p) {
            add(new javafx.scene.shape.Line(p.start.x, p.start.y, p.end.x, p.end.y));
        }

        @Override
        public void caseRect(BaseDrawingElement.RectangleDrawingElement p) {
            add(new javafx.scene.shape.Rectangle(p.upLeft.x, p.upLeft.y,
                    p.downRight.x - p.upLeft.x, p.downRight.y - p.upLeft.y));
        }

        @Override
        public void caseCircle(BaseDrawingElement.CircleDrawingElement p) {
            add(new javafx.scene.shape.Circle(p.center.x, p.center.y, p.radius));
        }

        @Override
        public void caseGroup(BaseDrawingElement.GroupDrawingElement p) {
            for (DrawingElement child : p.elements) {
// *** recurse ***
                child.accept(this);
            }
        }

        @Override
        public void caseOther(DrawingElement p) {
// "not implemented/recognized drawingElement "+ p.getClass().getName();
        }

    }
    protected class DefaultSelectToolStateHandler extends DefaultToolStateHandler {
        @Override
        public void onMouseEnter() {
            drawingPane.setCursor(Cursor.DEFAULT);
        }
    }
}

