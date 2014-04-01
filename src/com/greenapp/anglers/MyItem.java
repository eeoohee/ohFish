package com.greenapp.anglers;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private String name;

    public MyItem(double lat, double lng, String name) {
        mPosition = new LatLng(lat, lng);
        name = name;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
    
    public String getName() {
    	return name;
    }
}
