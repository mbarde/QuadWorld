package de.funpic.mistavista.quadworld;

import android.content.Context;

public class TTextureManager {

	public int[] textures;
	private Context activityContext;
	
	public TTextureManager(Context activityContext) {
		this.activityContext = activityContext;
		loadTextures();
	}
	
	public int getTexture(int id) {
		if (id >= 0 && id < textures.length) return textures[id];
		else return -1;
	}
	
	private void loadTextures() {
		textures = new int[Cons.textureCount];
		textures[Cons.TID_BLANK] = TextureHelper.loadTexture(activityContext, R.drawable.blank);
		textures[Cons.TID_BUSH] = TextureHelper.loadTexture(activityContext, R.drawable.bush);
		textures[Cons.TID_TREE] = TextureHelper.loadTexture(activityContext, R.drawable.tree);
		textures[Cons.TID_TREE2] = TextureHelper.loadTexture(activityContext, R.drawable.tree2);
		textures[Cons.TID_REED] = TextureHelper.loadTexture(activityContext, R.drawable.reed);
		textures[Cons.TID_GRASS] = TextureHelper.loadTexture(activityContext, R.drawable.grass);
		textures[Cons.TID_SAND] = TextureHelper.loadTexture(activityContext, R.drawable.sand);
		textures[Cons.TID_WATER] = TextureHelper.loadTexture(activityContext, R.drawable.water);
		textures[Cons.TID_PATH_01] = TextureHelper.loadTexture(activityContext, R.drawable.path_01);
		textures[Cons.TID_BRIDGE_01] = TextureHelper.loadTexture(activityContext, R.drawable.bridge_01);
		textures[Cons.TID_PATH_03] = TextureHelper.loadTexture(activityContext, R.drawable.path_03);
		textures[Cons.TID_HUT] = TextureHelper.loadTexture(activityContext, R.drawable.hut);		
		textures[Cons.TID_WATER_HOLE] = TextureHelper.loadTexture(activityContext, R.drawable.water_hole);		
		textures[Cons.TID_ARROW_DOWN] = TextureHelper.loadTexture(activityContext, R.drawable.arrow_down);		
		textures[Cons.TID_FRAME] = TextureHelper.loadTexture(activityContext, R.drawable.frame);		
		textures[Cons.TID_SMALL_ISLAND] = TextureHelper.loadTexture(activityContext, R.drawable.small_island);		
		textures[Cons.TID_WOOD] = TextureHelper.loadTexture(activityContext, R.drawable.wood);		
		textures[Cons.TID_STUMP] = TextureHelper.loadTexture(activityContext, R.drawable.stump);		
		textures[Cons.TID_PERSON] = TextureHelper.loadTexture(activityContext, R.drawable.person);		
		textures[Cons.TID_STONE] = TextureHelper.loadTexture(activityContext, R.drawable.stone);		
		textures[Cons.TID_ROCK] = TextureHelper.loadTexture(activityContext, R.drawable.rock);		
		textures[Cons.TID_WELL] = TextureHelper.loadTexture(activityContext, R.drawable.well);		
		textures[Cons.TID_BRICK] = TextureHelper.loadTexture(activityContext, R.drawable.brick);		
		textures[Cons.TID_WATER_BUCKET] = TextureHelper.loadTexture(activityContext, R.drawable.water_bucket);		
		textures[Cons.TID_BRICK_GROUND] = TextureHelper.loadTexture(activityContext, R.drawable.brick_ground);
		textures[Cons.TID_STONE_HUT] = TextureHelper.loadTexture(activityContext, R.drawable.stone_hut);
		textures[Cons.TID_POT_PLANT] = TextureHelper.loadTexture(activityContext, R.drawable.potplant);
		textures[Cons.TID_FISH_JUMP] = TextureHelper.loadTexture(activityContext, R.drawable.fish_jump);
		textures[Cons.TID_MAGIC_SNAKE] = TextureHelper.loadTexture(activityContext, R.drawable.magic_snakebite_sheet);
		textures[Cons.TID_FONT] = TextureHelper.loadTexture(activityContext, R.drawable.font);
		textures[Cons.TID_LILY] = TextureHelper.loadTexture(activityContext, R.drawable.lily);
		textures[Cons.TID_BUSH2] = TextureHelper.loadTexture(activityContext, R.drawable.bush2);
		textures[Cons.TID_TREE3] = TextureHelper.loadTexture(activityContext, R.drawable.tree3);

	}

}
