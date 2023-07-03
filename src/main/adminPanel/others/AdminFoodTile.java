package main.adminPanel.others;

import main.adminPanel.AdminClient;
import main.adminPanel.controllers.RestaurantFoodManagementPageController;
import main.classes.Food;
import javafx.scene.image.Image;

public class AdminFoodTile extends AdminTile{
    
    private Food food;
    private String restaurantName;
    private int quantity;
    
    public AdminFoodTile(Food f, int quantity, String restaurantName){
        super();
        food = f;
        this.quantity = quantity;
        this.restaurantName = restaurantName;
        imageView.setImage(new Image(food.getFoodImagePath()));
        nameLabel.setText(food.getFoodName());
        typeLabel.setText(food.getFoodType().name());
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, buttonBox);

        editButton.setOnAction(
            e -> {
                try {
                    AdminClient.toServer.writeObject("Get Food");
                    AdminClient.toServer.writeObject(restaurantName);
                    AdminClient.toServer.writeObject(food);
                    editButtonClicked("editFoodBox.fxml", "Edit Food");
                    RestaurantFoodManagementPageController.refresh();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        );
    }
    
}
