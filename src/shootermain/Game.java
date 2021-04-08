package shootermain;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/*
 * !!NOTE!!
 * 	
 * 	This is not my code. I used a tutorial for this.
 * 	I understand all the ideas presented in this code, but I never would 
 * 	thought how to do this unless I saw a tutorial. There are additional 
 * 	embellishments that are mine, but the basic concepts, ideas, and much 
 * 	the code is taken from a tutorial
 * 
 * 	Credit goes to RealTutsGML for this code
 * 
 * 
 */


public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 2332353431275813628L;
	
	//The ratio here between the width and height gives a 16:9 aspect ratio
	public static final int WIDTH = 640, 				//Width of the window
							HEIGHT = WIDTH / 12 * 9;	//Height of the window
	
	public static final int PLAYWIDTH = WIDTH;				//Width available to the objects
	public static final int PLAYHEIGHT= HEIGHT - HEIGHT/4;	//Height available to the objects
	
	private boolean running = false;		//Boolean value to check if the game is running

	private Thread thread;					//Thread to handle the running game
	private Handler handler;				//Handler that stores all game objects
	
	private BufferedImage Player_Tank = null;
	private BufferedImage BasicEnemy_Tank = null;
	private BufferedImage Background = null;
	
	Spawner spawn;
	
	
	Random r;
	
	
	public static void main(String[] args) 
	{
		new Game();
	}
	
	
	/**************************************************
	 * Constructor (default constructor) 
	 * 		Creates a Game
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public Game() 
	{
		handler = new Handler();
		r = new Random();
		spawn = new Spawner(handler);
		new Window(HEIGHT, WIDTH, "New Game", this);
		
		try {
			Player_Tank = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Player tank ufacing.png"));
			BasicEnemy_Tank = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/BasicEnemy tank dfacing.png"));
			Background = ImageIO.read(getClass().getResource("/Game pictures/Test background.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Size pSize = new Size(28,28, PLAYWIDTH/2, (HEIGHT - HEIGHT/4) - 32);
		Player p1 = new Player(PLAYWIDTH/2, (HEIGHT - HEIGHT/4) - 32, pSize, ID.Player,  handler, Player_Tank);
		Size eSize = new Size(32,28, PLAYWIDTH/2,PLAYHEIGHT/2);
		BasicEnemy e = new BasicEnemy(PLAYWIDTH/2 - 4, PLAYHEIGHT/2 - 2, eSize, ID.BasicEnemy, handler, BasicEnemy_Tank);
		
		this.addKeyListener(new KeyInput(handler,p1));
		spawn.Spawn(p1);
		spawn.Spawn(e);	
	}
	
	
	/**************************************************
	 * Method clamp
	 * 		Sets the boundaries for where an object
	 * 		can go
	 * 
	 * Parameters:
	 * 		current(int): object's current position
	 * 		min(int): object's minimum position
	 * 		max(int): object's maximum position
	 * 
	 * Return:
	 * 		int
	 * 
	 *************************************************/
	public static int clamp(int current, 	//Object's current position
							int min, 	 	//Object's minimum position
							int max)		//Object's maximum position
	{
		//If the current position is greater than the maximum position, then return the maximum position
		if(current > max)
			return current = max;
		//If the current position is smaller than the maximum position, then return the minimum position
		else if(current < min)
			return current = min;
		//Otherwise, return the current position
		else
			return current;
	}
	
	
	/**************************************************
	 * Method start
	 * 		Creates a thread and starts the game
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public synchronized void start()
	{
		running = true;				//Alerts the computer that the game is running
		thread = new Thread(this);	//Creates a new thread
		thread.start();				//Calling the run method for the thread					
	}
	
	/**************************************************
	 * Method stop
	 * 		Stops all threads from running
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**************************************************
	 * Method run
	 * 		Overridden run method from Thread
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	
	/*!This is not my code. All credit goes to Notch for the game loop.
	 * This code was just on the Internet and it worked really well!*/
	public void run() 
	{
		this.requestFocus();
	    long lastTime = System.nanoTime(); 		// get current time to the nanosecond
		double amountOfTicks = 60.0; 				// set the number of ticks 
		double ns = 1000000000 / amountOfTicks; 	// this determines how many times we can divide 60 into 1e9 of nanoseconds or about 1 second
		double delta = 0; 						// change in time 
		long timer = System.currentTimeMillis(); 	// get current time
		int frames = 0; 							// set frame variable
		while(running)
		{ 
			long now = System.nanoTime(); 	// get current time in nanoseconds during current loop
			delta += (now - lastTime) / ns; 	// add the amount of change since the last loop
			lastTime = now;  					// set lastTime to now to prepare for next loop
			while(delta >= 1)
			{
				  // One tick of time has passed in the game this 
				  // ensures that we have a steady pause in our game loop 
				  // so we don't have a game that runs too quickly. 
				  // Basically we are waiting for  enough time to pass so we 
				  // have about 60 frames per one second interval determined to the nanosecond.
				  // Once this pause is done we render the next frame
				tick();  
				delta--;  // lower our delta back to 0 to start our next frame wait
			}
			if(running)
			{
				render(); // render the visuals of the game
			}
			frames++; // note that a frame has passed
			  
			  //Outputs the frames per second
			if(System.currentTimeMillis() - timer > 1000 ) // if one second has passed
			{ 
				timer+= 1000; 				// add a thousand to our timer for next time
				// print out how many frames have happened in the last second
				//System.out.println("FPS: " + frames); 
				frames = 0; 					// reset the frame count for the next second
			}
		}
		stop(); //Stop the thread
		
	}
	
	/**************************************************
	 * Method tick
	 * 		Updates each GameObject in the handler
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	private void tick()
	{
		handler.tick();	
		spawn.tick();
	}
	
	/**************************************************
	 * Method render
	 * 		Draws each GameObject in the Handler on the screen
	 *
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	private void render()
	{
		
		
		//Creating a graphics buffer to allow for smoother transitions
		BufferStrategy bs = this.getBufferStrategy();
		
		//If the bufferstrategy is uninitialized, then we create a set of 3 buffers to use 
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		//Sets current graphics to the graphics in the buffer
		Graphics g = bs.getDrawGraphics();
		
		
		//Draws a black background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.drawImage(Background, 0, 0, null);
		
		g.setColor(Color.white);
		g.fillRect(0, HEIGHT - HEIGHT/4, WIDTH, 16);
		
		
		//Calls the render method for every object being used
		handler.render(g);

		
		//Clears the memory of the current buffer
		g.dispose();
		//Queues the next buffer to be used in the JFrame
		bs.show();
	}

}
