package com.group4.service.accountService;

import com.group4.model.account.Account;
import com.group4.model.account.Role;

import com.group4.service.roleService.IRoleService;
import com.group4.service.roleService.RoleService;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {
    public static final String SHOW_FRIEND_LIST = "SELECT id_account2, id_account1 FROM relationship_pool join account a on a.id = relationship_pool.id_account1 join account a2 on a2.id = relationship_pool.id_account2 join relationship r on relationship_pool.id_relationship = r.id_relationship where r.id_relationship = 1 AND id_account1 = ? OR id_account2 = ?;";
    public static final String SHOW_BLOCK_LIST = "SELECT id_account2, id_account1 FROM relationship_pool join account a on a.id = relationship_pool.id_account1 join account a2 on a2.id = relationship_pool.id_account2 join relationship r on relationship_pool.id_relationship = r.id_relationship where r.id_relationship = 2 AND id_account1 = ? OR id_account2 = ?;";
    public static final String SELECT_ACCOUT_BY_USERNAME = "SELECT * from account a where a.username = ?;";
    private final Connection connection = SingletonConnection.getConnection();
    private final IRoleService roleService = new RoleService();

    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO account (username,password,name,dob,email,address,status,role_id) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SELECT_ACCOUNT_BY_ID = "select * from account where id =?";
    private static final String SELECT_ALL_ACCOUNT = "select * from account";
    private static final String DELETE_ACCOUNT_SQL = "delete from account where id = ?";
    private static final String UPDATE_ACCOUNT_SQL = "update account set username = ?,password = ?,name = ?,dob = ?,email = ?,address = ?,status = ?,role_id = ? where id = ?";

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                boolean status = resultSet.getBoolean("status");
                int role_id = resultSet.getInt("role_id");
                Role role = roleService.findById(role_id);
                accountList.add(new Account(id, username, password, name, dob, email, address, status, role));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountList;
    }

    @Override
    public void save(Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getDob());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setString(6, account.getAddress());
            preparedStatement.setBoolean(7, account.isStatus());
            preparedStatement.setInt(8, account.getRole().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Account findById(int id) {
        Account account = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                boolean status = resultSet.getBoolean("status");
                int role_id = resultSet.getInt("role_id");
                Role role = roleService.findById(role_id);
                account = new Account(id, username, password, name, dob, email, address, status, role);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean update(Account account) {
        boolean rowUpdate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_SQL);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getDob());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setString(6, account.getAddress());
            preparedStatement.setBoolean(7, account.isStatus());
            preparedStatement.setInt(8, account.getRole().getId());
            preparedStatement.setInt(9, account.getId());
            rowUpdate = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public boolean deleteById(int id) {
        boolean rowDelete = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_SQL);
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }

    public Account checkLogin(String username,String password){
        Account account = null;
        List<Account>accountList = findAll();
        for (Account a : accountList) {
            if (a.getUsername().equals(username)
                    && a.getPassword().equals(password)) {
                account = a;
                break;
            }
        }
        return account;
    }
@Override
    public List<Account> showFriendListByType(String query, int id) {
        List<Account> accountList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_account =  resultSet.getInt("id_account2");
              if (id_account == id) {
                  int id_account1 = resultSet.getInt("id_account1");
                  Account account = findById(id_account1);
                  accountList.add(account);
              } else {
                  Account account = findById(id_account);
                  accountList.add(account);
              }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountList;
    }
    public List<Account> showFriendList(int id) {
       return showFriendListByType(SHOW_FRIEND_LIST, id);
    }
    public List<Account> showBlockList(int id) {
        return showFriendListByType(SHOW_BLOCK_LIST, id);
    }

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        List<Account> accountList = accountService.showFriendList(1);
        List<Account> accountList1 = accountService.showBlockList(2);
        for (Account a: accountList
             ) {
            System.out.println(a);
        }
        System.out.println("-------------");
        for (Account a: accountList1
             ) {
            System.out.println(a);
        }
    }

    public Account searchUserByUsername(String username) {
        Account account = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUT_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                account = findById(id);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    public boolean addFriend(int id1, int id2) {
       boolean rowAddFriend = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO relationship_pool (id_account1, id_account2, id_relationship) VALUE (?, ?, ?);");
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.setInt(1, 1);
           rowAddFriend =  preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAddFriend;
    }

    public boolean deleteFriend(int id1, int id2) {
        boolean rowUnFriend = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM relationship_pool where id_account1 = ? AND id_account2 = ? OR id_account1 = ? or id_accoutn2 = ?;");
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.setInt(3, id2);
            preparedStatement.setInt(4, id1);
            rowUnFriend =  preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUnFriend;
    }
    public boolean blockUser(int id1, int id2){
        boolean rowBlock = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO relationship_pool (id_account1, id_account2, id_relationship) VALUE (?, ?, 2);");
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            rowBlock = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowBlock;
    }

    public boolean blockFriend(int id1, int id2) {
        boolean rowBlock = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE relationship_pool SET id_relationship = 2 WHERE id_account1 = ? AND id_account2 = ? OR id_account2 = ? AND id_account1 = ?;");
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.setInt(3, id1);
            preparedStatement.setInt(4, id2);
            rowBlock = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowBlock;
    }
}
