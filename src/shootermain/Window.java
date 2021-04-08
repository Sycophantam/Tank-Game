package shootermain;

import java.awt.*;
import javax.swing.*;

public class Window extends Canvas
{

	private static final long serialVersionUID = 7851898602738266934L;

	public Window(int height, 	//Height of the window
				  int width, 	//Width of the window
				  String title, //Title of the window	
				  Game game)	//Game that the window displays
	{
		JFrame frame = new JFrame(title);
		
		
		//Setting the size of the window
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	
}
