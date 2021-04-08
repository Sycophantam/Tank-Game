package shootermain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * !!NOTE!!

 * 	
 * 	While most of this code is mine, the ideas presented
 * 	in the tick, render, and getBounds methods you will find in other classes are not.
 * 	This is mostly a new manifestation of ideas that I've never
 * 	seen before.
 * 
 * 	Credit goes to RealTutsGML for this code
 * 
 * 
 */


/**************************************************
 * GameObject Class
 * 		Playable game object
 * 
 * Accessors:
 * 		getX
 * 		getY
 * 		getID
 * 
 * Mutators:
 * 		setX
 * 		setY
 * 		setID
 *************************************************/
public abstract class GameObject
{
	private int x, y;		//x and y positions of the object
	private ID id;			//Identification number of the object
	private int velX, velY;	//x and y velocities of the object
	private Size sz;		//Size of the object
	public BufferedImage image;		//Image of the object
	public BufferedImage pjimage;	//Image of the projectile it shoots
	
	public int facing = 0;			//Direction the object is facing: 
									//Up: 0, Right: 1, Down: 2, Left: 3
	
	public int firex, 			//X position where the projectile spawns
			   firey, 			//Y position where the projectile spawns
			   firevelx, 		//Projectile's X velocity
			   firevely;		//Projectile's Y velocity
	
	
	/**************************************************
	 * Constructor (default constructor) 
	 * 		Creates a GameObject
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
	public GameObject(int x, 			//x position
					  int y, 			//y position
					  Size sz,			//Size of the object
			  		  ID id,			//GameObject's ID
			  		  BufferedImage i)	//Image of the object
	{
		this.x = x;
		this.y = y;
		this.sz = sz;
		this.id = id;
		image = i;
		
		
	}
	
	//Method that updates the status of the GameObject
	public abstract void tick();
	
	//Method that redraws the GameObject
	public abstract void render(Graphics g);

	//Returns the distance where the GameObject is
	public abstract Rectangle getBounds();
	
	
	/********************
	 *     ACCESSORS	*
	 *******************/
	
	/**************************************************
	 * Method getX
	 * 		Returns the x location of the object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		x(int)
	 * 
	 *************************************************/
	public int getX()
	{
		return x;
	}
	
	/**************************************************
	 * Method getY
	 * 		Returns the y location of the object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		y(int)
	 * 
	 *************************************************/
	public int getY()
	{
		return y;
	}
	
	/**************************************************
	 * Method getID
	 * 		Returns the id of the object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		id(ID)
	 * 
	 *************************************************/
	public ID getID()
	{
		return id;
	}
	
	/**************************************************
	 * Method getVelX
	 * 		Returns the x velocity of the object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		velX(int)
	 * 
	 *************************************************/
	public int getVelX()
	{
		return velX;
	}
	
	/**************************************************
	 * Method getVelY
	 * 		Returns the y velocity of the object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		velY(int)
	 * 
	 *************************************************/
	public int getVelY()
	{
		return velY;
	}
	
	/**************************************************
	 * Method getSize
	 * 		Returns the size of the object
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		sz(Size)
	 * 
	 *************************************************/
	public Size getSize()
	{
		return sz;
	}
	
	/********************
	 *     MUTATORS		*
	 *******************/
	
	
	/**************************************************
	 * Method setX
	 * 		Sets the x position of the object
	 * 
	 * Parameters:
	 * 		newX(int)
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void setX(int newX)
	{
		x = newX;
	}
	
	/**************************************************
	 * Method setY
	 * 		Sets the y position of the object
	 * 
	 * Parameters:
	 * 		newY(int)
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void setY(int newY)
	{
		y = newY;
	}
	
	/**************************************************
	 * Method setID
	 * 		Sets the id of the object
	 * 
	 * Parameters:
	 * 		newid(ID)
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void setID(ID newid)
	{
		id = newid;
	}
	
	/**************************************************
	 * Method setVelX
	 * 		Sets the x velocity of the object
	 * 
	 * Parameters:
	 * 		velX(int)
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void setVelX(int velX)
	{
		this.velX = velX;
	}
	
	/**************************************************
	 * Method setVelY
	 * 		Sets the y velocity of the object
	 * 
	 * Parameters:
	 * 		velY(int)
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void setVelY(int velY)
	{
		this.velY = velY;
	}

	/**************************************************
	 * Method setVelY
	 * 		Sets the size of the object
	 * 
	 * Parameters:
	 * 		s(Size)
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void setSize(Size s)
	{
		sz = s;
	}

}
