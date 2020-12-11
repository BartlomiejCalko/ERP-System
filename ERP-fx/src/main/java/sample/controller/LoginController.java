package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.dto.OperatorCredentialsDTO;
import sample.factory.PopupFactory;
import sample.rest.Authenticator;
import sample.rest.AuthenticatorImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private PopupFactory popupFactory;
    private Authenticator authenticator;

    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private Button exitButton;
    @FXML
    private Button loginButton;

    public LoginController() {
        popupFactory = new PopupFactory();
        authenticator = new AuthenticatorImpl();
    }

    public void initialize(URL location, ResourceBundle resources) {
        initializedExitButton();
        initializedLoginButton();
    }

    private void initializedLoginButton() {
        loginButton.setOnAction((x) -> {
            performAuthentication();
        });
    }

    private void performAuthentication() {
        Stage waitingPopup = popupFactory.createWaitingPopup("Connecting to server...");
        waitingPopup.show();
        String login = loginTextField.getText();
        String password = passwordTextField.getText();
        OperatorCredentialsDTO dto = new OperatorCredentialsDTO();
        dto.setLogin(login);
        dto.setPassword(password);
        authenticator.authenticate(dto, (authenticationResult) -> {
            Platform.runLater(() -> {
                waitingPopup.close();
                System.out.println("authenticationResult: " + authenticationResult.isAuthenticated() + ", authenticationResult: " + authenticationResult.toString());
            });
        });
    }

    private void initializedExitButton() {
        exitButton.setOnAction((x) -> {
            getStage().close();
        });
    }

    private Stage getStage() {
        return (Stage) loginAnchorPane.getScene().getWindow();
    }




}