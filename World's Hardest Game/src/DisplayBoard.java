import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DisplayBoard extends JPanel {
	//private ArrayList<AI> ais;
	private Population pop;
	private Goal goal;
	private Board board;
	private ArrayList<Wall> walls;
	private ArrayList<Enemy> enemies;
	
	public DisplayBoard(Population pop, Goal goal, Board board, ArrayList<Wall> walls, ArrayList<Enemy> enemies) {
		this.pop = pop;
		this.goal = goal;
		this.board = board;
		this.walls = walls;
		this.enemies = enemies;
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(255,0,0));
		for (Rectangle r : board.getRects()) {
			g.drawRect(r.getX1(), r.getY1(), r.getX2() - r.getX1(), r.getY2() - r.getY1());
			//g.fillRect(r.getX1(), r.getY1(), r.getX2() - r.getX1(), r.getY2() - r.getY1());
		}
		
		g.setColor(new Color(255,0,0));
		for (int i = 0; i < pop.getPopulation().size(); i++) {
			AI current = pop.getPopulation().get(i);
			g.drawOval(current.getxCoord(), current.getyCoord(), current.getWidth(), current.getHeight());
			g.fillOval(current.getxCoord(), current.getyCoord(), current.getWidth(), current.getHeight());
		}
		g.setColor(new Color(0,255,0));
		g.drawRect(goal.getxCoord(), goal.getyCoord(), goal.getWidth(), goal.getHeight());
		g.fillRect(goal.getxCoord(), goal.getyCoord(), goal.getWidth(), goal.getHeight());
		
		g.setColor(new Color(0,0,0));
		for (int i = 0; i < walls.size(); i++) {
			g.drawRect(walls.get(i).getxCoord(), walls.get(i).getyCoord(), walls.get(i).getWidth(), walls.get(i).getHeight());
			g.fillRect(walls.get(i).getxCoord(), walls.get(i).getyCoord(), walls.get(i).getWidth(), walls.get(i).getHeight());
		}
		
		g.setColor(new Color(50,0,50));
		for (int i = 0; i < enemies.size(); i++) {
			g.drawOval(enemies.get(i).getxCoord(), enemies.get(i).getyCoord(), enemies.get(i).getWidth(), enemies.get(i).getHeight());
			g.fillOval(enemies.get(i).getxCoord(), enemies.get(i).getyCoord(), enemies.get(i).getWidth(), enemies.get(i).getHeight());
		}
	}
}
