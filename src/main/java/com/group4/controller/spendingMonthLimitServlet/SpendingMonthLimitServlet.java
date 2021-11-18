package com.group4.controller.spendingMonthLimitServlet;

import com.group4.controller.spending.SpendingServlet;
import com.group4.model.account.Account;
import com.group4.model.limit.SpendingMonthLimit;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import com.group4.service.spending_month_limit.ISpendingMonthLimitService;
import com.group4.service.spending_month_limit.SpendingMonthLimitService;

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

@WebServlet(name = "SpendingMonthLimitServlet", value = "/limit")
public class SpendingMonthLimitServlet extends HttpServlet {
    ISpendingMonthLimitService limitService = new SpendingMonthLimitService();
    IAccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteLimit(request, response);
                break;
            case "findById":
                showFindById(request, response);
                break;
            case "set":
                setNewLimit(request, response);
                break;
            default:
                listLimit(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action ="";
        }

        switch (action) {
            case "create":
                createLimit(request, response);
                break;
            case "edit":
                editLimit(request, response);
                break;
            case "findById":
                findLimitById(request, response);
                break;
        }
    }


    private void listLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SpendingMonthLimit> listLimit = null;
        try {
            listLimit = limitService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listLimit", listLimit);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account accountLogging = (Account) session.getAttribute("accountLogging");

        double amount = Double.parseDouble(request.getParameter("amount"));


        SpendingMonthLimit limit = new SpendingMonthLimit(amount, accountLogging);

        limitService.save(limit);

        SpendingMonthLimit spendingMonthLimit = limitService.findLimitByAccountId(accountLogging.getId());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/list.jsp");
        request.setAttribute("limit", spendingMonthLimit);
        requestDispatcher.forward(request, response);

    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Account accountLogging = (Account) session.getAttribute("accountLogging");

        SpendingMonthLimit existingLimit = null;
        existingLimit = limitService.findLimitByAccountId(accountLogging.getId());


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/edit.jsp");
        request.setAttribute("limit", existingLimit);
        requestDispatcher.forward(request, response);


    }

    private void editLimit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        Account accountLogging = (Account) session.getAttribute("accountLogging");

        SpendingMonthLimit limit = null;

        limit = limitService.findLimitByAccountId(accountLogging.getId());
        SpendingMonthLimit newLimit = null;

        int id = Integer.parseInt(request.getParameter("id"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            limitService.update(new SpendingMonthLimit(id, amount, accountLogging));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SpendingMonthLimit spendingMonthLimit = limitService.findLimitByAccountId(accountLogging.getId());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/list.jsp");
        request.setAttribute("limit", spendingMonthLimit);
        requestDispatcher.forward(request, response);

    }


    private void deleteLimit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            limitService.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<SpendingMonthLimit> limits = null;
        try {
            limits = limitService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/list.jsp");
        request.setAttribute("listLimit", limits);
        requestDispatcher.forward(request, response);


    }

    private void showFindById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/find.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findLimitById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        SpendingMonthLimit limit = null;
        try {
            limit = limitService.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if  (limit == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/find.jsp");
            request.setAttribute("message", "Couldn't find the limit");
            requestDispatcher.forward(request, response);



        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/find.jsp");
            request.setAttribute("limit", limit);
            request.setAttribute("result", true);
            requestDispatcher.forward(request, response);

        }
    }

    public void setNewLimit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Account accountLogging = (Account) session.getAttribute("accountLogging");

        SpendingMonthLimit limit = null;

        limit = limitService.findLimitByAccountId(accountLogging.getId());

        if (limit == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/create.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/edit.jsp");
            request.setAttribute("limit", limit);
            requestDispatcher.forward(request, response);
        }
    }
}
