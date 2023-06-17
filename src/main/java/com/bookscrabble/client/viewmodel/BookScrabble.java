package com.bookscrabble.client.viewmodel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class BookScrabble extends Application {

    private static StackPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        rootLayout = new StackPane();
        Scene scene = new Scene(rootLayout, 800, 600); // You can specify size here or let it be resized later

        primaryStage.setTitle("Scramble Game");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        // Load the initial scene
        loadScene("../view/launchPage.fxml");
    }
    public static void loadScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BookScrabble.class.getResource(fxml));
        Parent sceneRoot = loader.load();

        // Remove the previous root from the root layout and add the new one
        rootLayout.getChildren().clear();
        rootLayout.getChildren().add(sceneRoot);
    }

    public static void main(String[] args) {
        launch(args);
    }

}