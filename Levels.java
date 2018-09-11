import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComponent;

public class Levels extends JComponent {
	int playerX = 0, playerY = 0;
	protected Graphics2D graphic;
	protected Rectangle pacMan = new Rectangle();
	int foodX, foodY, score = 0, circleMove = 7;
	int deaths = 0;
	boolean onGround = false;
	static final int scaleFactor = 25;
	protected CopyOnWriteArrayList<Obstacle> circlesLeft = new CopyOnWriteArrayList<Obstacle>();
	protected CopyOnWriteArrayList<Obstacle> circlesRight = new CopyOnWriteArrayList<Obstacle>();

	private WorldsHardestGame p = null;
	
	public boolean LevelOver = false;
	
	public Levels(WorldsHardestGame p) {
		this.p=p;
	}

	public void start() {
		Thread animationThread = new Thread(new Runnable() {
			public void run() {
				while (!win()) {

					if (p.pX[0] == 1) {
						playerX += 4;
					}
					if (pacMan.getX() >= getWidth() - (score + 1) * scaleFactor * 2) {
						playerX -= 4;
					}
					if (pacMan.getX() <= 0) {
						playerX += 4;
					}
					if (p.pX[0] == -1) {
						playerX -= 4;
					}
					if (p.pY[0] == 1) {
						playerY -= 4;
					}
					if (p.pY[0] == -1) {
						playerY += 4;
					}
					if (playerY <= -10) {
						playerY += 4;
					}
					if (playerY >= getHeight() - (score + 1) * scaleFactor * 2) {
						playerY -= 4;
					}
					repaint();
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		animationThread.start();
	}

	public boolean win() {
		if (playerX >= 2440) {
			System.out.println("true");
			LevelOver=true;
		}
		return LevelOver;
	}

	public void paintComponent(Graphics g) {
		
	}
	
	public void reset() {

		circlesLeft = new CopyOnWriteArrayList<Obstacle>();
				
		circlesRight = new CopyOnWriteArrayList<Obstacle>();
	}
}

