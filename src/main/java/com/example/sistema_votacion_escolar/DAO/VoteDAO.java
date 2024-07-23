package com.example.sistema_votacion_escolar.DAO;

import com.example.sistema_votacion_escolar.utils.DBConnection;
import com.example.sistema_votacion_escolar.Models.Vote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteDAO {

    public List<Vote> getAllVotes() throws SQLException{
        List<Vote> votes = new ArrayList<>();
        String query = "SELECT * FROM votes";

        try(Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                Vote vote = new Vote();
                vote.setId(resultSet.getInt("id"));
                vote.setVoterId(resultSet.getInt("voter id"));
                vote.setCandidateId(resultSet.getInt("candidate id"));
                vote.setTimestamp(resultSet.getTimestamp("timestamp"));
                votes.add(vote);

            }
        } return votes;

    }
    public void insertVote(Vote vote) throws SQLException {
        String query = "INSERT INTO votes (voter_id, candidate_id) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vote.getVoterId());
            preparedStatement.setInt(2, vote.getCandidateId());
            preparedStatement.executeUpdate();
        }
    }

    public void updateVote(Vote vote) throws SQLException {
        String query = "UPDATE votes SET voter_id = ?, candidate_id = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vote.getVoterId());
            preparedStatement.setInt(2, vote.getCandidateId());
            preparedStatement.setInt(3, vote.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteVote(int id) throws SQLException {
        String query = "DELETE FROM votes WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
