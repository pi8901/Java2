package Test;

import java.awt.Color;
import javax.swing.JFrame;

import A6.Graphic;

public class GridLayoutExample {
    private static char[][] maze = {
        {' ','X',' ','X',' ',' '},
        {' ','X',' ',' ',' ','X'},
        {' ',' ','X','X',' ','X'},
        {'X',' ',' ',' ',' ','X'},
        {' ',' ',' ','X',' ','X'},
        {'X','X',' ',' ',' ',' '}
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze GridLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Graphic graphic = new Graphic(300, 300); // Größe des Panels anpassen
        frame.add(graphic);

        int gridSize = maze.length;
        int cellSize = 50; // Größe der Zellen anpassen
        createMaze(gridSize, cellSize, graphic);

        frame.pack();
        frame.setVisible(true);
    }

    private static void createMaze(int gridSize, int cellSize, Graphic graphic) {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                // Zeichne leeres Rechteck
                graphic.drawRect(x, y, cellSize, cellSize);

                // Überprüfe, ob das Array-Element ein 'X' ist, und fülle das Rechteck entsprechend schwarz
                if (maze[row][col] == 'X') {
                    graphic.setColor(Color.BLACK);
                    graphic.fillRect(x, y, cellSize, cellSize);
                }
            }
        }
    }
}
