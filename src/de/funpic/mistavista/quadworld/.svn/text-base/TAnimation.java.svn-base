package de.funpic.mistavista.quadworld;

import android.opengl.Matrix;

public class TAnimation {
	
	private float[] mMVPMatrix = new float[16];
	
	private Square[] squares;
	private long frameTime, lastChangeTime;
	private float startprob;
	
	private int curFrame;
	private boolean stop = true;
	
	public TAnimation(Square[] squares, long frameTime, float startprob) {
		this.squares = squares.clone();
		this.frameTime = frameTime;
		this.startprob = startprob;
		curFrame = 0;
	}
	
	public void start() {
		curFrame = 0;
		lastChangeTime = System.nanoTime();
		stop = false;		
	}
	
	public void render(float[] mModelMatrix, float[] mVMatrix, float[] mProjMatrix) {
		update();
		
		if (!stop) {
			Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
			Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
			squares[curFrame].draw(mMVPMatrix);						
		}		
	}
	
	private void update() {
		if (stop) 
			if ( startprob >= Math.random() ) start();
	
		
		if (!stop && System.nanoTime() - lastChangeTime > frameTime) {
			curFrame = (curFrame + 1) % squares.length;
			if (curFrame == 0) stop = true;
			lastChangeTime = System.nanoTime();
		}
	}
	
	public TAnimation clone() {
		return new TAnimation(squares, frameTime, startprob);
	}
	
}
