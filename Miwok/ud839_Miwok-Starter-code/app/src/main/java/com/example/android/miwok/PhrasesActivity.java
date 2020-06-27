package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener mediaPlayerCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> listItems = new ArrayList<>();
        listItems.add(new Word("Where are you going ?","minto wuksus", R.raw.phrase_where_are_you_going));
        listItems.add(new Word("What is your name ?","tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        listItems.add(new Word("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        listItems.add(new Word("How are you feeling ?","michәksәs?", R.raw.phrase_how_are_you_feeling));
        listItems.add(new Word("I'm feeling good","kuchi achit", R.raw.phrase_im_feeling_good));
        listItems.add(new Word("Are you coming ?","әәnәs'aa?", R.raw.phrase_are_you_coming));
        listItems.add(new Word("Yes, I'm coming","hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        listItems.add(new Word("I'm coming","әәnәm", R.raw.phrase_im_coming));
        listItems.add(new Word("Let's go","yoowutis", R.raw.phrase_lets_go));
        listItems.add(new Word("Come here","әnni'nem", R.raw.phrase_come_here));

        WordAdapter listAdapter = new WordAdapter(this,listItems);

        ListView listView = (ListView)findViewById(R.id.phrases_list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = listItems.get(i);

                releaseMedia();

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getmSoundSourceId());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(mediaPlayerCompleteListener);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMedia();
    }

    public void releaseMedia(){
        if(mediaPlayer != null) {
            mediaPlayer.release();

            mediaPlayer = null;
        }
    }
}
