package com.example.demo3forsdk20.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Objects;

public class LoginPage extends BorderPane {
    private TextField userNameTextFiled;
    private PasswordField passwordField;
    private Label errorLabel;
    private Button button;

    private Label signUpLabel;

    public LoginPage() {

        Image iconImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("icon.png")));
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(100);
        iconImageView.setFitWidth(100);
        HBox iconHBox = new HBox();
        iconHBox.setAlignment(Pos.CENTER);
        iconHBox.getChildren().add(iconImageView);
        setMargin(iconHBox, new Insets(64, 0, 0, 0));

        Label userNameLabel = new Label("نام کاربری:");
        Label passwordLabel = new Label("رمز عبور:");
        userNameLabel.setMaxWidth(Double.MAX_VALUE);
        passwordLabel.setMaxWidth(Double.MAX_VALUE);
        VBox labelVBox = new VBox(userNameLabel, passwordLabel);
        labelVBox.setAlignment(Pos.CENTER);
        labelVBox.setSpacing(16);

        userNameTextFiled = new TextField();
        passwordField = new PasswordField();
        userNameTextFiled.setMaxWidth(Double.MAX_VALUE);
        passwordField.setMaxWidth(Double.MAX_VALUE);
        VBox valueVBox = new VBox(userNameTextFiled, passwordField);
        valueVBox.setAlignment(Pos.CENTER);
        valueVBox.setSpacing(16);

        HBox fieldsHBox = new HBox(valueVBox, labelVBox);
        fieldsHBox.setSpacing(8);
        fieldsHBox.setAlignment(Pos.CENTER);

        button = new Button("ورود");
        errorLabel = new Label();

        VBox fieldsWithButtonAndRememberPassword = new VBox(fieldsHBox, button, errorLabel);
        fieldsWithButtonAndRememberPassword.setAlignment(Pos.CENTER);
        fieldsWithButtonAndRememberPassword.setSpacing(16);

        signUpLabel = new Label("ثبت نام");
        signUpLabel.setTextFill(Color.BLUE);

        HBox hbox = new HBox();
        hbox.getChildren().add(signUpLabel);
        hbox.setAlignment(Pos.CENTER);
        setMargin(hbox, new Insets(0, 0, 16, 0));
        this.setTop(iconHBox);
        this.setCenter(fieldsWithButtonAndRememberPassword);
        this.setBottom(hbox);

    }

    public TextField getUserNameTextFiled() {
        return userNameTextFiled;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public Button getButton() {
        return button;
    }

    public Label getSignUpLabel() {
        return signUpLabel;
    }
}
