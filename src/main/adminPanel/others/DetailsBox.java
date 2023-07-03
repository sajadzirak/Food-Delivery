package main.adminPanel.others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DetailsBox {
 
    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected Button confirmButton;

    @FXML
    protected GridPane mainGrid;

    @FXML
    protected Button selectImageButton;

    @FXML
    protected Label selectedImageLabel;

    @FXML
    protected ImageView selectedImageView;

    protected File selectedFile;

    @FXML
    private void selectImageButtonClicked() throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("jpg", "*.jpg"),
        new ExtensionFilter("png", "*.png"), new ExtensionFilter("jpeg", "*.jpeg"));
        selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
            InputStream inputStream = new FileInputStream(selectedFile.getAbsolutePath());
            selectedImageLabel.setVisible(false);
            selectedImageView.setImage(new Image(inputStream));
        }
    }
}
