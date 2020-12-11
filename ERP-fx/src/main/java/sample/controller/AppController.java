package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private TableView<?> employeeTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
