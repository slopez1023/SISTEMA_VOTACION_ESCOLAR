package com.example.sistema_votacion_escolar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga la pantalla de login (login.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sistema_votacion_escolar/login.fxml"));
        primaryStage.setTitle("Login - Sistema de Votación Escolar");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    }





