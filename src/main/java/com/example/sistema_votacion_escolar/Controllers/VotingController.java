package com.example.sistema_votacion_escolar.Controllers;

import com.example.sistema_votacion_escolar.DAO.CandidateDAO;
import com.example.sistema_votacion_escolar.DAO.VoteDAO;
import com.example.sistema_votacion_escolar.DAO.VoterDAO;
import com.example.sistema_votacion_escolar.Models.Candidate;
import com.example.sistema_votacion_escolar.Models.Vote;
import com.example.sistema_votacion_escolar.utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class VotingController {

    @FXML private TableView<Candidate> candidatesTable;
    @FXML private TableColumn<Candidate, Integer> idColumn;
    @FXML private TableColumn<Candidate, String> nameColumn;
    @FXML private TableColumn<Candidate, String> campaignDetailsColumn;
    @FXML private Button voteButton;
    @FXML private Label messageLabel;

    private ObservableList<Candidate> candidatesList;
    private CandidateDAO candidateDAO;
    private VoteDAO voteDAO;
    private VoterDAO voterDAO;
    private String voterIdentificationNumber;

    @FXML
    public void initialize() {
        candidateDAO = new CandidateDAO();
        voteDAO = new VoteDAO();
        voterDAO = new VoterDAO();
        candidatesList = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        campaignDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("campaignDetails"));

        loadCandidates();
    }

    public void setVoterIdentificationNumber(String identificationNumber) {
        this.voterIdentificationNumber = identificationNumber;
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
    public void handleVoteButtonAction() {
        Candidate selectedCandidate = candidatesTable.getSelectionModel().getSelectedItem();
        if (selectedCandidate != null) {
            if (voterIdentificationNumber == null || voterIdentificationNumber.isEmpty()) {
                messageLabel.setText("El número de identificación del votante no es válido.");
                return;
            }
            try {
                int voterId = voterDAO.getVoterIdByIdentificationNumber(voterIdentificationNumber); // Método que obtenga el ID del votante a partir del número de identificación
                if (voterId == -1) {
                    messageLabel.setText("No se encontró el votante.");
                    return;
                }

                Vote vote = new Vote();
                vote.setVoterId(voterId);
                vote.setCandidateId(selectedCandidate.getId());
                voteDAO.insertVote(vote);

                messageLabel.setText("¡Gracias por tu voto!");
            } catch (SQLException e) {
                e.printStackTrace();
                messageLabel.setText("Error al registrar el voto. Inténtalo de nuevo.");
            }
        } else {
            messageLabel.setText("Por favor, selecciona un candidato.");
        }
    }

    @FXML
    public void handleReturnToMain() {
        SceneManager.loadScene((Stage) voteButton.getScene().getWindow(), "main.fxml");
    }
}
