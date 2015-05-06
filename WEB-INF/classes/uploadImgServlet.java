import mybeans.User;
import mybeans.Pageimg;

import java.io.File;

import engine.PageManagement;

/**
 * Uploads  images.
 *
 * @author  freelancing.gr
 * @see     org.apache.log4j.Logger
 * @see     javax.servlet.http.HttpServlet
 * @see     Class
 */
public class uploadImgServlet extends javax.servlet.http.HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("uploadImgServlet");

    public void init(javax.servlet.ServletConfig config) throws javax.servlet.ServletException {
        super.init(config);
        cat.info("Initializing uploadImgServlet ");
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
    } catch (org.apache.commons.fileupload.FileUploadException e) {
        e.printStackTrace();
        cat.error(e.getMessage());//check if this applies here
    }
 javax.servlet.http.HttpSession session = request.getSession();
    User user=(User) session.getAttribute("user");

   String pagename=request.getParameter("pagename");
   int imgid=Integer.parseInt(request.getParameter("imgid"));
   int id=Integer.parseInt(request.getParameter("id"));



       

// int pageid=(Integer) session.getAttribute("pageid");
//    int domainid=(Integer) session.getAttribute("domainid");
//    int elemid=(Integer) session.getAttribute("elemid");
//      session.removeAttribute("pageid");
//      session.removeAttribute("domainid");
//      session.removeAttribute("elemid");
//

    // Create a factory for disk-based file items
// Process the uploaded items
java.util.Iterator iter = items.iterator();
while (iter.hasNext()) {
  org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem) iter.next();


if (item.isFormField()) {
       // get the name of the field
String fieldName = item.getFieldName();

// if it is name, we can set it in request to thank the user
if(fieldName.equals("name"))
    request.setAttribute("msg", "Thank You: " + item.getString());
} else {
// the item must be an uploaded file save it to disk. Note that there
    // seems to be a bug in item.getName() as it returns the full path on
    // the client's machine for the uploaded file name, instead of the file
    // name only. To overcome that, I have used a workaround using
    // fullFile.getName().
    java.io.File fullFile  = new java.io.File(item.getName());

  // cat.info("The filename" + fullFile.getName().substring(item.getName().lastIndexOf("\\")+1));   ////gia to server epano:
 cat.info(item.getName() +"   "+fullFile.getName());   ////na ginei sholio

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
         response.sendRedirect(response.encodeRedirectURL("admin/"+pagename+".jsp?success=notok"));
     }else{
//     response.sendRedirect(response.encodeRedirectURL("http://afianeswines.freelancing.gr/login.jsp"));     //allagi
     response.sendRedirect(response.encodeRedirectURL("localhost:8080/afianeswines/login.jsp"));
     }


    return;


        }else{

    /*from here creating a new directory for this user, if he does not have one*/
String strManyDirectories ="C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/afianeswines/";  ////na ginei sholio
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
            item.write(savedFile);
        } catch (Exception e) {
            e.printStackTrace();


        }
      cat.info("saving:"+savedFile);

//  String filename= "http://afianeswines.freelancing.gr/"+strManyDirectories2+"/"+fullFile.getName().substring(item.getName().lastIndexOf("\\")+1); //// gia to server epano:

String filename="http://evo:8080/afianeswines/"+strManyDirectories2+"/"+fullFile.getName(); ////na ginei sholio

  filename = filename.replace(" ","%20");


    cat.info("thefilename:"+filename);

   PageManagement pageman = new PageManagement() ;
            try {
                int updated= pageman.updatePageimg(id,filename);
            } catch (Exception e) { e.printStackTrace(); }

            if(user.getEmail()!= null) {
               response.sendRedirect(response.encodeRedirectURL("admin/"+pagename+".jsp?success=ok"));
              }else{
               response.sendRedirect(response.encodeRedirectURL("http://afianeswines.freelancing.gr/login.jsp"));
               response.sendRedirect(response.encodeRedirectURL("http://localhost:8080/afianeswines.jsp"));
               }                                              //allagi http://afianeswines.freelancing.gr/login.jsp



   }

 return;



}
}


    }// servlet doget method close


    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }


}
