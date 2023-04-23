package com.example.demo3forsdk20;

import com.example.demo3forsdk20.db.DatabaseConnection;
import com.example.demo3forsdk20.view.LoginPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPageController {

    private LoginPage loginPage;

    public LoginPageController() {
        loginPage = new LoginPage();
        initActions();
    }

    private void initActions() {
        loginPage.getButton().setOnAction(actionEvent -> {

            if (loginPage.getUserNameTextFiled().getText().isEmpty() || loginPage.getPasswordField().getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("خطا");
                alert.setHeaderText("نام کاربری یا کلمه عبور");
                alert.setContentText("نام کاربری یا کلمه عبور خالی است");

                alert.showAndWait();
                return;
            }

            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();
            String sqlString = "select * from USER_TABLE where USERNAME = '" + loginPage.getUserNameTextFiled().getText() + "'";
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlString);
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
                    if (password.equals(loginPage.getPasswordField().getText())) {
                        FXMLLoader fxmlLoader = new FXMLLoader(LoginPageController.class.getResource("main-page.fxml"));
                        goToOtherPage(fxmlLoader.load());
                    } else {
                        loginPage.getErrorLabel().setTextFill(Color.RED);
                        loginPage.getErrorLabel().setText("نام کاربری یا کلمه عبور اشتباه هست");
                    }
                } else {
                    loginPage.getErrorLabel().setTextFill(Color.RED);
                    loginPage.getErrorLabel().setText("نام کاربری یا کلمه عبور اشتباه هست");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                    if (resultSet != null)
                        resultSet.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        loginPage.getSignUpLabel().

                setOnMouseClicked(mouseEvent ->

                {
                    RegisterPageController registerPageController = new RegisterPageController();
                    goToOtherPage(registerPageController.getRegisterPage());
                });
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    private void goToOtherPage(Pane pane) {
        loginPage.getScene().getWindow().hide();
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(pane));
        mainStage.setMaximized(true);
        mainStage.show();
    }
}
