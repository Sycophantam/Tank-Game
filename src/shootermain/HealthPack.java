package shootermain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HealthPack extends GameObject{

	private Handler handler;
	public HealthPack(int x, int y, Size sz, ID id, BufferedImage i, Handler h) {
		super(x, y, sz, id, i);
		
		handler = h;
		setX(x);
		setY(y);
		
		getSize().x = getX();
		getSize().y = getY();
		
		getSize().height = 18;
		getSize().width = 18;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY(), null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getSize().x, getSize().y, getSize().width, getSize().height);
	}

	
}
