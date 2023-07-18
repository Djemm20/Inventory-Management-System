package com.example.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewVendorController implements Initializable {
    @FXML
    private TableView<Vendor> vtable;
    @FXML
    private TableColumn<Vendor, Integer> vid;
    @FXML
    private TableColumn<Vendor, String> vfname;
    @FXML
    private TableColumn<Vendor, String> vlname;
    @FXML
    private TableColumn<Vendor, String> vpnum;
    @FXML
    private TableColumn<Vendor, String> vemail;
    @FXML
    private TableColumn<Vendor, String> vdate;
    @FXML
    private TableColumn<Vendor, String> vcontact;
    @FXML
    private Label welcomeText;

    ObservableList<Vendor> vendorObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDate() throws SQLException {
        refreshTable();

        vid.setCellValueFactory(new PropertyValueFactory<>("id"));
        vfname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        vlname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        vpnum.setCellValueFactory(new PropertyValueFactory<>("pnum"));
        vemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        vdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        vcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void refreshTable() throws SQLException {
        vendorObservableList.clear();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "SELECT * FROM vendor";
        PreparedStatement statement = connectDB.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        try{
            while (resultSet.next()) {
                vendorObservableList.add(new Vendor(
                        resultSet.getInt("id"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("Phone_Number"),
                        resultSet.getString("Email_Address"),
                        resultSet.getDate("Start_Date"),
                        resultSet.getString("Emergency_Contact")));

                vtable.setItems(vendorObservableList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void addVendor(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-vendor.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewIssuedGoods(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-issued-goods.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void removeVendorOnAction(ActionEvent actionEvent) throws SQLException {
        removeFromDatabase();
    }

    private void removeFromDatabase() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "DELETE FROM vendor WHERE id=" + vtable.getSelectionModel().getSelectedItem().getId();
        PreparedStatement statement = connectDB.prepareStatement(sql);
        statement.executeUpdate();
        refreshTable();
    }

    public void issueGoodsOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("issued-goods.fxml")));
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
