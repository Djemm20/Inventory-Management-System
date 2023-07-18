package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

public class AddGoodsController {
    public Text addGoodsLabel;
    public TextField gname;
    public ChoiceBox gcategory;
    public TextField gcost;
    public TextField gsell;
    public TextField gquantity;
    public DatePicker gstocked;
    public Button addButton;
    public Button resetButton;
    public Button goodsButton;

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

    public void goodsButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-goods.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void insertDataIntoDatabase(String fname, String lname, String num, String email, String date, String contact) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "INSERT INTO goods (Goods_Name, Category, Quantity, Cost_Price, Selling_Price, Stocked_On) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, num);
            statement.setString(4, email);
            statement.setString(5, date);
            statement.setString(6, contact);
            statement.executeUpdate();
            addGoodsLabel.setText("Data inserted successfully.");
            gname.clear();
            gquantity.clear();
            gcost.clear();
            gsell.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
        if (!gname.getText().isBlank() && gcategory.getValue() != null){
            insertDataIntoDatabase(gname.getText(), (String) gcategory.getValue(), gquantity.getText(), gcost.getText(), gsell.getText(), String.valueOf(gstocked.getValue()));
        } else {
            addGoodsLabel.setText("Please enter name and category of product");
        }
    }

    public void resetButtonOnAction(ActionEvent actionEvent){
        gname.clear();
        gquantity.clear();
        gcost.clear();
        gsell.clear();
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
