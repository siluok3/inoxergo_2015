package mybeans;

/**
 * Created by IntelliJ IDEA.
 * User: Zarty
 * Time: 11:55:34 πμ
 * To change this template use File | Settings | File Templates.
 */
public class LinkUtil {
       public LinkUtil() {
        info.put("welcome", "welcome");
        info.put("rooms", "rooms");
        info.put("prices", "prices");
        info.put("contact", "contact");
    }


    private java.util.HashMap info = new java.util.HashMap(); // Stores the data for
    // linking.

    public void addEntry(String link, String title) {
        // Record the title for specified link.
        info.put(link, title);
    }

    public String getTitle(String link) {
        // Retrieve the title for a specified link .
        // Returns null if there is no title for the link.
        return (String) info.get(link);
    }

} // end class LinkDirectory