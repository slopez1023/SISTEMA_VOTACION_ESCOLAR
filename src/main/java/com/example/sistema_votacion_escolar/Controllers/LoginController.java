package com.example.sistema_votacion_escolar.Controllers;

import com.example.sistema_votacion_escolar.DAO.VoterDAO;
import com.example.sistema_votacion_escolar.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField identificationNumberField;
    @FXML private Label messageLabel;

    private VoterDAO voterDAO;

    @FXML
    public void initialize() {
        voterDAO = new VoterDAO();
    }

    @FXML
    public void handleLoginButtonAction(ActionEvent event) {
        String identificationNumber = identificationNumberField.getText();

        if (identificationNumber.isEmpty()) {
            messageLabel.setText("Por favor, ingrese su número de identificación.");
            return;
        }

        if (voterDAO.isValidVoter(identificationNumber)) {
            // Navegar a la pantalla principal
            navigateToMainScreen();
        } else {
            // Mostrar mensaje de error
            messageLabel.setText("Identificación no válida.");
        }
    }

    private void navigateToMainScreen() {
        try {
            Stage stage = (Stage) identificationNumberField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/sistema_votacion_escolar/main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sistema de Votación Escolar");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error al cargar la pantalla principal.");
        }
    }
}


