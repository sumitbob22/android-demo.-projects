package com.example.sumitbobade.videopickerplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class ExoPlayer extends AppCompatActivity
{

    SimpleExoPlayer exoPlayer;
    String urlString=null;

    SimpleExoPlayerView simpleExoPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);
        simpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exoplayerview);


        String videoString=null;
        Intent intent=getIntent();
        videoString=intent.getStringExtra("uri");
        urlString=intent.getStringExtra("url");

//        Bundle bundle=intent.getExtras();
//       Uri uri= (Uri) bundle.get("uri");


        try {


            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videoURI;
            if(videoString!=null)
            {
                videoURI = Uri.parse(videoString);
            }
            else
            {
                videoURI=Uri.parse(urlString);
            }



            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            //     MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
            MediaSource mediaSource=buildMediaSource(videoURI);

            simpleExoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }catch (Exception e){
            Log.e("MainAcvtivity"," exoplayer error "+ e.toString());
        }

    }
    private MediaSource buildMediaSource(Uri videoURI)
    {
        return new ExtractorMediaSource(videoURI,
                new DefaultDataSourceFactory(this,"ua"),
                new DefaultExtractorsFactory(),null,null);


    }

}

