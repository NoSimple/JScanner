package com.example.user.jscanner.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.jscanner.R;

public class AboutActivity extends AppCompatActivity {

    ImageView photo;
    TextView title;
    TextView fullText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initViews();

    }

    public void initViews() {
        photo = findViewById(R.id.photo_about);
        title = findViewById(R.id.title_about);
        fullText = findViewById(R.id.fullText);

        photo.setImageResource(R.drawable.photo_about);
    }


}
