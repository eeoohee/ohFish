package com.greenapp.anglers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class FishMapActivity extends BaseDemoActivity 
				implements ClusterManager.OnClusterClickListener<MyItem>, 
				ClusterManager.OnClusterInfoWindowClickListener<MyItem>, 
				ClusterManager.OnClusterItemClickListener<MyItem>, 
				ClusterManager.OnClusterItemInfoWindowClickListener<MyItem> {
	
	private ClusterManager<MyItem> mClusterManager;
	


	private class MyItemReader extends DefaultClusterRenderer<MyItem> {
		private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;


		public MyItemReader() {
			super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
		}
		
		
		@Override
        protected void onBeforeClusterItemRendered(MyItem fish, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
            mImageView.setImageResource(fish.profilePhoto);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(fish.name);
        }
		
		@Override
        protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
			
			// Draw multiple people.
	        // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
	        List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
	        int width = mDimension;
	        int height = mDimension;
	
	        for (MyItem p : cluster.getItems()) {
	            // Draw 4 at most.
	            if (profilePhotos.size() == 4) break;
	            Drawable drawable = getResources().getDrawable(p.profilePhoto);
	            drawable.setBounds(0, 0, width, height);
	            profilePhotos.add(drawable);
	        }
	        MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
	        multiDrawable.setBounds(0, 0, width, height);
	
	        mClusterImageView.setImageDrawable(multiDrawable);
	        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
	        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
	    }
	
	    @Override
	    protected boolean shouldRenderAsCluster(Cluster cluster) {
	        // Always render clusters.
	        return cluster.getSize() > 1;
	    }


		/*
	     * This matches only once in whole input,
	     * so Scanner.next returns whole InputStream as a String.
	     * http://stackoverflow.com/a/5445161/2183804
	     */
	    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";
	    
	    public List<MyItem> read(InputStream inputStream) throws JSONException {
	        List<MyItem> items = new ArrayList<MyItem>();
	        String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
	        JSONArray array = new JSONArray(json);
	        for (int i = 0; i < array.length(); i++) {
	            JSONObject object = array.getJSONObject(i);
	            String name = object.getString("fish");
	            double lat = object.getDouble("lng"); //TODO filp the data...
	            double lng = object.getDouble("lat");
	            int photoid = getPhoto(name);
	            
	            items.add(new MyItem(lat, lng, name, photoid));
	        }
	        return items;
	    }
	    
	    public int getPhoto(String name) {
	    	
	    	if (name.equals("Bloody-Red Shrimp")) {
	    		return R.drawable.f1_bloody_shrimp;
	    		
	    	} else if (name.equals("Atlantic Salmon")){
	    		return R.drawable.f2_atlantic_salmon;
	    		
	    	} else if (name.equals("Brown Trout")){
	    		return R.drawable.f3_brown_trout;
	    		
	    	} else if (name.equals("Chinook Salmon")){
	    		return R.drawable.f4_chinook_salmonart_art;
	    		
	    	} else if (name.equals("Coho Salmon")){
	    		return R.drawable.f5_coho_salmon;
	    		
	    	} else if (name.equals("Common Periwinkle")){
	    		return R.drawable.f6_common_periwinkle;
	    			
	    	} else if (name.equals("Green Crab")){
	    		return R.drawable.f7_green_crab;
	    		
	    	} else if (name.equals("Japanese Littleneck")){
	    		return R.drawable.f8_japan_littleneck;
	    		
	    	} else if (name.equals("Rainbow Trout")){
	    		return R.drawable.f9_rainbow_trout;
	    		
	    	} else if (name.equals("Softshell Clam")){
	    		return R.drawable.f11_softshell_clam;
	    		
	    	} else if (name.equals("White Bass")){
	    		return R.drawable.f12_white_bass;
	    		
	    	} else if (name.equals("Rusty Crayfish")){
	    		return R.drawable.f13_rusty_crayfish;
	    		
	    	} else if (name.equals("White Perch")){
	    		return R.drawable.f14_white_perch;
	    		
	    	} else {
	    		return R.drawable.others;
	    		
	    	}
	    	
	    	//return 1;
	    	
	    }
	    
	    
	}


    @Override
    protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.635621, -104.218750), 5f));

        mClusterManager = new ClusterManager<MyItem>(this, getMap());
        //old onesold onesold onesold onesold onesold onesold onesold ones
       mClusterManager.setRenderer(new MyItemReader());
        getMap().setOnCameraChangeListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        //readItems();
       //mClusterManager.cluster();
        //old onesold onesold onesold onesold onesold onesold onesold ones
        
        
        getMap().setOnCameraChangeListener(mClusterManager);
        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
        mClusterManager.cluster();
        
       
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
                
                MyItem offsetItem = new MyItem(lat, lng, item.name, item.profilePhoto);
                mClusterManager.addItem(offsetItem);
            }
        //}
    }

    @Override
    public boolean onClusterClick(Cluster<MyItem> cluster) {
        // Show a toast with some info when the cluster is clicked.
        String fishName = cluster.getItems().iterator().next().name;
        Toast.makeText(this, cluster.getSize() + " (including " + fishName + ")", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<MyItem> cluster) {
        // Does nothing, but you could go to a list of the users.
    }

    @Override
    public boolean onClusterItemClick(MyItem item) {
        // Does nothing, but you could go into the user's profile page, for example.
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(MyItem item) {
        // Does nothing, but you could go into the user's profile page, for example.
    }


}