package de.funpic.mistavista.quadworld;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends Activity {

	public float fps = 		0.0f;
	
    private MyGLSurfaceView mGLView;   


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // get the world name from the activity which started this one
        Intent intent = getIntent();
        String worldName = intent.getStringExtra(MenuActivity.WORLD_NAME);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new MyGLSurfaceView(this, worldName);
        setContentView(mGLView);
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC); 
        System.out.println("CREATED");
    }
    

    @Override
    protected void onPause() {
        super.onPause();

        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
        mGLView.getRenderer().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
 
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
        mGLView.getRenderer().onResume();
    }
    
    @Override
    public void onBackPressed() {
    	if ( mGLView.getRenderer().backPressed() == 0 ) this.finish();
    }
   
}

class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    
    private GestureDetector mGestureDetector;

    public MyGLSurfaceView(Context context, String worldName) {
        super(context);
        
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer(context, worldName);
        setRenderer(mRenderer);

        MainActivity activity = (MainActivity) context;
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
        mGestureDetector = new GestureDetector(context, new TGestureListener(mRenderer,
        										metrics.widthPixels, metrics.heightPixels) );
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	return (mGestureDetector.onTouchEvent(e));
    }
    
    public MyGLRenderer getRenderer() { return mRenderer; }
    
}
