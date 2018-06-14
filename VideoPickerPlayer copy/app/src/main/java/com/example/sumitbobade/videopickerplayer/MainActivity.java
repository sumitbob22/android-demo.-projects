package com.example.sumitbobade.videopickerplayer;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Button pickVideoB;
    Button exobutton;
    EditText urltext;
    private static final int REQUEST_TAKE_GALLERY_VIDEO=1;
    Uri selectedVideoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView=findViewById(R.id.videoView);
        pickVideoB=findViewById(R.id.pickVideob);
        exobutton=findViewById(R.id.exob);
        urltext=(EditText) findViewById(R.id.urltext);

        pickVideoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);
            }
        });

        exobutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this,ExoPlayer.class);
                String urlString=urltext.getText().toString();
                if(selectedVideoUri!=null){


                        intent.putExtra("uri", selectedVideoUri.toString());
                        startActivity(intent);
                    }else
                    {
                        intent.putExtra("url",urlString);
                        startActivity(intent);
                    }



            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO)
            {
                 selectedVideoUri = data.getData();
                videoView.setMediaController(new MediaController(this));
                    videoView.setVideoURI(selectedVideoUri);
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp)
                        {
                            //mp.setLooping(true);
                            videoView.start();
                        }
                    });
                }
            }
        }


}
