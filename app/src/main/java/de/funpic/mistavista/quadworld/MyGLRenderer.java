package de.funpic.mistavista.quadworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import android.widget.Toast;

public class MyGLRenderer implements GLSurfaceView.Renderer {
	
	private static int M_START = 0;
	private static int M_GAME = 1;
	private static int M_HELP = 2;
	
	// Position the eye behind the origin.
	final float eyeX = 0.0f;
	final float eyeY = 0.0f;
	final float eyeZ = 4;

	// We are looking toward the distance
	final float lookX = 0.0f;
	final float lookY = 0.0f;
	final float lookZ = -5.0f;

	// Set our up vector. This is where our head would be pointing were we holding the camera.
	final float upX = 0.0f;
	final float upY = 1.0f;
	final float upZ = 0.0f;
	
	public float dx = 0.0f;
	public float dy = 0.0f;

    private static final String TAG = "MyGLRenderer";

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjMatrix = new float[16];
    private final float[] mVMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];
    
    private Context activityContext;
    private MainActivity mContext;
    private int mode = 0;
    private TWorld world = null;
    private TTextureManager texMan = null;
    private TTypeManager typeMan = null;
    private JSONObject json = null;
    private String worldName;

    private boolean ignoreTouch = false;
    private long lastTouchTime, touchIgnoreTime;
    
    private float timeDeltaS = 1;
    public float FPS = 0.0f;
    
    public MyGLRenderer(Context activityContext, String worldName) {
    	this.activityContext = activityContext;
    	this.mContext = (MainActivity) activityContext;
    	
    	this.worldName = worldName;
    }
    
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
 	
        // Set the background frame color
        GLES20.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);

		// Set the view matrix. This matrix can be said to represent the camera position.
		// NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
		// view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
		Matrix.setLookAtM(mVMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
    	
	   	ignoreTouch = false;
	   	texMan = new TTextureManager(activityContext);
	   	typeMan = new TTypeManager(texMan);
	   	
	   	loadJSONObject(worldName);
	   	world = new TWorld(60, 60, texMan, typeMan, mVMatrix, mProjMatrix, json);
		
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
	
    }

    @Override
    public void onDrawFrame(GL10 unused) {

        final long lastNS = System.nanoTime();
        
    	GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
    	
    	if (ignoreTouch)
    		if (System.nanoTime() - lastTouchTime > touchIgnoreTime) ignoreTouch = false;
    	
    	// Render here
    	world.render();
    	world.update(timeDeltaS);
    	
        final long  timeDeltaNS = System.nanoTime() - lastNS;

        // Determine time since last frame in seconds.
        final float timeDeltaS = timeDeltaNS * 1.0e-9f;
        FPS = 1 / timeDeltaS;

        mContext.fps = Math.round( FPS );
        //mContext.setTitleText("QuadWorld! Fps: " + FPS);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
		// Set the OpenGL viewport to the same size as the surface.
		GLES20.glViewport(0, 0, width, height);

		// Create a new perspective projection matrix. The height will stay the same
		// while the width will vary as per aspect ratio.
		final float ratio = (float) width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1f;
		final float far = 10.0f;
		
		Matrix.frustumM(mProjMatrix, 0, left, right, bottom, top, near, far);
		world.setProjMatrix(mProjMatrix);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
     * Utility method for debugging OpenGL calls. Provide the name of the call
     * just after making it:
     *
     * <pre>
     * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
     * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
     *
     * If the operation is not successful, the check throws an error.
     *
     * @param glOperation - Name of the OpenGL call to check.
     */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
    
    public void touched(FPoint pos) { 
    	if (!ignoreTouch) {
        	//world.touched(pos);
    	}
    }
    
    public void onLongPress(FPoint pos) {
    	if (!ignoreTouch) {
        	world.touched(pos);
    	}
    }
    
    public int backPressed() {
    	return 0;
    }
    
    public void ignoreTouch(long time) {
    	lastTouchTime = System.nanoTime();
    	touchIgnoreTime = time;
    	ignoreTouch = true;
    }
    
    public void moved(FPoint delta) {

    }
    
    public void scrolled(FPoint delta, FPoint pos1, FPoint pos2) {
    	world.scrolled(delta.x, delta.y, pos1, pos2);
    }
    
    public void onPause() { 
    	try {
			json = world.toJSONObject();
			saveJSONObjectToFile(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void onResume() { 

    }    
    
    public void saveJSONObjectToFile(JSONObject json) throws IOException {		
        OutputStreamWriter outputStreamWriter = 
        		new OutputStreamWriter(mContext.openFileOutput(worldName, Context.MODE_PRIVATE));
		outputStreamWriter.write( json.toString() );
		outputStreamWriter.close();
		

		Toast.makeText(mContext.getApplicationContext(), 
				worldName +  " saved.", Toast.LENGTH_LONG).show();
    }
    
    public JSONObject loadJSONObject(String worldName) {
		File file = new File( mContext.getFilesDir(), worldName);
		if (!file.exists()) return null;
		
		StringBuilder text = new StringBuilder();

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		    }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			json = new JSONObject( text.toString() );
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json;
    }
    
}