package shootermain;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Armored tanks take one extra hit to kill them
public class ArmoredTank extends BasicEnemy{

	boolean hit;
	public ArmoredTank(int x, int y, Size sz, ID id, Handler h, BufferedImage i) {
		super(x, y, sz, id, h, i);
		hit = false;
	}
	
	/**************************************************
	 * Method returnTankImage
	 * 		returns the image of the tank
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public BufferedImage returnTankImage()
	{
		BufferedImage tempImage = null;
		try {
			if(facing == 0)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Armored tank ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Armored tank rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Armored tank dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Armored tank lfacing.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	/**************************************************
	 * Method returnProjectileImage
	 * 		returns the image of the projectile
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public BufferedImage returnProjectileImage()
	{
		BufferedImage tempImage = null;
		try {
			if(facing == 0)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Gray missiles/Gray missile ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Gray missiles/Gray missile rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Gray missiles/Gray missile dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Gray missiles/Gray missile lfacing.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	/**************************************************
	 * Method die
	 * 		Removes the object from the game's memory
	 * 		-Armored tanks will take an extra hit before dying
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	@Override
	public void die()
	{
		if(hit)
			handler.removeObject(this);
		else
			hit = true;
			
	}

}
