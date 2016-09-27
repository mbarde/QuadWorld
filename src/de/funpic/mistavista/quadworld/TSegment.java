package de.funpic.mistavista.quadworld;

import java.util.ArrayList;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class TSegment {
	
	private Square square, sqObject = null;
	private float zOff, yOff;
	private TType type = null;
	private boolean swingUp;
	private float angle, objZAngle, maxAngle = 60;
	private float xPos, yPos, zPos, width, height;
	private float[] mMVPMatrix = new float[16];
	private TAnimation animation = null;
	
	// neighbours ;-)
	private TSegment top, right, down, left;
	
	// persons which are actual on this segment
	private ArrayList<TPerson> persons;
	
	public TSegment(float x, float y, float z, float width, float height,
					TType type) {
		
		setType(type);
		
		this.width = width; this.height = height;
		xPos = x; yPos = y; zPos = z;
		
		persons = new ArrayList<TPerson>();
	}
	
	public void render(float[] mModelMatrix, float[] mVMatrix, float[] mProjMatrix) {
		float[] oldModelMatrix = new float[16];
		if (sqObject != null && angle == 0) oldModelMatrix = mModelMatrix.clone();
		
		Matrix.translateM(mModelMatrix, 0, xPos, yPos, 0);
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
		if (square != null) square.draw(mMVPMatrix);
		
		if (sqObject != null && angle == 0) {
			GLES20.glEnable(GLES20.GL_BLEND);
			GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
			GLES20.glDisable(GLES20.GL_DEPTH_TEST);
			
			renderTransparent(oldModelMatrix, mVMatrix, mProjMatrix, true);
			
			GLES20.glDisable(GLES20.GL_BLEND);
			GLES20.glEnable(GLES20.GL_DEPTH_TEST);			
		}
	}
	
	public void renderPersons(float[] mModelMatrix, float[] mVMatrix, float[] mProjMatrix) {
		int i = 0;
		while (i < persons.size()) {
			float[] oldModelMatrix = mModelMatrix.clone();
			persons.get(i).render(mModelMatrix, mVMatrix, mProjMatrix);
			mModelMatrix = oldModelMatrix.clone();
			i++;
		}	
	}
		
	
	public void renderTransparent(float[] mModelMatrix, float[] mVMatrix, float[] mProjMatrix,
									boolean force) {
		if ( (force) || (sqObject != null && angle > 0) ) {
			Matrix.translateM(mModelMatrix, 0, xPos, yPos+yOff, zOff);
			Matrix.rotateM(mModelMatrix, 0, angle, 1, 0, 0);
			Matrix.rotateM(mModelMatrix, 0, objZAngle, 0, 0, 1);

			Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
			Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
			if (sqObject != null) sqObject.draw(mMVPMatrix);
		}
		else		
		if (animation != null) {
			Matrix.translateM(mModelMatrix, 0, xPos, yPos, 0);
			animation.render(mModelMatrix, mVMatrix, mProjMatrix);
		}
	}
	
	public boolean touched(FPoint pos) {
		//if (sqObject == null) {
		float wh = width / 2;
		float hh = height / 2;
		if ( pos.x >= xPos-wh && pos.x <= xPos+wh
				&& pos.y >= yPos-hh && pos.y <= yPos+hh) {
					return true;
		}
		//}
		return false;
	}
	
	public void setType(TType type) {
		if (this.type == type) return;		
		this.type = type;
		if (type.square != null) this.square = type.square;
		if (type.objSquare != null) spawnObject();
		else sqObject = null;
		
		if (type.animation != null) animation = type.animation.clone();
		else animation = null;
	}
	
	private void spawnObject() {
		sqObject = type.objSquare;
		angle = 0;
		maxAngle = type.objXAngle;
		objZAngle = type.objZAngle;
		
		swingUp = true;
	}
	
	public void update(float delta) {
		
		if (swingUp) {
			angle += (delta * 2);
				
			if (angle >= maxAngle) {
				angle = maxAngle;
				swingUp = false;
			}
			
			if (sqObject != null) {
			zOff = (float)Math.sin( Math.toRadians(angle) ) * (sqObject.height/2);
			yOff = (float)Math.cos( Math.toRadians(angle) ) * (sqObject.height/2);
			if (angle == 0) {
				zOff = 0; yOff = 0;
				}
			}
			
		}
	}
	
	public TType getType() { return type; }
	
	public FPoint getPos() { return new FPoint(xPos, yPos); }
	
	public void addPerson(TPerson person) { 
		persons.add(person);
		person.setSegment(this);
	}
	
	public void removePerson(TPerson person) { persons.remove(person); }
	
	public ArrayList<TPerson> getPersons() { return persons; }
	
	public void setNeighbourhood(TSegment top, TSegment right, TSegment down, TSegment left){
		this.top = top;
		this.down = down;
		this.right = right;
		this.left = left;
	}
	
	public TSegment[] getNeighbours() { 
		return new TSegment[] { top, right, down, left }; 
	}
	
}
