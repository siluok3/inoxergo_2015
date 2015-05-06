package mybeans;

/**
 * Created by IntelliJ IDEA.
 * User: evgenia
 * Date: 18 Φεβ 2010
 * Time: 5:03:37 μμ
 * To change this template use File | Settings | File Templates.
 */
public class Pageimg {
    int id;
    String pagename;
    int imgid;
    String imgpath;
    String thumbpath;
    String pou;
    String title;
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getThumbpath() {
        return thumbpath;
    }

    public String getPou() {
        return pou;
    }

    public void setPou(String pou) {
        this.pou = pou;
    }

    public void setThumbpath(String thumbpath) {
        this.thumbpath = thumbpath;
    }
}
