package com.example.sumitbobade.fbintergration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.share.Share;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;

public class ProfileData extends AppCompatActivity
{
    TextView profiledata;
    Button sharelink;
    Button postphoto,fblogout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);
        profiledata=findViewById(R.id.profiledata);
        sharelink=findViewById(R.id.sharelinkb);
        postphoto=findViewById(R.id.postphoto);
        fblogout=findViewById(R.id.fblogout);
        getProfileData();
    }
    public void getProfileData()
    {
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        String name=intent.getStringExtra("name");
        String link=intent.getStringExtra("link");
        String allfield="Id : "+id+"\n name : "+name+"\nlink :"+link;
        profiledata.setText(allfield);

    }

    public void postLink(View v)
    {
                ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
    }

    public void postPhoto(View v)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }


    public void postVideo(View v)
    {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
            ShareDialog shareDialog = new ShareDialog(this);
            shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
        }
        if (requestCode == 2)
        {
            ShareVideo shareVideo = new ShareVideo.Builder()
                    .setLocalUrl(data.getData())
                    .build();
            if (ShareDialog.canShow(ShareVideoContent.class))
            {
                ShareVideoContent shareVideoContent = new ShareVideoContent.Builder()
                        .setVideo(shareVideo)
                        .build();
                ShareDialog shareDialog = new ShareDialog(this);
                shareDialog.show(shareVideoContent);
            }

        }
    }

    public void logOutFb(View view)
    {

        if (AccessToken.getCurrentAccessToken() == null)
        {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse)
            {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();

        AccessToken.setCurrentAccessToken(null);
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

}
