package main.adminPanel.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.adminPanel.AdminClient;
import main.classes.FxmlLoader;
import main.classes.methods;
import main.server.DataBase;

public class AdminMainPageController implements Initializable{
    
    @FXML
    private Label exitIcon;
    
    @FXML
    private Label homeIcon;
    
    @FXML
    private VBox leftMenu;
    
    @FXML
    private BorderPane mainPane;

    @FXML
    private Label restaurantsIcon;

    @FXML
    void exitLabelClicked(MouseEvent event) throws IOException {
        methods.changeOpacity("exitIcon", leftMenu);
        AdminClient.toServer.writeObject("exit");
        AdminClient.window.close();
    }

    @FXML
    void homeLabelClicked(MouseEvent event) throws IOException {
        methods.changeOpacity("homeIcon", leftMenu);
        mainPane.setCenter(new FxmlLoader().getPage(DataBase.adminFxmlLoaderPath+"adminWelcomePage.fxml"));
    }

    @FXML
    void restaurantsLabelClicked(MouseEvent event) throws IOException {
        methods.changeOpacity("restaurantsIcon", leftMenu);
        mainPane.setCenter(new FxmlLoader().getPage(DataBase.adminFxmlLoaderPath+"adminRestaurantManagementPage.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainPane.setCenter(new FxmlLoader().getPage(DataBase.adminFxmlLoaderPath+"adminWelcomePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
