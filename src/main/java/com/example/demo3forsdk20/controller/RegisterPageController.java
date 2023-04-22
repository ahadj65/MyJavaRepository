package com.example.demo3forsdk20.controller;

import com.example.demo3forsdk20.db.DatabaseConnection;
import com.example.demo3forsdk20.view.MainPage;
import com.example.demo3forsdk20.view.RegisterPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterPageController {
    private RegisterPage registerPage;

    public RegisterPageController() {
        registerPage = new RegisterPage();
        initActions();
    }

    private void initActions() {
        registerPage.getButton().setOnAction(actionEvent -> {
            if (registerPage.getUserNameTextField().getText().isEmpty()) {
                showError("نام کاربری");
                return;
            }
            if (registerPage.getPasswordTextField().getText().isEmpty()) {
                showError("رمز عبور");
                return;
            }
            if (registerPage.getRepeatPasswordTextField().getText().isEmpty()) {
                showError("تکرار رمز عبور");
                return;
            }
            if (!registerPage.getPasswordTextField().getText().equals(registerPage.getRepeatPasswordTextField().getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("خطا");
                alert.setHeaderText("رمز عبور");
                alert.setContentText("رمز عبور و تکرار رمز عبور باید یکسان باشند");

                alert.showAndWait();
                return;
            }

            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();
            String sql = "INSERT INTO USER_TABLE (USERNAME, PASSWORD) VALUES (?, ?)";
            PreparedStatement pstmt;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, registerPage.getUserNameTextField().getText());
                pstmt.setString(2, registerPage.getPasswordTextField().getText());
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    goToMainPage();
                }
            } catch (SQLException e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("خطا");
                alert.setHeaderText("ثبت نام");
                alert.setContentText("خطا در ثبت نام");
                alert.showAndWait();
            }
        });
    }

    private void showError(String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("خطا");
        alert.setHeaderText(header);
        alert.setContentText(header + " نمی تواند خالی باشد");
        alert.showAndWait();
    }

    private void goToMainPage() {
        registerPage.getScene().getWindow().hide();
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(new MainPage()));
        mainStage.setMaximized(true);
        mainStage.show();
    }

    public RegisterPage getRegisterPage() {
        return registerPage;
    }

}
