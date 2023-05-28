package adminPanel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ListItem extends AnchorPane{
    ImageView image;
    Label nameLabel;
    Button button;
    public ListItem(){
        ImageView image = new ImageView();
        Label nameLabel = new Label("name");
        Button button = new Button("disable");
        this.getChildren().addAll(image, nameLabel, button);
        this.setStyle("-fx-background-color: #000;");
    }
}
