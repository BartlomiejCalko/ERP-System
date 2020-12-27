package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.dto.EmployeeDto;
import sample.rest.EmployeeRestClient;
import sample.table.EmployeeTableModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeeController implements Initializable {

    private static final String ADD_EMPLOYEE_FXML = "/fxml/addEmployee.fxml";
    private final EmployeeRestClient employeeRestClient;

    @FXML
    private TableView<EmployeeTableModel> employeeTableView;

    @FXML
    private Button addButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    public EmployeeController() {
        employeeRestClient = new EmployeeRestClient();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAddEmployeeButton();
        initializeTableView();
    }

    private void initializeAddEmployeeButton() {
        addButton.setOnAction((x) -> {
            Stage addEmployeeStage = new Stage();
            addEmployeeStage.initStyle(StageStyle.UNDECORATED);
            addEmployeeStage.initModality(Modality.APPLICATION_MODAL);
            try {
                Parent addEmployeeParent = FXMLLoader.load(getClass().getResource(ADD_EMPLOYEE_FXML));
                Scene scene = new Scene(addEmployeeParent, 500, 400);
                addEmployeeStage.setScene(scene);
                addEmployeeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeTableView() {
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("firstName"));

        TableColumn lastNameColumn = new TableColumn("Last Name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("lastName"));

        TableColumn salary = new TableColumn("Salary");
        salary.setMinWidth(100);
        salary.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("salary"));

        employeeTableView.getColumns().addAll(firstNameColumn, lastNameColumn, salary);

        ObservableList<EmployeeTableModel> data = FXCollections.observableArrayList();
        loadEmployeeData(data);
        employeeTableView.setItems(data);
    }

    private void loadEmployeeData(ObservableList<EmployeeTableModel> data) {
        Thread thread = new Thread(() -> {
            List<EmployeeDto> employees = employeeRestClient.getEmployees();
            data.addAll(employees.stream().map(EmployeeTableModel::of).collect(Collectors.toList()));
        });
        thread.start();
    }


}
