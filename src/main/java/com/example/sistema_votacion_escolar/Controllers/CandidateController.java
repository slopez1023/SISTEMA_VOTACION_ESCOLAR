package com.example.sistema_votacion_escolar.Controllers;

import com.example.sistema_votacion_escolar.DAO.CandidateDAO;
import com.example.sistema_votacion_escolar.Models.Candidate;
import com.example.sistema_votacion_escolar.utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CandidateController {
    @FXML private TableView<Candidate> candidatesTable;
    @FXML private TableColumn<Candidate, Integer> idColumn;
    @FXML private TableColumn<Candidate, String> nameColumn;
    @FXML private TableColumn<Candidate, String> photoPathColumn;
    @FXML private TableColumn<Candidate, String> campaignDetailsColumn;
    @FXML private TextField nameField;
    @FXML private TextField photoPathField;
    @FXML private TextArea campaignDetailsField;

    private ObservableList<Candidate> candidatesList;
    private CandidateDAO candidateDAO;

    @FXML
    public void initialize() {
        candidateDAO = new CandidateDAO();
        candidatesList = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        photoPathColumn.setCellValueFactory(new PropertyValueFactory<>("photoPath"));
        campaignDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("campaignDetails"));

        loadCandidates();
    }

    private void loadCandidates() {
        try {
            candidatesList.setAll(candidateDAO.getAllCandidates());
            candidatesTable.setItems(candidatesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddCandidate() {
        String name = nameField.getText();
        String photoPath = photoPathField.getText();
        String campaignDetails = campaignDetailsField.getText();

        if (!name.isEmpty() && !photoPath.isEmpty() && !campaignDetails.isEmpty()) {
            Candidate candidate = new Candidate();
            candidate.setName(name);
            candidate.setPhotoPath(photoPath);
            candidate.setCampaignDetails(campaignDetails);

            try {
                candidateDAO.insertCandidate(candidate);
                loadCandidates();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleUpdateCandidate() {
        Candidate selectedCandidate = candidatesTable.getSelectionModel().getSelectedItem();
        if (selectedCandidate != null) {
            String name = nameField.getText();
            String photoPath = photoPathField.getText();
            String campaignDetails = campaignDetailsField.getText();

            if (!name.isEmpty() && !photoPath.isEmpty() && !campaignDetails.isEmpty()) {
                selectedCandidate.setName(name);
                selectedCandidate.setPhotoPath(photoPath);
                selectedCandidate.setCampaignDetails(campaignDetails);

                try {
                    candidateDAO.updateCandidate(selectedCandidate);
                    loadCandidates();
                    clearFields();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void handleDeleteCandidate() {
        Candidate selectedCandidate = candidatesTable.getSelectionModel().getSelectedItem();
        if (selectedCandidate != null) {
            try {
                candidateDAO.deleteCandidate(selectedCandidate.getId());
                loadCandidates();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        nameField.clear();
        photoPathField.clear();
        campaignDetailsField.clear();
    }

    @FXML
    public void handleReturnToMain() {
        SceneManager.loadScene((Stage) nameField.getScene().getWindow(), "main.fxml");
    }
}
