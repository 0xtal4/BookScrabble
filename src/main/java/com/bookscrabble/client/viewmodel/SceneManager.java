package com.bookscrabble.client.viewmodel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * responsible for managing the movement between the application's scenes
 */
public class SceneManager {
    @FXML
    private TextField hostNameField;
    @FXML
    private TextField joinNameField;
    @FXML
    private TextField joinIpField;

    @FXML
    public void startGameAsHost(ActionEvent event) throws Exception {
        BookScrabble.loadScene("../view/hostLaunch.fxml");
    }

    @FXML
    public void connectToHost(ActionEvent event) throws Exception {
        BookScrabble.loadScene("../view/guestLaunch.fxml");
    }
}
