package lsw.guichange.Adapter.RecyclerViewAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import lsw.guichange.Activity.CustomBulletinActivity;
import lsw.guichange.Adapter.PagerAdapter;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Interface.OnListItemClickListener;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.R;

/**
 * Created by anandbose on 09/06/15.
 */
public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private List<Item> data;
    Context mContext;
    PagerAdapter pagerAdapter;
    ApplicationController application;
    public ExpandableListAdapter(List<Item> data, Context context, PagerAdapter pagerAdapter) {
        this.data = data;
        this.mContext = context;
        this.pagerAdapter = pagerAdapter;
        application = ApplicationController.getInstance();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int subItemPaddingLeft = (int) (18 * dp);
        int subItemPaddingTopAndBottom = (int) (5 * dp);
        switch (type) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_header, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;
            case CHILD:
                LayoutInflater dinflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = dinflater.inflate(R.layout.bulletin_list_item, parent, false);

                ListChildViewHolder child = new ListChildViewHolder(view);
                return child;
        }
        return null;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.header_title.setText(item.text);
                itemController.header_image.setImageResource(item.image);
                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expandable_arrowup);
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expandable_arrowdown);
                }
                holder.itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);
                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expandable_arrowdown);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_expandable_arrowup);
                            item.invisibleChildren = null;
                        }
                    }
                });

                break;
            case CHILD:
                final ListChildViewHolder citemController = (ListChildViewHolder) holder;
                citemController.refferalItem = item;
                citemController.child_title.setText(item.text);
                citemController.child_image.setImageResource(item.image);
//                citemController.modifyBtn.setImageResource(R.drawable.ic_custom_pencil);
                if(application.isBulletinInChoicedBulletins(data.get(position).text) == false){
                    citemController.isSelect.setImageResource(R.drawable.ic_bulletinlist_select);

                }else{
                    citemController.isSelect.setImageResource(R.drawable.ic_bulletinlist_unselect);
                }
                citemController.isSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(application.isBulletinInChoicedBulletins(data.get(position).text) == false){
//                            data.get(position).isSelect = 1;
                            if(data.get(position).text.equals(data.get(position).Category) == false) {
                                RecentBulletin recentBulletin = new RecentBulletin(data.get(position).image, data.get(position).Category, data.get(position).text);
                                application.setChoiced_bulletins(recentBulletin);
                                application.makePostDB(data.get(position).text);
                                Toast.makeText(mContext, "나의 게시판에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                                citemController.isSelect.setImageResource(R.drawable.ic_bulletinlist_unselect);
                            }else{
                                notifyDataSetChanged();
                            }
                        }else{
                            citemController.isSelect.setImageResource(R.drawable.ic_bulletinlist_select);
//                            data.get(position).isSelect=0;
                            application.deleteChoiced_bulletins(data.get(position).text);
                            Toast.makeText(mContext,"해제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                citemController.modifyBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(mContext, CustomBulletinActivity.class);
//                        intent.putExtra("BulletinName", data.get(position).text);
//                        intent.putExtra("BulletinImg", data.get(position).image);
//                        mContext.startActivity(intent);
//                    }
//                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public ImageView header_image;
        public ImageView btn_expand_toggle;
        public Item refferalItem;

        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            header_title = (TextView) itemView.findViewById(R.id.header_title);
            header_image =(ImageView) itemView.findViewById(R.id.header_image);
            btn_expand_toggle = (ImageView) itemView.findViewById(R.id.btn_expand_toggle);
        }
    }
    private static class ListChildViewHolder extends RecyclerView.ViewHolder {
        public TextView child_title;
        public ImageView child_image;
        public ImageView isSelect;
//        public ImageView modifyBtn;
        public Item refferalItem;

        public ListChildViewHolder(View itemView) {
            super(itemView);
            child_title= (TextView) itemView.findViewById(R.id.bulletin_name);
            child_image =(ImageView) itemView.findViewById(R.id.bulletin_image);
            isSelect = (ImageView) itemView.findViewById(R.id.is_bulletin_selected);
//            modifyBtn = (ImageView) itemView.findViewById(R.id.modifyBulletinBtn);
        }
    }


    public static class Item {
        public int type;
        public String text;
        public int image;
        public List<Item> invisibleChildren;
        public String Category;
        public Item() {
        }

        public Item(int type, String text, int image, String category) {
            this.type = type;
            this.text = text;
            this.image = image;
            this.Category = category;
        }
    }
}