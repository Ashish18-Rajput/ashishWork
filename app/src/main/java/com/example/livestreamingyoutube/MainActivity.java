package com.example.livestreamingyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText link;
    Button nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name = "Ashish";
        String organisation = "appsquadz";

        link = findViewById(R.id.link);
        nextActivity = findViewById(R.id.nextActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),YoutubePlayerView.class);
                intent.putExtra("link1",link.getText().toString());
                startActivity(intent);
            }
        });
    }
}