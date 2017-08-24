package lsw.guichange.Adapter.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import lsw.guichange.Activity.CategoryActivity;
import lsw.guichange.Activity.PostActivity;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Interface.FragmentDataChangeListener;
import lsw.guichange.Interface.OnListItemClickListener;
import lsw.guichange.Interface.OnRecentItemClickListener;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-10.
 */

public class RecentAdapter  extends RecyclerView.Adapter<RecentAdapter.ViewHolder> implements OnRecentItemClickListener {

    Context mContext;
    ArrayList<RecentBulletin> choiced_bulletins;
    ApplicationController application;
    FragmentDataChangeListener mCallback;
    public RecentAdapter(Context mContext, FragmentDataChangeListener mCallback) {
        application = ApplicationController.getInstance();
        this.choiced_bulletins = application.getChoiced_bulletins();
        this.mContext = mContext;
        this.mCallback = mCallback;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i){
        viewHolder.bulletin_name.setText(choiced_bulletins.get(i).getBulletin_Name());
        viewHolder.bulletin_img.setImageResource(choiced_bulletins.get(i).getBulletin_Img());
//        viewHolder.bulletin_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                application.deleteChoiced_bulletins(choiced_bulletins.get(i).getBulletin_Name());
//                Toast.makeText(mContext, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
//                notifyDataSetChanged();
//                mCallback.Datachange();
//
//            }
//        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recent_bulletin_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.setOnListItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onRecentItemClick(int position){
        Intent intent = new Intent(mContext, PostActivity.class);
        intent.putExtra("BulletinName", choiced_bulletins.get(position).getBulletin_Name());
        intent.putExtra("BulletinImage", choiced_bulletins.get(position).getBulletin_Img());
        mContext.startActivity(intent);

    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView bulletin_img;
        public TextView bulletin_name;
        public TextView bulletin_content;
        public ImageView bulletin_delete;
        OnRecentItemClickListener mListner;

        public void setOnListItemClickListener(OnRecentItemClickListener onRecentItemClickListener){
            mListner = onRecentItemClickListener;

        }
        public ViewHolder(View itemView){
            super(itemView);
            bulletin_img  = (ImageView) itemView.findViewById(R.id.recent_bulletin_image);
            bulletin_name = (TextView) itemView.findViewById(R.id.recent_bulletin_name);
            bulletin_content = (TextView) itemView.findViewById(R.id.recent_bulletin_content);
//            bulletin_delete = (ImageView) itemView.findViewById(R.id.recent_bulletin_item_delete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  mListner.onRecentItemClick(getAdapterPosition());
                }
            });


        }

    }

    @Override public int getItemCount(){
        return choiced_bulletins.size();
    }


}
