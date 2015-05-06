package mybeans;

/**
  * @author Freelancing
 *
 * Bean for handling links
 */
public class linkGetter {

   public static String  getlink (String link){
       	org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("linkGetter.class");

       String linker =null;
    if (link != null) {
        linker =  "/" + link.concat(".jsp");
    }

    if (link == null) {
        linker = "default.jsp";
    }
       cat.info("**** linker || = "+linker+" || ****");
       return linker;
   }
}
