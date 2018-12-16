package com.example.user.jscanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.jscanner.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    private static final String SHARED_PREF_INSTANCE = "INTRO_SHARED_PREF";
//    private static final String BOOLEAN_PREF_KEY = "KEY_SHARED_PREF";
    private static final String LOG_TAG = "Testlog";
    Button scanBtn;
    EditText manualET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(LOG_TAG, "isFirst? " + isFirstTime());



        scanBtn = findViewById(R.id.scan_btn);
        manualET = findViewById(R.id.manual_barcode);
        manualET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    onClick(scanBtn);
                    handled = true;
                }
                return handled;
            }
        });
        manualET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(s.toString().matches("^\\d{13}"))){
                    manualET.setError("Невалидный код");
                }
            }
        });

        scanBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        String manualBarcode = manualET.getText().toString();
        if(!(manualBarcode.isEmpty() || manualBarcode.matches("^\\d{13}"))){
            manualET.setError("Невалидный код");
            return;
        }
        if (manualBarcode.isEmpty()) {
            Intent intent = new Intent(this, ScannerActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("CODE", manualBarcode);
            startActivity(intent);
        }
    }

//    private boolean isFirstTime() {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_INSTANCE, MODE_PRIVATE);
//        return sharedPreferences.getBoolean(BOOLEAN_PREF_KEY, true);
//    }
//
//    private void saveBooleanToSharedPref(Boolean firstTime) {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_INSTANCE, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean(BOOLEAN_PREF_KEY, firstTime);
//        editor.apply();
//    }






    @Override
    protected void onStop() {
        super.onStop();

    }
}
