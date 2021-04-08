package shootermain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Projectile extends GameObject{

	public Handler handler;	//Data of all objects in the game
	
	
	public Projectile(int x, 			//Initial x location of the projectile
					  int y, 			//Initial x location of the projectile	
					  Size sz, 			//Size of the projectile
					  ID id, 			//Projectile Id
					  Handler h, 		//Handler of objects
					  BufferedImage i, 	//Image of the projectile
					  int velx, 		//Initial x velocity of the projectile
					  int vely)			//Initial y velocity of the projectile
	{
		super(x,y,sz,id, i);
		handler = h;
		setVelX(velx);
		setVelY(vely);
	}

	@Override
	public void tick() 
	{
		//If the projectile travels off screen, the projectile's memory is deallocated
		if(getX() >= Game.PLAYWIDTH || getX() <= 0)
		{
			handler.removeObject(this);
		}
		else if(getY() >= Game.PLAYHEIGHT || getY() <= 0)
		{
			handler.removeObject(this);
		}
		//Update the projectile's location
		else
		{
			setX(getX() + getVelX());
			setY(getY() + getVelY());
			
			getSize().x = getX() + getVelX();
			getSize().y = getY() + getVelY();
			
			//Checks to see if the projectile has collided with anything
			collision();
		}
		
	}

	@Override
	public void render(Graphics g) 
	{
		//Draws the image of the projectile
		g.drawImage(image, this.getX(), this.getY(), null);	
		//g.setColor(Color.white);
		//g.fillRect(getSize().x, getSize().y, getSize().width, getSize().height);
	}

	@Override
	
	public Rectangle getBounds()
	{
		//Returns the area that the projectile occupies
		return new Rectangle(getSize().x, getSize().y, getSize().width, getSize().height);
	}

	/**************************************************
	 * Method collision
	 * 		Checks if the projectile has collided with an enemy
	 *
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void collision()
	{
		//Checks if the projectile's area collides with that of an enemy
		for(int i = 0; i < handler.getObjects().size(); i++)
		{
			GameObject tempObject = handler.getObjects().get(i);
			if(tempObject.getID() == ID.BasicEnemy)
			{
				BasicEnemy tempEnemy = (BasicEnemy)tempObject;
				if(getBounds().intersects(tempEnemy.getBounds()))
				{
					tempEnemy.die();
					handler.removeObject(this);
				}
			}
		}
	}
}
