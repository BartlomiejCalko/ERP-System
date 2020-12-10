package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    public void initialize(URL location, ResourceBundle resources) {

        exitButton.setOnAction((x) -> {
            System.out.println("exit button clicked. Action with lambda");
        });

    }


}