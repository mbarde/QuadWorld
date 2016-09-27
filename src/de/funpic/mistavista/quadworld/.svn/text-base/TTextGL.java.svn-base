package de.funpic.mistavista.quadworld;

import android.opengl.Matrix;

public class TTextGL{
	
	private Square[] squares;

	
	public TTextGL(int texture) {
		generateSquares(texture, 1, 10);
	}
	
	private void generateSquares(int texture, int rows, int cols) {
		squares = new Square[70];
		
		for (int i = 0; i<70; i++) {
			float xFac = (float)1/cols;
			float yFac = (float)1/rows;
			
			float x = xFac * (i % cols);
			float y = yFac * (float)Math.floor(i / cols);

			squares[i] = new Square(0,0,0, 1, 1, texture,
					x, 0,
					x, 1,
					x + xFac, 1,
					x + xFac, 0 );
		}
	}
	
	// shows text, cPos is the center position
	public void showText(FPoint cPos, String text, float fsize, 
						float clr_r, float clr_g, float clr_b,
						float[] mModelMatrix, float[] mVMatrix, float[] mProjMatrix) 
	{
		// init pos and translate to the pos
		FPoint pos = new FPoint(cPos.x - text.length()*fsize/2, cPos.y);
		
		Matrix.translateM(mModelMatrix, 0, pos.x, pos.y, 0.0f);
		Matrix.scaleM(mModelMatrix, 0, fsize, fsize, 1);
		
		int nr;
		
		// map ASCII characters to our character map
		for (int i = 0; i < text.length(); i++) {
			int c = text.charAt(i);
			
			if ( c >= 48 && c <= 57) nr = c - 48;
				else
					nr = -1;
			
			if (nr > -1) {
				float[] mMVPMatrix = new float[16];
				Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
				Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
				squares[nr].setColor(clr_r, clr_g, clr_b);
				squares[nr].draw(mMVPMatrix);
			}
			
			// just translate one step to the right
			Matrix.translateM(mModelMatrix, 0, 1, 0, 0.0f);
		}
		
	}
	
}