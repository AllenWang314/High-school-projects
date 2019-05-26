import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
	private Dinosaur dino;
	private Population dinos;
	private int selectedDino;
	private ArrayList<Wall> walls;
	private ArrayList<Bird> birds;
	private BufferedImage wall;
	private BufferedImage bird;
	
	public Board(Dinosaur dino, Population dinos, int selectedDino, ArrayList<Wall> walls, ArrayList<Bird> birds) {
		this.dino = dino;
		this.dinos = dinos;
		this.selectedDino = selectedDino;
		this.walls = walls;
		this.birds = birds;
		try {
			this.wall = ImageIO.read(new File("Wall.png"));
			this.bird = ImageIO.read(new File("Bird.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getSelectedDino() {
		return selectedDino;
	}
	
	public void setSelectedDino(int d) {
		selectedDino = d % dinos.getMaxPop();
	}
	
	public void paintComponent(Graphics g) {
		//Graphics2D g2 = (Graphics2D) g;
		// Draw Dino
		if (dino.isVisible())
			g.setColor(new Color(0, 255, 0));
			g.drawRect(dino.getxCoord(), dino.getyCoord(), dino.getWidth(), dino.getHeight());
			g.fillRect(dino.getxCoord(), dino.getyCoord(), dino.getWidth(), dino.getHeight());
			g.setColor(new Color(0, 0, 0));
		// Draw Dinos
		for (int i = 0; i < dinos.getPopulation().size(); i++) {
			Dinosaur currentDino = dinos.getPopulation().get(i).getDino();
			if (currentDino.isVisible())
				g.drawRect(currentDino.getxCoord(), currentDino.getyCoord(), currentDino.getWidth(), currentDino.getHeight());
		}
		
		System.out.println(dinos.getPopulation().get(selectedDino).getBirdWeight());
		if (dinos.getPopulation().get(selectedDino).getDino().isVisible()) {
			g.setColor(new Color(255, 0, 0));
			System.out.println("Highlighted selected dino red");
			g.fillRect(dinos.getPopulation().get(selectedDino).getDino().getxCoord(),dinos.getPopulation().get(selectedDino).getDino().getyCoord(),dinos.getPopulation().get(selectedDino).getDino().getWidth(),dinos.getPopulation().get(selectedDino).getDino().getHeight());
			g.setColor(new Color(0, 0, 0));
		}
		
		// Draw Walls
		for (int i = 0; i < walls.size(); i++) {
			if (walls.get(i).isVisible())
				g.drawRect(walls.get(i).getxCoord(), walls.get(i).getyCoord(), walls.get(i).getWidth(), walls.get(i).getHeight());
				//g.drawImage(wall, walls.get(i).getxCoord(), walls.get(i).getyCoord(), null);
		}
		
		// Draw Birds
		for (int i = 0; i < birds.size(); i++) {
			if (birds.get(i).isVisible())
				g.drawRect(birds.get(i).getxCoord(), birds.get(i).getyCoord(), birds.get(i).getWidth(), birds.get(i).getHeight());
				//g.drawImage(bird, birds.get(i).getxCoord(), birds.get(i).getyCoord(), null);
		}
	}
}



/**
 * import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
	private Dinosaur dino;
	private Population dinos;
	private ArrayList<Wall> walls;
	private ArrayList<Bird> birds;
	private BufferedImage dinoStanding;
	private BufferedImage dinoDucking;
	private BufferedImage wall;
	private BufferedImage bird;
	
	public Board(Dinosaur dino, Population dinos, ArrayList<Wall> walls, ArrayList<Bird> birds) {

		this.dino = dino;
		this.dinos = dinos;
		this.walls = walls;
		this.birds = birds;
		try {
			this.dinoStanding = ImageIO.read(new File("Dino_Standing.png"));
			this.dinoDucking = ImageIO.read(new File("Dino_Ducking.png"));
			this.wall = ImageIO.read(new File("wall.png"));
			this.bird = ImageIO.read(new File("bird.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public void paintComponent(Graphics g) {
		setBackground(Color.black);

		//Graphics2D g2 = (Graphics2D) g;
		
		// Draw Dino
		if (dino.isVisible()) {
			if (dino.getWidth() < 40) {
				//g.drawRect(dino.getxCoord(), dino.getyCoord(), dino.getWidth(), dino.getHeight());
				g.drawImage(this.dinoStanding, dino.getxCoord(), dino.getyCoord(), null);
			}
			else {
				g.drawImage(this.dinoDucking, dino.getxCoord(), dino.getyCoord(), null);
			}
		}	
		// Draw Dinos
		for (int i = 0; i < dinos.getPopulation().size(); i++) {
			Dinosaur currentDino = dinos.getPopulation().get(i).getDino();
			if (currentDino.isVisible())
				g.drawRect(currentDino.getxCoord(), currentDino.getyCoord(), currentDino.getWidth(), currentDino.getHeight());
			g.drawImage(this.dinoStanding, currentDino.getxCoord(), currentDino.getyCoord(), null);

		}
		
		// Draw Walls
		for (int i = 0; i < walls.size(); i++) {
			if (walls.get(i).isVisible())
				//g.drawRect(walls.get(i).getxCoord(), walls.get(i).getyCoord(), walls.get(i).getWidth(), walls.get(i).getHeight());
				g.drawImage(wall, walls.get(i).getxCoord(), walls.get(i).getyCoord(), null);
		}
		
		// Draw Birds
		for (int i = 0; i < birds.size(); i++) {
			if (birds.get(i).isVisible())
				//g.drawRect(birds.get(i).getxCoord(), birds.get(i).getyCoord(), birds.get(i).getWidth(), birds.get(i).getHeight());
				g.drawImage(bird, birds.get(i).getxCoord(), birds.get(i).getyCoord(), null);
		}
	}
}
 */
