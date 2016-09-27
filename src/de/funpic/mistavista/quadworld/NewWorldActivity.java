package de.funpic.mistavista.quadworld;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class NewWorldActivity extends Activity 
			implements DialogInterface.OnClickListener {

	private EditText editNewWorld;
	private AlertDialog.Builder builder;
	private String worldName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_world);
		
		editNewWorld = (EditText) findViewById(R.id.editNewWorld);
		editNewWorld.setText( this.getResources().getString(R.string.new_world_name) );
		
		// heyho here a alert is build we can use later
		builder = new AlertDialog.Builder(this);

	    builder.setTitle("Attention");
	    builder.setMessage("A world with this name already exists. Do you want to overwrite it?");

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
		getMenuInflater().inflate(R.menu.activity_new_world, menu);
		return true;
	}
	
	public void createWorld(View view) {
	    worldName = editNewWorld.getText().toString();
	    
		File file = new File( getFilesDir(), worldName);
		if (file.exists()) {
			AlertDialog alert = builder.create();
			alert.show();
			return;
		}
	    
		startOpenGLActivity();
	}
	
	private void startOpenGLActivity() {
	    Intent intent = new Intent(this, MainActivity.class);
	    intent.putExtra(MenuActivity.WORLD_NAME, worldName);
	    startActivity(intent);	
	    editNewWorld.setText( this.getResources().getString(R.string.new_world_name) );
	}
	
	@Override
    public void onClick(DialogInterface dialog, int which) {
		File file = new File( getFilesDir(), worldName);
		if (file.exists()) file.delete();
		startOpenGLActivity();
    }

}
