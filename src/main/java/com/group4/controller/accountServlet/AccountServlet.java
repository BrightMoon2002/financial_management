package com.group4.controller.accountServlet;

import com.group4.controller.financialServlet.RevenueServlet;
import com.group4.controller.spending.SpendingServlet;
import com.group4.model.account.Account;
import com.group4.model.account.Role;
import com.group4.model.financial.Revenue;
import com.group4.model.financial.Spending;
import com.group4.service.accountService.AccountService;
import com.group4.service.financial.Revenue.IRevenueService;
import com.group4.service.financial.Revenue.RevenueService;
import com.group4.service.roleService.IRoleService;
import com.group4.service.roleService.RoleService;
import com.group4.service.spendingService.ISpendingDAO;
import com.group4.service.spendingService.SpendingDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "AccountServlet", urlPatterns = "/login")
public class AccountServlet extends HttpServlet {
    private final AccountService accountService = new AccountService();
    private final IRoleService roleService = new RoleService();
    RevenueServlet revenueServlet = new RevenueServlet();
    SpendingServlet spendingServlet = new SpendingServlet();
    IRevenueService revenueService = new RevenueService();
    SpendingDAO spendingDAO = new ISpendingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateAccount(request, response);
                break;
            case "editAccountUser":
                showEditAccountUser(request, response);
                break;
            case "adminEditAccount":
                showAdminEditAccount(request, response);
                break;
            case "adminDeleteAccount":
                adminDeleteAccount(request, response);
                break;
            case "showAccountList":
                showAccountList(request, response);
                break;
            case "showUserPage":
                showUserPage(request, response);
                break;
            case "showAdminCreateAccount":
                showAdminCreateAccount(request, response);
                break;
            case "showAdminPage":
                showUserPage(request, response);
                break;
            case "logoutAccount":
                logoutAccount(request, response);
                break;
            case "searchFriend":
                showFriendList(request, response);
                break;
            case "addFriend":
                addFriend(request, response);
                break;
            case "blockUser":
                blockFriend(request, response);
                break;
            default:
                showLogin(request, response);
                break;
        }
    }

    private void blockFriend(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Account accountLogging = (Account)session.getAttribute("accountLogging");
        Account account = accountService.findById(id);
        if (account == null) {
            try {
                response.sendRedirect("/view/error404.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            accountService.blockFriend(account.getId(), accountLogging.getId());
            accountService.blockUser(account.getId(), accountLogging.getId());
        }
        try {
            response.sendRedirect("/login?action=showUserPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFriend(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Account accountLogging = (Account) session.getAttribute("accountLogging");
        Account account = accountService.findById(id);
        if (account == null) {
            try {
                response.sendRedirect("/view/error404.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            accountService.addFriend(accountLogging.getId(), account.getId());
        }
        try {
            response.sendRedirect("/login?action=showUserPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFriendList(HttpServletRequest request, HttpServletResponse response) {
       String username = request.getParameter("username");

        Account account = accountService.searchUserByUsername(username);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/searchFriend.jsp");
        request.setAttribute("account", account);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void adminDeleteAccount(HttpServletRequest request, HttpServletResponse response) {
        int id_account = Integer.parseInt(request.getParameter("id"));
        accountService.deleteById(id_account);
        try {
            response.sendRedirect("/login?action=showAccountList");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("/login?action=showUserPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAdminEditAccount(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/adminEditAccount.jsp");
        int id_account = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(id_account);
        request.setAttribute("accountSelect", account);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showAdminCreateAccount(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/adminCreateAccount.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void showAccountList(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = accountService.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/listAccountWithAdmin.jsp");
        request.setAttribute("accountList", accountList);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    //logout account
    private void logoutAccount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showEditAccountUser(HttpServletRequest request, HttpServletResponse response) {
        int id_account = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(id_account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/editAccountUser.jsp");
        request.setAttribute("accountLogging", account);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showAdminPage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/homepageAdmin.jsp");
        HttpSession session = request.getSession(false);
        if (session != null) {
            Account accountLogin = (Account) session.getAttribute("account");
            List<Account> accountList = (List<Account>) session.getAttribute("accountList");
            Account account = (Account) session.getAttribute("account");
            request.setAttribute("account", account);
            request.setAttribute("accountList", accountList);
            request.setAttribute("accountLogin", accountLogin);
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showUserPage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/homepageUser.jsp");

        try {
            revenueServlet.listRevenueHomepage(request, response);
            spendingServlet.listSpendingHomepage(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            Account account = (Account) session.getAttribute("accountLogging");
            request.setAttribute("accountLogging", account);

            if (account.getRole().getId() == 1) {
                double revenueTotalAdmin = (double) session.getAttribute("revenueTotalAdminHomepage");
                request.setAttribute("revenueTotalAdmin", revenueTotalAdmin);
            }
            List<Account> friendList = accountService.showFriendList(account.getId());
            double spendingTotalAmount = spendingDAO.getTotalById(account.getId());
            double revenueTotalUser = revenueService.getTotalById(account.getId());
            List<Revenue> listRevenue = (List<Revenue>) session.getAttribute("listRevenueHomepage");
            List<Revenue> listRevenueUser = (List<Revenue>) session.getAttribute("listRevenueUserHomepage");
            List<Spending> spendingList = (List<Spending>) session.getAttribute("spendingsHomepage");
            double accountBalance = revenueTotalUser - spendingTotalAmount;

            session.setAttribute("accountBalance", accountBalance);

            request.setAttribute("listRevenue", listRevenue);
            request.setAttribute("listRevenueUser", listRevenueUser);
            request.setAttribute("revenueTotalUser", revenueTotalUser);
            request.setAttribute("spendingTotalAmount", spendingTotalAmount);
            request.setAttribute("spendings", spendingList);
            request.setAttribute("accountBalance", accountBalance);
            request.setAttribute("friendList", friendList);
            request.setAttribute("role", account.getRole().getId());

            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showCreateAccount(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/createUser.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showLogin(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                CreateAccount(request, response);
                break;
            case "editAccountUser":
                EditAccountUser(request, response);
                break;
            case "checkLogin":
                checkLogin(request, response);
                break;
            case "showAdminCreateAccount":
                adminCreateAccount(request, response);
                break;
            case "adminEditAccount":
                adminEditAccount(request, response);
                break;

        }

    }

    private void adminEditAccount(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession(false);
//        Account account = (Account) session.getAttribute("accountSelect");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        Role role = roleService.findById(role_id);
        Account account1 = new Account(id, username, password, name, dob, email, address, status, role);
        request.setAttribute("accountSelect", account1);
        accountService.update(account1);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/adminEditAccount.jsp");
        session.setAttribute("accountSelect", account1);
        request.setAttribute("message", "Successfully edited!");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void adminCreateAccount(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        System.out.println(status);
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        Role role = roleService.findById(role_id);
        Account account = new Account(username, password, name, dob, email, address, status, role);
        accountService.save(account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/adminCreateAccount.jsp");
        request.setAttribute("message", "Account successfully created!");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void EditAccountUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("accountLogging");
        String username = account.getUsername();
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Role role = roleService.findById(1);
        Account account1 = new Account(id, username, password, name, dob, email, address, status, role);
        request.setAttribute("accountLogging", account1);
        accountService.update(account1);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/editAccountUser.jsp");
        session.setAttribute("accountLogging", account1);
        request.setAttribute("message", "Successfully edited!");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountService.checkLogin(username, password);
        List<Account> accountList = accountService.findAll();

        if (account != null) {
            HttpSession session = request.getSession();
            if (account.getRole().getName().equalsIgnoreCase("user")) {
                session.setAttribute("accountLogging", account);
                response.sendRedirect("/login?action=showUserPage");
            } else {
                session.setAttribute("account", account);
                session.setAttribute("accountList", accountList);
                session.setAttribute("accountLogging", account);
                response.sendRedirect("/login?action=showAdminPage");
            }
        } else {
            request.setAttribute("message", "Wrong input, please re-enter");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/login.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void CreateAccount(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = accountService.findAll();
        boolean check = true;
        String username = request.getParameter("username");
        for (Account value : accountList) {
            if (value.getUsername().equals(username)) {
                check = false;
                break;
            }
        }
        if (check) {
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            Role role = roleService.findById(1);
            Account account = new Account(username, password, name, dob, email, address, true, role);
            accountService.save(account);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/createUser.jsp");
            request.setAttribute("message", "Account successfully created!");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("message1", "Account already exists!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login/createUser.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
