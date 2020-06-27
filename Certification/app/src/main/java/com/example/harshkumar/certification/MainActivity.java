package com.example.harshkumar.certification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    void callSnacbar(){
    }

    void callToast() {
        Toast toast = Toast.makeText(getApplicationContext(),"Hello Toast",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,Gravity.LEFT,0);
        toast.show();
    }
}
