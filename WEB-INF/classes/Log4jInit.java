

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html; charset=windows-1253";

    public void init(ServletConfig config) throws ServletException
    {
      super.init(config);
      System.out.println("Log4Jint started");
      String prefix = getServletContext().getRealPath("/");
      String file = getInitParameter("log4j-init-file");
      if (file != null)
      {
         PropertyConfigurator.configure(prefix + file);
      } else {
         System.out.println("File null");
      }
      System.out.println("Log4J Initialized");
      // -- code added by thanos - check to see if ProposalManagement Exists...

    }
}
