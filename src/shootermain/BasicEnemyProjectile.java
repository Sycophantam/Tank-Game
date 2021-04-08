package shootermain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class BasicEnemyProjectile extends Projectile {
	
	public BasicEnemyProjectile(int x, 				//Initial x location of the projectile
							    int y, 				//Initial x location of the projectile	
							    Size sz, 			//Size of the projectile
							    ID id, 				//Projectile Id
							    Handler h, 			//Handler of objects
							    BufferedImage i, 	//Image of the projectile
							    int velx, 			//Initial x velocity of the projectile
							    int vely)			//Initial y velocity of the projectile
	{
		super(x, y, sz, id, h, i, velx, vely);
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
		g.drawImage(image, this.getX(), this.getY(), null);		
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(getSize().x, getSize().y, getSize().width, getSize().height);
	}

	/**************************************************
	 * Method collision
	 * 		Checks if the projectile has collided with the player
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
		//Checks if the projectile's area collides with that of the Player
		//If so, the player's health goes down
		for(int i = 0; i < handler.getObjects().size(); i++)
		{
			GameObject tempObject = handler.getObjects().get(i);
			{
				if(tempObject.getID() == ID.Player)
				{
					Player tempPlayer = (Player)tempObject;
					if(getBounds().intersects(tempPlayer.getBounds()))
					{
						tempPlayer.hud.HEALTH--;
					}
				}
			}
		}
	}

}
