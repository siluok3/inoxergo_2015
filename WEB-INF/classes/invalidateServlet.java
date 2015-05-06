

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Used to change the language. When a user changes language, language is set on session and then the servlet redirects to the right page on the web site.
 *
 * @author  freelancing.gr
 * @see     javax.servlet.http.HttpServlet
 * @see     Class
 */
public class invalidateServlet extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        String country = request.getParameter("country");
        String path = request.getParameter("path");

        String pagename= (String) session.getAttribute("pagename");
        session.setAttribute("lang", lang);
        session.setAttribute("country", country);
        session.setAttribute("pagename", pagename);




                if(path!=null) {
                path=path.replace("&lang="+lang,"");
                    if(path.contains("?")){
                        response.sendRedirect(path+"&lang="+lang+"&pagename="+pagename);
                    }else{
                        response.sendRedirect(path+"?lang="+lang+"&pagename="+pagename);
                    }
                }else{
                  {response.sendRedirect("index.jsp?lang="+lang+"&pagename="+pagename);}
                }




            }



     



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}