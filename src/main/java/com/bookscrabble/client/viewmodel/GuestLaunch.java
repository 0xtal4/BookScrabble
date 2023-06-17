package com.bookscrabble.client.viewmodel;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GuestLaunch {
    @FXML
    private TextField hostIpField;

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        // Initially, the error message is not visible
        errorLabel.setVisible(false);
    }

    @FXML
    public void tryConnect(ActionEvent event) {
        if(checkIpFormatDisplayError())
        {
            //connect to the host and everything
        }
    }

    private boolean checkIpFormatDisplayError() {
        String ip = hostIpField.getText();
        if (!ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
            // If the IP is invalid, show an error message
            errorLabel.setText("Invalid IP format");
            errorLabel.setVisible(true);

            // Hide the error message after a few seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> errorLabel.setVisible(false));
            pause.play();
            return false;
        }
        return true;
    }

    @FXML
    public void launchGame(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/hostLaunch.fxml"));
        // Load the second scene
        Parent root = loader.load();

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void handleBackButton(ActionEvent event) throws IOException {
        // Load the previous scene
        BookScrabble.loadScene("../view/launchPage.fxml");
    }
}
