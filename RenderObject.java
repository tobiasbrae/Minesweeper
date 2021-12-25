import java.awt.Graphics;

public class RenderObject
{
	public RenderObject Before, After;
	protected int PosX, PosY, SizeX, SizeY, X = -1, Y = -1;
	protected boolean Enabled = true, Visible = true, Down = false;
	
	public RenderObject(int PosX, int PosY, int SizeX, int SizeY)
	{
		this.PosX = PosX;
		this.PosY = PosY;
		this.SizeX = SizeX;
		this.SizeY = SizeY;
	}
	
	public void setXY(int X, int Y)
	{
		this.X = X;
		this.Y = Y;
	}
	
	public int getX()
	{
		return X;
	}
	
	public int getY()
	{
		return Y;
	}
	
	public void setEnabled(boolean Enabled)
	{
		this.Enabled = Enabled;
	}
	
	public boolean isEnabled()
	{
		return Enabled;
	}
	
	public void setVisible(boolean Visible)
	{
		this.Visible = Visible;
	}
	
	public boolean isVisible()
	{
		return Visible;
	}
	
	public void setDown(boolean Down)
	{
		this.Down = Down;
	}
	
	public boolean isDown()
	{
		return Down;
	}
	
	public boolean isMouseOver(int MouseX, int MouseY)
	{
		MouseX -= Main.XOffset;
		MouseY -= Main.YOffset;
		
		if(MouseX > PosX && MouseX < PosX + SizeX && MouseY > PosY && MouseY < PosY + SizeY)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void paint(Graphics g)
	{
	
	}
}