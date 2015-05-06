import engine.UserManagement;
import mybeans.User;
import org.apache.log4j.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
  /**
 * User log in.
 *
 * @author  freelancing.gr
 * @see     org.apache.log4j.Logger
 * @see     javax.servlet.http.HttpServlet
 * @see     Class
 */
public class loginServlet extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    Logger cat = Logger.getLogger("loginServlet");

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }


/**
* @see                javax.servlet.http.HttpServletRequest
* @see                javax.servlet.http.HttpServletResponse
* @see                HttpSession
* @see                engine.UserManagement#AuthorizeUser(String,String)
* @see                engine.UserManagement#checkAdmin(String)
* @see                engine.UserManagement#checkUser(String)
* @see                mybeans.User
*/
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        request.setCharacterEncoding("UTF-8");
        response.setContentType(CONTENT_TYPE);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");



        /* Get email and password from login form */
        String userName = request.getParameter("username");     //    cat.info("userName========"+userName);

        String password = request.getParameter("password");   // cat.info("password========"+password);

        cat.info("");
        cat.info("Starting LoginServlet ");
        cat.info("Username: " + userName);


        try
        {


        UserManagement um = new UserManagement();

        int usid=um.getuserid(userName);
        User user =um.getUser(usid);



            if(um.AuthorizeUser(userName, password))
            {

                HttpSession session = request.getSession();
                session.setAttribute("user", user); //set user to session to be easily accessed from all over the web site


                try {

                if(um.checkAdmin(userName)){

                     cat.info("Username: " + userName +" is admin..");
                    session.setAttribute("admin", "OK");
                     response.sendRedirect(response.encodeRedirectURL("admin/adminlogged.jsp"));

                }


            }catch ( Exception e)
            {
                cat.error("loginServlet: exception in checkAdmin  ", e);
                String error="Error on System. Try again later.";
                response.sendRedirect(response.encodeRedirectURL("index.jsp?error="+error));

            }
            }else{
                cat.info("loginServlet: wrong password in authorizeUser  ");
                String error="Wrong password. Try again later";
                response.sendRedirect(response.encodeRedirectURL("index.jsp?error="+error));
            }




        } catch ( Exception e)
        {
            cat.error("loginServlet: exception in authorizeUser  ", e);
            String error="Error on System. Try again later.";
            response.sendRedirect(response.encodeRedirectURL("index.jsp?error="+error));





        }


    }







}



