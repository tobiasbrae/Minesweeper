import javax.swing.JFrame;

public class Window extends JFrame
{
	public Main CurrentMain;
	public int SizeX, SizeY, FieldsX, FieldsY;
	private UserInput UInput;
	private MouseHandler MHandler;
	public Renderer CurrentRenderer;
	private Field[][] Fields;
	private int Bombs;
	public Button B_Easy, B_Middle, B_Hard, B_Reset;
	private Display D_Bombs, D_Time;
	
	
	public Window(Main CurrentMain)
	{
		super("Minesweeper");
		
		this.CurrentMain = CurrentMain;
		
		SizeX = Main.FieldsX * Main.FieldSize + Main.XOffset + 10;
		SizeY = Main.FieldsY * Main.FieldSize + Main.YOffset + Main.GameOffset + 10;
		this.FieldsX = Main.FieldsX;
		this.FieldsY = Main.FieldsY;
		
		setSize(SizeX, SizeY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		MHandler = new MouseHandler(this);
		
		UInput = new UserInput(MHandler);
		addMouseListener(UInput);
		addMouseMotionListener(UInput);
		
		CurrentRenderer = new Renderer(this);
		CurrentRenderer.start();
		
		Fields = new Field[FieldsX][FieldsY];
		for(int i = 0; i < FieldsX; i++)
		{
			for(int j = 0; j < FieldsY; j++)
			{
				Fields[i][j] = new Field(i * Main.FieldSize, j * Main.FieldSize + Main.GameOffset);
				Fields[i][j].setXY(i, j);
				CurrentRenderer.addRenderObject(Fields[i][j]);
			}
		}
		
		B_Reset = new Button(5, 5, 20, 20, 1);
		MHandler.addRenderObject(B_Reset);
		CurrentRenderer.addRenderObject(B_Reset);
		
		D_Bombs = new Display(30, 5, 30, 15, Main.Bombs + "");
		CurrentRenderer.addRenderObject(D_Bombs);
	}
	
	public void resetGame()
	{
		Bombs = Main.Bombs;
		D_Bombs.setText(Bombs + "");
		for(int i = 0; i < FieldsX; i++)
		{
			for(int j = 0; j < FieldsY; j++)
			{
				Fields[i][j].setVisible(false);
				Fields[i][j].setMarked(false);
				Fields[i][j].setBomb(false);
				Fields[i][j].setBombs(0);
				Fields[i][j].setDown(false);
			}
		}
	}
	
	public Field getField(int X, int Y)
	{
		if(X < 0 || X >= FieldsX || Y < 0 || Y >= FieldsY)
		{
			return null;
		}
		return Fields[X][Y];
	}
	
	private int[] getRandomField()
	{
		int[] Output = new int[2];
		double Rand = Math.random() * FieldsX;
		Output[0] = (int) Rand;
		Rand = Math.random() * FieldsY;
		Output[1] = (int) Rand;
		return Output;
	}
	
	public void setBombs(Field CurField)
	{
		for(int i = 0; i < Main.Bombs; i++)
		{
			int[] RandField = getRandomField();
			while(Fields[RandField[0]][RandField[1]].isBomb() || Fields[RandField[0]][RandField[1]] == CurField)
			{
				RandField = getRandomField();
			}
			Fields[RandField[0]][RandField[1]].setBomb(true);
		}
		for(int i = 0; i < FieldsX; i++)
		{
			for(int j = 0; j < FieldsY; j++)
			{
				int Bombs = 0;
				for(int x = -1; x < 2; x++)
				{
					for(int y = -1; y < 2; y++)
					{
						if(i+x >= 0 && i+x < FieldsX && j+y >= 0 && j+y < FieldsY)
						{
							if(Fields[i+x][j+y].isBomb())
							{
								Bombs++;
							}
						}
					}
				}
				Fields[i][j].setBombs(Bombs);
			}
		}
	}
	
	public void openAllFields(Field Input, boolean withBombs)
	{
		if(Input instanceof Field)
		{
			int i = Input.getX();
			int j = Input.getY();
			
			for(int x = -1; x < 2; x++)
			{
				for(int y = -1; y < 2; y++)
				{
					if(i+x >= 0 && i+x < FieldsX && j+y >= 0 && j+y < FieldsY)
					{
						Field CurField = Fields[i+x][j+y];
						if(CurField != Input && !CurField.isVisible() && !CurField.isMarked() && ((withBombs && !CurField.isMarked()) || !CurField.isBomb()))
						{
							openField(Fields[i+x][j+y]);
						}
					}
				}
			}
		}
	}
	
	public void uncoverBombs()
	{
		for(int i = 0; i < FieldsX; i++)
		{
			for(int j = 0; j < FieldsY; j++)
			{
				if(Fields[i][j].isBomb())
				{
					Fields[i][j].setVisible(true);
				}
			}
		}
	}
	
	public void openField(Field CurField)
	{
		if(!CurrentMain.gameStarted())
		{
			CurrentMain.startGame(CurField);
		}
		
		CurField.setVisible(true);
		if(CurField.isBomb())
		{
			CurrentMain.finishGame(false);
		}
		else if(noBombsLeft())
		{
			CurrentMain.finishGame(true);
		}
		else
		{
			if(CurField.getBombs() == 0)
			{
				openAllFields(CurField, false);
			}
		}
	}
	
	private boolean noBombsLeft()
	{
		for(int i = 0; i < FieldsX; i++)
		{
			for(int j = 0; j < FieldsY; j++)
			{
				Field CurField = Fields[i][j];
				if(!CurField.isVisible() && !CurField.isBomb())
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void changeMarkedBombs(boolean Lower)
	{
		if(Lower)
		{
			Bombs--;
		}
		else
		{
			Bombs++;
		}
		D_Bombs.setText(Bombs + "");
	}
	
	public void setGameOver(boolean Winner)
	{
		if(Winner)
		{
			D_Bombs.setText("0");
		}
		else
		{
		
		}
	}
}