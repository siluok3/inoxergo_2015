package mybeans;

/**
 * Freelancing.
 * User: Zissis K Tialios
 * Date: Aug 8, 2006
 * Time: 10:43:18 AM
 * Comment:A bean for the paragraphs that consist a page.
 */
public class PageParagraphs {
	int id;
	String name;
    String title;
	String lang;
	Paragraph[] paragraphs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Paragraph[] getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(Paragraph[] paragraphs) {
		this.paragraphs = paragraphs;
	}
}
