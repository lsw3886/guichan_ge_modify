package lsw.guichange.Item;

/**
 * Created by lsw38 on 2017-08-15.
 */

public class addBulletin {
    public String bulletinName;
    public String bulletinAddress;
    public String token;
    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBulletinName() {
        return bulletinName;
    }

    public void setBulletinName(String bulletinName) {
        this.bulletinName = bulletinName;
    }

    public String getBulletinAddress() {
        return bulletinAddress;
    }

    public void setBulletinAddress(String bulletinAddress) {
        this.bulletinAddress = bulletinAddress;
    }

    public addBulletin() {
    }

    public addBulletin(String bulletinName, String bulletinAddress, String token, String password) {
        this.bulletinName = bulletinName;
        this.bulletinAddress = bulletinAddress;
        this.token = token;
        this.password = password;
    }
}
