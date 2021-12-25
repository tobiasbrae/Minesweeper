public class MouseHandler
{
	private Window CurrentWindow;
	private RenderObject First, Last, Moused;
	private Field[] DownFields;
	private boolean LeftButtonPressed, RightButtonPressed;
	
	public MouseHandler(Window CurrentWindow)
	{
		this.CurrentWindow = CurrentWindow;
	}
	
	private boolean gameOver()
	{
		return CurrentWindow.CurrentMain.gameOver();
	}
	
	public void addRenderObject(RenderObject Input)
	{
		if(First == null)
		{
			First = Input;
			Last = Input;
		}
		else
		{
			Last.After = Input;
			Input.Before = Last;
			Last = Input;
		}
	}
	

	public synchronized void mouseExited()
	{
		if(Moused instanceof Field && Moused.isDown())
		{
			setAllDown(Moused, false);
		}
		Moused = null;
	}
	
	public synchronized void mousePressed(int Button)
	{
		if(Button == Main.LeftButton)
		{
			LeftButtonPressed = true;
			if(Moused != null)
			{
				if(RightButtonPressed)
				{
					if(!gameOver() && Moused instanceof Field)
					{
						Field CurField = getFieldByObject(Moused);
						if(!CurField.isMarked() && CurField.isVisible())
						{
							setAllDown(Moused, true);
						}
					}
				}
				else
				{
					if(Moused instanceof Field)
					{
						if(!gameOver())
						{
							Field CurField = getFieldByObject(Moused);
							if(!CurField.isVisible() && !CurField.isMarked())
							{
								CurField.setDown(true);
							}
						}
					}
					else
					{
						if(Moused != null)
						{
							Moused.setDown(true);
						}
					}
				}
			}
		}
		else if(Button == Main.RightButton)
		{
			RightButtonPressed = true;
			if(Moused != null)
			{
				if(LeftButtonPressed)
				{
					if(Moused instanceof Field)
					{
						if(!gameOver())
						{
							Field CurField = getFieldByObject(Moused);
							if(!CurField.isMarked() || CurField.isVisible())
							{
								setAllDown(Moused, true);
							}
						}
					}
				}
				else
				{
					if(Moused instanceof Field)
					{
						if(!gameOver())
						{
							Field CurField = getFieldByObject(Moused);
							if(CurField.isMarked())
							{
								CurField.setMarked(false);
								CurrentWindow.changeMarkedBombs(false);
							}
							else if(!CurField.isVisible())
							{
								CurField.setMarked(true);
								CurrentWindow.changeMarkedBombs(true);
							}
						}
					}
				}
			}
		}
	}
	
	public synchronized void mouseReleased(int Button)
	{
		if(Button == Main.LeftButton)
		{
			LeftButtonPressed = false;
			if(Moused != null)
			{
				if(RightButtonPressed)
				{
					if(!gameOver() && areAllDown())
					{
						if(Moused.isVisible())
						{
							CurrentWindow.openAllFields(bombCountFitting(), true);
						}
						setAllDown(Moused, false);
					}
				}
				else
				{
					if(Moused != null && Moused.isDown())
					{
						if(Moused instanceof Field)
						{
							Moused.setDown(false);
							Field CurField = getFieldByObject(Moused);
							if(!CurField.isMarked() && !CurField.isVisible())
							{
								CurrentWindow.openField(CurField);
							}
						}
						else if(Moused instanceof Button)
						{
							Moused.setDown(false);
							if(Moused == CurrentWindow.B_Reset)
							{
								CurrentWindow.CurrentMain.resetGame();
							}
						}
						else
						{
							Moused.setDown(false);
						}
					}
				}
			}
		}
		else if(Button == Main.RightButton)
		{
			RightButtonPressed = false;
			if(Moused != null)
			{
				if(LeftButtonPressed)
				{
					if(areAllDown())
					{
						if(Moused.isVisible())
						{
							CurrentWindow.openAllFields(bombCountFitting(), true);
						}
						setAllDown(Moused, false);
					}
				}
				else
				{
					
				}
			}
		}
	}
	
	public void mouseMoved(int MouseX, int MouseY)
	{
		Field CurField = getFieldByMouse(MouseX, MouseY);
		if(CurField == null)
		{
			RenderObject CurObject = First;
			while(CurObject != null)
			{
				Moused = CurObject;
				if(CurObject.isMouseOver(MouseX, MouseY))
				{
					break;
				}
				CurObject = CurObject.After;
			}
			Moused = CurObject;
		}
		else
		{
			Moused = CurField;
		}
	}
	
	public void mouseDragged(int MouseX, int MouseY)
	{
	
	}
	
	private Field getFieldByMouse(int MouseX, int MouseY)
	{
		int X = (MouseX - Main.XOffset) / Main.FieldSize;
		int Y = (MouseY - Main.YOffset - Main.GameOffset) / Main.FieldSize;
		
		Field CurField = CurrentWindow.getField(X, Y);
		
		if(CurField != null && CurField.isMouseOver(MouseX, MouseY))
		{
			return CurField;
		}
		else
		{
			return null;
		}
	}
	
	private Field getFieldByObject(RenderObject CurObject)
	{
		int X = CurObject.getX();
		int Y = CurObject.getY();
		
		return CurrentWindow.getField(X, Y);
	}
	
	private boolean areAllDown()
	{
		if(DownFields == null)
		{
			return false;
		}
		for(int i = 0; i < 9; i++)
		{
			if(DownFields[i] != null)
			{
				return true;
			}
		}
		return false;
	}
	
	private Field bombCountFitting()
	{
		if(Moused instanceof Field)
		{
			Field Output = null;
			int BombCounter = 0, Bombs = 0;
			for(int i = 0; i < 9; i++)
			{
				if(DownFields[i] == Moused)
				{
					Bombs = DownFields[i].getBombs();
					Output = DownFields[i];
				}
				else if(DownFields[i] != null && DownFields[i].isMarked())
				{
					BombCounter++;
					System.out.println("Bombe " + DownFields[i].getX() + " " + DownFields[i].getY());
				}
			}
			System.out.println("----------------------------");
			if(Bombs == BombCounter)
			{
				return Output;
			}
		}
		return null;
	}
	
	private void setAllDown(RenderObject Center, boolean Down)
	{
		if(Center instanceof Field)
		{
			if(Down)
			{
				DownFields = new Field[9];
			}
			
			if(DownFields != null)
			{
				Field CenterField = getFieldByObject(Center);
				int Counter = 0;
				int X = CenterField.getX();
				int Y = CenterField.getY();
				for(int i = -1; i < 2; i++)
				{
					for(int j = -1; j < 2; j++)
					{
						DownFields[Counter] = CurrentWindow.getField(X + i, Y + j);
						Counter++;
					}
				}
				for(int i = 0; i < 9; i++)
				{
					Field CurField = DownFields[i];
					if(CurField != null && !CurField.isMarked() && !CurField.isVisible())
					{
						CurField.setDown(Down);
					}
				}
			}
			if(!Down)
			{
				DownFields = null;
			}
		}
	}
}