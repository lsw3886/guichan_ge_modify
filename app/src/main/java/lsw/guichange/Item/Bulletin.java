package lsw.guichange.Item;

import java.util.ArrayList;

/**
 * Created by lsw38 on 2017-08-09.
 */

public class Bulletin {
    int Bulletin_Img;
    String Bulletin_Name;
    int select_Img;
    String recent_post;
    String Category;
    int ischecked = 0;
    ArrayList<Post> posts;

    public int getIschecked() {
        return ischecked;
    }

    public void setIschecked(int ischecked) {
        this.ischecked = ischecked;
    }

    public Bulletin(int bulletin_Img, String Category, String bulletin_Name) {
        Bulletin_Img = bulletin_Img;
        Bulletin_Name = bulletin_Name;
        this.Category = Category;
    }
    public Bulletin(int bulletin_img, String Category, String bulletin_Name, ArrayList<Post> posts){
        Bulletin_Img = bulletin_img;
        Bulletin_Name = bulletin_Name;
        this.Category = Category;
        recent_post = posts.get(posts.size()-1).getTitle();


    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public int getBulletin_Img() {
        return Bulletin_Img;
    }

    public void setBulletin_Img(int bulletin_Img) {
        Bulletin_Img = bulletin_Img;
    }

    public String getBulletin_Name() {
        return Bulletin_Name;
    }

    public void setBulletin_Name(String bulletin_Name) {
        Bulletin_Name = bulletin_Name;
    }

    public int getSelect_Img() {
        return select_Img;
    }

    public void setSelect_Img(int select_Img) {
        this.select_Img = select_Img;
    }

    public String getRecent_post() {
        return recent_post;
    }

    public void setRecent_post(String recent_post) {
        this.recent_post = recent_post;
    }
}
