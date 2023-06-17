package com.bookscrabble.client.viewmodel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.print.Book;
import java.io.IOException;

public class HostLaunch {
    @FXML
    private Label hostIp;
    // ... other code

    @FXML
    public void initialize() {
        //TODO: find a way to get actual ip
        String ipAddress = "192.168.0.1";
        hostIp.setText("Your IP: " + ipAddress);
    }

    public void handleBackButton(ActionEvent event) throws IOException {
        // Load the previous scene
        BookScrabble.loadScene("../view/launchPage.fxml");
    }
}
