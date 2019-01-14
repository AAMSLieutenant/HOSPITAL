package com.hospital.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author Rostislav Stakhov
 * Logout servlet
 */
public class LogoutServlet extends HttpServlet {

    private static final Logger logger= LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final HttpSession session = req.getSession();

        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");

        logger.info("CURRENT PATH: "+req.getContextPath() + "/");

        resp.sendRedirect(req.getContextPath() + "/");

    }

}
