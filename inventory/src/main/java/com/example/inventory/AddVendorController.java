package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class AddVendorController {
    public TextField vfname;
    public TextField vlname;
    public TextField vpnum;
    public TextField vemail;
    public DatePicker vdate;
    public TextField vcontact;
    public Text addVendorLabel;
    public Button resetButton;
    public Button addButton;
    public Button vendorButton;

    private void insertDataIntoDatabase(String fname, String lname, String num, String email, String date, String contact) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "INSERT INTO vendor (First_Name, Last_Name, Phone_Number, Email_Address, Start_Date, Emergency_Contact) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, num);
            statement.setString(4, email);
            statement.setString(5, date);
            statement.setString(6, contact);
            statement.executeUpdate();
            System.out.println("Data inserted successfully.");
            vfname.clear();
            vlname.clear();
            vpnum.clear();
            vemail.clear();
            vcontact.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
        if (!vfname.getText().isBlank() && !vlname.getText().isBlank()){
            insertDataIntoDatabase(vfname.getText(), vlname.getText(), vpnum.getText(), vemail.getText(), String.valueOf(vdate.getValue()), vcontact.getText());
        } else {
            addVendorLabel.setText("Please enter your name and start date");
        }
    }

    public void resetButtonOnAction(ActionEvent actionEvent){
        vfname.clear();
        vlname.clear();
        vpnum.clear();
        vemail.clear();
        vcontact.clear();
    }

    public void vendorButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-vendor.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewVendorOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-vendor.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addGoodsOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-goods.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewGoodsOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-goods.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void issueGoodsOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("issued-goods.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewIssueGoods(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-issued-goods.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewBills(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-bills.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}