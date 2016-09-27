package de.funpic.mistavista.quadworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {
	
	// used to pass the world name to the openGL activity
	public static String WORLD_NAME = "de.funpic.mistavista.QuadWorld.WorldName";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}
	
	public void gotoStartNewWorld(View view) {
		 Intent intent = new Intent(this, NewWorldActivity.class);
		 startActivity(intent);
	}
	
	public void gotoLoadWorld(View view) {
		 Intent intent = new Intent(this, LoadWorldActivity.class);
		 startActivity(intent);
	}

}
