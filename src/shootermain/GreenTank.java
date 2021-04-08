package shootermain;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Green tanks fire faster and their missile move faster
public class GreenTank extends BasicEnemy{

	public GreenTank(int x, int y, Size sz, ID id, Handler h, BufferedImage i) {
		super(x, y, sz, id, h, i);
		
		//Sets the velocity of the projectile to be 12 and not 6
		PVELOCITY = 12;
		// TODO Auto-generated constructor stub
	}
	
	/**************************************************
	 * Method shoot
	 * 		Spawns a new projectile
	 * 		-Green Tank projectiles are faster
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	@Override
	public void shoot()
	{
		if(canFire)
		{
			Projectile p = null;
			Size s = new Size(pheight, pwidth, getSize().x + getSize().width/2 - 4, getSize().y);
			p = new BasicEnemyProjectile(firex, firey, s, ID.EnemyProjectile, handler, pjimage, firevelx, firevely);
			handler.addObject(p);
			canFire = false;
			fired = true;
		}
	}
	
	/**************************************************
	 * Method fireLogistics
	 * 		Checks if the tank can fire
	 * 		-Green Tanks fire more frequently
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public void fireLogistics()
	{
		//If we have fired, we increment cooldown by one each tick
		if(fired)
		{
			cooldown++;
		}
		
		//If 70 ticks have passed, we fire
		if(cooldown == 70)
		{
			//Reset the cooldown and fire state variables
			cooldown = 0;
			canFire = true;
			fired = false;	
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
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Green tank ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Green tank rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Green tank dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Green tank lfacing.png"));
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
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Green missiles/Green missile ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Green missiles/Green missile rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Green missiles/Green missile dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Green missiles/Green missile lfacing.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
