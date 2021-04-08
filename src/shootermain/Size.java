package shootermain;

public class Size {

	public int x;		//X location of the object
	public int y;		//Y location of the object 
	public int height;	//Distance the object extends downward
	public int width;	//Distance the object extends rightward
	
	/**************************************************
	 * Constructor (default constructor) 
	 * 		Creates a Size
	 * 
	 * Parameters:
	 * 		none
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public Size()
	{
		x = y = height = width = 0;
	}
	
	/**************************************************
	 * Constructor 
	 * 		Creates a Size
	 * 
	 * Parameters:
	 * 		height(int) - distance the object extends downward
	 * 		width(int) - distance the object extends rightward
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public Size(int height, 	//Distance that the object takes up below it
			int width)			//Distance that the object takes up right of it
	{
		this.height = height;
		this.width = width;
		//Default position is at 0,0
		x = y = 0;
	}
	
	/**************************************************
	 * Constructor 
	 * 		Creates a Size
	 * 
	 * Parameters:
	 * 		height(int) - distance the object extends downward
	 * 		width(int) 	- distance the object extends rightward
	 * 		x(int) 		- x location of the object
	 * 		y(int) 		- y location of the object 
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public Size(int height, int width, int x, int y)
	{
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
	
	/**************************************************
	 * Method toString
	 * 		Prints out the information of the string
	 * 
	 * Parameters:
	 * 		e(KeyEvent): pressed key
	 * 
	 * Return:
	 * 		none
	 * 
	 *************************************************/
	public String toString()
	{
		String temp = "X: ";
		temp += x + ", ";
		temp += "Y: ";
		temp += y + ", ";
		temp += "\n";
		temp += "Height: " + height + ", ";
		temp += "Width: " + width;
		
		return temp;
	}
		
}