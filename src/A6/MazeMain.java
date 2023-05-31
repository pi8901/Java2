package A6;

public class MazeMain {
  public static void main(String[] args) {
	  
    char[][] maze =
      {{' ','X',' ','X',' ',' '},
       {' ','X',' ',' ',' ','X'},
       {' ',' ','X','X',' ','X'},
       {'X',' ',' ',' ',' ','X'},
       {' ',' ',' ','X',' ','X'},
       {'X','X',' ',' ',' ',' '}};
    
    Maze mymaze = new Maze(maze);
    mymaze.canExit(0,0);
    
    mymaze.printMaze();
    
    System.out.println(mymaze.getSolution());
    
  }
}