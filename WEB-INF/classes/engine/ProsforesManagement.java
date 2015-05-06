package engine;

import mybeans.ProsforesBean;

import java.util.Vector;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: evgenia
 * Date: 18 Φεβ 2010
 * Time: 5:11:26 μμ
 * To change this template use File | Settings | File Templates.
 */
public class ProsforesManagement {
       org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("ProsforesManagement.class");

         java.sql.ResultSet rs = null;

       public ProsforesManagement () {}




    /**
      * Gets a Prosfora by id.
      *
     *  @param id         field from the table prosfores
     * @return              a ProsforesBean
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.ProsforesBean
       */
        public ProsforesBean getProsfora(int id)  throws Exception {
            final String methodsig = "ProsforesManagement.getProsfora()";
            cat.info("MethodStart:" + methodsig);
            DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("SELECT *  FROM prosfores WHERE id="+ id);

          ProsforesBean prosfora = new ProsforesBean();

           try {
                  rs = dbHandler.lookup();
                while (rs.next()) {
                 prosfora.setId(rs.getInt(1));
                 prosfora.setImgpath(rs.getString(2));
                 prosfora.setThumbpath(rs.getString(3));
                 prosfora.setTitle(rs.getString(4));
                 prosfora.setText(rs.getString(5));
                      }
               }

           catch (Exception e) {
               cat.info("exception in ProsforesManagement.getProsfora" + e.getMessage());
               e.printStackTrace();
           }

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
            return prosfora;
       }

 /**
      * Inserts a new Prosfora in table Prosfores.
      *
      * @param  prosfora     the ProsforesBean data

      * @return              the id of the new Prosfora
     * @throws Exception    if an error occurs in code
      * @see                 mybeans.ProsforesBean
      */
        public int insertProsfora(ProsforesBean prosfora)  throws Exception {
            final String methodsig = "ProsforesManagement.insertProsfora()";

         cat.info("MethodStart:" + methodsig);
         DBHandler dbHandler = new DBHandler(cat);
         dbHandler.setQueryString("INSERT INTO prosfores (imgpath, thumbpath, title, text)" +
                 " VALUES('" + prosfora.getImgpath() + "','" + prosfora.getThumbpath() + "',?, '"+ prosfora.getText()+"')");
         dbHandler.executeUpdate(prosfora.getTitle());

         String sql2 = "SELECT id FROM prosfores WHERE imgpath='" + prosfora.getImgpath()
                 + "' AND thumbpath='" + prosfora.getThumbpath()+"' AND title='" + prosfora.getTitle()+"'";
         dbHandler.setQueryString(sql2);
         rs = dbHandler.lookup();

         int prosfid = 0;
         while (rs.next()) {
             prosfid = rs.getInt(1);
         }
         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return prosfid;
     }

       /**
   * Updates a Prosfora to the table prosfores
   *
   * @param id       the ProsforesBean id
   * @param path       the new imgpath
   * @param thpath       the new thumbpath
   * @param title       the new title
   * @param text       the new text
   * @return           1 if the entry was updated or else 0
   * @throws Exception    if an error occurs in code
   * @see             ProsforesBean
      */
   public int updateProsfora(int id, String path, String thpath, String title, String text) throws Exception {
         final String methodsig = "ProsforesManagement.updateProsfora()";
         cat.info("MethodStart:" + methodsig);
         DBHandler dbHandler = new DBHandler(cat);
         int reply = 0;
         dbHandler.setQueryString("UPDATE prosfores SET  imgpath='" + path +
                 "', thumbpath='"+ thpath + "',title='" + title + "',text='" + text + "'  WHERE id=? ");
         reply = dbHandler.executeUpdate(String.valueOf(id));

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return reply;
     }

    /**
   * Updates a Prosfora to the table prosfores
   *
   * @param id       the ProsforesBean id

   * @param title       the new title
   * @param text       the new text
   * @return           1 if the entry was updated or else 0
   * @throws Exception    if an error occurs in code
   * @see             ProsforesBean
      */
   public int updateProsforaTitleText(int id, String title, String text) throws Exception {
         final String methodsig = "ProsforesManagement.updateProsforaTitleText()";
         cat.info("MethodStart:" + methodsig);
         DBHandler dbHandler = new DBHandler(cat);
         int reply = 0;
         dbHandler.setQueryString("UPDATE prosfores SET  title='" + title + "',text='" + text + "'  WHERE id=? ");
         reply = dbHandler.executeUpdate(String.valueOf(id));

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return reply;
     }

 
  /**
    * Gets all prosfores
    *
    * @return           a vector of prosfores (ProsforesBean)
    * @throws Exception    if an error occurs in code
    * @see              ProsforesBean
    */
    public Vector getAllProsfores() throws Exception {
           final String methodsig = "ProsforesManagement.getAllProsfores()";
           cat.info("MethodStart:" + methodsig);

           DBHandler dbHandler = new DBHandler(cat);
           dbHandler.setQueryString("SELECT id, imgpath, thumbpath, title, text  FROM prosfores");
           rs = dbHandler.lookup();
        Vector prosforesvec = new Vector();

           while (rs.next()) {
               ProsforesBean prosfora = new ProsforesBean();

               prosfora.setId(rs.getInt(1));
               prosfora.setImgpath(rs.getString(2));
               prosfora.setThumbpath(rs.getString(3));
               prosfora.setTitle(rs.getString(4));
               prosfora.setText(rs.getString(5));

            prosforesvec.addElement(prosfora);
           }
           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return prosforesvec;
       }

        /** Deletes user assessment  portfolio thumbnails names from db and calls method to erase them from user personal directory for  portfolio thumbnails files*/
       public boolean deleteProsfora(int id, String imgpath, String thumbpath) throws Exception {
           final String methodsig = "PortfolioManagement.deleteProsfora()";
           cat.info("MethodStart:" + methodsig);

        deleteProsforaThumpnailfromDir("C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/inoxergo/images/slides/"+thumbpath);
        deleteProsforaThumpnailfromDir("C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/inoxergo/images/slides/"+imgpath);
         // deleteProsforaThumpnailfromDir("/usr/local/tomcat/webapps/inoxergo/images/slides/"+thumbpath);
        //  deleteProsforaThumpnailfromDir("/usr/local/tomcat/webapps/inoxergo/images/slides/"+imgpath);
         //  deleteUAFilefromDir("https://freelancing.gr/userportfolio/"+userid+"/"+thumbnailname);

           DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("DELETE FROM `prosfores` WHERE `id`="+ id+" AND imgpath='"+thumbpath+"'");

           boolean check = dbHandler.execute();

           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return check;

       }

     /** erases user thumbnail files from user personal directory for portfolio thumbnails*/
       public void deleteProsforaThumpnailfromDir(String fileName) {
       try {
         // Construct a File object for the file to be deleted.
             File target = new File(fileName);

             if (!target.exists()) {
               cat.info("File " + fileName
                   + " not present to begin with!");
               return;
             }

           boolean success= target.delete();
           if(! success) {
           cat.info("Fail to delete");
           }else{
           cat.info("File deleted");}


       } catch (SecurityException e) {
         cat.info("Unable to delete " + fileName + "("
             + e.getMessage() + ")");
       }

     }

}
