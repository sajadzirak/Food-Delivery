package main.adminPanel.others;

import java.io.IOException;
import main.adminPanel.AdminClient;
import main.adminPanel.controllers.AdminRestaurantManagementPageController;
import main.classes.FxmlLoader;
import main.classes.Restaurant;
import main.server.DataBase;
import java.util.Optional;

import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class AdminRestaurantTile extends AdminTile {

    private Alert alert;
    private Restaurant restaurant;
    private Button disableButton;

    public AdminRestaurantTile(Restaurant r) {
        super();
        restaurant = r;
        disableButton = new Button("Disable");
        buttonBox.getChildren().add(disableButton);
        disableButton.setCursor(Cursor.HAND);
        imageLabel.setCursor(Cursor.HAND);
        imageLabel.setOnMouseClicked(
                e -> {
                    try {
                        AdminClient.toServer.writeObject("Get Restaurant");
                        AdminClient.toServer.writeObject(restaurant.getName());
                        BorderPane pane = (BorderPane)super.getParent().getParent().getParent().getParent().getParent().getParent();
                        pane.setCenter(new FxmlLoader().getPage(DataBase.adminFxmlLoaderPath+"restaurantFoodManagementPage.fxml"));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
        disableButton.setOnAction(
                e -> {
                    try {
                        disableRestaurant(restaurant, true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
        editButton.setOnAction(
                e -> {
                    try {
                        AdminClient.toServer.writeObject("Get Restaurant");
                        AdminClient.toServer.writeObject(restaurant.getName());
                        editButtonClicked("editRestaurantBox.fxml", "Edit Restaurant");
                        AdminRestaurantManagementPageController.refresh();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
        delButton.setOnAction(
                e -> {
                    try {
                        alert = new Alert(AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Do you really want to delete the restaurant?");
                        Optional<ButtonType> buttonType = alert.showAndWait();
                        if (buttonType.get().equals(ButtonType.OK)) {
                            AdminClient.toServer.writeObject("Delete Restaurant");
                            AdminClient.toServer.writeObject(restaurant);
                            AdminRestaurantManagementPageController.refresh();
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                });
        imageView.setImage(new Image(restaurant.getRestaurantImagePath()));
        this.setOnMouseClicked(
                e -> {
                    try{
                        disableRestaurant(restaurant, false);
                    }catch(Exception e1){
                        e1.printStackTrace();
                    }
                    
                });
        if (restaurant.isDisable()) {
            layout.setDisable(true);
            this.setCursor(Cursor.HAND);
        }
        nameLabel.setText(restaurant.getName());
        typeLabel.setText(restaurant.getrestaurantType().name());
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, buttonBox);
    }

    private void disableRestaurant(Restaurant r, boolean status) throws IOException {
        layout.setDisable(status);
        AdminClient.toServer.writeObject("Set Disable");
        AdminClient.toServer.writeObject(r.getName());
        if(status){
            this.setCursor(Cursor.HAND);
        }
        else{
            this.setCursor(Cursor.DEFAULT);
        }
        AdminClient.toServer.writeObject(status);
    }
}
