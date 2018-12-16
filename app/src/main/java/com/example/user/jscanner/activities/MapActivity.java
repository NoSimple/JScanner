package com.example.user.jscanner.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.jscanner.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MapActivity extends AppCompatActivity {

    private MapView mapview;
    private MapObjectCollection mapObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("78455045-295b-403a-8703-d72a58d52b7e");
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_map);
        mapview = findViewById(R.id.mapView);
        mapview.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 10.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        mapObject = mapview.getMap().getMapObjects().addCollection();
        mapObject.addPlacemark(new Point(55.800698, 37.594215)).setIcon(ImageProvider.fromResource(this, R.drawable.ic_action_flag));
        mapObject.addPlacemark(new Point(55.718890, 37.675840)).setIcon(ImageProvider.fromResource(this, R.drawable.ic_action_flag));
        mapObject.addPlacemark(new Point(55.722870, 37.674925)).setIcon(ImageProvider.fromResource(this, R.drawable.ic_action_flag));
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