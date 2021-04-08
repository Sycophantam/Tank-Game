package shootermain;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**************************************************
 * Player Class
 * 		Playable game object
 * 
 * Accessors:
 * 		none
 * 
 * Mutators:
 * 		tick()
 * 		render(Graphics g)
 *************************************************/
public class Player extends GameObject {

	
	Handler handler;				//Data of every object in the game
	HUD hud;						//Information regarding score and health
	
	//Variables that control whether or not the player can fire a missile
	boolean canFire = true;	//Player can fire				
	boolean fired = false;	//Player has just fired
	long cooldown = 0;		//Fire cooldown
	
	private int pwidth, pheight;	//Width and height of the projectile it shoots
	
	private static Rectangle hitbox;	//Location where the object is
	/**************************************************
	 * Constructor (default constructor) 
	 * 		Creates a Player
	 * 
	 * Parameters:
	 * 		x(int) - x position
	 * 		y(int) - y position
	 * 		id(ID) - id of the object
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public Player(int x, 		//x position of the Player object
				  int y, 		//y position of the Player object
				  Size sz,		//Size of the Player
				  ID id,		//id of the Player object
				  Handler h,	//Data of all other objects in the game
				  BufferedImage i)	//Player's image
	{
		//Calls the constructor of GameObject
		super(x, y, sz, id, i);
		handler = h;
		hud = new HUD();
		
		hitbox = new Rectangle();
	}
		

	/********************
	 *     MUTATORS		*
	 *******************/
	
	/**************************************************
	 * Method tick
	 * 		Updates the position of the Player object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public void tick() 
	{
		
		//Updates position and velocity of the object and its hitbox
		setX(getX() + getVelX());
		setY(getY() + getVelY());
		
		getSize().x = getX() + getVelX();
		getSize().y = getY() + getVelY();
		
		setX(Game.clamp(getX(), 0, Game.PLAYWIDTH - getSize().width));
		setY(Game.clamp(getY(), 0, Game.PLAYHEIGHT - getSize().height));
		
		
		if(getVelX() > 0)
		{
			facing = 1;
		}
		else if(getVelX() < 0)
		{
			facing = 3;
		}
		if(fired)
		{
			cooldown++;
		}
		
		//If 30 iterations of tick have gone through, then the player can fire again
		if(cooldown == 30)
		{
			cooldown = 0;
			canFire = true;
			fired = false;	
		}
		hud.tick();
		collision();
		hitbox.setBounds(getSize().x, getSize().y, getSize().width, getSize().height);
		if(hud.HEALTH == 0)
		{
			die();
		}
		//System.out.println("Your location: " + getX() + ", " + getY());
		
		
	}

	/**************************************************
	 * Method render
	 * 		Redraws the Player object
	 * 
	 * Parameters:
	 * 		g(Graphics) - graphics information to update
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public void render(Graphics g) 	//Graphics object to update
	{
		//g.setColor(Color.white);
		//g.fillRect(getSize().x, getSize().y, getSize().width, getSize().height);
		
		/*	Depending on what direction we are facing, we set our fire coordinates
		 * 	and load the images necessary to run
		 */
		
		
		//Facing UPWARD
		if(facing == 0)
		{
			
			firex = getX() + getSize().width/2 - 4;
			//Approximately half the width of the tank
			firey = getY() - getSize().height;
			//Approximately half the height of the tank
			
			//Velocities for a spawned projectile
			//-will only move upward
			firevelx = 0;
			firevely = -6;
			//Projectile's height and width will be flipped if the tank is facing horizontally
			pwidth = 4;
			pheight = 8;
			try {
				image = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Player tank ufacing.png"));
				pjimage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Player missiles/Player missile ufacing.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			firevelx = 6;
			firevely = 0;
			//Projectile's height and width will be flipped if the tank is facing vertically
			pwidth = 8;
			pheight = 4;
			try {
				image = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Player tank rfacing.png"));
				pjimage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Player missiles/Player missile rfacing.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			firevely = 6;
			
			//Projectile's height and width will be flipped if the tank is facing horizontally
			pwidth = 4;
			pheight = 8;
			try {
				image = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Player tank dfacing.png"));
				pjimage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Player missiles/Player missile dfacing.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Facing LEFT
		else if(facing == 3)
		{
			firex = getX();
			//Front of the tank
			firey = getY() + getSize().height/2 - 8;
			//Approximately half the height of the tank
			
			//Velocities for a spawned projectile
			//-will only move leftward
			firevelx = -6;
			firevely = 0;
			
			//Projectile's height and width will be flipped if the tank is facing vertically
			pwidth = 8;
			pheight = 4;
			try {
				image = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Player tank lfacing.png"));
				pjimage = ImageIO.read(getClass().getResource("/Game pictures/Projectiles/Player missiles/Player missile lfacing.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Displaying the score
		g.drawImage(image, getX(), getY(), null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Score: " + HUD.score, 10, 30);
		hud.render(g);
		
			
	}
	
	/**************************************************
	 * Method collision
	 * 		Checks if the player collides with anything
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
		//Parses through the handler
		for(int i = 0; i < handler.getObjects().size(); i++)
		{
			GameObject tempObject = handler.getObjects().get(i);
			
			//If the player touches an enemy, the player will take damage
			if(tempObject.getID() == ID.BasicEnemy)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					hud.HEALTH--;
					hud.tick();
				}
			}
			//If the player touches a health pack, the player regains health
			if(tempObject.getID() == ID.HealthPack)
			{
				
				if(this.getBounds().intersects(tempObject.getBounds()))
				{
					hud.HEALTH += 50;
					hud.tick();
					handler.removeObject(tempObject);
					Spawner.healthnum--;
				}
			}
			
		}
	}
	

	/**************************************************
	 * Method getBounds
	 * 		Returns a rectangle with the size and shape of 
	 * 		the player
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public Rectangle getBounds()
	{
		return new Rectangle(getSize().x, getSize().y, getSize().width, getSize().height);
	}

	/**************************************************
	 * Method shoot
	 * 		Fires a missile
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
		//Checks if the fire cooldown has run out
		if(canFire)
		{
			Projectile p = null;
			
			//Spawns the size and information about the projectile
			Size s = new Size(pheight, pwidth, getSize().x + getSize().width/2 - 4, getSize().y);
			p = new Projectile(firex, firey, s, ID.Projectile, handler, pjimage, firevelx, firevely);
			//Add the projectile to the list of objects
			handler.addObject(p);
			
			//Resets the fire cooldown
			canFire = false;
			fired = true;
		}
		
	}
	/**************************************************
	 * Method shoot
	 * 		Ends the game; creates the game over screen
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
		handler.getObjects().clear();
		//handler.removeObject(this);
	}

	/**************************************************
	 * Method broadcast
	 * 		Static method that can be called anywhere
	 * 		Used to let other objects know where the player is
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		r-Rectangle encompassing the Player's location
	 * 
	 *************************************************/	
	public static Rectangle broadcast()
	{
		return hitbox;
	}
	
}
