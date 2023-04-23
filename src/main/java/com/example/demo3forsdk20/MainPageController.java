package com.example.demo3forsdk20;

import com.example.demo3forsdk20.db.DatabaseConnection;
import com.example.demo3forsdk20.model.FlowerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<FlowerModel> flower_table;
    @FXML
    private TextField flower_name_field;
    @FXML
    private TextField flower_type_field;
    @FXML
    private TextField need_light_field;
    @FXML
    private TextField need_water_field;
    @FXML
    private TextField description_field;
    @FXML
    private Button submit;

    @FXML void setSubmit(ActionEvent event) {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();
        String sql = "INSERT INTO FLOWER (NAME, IMAGE, FLOWER_TYPE, LIGHT_NEED, WATER_NEED, DESCRIPTION) VALUES (?, ?, ?, ?, ? ,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, flower_name_field.getText());
            preparedStatement.setString(2, null);
            preparedStatement.setString(3, flower_type_field.getText());
            preparedStatement.setString(4, need_light_field.getText());
            preparedStatement.setString(5, need_water_field.getText());
            preparedStatement.setString(6, description_field.getText());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                fetchDataFromDB();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchDataFromDB();
    }

    private void fetchDataFromDB() {
        root.setStyle("-fx-background-color: blue");
        root.setStyle("-fx-background-radius: 16");
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from FLOWER");
            List<FlowerModel> myFlowers = new ArrayList<>();
            while (resultSet.next()) {
                FlowerModel flowerModel = new FlowerModel(resultSet.getInt("FLOWER_ID"), resultSet.getString("NAME"), resultSet.getString("IMAGE"), resultSet.getString("FLOWER_TYPE"), resultSet.getString("LIGHT_NEED"), resultSet.getString("WATER_NEED"), resultSet.getString("DESCRIPTION"));
                flower_table.getItems().add(flowerModel);
                myFlowers.add(flowerModel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
