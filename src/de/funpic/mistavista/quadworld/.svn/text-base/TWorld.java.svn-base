package de.funpic.mistavista.quadworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

public class TWorld {
	
    private float[] mProjMatrix = new float[16];
    private float[] mVMatrix = new float[16];
    private float[] mModelMatrix = new float[16];
	
	private int width, height;
	private FPoint pos;
	private float zPos = 0;
	private TTextureManager texMan;
	private TTypeManager typeMan;
	private TPatternManager patMan;
	private TInGameMenu menu;
	private TTextGL text;
	private TSegment[][] segments;
	
	private ArrayList<TPerson> persons;
	private Square[] personSquares;
	
	public TWorld(int width, int height, TTextureManager texMan, TTypeManager typeMan,
					float[] mVMatrix, float[] mProjMatrix, JSONObject json) {
		this.width = width;
		this.height = height;
		this.texMan = texMan;
		this.typeMan = typeMan;
		this.mVMatrix = mVMatrix;
		this.mProjMatrix = mProjMatrix;
		
		text = new TTextGL( texMan.getTexture(Cons.TID_FONT) );
		patMan = new TPatternManager(typeMan);
		menu = new TInGameMenu(typeMan, texMan, text);
		
		pos = new FPoint(width*Cons.segSize/2-Cons.segSize/2,height*Cons.segSize/2-Cons.segSize/2);
		
		persons = new ArrayList<TPerson>();
		initPersonSquares();
		
		if (json != null)
			try {
				this.loadFromJSONObject(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		else createWorld();
	}
	
	private void createWorld() {
		segments = new TSegment[width][height];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				segments[x][y] = new TSegment(  x * Cons.segSize, y * Cons.segSize, 0,
												Cons.segSize, Cons.segSize,
												typeMan.getRandomElementalType() );
				//Log.d("crWrld", x + " " + y + " at " + x * Cons.segSize + " " + y * Cons.segSize);
			}
		
	
		this.spawnRiver(false);
		this.spawnRiver(false);
		this.spawnRiver(true);
		this.spawnRiver(true);
		
		this.setNeighbours();
		
		this.checkPatterns(0, width-1, 0, height-1);
		
	}
	
	private void setNeighbours() {
		TSegment top, right, down, left;
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				top = getSegment(x,y+1);
				right = getSegment(x+1,y);
				down = getSegment(x,y-1);
				left = getSegment(x-1,y);
				segments[x][y].setNeighbourhood(top, right, down, left);
			}
	}
	
	public void update(float delta) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				segments[x][y].update(delta);
			}
		
		menu.update(delta);
		
		// update persons
		for (int i = 0; i < persons.size(); i++)
			persons.get(i).update(delta);

		
	}
	
	public void render() {
		int fx = (int)Math.round( pos.x ) - Cons.XViewSize;
		int tx = fx + Cons.XViewSize * 2;
		int fy = (int)Math.round( pos.y ) - Cons.YViewSize;
		int ty = fy + Cons.YViewSize * 2;
		
		if (fx < 0) fx = 0;
		if (tx > width-1) tx = width-1;
		if (fy < 0) fy = 0;
		if (ty > height-1) ty = height-1;
		
		// render intransparent stuff
		for (int x = fx; x <= tx; x++)
			for (int y = fy; y <= ty; y++) {
				Matrix.setIdentityM(mModelMatrix, 0);
		        Matrix.translateM(mModelMatrix, 0, -pos.x, -pos.y, zPos);
				segments[x][y].render(mModelMatrix, mVMatrix, mProjMatrix);
			}
		
		// render transparent stuff
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		GLES20.glDisable(GLES20.GL_DEPTH_TEST);
		
		// draw objects and persons
		for (int y = ty; y >= fy; y--)
			for (int x = tx; x >= fx; x--) {
				Matrix.setIdentityM(mModelMatrix, 0);
		        Matrix.translateM(mModelMatrix, 0, -pos.x, -pos.y, zPos);
				segments[x][y].renderTransparent(mModelMatrix, mVMatrix, mProjMatrix, false);
				
				Matrix.setIdentityM(mModelMatrix, 0);
		        Matrix.translateM(mModelMatrix, 0, -pos.x, -pos.y, zPos);
		        segments[x][y].renderPersons(mModelMatrix, mVMatrix, mProjMatrix);
			}
		
		menu.render(mVMatrix, mProjMatrix);
		
		GLES20.glDisable(GLES20.GL_BLEND);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
	}
	
	public void checkPatterns(int fromX, int toX, int fromY, int toY) {
		boolean found;
		int runs = 0;
		
		do {
		found = false;
		for (int x = fromX; x <= toX; x++) {
			for (int y = fromY; y <= toY; y++) {
				
				for (int i = 0; i < patMan.getPatternCount(); i++) 
					if (checkPattern( x, y, patMan.getPattern(i) ) ) {
						setPattern(x, y, patMan.getPattern(i));
						found = true;
					}
			}
		}
		runs++;
		} while (found && runs < Cons.maxCheckPatternsRuns);
		
		this.setNeighbours();
	}
	
	public boolean checkPattern(int x, int y, TPattern pattern) {
		for (int mx = 0; mx < pattern.width; mx++)
			for (int my = 0; my < pattern.height; my++)
				if ( pattern.types[mx][my] != null) {
					TType type = checkType(x+mx, y+my);
					if (type == null) return false; // out of bounds !!!
					
					if (!pattern.checkIfEqualTypes(type, mx, my))
						return false;
				}		
		return true;
	}
	
	public void setPattern(int x, int y, TPattern pattern) {

		for (int mx = 0; mx < pattern.width; mx++)
			for (int my = 0; my < pattern.height; my++)
				if (pattern.typesAfter[mx][my] != null) {
					segments[x+mx][y+my].setType( pattern.typesAfter[mx][my] );
					if ( segments[x+mx][y+my].getType().id == Cons.TYP_HUT )
						spawnPerson( segments[x+mx][y+my].getPos(), Cons.JOB_LOGGER, null );
					
					if ( segments[x+mx][y+my].getType().id == Cons.TYP_STONE_HUT )
						spawnPerson( segments[x+mx][y+my].getPos(), Cons.JOB_PAVER, null );
				}
					
	}
	
	public TType checkType(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return null;
		return segments[x][y].getType();
	}
	
	public void touched(FPoint pos) {
		
		if ( menu.touched(pos) ) return;
		
		pos.x = (pos.x * Cons.dispToGLRatioX) + this.pos.x - (Cons.dispToGLRatioX*Cons.segSize/2); 
		pos.y = ( (1-pos.y-0.05f) * Cons.dispToGLRatioY) + this.pos.y - (Cons.dispToGLRatioY*Cons.segSize/2); 
		
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) 
				if ( segments[x][y].touched(pos) ) 
					segmentTouched( segments[x][y], menu.getChoosenItem() );
		
		this.checkPatterns(0, width-1, 0, height-1);
	}
	
	public void segmentTouched(TSegment segment, TType nextType) {
		TType type = segment.getType();
		int t = type.nextType;
		if (t > -1) segment.setType( typeMan.getType(t) );
		else {
			segment.setType( nextType );
			menu.deleteOneOfChoosenItem();
		}
		
		menu.addItems(type.typesToDrop);
	}
	
	public void segmentChange(int x, int y) {
		segments[x][y].setType( typeMan.getNextElementalType(segments[x][y].getType()) );
		this.checkPatterns(0, width-1, 0, height-1);
	}
		
	public void scrolled(float distanceX, float distanceY, FPoint pos1, FPoint pos2) {
		if (menu.scrolled(distanceX, distanceY, pos1, pos2)) return;
		pos.x += (distanceX * 10);
		pos.y -= (distanceY * 10);
	}
	
	private void initPersonSquares() {
		personSquares = new Square[16];
		for (int i = 0; i < 16; i++) {
			float x = (float)Math.floor(i / 4) * 1/4f;
			float y = (i % 4) * 1/4f;
			personSquares[i] = new Square(0,0,0, Cons.personWidth, Cons.personHeight,
										texMan.getTexture( Cons.TID_PERSON),
										x, y, x, y + 1/4f,
										x + 1/4f, y + 1/4f, x + 1/4f, y );
		}
	}
	
	private void spawnPerson(FPoint pos, int job, JSONObject json) {
		persons.add( new TPerson(personSquares, pos, job, this, typeMan, json) );
	}
	
	public void changePersonSegmentPos(TPerson person, int oldX, int oldY, 
										int newX, int newY) {
		if (oldX > -1 && oldY > -1) segments[oldX][oldY].removePerson(person);
		segments[newX][newY].addPerson(person);		
	}
	
	public void setProjMatrix(float[] mProjMatrix) { this.mProjMatrix = mProjMatrix; }
	
	private void spawnRiver(boolean vertical) {
		int lastr, x, y;
		
		if (vertical) {
			x = (int) Math.round( Math.random() * (width - 10) ) + 5;
			y = height-2;
		
			segments[x][height-1].setType( typeMan.getType(Cons.TYP_WATER) );
			segments[x][height-2].setType( typeMan.getType(Cons.TYP_WATER) );
		
			lastr = 2;
		} else {
			x = 1;
			y = (int) Math.round( Math.random() * (height - 10) ) + 5;
		
			segments[0][y].setType( typeMan.getType(Cons.TYP_WATER) );
			segments[1][y].setType( typeMan.getType(Cons.TYP_WATER) );
		
			lastr = 1;			
		}
			
		do {
			int r;
			do {
			r = (int) Math.round( Math.random() * 3 );
			} while (r == (lastr+2)%4 );

			if (r == 0) y += 1;
			else
			if (r == 1) x += 1;
			else
			if (r == 2) y -= 1;
			else
			if (r == 3) x -= 1;
			
			segments[x][y].setType( typeMan.getType(Cons.TYP_WATER) );
		} while (x < width-1 && x > 0 && y < height-1 && y > 0);
			
	}
	
	public TSegment getSegment(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return null;
		return segments[x][y];
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject json = new JSONObject();
		JSONArray segArray = new JSONArray();
		JSONArray persArray = new JSONArray();
		json.put("width", width);
		json.put("height", height);
		json.put("posx", pos.x);
		json.put("posy", pos.y);
		
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				JSONObject js = new JSONObject();
				js.put("x", x);
				js.put("y", y);
				js.put("typeid", segments[x][y].getType().id);
				segArray.put(js);
			}
		
		for (int i = 0; i < persons.size(); i++)
			persArray.put( persons.get(i).toJSONObject() );
		
		json.put("segments", segArray);
		json.put("persons", persArray);
		json.put("menu", menu.toJSONObject() );
		
		return json;
	}
	
	public void loadFromJSONObject(JSONObject json) throws JSONException {
		pos.x = (float)json.getDouble("posx");
		pos.y = (float)json.getDouble("posy");
		
		width = json.getInt("width");
		height = json.getInt("height");
		segments = new TSegment[width][height];
		
		JSONArray segArray = json.getJSONArray("segments");
		
		for (int i = 0; i < segArray.length(); i++)  {
			JSONObject js = segArray.getJSONObject(i);
			int x = js.getInt("x");
			int y = js.getInt("y");
			int t = js.getInt("typeid");
			segments[x][y] = new TSegment(  x * Cons.segSize, y * Cons.segSize, 0,
								Cons.segSize, Cons.segSize,
								typeMan.getType(t) );
		}
		
		JSONArray persArray = json.getJSONArray("persons");
		for (int i = 0; i < persArray.length(); i++)
			spawnPerson( new FPoint(0,0), 0, (JSONObject)persArray.get(i) );
		
		menu.loadFromJSONObject( json.getJSONObject("menu") );
		
		this.setNeighbours();
	}
	
	public void findShortestWay(int x1, int y1, int x2, int y2) {
		
	}
	
	public TInGameMenu getnGameMenu() { return menu; }

}
