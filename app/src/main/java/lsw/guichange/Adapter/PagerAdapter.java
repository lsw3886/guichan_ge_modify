package lsw.guichange.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import lsw.guichange.fragment.BookmarkFragment;
import lsw.guichange.fragment.Expandable;
import lsw.guichange.fragment.ListFragment;
import lsw.guichange.fragment.RecentFragment;
import lsw.guichange.fragment.SettingFragment;

/**
 * Created by lsw38 on 2017-08-08.
 */

public class PagerAdapter extends FragmentPagerAdapter {
//    ListFragment listFragment;
    public RecentFragment recentFragment;
    public BookmarkFragment bookmarkFragment;
    public SettingFragment settingFragment;
    public Expandable expandable;

    public PagerAdapter(FragmentManager fm){

        super(fm);
//        listFragment = ListFragment.newInstance();
        recentFragment = RecentFragment.newInstance();
        bookmarkFragment = BookmarkFragment.newInstance();
//        settingFragment = SettingFragment.newInstance();
        expandable = Expandable.newInstance(this);

    }

    @Override
    public Fragment getItem(int position){
        switch(position){

            case 0:

                return recentFragment;


            case 1:

                return bookmarkFragment;

            case 2:

                return expandable;

            default :
                return null;
        }
    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public int getItemPosition(Object item) {

        return POSITION_NONE;   // notifyDataSetChanged


    }
}
