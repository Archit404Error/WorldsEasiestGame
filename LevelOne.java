import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class LevelOne extends Levels {

	public LevelOne(WorldsHardestGame p) {
		super(p);
		// TODO Auto-generated constructor stub
		for (int i = 0; i <= 10; i++) {
			Obstacle o = new Obstacle(50, 100, i * 180);
			circlesLeft.add(o);
		}
		for (int i = 0; i <= 18; i++) {
			Obstacle o = new Obstacle(50, p.frame.getWidth() - 100, (i + 1) * 90);
			circlesRight.add(o);
		}
	}

	public void paintComponent(Graphics g) {
		graphic = (Graphics2D) g;
		graphic.setColor(Color.GREEN);
		graphic.drawRect(0, 0, 40, getHeight());
		graphic.setColor(Color.GRAY);

		for (int i = 0; i < circlesLeft.size(); i++) {
			if (circlesLeft.get(i).getX() <= 0) {
				if (circleMove < 0) {
					circleMove *= -1;
				}
			}
			if (circlesLeft.get(i).getX() >= getWidth()) {
				if (circleMove > 0) {
					circleMove *= -1;
				}
			}
			if (pacMan.contains(circlesLeft.get(i).getX(), circlesLeft.get(i).getY())
					|| pacMan.contains(circlesLeft.get(i).getX(), circlesLeft.get(i).getY() + 50)) {
				if (playerX >= 5) {
					playerX = 0;
					deaths++;
				}
			}
			circlesLeft.get(i).addX(circleMove);
			graphic.fillOval(circlesLeft.get(i).getX(), circlesLeft.get(i).getY(), circlesLeft.get(i).getRadius(),
					circlesLeft.get(i).getRadius());
		}
		for (int i = 0; i < circlesRight.size(); i += 2) {
			circlesRight.get(i).addX(-1 * circleMove);
			graphic.fillOval(circlesRight.get(i).getX(), circlesRight.get(i).getY(), circlesRight.get(i).getRadius(),
					circlesRight.get(i).getRadius());
			if (pacMan.contains(circlesRight.get(i).getX(), circlesRight.get(i).getY())
					|| pacMan.contains(circlesRight.get(i).getX(), circlesRight.get(i).getY() + 50)) {
				if (playerX >= 5) {
					playerX = 0;
					deaths++;
				}
			}
		}

		pacMan = new Rectangle(playerX, playerY, (score + 1) * scaleFactor * 2, (score + 1) * scaleFactor * 2);

		
		graphic.setColor(Color.BLUE);
		// graphic.fillOval(100, 0, scaleFactor, scaleFactor);
		graphic.setColor(Color.RED);
		graphic.fill(pacMan);
		if (onGround) {
			// graphic.fillRect(foodX, foodY, 10, 10);
		}
		graphic.setColor(Color.BLUE);
		graphic.drawRect(getWidth() - 80, 0, 80, getHeight());
		graphic.setColor(Color.GREEN);
		graphic.fillRect(getWidth() - 75, 0, 75, getHeight());
		graphic.setColor(Color.RED);
		graphic.drawLine(0, 0, playerX, playerY);
		graphic.drawLine(getWidth(), getHeight(), playerX, playerY);
		graphic.drawLine(getWidth(), 0, playerX + 50, playerY + 25);
		graphic.setFont(new Font("Courier", Font.ITALIC, 60));
		graphic.drawString("FAILS: " + deaths, 1000, 50);

	}
}

