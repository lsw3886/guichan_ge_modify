package lsw.guichange.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import lsw.guichange.Adapter.RecyclerViewAdapter.ListAdapter;
import lsw.guichange.Adapter.RecyclerViewAdapter.ListInListAdapter;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.R;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListInListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Bundle extra = getIntent().getExtras();
        String s = extra.getString("category");
        TextView title_view = (TextView) findViewById(R.id.category_activity_title);
        title_view.setText(s);

        recyclerView = (RecyclerView) findViewById(R.id.category_listinlist_recyclerview);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        this.adapter = new ListInListAdapter(this, s);

        recyclerView.setAdapter(adapter);



    }


}
