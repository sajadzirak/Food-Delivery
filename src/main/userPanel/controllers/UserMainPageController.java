package main.userPanel.controllers;

import main.classes.FxmlLoader;
import main.classes.methods;
import main.server.DataBase;
import main.userPanel.UserClient;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class UserMainPageController {
   
    @FXML
    private Label cartIcon;

    @FXML
    private Label exitIcon;

    @FXML
    private VBox leftMenu;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label restaurantsIcon;

    @FXML
    private Label userIcon;


    @FXML
    void cartLabelClicked(MouseEvent event) throws IOException {
        methods.changeOpacity(cartIcon.getId(), leftMenu);
        mainPane.setCenter(new FxmlLoader().getPage(DataBase.userFxmlLoaderPath+"cartPage.fxml"));
    }

    @FXML
    void exitLabelClicked(MouseEvent event) throws IOException {
        methods.changeOpacity(exitIcon.getId(), leftMenu);
        UserClient.toServer.writeObject("Update User");
        UserClient.toServer.writeObject(UserClient.currentUser);
        UserClient.toServer.writeObject("exit");
        UserClient.window.close();
    }

    @FXML
    void restaurantsLabelClicked(MouseEvent event) throws IOException {
        methods.changeOpacity(restaurantsIcon.getId(), leftMenu);
        mainPane.setCenter(new FxmlLoader().getPage(DataBase.userFxmlLoaderPath+"userRestaurantsPage.fxml"));
    }

    @FXML
    void userLabelClicked(MouseEvent event) throws IOException {
        methods.changeOpacity(userIcon.getId(), leftMenu);
        mainPane.setCenter(new FxmlLoader().getPage(DataBase.userFxmlLoaderPath+"userProfilePage.fxml"));
    }

}
