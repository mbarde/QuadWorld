package de.funpic.mistavista.quadworld;

public class TTypeManager {
	
	private TTextureManager texMan;
	private TType[] types;
	
	public TTypeManager(TTextureManager texMan) {
		this.texMan = texMan;
		
		loadtypes();
	}
	
	private void loadtypes() {
		types = new TType[Cons.typeCount];
		types[Cons.TYP_LILY] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_WATER)),
											new Square(0,0,0, 1, 1, 
											texMan.getTexture(Cons.TID_LILY)), 0, 0,
											null,
											-1,	Cons.TYP_LILY );
		types[Cons.TYP_BUSH] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, 1, 0.5f, 
											texMan.getTexture(Cons.TID_BUSH)), 30, 0,
											null,
											-1,	Cons.TYP_BUSH );
		types[Cons.TYP_BUSH2] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, 1, 0.5f, 
											texMan.getTexture(Cons.TID_BUSH2)), 30, 0,
											null,
											-1,	Cons.TYP_BUSH2 );
		types[Cons.TYP_ROCK] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_STONE)),
											new Square(0,0,0, 1, 0.5f, 
											texMan.getTexture(Cons.TID_ROCK)), 0, 0,
											new int[][] { {Cons.TYP_BRICK,2} },
											Cons.TYP_GRASS,	Cons.TYP_ROCK );
		types[Cons.TYP_BRICK] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_SAND)),
											new Square(0,0,0, 0.5f, 0.2f, 
											texMan.getTexture(Cons.TID_BRICK)), 0, 0,
											new int[][] { {Cons.TYP_BRICK,1} },
											-1,	Cons.TYP_BRICK );
		types[Cons.TYP_BRICK_GROUND] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_BRICK_GROUND)),
											null, 0, 0,
											new int[][] { {Cons.TYP_BRICK_GROUND,1} },
											-1,	Cons.TYP_BRICK_GROUND );
		types[Cons.TYP_BRICK_GROUND].overClass = Cons.CLASS_WAY;
		types[Cons.TYP_WATER_BUCKET] = new TType( null,
											new Square(0,0,0, 0.5f, 0.5f, 
											texMan.getTexture(Cons.TID_WATER_BUCKET)), 0, 0,
											new int[][] { {Cons.TYP_WATER_BUCKET,1} },
											-1,	Cons.TYP_WATER_BUCKET );
		types[Cons.TYP_POT_PLANT] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_BRICK_GROUND)),
											new Square(0,0,0, 0.8f, 0.8f, 
											texMan.getTexture(Cons.TID_POT_PLANT)), 20, 0,
											new int[][] { {Cons.TYP_POT_PLANT,1} },
											-1, Cons.TYP_POT_PLANT );
		types[Cons.TYP_WELL] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_BRICK_GROUND)),
											new Square(0,0,0, 0.8f, 0.8f, 
											texMan.getTexture(Cons.TID_WELL)), 30, 0,
											new int[][] { {Cons.TYP_WATER_BUCKET,1} },
											Cons.TYP_WELL, Cons.TYP_WELL );
		types[Cons.TYP_REED] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_SAND)),
											new Square(0,0,0, 0.5f, 1f, 
											texMan.getTexture(Cons.TID_REED)), 30, 0,
											new int[][] { {Cons.TYP_REED,1} },
											-1, Cons.TYP_REED );
		types[Cons.TYP_STUMP] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, 0.7f, 0.7f, 
											texMan.getTexture(Cons.TID_STUMP)), 30, 0,
											null,
											-1, Cons.TYP_STUMP );
		types[Cons.TYP_WOOD] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, 0.7f, 0.7f, 
											texMan.getTexture(Cons.TID_WOOD)), 30, 0,
											new int[][] { {Cons.TYP_WOOD,1} },
											-1, Cons.TYP_WOOD );
		types[Cons.TYP_TREE] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, 2, 2, 
											texMan.getTexture(Cons.TID_TREE)), 30, 0,
											new int[][] { {Cons.TYP_WOOD,1} },
											Cons.TYP_STUMP, Cons.TYP_TREE );
		types[Cons.TYP_TREE2] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, 2, 2, 
											texMan.getTexture(Cons.TID_TREE2)), 30, 0,
											new int[][] { {Cons.TYP_WOOD,1} },
											Cons.TYP_STUMP, Cons.TYP_TREE2 );
		types[Cons.TYP_TREE3] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, 2, 2, 
											texMan.getTexture(Cons.TID_TREE3)), 30, 0,
											new int[][] { {Cons.TYP_WOOD,1} },
											Cons.TYP_STUMP, Cons.TYP_TREE3 );		
		types[Cons.TYP_HUT] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,-0.3f,0, 1, 1.2f, 
											texMan.getTexture(Cons.TID_HUT)), 35, 0,
											new int[][] { {Cons.TYP_HUT,1} },
											-1, Cons.TYP_HUT );
		types[Cons.TYP_STONE_HUT] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,-0.3f,0, 4, 1.5f, 
											texMan.getTexture(Cons.TID_STONE_HUT)), 25, 0,
											new int[][] { {Cons.TYP_STONE_HUT,1} },
											-1, Cons.TYP_STONE_HUT );
		types[Cons.TYP_WATER_HOLE] = new TType( 	new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_WATER_HOLE)), 0, 0,
											null,
											-1, Cons.TYP_WATER_HOLE );
		types[Cons.TYP_SMALL_ISLAND] = new TType( new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_SMALL_ISLAND)),
											null, 0, 0, 
											null, -1,
											Cons.TYP_SMALL_ISLAND );
		types[Cons.TYP_GRASS] = new TType( new Square(0,0,0, Cons.segSize+0.065f, Cons.segSize+0.065f,
											texMan.getTexture(Cons.TID_GRASS)),
											null, 0, 0, 
											null, -1,
											Cons.TYP_GRASS );
		types[Cons.TYP_STONE] = new TType( new Square(0,0,0, Cons.segSize+0.065f, Cons.segSize+0.065f,
											texMan.getTexture(Cons.TID_STONE)),
											null, 0, 0, 
											null, -1,
											Cons.TYP_STONE );
		types[Cons.TYP_WATER] = new TType( new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_WATER)),
											null, 0, 0, 
											null, -1,
											Cons.TYP_WATER );
		
		Square[] squares;
		squares = new Square[4];
		for (int i = 0; i < 4; i++) {
			float y = 1/4f * i;
			squares[i] = new Square(0,0,0, Cons.segSize, Cons.segSize, 
							texMan.getTexture(Cons.TID_FISH_JUMP),
							0, y, 0, y + 1/4f, 1, y + 1/4f, 1, y); 
		}
		
		types[Cons.TYP_WATER].animation = new TAnimation(squares, 80000000, 0.001f);
		
		types[Cons.TYP_SAND] = new TType( new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_SAND)),
											null, 0, 0, 
											null, -1,
											Cons.TYP_SAND);
		
		int[][] pathList = { {Cons.TYP_PATH_01,-1}, {Cons.TYP_PATH_02,-1},
			{Cons.TYP_PATH_LD,-1}, {Cons.TYP_PATH_RD,-1}, 
			{Cons.TYP_PATH_RU,-1}, {Cons.TYP_PATH_LU,-1} };
		
		types[Cons.TYP_PATH_01] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_PATH_01)), 0, 0,
											pathList, -1,
											Cons.TYP_PATH_01 );
		types[Cons.TYP_PATH_01].overClass = Cons.CLASS_WAY;
		types[Cons.TYP_PATH_02] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_PATH_01)), 0, 90,
											pathList, -1,
											Cons.TYP_PATH_02 );
		types[Cons.TYP_PATH_02].overClass = Cons.CLASS_WAY;
		types[Cons.TYP_PATH_LD] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_PATH_03)), 0, 0,
											pathList, -1,
											Cons.TYP_PATH_LD );
		types[Cons.TYP_PATH_LD].overClass = Cons.CLASS_WAY;
		types[Cons.TYP_PATH_RD] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_PATH_03)), 0, 90,
											pathList, -1,
											Cons.TYP_PATH_RD );
		types[Cons.TYP_PATH_RD].overClass = Cons.CLASS_WAY;
		types[Cons.TYP_PATH_RU] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_PATH_03)), 0, 180,
											pathList, -1,
											Cons.TYP_PATH_RU );
		types[Cons.TYP_PATH_RU].overClass = Cons.CLASS_WAY;
		types[Cons.TYP_PATH_LU] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_GRASS)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_PATH_03)), 0, -90,
											pathList, -1,
											Cons.TYP_PATH_LU );
		types[Cons.TYP_PATH_LU].overClass = Cons.CLASS_WAY;
		types[Cons.TYP_BRIDGE_01] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_WATER)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_BRIDGE_01)), 0, 0,
											new int[][] { {Cons.TYP_WOOD, 1 } }, -1,
											Cons.TYP_BRIDGE_01 );
		types[Cons.TYP_BRIDGE_02] = new TType(new Square(0,0,0, Cons.segSize, Cons.segSize,
											texMan.getTexture(Cons.TID_WATER)),
											new Square(0,0,0, Cons.segSize, Cons.segSize, 
											texMan.getTexture(Cons.TID_BRIDGE_01)), 0, 90,
											new int[][] { {Cons.TYP_WOOD, 1 } }, -1,
											Cons.TYP_BRIDGE_02 );
	}
	
	public TType getType(int id) {
		if (id < 0 || id >= types.length) return null;
		return types[id];
	}
	
	public TType getRandomType() {
		int r = (int)Math.round( Math.random() * (Cons.typeCount-1) );
		return getType(r);
	}
	
	public TType getRandomElementalType() {
		float[] rA = new float[Cons.elementalTypesCount];
		for (int i = 0; i < rA.length; i++)
			rA[i] = (float)Math.random() + Cons.elSpawnProbs[i];
		
		int max = 0;
		for (int i = 1; i < rA.length;i++)
			if (rA[i] > rA[max]) max = i;

		return getType(max);
	}
	
	public TType getNextElementalType(TType type) {
		int tid = (type.id + 1) % Cons.elementalTypesCount;
		return getType(tid);
	}
	
	public TType getOneTypeOfThis(TType[] types) {
		int r = (int)Math.round( Math.random() * (types.length-1) );
		return getType(r);
	}
	
	public boolean isFreeToGo(int id) {
		if ( id == Cons.TYP_BRICK_GROUND 
				|| (id >= Cons.elementalTypesCount+4 && id <= Cons.elementalTypesCount+11) )
			return true;
		else return false;
	}

}
