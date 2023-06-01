package A6;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GraphicMaze extends JFrame
{
	private static final long serialVersionUID = 1L;

	Container c;
	Graphic g;
	int state;

	public void drawBoard(Maze maze)
	{
		int x = 0;
		int y = 0;
		for (int i = 0; i < maze.maze.length; i++)
		{
			for (int j = 0; j < maze.maze[1].length; j++)
			{
				if (maze.maze[i][j] == ' ')
				{
					g.setColor(Color.WHITE);
					g.fillRect(50 * i, 50 * j, 50, 50);

					g.setColor(Color.BLACK);
					g.drawRect(50 * i, 50 * j, 50, 50);
				
				} else
				{
					g.setColor(Color.BLACK);
					g.fillRect(50 * i, 50 * j, 50, 50);
				}
			}
		}
		// g.fillRect(100, 0, 100, 100);

		JButton b1 = new JButton("zurück");
		b1.setBounds(50, 310, 75, 30);
		g.add(b1);

		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//zurück
				
				Point p = maze.points.get(state);
				g.setColor(Color.blue);
				g.fillOval(50 * p.x, 50 * p.y, 50, 50);
				System.out.println(p.x + " " + p.y);
				state ++;
			}
		});

		JButton b2 = new JButton("vor");
		b2.setBounds(175, 310, 75, 30);
		g.add(b2);
		
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//zurück
				System.out.println("Hello");
			}
		});

		g.redraw();
	}

	public GraphicMaze()
	{
		c = getContentPane();
		g = new Graphic(300, 350);
		c.add(g);
	}

	public static void main(String args[])
	{
		char[][] maze =
		{
				{ ' ', 'X', ' ', 'X', ' ', ' ' },
				{ ' ', 'X', ' ', ' ', ' ', 'X' },
				{ ' ', ' ', 'X', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', 'X' },
				{ ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', 'X', ' ', ' ', ' ', ' ' } };

		Maze mymaze = new Maze(maze);
		mymaze.canExit(0, 0);
		GraphicMaze fenster = new GraphicMaze();
		fenster.setTitle("Graphic");
		fenster.pack();
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.drawBoard(mymaze);
	}
}
