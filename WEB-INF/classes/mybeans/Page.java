package mybeans;

/**
  * @author Freelancing
 *
*   Bean used connecting paragraphs and services with certain pages from db
*/
public class Page {
	String pagename;
    String title;
    String lang;

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
}
