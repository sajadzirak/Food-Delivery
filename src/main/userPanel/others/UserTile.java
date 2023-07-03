package main.userPanel.others;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public abstract class UserTile extends AnchorPane{
    protected VBox layout;
    protected ImageView imageView;
    protected Label nameLabel, typeLabel, imageLabel;

    public UserTile(){
        layout = new VBox(20);
        layout.setId("layout");
        layout.setAlignment(Pos.CENTER);
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
}
