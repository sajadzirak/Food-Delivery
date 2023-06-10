package adminPanel;

import java.io.File;

import javafx.scene.image.Image;
import server.src.DataBase;
import server.src.Food;

public class FoodTile extends Tile{
    
    private Food food;
    
    public FoodTile(Food f){
        super();
        food = f;
        File file = new File(food.getFoodImagePath());
        imageView.setImage(new Image(DataBase.imageAbsolutePath+file.getName()));
        nameLabel.setText(food.getFoodName());
        typeLabel.setText(food.getFoodType().name());
        layout.getChildren().addAll(imageLabel, nameLabel, typeLabel, buttonBox);
    }
    
}
