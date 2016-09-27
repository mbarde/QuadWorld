package de.funpic.mistavista.quadworld;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class LoadWorldActivity extends Activity 
				implements DialogInterface.OnClickListener {

	private Spinner worldNamesSpinner;
	private AlertDialog.Builder builder;
	private String worldName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_world);
		
		worldNamesSpinner = (Spinner) findViewById(R.id.worldNameSpinner);
		
		loadWorldNames();
		
		// heyho here a alert is build we can use later
		builder = new AlertDialog.Builder(this);

	    builder.setTitle("Attention");
	    builder.setMessage("Really delete this world?");

	    builder.setPositiveButton("YES", this);

	    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            // Do nothing
	            dialog.dismiss();
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_load_world, menu);
		return true;
	}
	
	private void loadWorldNames() {
		String[] strings = getFilesDir().list();		
		ArrayList<String> files = new ArrayList<String>();
		
		for (int i = 0; i < strings.length; i++)
			files.add(strings[i]);

		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, files);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		worldNamesSpinner.setAdapter(adapter);
	}
	
	public void loadSelectedWorld(View view) {
		if (worldNamesSpinner.getCount() > 0) {
			Intent intent = new Intent(this, MainActivity.class);
			String worldName = worldNamesSpinner.getSelectedItem().toString();
			intent.putExtra(MenuActivity.WORLD_NAME, worldName);
			startActivity(intent);
		}
	}
	
	public void deleteSelectedWorld(View view) {
		worldName = worldNamesSpinner.getSelectedItem().toString();
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	@Override
    public void onClick(DialogInterface dialog, int which) {
		File file = new File( getFilesDir(), worldName);
		if (file.exists()) file.delete();
		loadWorldNames();
    }
	
}
