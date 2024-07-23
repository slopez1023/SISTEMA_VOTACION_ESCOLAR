package com.example.sistema_votacion_escolar.DAO;

import com.example.sistema_votacion_escolar.utils.DBConnection;
import com.example.sistema_votacion_escolar.Models.Voter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoterDAO {


    public List<Voter> getAllVoters() throws SQLException {
        List<Voter> voters = new ArrayList<>();
        String query = "SELECT * FROM voters";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while(resultSet.next()){
                Voter voter = new Voter();
                voter.setId(resultSet.getInt("id"));
                voter.setName(resultSet.getString("name"));
                voter.setIdentificationNumber(resultSet.getString("identification_number")); // Corrección aquí
                voters.add(voter);
            }
        }
        return voters;
    }

    public void insertVoter(Voter voter) throws SQLException {
        String query = "INSERT INTO voters (name, identification_number) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, voter.getName());
            preparedStatement.setString(2, voter.getIdentificationNumber());
            preparedStatement.executeUpdate();
        }
    }

    public boolean isValidVoter(String identificationNumber) {
        String query = "SELECT * FROM voters WHERE identification_number = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, identificationNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateVoter(Voter voter) throws SQLException {
        String query = "UPDATE voters SET name = ?, identification_number = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, voter.getName());
            preparedStatement.setString(2, voter.getIdentificationNumber());
            preparedStatement.setInt(3, voter.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteVoter(int id) throws SQLException {
        String query = "DELETE FROM voters WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    public int getVoterIdByIdentificationNumber(String identificationNumber) {
        String query = "SELECT id FROM voters WHERE identification_number = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, identificationNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Devuelve -1 si no se encuentra el votante
    }
}

