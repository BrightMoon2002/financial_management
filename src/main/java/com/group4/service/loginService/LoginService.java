package com.group4.service.loginService;

import com.group4.model.account.Account;
import com.group4.model.login.Login;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginService implements ILoginService {
    private static Connection connection = SingletonConnection.getConnection();
    private IAccountService accountService = new AccountService();


    @Override
    public List<Login> findAll()  {
        List<Login> loginList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM login l JOIN account a ON l.id_account = a.id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_login = resultSet.getInt("l.id");
                int id_account = resultSet.getInt("id_account");
                String login_date = resultSet.getString("loginDate");
                String logout_date = resultSet.getString("logoutDate");

                boolean status = resultSet.getBoolean("a.status");
                Account accountLogin = accountService.findById(id_account);
                Login login = new Login(id_login, accountLogin, login_date, logout_date, status);
                loginList.add(login);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return loginList;
    }

    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        List<Login> loginList = loginService.findAll();
        for (Login l: loginList
             ) {
            System.out.println(l);
        }
        System.out.println(loginService.findById(1));
        System.out.println(LocalDateTime.now());
    }

    @Override
    public void save(Login login) {

    }

    @Override
    public Login findById(int id) {
        Login login = new Login();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM login l JOIN account a ON l.id_account = a.id where a.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id_login = resultSet.getInt("l.id");
                int id_account = resultSet.getInt("id_account");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
                Date login_date = resultSet.getDate("loginDate");
                String dateLogin = sdf.format(login_date);
                Date logout_date = resultSet.getDate("logoutDate");
                String dateLogout = sdf.format(logout_date);
                boolean status = resultSet.getBoolean("a.status");
                Account accountLogin = accountService.findById(id_account);
                login = new Login(id_login, accountLogin, dateLogin, dateLogout, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return login;
    }

    @Override
    public boolean update(Login login) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return false;
    }
}
