package com.example.sistema_votacion_escolar.Controllers;

import com.example.sistema_votacion_escolar.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

    @FXML
    public void handleManageCandidates(ActionEvent event) {
        SceneManager.loadScene((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow(), "candidates.fxml");
    }

    @FXML
    public void handleManageVoters(ActionEvent event) {
        SceneManager.loadScene((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow(), "voters.fxml");
    }

    @FXML
    public void handleRegisterVote(ActionEvent event) {
        SceneManager.loadScene((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow(), "votes.fxml");
    }

}
