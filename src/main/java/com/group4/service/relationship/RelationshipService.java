package com.group4.service.relationship;

import com.group4.model.relationshipAccount.Relationship;

import java.sql.SQLException;
import java.util.List;

public class RelationshipService implements IRelationshipService{
    @Override
    public List<Relationship> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Relationship relationship) {

    }

    @Override
    public Relationship findById(int id) throws SQLException {
        return null;
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
