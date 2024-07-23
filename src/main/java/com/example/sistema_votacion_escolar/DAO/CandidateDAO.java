package com.example.sistema_votacion_escolar.DAO;

import com.example.sistema_votacion_escolar.utils.DBConnection;
import com.example.sistema_votacion_escolar.Models.Candidate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

    public List<Candidate> getAllCandidates() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Candidate candidate = new Candidate();
                candidate.setId(resultSet.getInt("id"));
                candidate.setName(resultSet.getString("name"));
                candidate.setPhotoPath(resultSet.getString("photo_path"));  // Verificar el nombre de la columna aquí
                candidate.setCampaignDetails(resultSet.getString("campaign_details"));
                candidates.add(candidate);
            }
        }
        return candidates;
    }

    public void insertCandidate(Candidate candidate) throws SQLException {
        String query = "INSERT INTO candidates (name, photo_path, campaign_details) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getPhotoPath());
            preparedStatement.setString(3, candidate.getCampaignDetails());
            preparedStatement.executeUpdate();
        }
    }

    public void updateCandidate(Candidate candidate) throws SQLException {
        String query = "UPDATE candidates SET name = ?, photo_path = ?, campaign_details = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getPhotoPath());
            preparedStatement.setString(3, candidate.getCampaignDetails());
            preparedStatement.setInt(4, candidate.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteCandidate(int id) throws SQLException {
        String query = "DELETE FROM candidates WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
