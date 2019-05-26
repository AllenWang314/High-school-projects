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
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame implements KeyListener {

	private JPanel contentPane;
	//private ArrayList<AI> ais;
	private Population population;
	private ArrayList<Wall> walls;
	private ArrayList<Enemy> enemies;
	private DisplayBoard displayBoard;
	private Goal goal;
	private Board board;
	
	public static void main(String[] args) {
		// run stuff
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame game = new App();
					game.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public App() {
		setTitle("Test");
		int screenWidth = 1200;
		int screenHeight = 800;
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
		
//		ais = new ArrayList<AI>();
//		ais.add(new AI());
		goal = new Goal(200,30,10,10);
		board = new Board();
		population = new Population(200,0.15,0.2,0.5,0.5);
		walls = new ArrayList<Wall>();
		walls.add(new Wall(50,70,270,20));
		walls.add(new Wall(160,0,20,80));
		walls.add(new Wall(100,140,300,20));
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy(10,20,10,10,10,20,110,120,2));
		
		displayBoard = new DisplayBoard(population, goal, board, walls, enemies);
		contentPane.add(displayBoard, BorderLayout.CENTER);
		
		Timer timer = new Timer(20, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < population.getPopulation().size(); i++) {
					AI current = population.getPopulation().get(i);
					if (!current.getIsDead() && !current.getReachedGoal()) {
						Vector v = current.getCurrentMove();
						if (board.isInBounds(current,v)) {
							boolean blocked = false;
							for (int j = 0; j < walls.size(); j++) {
								if (current.isInBounds(walls.get(j).getxCoord(), walls.get(j).getyCoord(), walls.get(j).getWidth(), walls.get(j).getHeight(),v)) {
									blocked = true;
									break;
								}
							}
							for (int j = 0; j < enemies.size(); j++) {
								if (current.isInBounds(enemies.get(j).getxCoord(), enemies.get(j).getyCoord(), enemies.get(j).getWidth(), enemies.get(j).getHeight(),v)) {
									current.setIsDead(true);
									break;
								}
							}
							if (!blocked) {
								current.setxCoord(current.getxCoord() + v.getX());
								current.setyCoord(current.getyCoord() + v.getY());
							}
							if (current.isInBounds(goal.getxCoord(), goal.getyCoord(), goal.getWidth(), goal.getHeight())) {
								current.setReachedGoal(true);
								//System.out.println("Hit goal");
								System.out.println(current.getStep());
								current.setFitness(100000 + 100000000/(current.getStep()*current.getStep()));
							}
						} else {
							//current.setIsDead(true);
							//current.setStep(1000);
						}
						//System.out.println("X: "+current.getxCoord()+" Y: "+current.get(i).getyCoord());
					} 
					if (current.getIsDead() && !current.getReachedGoal()) {
						double dist = Math.hypot(current.getxCoord()-goal.getxCoord(), current.getyCoord()-goal.getyCoord());
						current.setFitness(10000 / (dist * dist));
					}
				}
				if (population.isDone()) {
					newGen();
				}
				for (int i = 0; i < enemies.size(); i++) {
					Enemy current = enemies.get(i);
					Vector v = current.getVector();
					//System.out.println("Vector: x-"+v.getX()+" y-"+v.getY());
					if (current.isForward()) {
						if (Math.hypot(current.getxCoord() - current.getX2(),current.getyCoord() - current.getY2()) < 25) {
							current.setForward(false);
						}
						current.setxCoord(current.getxCoord() + v.getX());
						current.setyCoord(current.getyCoord() + v.getY());
					} else {
						if (Math.hypot(current.getxCoord() - current.getX1(),current.getyCoord() - current.getY1()) < 25) {
							current.setForward(true);
						}
						current.setxCoord(current.getxCoord() - v.getX());
						current.setyCoord(current.getyCoord() - v.getY());
					}
					
				}
				repaint();
			}
			
		});
		

		JButton startButton = new JButton("Start");
		buttonPanel.add(startButton);
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetFocus();
				timer.start();
				System.out.println("Button pressed");
			}
			
		});
		
//		JButton newGenButton = new JButton("New Gen");
//		buttonPanel.add(newGenButton);
//		newGenButton.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				resetFocus();
//				newGen();
//			}
//			
//		});
		
		JSlider speedTest = new JSlider(JSlider.VERTICAL,2,10,2);
		speedTest.setMinorTickSpacing(1);
		speedTest.setMajorTickSpacing(2);
		speedTest.setPaintLabels(true);
		speedTest.setPaintTicks(true);
		speedTest.setSnapToTicks(true);
		speedTest.setToolTipText("Number of Sides");
		speedTest.addChangeListener(new ChangeListener() {
		        public void stateChanged(ChangeEvent ce) {
		            enemies.get(0).setSpeed(((JSlider) ce.getSource()).getValue());
		            repaint();
		        }
		    });
		contentPane.add(speedTest, BorderLayout.WEST);
	}
	
	public void resetFocus() {
		this.requestFocus();
	}
	
	public void newGen() {
		//population.calcFitness(goal);
		population.breed(population.selectBest());
		//population.breed(population.naturalSelect());
		for (Enemy e : enemies) {
			e.setxCoord(e.getX1());
			e.setyCoord(e.getY1());
			//e.reset();
		}
		System.out.println("New Generation!");		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
