package lsw.guichange.Adapter.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.AbstractCollection;
import java.util.ArrayList;

import lsw.guichange.Activity.CategoryActivity;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Interface.OnListItemClickListener;
import lsw.guichange.Item.Category;
import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-08.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements OnListItemClickListener{

    Context mContext;
    ApplicationController application = ApplicationController.getInstance();
    ArrayList<Category> categories;
    int type;
            public ListAdapter(Context mContext, int type) {
                this.categories = application.categories;
                this.mContext = mContext;
                this.type = type;

            }
            public ListAdapter(Context mContext, ArrayList<Category> categories, int type) {
                this.categories = categories;
                this.mContext = mContext;
                this.type = type;

            }


            @Override
            public void onBindViewHolder(final ViewHolder viewHolder, int i){
                viewHolder.category_title.setText(categories.get(i).getName());
                viewHolder.category_img.setImageResource(categories.get(i).getImage());
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder(v);
                viewHolder.setOnListItemClickListener(this);
                return viewHolder;
            }

            @Override
            public void onListItemClick(int position){
                if(type == 0) {
                    Intent intent = new Intent(mContext, CategoryActivity.class);
                    intent.putExtra("category", categories.get(position).getName());
                    mContext.startActivity(intent);
                }
            }
            class ViewHolder extends RecyclerView.ViewHolder{
                public ImageView category_img;
                public TextView category_title;
                OnListItemClickListener mListner;

                public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener){
                    mListner = onListItemClickListener;

                }
                public ViewHolder(View itemView){
                    super(itemView);
                    category_img  = (ImageView) itemView.findViewById(R.id.category_image);
                    category_title = (TextView) itemView.findViewById(R.id.category_name);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mListner.onListItemClick(getAdapterPosition());
                        }
                    });


        }

    }

    @Override public int getItemCount(){
        return 4;
    }


}