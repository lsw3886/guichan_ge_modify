package lsw.guichange.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;


import lsw.guichange.Adapter.PagerAdapter;
import lsw.guichange.Adapter.RecyclerViewAdapter.ExpandableListAdapter;
import lsw.guichange.Adapter.RecyclerViewAdapter.ListAdapter;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Controller.NetworkService;
import lsw.guichange.DB.DBHelper;
import lsw.guichange.GCM.MyFirebaseInstanceIDService;
import lsw.guichange.Item.addBulletin;
import lsw.guichange.Item.exItem;
import lsw.guichange.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.bitmap;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Expandable.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Expandable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Expandable extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerview;
    FloatingActionButton fab;
    FirebaseDatabase database;
    DatabaseReference myRef;
    PagerAdapter pagerAdapter;
    DBHelper dbHelper;
    Bitmap bmap;
    ApplicationController application;
    List<ExpandableListAdapter.Item> data;
    NetworkService networkService;
    final int REQUEST_IMAGE = 002;

    //    ExpandableListAdapter.Item shopping;
//    ExpandableListAdapter.Item exam;
//    ExpandableListAdapter.Item job;
//    ExpandableListAdapter.Item places;
//
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public Expandable() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Expandable newInstance(PagerAdapter pagerAdapter) {
        Expandable fragment = new Expandable();
        fragment.pagerAdapter = pagerAdapter;
        fragment.data = new ArrayList<>();
        fragment.application = ApplicationController.getInstance();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getActivity());

        data = MakeBulletinList();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expandable, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        boolean drag = false;

        fab = (FloatingActionButton) getView().findViewById(R.id.floating_Btn);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dialog();
            }
        });

        recyclerview = (RecyclerView) getView().findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));




        recyclerview.setAdapter(new ExpandableListAdapter(data, getContext(), pagerAdapter));

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void show_dialog() {
        LayoutInflater _inflater = (LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View _layout = _inflater.inflate(R.layout.add_bulletin_popup, null);
        final EditText inputBulletinName = (EditText)_layout.findViewById(R.id.add_bulletin_name);
        final EditText inputBulletinAddress = (EditText)_layout.findViewById(R.id.add_bulletin_address);
        final EditText inputPassword = (EditText)_layout.findViewById(R.id.add_bulletin_password);
//        final ImageView addBulletinImg = (ImageView)_layout.findViewById(R.id.add_bulletin_img);


        AlertDialog alert = new AlertDialog.Builder(getActivity())
                .setTitle("게시판 추가하기")
                .setPositiveButton("전송", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if(inputBulletinAddress.getText().toString().equals("") || inputBulletinName.getText().toString().equals("")){
                            Toast.makeText(getActivity().getApplicationContext(),"전송실패!", Toast.LENGTH_SHORT).show();
                        }else {
                            database = FirebaseDatabase.getInstance();
                            String token = FirebaseInstanceId.getInstance().getToken();
                            myRef = database.getReference("Bulletins");
                            addBulletin addBulletin = new addBulletin(inputBulletinName.getText().toString(), inputBulletinAddress.getText().toString(), token, inputPassword.getText().toString());
                            myRef.child(inputBulletinName.getText().toString()).setValue(addBulletin);
                            Toast.makeText(getActivity().getApplicationContext(), "전송성공!",Toast.LENGTH_SHORT).show();
                            SQLiteDatabase db = dbHelper.getWritableDatabase();

                            db.execSQL("insert into RequestBulletin values("+"\""+inputBulletinName.getText().toString()+ "\","+ "\""+inputBulletinAddress.getText().toString()+"\","+"\""+ inputPassword.getText().toString()+"\", "+R.drawable.guichan+");");
                        }




                    }
                })
                .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getApplicationContext(), "취소되었습니다.",Toast.LENGTH_SHORT).show();

                        //CAncel  버튼 눌렀을때

                    }
                }).create();
        alert.setView(_layout);
        alert.show();
    }

    public List<ExpandableListAdapter.Item> MakeBulletinList(){
        List<ExpandableListAdapter.Item> data = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from Bulletin;", null);

        ExpandableListAdapter.Item shopping = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "쇼핑", R.drawable.ic_bulletinlist_cart, "쇼핑");
        ExpandableListAdapter.Item exam = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "시험",R.drawable.ic_bulletinlist_exam, "시헝");
        ExpandableListAdapter.Item job = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "취업", R.drawable.ic_bulletinlist_bag, "취업");
        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "커뮤니티", R.drawable.ic_bulletinlist_com, "커뮤니티");
        ExpandableListAdapter.Item myBulletin = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "나만의 게시판", R.drawable.guichan, "나만의 게시판");
        shopping.invisibleChildren = new ArrayList<>();
        exam.invisibleChildren = new ArrayList<>();
        job.invisibleChildren = new ArrayList<>();
        places.invisibleChildren = new ArrayList<>();
        myBulletin.invisibleChildren = new ArrayList<>();

//        Category TEXT, BulletinName TEXT, BulletinImg INTEGER, Id TEXT, Password TEXT
        while(rs.moveToNext()){
            String Category = rs.getString(0);
            String BulletinName = rs.getString(1);
            int BulletinImg = rs.getInt(2);
            String Id = rs.getString(3);
            String Password = rs.getString(4);

            switch(Category){

                case "쇼핑":
                    shopping.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, BulletinName,BulletinImg, Category));

                    break;

                case "시험":
                    exam.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, BulletinName, BulletinImg, Category));

                    break;

                case "취업":
                    job.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, BulletinName, BulletinImg, Category));

                    break;

                case "커뮤니티":
                    places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, BulletinName, BulletinImg, Category));

                    break;


                default:
                    myBulletin.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, BulletinName, BulletinImg, Category));

                    break;

            }

        }

        data.add(shopping);
        data.add(exam);
        data.add(job);
        data.add(places);
        data.add(myBulletin);

        return data;
    }




}
