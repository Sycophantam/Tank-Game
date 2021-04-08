package shootermain;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Spawner {

	private Handler handler;	//Data of all objects in the game
	private int BEcoef, 		//Chances of spawning a BasicEnemy tank
				GTcoef, 		//Chances of spawning a Green tank
				FTcoef,			//Chances of spawning a Fast tank
				ATcoef, 		//Chances of spawning a Armored tank
				healthcoef,		//Trigger to spawn a health pack
				spawncooldown, 	//Time it takes to spawn a tank
				timecoef = 0;	//Trigger to spawn a tank
	
	public static int healthnum = 0;
	
	//Random number that determines where the new object goes
	private Random r = new Random(System.currentTimeMillis());
	
	private int x,y = 0;	//Location of the new tank
	
	/**************************************************
	 * Spawner Class
	 * 		Responsible for spawning objects
	 * 
	 * Accessors:
	 * 		none
	 * 
	 * Mutators:
	 * 		tick()
	 * 		chooseTank()
	 * 		Spawn(GameObject object)
	 * 		chooseLoc()
	 *************************************************/
	public Spawner(Handler handler)
	{
		this.handler = handler;
		timecoef = 500;
	}
	
	public void tick()
	{
		
		//Depending on what score the player has, the game will adjust the percentage of what kind of tank spawns
		//and how often the tanks will spawn
		if(HUD.score < 600)
		{
			//Percentage of each type of tank spawning
			BEcoef = 100;
			GTcoef = 0;
			FTcoef = 0;
			ATcoef = 0;
			
			//The number of ticks it takes to spawn a tank
			timecoef = 400;
			
			//The number of ticks it takes to spawn a health pack
			healthcoef = 450;
		}
		else if(HUD.score < 1000)
		{
			//Percentage of each type of tank spawning
			BEcoef = 49;
			GTcoef = 49;
			FTcoef = 2;
			ATcoef = 0;
			
			//The number of ticks it takes to spawn a tank
			timecoef = 400;
			
			//The number of ticks it takes to spawn a health pack
			healthcoef = 360;
		}
		else if(HUD.score < 2500)
		{
			//Percentage of each type of tank spawning
			BEcoef = 25;
			GTcoef = 30;
			FTcoef = 30;
			ATcoef = 15;
			
			//The number of ticks it takes to spawn a tank
			timecoef = 300;
			
			//The number of ticks it takes to spawn a health pack
			healthcoef = 350;
		}
		else if(HUD.score < 4000)
		{
			//Percentage of each type of tank spawning
			BEcoef = 10;
			GTcoef = 25;
			FTcoef = 50;
			ATcoef = 15;
			
			//The number of ticks it takes to spawn a tank
			timecoef = 200;
			
			//The number of ticks it takes to spawn a health pack
			healthcoef = 400;
		}
		else if(HUD.score < 5000)
		{
			//Percentage of each type of tank spawning
			BEcoef = 4;
			GTcoef = 15;
			FTcoef = 50;
			ATcoef = 31;
			
			//The number of ticks it takes to spawn a tank
			timecoef = 100;
			
			//The number of ticks it takes to spawn a health pack
			healthcoef = 150;
		}
		else if(HUD.score < 6000)
		{
			//Percentage of each type of tank spawning
			BEcoef = 1;
			GTcoef = 9;
			FTcoef = 40;
			ATcoef = 50;
			
			//The number of ticks it takes to spawn a tank
			timecoef = 80;
			
			//The number of ticks it takes to spawn a health pack
			healthcoef = 50;
		}
		//System.out.println(spawncooldown);
		//Spawns a health pack
		if(spawncooldown % healthcoef == 0 && chooseLoc() && healthnum <= 2)
		{
			BufferedImage i = null;
			try {
				i = ImageIO.read(getClass().getResource("/Game pictures/Health pack.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Spawn(new HealthPack(x,y, new Size(18,18), ID.HealthPack, i, handler));
			healthnum++;
		}
		
		
		//If the location is valid and the cooldown for spawning has exhausted, then spawn a new Tank
		if(spawncooldown % timecoef == 0 && chooseLoc())
		{
			spawncooldown = 0;
			
			int newTank = chooseTank();
			
			BufferedImage NewImage = null;
			
			//If newTank is 1, we spawn a BasicEnemy tank
			if(newTank == 1)
			{
				try {
					NewImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/BasicEnemy tank ufacing.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.println("Spawned a BasicEnemy tank");
				Spawn(new BasicEnemy(x,y, new Size(32,32,x,y), ID.BasicEnemy, handler, NewImage));
			}
			
			//If newTank is 2, we spawn a Green tank
			else if(newTank == 2)
			{
				try {
					NewImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Green tank ufacing.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.println("Spawned a Green tank");

				Spawn(new GreenTank(x,y, new Size(32,32,x,y), ID.BasicEnemy, handler, NewImage));
			}
			
			//If newTank is 3, we spawn a Fast tank
			else if(newTank == 3)
			{
				try {
					NewImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Fast tank ufacing.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.println("Spawned a Fast tank");

				Spawn(new FastTank(x,y, new Size(32,32,x,y), ID.BasicEnemy, handler, NewImage));
			}
			
			//If newTank is 4, we spawn an Armored tank
			else if(newTank == 4)
			{
				try {
					NewImage = ImageIO.read(getClass().getResource("/Game pictures/New tank pictures/Armored tank ufacing.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.println("Spawned an Armored tank");

				Spawn(new ArmoredTank(x,y, new Size(32,32,x,y), ID.BasicEnemy, handler, NewImage));
			}
		}
		spawncooldown++;
			
	}
	
	/**************************************************
	 * Method chooseTank
	 * 		Chooses which type of tank to spawn
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		number that corresponds to the type of tank that spawns
	 * 
	 *************************************************/	
	private int chooseTank()
	{
		
		//A random number is chosen
		int temp = r.nextInt(100) + 1;
		
		//System.out.println("We chose: " + temp);
		//Depending on where the random number falls, a different type of 
		//tank will be generated
		if(0 <= temp && temp <= BEcoef)
		{
			//System.out.println("We should be spawning a BasicEnemy Tank");
			return 1;
		}
		else if(BEcoef <= temp && temp < BEcoef + GTcoef)
		{
			//System.out.println("We should be spawning a Green Tank");
			return 2;
		}
			
		else if(BEcoef + GTcoef <= temp && temp < BEcoef + GTcoef + FTcoef)
		{
			//System.out.println("We should be spawning a Fast Tank");
			return 3;
		}
			
		else if(BEcoef + GTcoef + FTcoef <= temp && temp <= BEcoef + GTcoef + FTcoef + ATcoef)
		{
			//System.out.println("We should be spawning an Armored Tank");
			return 4;
		}
			
		
		return 0;
	}
	
	/**************************************************
	 * Method chooseLoc
	 * 		Chooses where the player spawns
	 * 		If there are no available places where an 
	 * 		item can spawn, no item spawns
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		boolean
	 * 
	 *************************************************/	
	private boolean chooseLoc()
	{
		int tempx = r.nextInt(Game.PLAYWIDTH - 50) + 51;
		int tempy = r.nextInt(Game.PLAYHEIGHT - 50) + 51;
		Rectangle r = new Rectangle(tempx, tempy, 32, 32);
		
		//Goes through all objects in the game
		//If the new object overlaps with an older one, it does not spawn
		for(int i = 0; i < handler.getObjects().size(); i++)
		{
			if(r.intersects(handler.getObjects().get(i).getBounds()))
			{
				return false;		
			}
			//If the locations do not intersect, the location is saved and a flag is set to spawn an object
			else
			{
				x = tempx;
				y = tempy;
				return true;
			}
		}
		return false;
	}
	
	/**************************************************
	 * Method Spawn
	 * 		Spawns an object
	 * 		-Simply adds a new object to the handler
	 * 
	 * Parameters:
	 * 		object(GameObject) - new GameObject to spawn
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/	
	public void Spawn(GameObject object)	//GameObject to spawn
	{
		handler.addObject(object);
	}
}
