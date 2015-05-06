import engine.UserManagement;
import mail.smtpsend;
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
 * User forget password and ask to be sent to her by e-mail.
 *
 * @author  freelancing.gr
 * @see     org.apache.log4j.Logger
 * @see     javax.servlet.http.HttpServlet
 * @see     Class
 */
public class forgotPasswordServlet  extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    Logger cat = Logger.getLogger("forgotPasswordServlet");

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cat.info("Initializing forgotPasswordServlet ");
    }


/**
* @see                javax.servlet.http.HttpServletRequest
* @see                javax.servlet.http.HttpServletResponse
* @see                HttpSession
* @see                engine.UserManagement#getUserPassword(String)
* @see                smtpsend
* @see                mybeans.User
*/

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean reply=false;
        request.setCharacterEncoding("UTF-8");
        response.setContentType(CONTENT_TYPE);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        cat.info("Starting forgotPasswordServlet ");
        User user= new User();

        String email=null;
        email = request.getParameter("email");
        user.setEmail(email);

        String emailsubject = request.getParameter("Password recovery.");
        String emailbody1 = request.getParameter("Dear user ");
        String emailbody2 = request.getParameter("You can see below your  credentials");
        String emailbody3 = request.getParameter("You can login.");
        String emailbody4 = request.getParameter("Regards, freelancing.gr.");
        String emailbody5 = request.getParameter("Please dont reply to this email.");


        HttpSession session1 = request.getSession();

        // check this
        try{
        UserManagement um = new UserManagement();

        user = um.getUserPassword(email);
        cat.info(email);
	    if (user.getPassword()!=null)
	    {reply=true;}
        else
        {
         if (session1.getAttribute("precover") != null)
            session1.removeAttribute("precover");
            session1.setAttribute("precover","notamamber");
            session1.setAttribute("precoveremail",email);
              cat.info(email+"in->(!um.checkExistedUser(email))");
         response.sendRedirect(response.encodeRedirectURL("forgotten.jsp"));
         return;
        }


        }catch(Exception e){
                System.out.println("Exception in database connection while user retreive forgoten password: reply=" + reply+"kai user="+email+user.getEmail() +user.getUser_id());
                cat.error("Exception in database connection while user retreive forgoten password: reply=" + reply+"kai user="+email+user.getEmail() +user.getUser_id());
                cat.error(e.getMessage());
        }




        if(reply){

            cat.info("Sending password to:"+email+" - password:"+user.getPassword());


    smtpsend mailer= new smtpsend();

               String[] args=new String[18];
         args[0]="-M";
         args[1]="localhost"; //localhost    10.10.10.11
         args[2]="-U";
         args[3]="root@freelancing.gr";  // not nessassary
         args[4]="-P";
        // args[5]="zarty74";
         args[5]="";     // no pass
         args[6]="-s";
         args[7]=emailsubject; //subject
         args[8]="-o";
         args[9]="no-reply@freelancing.gr";
         args[10]="-To";
         args[11]=email;
         args[12]="-c";
         args[13]="webmaster@freelancing.gr";
         args[14]="-d";
         args[15]="-a";
         args[16]="-bo";
         args[17]="\n \n"+emailbody1+user.getFirstname()+ " "+ user.getSurname()+" \n \n "+
              emailbody2 +"\n Username: "+ email+"\n Password:"+user.getPassword()+
               "\n \n "+emailbody3+  "\n \n "+emailbody4+ "\n \n \n "+emailbody5+ "\n \n ";


         mailer.send(args);

            cat.info("email send to:"+email);


        if (session1.getAttribute("precover") != null)
            session1.removeAttribute("precover");
            session1.setAttribute("precover","emailsent");

         response.sendRedirect(response.encodeRedirectURL("forgotten.jsp"));
            return;
        }else{

        String sessionId = request.getSession().getId();
        cat.info("SessionId: " + sessionId);
        if (session1.getAttribute("error") != null)
            session1.removeAttribute("error");
            session1.setAttribute("error","trouble");
            response.sendRedirect(response.encodeRedirectURL("forgotten.jsp"));
            return;
        }

    }// servlet doget method close


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}