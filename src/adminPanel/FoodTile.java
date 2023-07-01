package adminPanel;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.src.DataBase;
import server.src.Food;

public class FoodTile extends Tile{
    
    private Food food;
    private String restaurantName;
    private int quantity;
    
    public FoodTile(Food f, int quantity, String restaurantName){
        super();
        food = f;
        this.quantity = quantity;
        this.restaurantName = restaurantName;
        File file = new File(food.getFoodImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        nameLabel.setText(food.getFoodName());
        typeLabel.setText(food.getFoodType().name());
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, buttonBox);

        editButton.setOnAction(
            e -> {
                try {
                    adminClient.toServer.writeObject("Get Food");
                    adminClient.toServer.writeObject(restaurantName);
                    adminClient.toServer.writeObject(food);
                    editButtonClicked("editFoodBox.fxml", "Edit Food");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        );
    }

    // private void editButtonClicked() throws IOException {
    //     editBox = new Stage();
    //     Parent root = FXMLLoader.load(getClass().getResource("editFoodBox.fxml"));
    //     editBox.setScene(new Scene(root));
    //     editBox.setTitle("Edit Restaurant");
    //     editBox.initModality(Modality.APPLICATION_MODAL);
    //     editBox.showAndWait();
    // }
    
}
