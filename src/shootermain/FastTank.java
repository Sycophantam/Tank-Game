package shootermain;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


//Fast tanks move faster and more frequently
public class FastTank extends BasicEnemy{

	
	
	
	public FastTank(int x, int y, Size sz, ID id, Handler h, BufferedImage i) {
		super(x, y, sz, id, h, i);
		SPEED = 7;
		MOVETIME = 10;
		// TODO Auto-generated constructor stub
	}
	
	/**************************************************
	 * Method moveLogistics
	 * 		Governs how the object moves
	 * 		-Fast tanks will move faster and more frequently
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public void moveLogistics()
	{
		//If 100 ticks have gone by, we move
		if(moveCooldown == 100)
		{
			move(SPEED);
			//Reset moveCooldown
			moveCooldown = 0;
		}
		
		//As we are moving, we decrease moveCounter
		if(moving)
		{
			moveCounter--;
		}
		
		//Once we are done moving, we stop and reset moveCounter
		if(moveCounter == 0)
		{
			setVelX(0);
			setVelY(0);
			moveCounter = MOVETIME;
		}
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
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Fast tank ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Fast tank rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Fast tank dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Fast tank lfacing.png"));
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
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Yellow missiles/Yellow missile ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Yellow missiles/Yellow missile rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Yellow missiles/Yellow missile dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Yellow missiles/Yellow missile lfacing.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
