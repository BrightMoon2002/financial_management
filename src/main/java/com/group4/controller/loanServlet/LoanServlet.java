package com.group4.controller.loanServlet;

import com.group4.model.account.Account;
import com.group4.model.financial.Revenue;
import com.group4.model.financial.Spending;
import com.group4.model.loan.Interest;
import com.group4.model.loan.Loan;
import com.group4.model.loan.Loan_Status;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import com.group4.service.financial.Revenue.IRevenueService;
import com.group4.service.financial.Revenue.RevenueService;
import com.group4.service.loan.IInterestService;
import com.group4.service.loan.ILoanService;
import com.group4.service.loan.InterestService;
import com.group4.service.loan.ServiceLoan;
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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;

@WebServlet(name = "LoanServlet", value = "/loans")

public class LoanServlet extends HttpServlet {

    private ILoanService loanService = new ServiceLoan();
    private IInterestService iInterestService = new InterestService();
    private IAccountService accountService = new AccountService();
    private IRevenueService revenueService = new RevenueService();

    private ISpendingDAO spendingDAO = new SpendingDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "delete":
                showDeleteForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "search":
                showListLoan(req, resp);
                break;
            default:
                showListLoanById(req, resp);
                break;
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Loan loanDelete = loanService.findById(id);
            req.setAttribute("loanDelete", loanDelete);
            RequestDispatcher dispatcher;
            if (loanDelete == null) {
                dispatcher = req.getRequestDispatcher("view/loan/error.jsp");
            } else {
                dispatcher = req.getRequestDispatcher("view/loan/delete.jsp");
            }
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Loan loanEdit = loanService.findById(id);
            req.setAttribute("loanEdit", loanEdit);
            req.setAttribute("interest", iInterestService.findAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("view/loan/edit.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showListLoan(HttpServletRequest req, HttpServletResponse resp) {
        List<Loan> loanList;
        String pendingList = req.getParameter("searchPending");
        String rejectList = req.getParameter("searchReject");
        HttpSession session = req.getSession();
        Account accountLogging = (Account) session.getAttribute("accountLogging");
        if (pendingList == null && rejectList == null) {
            try {
                loanList = loanService.findAll();
                req.setAttribute("list", loanList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (rejectList == null){
            loanList = loanService.getPendingLoan();
            req.setAttribute("list", loanList);
        } else  {
            loanList = loanService.getRejectLoan(accountLogging.getId());
            req.setAttribute("list", loanList);
        }
        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("/view/loan/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListLoanById(HttpServletRequest req, HttpServletResponse resp) {
        List<Loan> loanList;
        HttpSession session = req.getSession();
        Account accountLogging = (Account) session.getAttribute("accountLogging");
        int idRoll = accountLogging.getRole().getId();
        if (idRoll == 2) {
            loanList = loanService.findAllById(accountLogging.getId());
            req.setAttribute("list", loanList);
        } else {
            try {
                loanList = loanService.findAll();
                req.setAttribute("list", loanList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("/view/loan/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("interestList", iInterestService.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/loan/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
                case "create":
                    creatNewLoan(req, resp);
                    break;
                case "delete":
                    deleteLoan(req, resp);
                    break;
                case "edit":
                    editLoan(req, resp);
                    break;
                case "search":
                default:
                    showListLoan(req, resp);
                    break;
            }
        }

    private void deleteLoan(HttpServletRequest req, HttpServletResponse resp) {
        int idLoan = Integer.parseInt(req.getParameter("id"));
        try {
            Loan loanDelete = loanService.findById(idLoan);
            if (loanDelete == null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("view/loan/error.jsp");
                try {
                    dispatcher.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                loanService.deleteById(loanDelete.getId());
                try {
                    resp.sendRedirect("/loans");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void editLoan (HttpServletRequest req, HttpServletResponse resp) {
        int id_loan = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        Account accountLogging = (Account) session.getAttribute("accountLogging");
        int id_account = 0;
        try {
            id_account = loanService.findById(id_loan).getAccount().getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Account account = accountService.findById(id_account);
            if (accountLogging.getRole().getId() == 1) {
                try {
                    Loan loan = loanService.findById(id_account);
                    if (loan == null) {
                        RequestDispatcher dispatcher = req.getRequestDispatcher("view/book/error.jsp");
                        try {
                            dispatcher.forward(req, resp);
                        } catch (ServletException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String name = req.getParameter("name");
                        Date startOfLoan = Date.valueOf(req.getParameter("startOfLoan"));
                        Date endOfLoan = Date.valueOf(req.getParameter("endOfLoan"));
                        double amount = Double.parseDouble(req.getParameter("amount"));
                        int idInterest = Integer.parseInt(req.getParameter("idInterest"));
                        String status = req.getParameter("status");
                        Account account1 = accountService.findById(id_account);
                        Interest interest = iInterestService.findById(idInterest);
                        int id_status;

                        if (status.equals("dont paid")) {
                            id_status = 2;
                            Loan_Status loan_status = new Loan_Status(id_status, "dont paid");

                            Loan loanUpdate = new Loan(id_loan, startOfLoan, endOfLoan, amount, account1, interest, loan_status);

                            loanService.update(loanUpdate);
                            String type = "loan";
                            String description = "loan " + startOfLoan;
                            Date date = Date.valueOf(LocalDate.now());
                            Revenue revenue = new Revenue(type,description, amount, date, account);
                            revenueService.save(revenue);




                            String typeS = "loan " + account.getName();
                            String descriptionS = "loan " + startOfLoan;
                            Date dateS = Date.valueOf(LocalDate.now());
                            Account accountAdmin = accountService.findById(1);
                            Spending spending = new Spending(typeS,descriptionS, amount, date, accountAdmin);
                            spendingDAO.save(spending);

                        } else  if (status.equals("paid")) {
                            id_status = 1;
                            Loan_Status loan_status = new Loan_Status(id_status, status);

                            Loan loanUpdate = new Loan(id_loan, startOfLoan, endOfLoan, amount, account, interest, loan_status);

                            loanService.update(loanUpdate);

                            loanService.update(loanUpdate);
                            String type = "paid loan";
                            String description = "paid loan " + startOfLoan;
                            Date date = Date.valueOf(LocalDate.now());
                            Account accountAdmin = accountService.findById(1);
                            if (interest.getId()==1) {
                                amount = amount + amount * 0.1;
                            } else {
                                amount = amount + amount * 0.08 * 3;
                            }
                            Revenue revenue = new Revenue(type,description, amount, date, accountAdmin);
                            revenueService.save(revenue);




                            String typeS = "paid loan " + account.getName();
                            String descriptionS = "paid loan " + startOfLoan;
                            Date dateS = Date.valueOf(LocalDate.now());

                            Spending spending = new Spending(typeS,descriptionS, amount, date, account);
                            spendingDAO.save(spending);
                        } else if (status.equals("pending")) {
                            id_status = 4;
                            Loan_Status loan_status = new Loan_Status(id_status, status);

                            Loan loanUpdate = new Loan(id_loan, startOfLoan, endOfLoan, amount, account, interest, loan_status);

                            loanService.update(loanUpdate);
                        } else if (status.equals("over date")){
                            id_status = 3;
                            Loan_Status loan_status = new Loan_Status(id_status, status);

                            Loan loanUpdate = new Loan(id_loan, startOfLoan, endOfLoan, amount, account, interest, loan_status);

                            loanService.update(loanUpdate);
                        } else {
                            id_status = 5;
                            Loan_Status loan_status = new Loan_Status(id_status, status);

                            Loan loanUpdate = new Loan(id_loan, startOfLoan, endOfLoan, amount, account, interest, loan_status);

                            loanService.update(loanUpdate);
                        }


                        try {
                            resp.sendRedirect("/loans");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                resp.sendRedirect("/loans");
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }


    }

    private void creatNewLoan(HttpServletRequest req, HttpServletResponse response) {
        double amount = Double.parseDouble(req.getParameter("amount"));
        Date startOfLoan = Date.valueOf(req.getParameter("startOfLoan"));
        int idInterest = Integer.parseInt(req.getParameter("idInterest"));
        HttpSession session = req.getSession();
       Account account = (Account) session.getAttribute("accountLogging");

        try {
            account = accountService.findById(account.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Interest interest = iInterestService.findById(idInterest);
            Loan_Status loan_status = new Loan_Status(4, "pending");
            if (idInterest == 1) {
                LocalDate localDate = startOfLoan.toLocalDate();
                LocalDate endOfLoan = localDate.plusMonths(1);
                Date endOfLoan1 = Date.valueOf(endOfLoan);
                Loan loan = new Loan(startOfLoan, endOfLoan1, amount, account, interest,loan_status);
                loanService.save(loan);
            } else {
                LocalDate localDate = startOfLoan.toLocalDate();
                LocalDate endOfLoan = localDate.plusMonths(3);
                Date endOfLoan1 = Date.valueOf(endOfLoan);
                Loan loan = new Loan(startOfLoan, endOfLoan1, amount, account, interest,loan_status);
                loanService.save(loan);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            response.sendRedirect("/loans");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
