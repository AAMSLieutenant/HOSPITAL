package com.hospital.filter;

import com.hospital.dao.UserDAO;
import com.hospital.dao.UuserDao;
import com.hospital.interfaces.IAppointmentDao;
import com.hospital.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;







import static java.util.Objects.nonNull;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    private static final Logger logger=LoggerFactory.getLogger(AuthFilter.class);

    private AtomicReference<UuserDao> userDao;
    private String result;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {



//        PropertyConfigurator.configure("F:\\Rostislav\\EPAM\\HOSPITAL\\HOSPITAL\\src\\main\\resources\\log4j.properties");

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        logger.info("FILTER STARTED");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {


        request.setCharacterEncoding("UTF-8");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        String login = req.getParameter("login");
        String password = req.getParameter("password");
//--------------------------------------
        this.userDao=new AtomicReference<>();
        final Object userDao=req.getServletContext().getAttribute("userDao");
        this.userDao=(AtomicReference<UuserDao>)userDao;
        final HttpSession session =req.getSession();

        if((login!=null)&&(password!=null)){
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);
        }

//        System.out.println("current login:"+login);
//        System.out.println("current password:"+password);
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            System.out.println("NONNULL");

            try {
                result = this.userDao.get().authorize(login, password);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            if(result.equals("-1")){
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
            }
            else{
//                System.out.println("SUCCESS");
                req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);
            }
        }
        else{
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }


//--------------------------------------

//        @SuppressWarnings("unchecked")
//        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");
//
//        final HttpSession session = req.getSession();
//
//        //Logged user.
//        if (nonNull(session) &&
//                nonNull(session.getAttribute("login")) &&
//                nonNull(session.getAttribute("password"))) {
//
//            final User.ROLE role = (User.ROLE) session.getAttribute("role");
//
//            moveToMenu(req, res, role);
//
//
//        } else if (dao.get().userIsExist(login, password)) {
//
//            final User.ROLE role = dao.get().getRoleByLoginPassword(login, password);
//
//            req.getSession().setAttribute("password", password);
//            req.getSession().setAttribute("login", login);
//            req.getSession().setAttribute("role", role);
//
//            moveToMenu(req, res, role);
//
//        } else {
//
//            moveToMenu(req, res, User.ROLE.UNKNOWN);
//        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final User.ROLE role)
            throws ServletException, IOException {


        if (role.equals(User.ROLE.ADMIN)) {

            req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);

        } else if (role.equals(User.ROLE.USER)) {

            req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);

        } else {

            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }

}
