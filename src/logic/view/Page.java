package logic.view;

import java.io.IOException;

import javafx.scene.layout.VBox;

public abstract class Page extends VBox {
	
	protected abstract void createPage() throws IOException;
}
