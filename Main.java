public class Main
{
	public static int FieldsX = 16;
	public static int FieldsY = 16;
	public static int Bombs = 40;
	public static int FieldSize = 20;
	public static int LeftButton = 1;
	public static int MiddleButton = 2;
	public static int RightButton = 3;
	public static int XOffset = 10;
	public static int YOffset = 32;
	public static int GameOffset = 30;
	
	private boolean GameStarted, GameOver;
	
	private Window CurrentWindow;	
	
	public static void main(String[] args)
	{
		Main main = new Main();
	}
	
	public Main()
	{
		CurrentWindow = new Window(this);
	}
	
	public void resetGame()
	{
		CurrentWindow.resetGame();
		GameOver = false;
		GameStarted = false;
	}
	
	public boolean gameStarted()
	{
		return GameStarted;
	}
	
	public boolean gameOver()
	{
		return GameOver;
	}
	
	public void startGame(Field CurField)
	{
		if(!GameStarted)
		{
			GameStarted = true;
			CurrentWindow.setBombs(CurField);
		}
	}
	
	public void finishGame(boolean Winner)
	{
		GameOver = true;
		if(Winner)
		{
			System.out.println("Du hast gewonnen.");
		}
		else
		{
			System.out.println("Du hast verloren.");
			CurrentWindow.uncoverBombs();
		}
		CurrentWindow.setGameOver(Winner);
	}
}