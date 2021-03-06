package com.myProject.bankSystem.handler;

import com.myProject.bankSystem.controller.PaymentTransfersServlet;
import com.myProject.bankSystem.utils.LocaleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "errorHandler", urlPatterns = "/errorHandler")
public class ErrorHandler extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ErrorHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isError = true;
        boolean isException = false;
        boolean isAccessDenied = false;

        // Analyze the servlet exception
        Integer statusCode = (Integer)
                req.getAttribute("javax.servlet.error.status_code");

        req.setAttribute("errorCode", statusCode);

        logger.error("error code " + statusCode);

        LocaleUtils.setLocaleHeaderAndFooter(req);
        LocaleUtils.setLocaleError(req, isError, isException, isAccessDenied);

        req.getRequestDispatcher("templates/error.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
