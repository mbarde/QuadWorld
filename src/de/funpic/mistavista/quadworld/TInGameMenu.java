package de.funpic.mistavista.quadworld;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.opengl.Matrix;

public class TInGameMenu {
	
	private float[] mModelMatrix = new float[16];
	private float[] mMVPMatrix = new float[16];
	
	private Square sqBack, sqArrowDown, sqFrame;
	private FPoint pos;
	private float yDownPos, yUpPos;
	private boolean goDown, goUp, isDown, isUp;
	
	private float itemSize = 1;
	private float itemDist = 0.2f;
	
	private ArrayList<TType> items;
	private ArrayList<Integer> itemCounts;
	private float itemXPos, itemXLeftPos, itemXRightPos;;
	private int choosenItem = 0;
	private boolean newItem;
	
	private TTextGL text;	
	private TTypeManager typMan;
	
	public TInGameMenu(TTypeManager typMan, TTextureManager texMan, TTextGL text) {
		sqFrame = new Square(0,0,0, itemSize, itemSize, texMan.getTexture(Cons.TID_FRAME));
		sqArrowDown = new Square(0,0,0, 1, 0.5f, texMan.getTexture(Cons.TID_ARROW_DOWN) );
		sqBack = new Square(0,0,0, Cons.itemBarWidth, Cons.itemBarHeight, -1);
		sqBack.setColor(0.1f, 0.1f, 0.1f);
		
		this.typMan = typMan;
		this.text = text;
		
		yDownPos = Cons.dispToGLRatioY / 2 - Cons.itemBarHeight / 2;
		yUpPos = Cons.dispToGLRatioY / 2 + Cons.itemBarHeight / 2;
		
		pos = new FPoint(0, yUpPos);
		isDown = false;
		isUp = true;
		choosenItem = 0;

		items = new ArrayList<TType>();
		itemCounts = new ArrayList<Integer>();
		
		for (int i = 0; i < Cons.elementalTypesCount; i++)
			this.addItems( new int[][] { {i,-1} } );
		
	}
	
	public void render(float[] mVMatrix, float[] mProjMatrix) {
		if (isUp) {
			Matrix.setIdentityM(mModelMatrix, 0);
			Matrix.translateM(mModelMatrix, 0, 0, yUpPos - 1, 0);
			Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
			Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
			if (newItem) sqArrowDown.setColor(1, 0, 0);
			sqArrowDown.draw(mMVPMatrix);
			if (newItem) sqArrowDown.setColor(1, 1, 1);
		}
		
		if (!isUp) {
		// draw background
		Matrix.setIdentityM(mModelMatrix, 0);
		Matrix.translateM(mModelMatrix, 0, pos.x, pos.y, 0);
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
		sqBack.draw(mMVPMatrix);
		
		// and all this crazy little items
		int i = 0;
		do {
			TType type = items.get(i);
			Square square = type.objSquare;
			if (square == null) square = type.square;
			float h = square.width;
			if (square.height  > h) h = square.height;
			h = 1 / h;
			
			Matrix.setIdentityM(mModelMatrix, 0);
			Matrix.translateM(mModelMatrix, 0, itemXPos + i * (itemSize + itemDist), pos.y, 0);
			if (choosenItem == i) Matrix.translateM(mModelMatrix, 0, 0, 0, 0.1f);
			Matrix.rotateM(mModelMatrix, 0, type.objZAngle, 0, 0, 1);
			Matrix.scaleM(mModelMatrix, 0, h, h, 0);
			Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
			Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
			square.draw(mMVPMatrix);
			
			// Rescale it to draw the count and maybe the frame
			Matrix.scaleM(mModelMatrix, 0, 1/h, 1/h, 0);
			Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mModelMatrix, 0); 
			Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
			if (i == choosenItem) sqFrame.draw(mMVPMatrix);
				
			if (itemCounts.get(i) > -1) {
				Matrix.translateM(mModelMatrix, 0, -0.2f, -0.3f, 0);
				
				text.showText( new FPoint(0,0), "" + itemCounts.get(i), 0.2f, 1, 1, 1, 
								mModelMatrix, mVMatrix, mProjMatrix);
			}
			
			i++;
		} while(i < items.size());
		}
	}
	
	public void update(float delta) {
		if (goDown) {
			pos.y -= delta / 25;			
			if (pos.y <= yDownPos) {
				pos.y = yDownPos;
				goDown = false;
				isDown = true;
				newItem = false;
			}
		}
			
		if (goUp) {
			pos.y += delta / 25;
			if (pos.y >= yUpPos) {
				pos.y = yUpPos;
				goUp = false;
				isUp = true;
			}
		}
	}
	
	public boolean touched(FPoint pos) {
		if  ( isDown && pos.y <= 0.2f ){
			choosenItem = (int)Math.round( (pos.x * 5) - (itemXPos * 5/6) ) - 2;
			fixChoosenItem();
			return true;
		}
		
		if ( isUp && pos.y <= 0.1f && pos.x >= 0.45f && pos.x <= 0.55f) {
			toggleUpDown();
			return true;
		}
		
		return false;
	}
	
	public boolean scrolled(float distanceX, float distanceY, FPoint pos1, FPoint pos2) {
		if ( isDown && pos1.y <= 0.2f ) {
			if (distanceY > 0.01f) {
				toggleUpDown();
				return true;
			}
			
			itemXPos -= (distanceX * 10);
			return true;			
		}
		
		if ( isUp && pos2.y <= 0.1f && distanceY < -0.01f ) {
			toggleUpDown();
			return true;
		}
		
		return false;
	}
	
	public void addItems(int[][] items) {
		if (items == null) return;
		
		for (int i = 0; i < items.length; i++) 
			addItem( typMan.getType(items[i][0]), items[i][1] );
		
	}
	
	private void addItem(TType item, int count) {
		for (int i = 0; i < items.size(); i++) {
			if ( items.get(i) == item ) {
				if (itemCounts.get(i) > -1) 
					itemCounts.set( i, itemCounts.get(i) + count );
				return;
			}
		}
		
		items.add(item);
		itemCounts.add(count);
		newItemWasAdded();
	}
	
	private void newItemWasAdded() {
		if (isUp) newItem = true;
		//else choosenItem = items.size()-1; its sucks sometimes ...
	}
	
	private void toggleUpDown() {
		if (isDown) {
			goUp = true;
			isDown = false;
		}
		if (isUp) {
			goDown = true;
			isUp = false;
		}
	}
	
	public TType getChoosenItem() {
		fixChoosenItem();
		return items.get(choosenItem);
	}
		
	public void deleteOneOfChoosenItem() {
		fixChoosenItem();
		
		if (itemCounts.get(choosenItem) > -1)
			itemCounts.set( choosenItem, itemCounts.get(choosenItem) - 1 );
		
		TType item = items.get(choosenItem);
		if (itemCounts.get(choosenItem) == 0) {
			items.remove(choosenItem);
			itemCounts.remove(choosenItem);
			fixChoosenItem();
		}
		
	}	
	
	private void fixChoosenItem() {
		if (choosenItem < 0) choosenItem = 0;
		if (choosenItem >= items.size()) choosenItem = items.size()-1;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("choosenitem", choosenItem);
		
		// the allItemsArray contains a array for each item
		// this array contains the item type id and the item count
		JSONArray allItemsArray = new JSONArray();
		
		for (int i = 0; i < items.size(); i++) {
			JSONObject item = new JSONObject();
			item.put("id", items.get(i).id);
			item.put("count", itemCounts.get(i));
			allItemsArray.put(item);
		}
		
		json.put("items", allItemsArray);
		return json;
	}
	
	public void loadFromJSONObject(JSONObject json) throws JSONException {
		choosenItem = json.getInt("choosenitem");
		JSONArray allItemsArray = json.getJSONArray("items");
		
		int id, count;
		items.clear();
		itemCounts.clear();
		for (int i = 0; i < allItemsArray.length(); i++) {
			JSONObject item = allItemsArray.getJSONObject(i);
			id = item.getInt("id");
			count = item.getInt("count");
			this.addItem( typMan.getType(id), count);
		}
	}

}
