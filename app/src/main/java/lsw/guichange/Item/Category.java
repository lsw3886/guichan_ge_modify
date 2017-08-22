package lsw.guichange.Item;

import java.util.List;

/**
 * Created by lsw38 on 2017-08-08.
 */

public class Category {

    String name;
    int image;


    public Category(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
