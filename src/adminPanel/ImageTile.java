package adminPanel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ImageTile extends AnchorPane{
    ImageView image;
    Label nameLabel;
    Button button;
    public ImageTile(){
        ImageView image = new ImageView();
        Label nameLabel = new Label("name");
        Button button = new Button("disable");
        // this.setStyle("-fx-background-color: #000;");
        this.getChildren().addAll(image, nameLabel, button);
    }
}
