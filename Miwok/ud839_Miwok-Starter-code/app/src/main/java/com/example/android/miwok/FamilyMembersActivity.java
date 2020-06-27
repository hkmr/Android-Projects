package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family_members);

        final ArrayList<Word> listItems = new ArrayList<>();
        listItems.add(new Word("Father","әpә",R.drawable.family_father, R.raw.family_father));
        listItems.add(new Word("Mother","әṭa",R.drawable.family_mother, R.raw.family_mother));
        listItems.add(new Word("Son","angsi",R.drawable.family_son, R.raw.family_son));
        listItems.add(new Word("Daughter","tune",R.drawable.family_daughter, R.raw.family_daughter));
        listItems.add(new Word("Older brother","taachi",R.drawable.family_older_brother, R.raw.family_older_brother));
        listItems.add(new Word("Younger brother","chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        listItems.add(new Word("older sister","teṭe",R.drawable.family_older_sister, R.raw.family_older_sister));
        listItems.add(new Word("Younger sister","kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        listItems.add(new Word("Grandmother","ama",R.drawable.family_grandmother, R.raw.family_grandmother));
        listItems.add(new Word("Grandfather","paapa",R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter familyAdapter = new WordAdapter(this,listItems);

        ListView listView = (ListView) findViewById(R.id.family_list);
        listView.setAdapter(familyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = listItems.get(i);

                releaseMedia();

                mediaPlayer = MediaPlayer.create(FamilyMembersActivity.this,word.getmSoundSourceId());
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
