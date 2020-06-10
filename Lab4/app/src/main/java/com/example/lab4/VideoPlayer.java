package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;
import android.net.Uri;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class VideoPlayer extends AppCompatActivity {
    String [] ohoices = {"small", "funny","dog"};
    String choosed_option;
    VideoView videoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        spinner();
        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);
    }
    public void spinner(){
        FileFilter filter = new FileFilter() {

            public boolean accept(File f)
            {
                return f.getName().endsWith("webm");
            }
        };
        List<String> choices = new ArrayList<String>();
        File[] files = new File(String.format("android.resource://%s/%s",this.getPackageName(),"raw")).listFiles(filter);
        if (files != null) {
            for (File file : files) {
            if (file.isFile()) {
                choices.add(file.getName());
            }
            }
        }
        Spinner spinner = (Spinner) findViewById(R.id.choices);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ohoices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choosed_option = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choosed_option = "";
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }
    public static String[] GetFiles(final String path, final String searchPattern) {
        final Pattern re = Pattern.compile(searchPattern.replace("*", ".*").replace("?", ".?"));
        return new File(path).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isFile() && re.matcher(name).matches();
            }
        });
    }
    public void play(View view){
        if (choosed_option.equals("dog")) {
            videoPlayer.setVideoPath("https://thumbs.gfycat.com/WellinformedViciousIchthyosaurs-mobile.mp4");
            MediaController mediaController = new MediaController(this);
            videoPlayer.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoPlayer);
            videoPlayer.start();
      }
        else{
            Uri myVideoUri = Uri.parse(String.format("android.resource://%s/%s/%s", this.getPackageName(), "raw", choosed_option));
            videoPlayer.setVideoURI(myVideoUri);
            MediaController mediaController = new MediaController(this);
            videoPlayer.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoPlayer);
            videoPlayer.start();
        }
    }

    public void pause(View view){
        videoPlayer.pause();
    }
    public void stop(View view){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }
}
