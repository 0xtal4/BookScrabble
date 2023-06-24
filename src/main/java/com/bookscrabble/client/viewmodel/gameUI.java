package com.bookscrabble.client.viewmodel;

import com.bookscrabble.client.model.Host;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class gameUI {
    @FXML
    private GridPane gridPane;
    public void start() throws Exception {
        Label l = new Label("Book Scrabble");
        l.getStyleClass().add("main-label");
        BorderPane p = new BorderPane();
        gridPane = new GridPane();
        gridPane.getStyleClass().add("game-grid");
        Scene scene = new Scene(p,1200, 900);

        scene.getStylesheets().add("com/bookscrabble/client/view/styles.css");

        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++)
            {
                TextField textField = new TextField ();
                    textField.appendText(String.valueOf(Host.getHost().gameBoard.getBonuses()[i][j]));
                gridPane.add(textField, i, j, 1, 1);
            }
        }
        p.setTop(l);
        p.setCenter(gridPane);
        BookScrabble.pStage.setScene(scene);
    }
}
