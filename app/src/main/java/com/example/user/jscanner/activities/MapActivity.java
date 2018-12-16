package com.example.user.jscanner.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.jscanner.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MapActivity extends AppCompatActivity {

    private MapView mapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("78455045-295b-403a-8703-d72a58d52b7e");
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_map);
        mapview = findViewById(R.id.mapView);
        mapview.getMap().move(
                new CameraPosition(new Point(57.751588, 37.573856), 20.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapview.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
    }
}