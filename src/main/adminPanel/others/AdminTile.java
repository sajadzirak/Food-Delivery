package main.adminPanel.others;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.server.DataBase;

public  abstract class AdminTile extends AnchorPane{
    
    protected Stage editBox;
    protected VBox layout;
    protected HBox buttonBox;
    protected ImageView imageView;
    protected Label nameLabel, typeLabel, imageLabel;
    protected Button editButton, delButton;
    
    public AdminTile(){
        layout = new VBox(20);
        layout.setId("layout");
        buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        editButton = new Button("Edit");
        delButton = new Button("Delete");
        buttonBox.getChildren().addAll(editButton, delButton);
        editButton.setCursor(Cursor.HAND);
        delButton.setCursor(Cursor.HAND);
        imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageLabel = new Label();
        imageLabel.setGraphic(imageView);
        nameLabel = new Label();
        nameLabel.setId("nameLabel");
        typeLabel = new Label();
        typeLabel.setId("typeLabel");
        this.setPrefWidth(350);
        this.setPrefHeight(350);
        this.getChildren().add(layout);
        this.setId("tile");
        this.getStylesheets().addAll("resources/styles/variables.css", "resources/styles/generalStyle.css",
        "resources/styles/tileStyle.css");
    }

    protected void editButtonClicked(String fxmlName, String title) throws IOException {
        editBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(DataBase.adminViewPath+fxmlName));
        editBox.setScene(new Scene(root));
        editBox.setTitle(title);
        editBox.initModality(Modality.APPLICATION_MODAL);
        editBox.setResizable(false);
        editBox.showAndWait();
    }
}
