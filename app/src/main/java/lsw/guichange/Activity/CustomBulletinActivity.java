package lsw.guichange.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import lsw.guichange.R;

public class CustomBulletinActivity extends AppCompatActivity {
    int REQUEST_IMAGE = 002;
    Bitmap bmap;
    CircleImageView currentBulletinImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bulletin);

        Bundle extra = getIntent().getExtras();
        String s = extra.getString("BulletinName");
        int i = extra.getInt("BulletinImg");

        TextView currentBulletinName = (TextView)findViewById(R.id.currentBulletinName);
        currentBulletinImg = (CircleImageView)findViewById(R.id.currentBulletinImage);
        currentBulletinName.setText(s);
        currentBulletinImg.setImageResource(i);
        currentBulletinImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });

        TextView savebtn = (TextView)findViewById(R.id.custom_save);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        TextView canclebtn = (TextView)findViewById(R.id.custom_cancel);
        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null)
        {
            final Uri selectImageUri = data.getData();
            final String[] filePathColumn = {MediaStore.Images.Media.DATA};
            final Cursor imageCursor = this.getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
            imageCursor.moveToFirst();
            final int columnIndex = imageCursor.getColumnIndex(filePathColumn[0]);
            final String imagePath = imageCursor.getString(columnIndex);
            imageCursor.close();
            final Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            RequestOptions requestOptions = new RequestOptions();

            currentBulletinImg.setImageBitmap(bitmap);


            //interface make!!
        }

    }
}
