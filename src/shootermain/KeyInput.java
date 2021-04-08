package shootermain;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter{

	/*
	 * !!NOTE!!
	 * 	
	 * 	This is not my code. I used a tutorial for this.
	 * 	I had no idea how to make Java respond to key inputs,
	 * 	and now thanks to the tutorial I used I do now.
	 * 
	 * 	Credit goes to RealTutsGML for this code
	 * 
	 * 
	 */
	private Handler handler;
	private Player player;
	
	/**************************************************
	 * Constructor 
	 * 		Initializes the object handler
	 * 
	 * Parameters:
	 * 		h(Handler)-GameObject handler
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public KeyInput(Handler h, Player p)
	{
		handler = h;
		player = p;
	}
	
	/**************************************************
	 * Method keyPressed
	 * 		Controls a game object based on what key is pressed
	 * 
	 * Parameters:
	 * 		e(KeyEvent): pressed key
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void keyPressed(KeyEvent e)		//Key that is pressed
	{
		int key = e.getKeyCode();
		
		
		//Moves the player
		//Makes movement fluid
		if(key == KeyEvent.VK_W)
		{
			player.facing = 0;
			player.setVelY(player.getVelY()-5);
		}
		else if(key == KeyEvent.VK_A)
		{
			player.facing = 3;
			player.setVelX(player.getVelX()-5);
		}
		else if(key == KeyEvent.VK_S)
		{
			player.facing = 2;
			player.setVelY(player.getVelY()+5);
		}
		else if(key == KeyEvent.VK_D)
		{
			player.facing = 1;
			player.setVelX(player.getVelX()+5);
		}
		else if(key == KeyEvent.VK_SPACE)
		{
			player.shoot();
		}
		
		player.setVelX(Game.clamp(player.getVelX(), -5, 5));
		player.setVelY(Game.clamp(player.getVelY(), -5, 5));
		
	}
	
	/**************************************************
	 * Method keyReleased
	 * 		Responsible for the behavior of objects after a key is released
	 * 
	 * Parameters:
	 * 		e(KeyEvent): released key
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		
		//Stops the player from moving
		//Keeps the player from sliding slightly after movement is stopped,
		//also makes movement more fluid
		if(key == KeyEvent.VK_W)
		{
			player.setVelY(player.getVelY()+5);
		}
		else if(key == KeyEvent.VK_A)
		{
			player.setVelX(player.getVelX()+5);
		}
		else if(key == KeyEvent.VK_S)
		{
			player.setVelY(player.getVelY()-5);
		}
		else if(key == KeyEvent.VK_D)
		{
			player.setVelX(player.getVelX()-5);
		}
		
		player.setVelX(Game.clamp(player.getVelX(), -5, 5));
		player.setVelY(Game.clamp(player.getVelY(), -5, 5));

	}
}
