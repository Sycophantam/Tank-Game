package shootermain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public double HEALTH = 100;		//Player's health
	public static int score = 0;	//Player's score	
	public void render(Graphics g)
	{		
		//Drawing the health bar and information
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.setColor(Color.red);
		g.drawString("HEALTH: ", Game.WIDTH/3 - 40, Game.HEIGHT * 7/8);
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH/2, Game.HEIGHT * 13/16, 200, 32);
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH/2, Game.HEIGHT * 13/16, (int)HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2, Game.HEIGHT * 13/16, 200, 32);
		
	}
	public void tick()
	{
		HEALTH = Game.clamp((int)HEALTH, 0, 100);
	}
}