import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * Uploads user assessment files for their user accounts.
 *
 * @author  freelancing.gr
 * @see     org.apache.log4j.Logger
 * @see     javax.servlet.http.HttpServlet
 * @see     Class
 */

public class uploadPortfolioThumbnailServlet extends javax.servlet.http.HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("uploadPortfolioThumbnailServlet");

    public void init(javax.servlet.ServletConfig config) throws javax.servlet.ServletException {
        super.init(config);
        cat.info("Initializing uploadPortfolioThumbnailServlet ");
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
        cat.info("Starting uploadPortfolioThumbnailServlet ");
        response.setContentType(CONTENT_TYPE);
    mybeans.ProsforesBean prosfora = new mybeans.ProsforesBean();
// Parse the request
      
       
       // Check that we have a file upload request
boolean isMultipart = org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request);

// Create a factory for disk-based file items
org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();
java.io.File dir = new java.io.File("/tmp/");  //// gia to server epano /tmp/
// Set factory constraints
factory.setSizeThreshold(50000);
factory.setRepository(dir);

// Create a new file upload handler
org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);

// Set overall request size constraint
upload.setSizeMax(1000000);


        java.util.List  items = null;
        try {
            items = upload.parseRequest(request);
        } catch (org.apache.commons.fileupload.FileUploadException e) {
            e.printStackTrace();
            cat.error(e.getMessage());//check if this applies here
        }
    javax.servlet.http.HttpSession session = request.getSession();
     String thumbnailstate ="";
             //=session.getAttribute("thumbnailstate").toString() ;
     String correctfilename="";

//       cat.info(request.getAttribute("thumbnailstate"));
//     javax.servlet.http.HttpSession session = request.getSession();
//     session.setAttribute("thumbnailstate",    request.getAttribute("thumbnailstate"));
//     String info = session.getAttribute("thumbnailstate").toString();
//     cat.info("ttt"+ info );
//     String thumbnailstate= info ;
//     String correctfilename="";
//     if(thumbnailstate.equals("update")) { correctfilename=session.getAttribute("correctfilename").toString(); }




        // Create a factory for disk-based file items
// Process the uploaded items
java.util.Iterator iter = items.iterator();
while (iter.hasNext()) {
  org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem) iter.next();


    if (item.isFormField()) {
       	// get the name of the field
	String fieldName = item.getFieldName();
    if(fieldName.equals("thumbnailstate")){
     thumbnailstate = item.getString();
        cat.info("einai thumb= "+thumbnailstate );

    }

     if(thumbnailstate.equals("update")) { correctfilename=session.getAttribute("correctfilename").toString(); }

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

         prosfora.setThumbpath(fullFile.getName());
         prosfora.setImgpath(fullFile.getName());



    cat.info("The filename" + fullFile.getName().substring(item.getName().lastIndexOf("\\")+1));   ////gia to server epano:
   //    cat.info(item.getName() +"   "+fullFile.getName());   ////na ginei sholio

         String sExtension = ".no";
        if((fullFile.getName()!=null)&&(!(fullFile.getName().equals("")))&&(!(fullFile.getName().equals("null")))){
  // get the extension
         sExtension = fullFile.getName().substring(fullFile.getName().lastIndexOf("."),fullFile.getName().length());
        }
        

        sExtension= sExtension.toLowerCase();
        if(!(sExtension.endsWith(".jpg")||sExtension.endsWith(".ai")||sExtension.endsWith(".png")))
        {

     cat.info("Wrong extension in file : "+sExtension);



                 response.sendRedirect(response.encodeRedirectURL("admin/arxiki.jsp?fileuploaded="+correctfilename+"&wrongname=yes"));



     return;


        }else{

            /*from here creating a new directory for this user, if he does not have one*/
   //    String strManyDirectories ="C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/inoxergo/images/slides/";  ////na ginei sholio
    String strManyDirectories ="/usr/local/tomcat/webapps/inoxergo/images/slides/"; ////gia to server epano:




      // saving the img temporarily in pastprojectsportfolio directory
       File savedFile = new File(getServletContext().getRealPath("/images/slides/big/"), fullFile.getName().substring(item.getName().lastIndexOf("\\")+1));  ////gia to server epano:
       File savedFilesmall = new File(getServletContext().getRealPath("/images/slides/small/"), fullFile.getName().substring(item.getName().lastIndexOf("\\")+1));  ////gia to server epano:
//   java.io.File savedFile = new java.io.File(getServletContext().getRealPath("images/slides/big/"),fullFile.getName());  ////na ginei sholio
//  java.io.File savedFilesmall = new java.io.File(getServletContext().getRealPath("images/slides/small/"),fullFile.getName());  ////na ginei sholio

         try {
                item.write(savedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
          cat.info("saving:"+savedFile);
          cat.info("saving:"+savedFilesmall);

            

            //RESIZE START
           int width = 300;
           int widthsmall = 30;
           int height = 225;
           int heightsmall = 23;

            // Server Location of the image
                String imageLoc = savedFile.getPath();
            cat.info("imageLoc"+imageLoc);

            try {
               // Read the original image from the Server Location
               BufferedImage bufferedImage = ImageIO.read(new File(imageLoc));
                cat.info("bufferedImage"+bufferedImage.toString());

                      // Write the image
              boolean isres=  ImageIO.write(createResizedCopy(bufferedImage, width, height), sExtension.replace(".",""), savedFile);
              boolean isres2=  ImageIO.write(createResizedCopy(bufferedImage, widthsmall, heightsmall), sExtension.replace(".",""), savedFilesmall);
                cat.info("  ImageIO.write"+  isres);
                cat.info("  ImageIO.write"+  isres2);


           } catch (Exception e) {
               cat.error("Problem with image: " + fullFile.getName() + e);
           }

          //END RESIZE





      String filename= fullFile.getName().substring(item.getName().lastIndexOf("\\")+1); //// gia to server epano:

     //  String filename=fullFile.getName(); ////na ginei sholio



        cat.info("thefilename:"+filename);





       response.sendRedirect(response.encodeRedirectURL("admin/arxiki.jsp?fileuploaded="+filename+"&isok=ok"));


     return;

     }




      }


}

    }// servlet doget method close




    	BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight) {
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = scaledBI.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}




    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }


}
