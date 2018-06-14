package com.example.sumitbobade.handling_camera;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{

    Button takeimg,videob;
    ImageView imgview;
    String  currentPhotoPath;
    static final int REQUEST_CODE_IMAGE=22;
    static final int REQUEST_CODE_VIDEO=24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takeimg=findViewById(R.id.takeimg);
        imgview=findViewById(R.id.imgview);
        videob=findViewById(R.id.videob);
      //  StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
       // StrictMode.setVmPolicy(builder.build());

        takeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null)
                {
                    File photoFile=null;
                    photoFile=getFile();
                    if(photoFile!=null)
                    {
                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                                "com.example.android.fileprovider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);

                        startActivityForResult(takePictureIntent, REQUEST_CODE_IMAGE);
                    }
                }



            }
        });
        videob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent,REQUEST_CODE_VIDEO );
                }
            }
        });




    }

    public File getFile()
    {
        File folder=new File("sdcard/camera_app");
        if(!folder.exists())
        {
            folder.mkdir();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image=null;
        try {
             image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPhotoPath=image.getAbsolutePath();

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if(requestCode==REQUEST_CODE_IMAGE)
        imgview.setImageDrawable(Drawable.createFromPath(currentPhotoPath));

        if(requestCode==REQUEST_CODE_VIDEO)
        {
            Uri videoUri = data.getData();
            //videov.setVideoURI(videoUri);
        }
    }
}
