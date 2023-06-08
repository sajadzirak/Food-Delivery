package adminPanel;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.src.DataBase;
import server.src.Restaurant;
import server.src.Server;

public class RestaurantTile extends AnchorPane {
    VBox layout;
    HBox buttonBox;
    ImageView imageView;
    Label imageLabel;
    Label nameLabel, typeLabel;
    Button disableButton, editButton;

    public RestaurantTile(Restaurant r) {
        File file = new File(r.getRestaurantImagePath());
        buttonBox = new HBox(20);
        editButton = new Button("Edit");
        disableButton = new Button("Disable");
        buttonBox.getChildren().addAll(editButton, disableButton);
        buttonBox.setAlignment(Pos.CENTER);
        disableButton.setCursor(Cursor.HAND);
        editButton.setCursor(Cursor.HAND);
        disableButton.setOnAction(
                e -> {
                    try {
                        disableButtonClicked(r);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        imageView = new ImageView(new Image(DataBase.imageAbsolutePath + file.getName()));
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageLabel = new Label();
        imageLabel.setGraphic(imageView);
        imageLabel.setCursor(Cursor.HAND);
        this.setOnMouseClicked(
            e->{
                layout.setDisable(false);
                r.setDisable(false);
                this.setCursor(Cursor.DEFAULT);
            }
        );
        if(r.isDisable()){
            layout.setDisable(true);
            this.setCursor(Cursor.HAND);
        }
        nameLabel = new Label(r.getName());
        nameLabel.setStyle("-fx-text-fill:#04030f;-fx-font-size:18px;-fx-font-family:Ubuntu;"+
        "-fx-font-weight:700;");
        typeLabel = new Label(r.getrestaurantType().name());
        typeLabel.setStyle("-fx-text-fill:#04030f;-fx-font-size:15px;-fx-font-family:Ubuntu;"+
        "-fx-font-weight:500;");
        editButton.setStyle("-fx-background-color: #ff0022;-fx-text-fill:#fff;-fx-border-radius:20;" +
        "-fx-background-radius:20;-fx-effect: dropshadow(three-pass-box, -fx-grey, 8, 0, 3, 3);");
        disableButton.setStyle("-fx-background-color: #ff0022;-fx-text-fill:#fff;-fx-border-radius:20;" +
                "-fx-background-radius:20;-fx-effect: dropshadow(three-pass-box, -fx-grey, 8, 0, 3, 3);");
        layout.setStyle("-fx-padding: 10px;-fx-background-color:#edfdfb;" +
                "-fx-margin:10px;-fx-border-radius:20;-fx-background-radius:20;");
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, buttonBox);
        this.setPrefWidth(350);
        this.setPrefHeight(350);
        this.getChildren().add(layout);
        this.setStyle("-fx-border-radius:20;-fx-background-radius:20;-fx-margin:10px;"+
        "-fx-effect: dropshadow(three-pass-box, -fx-grey, 8, 0, 3, 3);");
    }

    private void disableButtonClicked(Restaurant r) throws IOException {
        layout.setDisable(true);
        r.setDisable(true);
        this.setCursor(Cursor.HAND);
    }

    private void editButtonClicked() throws IOException{
        Stage editBox;
        editBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addRestaurantBox.fxml"));
        editBox.setScene(new Scene(root));
        editBox.setTitle("Add Restaurant");
        editBox.initModality(Modality.APPLICATION_MODAL);
        editBox.showAndWait();
    }
}
