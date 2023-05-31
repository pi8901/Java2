package A6;

import javax.swing.*;

import Test.Graphic;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Iterator;

public class GraphicMaze extends JFrame {

	private static final long serialVersionUID = 1L;
	
	Container container;
	Graphic graphic;
	
	public GraphicMaze(){
		container = getContentPane();
	    graphic = new Graphic(300, 300);
	    container.add(graphic);
	}
	
	public void draw(char[][] maze){
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (maze[i][j] == ' ') {
					int x = i;
					int y = j;
					graphic.drawRect(i, j, i, j);
					graphic.setColor(Color.BLACK);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		 char[][] maze =
		        {{' ','X',' ','X',' ',' '},
		         {' ','X',' ',' ',' ','X'},
		         {' ',' ','X','X',' ','X'},
		         {'X',' ',' ',' ',' ','X'},
		         {' ',' ',' ','X',' ','X'},
		         {'X','X',' ',' ',' ',' '}};
		
		
		GraphicMaze fenster = new GraphicMaze();
		fenster.setTitle("GraphicMaze");
	    fenster.setVisible(true);
	    fenster.pack();
	    fenster.setLayout(new GridLayout(maze.length, maze[0].length));
	    fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    
	    
	    fenster.draw(maze);
	}
}