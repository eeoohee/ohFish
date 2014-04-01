package com.greenapp.anglers;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFish extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_fish);
	
	}
	

	
	
	/** Called when the user clicks the Species button */
	public void showMap(View view) {
		Intent intent = new Intent(this, FishMapActivity.class);
	    //Intent intent = new Intent(this, MapActivity.class);
	   // EditText editText = (EditText) findViewById(R.id.edit_message);
	   //String message = editText.getText().toString();
	   // intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_fish, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		//Fragment fragment = new DummySectionFragment();
		//Bundle args = new Bundle();
		//args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		//fragment.setArguments(args);
		//getSupportFragmentManager().beginTransaction()
		//.replace(R.id.container, fragment).commit();
		return true;
	}



}
