package com.example.sistema_votacion_escolar.Controllers;

import com.example.sistema_votacion_escolar.DAO.VoterDAO;
import com.example.sistema_votacion_escolar.Models.Voter;
import com.example.sistema_votacion_escolar.utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class VoterController {

    @FXML private TableView<Voter> votersTable;
    @FXML private TableColumn<Voter, Integer> idColumn;
    @FXML private TableColumn<Voter, String> nameColumn;
    @FXML private TableColumn<Voter, String> identificationNumberColumn;
    @FXML private TextField nameField;
    @FXML private TextField identificationNumberField;

    private ObservableList<Voter> votersList;
    private VoterDAO voterDAO;

    @FXML
    public void initialize() {
        voterDAO = new VoterDAO();
        votersList = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        identificationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("identificationNumber"));

        loadVoters();
    }

    private void loadVoters() {
        try {
            votersList.setAll(voterDAO.getAllVoters());
            votersTable.setItems(votersList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddVoter() {
        String name = nameField.getText();
        String identificationNumber = identificationNumberField.getText();

        if (!name.isEmpty() && !identificationNumber.isEmpty()) {
            Voter voter = new Voter();
            voter.setName(name);
            voter.setIdentificationNumber(identificationNumber);

            try {
                voterDAO.insertVoter(voter);
                loadVoters();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleUpdateVoter() {
        Voter selectedVoter = votersTable.getSelectionModel().getSelectedItem();
        if (selectedVoter != null) {
            String name = nameField.getText();
            String identificationNumber = identificationNumberField.getText();

            if (!name.isEmpty() && !identificationNumber.isEmpty()) {
                selectedVoter.setName(name);
                selectedVoter.setIdentificationNumber(identificationNumber);

                try {
                    voterDAO.updateVoter(selectedVoter);
                    loadVoters();
                    clearFields();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void handleDeleteVoter() {
        Voter selectedVoter = votersTable.getSelectionModel().getSelectedItem();
        if (selectedVoter != null) {
            try {
                voterDAO.deleteVoter(selectedVoter.getId());
                loadVoters();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        nameField.clear();
        identificationNumberField.clear();
    }

    @FXML
    public void handleReturnToMain() {
        SceneManager.loadScene((Stage) nameField.getScene().getWindow(), "main.fxml");
    }
}
