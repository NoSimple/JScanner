package com.example.user.jscanner.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.jscanner.Adapters.CustomAdapter;
import com.example.user.jscanner.R;
import com.example.user.jscanner.presenters.DetailPresenter;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EBAY_BARCODE_LINK = "https://www.ebay.com/sch/i.html?&_nkw=%s";
    public static final String GOODSMATRIX_LINK = "http://www.goodsmatrix.ru/goods/%s.html";
    public static final String BARCODE_LOOKUP_LINK = "https://www.barcodelookup.com/%s";
    public static final String CODE_EXTRA_KEY = "CODE";

    private ProgressBar progressBar;
    private DetailPresenter presenter;
    private ImageView flagImageView;
    private TextView flagTextView;
    private TextView barcodeCodeTextView;
    private ImageView barcodeImageView;
    private LinearLayout searchLayout;
    private ProgressBar loadProgressBar;
    private RecyclerView recyclerView;

    private CustomAdapter adapter;


    private String code;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.det_pb);
        flagImageView = findViewById(R.id.flag_image);
        flagTextView = findViewById(R.id.flag_text);
        barcodeImageView = findViewById(R.id.det_barcode_image);
        barcodeCodeTextView = findViewById(R.id.det_barcode_code);
        searchLayout = findViewById(R.id.search_layout);
        loadProgressBar = findViewById(R.id.det_load_pb);
        recyclerView = findViewById(R.id.det_rv);

        findViewById(R.id.ebay_search).setOnClickListener(this);
        findViewById(R.id.gm_search).setOnClickListener(this);
        findViewById(R.id.bl_search).setOnClickListener(this);

        presenter = new DetailPresenter();
        presenter.onAttach(this);

        code = getIntent().getStringExtra(CODE_EXTRA_KEY);

        adapter = new CustomAdapter(code);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        presenter.process(code);
    }

    @Override
    protected void onStop() {
        presenter.onDetach();
        super.onStop();
    }

    public void showPB(){
       progressBar.setVisibility(View.VISIBLE);
    }

    public void hidePB(){
        progressBar.setVisibility(View.GONE);
    }

    public void setFlagImage(Drawable drawable){flagImageView.setImageDrawable(drawable);}

    public void setFlagText(String text){flagTextView.setText(text);}

    public void setBarcodeImage(Bitmap bitmap){barcodeImageView.setImageBitmap(bitmap);}

    public void setBarcodeCode(String code){barcodeCodeTextView.setText(code);}

    public ImageView getFlagImageView() {return flagImageView;}

    public void hideBarcodeImage() {barcodeImageView.setVisibility(View.GONE);}

    public void hideSearch() {searchLayout.setVisibility(View.GONE);}

    public void hideLoadPB(){
        loadProgressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        String url = null;
        switch (v.getId()){
            case R.id.ebay_search :
                url = String.format(EBAY_BARCODE_LINK, code);
                break;
            case R.id.gm_search :
                url = String.format(GOODSMATRIX_LINK, code);
                break;
            case R.id.bl_search :
                url = String.format(BARCODE_LOOKUP_LINK, code);
                break;
            default:
        }
        if (url != null){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
