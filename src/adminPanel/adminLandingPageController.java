package adminPanel;

import javafx.scene.layout.TilePane;

public class adminLandingPageController {
    public TilePane tilePane;
    public ListItem listItem;
    public void addButton(){
        listItem = new ListItem();
        tilePane.getChildren().add(listItem);
        System.out.println("clicked");
    }
}
