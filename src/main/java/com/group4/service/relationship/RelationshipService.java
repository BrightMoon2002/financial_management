package com.group4.service.relationship;

import com.group4.model.relationshipAccount.Relationship;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelationshipService implements IRelationshipService{
    Connection connection = SingletonConnection.getConnection();

    @Override
    public List<Relationship> findAll() {
        List<Relationship> relationshipList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM relationship");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
              int id =  resultSet.getInt("id_relationship");
               String name =  resultSet.getString("name_relationship");
                relationshipList.add(new Relationship(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return relationshipList;
    }

    @Override
    public void save(Relationship relationship) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO relationship(name_relationship) value (?) ");
            preparedStatement.setString(1, relationship.getName_relationship());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Relationship findById(int id) throws SQLException {
        Relationship relationship = new Relationship();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM relationship where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id1 =  resultSet.getInt("id_relationship");
                String name =  resultSet.getString("name_relationship");
                relationship = new Relationship(id1, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return relationship;
    }

    @Override
    public boolean update(Relationship relationship) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return false;
    }
}
