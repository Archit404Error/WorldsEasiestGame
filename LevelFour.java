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

@SuppressWarnings({ "unused", "serial" })
public class LevelFour extends Levels {
	int moveUp = 10;
	protected CopyOnWriteArrayList<Obstacle> horizontalCircle = new CopyOnWriteArrayList<Obstacle>(),
			horizontalCircle2 = new CopyOnWriteArrayList<Obstacle>();

	public LevelFour(WorldsHardestGame p) {
		super(p);
		for (int i = 0; i <= 10; i++) {
			Obstacle o = new Obstacle(50, 100, i * 180);
			circlesLeft.add(o);
		}
		for (int i = 0; i <= 18; i++) {
			Obstacle o = new Obstacle(50, p.frame.getWidth() - 100, (i + 1) * 90);
			circlesRight.add(o);
		}
		for (int i = 0; i <= 40; i++) {
			Obstacle o = new Obstacle(50, i * 180, 200);
			horizontalCircle.add(o);
		}
		for (int i = 0; i <= 80; i++) {
			Obstacle o = new Obstacle(50, (i + 1) * 90,  p.frame.getHeight()-200);
			horizontalCircle2.add(o);
		}
	}

	public void paintComponent(Graphics g) {
		graphic = (Graphics2D) g;
		graphic.setColor(Color.GREEN);
		graphic.drawRect(0, 0, 40, getHeight());
		graphic.setColor(Color.GRAY);

		for (int i = 0; i < horizontalCircle.size(); i++) {
			if (horizontalCircle.get(i).getY() <= 0) {
				if (moveUp < 0) {
					moveUp *= -1;
				}
			}
			if (horizontalCircle.get(i).getY() >= getHeight()) {
				if (moveUp > 0) {
					moveUp *= -1;
				}
			}
			horizontalCircle.get(i).addY(moveUp);
			if (pacMan.contains(horizontalCircle.get(i).getX(), horizontalCircle.get(i).getY())) {
				if (playerX >= 5) {
					deaths++;
					playerX = 0;
				}
			}

			graphic.fillOval(horizontalCircle.get(i).getX(), horizontalCircle.get(i).getY(),
					horizontalCircle.get(i).getRadius(), horizontalCircle.get(i).getRadius());
		}

		for (int i = 0; i < horizontalCircle2.size(); i+=2) {
			horizontalCircle2.get(i).addY(-1 * moveUp);
			if (pacMan.contains(horizontalCircle2.get(i).getX(), horizontalCircle2.get(i).getY())) {
				if(playerX >= 5) {
					deaths++;
					playerX = 0;
				}
			}
			graphic.fillOval(horizontalCircle2.get(i).getX(), horizontalCircle2.get(i).getY(),
					horizontalCircle2.get(i).getRadius(), horizontalCircle2.get(i).getRadius());
		}

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
			if (circlesLeft.get(i).getX() <= getWidth() / 2) {
				circlesLeft.get(i).addY(circleMove / 3);
			} else {
				circlesLeft.get(i).addY(-1 * circleMove / 3);
			}
			graphic.fillOval(circlesLeft.get(i).getX(), circlesLeft.get(i).getY(), circlesLeft.get(i).getRadius(),
					circlesLeft.get(i).getRadius());

		}
		for (int i = 0; i < circlesRight.size(); i += 2) {
			circlesRight.get(i).addX(-3 / 2 * circleMove);
			if (circlesRight.get(i).getX() <= getWidth() / 2) {
				circlesRight.get(i).addY(circleMove / 3);
			} else {
				circlesRight.get(i).addY(-1 * circleMove / 3);
			}
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
