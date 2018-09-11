public class Obstacle {
	int radius = 0;
	int posX, posY;

	public Obstacle(int radius, int posX, int posY) {
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
	}

	public int getRadius() {
		return radius;
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public void addX(int plus) {
		posX += plus;
	}
	public void addY(int plus) {
		posY += plus;
	}
}