package com.greenapp.anglers;

import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

public class FishMapActivity extends BaseDemoActivity {
    private ClusterManager<MyItem> mClusterManager;

    @Override
    protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.635621, -104.218750), 5));

        mClusterManager = new ClusterManager<MyItem>(this, getMap());

        getMap().setOnCameraChangeListener(mClusterManager);
        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MyItem> items = new MyItemReader().read(inputStream);
       // for (int i = 0; i < 10; i++) {
         //   double offset = i / 60d;
            for (MyItem item : items) {
                LatLng position = item.getPosition();
                double lat = position.latitude; //+ offset;
                double lng = position.longitude; // + offset;
                MyItem offsetItem = new MyItem(lat, lng, item.getName());
                mClusterManager.addItem(offsetItem);
            }
        //}
    }
}