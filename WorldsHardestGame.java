import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WorldsHardestGame {
	int level = 1;
	public JFrame frame = null;
	int[] pX = { 0 };
	int[] pY = { 0 };
	private boolean finished = false;

	public WorldsHardestGame() {

	}

	public void InitGame() {

		frame = new JFrame("Game Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(2600, 1800);
		frame.setLocationRelativeTo(null);

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == (KeyEvent.VK_LEFT)) {
					pX[0] = -1;
					// pY[0] = 0;
				}
				if (arg0.getKeyCode() == (KeyEvent.VK_RIGHT)) {
					pX[0] = 1;
					// pY[0] = 0;
				}
				if (arg0.getKeyCode() == (KeyEvent.VK_UP)) {
					pY[0] = 1;
					// pX[0] = 0;
				}
				if (arg0.getKeyCode() == (KeyEvent.VK_DOWN)) {
					pY[0] = -1;
					// pX[0] = 0;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyCode() == KeyEvent.VK_UP) {
					if (pY[0] == 1) {
						pY[0] = 0;
					}
				}
				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
					if (pY[0] == -1) {
						pY[0] = 0;
					}
				}

				if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					if (pX[0] == -1) {
						pX[0] = 0;
					}
				}
				if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (pX[0] == 1) {
						pX[0] = 0;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void play() {
		frame.setVisible(true);
		
		Levels L1 = new LevelOne(this);
		L1.start();
		frame.add(L1);
		frame.validate();

		while(!L1.win()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frame.remove(L1);
		
		L1=null;
		Levels L2 = new LevelTwo(this);
		frame.add(L2);
		frame.validate();
		L2.start();
		
		while(!L2.win()) {
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		frame.remove(L2);
		L2 = null;
		
		Levels L3 = new LevelFour(this);
		frame.add(L3);
		frame.validate();
		L3.start();
		
		while(!L3.win()) {
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public static void main(String[] args) {

		WorldsHardestGame H = new WorldsHardestGame();
		H.InitGame();
		H.play();
	}
}
