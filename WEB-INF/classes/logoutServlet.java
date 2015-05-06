

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

 /**
 * Removes the user data from session
 *
 * @author  freelancing.gr
 * @see     org.apache.log4j.Logger
 * @see     javax.servlet.http.HttpServlet
 * @see     Class
 */
public class logoutServlet extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    Logger cat = Logger.getLogger("logoutServlet");

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType(CONTENT_TYPE);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");

        cat.info("Starting logoutServlet...");

        HttpSession session = request.getSession();
        cat.info(" logoutServlet - login out: username = "+session.getAttribute(("username")));

        session.removeAttribute("admin");
        session.removeAttribute("user");
        session.removeAttribute("error");
        session.removeAttribute("pagename");
        session.removeAttribute("lang");

        session.invalidate();

        response.sendRedirect(response.encodeRedirectURL("index.jsp"));

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}