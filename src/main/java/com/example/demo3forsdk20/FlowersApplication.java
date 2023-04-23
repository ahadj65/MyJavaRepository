package com.example.demo3forsdk20;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlowersApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        LoginPageController loginController = new LoginPageController();
        stage.setScene(new Scene(loginController.getLoginPage()));
        stage.setTitle("گلخانه");
        stage.setWidth(400);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}