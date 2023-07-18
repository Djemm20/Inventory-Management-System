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

public class IssueGoodsController {
    public Text issueGoodsLabel;
    public TextField iname;
    public ChoiceBox icategory;
    public TextField icost;
    public TextField isell;
    public TextField iquantity;
    public DatePicker iissued;
    public Button issueButton;
    public Button resetButton;
    public Button issuedGoodsButton;

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
    public void resetButtonOnAction(ActionEvent actionEvent){
        iname.clear();
        iquantity.clear();
        icost.clear();
        isell.clear();
    }

    public void issuedGoodsButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("issued-goods.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void issueButtonOnAction(ActionEvent actionEvent) {
        if (!iname.getText().isBlank() && icategory.getValue() != null){
            insertDataIntoDatabase(iname.getText(), (String) icategory.getValue(), iquantity.getText(), icost.getText(), isell.getText(), String.valueOf(iissued.getValue()));
        } else {
            issueGoodsLabel.setText("Please enter name and category of product");
        }
    }

    private void insertDataIntoDatabase(String name, String category, String quantity, String cost, String sell, String date) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "INSERT INTO issuegoods (Goods_Name, Category, Quantity, Cost_Price, Selling_Price, Issued_On) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setString(3, quantity);
            statement.setString(4, cost);
            statement.setString(5, sell);
            statement.setString(6, date);
            statement.executeUpdate();
            issueGoodsLabel.setText("Data inserted successfully.");
            iname.clear();
            iquantity.clear();
            icost.clear();
            isell.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
