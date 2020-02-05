package logic.view;

import java.io.IOException;

import javafx.scene.layout.VBox;

public abstract class Page extends VBox {
	public abstract void createPage() throws IOException;
}
