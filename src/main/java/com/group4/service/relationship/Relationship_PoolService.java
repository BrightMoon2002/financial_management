package com.group4.service.relationship;

import com.group4.model.account.Account;

import java.sql.SQLException;
import java.util.List;

public class Relationship_PoolService implements IRelationship_PoolService{
    @Override
    public List<Account> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public Account findById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Account account) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return false;
    }
}
