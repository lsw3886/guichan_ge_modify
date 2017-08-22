package lsw.guichange.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-13.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int version = 1;

    public DBHelper(Context context){
        super(context, "guichangeDB", null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String choicedBulletin= "CREATE TABLE RecentBulletin(img INTEGER, category VARCHAR(20), name VARCHAR(20));";
        String bookMark = "CREATE TABLE Bookmark(sitename VARCHAR(20), link VARCHAR(100), comment VARCHAR(3), postnum INTEGER, title VARCHAR(10), date VARCHAR(20), bimg INTEGER, btitle VARCHAR(5));";
        String bulletin = "CREATE TABLE Bulletin(Category TEXT, BulletinName TEXT, BulletinImg INTEGER, Id TEXT, Password TEXT, ShowText);";
        String requestbulletin = "CREATE TABLE RequestBulletin(BulletinName TEXT, BulletinAddress TEXT, password TEXT, img INTEGER);";
        int t = R.drawable.ic_bulletinlist_ppomppu;
        String query1 = "insert into Bulletin values(\"쇼핑\", \"뽐뿌게시판\","+ R.drawable.ic_bulletinlist_ppomppu + ",NULL,NULL, \"뽐뿌게시판\");";
        String query2 = "insert into Bulletin values(\"쇼핑\", \"중고나라\","+ R.drawable.ic_bulletinlist_jungo + ",NULL,NULL, \"중고나라\");";
        String query3 = "insert into Bulletin values(\"시험\", \"오르비\", "+ R.drawable.ic_bulletinlist_orbi+ ",NULL,NULL, \"오르비\");";
        String query4 = "insert into Bulletin values(\"시험\", \"행시사랑\"," + R.drawable.ic_bulletinlist_hangsi+",NULL,NULL, \"행시사랑\");";
        String query5 = "insert into Bulletin values(\"취업\", \"스펙업\"," + R.drawable.specup_ic+ ",NULL,NULL, \"스펙업\");";
        String query6 = "insert into Bulletin values(\"취업\", \"취업대학교\","+ R.drawable.ic_bulletinlist_chiup+",NULL,NULL, \"취업대학교\");";
        String query7 = "insert into Bulletin values(\"커뮤니티\", \"뽐뿌자게\","+ R.drawable.ic_bulletinlist_ppomppu+",NULL,NULL, \"뽐뿌자게\");";
        String query8 = "insert into Bulletin values(\"커뮤니티\", \"부스트캠프\","+ R.drawable.ic_bulletinlist_boost+",NULL,NULL, \"부스트캠프\");";



        db.execSQL(requestbulletin);
        db.execSQL(choicedBulletin);
        db.execSQL(bookMark);
        db.execSQL(bulletin);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);
        db.execSQL(query8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXSITS member");
        //새로 생성될 수 있도록 onCreate() 메소드를 생성한다.
        onCreate(db);
    }

}
