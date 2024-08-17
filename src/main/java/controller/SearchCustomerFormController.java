package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.util.List;

public class SearchCustomerFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tableCustomers;

    @FXML
    private JFXTextField txtSearchName;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Customer selectedCustomer = tableCustomers.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            // Confirm deletion with the user
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you want to delete this customer?");
            alert.setContentText("Customer: " + selectedCustomer.getName());

            if (alert.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
                // Remove the customer from the database (DBConnection's list)
                DBConnection.getInstance().getConnection().remove(selectedCustomer);
                // Refresh the table view
                tableCustomers.getItems().remove(selectedCustomer);
            }
        } else {
            // If no customer is selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
        }
    }


    @FXML
    void btnExitOnAction(ActionEvent event) {
        txtSearchName.getScene().getWindow().hide();
    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
String searchName = txtSearchName.getText().toLowerCase();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        ObservableList<Customer> filteredCustomers = FXCollections.observableArrayList();
        for(Customer customer : DBConnection.getInstance().getConnection()){
            if(customer.getName().toLowerCase().contains(searchName)){
                filteredCustomers.add(customer);
                System.out.println(customer);
            }
        }
        tableCustomers.setItems(filteredCustomers);
    }

}
