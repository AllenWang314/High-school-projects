import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class AIGame extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Board board;
	private Population dinos;
	private int deadDinos;
	private Dinosaur dino;
	private ArrayList<Wall> walls;
	private ArrayList<Bird> birds;
	private int generateDelay = 40;
	private int speed = 4;
	private int generation = 1;
	
	private int prevScore;
	private int score;
	private int distanceToNextWall;
	private int distanceToNextBird;
	private JTextField txtDinosAlive;
	private JTextField txtGeneration;
	private JTextField txtSampleDinoWeights;
	private JTextField txtSpeed;
	private JTextField txtDistance;
	private JTextField txtPrevDistance;
	
	public static void main(String[] args) {
		// run stuff
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame game = new AIGame();
					game.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AIGame() {
		setTitle("AI Dinosaur Game");
		int screenWidth = 600;
		int screenHeight = 400;
		int[] scoreMilestones = {250, 500, 750, 1000, 1250, 1500, 1750, 2000, 2250};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, screenWidth, screenHeight);
		setResizable(false);
		addKeyListener(this);
		setFocusable(true);
		this.requestFocus();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		//dino = new Dinosaur(20, screenHeight-130, 30, 55, 100, 20);
		dino = new Dinosaur();
		dinos = new Population(0.2, 100);
		walls = new ArrayList<Wall>();
		birds = new ArrayList<Bird>();
		int selectedDino = 0;
		
		board = new Board(dino, dinos, selectedDino, walls, birds);
		contentPane.add(board, BorderLayout.CENTER);
		
		txtGeneration = new JTextField();
		txtGeneration.setText("Generation: " + this.generation);
		board.add(txtGeneration);
		txtGeneration.setColumns(10);
		
		txtDinosAlive = new JTextField();
		txtDinosAlive.setText("Dinos Alive: " + (this.dinos.getMaxPop() - this.deadDinos));
		board.add(txtDinosAlive);
		txtDinosAlive.setColumns(10);
		
		txtSpeed = new JTextField();
		txtSpeed.setText("Speed: " + this.speed);
		board.add(txtSpeed);
		txtSpeed.setColumns(10);
		
		txtPrevDistance = new JTextField();
		txtPrevDistance.setText("Previous Best: " + this.prevScore);
		board.add(txtPrevDistance);
		txtPrevDistance.setColumns(10);
		
		txtDistance = new JTextField();
		txtDistance.setText("Distance: " + this.score);
		board.add(txtDistance);
		txtDistance.setColumns(10);
		
		txtSampleDinoWeights = new JTextField();
		txtSampleDinoWeights.setText("Selected Dino Weights: " + (double)Math.round((dinos.getPopulation().get(board.getSelectedDino()).getWallWeight()*100))/100 + ", "+ (double) Math.round(dinos.getPopulation().get(board.getSelectedDino()).getBirdWeight()*100)/100);
		board.add(txtSampleDinoWeights);
		txtSampleDinoWeights.setColumns(30);
		
		Timer timer = new Timer(20, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtSampleDinoWeights.setText("Selected Dino Weights: " + (double)Math.round((dinos.getPopulation().get(board.getSelectedDino()).getWallWeight()*100))/100 + ", "+ (double) Math.round(dinos.getPopulation().get(board.getSelectedDino()).getBirdWeight()*100)/100);
				txtSpeed.setText("Speed: " + speed);
				txtDinosAlive.setText("Dinos Alive: " + (dinos.getMaxPop() - deadDinos));
				txtGeneration.setText("Generation: " + generation);
				txtDistance.setText("Distance: " + (score / 10) * 10);

				
				repaint();
				
				// Move birds and walls
				distanceToNextWall = screenWidth;
				for (int i = 0; i < walls.size(); i++) {
					
					walls.get(i).setxCoord(walls.get(i).getxCoord()-((int)(1.5*speed)));
					
					if (distanceToNextWall == screenWidth && walls.get(i).getxCoord()-dino.getxCoord() > 0) {
						distanceToNextWall = walls.get(i).getxCoord()-dino.getxCoord();
					}
					
					if (walls.get(i).isInBounds(dino.getxCoord(), dino.getyCoord(), dino.getWidth(), dino.getHeight())) {
						gameOver();
						//((Timer)arg0.getSource()).stop();
					}
					
 					if (!walls.get(i).isInBounds(0, 0, screenWidth, screenHeight)) {
 						//System.out.println("Wall removed");
 						walls.remove(i);
 						i--;
 					}
				}
				distanceToNextBird = screenWidth;
				for (int i = 0; i < birds.size(); i++) {
					
					birds.get(i).setxCoord(birds.get(i).getxCoord()-((int)(1.5*speed)));
					
					if (distanceToNextBird == screenWidth && birds.get(i).getxCoord()+birds.get(i).getWidth()-dino.getxCoord() > 0) {
						distanceToNextBird = birds.get(i).getxCoord()+birds.get(i).getWidth()-dino.getxCoord();
					}
					
					if (birds.get(i).isInBounds(dino.getxCoord(), dino.getyCoord(), dino.getWidth(), dino.getHeight())) {
						gameOver();
						//((Timer)arg0.getSource()).stop();
					}
					
 					if (!birds.get(i).isInBounds(0, 0, screenWidth, screenHeight)) {
 						//System.out.println("Bird removed");
 						birds.remove(i);
 						i--;
 					}
				}
				

				// Move Dinosaur up and down (jumping)
				if (dino.getJumpVelocity() > 0 ||!dino.isTouchingGround()) {
					int duckingFactor = 0;
					if (dino.isDucking()) {
						duckingFactor = 7;
					}
					dino.setyCoord(dino.getyCoord() - dino.getJumpVelocity()/(5) + duckingFactor);
					dino.setJumpVelocity(dino.getJumpVelocity()-(4+(speed)));
					
					// So dino doesn't go beneath the ground
					if (dino.getyCoord() > (dino.getGround() - dino.getHeight())) {
						dino.setyCoord(dino.getGround() - dino.getHeight());
					}
				}
				
				// ------------------------------------------------------------------------------------------
				
				for (int j = 0; j < dinos.getPopulation().size(); j++) {
					
					int d = screenWidth;
					Dinosaur currentDino = dinos.getPopulation().get(j).getDino();
					
					if (currentDino.isVisible()) {
						for (int i = 0; i < walls.size(); i++) {
							
							
							if (d == screenWidth && walls.get(i).getxCoord()-currentDino.getxCoord() > 0) {
								d = walls.get(i).getxCoord()-currentDino.getxCoord();
							}
							
							if (walls.get(i).isInBounds(currentDino.getxCoord(), currentDino.getyCoord(), currentDino.getWidth(), currentDino.getHeight())) {
								currentDino.setVisibility(false);
								deadDinos++;
								//System.out.println("dead dino!"+deadDinos);
								//gameOver();
								//((Timer)arg0.getSource()).stop();
							}
						}
						dinos.getPopulation().get(j).setDistToNextWall(d);
						
						int d2 = screenWidth;
						for (int i = 0; i < birds.size(); i++) {
							
							
							if (d2 == screenWidth && birds.get(i).getxCoord()+birds.get(i).getWidth()-currentDino.getxCoord() > 0) {
								d2 = birds.get(i).getxCoord()+birds.get(i).getWidth()-currentDino.getxCoord();
							}
							
							
							if (birds.get(i).isInBounds(currentDino.getxCoord(), currentDino.getyCoord(), currentDino.getWidth(), currentDino.getHeight())) {
								currentDino.setVisibility(false);
								deadDinos++;
								//System.out.println("dead dino!"+deadDinos);
								//gameOver();
								//((Timer)arg0.getSource()).stop();
							}
						}
						
						dinos.getPopulation().get(j).setDistToNextBird(d2);
						
	
						// Move Dinosaur up and down (jumping)
						if (currentDino.getJumpVelocity() > 0 ||!currentDino.isTouchingGround()) {
							int duckingFactor = 0;
							if (currentDino.isDucking()) {
								duckingFactor = 7;
							}
							currentDino.setyCoord(currentDino.getyCoord() - currentDino.getJumpVelocity()/(5) + duckingFactor);
							currentDino.setJumpVelocity(currentDino.getJumpVelocity()-(4+(speed)));
							
							// So dino doesn't go beneath the ground
							if (currentDino.getyCoord() > (currentDino.getGround() - currentDino.getHeight())) {
								currentDino.setyCoord(currentDino.getGround() - currentDino.getHeight());
							}
						}
						
						dinos.getPopulation().get(j).evaluate();
						//if (dinos.getPopulation().get(j).getDino().isVisible()) 
							//System.out.println("Weights: "+ dinos.getPopulation().get(j).getWallWeight() + ", "+dinos.getPopulation().get(j).getBirdWeight());
							//System.out.println("Bird: "+dinos.getPopulation().get(j).getDistToNextBird());
					}
				}
				
				if (deadDinos >= dinos.getMaxPop()) {
					System.out.println("All dinos are dead!");
					generation++;
					System.out.println("New generation: "+generation);
					resetGame();
					return;
				}
				
				int counter = 0;
				while (!dinos.getPopulation().get(board.getSelectedDino()).getDino().isVisible()) {
					board.setSelectedDino(counter);
					counter++;
				}
				
				// ------------------------------------------------------------------------------------------
				
				generateDelay -= 1;
				if (generateDelay == 0) {
					//
					generateDelay = 55 - 2*speed - ((int) (Math.random() * 20));
					double chance = Math.random();
					double chance2 = Math.random();
					//if (chance < 0.2) {
						if (chance < 0.75) {
							// Generate wall
							//System.out.println("Wall created");
							int wallHeight = 40 - ((int) (Math.random() * 10));
							walls.add(new Wall(screenWidth-44, screenHeight-75-wallHeight, 10, wallHeight));
							if (chance2 < 0.5) {
								walls.add(new Wall(screenWidth-31, screenHeight-75-wallHeight, 10, wallHeight));
							}
							if (chance2 < 0.2) {
								walls.add(new Wall(screenWidth-18, screenHeight-75-wallHeight, 10, wallHeight));
							}
							if (chance2 < 0.1) {
								walls.add(new Wall(screenWidth-5, screenHeight-75-wallHeight, 10, wallHeight));
							}
						} else {
							// Generate bird
							//System.out.println("Bird created");
							birds.add(new Bird(screenWidth-5, screenHeight-145, 25, 20));
						}
					//}
				}
				
				//System.out.println("Next Wall: "+distanceToNextWall+ " Next Bird: "+distanceToNextBird);
				score += 1;
				
				for (int i = 0; i < scoreMilestones.length; i++) {
					if (scoreMilestones[i] == score) {
						speed = 5+i;
						System.out.println("New speed: "+speed);
					}
				}
			}
			
		});
		
		JButton startStopButton = new JButton("Start");
		buttonPanel.add(startStopButton);
		startStopButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(startStopButton.getText().equals("Start")){
					startStopButton.setText("Stop");
					timer.start();
					resetFocus();
				}
				else{
					startStopButton.setText("Start");
					timer.stop();
					resetFocus();
				}
				
			}
			
		});
		
		JButton resetButton = new JButton("Reset");
		buttonPanel.add(resetButton);
		resetButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetFocus();
				//dino = new Dinosaur();
				resetGame();
				timer.start();
			}
			
		});
		
		
	}
	
//	public void addDinosaur(Dinosaur d) {
//		dinos.add(d);
//		System.out.println("Added a dinosaur");
//	}
	
	public void resetGame() {
		dinos.breed(dinos.naturalSelection());
		board.setSelectedDino(0);
		System.out.println(dinos.getPopulation().get(board.getSelectedDino()).getBirdWeight());
		dinos.getPopulation().get(board.getSelectedDino()).getDino().setVisibility(true);
		prevScore = score;
		txtPrevDistance.setText("Previous Best: " + prevScore);
		score = 0;
		walls.clear();
		birds.clear();
		speed = 4;
		generateDelay = 40;
		deadDinos = 0;
	}
	public void gameOver() {
		//System.out.println("Game Over. Next Wall: "+distanceToNextWall+ " Next Bird: "+distanceToNextBird);
		//System.out.println("Score: "+score);
	}

	public void keyTyped(KeyEvent ke) { 

	}
	public void keyPressed(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_UP) {
	    	dino.jump();
	    }
	    
	    if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
	    	dino.startDuck();
	    }
	    if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			int counter = board.getSelectedDino();
			do {
				board.setSelectedDino(--counter);
			}
			while (!dinos.getPopulation().get(counter%dinos.getMaxPop()).getDino().isVisible());
	    }
	    if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			int counter = board.getSelectedDino();
			do {
				board.setSelectedDino(++counter);
			}
			while (!dinos.getPopulation().get(counter%dinos.getMaxPop()).getDino().isVisible());
	    }
	}

	public void keyReleased(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
	    	dino.endDuck();
	    }
		
	}
	
	public void resetFocus() {
		this.requestFocus();
	}

	
}