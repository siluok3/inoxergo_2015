package mybeans;

/**
 * Created by IntelliJ IDEA.
 * User: evgenia
 * Time: 4:59:25 μμ
 * To change this template use File | Settings | File Templates.
 */
public class ProsforesBean {
    int id;
    String imgpath;
    String thumbpath;
    String title;
    String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setThumbpath(String thumbpath) {
        this.thumbpath = thumbpath;
    }
}
