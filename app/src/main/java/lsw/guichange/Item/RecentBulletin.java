package lsw.guichange.Item;

import java.util.ArrayList;

/**
 * Created by lsw38 on 2017-08-11.
 */

public class RecentBulletin {
    int Bulletin_Img;
    String Bulletin_Name;
    String recent_post;
    String Category;
    Post RecentPosts;

    int newCount;

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public RecentBulletin(int bulletin_Img, String Category, String bulletin_Name) {
        Bulletin_Img = bulletin_Img;
        Bulletin_Name = bulletin_Name;
        this.Category = Category;
    }
    public RecentBulletin(int bulletin_img, String Category, String bulletin_Name, ArrayList<Post> posts){
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

    public Post getRecentPosts() {
        return RecentPosts;
    }

    public void setRecentPosts(Post recentPosts) {
        RecentPosts = recentPosts;
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

    public String getRecent_post() {
        return recent_post;
    }

    public void setRecent_post(String recent_post) {
        this.recent_post = recent_post;
    }
}
