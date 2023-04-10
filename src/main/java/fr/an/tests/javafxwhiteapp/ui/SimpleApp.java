package fr.an.tests.javafxwhiteapp.ui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimpleApp extends Application {

    @Override
    public void start(Stage stage) {
    	BorderPane mainBorderPanel = new BorderPane();
    	
    	VBox menuAndToolbar = new VBox();
    	{ // MenuBar with "File" menu
            MenuBar mb = new MenuBar();
            menuAndToolbar.getChildren().add(mb);

            Menu fileMenu = new Menu("File");
            mb.getMenus().add(fileMenu);
            MenuItem openMenuItem = new MenuItem("Open");
            fileMenu.getItems().add(openMenuItem);
            MenuItem saveMenuItem = new MenuItem("Save");
            fileMenu.getItems().add(saveMenuItem);
    	}
    	
    	{ // button Toolbar
	    	ToolBar toolBar = new ToolBar();
	    	menuAndToolbar.getChildren().add(toolBar);
    		
	    	Button button1 = new Button("button1");
	    	toolBar.getItems().add(button1);
    	}
    	mainBorderPanel.setTop(menuAndToolbar);



		DrawingDocMode1 model = new DrawingDocMode1();
		DrawingElement content = createSimpleDrawing();
		model.setContent(content);
		{ // SplitPane( view1 | view2 )
			TextDrawingView view1 = new TextDrawingView(model);
			Node view1Comp = view1.getComponent();
			CanvasDrawingView view2 = new CanvasDrawingView(model);
			Node view2Comp = view2.getComponent();
			SplitPane splitViewPane = new SplitPane(view1Comp, view2Comp);
			mainBorderPanel.setCenter(splitViewPane);


		}

    	
		Scene scene = new Scene(mainBorderPanel, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

	public BaseDrawingElement.GroupDrawingElement createSimpleDrawing() {
		BaseDrawingElement.TextDrawingElement text = new BaseDrawingElement.TextDrawingElement("Hello", new DrawingPt(100, 100));
		BaseDrawingElement.LineDrawingElement line = new BaseDrawingElement.LineDrawingElement(new DrawingPt(100, 130),
				new DrawingPt(200, 230));
		BaseDrawingElement.RectangleDrawingElement rectangle = new BaseDrawingElement.RectangleDrawingElement(
				new DrawingPt(100, 300), new DrawingPt(200, 350));
		BaseDrawingElement.CircleDrawingElement circle = new BaseDrawingElement.CircleDrawingElement(new DrawingPt(150, 400), 45);
		BaseDrawingElement.GroupDrawingElement res = new BaseDrawingElement.GroupDrawingElement();
		res.addAll(text, line, rectangle, circle);
		return res;
	}


}
