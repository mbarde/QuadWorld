package de.funpic.mistavista.quadworld;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.opengl.Matrix;

public class TPerson {
	
	private float[] mMVPMatrix = new float[16];
	
	private long lastFrameUpdate, timeEachFrame = 100000000;
	private int frameOffset;
	
	private TWorld world;
	private TTypeManager typeMan;
	private Square[] squares;
	private TSegment segment;
	private FPoint pos, goToPos;
	private int segX, segY;
	private int job;
	private int oldDir; // shows where person last came from in the freeMatrix coords
	
	public TPerson(Square[] squares, FPoint pos, int job, TWorld world, TTypeManager typeMan,
					JSONObject json) {
		this.squares = squares.clone();
		this.world = world;
		this.typeMan = typeMan;
		this.job = job;
		this.pos =  new FPoint(pos.x, pos.y);
		this.goToPos = new FPoint(pos.x, pos.y);
		oldDir = -1;
		frameOffset = 0;
		lastFrameUpdate = System.nanoTime();
		
		segX = -1;
		segY = -1;
		
		if (json != null)
			try {
				this.loadFromJSONObject(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
		updateSegmentPos();
	}
	
	// hood shows if the neighbours are free to go (path)
	// 0 is TOP 	1 is RIGHT		2 is DOWN		3 is LEFT
	public void update(float delta) {
		float mdelta = delta / 60;
		if ( Math.abs(pos.x - goToPos.x) <= mdelta ) pos.x = goToPos.x;
		if ( Math.abs(pos.y - goToPos.y) <= mdelta ) pos.y = goToPos.y;
		
		if (pos.x < goToPos.x) pos.x += mdelta;
		else
		if (pos.x > goToPos.x) pos.x -= mdelta;
		else
		if (pos.y < goToPos.y) pos.y += mdelta;
		else
		if (pos.y > goToPos.y) pos.y -= mdelta;
		
		if (pos.x == goToPos.x && pos.y == goToPos.y && segment != null) {
			ArrayList<Integer> res = new ArrayList<Integer>();			
			TSegment[] hood = segment.getNeighbours();
			
			//doYourJob(hood);
			
			// first find out which neighbours are free to go
			for (int i = 0; i < hood.length; i++)
				if ( hood[i] != null && typeMan.isFreeToGo(hood[i].getType().id) 
					&& i != oldDir) 
					res.add(i);
			
			if (res.size() == 0) 
				for (int i = 0; i < hood.length; i++)
					if ( hood[i] != null && typeMan.isFreeToGo(hood[i].getType().id)) 
						res.add(i);
			
			if (res.size() == 0) {
				frameOffset = 0;
				return;
			}			
		
			// then randomize on which neighbour person is going 
			int i = (int)Math.round( Math.random() * (res.size()-1) );
			i = res.get(i);
			oldDir = (i+2) % 4;
			if (i == 0) {
				goToPos.x = pos.x;
				goToPos.y = pos.y + Cons.segSize;
			} else
			if (i == 1) {
				goToPos.x = pos.x + Cons.segSize;
				goToPos.y = pos.y;
			} else
			if (i == 2) {
				goToPos.x = pos.x;
				goToPos.y = pos.y - Cons.segSize;
			} else
			if (i == 3) {
				goToPos.x = pos.x - Cons.segSize;
				goToPos.y = pos.y;
			} 
			
		} 
		else { // if he is moving do this:
			updateSegmentPos();

			if (System.nanoTime() - lastFrameUpdate > timeEachFrame) {
				frameOffset = (frameOffset + 1) % 4;
				lastFrameUpdate = System.nanoTime();
			}
		}
		
	}
	
	public void render(float[] mModelMatrix, float[] mVMatrix, float[] mProjMatrix) {
		Matrix.translateM(mModelMatrix, 0, pos.x, pos.y, 0);
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
		int h = 0;
		if (oldDir > -1) h = oldDir * 4;
		squares[h + frameOffset].draw(mMVPMatrix);
	}
	
	private void updateSegmentPos() {
		int x = (int)Math.round( pos.x / Cons.segSize );
		int y = (int)Math.round( pos.y / Cons.segSize );
		
		if (x != segX || y != segY) {
			world.changePersonSegmentPos(this, segX, segY, x, y);
			segX = x;
			segY = y;
		}
	}
	
	public void doYourJob(TSegment[] hood) {
		
		if (job == Cons.JOB_PAVER) {
			for (int j = 0; j < hood.length; j++) {
				if ( hood[j].getType().id == Cons.TYP_GRASS 
						|| hood[j].getType().id == Cons.TYP_SAND )
					hood[j].setType( typeMan.getType(Cons.TYP_BRICK_GROUND) );
			}
		} else
			
		if (job == Cons.JOB_LOGGER) {
			for (int j = 0; j < hood.length; j++) {
				if ( hood[j].getType().id == Cons.TYP_TREE 
						|| hood[j].getType().id == Cons.TYP_TREE2)
					world.segmentTouched(hood[j], typeMan.getType(Cons.TYP_STUMP));
			}
			
		}
		
		
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("posx", pos.x);
		json.put("posy", pos.y);
		json.put("gotox", goToPos.x);
		json.put("gotoy", goToPos.y);
		json.put("olddir", oldDir);
		json.put("job", job);
		return json;
	}
	
	public void loadFromJSONObject(JSONObject json) throws JSONException {
		pos.x = (float)json.getDouble("posx");
		pos.y = (float)json.getDouble("posy");
		goToPos.x = (float)json.getDouble("gotox");
		goToPos.y = (float)json.getDouble("gotoy");
		oldDir = json.getInt("olddir");
		job = json.getInt("job");
	}
	
	public FPoint getPos() { return pos; }
	
	public void setSegment(TSegment segment) { this.segment = segment; }
}
