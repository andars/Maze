import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;

public class Maze {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int row;
		int column;
		Scanner reader = new Scanner(System.in);
		int quit = 0;
		int side;
		int finish;
		Dimension dim = new Dimension(1400,800);
		
		JFrame f = new JFrame();
		Panel p = new Panel();
		//Graphics g = p.getGraphics();
		//f.setPreferredSize(dim);
		f.setSize(dim);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(p);
		f.setVisible(true);
		p.setVisible(false);
		p.makeBorder();
		
		//p.paintAllComponent(g);
		p.genMaze();
		p.setVisible(true);
		
		
		
		//f.setSize(799, 799);
		//p.draw();
		//p.refresh();
		/**
		while(quit != -1){
			
			System.out.println("KNOCKING DOWN WALLS");
			System.out.print("Row (0-14): ");
			row = reader.nextInt();
			reader.nextLine();
			System.out.print("Column (0-14): ");
			column = reader.nextInt();
			reader.nextLine();
			System.out.print("Side (West is 1, North is 2, East is 3, South is 4): ");
			side = reader.nextInt();
			reader.nextLine();
			p.setWall(row, column, side, false);
			System.out.println("Enter -1 to quit and draw grid or 1 to continue: ");
			quit = reader.nextInt();
		}
		*/
		//p.setWall(3,3, 2,false);
		//p.setWall(1,1, 3, false);
		//p.draw();
		
		}

}

