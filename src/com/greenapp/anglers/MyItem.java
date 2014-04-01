package com.greenapp.anglers;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    public final String name;
    public final int profilePhoto;

    public MyItem(double lat, double lng, String name, int photo_id) {
        mPosition = new LatLng(lat, lng);
        this.name = name;
        this.profilePhoto = photo_id;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
    
 
}
