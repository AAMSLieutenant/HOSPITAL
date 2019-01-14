package com.hospital.filter;


import com.hospital.dao.UuserDao;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;







import static java.util.Objects.nonNull;

/**
 * @author Rostislav Stakhov
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    private static final Logger logger=LoggerFactory.getLogger(AuthFilter.class);

    private AtomicReference<UuserDao> userDao;
    private String result;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.info("FILTER STARTED");
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        logger.info("FILTER STARTED");
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

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

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
                req.setAttribute("login", login);
                req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);

            }
        }
        else{
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }

    }

    @Override
    public void destroy() {

    }

}
