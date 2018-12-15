package com.example.user.jscanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.jscanner.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button scanBtn;
    EditText manualET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.scan_btn);
        manualET = findViewById(R.id.manual_barcode);

        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String manualBarcode = manualET.getText().toString();
        if (manualBarcode == ""){
            //TODO открыть камеру
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("CODE", manualBarcode);
            startActivity(intent);
        }
    }
}
