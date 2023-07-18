package com.example.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class ViewIssuedGoodsController implements Initializable {
    public TableColumn<IssuedGoods, Integer> iid;
    public TableColumn<IssuedGoods, String> iname;
    public TableColumn<IssuedGoods, String> icategory;
    public TableColumn<IssuedGoods, String> iquantity;
    public TableColumn<IssuedGoods, String> icost;
    public TableColumn<IssuedGoods, String> isell;
    public TableColumn<IssuedGoods, String> idate;
    public TableView<IssuedGoods> itable;

    ObservableList<IssuedGoods> issuedGoodsObservableList = FXCollections.observableArrayList();

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

        iid.setCellValueFactory(new PropertyValueFactory<>("id"));
        iname.setCellValueFactory(new PropertyValueFactory<>("name"));
        icategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        iquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        icost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        isell.setCellValueFactory(new PropertyValueFactory<>("sell"));
        idate.setCellValueFactory(new PropertyValueFactory<>("issued"));
    }

    private void refreshTable() throws SQLException {
        issuedGoodsObservableList.clear();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "SELECT * FROM issuegoods";
        PreparedStatement statement = connectDB.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        try{
            while (resultSet.next()) {
                issuedGoodsObservableList.add(new IssuedGoods(
                        resultSet.getInt("id"),
                        resultSet.getString("Goods_Name"),
                        resultSet.getString("Category"),
                        resultSet.getString("Quantity"),
                        resultSet.getString("Cost_Price"),
                        resultSet.getString("Selling_Price"),
                        resultSet.getDate("Issued_On")));

                itable.setItems(issuedGoodsObservableList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGoodsOnAction(ActionEvent actionEvent) throws SQLException {
        removeFromDatabase();
    }

    private void removeFromDatabase() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String sql = "DELETE FROM issuegoods WHERE id=" + itable.getSelectionModel().getSelectedItem().getId();
        PreparedStatement statement = connectDB.prepareStatement(sql);
        statement.executeUpdate();
        refreshTable();
    }

    public void addGoods(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-goods.fxml")));
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

    public void viewBills(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-bills.fxml")));
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
}