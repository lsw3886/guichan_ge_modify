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

import java.util.ArrayList;

import lsw.guichange.Activity.webview;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Interface.FragmentDataChangeListener;
import lsw.guichange.Interface.OnPostItemClickListener;
import lsw.guichange.Item.Post;
import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-13.
 */

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> implements OnPostItemClickListener {

    Context mContext;
    ApplicationController application;
    ArrayList<Post> bookmarkPosts;
    FragmentDataChangeListener mCallback;
    int image;
    int bulletinName;
    public BookmarkAdapter(Context mContext, FragmentDataChangeListener mCallback) {
        application = ApplicationController.getInstance();
        this.bookmarkPosts = application.getBookmarks();
        this.mCallback = mCallback;
        this.mContext = mContext;

    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i){
        viewHolder.Post_comment.setText(bookmarkPosts.get(i).getComment());
        viewHolder.Post_content.setText(bookmarkPosts.get(i).getTitle());
        viewHolder.Post_title.setText(bookmarkPosts.get(i).getBulletinTitle());
        viewHolder.Post_img.setImageResource(bookmarkPosts.get(i).getBulletinImg());
//        viewHolder.Post_date.setText(bookmarkPosts.get(i).getDate());
        viewHolder.Post_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                application.removeBookmark(bookmarkPosts.get(i));
                notifyDataSetChanged();
                mCallback.Datachange();

            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookmark_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.setOnListItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onPostItemClick(int position){
        Intent intent = new Intent(mContext, webview.class);
        intent.putExtra("address", bookmarkPosts.get(position).getLink());
        mContext.startActivity(intent);
    }
    @Override public int getItemCount(){
        return bookmarkPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView Post_img;
        public TextView Post_title;
        public TextView Post_content;
        public TextView Post_comment;
        public TextView Post_date;
        public ImageView Post_delete;
        OnPostItemClickListener mListner;

        public void setOnListItemClickListener(OnPostItemClickListener onPostItemClickListener){
            mListner = onPostItemClickListener;

        }
        public ViewHolder(View itemView){
            super(itemView);
            Post_img  = (ImageView) itemView.findViewById(R.id.Bookmark_bulletin_image);
            Post_title = (TextView) itemView.findViewById(R.id.Bookmark_bulletin_name);
            Post_content = (TextView) itemView.findViewById(R.id.Bookmark_bulletin_content);
            Post_comment = (TextView) itemView.findViewById(R.id.Bookmark_comment);
            Post_date = (TextView) itemView.findViewById(R.id.Bookmark_date);
            Post_delete = (ImageView) itemView.findViewById(R.id.Bookmark_bulletin_item_delete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListner.onPostItemClick(getAdapterPosition());
                }
            });


        }

    }
}
