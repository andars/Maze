import java.awt.Graphics;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;


public class Panel extends JPanel {
	
	
	//Starting Coordinates
	int x = 3;
	int y = 3;
	
	//Maze Size
	int userRows= 168;
	int userColumns = 310;
	
	
	int size = 4;
	int rows = userRows + 2;
	int columns = userColumns + 2;
	Cell[][] cells = new Cell[rows][columns];
	int totalCells = userRows*userColumns;
	int sideChange, rowChange, columnChange;
	Cell currentCell, neighbor;
	int numVisited = 2;
	Random gen = new Random();
	Cell starCell, finCell;
	boolean a, b, c, d;
	Cell tempCell;
	int neighborNum;
	int currRow1;
	int currColumn1;
	int branchNum = 1;
	
	
	public Panel(){
		makeCells();
		//System.out.println("Panel() called");
		
	}

	private void makeCells() {
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cells[i][j] = new Cell(x, y, size);
				cells[i][j].setSize(size);
				cells[i][j].setColumn(j);
				cells[i][j].setRow(i);
				cells[i][j].setSize(size);
				x+=size;
				
				
			}
			x=3;
			y+=size;
		}
		starCell = cells[1][1];
		starCell.setVisited(true);
		finCell = cells[userRows][userColumns];
		finCell.setVisited(true);
		//System.out.println("makeCells() called");
	}
	
	public void makeBorder(){
		for (int i = 0; i<rows; i+= (rows -1)){
			for(int j = 0; j<columns;j++ ){
				cells[i][j].setVisited(true);
				//cells[i][j].drawCell(getGraphics());
				//cells[j][i].setVisited(true);
				//cells[j][i].drawCell(getGraphics());
			}
		}
		
		for (int i = 0; i<columns; i += (columns - 1)){
			for (int j = 0; j< rows; j++){
				cells[j][i].setVisited(true);
			}
		}
	}

/**
	public void draw() {
		repaint();
		
	}


	/*/
	public void paintComponent(Graphics g){
		int finX, finY, startX, startY;
		//System.out.println("paint called");
		for( int i = 0; i< rows; i++){
			for(int j = 0; j<columns; j++){
				cells[i][j].drawCell(g);
			}
		}
		finX= finCell.getX();
		finY = finCell.getY();
		g.drawRect(finX  , finY , size- 2, size -2);
		g.fillRect(finX, finY, size- 2, size -2);
		startX= starCell.getX();
		startY = starCell.getY();
		g.drawRect(startX , startY , size -2, size -2);
		g.fillRect(startX  , startY , size -2, size -2);
	}//end paintComponent
 

	public void setWall(int row, int column, int side, boolean wall){
		if (side ==1 && column > 0){
			sideChange = 2;
			columnChange = -1;
		}
		if (side==2 && row > 0){
			sideChange = 2;
			rowChange = -1;
		}
		if (side == 3 && column < columns){
			sideChange = -2;
			columnChange = 1;
		}
		if(side == 4 && row < rows){
			sideChange = -2;
			rowChange = 1;
		}
		cells[row][column].setWall(side, wall);
		cells[row + rowChange][column + columnChange].setWall(side +sideChange, wall );
		
		//reset necessary variables
		sideChange = 0;
		rowChange = 0;
		columnChange = 0;
	}
	
	public void genMaze(){
		Stack<Cell> stack = new Stack<Cell>();
		System.out.println(stack);
		currentCell = starCell;
		setWall(userRows,userColumns, 1, false);
		
		
		//System.out.println("genMaze called");
		repaint(1);
		
		
		while( numVisited < totalCells){
			//System.out.println("while called");
			//System.out.println(stack);
			//currRow1=currentCell.getRow();
			//currColumn1 = currentCell.getColumn();\
			
			
			/**
			try{
				Thread.sleep(0);
				
			}catch(InterruptedException e){
				
			}
			*/
			
			if(branchNum >= 80){
				for(int n = 0; n<70; n++){
					System.out.println("SIZE: " + stack.size());
					currentCell = stack.pop();
					stack.push(currentCell);
					
				}
				branchNum=0;
			}
			
			//cells[currRow1][currColumn1].drawCell(getGraphics());
			if(hasNeighbor(currentCell) && currentCell != finCell){
				//System.out.println("Panel() called");
				tempCell = currentCell;
				currentCell = getNeighbor(tempCell);
				//tempCell.drawCell(getGraphics());
				stack.push(currentCell);
				//System.out.println(currentCell);
				currentCell.setVisited(true);
				//System.out.println("getNeighbor called");
				//update(getGraphics());
				//repaint();
				//tempCell.drawCell(getGraphics());
				//currentCell.drawCell(getGraphics());
				numVisited++;
				//System.out.println("YAY called");
				System.out.println(currentCell.getRow());
				System.out.println(currentCell.getColumn() );
				System.out.println("NUMVISITED: "+ numVisited);
				branchNum++;
			}
			else{
				System.out.println("SIZE: " + stack.size());
				currentCell = stack.pop();
				branchNum=0;
				
			}
		}
		
		System.out.println(finCell.getWall(1));
		repaint();
	}
	
	
	/**
	 *    _
	 *  _|1|_
	 * |0|_|2|
	 *   |3|
	 *    
	 */
	
	public Cell getNeighbor(Cell g){
		neighborNum++;
		int currRow = g.getRow();
		 int currColumn = g.getColumn();
		//System.out.println("NEIGHBORS GOT" + neighborNum);
		int num= (gen.nextInt(4))+1;
		 a = cells[currRow - 1][currColumn].getVisited();
		 b = cells[currRow + 1][currColumn].getVisited();
		 c = cells[currRow][currColumn - 1].getVisited();
		 d = cells[currRow][currColumn + 1].getVisited();
		 
		// System.out.println("Rand: " + num);
		 switch(num){
		 	case 1: 
		 		if(!a){ 
		 			setWall(currRow, currColumn, 2, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow - 1][currColumn];
		 		}
		 		if (!b){
		 			setWall(currRow,currColumn, 4, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow + 1][currColumn];
		 		}
		 		if(!c){
		 			setWall(currRow, currColumn, 1, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn - 1];
		 		}
		 		if(!d){
		 			setWall(currRow, currColumn , 3, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn + 1];
		 		}
		 		
		 	case 2:	
		 		
		 		if (!b){
		 			setWall(currRow, currColumn, 4, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow + 1][currColumn];
		 		}
		 		if(!c){
		 			setWall(currRow, currColumn, 1, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn - 1];
		 		}
		 		if(!d){
		 			setWall(currRow, currColumn , 3, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn + 1];
		 		}
		 		if(!a){ 
		 			setWall(currRow, currColumn, 2, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow - 1][currColumn];
		 		}
		 		
		 	case 3:
		 		
		 		if(!c){
		 			setWall(currRow, currColumn, 1, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn - 1];
		 		}
		 		if(!d){
		 			setWall(currRow, currColumn , 3, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn + 1];
		 		}
		 		if(!a){ 
		 			setWall(currRow, currColumn, 2, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow - 1][currColumn];
		 		}
		 		if (!b){
		 			setWall(currRow, currColumn, 4, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow + 1][currColumn];
		 		}
		 		
		 	case 4:
		 		
		 		if(!d){
		 			setWall(currRow, currColumn, 3, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn + 1];
		 		}
		 		if(!a){ 
		 			setWall(currRow, currColumn, 2, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow - 1][currColumn];
		 		}
		 		if (!b){
		 			setWall(currRow, currColumn, 4, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow + 1][currColumn];
		 		}
		 		if(!c){
		 			setWall(currRow, currColumn, 1, false);
		 			//cells [currRow][currColumn].drawCell(getGraphics());
		 			return cells[currRow][currColumn - 1];
		 		}
		 		
		 	default:
		 		return g;
		 }
	}//end getNeighbor
	
	public boolean hasNeighbor(Cell y){
		int currRow = y.getRow();
		int currColumn = y.getColumn();
		int limit;
		
	
			
			 a = cells[currRow - 1][currColumn].getVisited();

			
			b = cells[currRow + 1][currColumn].getVisited();
		
			c = cells[currRow][currColumn - 1].getVisited();

	
			
			d = cells[currRow][currColumn + 1].getVisited();
		
		//System.out.println("hasNeighbor limit: " + limit);
		
		/**	
		if( (limit != 1)){ a = cells[currRow - 1][currColumn].getVisited();}
		if( (limit != 2)){ b = cells[currRow + 1][currColumn].getVisited();}
		if( (limit != 3)){ c = cells[currRow][currColumn - 1].getVisited();}
		if( (limit != 4)){ d = cells[currRow][currColumn + 1].getVisited();}
		*/
			
		if (!a || !b || !c || !d){
			return true;
		}else{
			return false;
		}
	}
	/**
	public void refresh(){
		draw();
	}
	*/
}
