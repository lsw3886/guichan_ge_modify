package lsw.guichange.Adapter.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lsw.guichange.Activity.webview;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Controller.NetworkService;
import lsw.guichange.Interface.OnPostItemClickListener;
import lsw.guichange.Item.Category;
import lsw.guichange.Item.Post;
import lsw.guichange.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lsw38 on 2017-08-13.
 */


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements OnPostItemClickListener {
    Context mContext;
    ApplicationController application = ApplicationController.getInstance();
    public ArrayList<Post> posts;
    int bulletin_img;
    String title;



    public PostAdapter(Context mContext, int img, String title, ArrayList<Post> posts) {
        this.mContext = mContext;
        this.posts = posts;
        this.bulletin_img = img;
        this.title = title;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i){
        viewHolder.Post_img.setImageResource(this.bulletin_img);
        viewHolder.Post_title.setText(this.title);
        viewHolder.Post_content.setText(posts.get(i).getTitle());
        viewHolder.Post_comment.setText(posts.get(i).getComment());
        viewHolder.Post_date.setText(makedate(posts.get(i).getDate()));
        viewHolder.Post_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(application.isBulletinInBookmark(posts.get(i).getTitle()) == false){
                    application.addBookmark(title, bulletin_img, posts.get(i));
                    Toast.makeText(mContext, "북마크에 추가 되었습니다.", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(mContext, "이미 추가 되었습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.setOnListItemClickListener(this);
        return viewHolder;

    }
    @Override
    public void onPostItemClick(int position){
        Intent intent = new Intent(mContext, webview.class);
        intent.putExtra("address", posts.get(position).getLink());
        mContext.startActivity(intent);



    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView Post_img;
        public TextView Post_title;
        public TextView Post_content;
        public TextView Post_comment;
        public TextView Post_date;
        public ImageView Post_bookmark;
        OnPostItemClickListener mListner;

        public void setOnListItemClickListener(OnPostItemClickListener onPostItemClickListener){
            mListner = onPostItemClickListener;

        }
        public ViewHolder(View itemView){
            super(itemView);
            Post_img  = (ImageView) itemView.findViewById(R.id.post_img);
            Post_title = (TextView) itemView.findViewById(R.id.post_bulletin_name);
            Post_content = (TextView) itemView.findViewById(R.id.post_contents);
            Post_comment = (TextView) itemView.findViewById(R.id.post_comment);
            Post_date = (TextView) itemView.findViewById(R.id.ppost_date);
            Post_bookmark = (ImageView) itemView.findViewById(R.id.bookmark_check);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListner.onPostItemClick(getAdapterPosition());
                }
            });


        }

    }


    @Override public int getItemCount(){
        return posts.size();
    }


    public String makedate(String dates){
        String year = dates.substring(0,2);
        String month = dates.substring(2,4);
        String date = dates.substring(4,6);
        String hour = dates.substring(6,8);
        String min = dates.substring(8,10);

        String returnDate = month + "월 "+ date +"일 "+ hour+ "시 "+ min +"분" ;

        return returnDate;


    }





}
