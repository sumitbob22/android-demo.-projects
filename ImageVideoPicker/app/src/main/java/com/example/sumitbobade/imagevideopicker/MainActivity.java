package com.example.sumitbobade.imagevideopicker;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    Button pickb;
    ViewPager viewPager;
    private static final int IMAGE_SELECTED=1;

    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    ArrayList<String> imagesEncodedList;
    MyCustomPagerAdapter myCustomPagerAdapter;
    ArrayList<Uri> mArrayUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickb=findViewById(R.id.pickimgb);
        viewPager=findViewById(R.id.viewPager);


        pickb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        try {
            mArrayUri = new ArrayList<Uri>();
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data)
            {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();
                        mArrayUri.add(mImageUri);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();

                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        myCustomPagerAdapter = new MyCustomPagerAdapter(MainActivity.this,mArrayUri);
        viewPager.setAdapter(myCustomPagerAdapter);

    }

}
