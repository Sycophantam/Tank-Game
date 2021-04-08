package shootermain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class BasicEnemy extends GameObject{

	Handler handler;				//Data of every object in the game
	public int pwidth, pheight;	//Projectile's width and height
	private Size fireRange;			//Range that the projectile will cover when fired
	//Player tempPlayer;				//Player variable
	
	//Variables that control whether or not the player can fire a missile
	boolean canFire = true;	//Player can fire				
	boolean fired = false;	//Player has just fired
	long cooldown = 0;		//Fire cooldown
		
	
	int PVELOCITY = 6;			//Velocity of the projectile spawned
	boolean moving = false;		//Checks if the tank is moving
	public int SPEED = 5;		//Speed that the tank moves
	public int MOVETIME = 15;	//Time that the tank spends moving
	int moveCooldown = 0;		//Time between movements
	int moveCounter = MOVETIME;	//Counts down from MOVETIME each tick
	
	
	int xleg = 0;		//X distance that the player is from the enemy
	int yleg = 0;		//Y distance that the player is from the enemy
	
	
	public BasicEnemy(int x, int y, Size sz, ID id, Handler h, BufferedImage i)
	{
		super(x, y, sz, id, i);
		
		handler = h;
		image = i;
		
		setVelX(0);
		setVelY(0);
		
		fireRange = new Size();
		
		
	}

	@Override
	public void tick() 
	{
		setX(getX() + getVelX());
		setY(getY() + getVelY());
			
		getSize().x = getX() + getVelX();
		getSize().y = getY() + getVelY();
		
		
		/*
		 * This section of code controls where the enemy faces
		 * when it knows your position
		 *  
		 */
		xleg = getBounds().x - Player.broadcast().x;
		yleg = getBounds().y - Player.broadcast().y;
		
		//The player is to the right of the enemy
		if((yleg <= 40 && 0 <= yleg) && xleg <= 0)
		{
			facing = 1;
		}
		else if((yleg >= -40 && 0 >= yleg) && xleg <= 0) 
		{
			facing = 1;
		}
		
		//The player is above the enemy
		else if(yleg >= 0 && (xleg <= 0 && xleg >= -40))
		{
			facing = 0;
		}
		else if(yleg >= 0 && (xleg >= 0 && xleg <= 40))
		{
			facing = 0;
		}
			
		//The player is to the left of the enemy
		else if(xleg >= 0 && (yleg >= 0 && yleg <= 40))
		{
			facing = 3;
		}
		else if(xleg >= 0 && (yleg <= 0 && yleg >= -40))
		{
			facing = 3;
		}
		
		//The player is below the enemy
		else if(yleg <= 0 && (xleg >= 0 && xleg <= 40))
		{
			facing = 2;
		}
		else if(yleg <= 0 && (0 >= xleg && xleg >= -40))
		{
			facing = 2;
		}
		
		//System.out.println(facing);
		
		
		//Changes the size and location of fireRange based on which way the tank is facing
		if(facing == 0)
		{
			fireRange.x = getX();
			fireRange.y = getY() - Game.PLAYHEIGHT;
			fireRange.height = Game.PLAYHEIGHT;
			fireRange.width = getSize().width;
		}
		else if(facing == 1)
		{
			fireRange.x = getX();
			fireRange.y = getY();
			fireRange.height = getSize().height;
			fireRange.width = Game.PLAYWIDTH;
		}
		else if(facing == 2)
		{
			fireRange.x = getX();
			fireRange.y = getY();
			fireRange.height = Game.PLAYHEIGHT;
			fireRange.width = getSize().width;
		}
		else if(facing == 3)
		{
			fireRange.x = getX() - Game.PLAYWIDTH;
			fireRange.y = getY();
			fireRange.height = getSize().height;
			fireRange.width = Game.PLAYWIDTH;
		}
		
		//Check if everything is ready to fire
		fireLogistics();
		
		//If the player is in the fireRange, shoot a projectile
		if(Player.broadcast().intersects(new Rectangle(fireRange.x, fireRange.y, fireRange.width, fireRange.height)))
		{
			shoot();
		}
		
		//Check if we can move 
		moveLogistics();
		setX(Game.clamp(getX(), 0, Game.PLAYWIDTH - getSize().width));
		setY(Game.clamp(getY(), 0, Game.PLAYHEIGHT - getSize().height));
		
		moveCooldown++;
			
	}

	@Override
	public void render(Graphics g) 
	{
//		Integer i = new Integer((Integer)moveCooldown);
//		g.drawString(i.toString(), 50, 50);
		//Facing UP
		if(facing == 0)
		{
			firex = getX() + getSize().width/2 - 4;
			//Approximately half the width of the tank
			firey = getY() - getSize().height;
			//Approximately half the height of the tank
			
			
			//Velocities for a spawned projectile
			//-will only move upward
			firevelx = 0;
			firevely = -PVELOCITY;
			//Projectile's height and width will be flipped if the tank is facing horizontally
			pwidth = 4;
			pheight = 16;
			image = returnTankImage();
			pjimage = returnProjectileImage();
		}
		
		//Facing RIGHT
		else if(facing == 1)
		{
			firex = getX() + getSize().width - 6;
			//The front of the tank
			firey = getY() + getSize().height/2 - 8;
			//Approximately half the height of the tank
			
			//Velocities for a spawned projectile
			//-will only move rightward
			firevelx = PVELOCITY;
			firevely = 0;
			
			//Projectile's height and width will be flipped if the tank is facing vertically
			pwidth = 16;
			pheight = 4;
			image = returnTankImage();
			pjimage = returnProjectileImage();
		}
		
		//Facing DOWN
		else if(facing == 2)
		{
			firex = getX() + getSize().width/2 - 8;
			//Approximately half the width of the tank
			firey = getY() + getSize().height - 8;
			//Bottom of the tank
			
			//Velocities for a spawned projectile
			//-will only move downward
			firevelx = 0;
			firevely = PVELOCITY;
			
			//Projectile's height and width will be flipped if the tank is facing horizontally
			pwidth = 4;
			pheight = 16;
			image = returnTankImage();
			pjimage = returnProjectileImage();
		}
		
		//Facing RIGHT
		else if(facing == 3)
		{
			firex = getX();
			//Front of the tank
			firey = getY() + getSize().height/2 - 8;
			//Approximately half the height of the tank
			
			//Velocities for a spawned projectile
			//-will only move leftward
			firevelx = -PVELOCITY;
			firevely = 0;

			//Projectile's height and width will be flipped if the tank is facing vertically
			pwidth = 16;
			pheight = 4;
			image = returnTankImage();
			pjimage = returnProjectileImage();
		}
		
		g.drawImage(image, getSize().x, getSize().y, null);
		
		
	}


	public Rectangle getBounds() 
	{
		return new Rectangle(getSize().x, getSize().y, getSize().width, getSize().height);
	}

	/**************************************************
	 * Method shoot
	 * 		Spawns a new projectile
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
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
	 * Method die
	 * 		Removes the object from the game's memory
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public void die()
	{
		HUD.score += 100;
		handler.removeObject(this);
		
	}
	
	/**************************************************
	 * Method moveLogistics
	 * 		Governs how the object moves
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
		//If 150 ticks have gone by, we move
		if(moveCooldown == 150)
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
	 * Method move
	 * 		Moves the object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public void move(int speed)
	{
		moving = true;
		Random r = new Random(System.currentTimeMillis());
		int direction = (r.nextInt(100) + 1) % 4;
		if(direction == 0)
		{
			setVelY(-speed);
			
		}
		else if(direction == 1)
		{
			setVelX(speed);
		}
		else if(direction == 2)
		{
			setVelY(speed);
		}
		else if(direction == 3)
		{
			setVelX(-speed);
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
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/BasicEnemy tank ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/BasicEnemy tank rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/BasicEnemy tank dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/BasicEnemy tank lfacing.png"));
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
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Brown missiles/Brown missile ufacing.png"));
			else if(facing == 1)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Brown missiles/Brown missile rfacing.png"));
			else if(facing == 2)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Brown missiles/Brown missile dfacing.png"));
			else if(facing == 3)
				tempImage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Brown missiles/Brown missile lfacing.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	/**************************************************
	 * Method fireLogistics
	 * 		Checks if the tank can fire
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
		
		//If 100 ticks have passed, we fire
		if(cooldown == 100)
		{
			//Reset the cooldown and fire state variables
			cooldown = 0;
			canFire = true;
			fired = false;	
		}
	}
	

}
