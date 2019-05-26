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

public class Game extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dinosaur dino;
	private ArrayList<Wall> walls;
	private ArrayList<Bird> birds;
	private int generateDelay = 40;
	private int speed = 4;
	
	private int score;
	private int distanceToNextWall;
	private int distanceToNextBird;
	
	public static void main(String[] args) {
		// run stuff
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame game = new Game();
					game.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Game() {
		int screenWidth = 600;
		int screenHeight = 400;
		int[] scoreMilestones = {250, 1000, 2500, 5000, 10000};
		//int[] scoreMilestones = {250, 500, 750, 1000, 1250, 1500};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, screenWidth, screenHeight);
		setResizable(false);
		addKeyListener(this);
		setFocusable(true);
		this.requestFocus();
		
		
		//this.addKeyListener(new myKeyListener());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		//dino = new Dinosaur(20, screenHeight-130, 30, 55, 100, 20);
		dino = new Dinosaur();
		DinoGene selectedDino = new DinoGene();
		selectedDino.getDino().setVisibility(false);
		walls = new ArrayList<Wall>();
		birds = new ArrayList<Bird>();
		
		JPanel board = new BoardOnePlayer(dino, walls, birds);
		contentPane.add(board, BorderLayout.CENTER);
		
		Timer timer = new Timer(20, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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
						((Timer)arg0.getSource()).stop();
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
					
					if (distanceToNextBird == screenWidth && birds.get(i).getxCoord()-dino.getxCoord() > 0) {
						distanceToNextBird = birds.get(i).getxCoord()-dino.getxCoord();
					}
					
					if (birds.get(i).isInBounds(dino.getxCoord(), dino.getyCoord(), dino.getWidth(), dino.getHeight())) {
						gameOver();
						((Timer)arg0.getSource()).stop();
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
					dino.setJumpVelocity(dino.getJumpVelocity()-(5+(speed)));
					
					// So dino doesn't go beneath the ground
					if (dino.getyCoord() > (dino.getGround() - dino.getHeight())) {
						dino.setyCoord(dino.getGround() - dino.getHeight());
					}
				}
				
				
				generateDelay -= 1;
				if (generateDelay == 0) {
					//
					generateDelay = 55 - ((int) (Math.random() * 20));
					double chance = Math.random();
					double chance2 = Math.random();
					//if (chance < 0.2) {
						if (chance < 0.75) {
							// Generate wall
							//System.out.println("Wall created");
							int wallHeight = 40 - ((int) (Math.random() * 10));
							walls.add(new Wall(screenWidth-5, screenHeight-75-wallHeight, 10, wallHeight));
							if (chance2 < 0.5) {
								walls.add(new Wall(screenWidth-18, screenHeight-75-wallHeight, 10, wallHeight));
							}
							if (chance2 < 0.2) {
								walls.add(new Wall(screenWidth-31, screenHeight-75-wallHeight, 10, wallHeight));
							}
							if (chance2 < 0.1) {
								walls.add(new Wall(screenWidth-44, screenHeight-75-wallHeight, 10, wallHeight));
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
				score = 0;
				walls.clear();
				birds.clear();
				speed = 4;
				generateDelay = 40;
				timer.start();
				System.out.println("ss");
			}
			
		});
		
		
	}
	public void gameOver() {
		System.out.println("Game Over. Next Wall: "+distanceToNextWall+ " Next Bird: "+distanceToNextBird);
		System.out.println("Score: "+score);
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