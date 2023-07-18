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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class BillController implements Initializable {
    @FXML
    private TableView<Bill> btable;
    @FXML
    private TableColumn<Bill, Integer> bid;
    @FXML
    private TableColumn<Bill, String> btype;
    @FXML
    private TableColumn<Bill, String> bamount;
    @FXML
    private TableColumn<Bill, String> bdate;
//    public TableColumn bid;
//    public TableColumn btype;
//    public TableColumn bamount;
//    public TableColumn bdate;
    public TextField billType;
    public TextField amount;
    public DatePicker date;

    ObservableList<Bill> billObservableList = FXCollections.observableArrayList();

    private void insertDataIntoDatabase(String bill, String bamount, String date) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "INSERT INTO bills (Bill, Amount, Bill_Date) VALUES (?, ?, ?)";
        try{
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1, bill);
            statement.setString(2, bamount);
            statement.setString(3, date);
            statement.executeUpdate();
            billType.clear();
            amount.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBill(ActionEvent actionEvent) {
        if (!billType.getText().isBlank() && !amount.getText().isBlank()){
            insertDataIntoDatabase(billType.getText(), amount.getText(), String.valueOf(date.getValue()));
        }
    }

    public void resetBill(ActionEvent actionEvent){
        billType.clear();
        amount.clear();
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

    public void removeBill(ActionEvent actionEvent) throws SQLException {
        removeFromDatabase();
    }

    private void removeFromDatabase() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "DELETE FROM bills WHERE id=" + btable.getSelectionModel().getSelectedItem().getId();
        PreparedStatement statement = connectDB.prepareStatement(sql);
        statement.executeUpdate();
        refreshTable();
    }

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

        bid.setCellValueFactory(new PropertyValueFactory<>("id"));
        btype.setCellValueFactory(new PropertyValueFactory<>("bill"));
        bamount.setCellValueFactory(new PropertyValueFactory<>("amt"));
        bdate.setCellValueFactory(new PropertyValueFactory<>("billDate"));
    }

    private void refreshTable() throws SQLException {
        billObservableList.clear();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "SELECT * FROM bills";
        PreparedStatement statement = connectDB.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        try{
            while (resultSet.next()) {
                billObservableList.add(new Bill(
                        resultSet.getInt("id"),
                        resultSet.getString("Bill"),
                        resultSet.getString("Amount"),
                        resultSet.getDate("Bill_Date")));

                btable.setItems(billObservableList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewBill(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-bills.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
