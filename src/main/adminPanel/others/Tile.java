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

public class Tile extends AnchorPane{
    
    protected static Stage editBox;
    protected String buttonStyle = "-fx-background-color: #ff0022;-fx-text-fill:#fff;-fx-border-radius:20;" +
    "-fx-background-radius:20;-fx-effect: dropshadow(three-pass-box, -fx-grey, 8, 0, 3, 3);";
    protected String textLabelStyle = "-fx-text-fill:#04030f;-fx-font-size:18px;-fx-font-family:Ubuntu;";
    protected VBox layout;
    protected HBox buttonBox;
    protected ImageView imageView;
    protected Label nameLabel, typeLabel, imageLabel;
    protected Button editButton, delButton;
    
    public Tile(){
        layout = new VBox(20);
        layout.setStyle("-fx-padding: 10px;-fx-background-color:#edfdfb;" +
        "-fx-margin:10px;-fx-border-radius:20;-fx-background-radius:20;");
        buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        editButton = new Button("Edit");
        delButton = new Button("Delete");
        buttonBox.getChildren().addAll(editButton, delButton);
        editButton.setCursor(Cursor.HAND);
        delButton.setCursor(Cursor.HAND);
        editButton.setStyle(buttonStyle);
        delButton.setStyle(buttonStyle);
        imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageLabel = new Label();
        imageLabel.setGraphic(imageView);
        nameLabel = new Label();
        nameLabel.setStyle(textLabelStyle+"-fx-font-weight:700;");
        typeLabel = new Label();
        typeLabel.setStyle(textLabelStyle+"-fx-font-weight:500;");
        this.setPrefWidth(350);
        this.setPrefHeight(350);
        this.getChildren().add(layout);
        this.setStyle("-fx-border-radius:20;-fx-background-radius:20;-fx-margin:10px;"+
        "-fx-effect: dropshadow(three-pass-box, -fx-grey, 8, 0, 3, 3);");
    }

    protected void editButtonClicked(String fxmlName, String title) throws IOException {
        editBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
        editBox.setScene(new Scene(root));
        editBox.setTitle(title);
        editBox.initModality(Modality.APPLICATION_MODAL);
        editBox.showAndWait();
    }
}
