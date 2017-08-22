package lsw.guichange.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import lsw.guichange.Adapter.PagerAdapter;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Interface.FragmentDataChangeListener;
import lsw.guichange.R;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
public class MainActivity extends AppCompatActivity implements FragmentDataChangeListener {
    ViewPager viewPager;
    TextView menu_title;
    PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashActivity.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        menu_title = (TextView) findViewById(R.id.menu_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("나의 게시판"));
        tabLayout.addTab(tabLayout.newTab().setText("북마크"));
        tabLayout.addTab(tabLayout.newTab().setText("게시판 설정"));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {  //SEND_SMS 권한이 없다면,
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 5);  //달라고 요청
        }
        tabLayout.setTabTextColors(Color.WHITE, Color.rgb(255, 171, 0));

        viewPager = (ViewPager) findViewById(R.id.view_pager);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                changeTitle(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }




    @Override
    protected void onRestart(){
        super.onRestart();
        pagerAdapter.notifyDataSetChanged();

    }
    protected void changeTitle(int position){
        switch (position){

            case 0:
                this.menu_title.setText("나의 게시판");
                break;

            case 1:
                this.menu_title.setText("북마크");
                break;

            case 2:
                this.menu_title.setText("게시판 설정");
                break;


            default:
                this.menu_title.setText("귀찮게");

        }

    }

    @Override
    public void Datachange(){
        this.pagerAdapter.notifyDataSetChanged();
    }
}
