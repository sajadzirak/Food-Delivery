package adminPanel;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.src.DataBase;
import server.src.Restaurant;

public class RestaurantTile extends Tile {
    
    private Restaurant restaurant;
    private Button disableButton;

    public RestaurantTile(Restaurant r) {
        super();
        restaurant = r;
        File file = new File(restaurant.getRestaurantImagePath());
        disableButton = new Button("Disable");
        buttonBox.getChildren().add(disableButton);
        disableButton.setCursor(Cursor.HAND);
        imageLabel.setOnMouseClicked(
            e -> {
                try{
                    adminClient.toServer.writeObject("Get Restaurant");
                    adminClient.toServer.writeObject(restaurant.getName());                    
                    adminMainPageController.mainPaneCopy.setCenter(new FxmlLoader().getPage("restaurantFoodManagementPage.fxml"));
                }catch(Exception e1){
                    e1.printStackTrace();
                }
            }
        );
        disableButton.setOnAction(
                e -> {
                    try {
                        disableButtonClicked(restaurant);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
        editButton.setOnAction(
            e -> {
                try{
                    adminClient.toServer.writeObject("Get Restaurant");
                    adminClient.toServer.writeObject(restaurant.getName());
                    editButtonClicked();
                }catch(Exception e1){
                    e1.printStackTrace();
                }
            }
        );
        imageView.setImage(new Image(DataBase.imageAbsolutePath + file.getName()));
        this.setOnMouseClicked(
            e->{
                layout.setDisable(false);
                restaurant.setDisable(false);
                this.setCursor(Cursor.DEFAULT);
            }
        );
        if(restaurant.isDisable()){
            layout.setDisable(true);
            this.setCursor(Cursor.HAND);
        }
        disableButton.setStyle("-fx-background-color: #ff0022;-fx-text-fill:#fff;-fx-border-radius:20;" +
                "-fx-background-radius:20;-fx-effect: dropshadow(three-pass-box, -fx-grey, 8, 0, 3, 3);");
        nameLabel.setText(restaurant.getName());
        typeLabel.setText(restaurant.getrestaurantType().name());
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, buttonBox);
    }

    private void disableButtonClicked(Restaurant r) throws IOException {
        layout.setDisable(true);
        r.setDisable(true);
        this.setCursor(Cursor.HAND);
    }

    private void editButtonClicked() throws IOException{
        editBox = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("editRestaurantBox.fxml"));
        editBox.setScene(new Scene(root));
        editBox.setTitle("Edit Restaurant");
        // adminClient.toServer.writeObject("Get Restaurant");
        // adminClient.toServer.writeObject(restaurant.getName());
        editBox.initModality(Modality.APPLICATION_MODAL);
        editBox.showAndWait();
    }
}
