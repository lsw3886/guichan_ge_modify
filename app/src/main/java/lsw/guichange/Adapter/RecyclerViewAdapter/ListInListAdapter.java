package lsw.guichange.Adapter.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lsw.guichange.Activity.CategoryActivity;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.DB.DBHelper;
import lsw.guichange.Interface.OnListItemClickListener;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.Item.Category;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-09.
 */

public class ListInListAdapter extends RecyclerView.Adapter<ListInListAdapter.ViewHolder> implements OnListItemClickListener{
    DBHelper dbHelper;
    Context mContext;
    ArrayList<Bulletin> bulletins;
    ApplicationController application;
    public ListInListAdapter(Context mContext, String s) {
        application = ApplicationController.getInstance();
        this.bulletins = application.setBulletins(s);
        this.mContext = mContext;

    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i){
        viewHolder.bulletin_name.setText(bulletins.get(i).getBulletin_Name());
        viewHolder.bulletin_img.setImageResource(bulletins.get(i).getBulletin_Img());
        if(bulletins.get(i).getIschecked() == 0){
            viewHolder.isBulletin_selected.setImageResource(R.drawable.ic_bulletinlist_select);

        }else{
            viewHolder.isBulletin_selected.setImageResource(R.drawable.ic_bulletinlist_unselect);
        }
        viewHolder.isBulletin_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(application.isBulletinInChoicedBulletins(bulletins.get(i).getBulletin_Name()) == false){
                    RecentBulletin recentBulletin = new RecentBulletin(bulletins.get(i).getBulletin_Img(), bulletins.get(i).getCategory(), bulletins.get(i).getBulletin_Name());
                    application.setChoiced_bulletins(recentBulletin);
                    application.makePostDB(bulletins.get(i).getBulletin_Name());
                    Toast.makeText(mContext, "나의 게시판에 추가되었습니다.", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(mContext,"이미 추가 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bulletin_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.setOnListItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onListItemClick(int position){


    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView bulletin_img;
        public TextView bulletin_name;
        public ImageView isBulletin_selected;
        OnListItemClickListener mListner;

        public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener){
            mListner = onListItemClickListener;

        }
        public ViewHolder(View itemView){
            super(itemView);
            bulletin_img  = (ImageView) itemView.findViewById(R.id.bulletin_image);
            bulletin_name = (TextView) itemView.findViewById(R.id.bulletin_name);
            isBulletin_selected = (ImageView) itemView.findViewById(R.id.is_bulletin_selected);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    mListner.onListItemClick(getAdapterPosition());
                }
            });


        }

    }

    @Override public int getItemCount(){
        return bulletins.size();
    }




}
