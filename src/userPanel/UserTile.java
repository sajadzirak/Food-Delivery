package userPanel;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserTile extends AnchorPane{
    protected VBox layout;
    protected ImageView imageView;
    protected Label nameLabel, typeLabel, imageLabel;
    protected String textLabelStyle = "-fx-text-fill:#04030f;-fx-font-size:18px;-fx-font-family:Ubuntu;";

    public UserTile(){
        layout = new VBox(20);
        layout.setStyle("-fx-padding: 10px;-fx-background-color:#edfdfb;" +
        "-fx-margin:10px;-fx-border-radius:20;-fx-background-radius:20;");
        layout.setAlignment(Pos.CENTER);
        imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageLabel = new Label();
        imageLabel.setGraphic(imageView);
        imageLabel.setCursor(Cursor.HAND);
        nameLabel = new Label();
        nameLabel.setStyle(textLabelStyle+"-fx-font-weight:700;");
        typeLabel = new Label(textLabelStyle+"-fx-font-weight:500;");
        this.setPrefWidth(350);
        this.setPrefHeight(350);
        this.getChildren().add(layout);
        this.setStyle("-fx-border-radius:20;-fx-background-radius:20;-fx-margin:10px;"+
        "-fx-effect: dropshadow(three-pass-box, -fx-grey, 8, 0, 3, 3);");
    }
}
