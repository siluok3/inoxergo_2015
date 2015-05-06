package engine;

import mybeans.Pagetext;
import mybeans.Pageimg;

import java.util.Vector;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: evgenia
 * Date: 18 Φεβ 2010
 * Time: 5:11:26 μμ
 * To change this template use File | Settings | File Templates.
 */
public class PageManagement {
       org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("PageManagement.class");

         java.sql.ResultSet rs = null;

       public PageManagement () {}



    /**
      * Gets a Pagetext by pagename, lang and its parid.
      *
      * @param pagename     field from the table pagetext

     *  @param parid         field from the table pagetext
      * @return              a Pagetext bean
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.Pagetext
      */
        public Pagetext getPagetext(String pagename, int parid)  throws Exception {
            final String methodsig = "PageManagement.getPagetext()";
            cat.info("MethodStart:" + methodsig);
            DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("SELECT *  FROM pagetext WHERE pagename='"+pagename +"' AND parid="+parid);

          Pagetext pt = new Pagetext();

           try {
                  rs = dbHandler.lookup();
            while (rs.next()) {
                 pt.setId(rs.getInt(1));
                 pt.setPagename(rs.getString(2));
                 pt.setParid(rs.getInt(3));
                 pt.setText(rs.getString(4));
                       }
               }

           catch (Exception e) {
               cat.info("exception in PageManagement.getPageText" + e.getMessage());
               e.printStackTrace();
           }

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
            return pt;
       }

    /**
      * Gets a Pageimg by pagename and its imgid.
      *
      * @param  pagename     field from the table pageimg
     *  @param imgid         field from the table pageimg
      * @return              a Pageimg bean
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.Pagetext
      */
        public Pageimg getPageimg(String pagename, int imgid)  throws Exception {
            final String methodsig = "PageManagement.getPageimg()";
            cat.info("MethodStart:" + methodsig);
            DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("SELECT *  FROM pageimg WHERE pagename='"+pagename +"' AND imgid="+imgid);

          Pageimg pimg = new Pageimg();

           try {
                  rs = dbHandler.lookup();
                while (rs.next()) {
                 pimg.setId(rs.getInt(1));
                 pimg.setPagename(rs.getString(2));
                 pimg.setImgid(rs.getInt(3));
                 pimg.setImgpath(rs.getString(4));
           
                       }
               }

           catch (Exception e) {
               cat.info("exception in PageManagement.getPageimg" + e.getMessage());
               e.printStackTrace();
           }

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
            return pimg;
       }


    /**
      * Gets a Pageimg by pagename and its imgid.
      *
      * @param  pagename     field from the table pageimg
     *  @param imgid         field from the table pageimg

      * @return              a Pageimg bean
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.Pagetext
      */
        public Pageimg getExoimg(String pagename, int imgid)  throws Exception {
            final String methodsig = "PageManagement.getPageimg()";
            cat.info("MethodStart:" + methodsig);
            DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("SELECT *  FROM pageimg WHERE pagename='"+pagename +"' AND imgid="+imgid+" AND pou='eksw'");

          Pageimg pimg = new Pageimg();

           try {
                  rs = dbHandler.lookup();
                while (rs.next()) {
                 pimg.setId(rs.getInt(1));
                 pimg.setPagename(rs.getString(2));
                 pimg.setImgid(rs.getInt(3));
                 pimg.setImgpath(rs.getString(4));
                 pimg.setPou(rs.getString(5));
                 pimg.setTitle(rs.getString(6));
                 pimg.setDescription(rs.getString(7));

                       }
               }

           catch (Exception e) {
               cat.info("exception in PageManagement.getPageimg" + e.getMessage());
               e.printStackTrace();
           }

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
            return pimg;
       }

    /**
      * Gets all images for Page by pagename and its imgid.
      *
      * @param  pagename     field from the table pageimg
     *  @param imgid         field from the table pageimg
      * @return              a Pageimg bean
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.Pagetext
      */
        public Vector getAllPageimg(String pagename, int imgid)  throws Exception {
            final String methodsig = "PageManagement.getAllPageimg()";
            cat.info("MethodStart:" + methodsig);
            DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("SELECT *  FROM pageimg WHERE pagename='"+pagename +"' AND imgid="+imgid);
             rs = dbHandler.lookup();
            Vector imagesvec = new Vector();

                while (rs.next()) {
                 Pageimg pimg = new Pageimg();

                 pimg.setId(rs.getInt(1));
                 pimg.setPagename(rs.getString(2));
                 pimg.setImgid(rs.getInt(3));
                 pimg.setImgpath(rs.getString(4));
                 pimg.setPou(rs.getString(5));
                 pimg.setTitle(rs.getString(6));
                 pimg.setDescription(rs.getString(7));

                 imagesvec.addElement(pimg);
                       }

                 dbHandler.close();
                 cat.info("MethodEnd:" + methodsig);
                  return imagesvec;
               }

/**
      * Gets all images for Page by pagename
      *
      * @param  pagename     field from the table pageimg

      * @return              a Pageimg bean
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.Pagetext
      */
        public Vector getAllFoldersPageimg(String pagename)  throws Exception {
            final String methodsig = "PageManagement.getAllFoldersPageimg()";
            cat.info("MethodStart:" + methodsig);
            DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("SELECT *  FROM pageimg WHERE pagename='"+pagename +"'");
             rs = dbHandler.lookup();
            Vector imagesvec = new Vector();

                while (rs.next()) {
                 Pageimg pimg = new Pageimg();

                 pimg.setId(rs.getInt(1));
                 pimg.setPagename(rs.getString(2));
                 pimg.setImgid(rs.getInt(3));
                 pimg.setImgpath(rs.getString(4));
                 pimg.setPou(rs.getString(5));
                 pimg.setTitle(rs.getString(6));
                 pimg.setDescription(rs.getString(7));

                 imagesvec.addElement(pimg);
                       }

                 dbHandler.close();
                 cat.info("MethodEnd:" + methodsig);
                  return imagesvec;
               }


 /**
      * Inserts a Pageimg by pagename and its imgid.
      *
      * @param  pagename     the name of the page
      * @param  imgid     the number of row for the foto
      * @param  imgpath    the foto path
      * @param  pou    eswteriki i exwteriki eikona
      * @param  title    titlos apo tin eikona
      * @param  description    perigrafi apo tin eikona
      * @return              the id of the new Pageimg
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.Pagetext
      */
        public int insertPageimg(String pagename, int imgid, String imgpath, String pou, String title, String description)  throws Exception {
            final String methodsig = "PageManagement.insertPageimg()";

         cat.info("MethodStart:" + methodsig);
         DBHandler dbHandler = new DBHandler(cat);
         Pageimg pimg=new Pageimg();
     if(pou.equals("eksw")){
         pimg= this.getExoimg(pagename,imgid);
             if (!(pimg==null)&& (pimg.getId()!=0)){
             dbHandler.setQueryString("UPDATE pageimg SET imgpath='"+imgpath+"',title= '"+title+"', description='"+description+"' WHERE id=? ");
             dbHandler.executeUpdate(String.valueOf(pimg.getId()));
             }else{

         dbHandler.setQueryString("INSERT INTO pageimg (pagename, imgid, imgpath, pou, title, description)" +
                 " VALUES('" + pagename + "'," + imgid + ",?, '"+ pou + "','"+ title +"','"+ description +"')");
         dbHandler.executeUpdate(imgpath); }

     }   else{

         dbHandler.setQueryString("INSERT INTO pageimg (pagename, imgid, imgpath, pou, title, description)" +
                 " VALUES('" + pagename + "'," + imgid + ",?, '"+ pou + "','"+ title +"','"+ description +"')");
         dbHandler.executeUpdate(imgpath);
     }
         String sql2 = "SELECT id FROM pageimg WHERE pagename='" + pagename
                 + "' AND imgid=" + imgid +" AND imgpath='" + imgpath +"'";
         dbHandler.setQueryString(sql2);
         rs = dbHandler.lookup();

         int pigmid = 0;
         while (rs.next()) {
             pigmid = rs.getInt(1);
         }
         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return pigmid;
     }

       /**
   * Updates an pageimg to the db
   *
   * @param id       the Pageimg id
   * @param path       the image path
   * @return           1 if the entry was updated or else 0
   * @throws Exception    if an error occurs in code
   * @see             Pageimg
   */
   public int updatePageimg(int id,String path) throws Exception {
         final String methodsig = "PageManagement.updatePageimg()";
         cat.info("MethodStart:" + methodsig);
         DBHandler dbHandler = new DBHandler(cat);
         int reply = 0;
         dbHandler.setQueryString("UPDATE pageimg SET  imgpath='" + path +
                 "'  WHERE id=? ");
         reply = dbHandler.executeUpdate(String.valueOf(id));

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return reply;
     }

    /**
   * Updates an pagetext to the db
   *
   * @param parid       the Paragraph id
   * @param link      the Pagename
   * @param text       the text of a paragraph in a page
   * @return           1 if the entry was updated or else 0
   * @throws Exception    if an error occurs in code
   * @see             Pagetext
   */
   public int updatePagetext(int parid,String link,String text) throws Exception {
         final String methodsig = "PageManagement.updatePagetext()";
         cat.info("MethodStart:" + methodsig);
         DBHandler dbHandler = new DBHandler(cat);
         int reply = 0;
         dbHandler.setQueryString("UPDATE pagetext SET  text='" + text +
                 "'  WHERE parid=? AND pagename='"+link+ "'");
         reply = dbHandler.executeUpdate(String.valueOf(parid));

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return reply;
     }

     /** Deletes user images thumbnails names from db and calls method to erase them from directory for oinos.jsp files*/
       public boolean deleteImg(int id, String imgpath) throws Exception {
           final String methodsig = "PageManagement.deleteImg()";
           cat.info("MethodStart:" + methodsig);

          deleteImgfromDir(imgpath);

         //  deleteUAFilefromDir("https://freelancing.gr/userportfolio/"+userid+"/"+thumbnailname);

           DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("DELETE FROM `pageimg` WHERE `id`="+ id+" AND imgpath='"+imgpath+"'");

           boolean check = dbHandler.execute();

           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return check;

       }

     /** erases user thumbnail files from user personal directory for portfolio thumbnails*/
       public void deleteImgfromDir(String fileName) {
         String path = fileName.replace("http://www.inoxergo.gr","/usr/local/tomcat/webapps/inoxergo");
             cat.info("Please delete... "+path);
       try {
                          
         // Construct a File object for the file to be deleted.
             File target = new File(path);

             if (!target.exists()) {
               cat.info("File " + path
                   + " not present to begin with!");
               return;
             }

           boolean success= target.delete();
           if(! success) {
           cat.info("Fail to delete");
           }else{
           cat.info("File deleted");}


       } catch (SecurityException e) {
         cat.info("Unable to delete " + path + "("
             + e.getMessage() + ")");
       }

     }

}

