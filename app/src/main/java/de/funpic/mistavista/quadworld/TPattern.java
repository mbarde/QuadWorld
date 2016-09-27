package de.funpic.mistavista.quadworld;

public class TPattern {
	
	public int width, height;
	public TType[][] types, typesAfter;
	public boolean[][] negMatrix;
	
	public TPattern(int width, int height) {
		this.width = width;
		this.height = height;
		types = new TType[width][height];
		typesAfter = new TType[width][height];
		negMatrix = new boolean[width][height];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				negMatrix[x][y] = false;
	}
	
	public void setAllTypes(TType type) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				types[x][y] = type;
	}
	
	public void setAllTypesAfter(TType type) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				typesAfter[x][y] = type;
	}
	
	public void setReallyAllTypes(TType type) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				types[x][y] = type;
				typesAfter[x][y] = type;
			}
	}
	
	public boolean checkIfEqualTypes(TType type, int x, int y) {
		boolean eq = (type == types[x][y]);
		if (negMatrix[x][y]) eq = !eq;
		
		return eq;
	}

}
