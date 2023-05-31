package A6;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Maze {

	protected char[][] maze;
	
	public Maze(char[][] maze) {
		this.maze = maze;
	}

	public boolean canExit(int i, int j) {
		
		int n = maze.length;
		
		if (i<0 || j<0 || i >=n || j>=n)
			return false;  // ausserhalb
		
		if (maze[i][j] != ' ')
			return false; // Mauer oder schon geprueft
		
		maze[i][j] = '.';
		
		if (i==n-1 && j==n-1 // Ziel
			  	|| canExit(i+1,j) /* unten */|| canExit(i,j+1) /* rechts */
			  	|| canExit(i-1,j) /* oben */ || canExit(i,j-1) /* links */
			) {
			System.out.println("("+j+","+i+")");
			maze[i][j] = '+';
			return true;
		}
		
		return false;
	}

	public void printMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++)
				System.out.print(maze[i][j] + " ");
			System.out.println();
		}
	}
	
	public List<Point> getSolution(){
		List<Point> koordinates = new ArrayList<>();
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze.length; j++) {
				if(maze[i][j] == '+') {
					koordinates.add(new Point(i,j));
				}
			}
		}
		return koordinates;
	}

	
}
