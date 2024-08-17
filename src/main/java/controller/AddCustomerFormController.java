package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private DatePicker DateDateOfBirth;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
     String id = txtId.getText();
     String name = txtName.getText();
     String address = txtAddress.getText();
     String title = cmbTitle.getValue();
        LocalDate dateofbirth = DateDateOfBirth.getValue();
        Customer customer = new Customer(id,name,address,title,dateofbirth);
        System.out.println(customer);
        List<Customer> customerList= DBConnection.getInstance().getConnection();
        customerList.add(customer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Mrs.");
   cmbTitle.setItems(titles);
    }

   

    public void btnViewCustomerOnAction(ActionEvent actionEvent) {
        Stage stage =new Stage();
        try{
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_customer_form.fxml"))));
            stage.show();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        cmbTitle.getScene().getWindow().hide();
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        Stage stage =new Stage();
        try{
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/search_customer_form.fxml"))));
            stage.show();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
