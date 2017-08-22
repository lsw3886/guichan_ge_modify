package lsw.guichange.Item;

/**
 * Created by lsw38 on 2017-08-22.
 */

public class Keyword {
    public String token;
    public String keyword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Keyword(String token, String keyword) {
        this.token = token;
        this.keyword = keyword;
    }
}
