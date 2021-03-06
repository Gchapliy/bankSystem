package com.myProject.bankSystem.controller;

import com.myProject.bankSystem.dao.BankAccountDAO;
import com.myProject.bankSystem.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "deleteBankAccountOrder", urlPatterns = {"/delete"})
public class DeleteBankAccountOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = AppUtils.getStoredConnection(req);

        int orderId = Integer.parseInt(req.getParameter("id"));

        BankAccountDAO.deleteBankAccountOrderById(connection, orderId);

        resp.sendRedirect("/userPage?pageA=1&pageUsO=1&pageYO=1");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
