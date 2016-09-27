package de.funpic.mistavista.quadworld;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class TGestureListener extends GestureDetector.SimpleOnGestureListener{

	private MyGLRenderer mRenderer;
	private int disp_width, disp_height;
	
	public TGestureListener(MyGLRenderer mRenderer, int disp_width, int disp_height) {
		this.mRenderer = mRenderer;
		this.disp_width = disp_width;
		this.disp_height = disp_height;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent ev) {		 
		//mRenderer.touched( new FPoint(ev.getX(), ev.getY() ));
		mRenderer.onLongPress( new FPoint(ev.getX() / disp_width, ev.getY() / disp_height ));
		return true;
	}

	@Override
	public void onShowPress(MotionEvent ev) {
	}

	@Override
	public void onLongPress(MotionEvent ev) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		mRenderer.scrolled( new FPoint( distanceX / disp_width, distanceY / disp_height ), 
							new FPoint( e1.getX() / disp_width, e1.getY() / disp_height ),
							new FPoint( e2.getX() / disp_width, e2.getY() / disp_height ) );
		return true;
	}

	@Override
	public boolean onDown(MotionEvent ev) {
		mRenderer.touched( new FPoint(ev.getX() / disp_width, ev.getY() / disp_height ));
		return true;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		mRenderer.moved( new FPoint(velocityX, velocityY) );
		return true;
	}

}
