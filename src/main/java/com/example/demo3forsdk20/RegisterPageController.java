package com.example.demo3forsdk20;

import com.example.demo3forsdk20.db.DatabaseConnection;
import com.example.demo3forsdk20.view.RegisterPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
            PreparedStatement pstmt = null;
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
            finally {
                try {
                    if (pstmt != null)
                        pstmt.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
        AnchorPane root = null;
        try {
            registerPage.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginPageController.class.getResource("main-page.fxml"));
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(fxmlLoader.load()));
            mainStage.setMaximized(true);
            mainStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        registerPage.getScene().getWindow().hide();
//        Stage mainStage = new Stage();
//        mainStage.setScene(new Scene(new MainPage()));
//        mainStage.setMaximized(true);
//        mainStage.show();
    }

    public RegisterPage getRegisterPage() {
        return registerPage;
    }

}
