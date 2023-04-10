package fr.an.tests.javafxwhiteapp.ui;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class TextDrawingView extends DrawingView{
    protected BorderPane component;
    protected TextArea textArea;
    protected Button applyButton;

    protected DrawingModelListener innerListener = new DrawingModelListener() {

        @Override
        public void onModelChange() {
            System.out.println("(from subscribe): model to view change");
            refreshModelToView();
        }
    };

    XStream xstream = createXStream();
    static XStream createXStream() {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Pt", DrawingPt.class);
        xstream.alias("Text", BaseDrawingElement.TextDrawingElement.class);
        xstream.alias("Line", BaseDrawingElement.LineDrawingElement.class);
        xstream.alias("Circle", BaseDrawingElement.CircleDrawingElement.class);
        xstream.alias("Rectangle", BaseDrawingElement.RectangleDrawingElement.class);
        xstream.alias("Group", BaseDrawingElement.GroupDrawingElement.class);
        return xstream;
    }
    public TextDrawingView(DrawingDocMode1 model) {
        super(model);
        this.component = new BorderPane();
        this.textArea = new TextArea();
        component.setCenter(textArea);
        this.applyButton = new Button("Apply");
        applyButton.setOnAction(e -> onClickApply());
        component.setBottom(applyButton);
        refreshModelToView();
        model.addListener(this.innerListener);

    }

    private void onClickApply() {
        System.out.println("apply view to model update");
        String text = textArea.getText();
        DrawingElement content = (DrawingElement) xstream.fromXML(text);
        model.setContent(content); // => fireModelChange ..
    }

    @Override
    public Node getComponent() {
        return component;
    }
    protected void refreshModelToView() {
        DrawingElement content = model.getContent();
        String text = xstream.toXML(content);
        textArea.setText(text);
    }

    private String recursiveElementToText(DrawingElement drawingElement) {
        TextDrawingElementVisitor visitor = new TextDrawingElementVisitor();
        drawingElement.accept(visitor);
        return visitor.result;
    }

}
