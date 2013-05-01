import java.awt.Graphics;
import java.awt.Point;


public class Cell {
	boolean East,West,North,South;
	Point a, b, c, d;
	boolean visited;
	int myRow;
	int myColumn;
	int size;
	
	public Cell(){
		East = true;
		West = true;
		North = true;
		South = true;
	}
	
	public Cell(int x, int y, int i){
		size = i;
		setPoints(x, y);
		East = true;
		West = true;
		North = true;
		South = true;
	}
	
	public void setWall(int i, boolean j){
		switch(i){
			case 1: West = j;
				break;
			case 2: North = j;
				break;
			case 3: East = j;
				break;
			case 4: South = j;
				break;
			default:
				break;
		}
	}
	
	public boolean getWall(int i){
		switch(i){
			case 1: return West;
				
			case 2: return North;
				
			case 3: return East;
				
			case 4: return South;
				
			default:
				return true;
		}
	}
	
	/**
	 * a-----b
	 * |     |
	 * |     |
	 * c-----d
	 */
	public void setPoints(int x, int y){
		a = new Point(x, y);
		b = new Point(x + size, y);
		c = new Point(x, y +size);
		d = new Point(x + size, y + size);
	}
	
	public void setSize(int i){
		size = i;
	}
	
	public int getX(){
		return (int) a.getX();
	}
	
	public int getY(){
		return (int) a.getY();
	}
	public void drawCell(Graphics g){
		/**if(West == true && North == true && East == true && South == true){
			int aX = (int) a.getX();
			int aY = (int)a.getY();
			g.drawRect(aX, aY, 10, 10);
		}*/
		if(West == true){
			int aX = (int)a.getX();
			int aY = (int)a.getY();
			int cX = (int)c.getX();
			int cY = (int)c.getY();
			g.drawLine(aX, aY, cX, cY);
		}if(North == true){
			int aX = (int)a.getX();
			int aY = (int)a.getY();
			int bX = (int)b.getX();
			int bY = (int)b.getY();
			g.drawLine(aX, aY, bX, bY);
		}if(East == true){
			int bX = (int)b.getX();
			int bY = (int)b.getY();
			int dX = (int)d.getX();
			int dY = (int)d.getY();
			g.drawLine(bX, bY, dX, dY);
		}if(South == true){
			int cX = (int)c.getX();
			int cY = (int)c.getY();
			int dX = (int)d.getX();
			int dY = (int)d.getY();
			g.drawLine(cX, cY, dX, dY);
		}
			
	}
	public void getInfo(){
		System.out.println("west: " + West + "\nNorth: " + North + "\n East: " + East + "\nSouth:" + South + a);
	}
	
	public void setRow(int r){
		myRow = r;
	}
	
	public void setColumn(int c){
		myColumn = c;
	
	}
	
	public int getRow(){
		return myRow;
	}
	public int getColumn(){
		return myColumn;
	}
	
	public void setVisited(boolean t){
		visited = t;
	}
	public boolean getVisited(){
		return visited;
	}
}
