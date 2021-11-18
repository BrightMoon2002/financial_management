package com.group4.model.login;

import com.group4.model.account.Account;

import java.time.LocalDate;

public class Login {
    private int id;
    private Account account;
    private String login;
    private String logout;
    private Boolean status;

    public Login(int id, Account account, String login, String logout, Boolean status) {
        this.id = id;
        this.account = account;
        this.login = login;
        this.logout = logout;
        this.status = status;
    }

    public Login() {
    }

    public Login(Account account, String login, String logout, Boolean status) {
        this.account = account;
        this.login = login;
        this.logout = logout;
        this.status = status;
    }

    public Login(Account account, String login, Boolean status) {
        this.account = account;
        this.login = login;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", account=" + account +
                ", login='" + login + '\'' +
                ", logout='" + logout + '\'' +
                ", status=" + status +
                '}';
    }
}
