package shootermain;


import java.util.*;
import java.awt.*;


/*
 * !!NOTE!!
 * 	
 * 	This is not my code. While the ideas presented
 * 	in this class are relatively simple, I would
 * 	not have thought about it unless I saw a tutorial
 * 
 * 	Credit goes to RealTutsGML for this code
 * 
 * 
 */


/**************************************************
 * Handler Class
 * 		Stores all game objects and is responsible 
 * 		for updating each one
 * 
 * Accessors:
 * 		none
 * 
 * Mutators:
 * 		tick()
 * 		render(Graphics g)
 * 		addObject(GameObject object)
 * 		removeObject(GameObject object)
 * 
 *
 *************************************************/
public class Handler {
	
	//LinkedList of GameObjects
	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	/********************
	 *     MUTATORS		*
	 *******************/
	
	/**************************************************
	 * Method tick
	 * 		Calls the tick() method for every GameObject
	 * 		used
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
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).tick();
		}
		
		
	}
	
	/**************************************************
	 * Method render
	 * 		Calls the render() method for every GameObject
	 * 		used
	 * 
	 * Parameters:
	 * 		g - Graphics object
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void render(Graphics g)
	{
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).render(g);
		}
		
	}
	
	/**************************************************
	 * Method addObject
	 * 		Adds a GameObject to the LinkedList of GameObjects
	 * 
	 * Parameters:
	 * 		object - GameObject to add
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void addObject(GameObject object)	//GameObject to add
	{
		objects.add(object);
	}
	
	/**************************************************
	 * Method removeObject
	 * 		Removes a GameObject from the LinkedList of GameObjects
	 * 
	 * Parameters:
	 * 		object - GameObject to remove
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void removeObject(GameObject object) 
	{
		objects.remove(object);
	}
	
	/**************************************************
	 * Method getObjects
	 * 		Returns the LinkedList of objects
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		objects - LinkedList of GameObjects
	 * 
	 *************************************************/
	public LinkedList<GameObject> getObjects()
	{
		return objects;
	}
}
