package lsw.guichange.Item;

/**
 * Created by lsw38 on 2017-08-09.
 */

public class Post {
    String site_name;
    String link;
    String comment;
    int post_num;
    String title;
    int BulletinImg;
    String BulletinTitle;
    String date;

    public Post() {
    }


    public Post(String link, String comment, String title, int post_num, int bulletinImg, String bulletinTitle, String date) {
        this.link = link;
        this.comment = comment;
        this.date = date;
        this.title = title;
        this.post_num = post_num;
        BulletinImg = bulletinImg;
        BulletinTitle = bulletinTitle;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBulletinImg() {
        return BulletinImg;
    }

    public void setBulletinImg(int bulletinImg) {
        BulletinImg = bulletinImg;
    }

    public String getBulletinTitle() {
        return BulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        BulletinTitle = bulletinTitle;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPost_num() {
        return post_num;
    }

    public void setPost_num(int post_num) {
        this.post_num = post_num;
    }
}
