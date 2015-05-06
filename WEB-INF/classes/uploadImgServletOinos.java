import mybeans.User;

import java.io.File;
import java.util.List;
import java.util.Iterator;
import mybeans.Pageimg;
import engine.PageManagement;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: eugene
 * Time: 2:18:11 μμ
 * To change this template use File | Settings | File Templates.
 */
public class uploadImgServletOinos extends javax.servlet.http.HttpServlet {

        private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
        org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("uploadImgServletOinos");


    public void init(javax.servlet.ServletConfig config) throws javax.servlet.ServletException {
            super.init(config);
            cat.info("Initializing uploadImgServletOinos ");
        }



    /**
    * @see                javax.servlet.http.HttpServletRequest
    * @see                javax.servlet.http.HttpServletResponse
    * @see                javax.servlet.http.HttpSession
    * @see                org.apache.commons.fileupload.disk.DiskFileItemFactory
    * @see                org.apache.commons.fileupload.servlet.ServletFileUpload
    * @see                java.util.Iterator
    * @see                org.apache.commons.fileupload.FileItem
    */
        public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType(CONTENT_TYPE);
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache");
            cat.info("Starting uploadImgServlet ");

           // Check that we have a file upload request
    boolean isMultipart = org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request);
        cat.info("is Multipart oeo??? "+isMultipart);

        String pou="";
        String title="";
        String description="";
         java.io.File fullFile=null;
         org.apache.commons.fileupload.FileItem arxeio=null;
// Create a factory for disk-based file items
    org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();
    java.io.File dir = new java.io.File("/");  //// gia to server epano /tmp/
// Set factory constraints
    factory.setSizeThreshold(50000);
    factory.setRepository(dir);

// Create a new file upload handler
    org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);

// Set overall request size constraint
    upload.setSizeMax(1000000);

// Parse the request

        java.util.List  items = null;
        try {
            items = upload.parseRequest(request);
            cat.info("what items oeo??? "+items);
        } catch (org.apache.commons.fileupload.FileUploadException e) {
            e.printStackTrace();
            cat.error(e.getMessage());//check if this applies here
        }
     javax.servlet.http.HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");

       String pagename=request.getParameter("pagename");
       int imgid=Integer.parseInt(request.getParameter("imgid"));


        // Create a factory for disk-based file items
// Process the uploaded items
    java.util.Iterator iter = items.iterator();

    while (iter.hasNext()) {
      org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem) iter.next();
      cat.info("item mesa sto while = "+item);
      if (item.isFormField()) {   //
          cat.info("flag");
          String name = item.getFieldName();
          cat.info("name: " + name);
          String value = item.getString();
          if (name.equals("pou")){
             pou=value;
          }else if (name.equals("title")){
             title=value;
          }else if (name.equals("text")){
             description=value;
          }// if it is name, we can set it in request to thank the user
           else if(name.equals("name"))
        request.setAttribute("msg", "Thank You: " + item.getString());


    } else


 {
// the item must be an uploaded file save it to disk. Note that there
        // seems to be a bug in item.getName() as it returns the full path on
        // the client's machine for the uploaded file name, instead of the file
        // name only. To overcome that, I have used a workaround using
        // fullFile.getName().
    arxeio=item;
     cat.info(item.getFieldName());
      fullFile  = new java.io.File(item.getName());
  }}
      // cat.info("The filename" + fullFile.getName().substring(item.getName().lastIndexOf("\\")+1));   ////gia to server epano:
     cat.info(arxeio.getName() +"   "+fullFile.getName());   ////na ginei sholio

          String sExtension="";

            if(fullFile.getName().length()>3){
            sExtension = fullFile.getName().substring(fullFile.getName().lastIndexOf("."),fullFile.getName().length());
            }else{
            sExtension = "noimage";
            }

            sExtension= sExtension.toLowerCase();
            if(!(sExtension.endsWith(".jpg")||sExtension.endsWith(".gif")||sExtension.endsWith(".ai")||sExtension.endsWith(".png")))
            {
          String sessionId = request.getSession().getId();
            cat.info("SessionId: " + sessionId);
            if (session.getAttribute("anerror") != null)
                session.removeAttribute("anerror");
                session.setAttribute("anerror","wrongimgformat");

            cat.info("Wrong extension in file : "+sExtension);



            if(user.getEmail()!= null) {
             response.sendRedirect(response.encodeRedirectURL("admin/skales.jsp?success=notok"));
         }else{
//     response.sendRedirect(response.encodeRedirectURL("http://afianeswines.freelancing.gr/login.jsp"));     //allagi
         response.sendRedirect(response.encodeRedirectURL("localhost:8080/inoxergo/login.jsp"));
         }


        return;


            }else{

        /*from here creating a new directory for this user, if he does not have one*/
    String strManyDirectories ="C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/inoxergo/";  ////na ginei sholio
    String strManyDirectories2 ="uploadfiles/"+pagename;    ////na ginei sholio
     //  String strManyDirectories ="/usr/local/tomcat/webapps/afianeswines/"; ////gia to server epano:
      // String strManyDirectories2 ="uploadfiles/"+pagename; ////gia to server epano:
        try{
           cat.info("_________try to create file directory for page ________ "+pagename);
         // Create one directory
          boolean success = (new File(strManyDirectories+strManyDirectories2)).mkdirs();
         if(success){ cat.info("_________file directory created_________ ");}
        }
        catch (Exception e){//Catch exception if any
          cat.info("Error_____in creating file directory for page___________: "+pagename + e.getMessage());
        }
/*to here*/




     //  File savedFile = new File(getServletContext().getRealPath(strManyDirectories2+"/"), fullFile.getName().substring(item.getName().lastIndexOf("\\")+1));  ////gia to server epano:
     java.io.File savedFile = new java.io.File(getServletContext().getRealPath(strManyDirectories2+"/"),fullFile.getName());  ////na ginei sholio



            try {
                arxeio.write(savedFile);
            } catch (Exception e) {
                e.printStackTrace();


            }
          cat.info("saving:"+savedFile);

//  String filename= "http://afianeswines.freelancing.gr/"+strManyDirectories2+"/"+fullFile.getName().substring(item.getName().lastIndexOf("\\")+1); //// gia to server epano:
  //  /usr/local/tomcat/webapps/inoxergo
  String  filename="http://evo:8080/inoxergo/"+strManyDirectories2+"/"+fullFile.getName(); ////na ginei sholio

      filename = filename.replace(" ","%20");


        cat.info("thefilename:"+filename);
        cat.info("pou einai???"+pou);
        cat.info("title einai???"+title);
        cat.info("description einai???"+description);
                PageManagement pageman = new PageManagement() ;
                                try {
//                    int updateid= pageman.updatePageimg(id,filename);
                                    int fotoid= pageman.insertPageimg(pagename,imgid,filename,pou,title,description);
                                } catch (Exception e) { e.printStackTrace(); }


                if(user.getEmail()!= null) {
                   response.sendRedirect(response.encodeRedirectURL("admin/skales.jsp?success=ok"));
                  }else{
                  // response.sendRedirect(response.encodeRedirectURL("http://www.inoxergo.gr/login.jsp"));
                   response.sendRedirect(response.encodeRedirectURL("http://localhost:8080/inoxergo/login.jsp"));
                   }                                              //allagi http://afianeswines.freelancing.gr/login.jsp



       }

     return;



   // }


        }// servlet doget method close


        public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
            doGet(request, response);
        }


    }


