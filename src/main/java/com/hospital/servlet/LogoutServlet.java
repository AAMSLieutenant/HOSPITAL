package com.hospital.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final HttpSession session = req.getSession();

        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");

        System.out.println("CURRENT PATH: "+req.getContextPath() + "/");
//        resp.sendRedirect(super.getServletContext().getContextPath());
        resp.sendRedirect(req.getContextPath() + "/");
//        req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
    }

}
