package main.classes;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader{

    public Pane getPage(String fileName) throws IOException{
        Pane view = new Pane();
        view =  FXMLLoader.load(getClass().getResource(fileName));
        return view;
    }
}