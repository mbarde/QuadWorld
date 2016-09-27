package de.funpic.mistavista.quadworld;

public class TPatternManager {
	
	private TPattern[] patterns;
	private TTypeManager typeMan;
	
	public TPatternManager(TTypeManager typeMan) {
		this.typeMan = typeMan;
		
		initPatterns();
	}
	
	private void initPatterns() {

		patterns = new TPattern[29];
		
		int i = 0;
		
		// reed
		patterns[i] = new TPattern(2,1);
		patterns[i].types[0][0] = typeMan.getType( Cons.TYP_SAND );
		patterns[i].types[1][0] = typeMan.getType( Cons.TYP_WATER );
		patterns[i].typesAfter[0][0] = typeMan.getType( Cons.TYP_REED );
		patterns[i].typesAfter[1][0] = typeMan.getType( Cons.TYP_LILY );
		
		i++;
		patterns[i] = new TPattern(2,1);
		patterns[i].types[1][0] = typeMan.getType( Cons.TYP_SAND );
		patterns[i].types[0][0] = typeMan.getType( Cons.TYP_WATER );
		patterns[i].typesAfter[1][0] = typeMan.getType( Cons.TYP_REED );
		patterns[i].typesAfter[0][0] = typeMan.getType( Cons.TYP_LILY );
		
		i++;
		patterns[i] = new TPattern(1,2);
		patterns[i].types[0][0] = typeMan.getType( Cons.TYP_SAND );
		patterns[i].types[0][1] = typeMan.getType( Cons.TYP_WATER );
		patterns[i].typesAfter[0][0] = typeMan.getType( Cons.TYP_REED );
		patterns[i].typesAfter[0][1] = typeMan.getType( Cons.TYP_LILY );
		
		i++;
		patterns[i] = new TPattern(1,2);
		patterns[i].types[0][1] = typeMan.getType( Cons.TYP_SAND );
		patterns[i].types[0][0] = typeMan.getType( Cons.TYP_WATER );
		patterns[i].typesAfter[0][1] = typeMan.getType( Cons.TYP_REED );
		patterns[i].typesAfter[0][0] = typeMan.getType( Cons.TYP_LILY );
		
		// pot plant
		i++;
		patterns[i] = new TPattern(3,3);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BRICK ) );
		patterns[i].types[0][0] = null;
		patterns[i].types[2][0] = null;
		patterns[i].types[0][2] = null;
		patterns[i].types[2][2] = null;
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_BUSH ); 
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].typesAfter[1][1] = typeMan.getType( Cons.TYP_POT_PLANT);				
		
		// stone hut
		i++;
		patterns[i] = new TPattern(3,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BRICK) );
		patterns[i].types[1][0] = typeMan.getType( Cons.TYP_WOOD);
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_WOOD);
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].typesAfter[1][0] = typeMan.getType( Cons.TYP_STONE_HUT);
		
		// brick ground
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BRICK) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_BRICK_GROUND ) );
		
		// pond
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_WATER_BUCKET) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_WATER ) );
		
		// well
		i++;
		patterns[i] = new TPattern(3,3);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BRICK ) );
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_WATER ); 
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].typesAfter[1][1] = typeMan.getType( Cons.TYP_WELL);
		
		// tree
		i++;
		patterns[i] = new TPattern(3,3);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BUSH ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].typesAfter[1][1] = typeMan.getType( Cons.TYP_TREE);
		
		i++;
		patterns[i] = new TPattern(3,3);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BUSH2 ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].typesAfter[1][1] = typeMan.getType( Cons.TYP_TREE);
		
		// create other tree
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BUSH ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].typesAfter[0][0] = typeMan.getType( Cons.TYP_TREE2 );
		
		// create other other tree
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BUSH2 ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].typesAfter[0][0] = typeMan.getType( Cons.TYP_TREE3 );
		
		// rock
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_STONE ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_ROCK ) );
		
		// hut
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].setAllTypes( typeMan.getType(Cons.TYP_WOOD) );
		patterns[i].setAllTypesAfter( typeMan.getType(Cons.TYP_GRASS) );
		patterns[i].typesAfter[0][0] = typeMan.getType(Cons.TYP_HUT);
		
		// water hole
		i++;
		patterns[i] = new TPattern(3,3);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_BUSH ) );
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_WATER );
		patterns[i].setAllTypesAfter( null );
		patterns[i].typesAfter[1][1] = typeMan.getType( Cons.TYP_WATER_HOLE );
		
		// water island small
		i++;
		patterns[i] = new TPattern(3,3);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_WATER ) );
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_SAND );
		patterns[i].setAllTypesAfter( null );
		patterns[i].typesAfter[1][1] = typeMan.getType( Cons.TYP_REED );
		
		// create bushes
		i++;
		patterns[i] = new TPattern(2,1);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_BUSH ) );
		
		i++;
		patterns[i] = new TPattern(1,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_GRASS ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_BUSH2 ) );

		// create path
		i++;
		patterns[i] = new TPattern(1,6);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_SAND) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_PATH_01 ) );

		i++;
		patterns[i] = new TPattern(6,1);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_SAND) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_PATH_02 ) );
		
		// path bend left down
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].types[0][0] = null;
		patterns[i].types[0][1] = typeMan.getType( Cons.TYP_PATH_02 );
		patterns[i].types[1][0] = typeMan.getType( Cons.TYP_PATH_01 );
		patterns[i].setAllTypesAfter( null );
		patterns[i].typesAfter[1][1] = typeMan.getType( Cons.TYP_PATH_LD);
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_PATH_LD);
		patterns[i].negMatrix[1][1] = true;
		
		// path bend right down
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].types[1][0] = null;
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_PATH_02 );
		patterns[i].types[0][0] = typeMan.getType( Cons.TYP_PATH_01 );
		patterns[i].setAllTypesAfter( null );
		patterns[i].typesAfter[0][1] = typeMan.getType( Cons.TYP_PATH_RD);
		patterns[i].types[0][1] = typeMan.getType( Cons.TYP_PATH_RD);
		patterns[i].negMatrix[0][1] = true;
		
		// path bend right up	
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].types[1][1] = null;
		patterns[i].types[1][0] = typeMan.getType( Cons.TYP_PATH_02 );
		patterns[i].types[0][1] = typeMan.getType( Cons.TYP_PATH_01 );
		patterns[i].setAllTypesAfter( null );
		patterns[i].typesAfter[0][0] = typeMan.getType( Cons.TYP_PATH_RU);
		patterns[i].types[0][0] = typeMan.getType( Cons.TYP_PATH_RU);
		patterns[i].negMatrix[0][0] = true;
		
		// path bend left up
		i++;
		patterns[i] = new TPattern(2,2);
		patterns[i].types[0][1] = null;
		patterns[i].types[0][0] = typeMan.getType( Cons.TYP_PATH_02 );
		patterns[i].types[1][1] = typeMan.getType( Cons.TYP_PATH_01 );
		patterns[i].setAllTypesAfter( null );
		patterns[i].typesAfter[1][0] = typeMan.getType( Cons.TYP_PATH_LU);
		patterns[i].types[1][0] = typeMan.getType( Cons.TYP_PATH_LU);
		patterns[i].negMatrix[1][0] = true;
		
		
		// turn path in right directions
		i++;
		patterns[i] = new TPattern(2,1);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_PATH_01 ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_PATH_02 ) );
		
		i++;
		patterns[i] = new TPattern(1,2);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_PATH_02 ) );
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_PATH_01 ) );
		
		// create bridge
		i++;
		patterns[i] = new TPattern(3,1);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_WATER) );
		patterns[i].types[1][0] = typeMan.getType( Cons.TYP_WOOD);
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_WATER ) );		
		patterns[i].typesAfter[1][0] = typeMan.getType( Cons.TYP_BRIDGE_01);
		
		i++;
		patterns[i] = new TPattern(1,3);
		patterns[i].setAllTypes( typeMan.getType( Cons.TYP_WATER) );
		patterns[i].types[0][1] = typeMan.getType( Cons.TYP_WOOD);
		patterns[i].setAllTypesAfter( typeMan.getType( Cons.TYP_WATER ) );		
		patterns[i].typesAfter[0][1] = typeMan.getType( Cons.TYP_BRIDGE_02);
		
	}
	
	public TPattern getPattern(int id) {
		if (id < 0 || id >= patterns.length) return null;
		return patterns[id];
	}
	
	public int getPatternCount() { return patterns.length; }

}
