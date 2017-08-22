package lsw.guichange.Item;

/**
 * Created by lsw38 on 2017-08-18.
 */

public class exItem {
    public String name;
    public String category;
    public int img;
    public String password;


    public exItem(String name, String category, int img, String password) {
        this.name = name;
        this.category = category;
        this.img = img;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
