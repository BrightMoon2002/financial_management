package com.group4.service.spendingService;

import com.group4.model.financial.Spending;
import com.group4.service.IService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface SpendingDAO extends IService<Spending> {
    List<Spending> sortByAmount() throws SQLException;

    List<Spending> findByDate(Date date) throws SQLException;

    List<Spending> findByDateOfAccountId(Date date, int account_id) throws SQLException;

    List<Spending> findAllSpendingByAccountId(int account_id) throws SQLException;

    List<Spending> sortByAmountOfAccountId(int account_id) throws SQLException;

    double getTotalById(int id);

    double checkSpendingLimit(Spending spending) throws SQLException;
    double getAmountLimitById(int account_id) throws SQLException;
}
