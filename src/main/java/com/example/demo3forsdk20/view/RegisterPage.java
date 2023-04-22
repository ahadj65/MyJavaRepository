package com.example.demo3forsdk20.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegisterPage extends BorderPane {

    public Button getButton() {
        return button;
    }

    private Button button;

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public TextField getRepeatPasswordTextField() {
        return repeatPasswordTextField;
    }

    private TextField userNameTextField;
    private TextField passwordTextField;
    private TextField repeatPasswordTextField;

    public RegisterPage() {
        Label titleLabel = new Label("فرم ثبت نام");
        titleLabel.setStyle("-fx-font-size: 24px;");
        HBox titleHBox = new HBox(titleLabel);
        titleHBox.setAlignment(Pos.CENTER);
        setMargin(titleHBox, new Insets(32, 0, 0, 0));

        Label usernameLabel = new Label("نام کاربری:");
        Label passwordLabel = new Label("رمز عبور");
        Label repeatPasswordLabel = new Label("تکرار رمز عبور");
        usernameLabel.setMaxWidth(Double.MAX_VALUE);
        passwordLabel.setMaxWidth(Double.MAX_VALUE);
        repeatPasswordLabel.setMaxWidth(Double.MAX_VALUE);
        VBox labelVBox = new VBox(usernameLabel, passwordLabel, repeatPasswordLabel);
        labelVBox.setAlignment(Pos.CENTER);
        labelVBox.setSpacing(16);

        userNameTextField = new TextField();
        passwordTextField = new TextField();
        repeatPasswordTextField = new TextField();
        userNameTextField.setMaxWidth(Double.MAX_VALUE);
        passwordTextField.setMaxWidth(Double.MAX_VALUE);
        repeatPasswordTextField.setMaxWidth(Double.MAX_VALUE);
        VBox valueVBox = new VBox(userNameTextField, passwordTextField, repeatPasswordTextField);
        valueVBox.setAlignment(Pos.CENTER);
        valueVBox.setSpacing(16);

        HBox fieldsHBox = new HBox(valueVBox, labelVBox);
        fieldsHBox.setSpacing(8);
        fieldsHBox.setAlignment(Pos.CENTER);

        button = new Button("ثبت نام و ورود");
        VBox fieldsWithButtonVBox = new VBox(fieldsHBox, button);
        fieldsWithButtonVBox.setAlignment(Pos.CENTER);
        fieldsWithButtonVBox.setSpacing(16);

        setTop(titleHBox);
        setCenter(fieldsWithButtonVBox);
    }

}
