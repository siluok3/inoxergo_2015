package mybeans;

/**
  * @author Freelancing
 *
*   Bean used for managing paragraphs in db
*/
public class Paragraph {
	int id;
	String lang;
    String keywords;
	String text;
	String foto;
	String page;
	int tranlateid;
    int fotosize;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getFotosize() {
		return fotosize;
	}

	public void setFotosize(int fotosize) {
		this.fotosize = fotosize;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getTranlateid() {
		return tranlateid;
	}

	public void setTranlateid(int tranlateid) {
		this.tranlateid = tranlateid;
	}

}
