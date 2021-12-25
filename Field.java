import java.awt.Graphics;
import java.awt.Color;

public class Field extends RenderObject
{
	private boolean Bomb, Marked;
	private int Bombs;
	
	public Field(int PosX, int PosY)
	{
		super(PosX, PosY, Main.FieldSize, Main.FieldSize);
		setVisible(false);
	}
	
	public void setBombs(int Bombs)
	{
		this.Bombs = Bombs;
	}
	
	public int getBombs()
	{
		return Bombs;
	}
	
	public void setBomb(boolean Bomb)
	{
		this.Bomb = Bomb;
	}
	
	public boolean isBomb()
	{
		return Bomb;
	}
	
	public void setMarked(boolean Marked)
	{
		this.Marked = Marked;
	}
	
	public boolean isMarked()
	{
		if(Visible)
		{
			return false;
		}
		return Marked;
	}
	
	public void paint(Graphics g)
	{
		if(isVisible())
		{
			if(Marked)
			{
				g.setColor(Color.black);
				g.drawRect(PosX, PosY, SizeX, SizeY);
				g.setColor(Color.blue);
				g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
			}
			else if(Bomb)
			{
				g.setColor(Color.black);
				g.drawRect(PosX, PosY, SizeX, SizeY);
				g.setColor(Color.red);
				g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
			}
			else if(Bombs == 0)
			{
				g.setColor(Color.black);
				g.drawRect(PosX, PosY, SizeX, SizeY);
				g.setColor(Color.yellow);
				g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
			}
			else
			{
				g.setColor(Color.black);
				g.drawRect(PosX, PosY, SizeX, SizeY);
				//g.setColor(Color.green);
				g.drawString(Bombs + "", PosX + 8, PosY + 12);
			}
		}
		else
		{
			if(Down)
			{
				g.setColor(Color.black);
				g.drawRect(PosX, PosY, SizeX, SizeY);
				g.setColor(Color.black);
				g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
			}
			else if(Marked)
			{
				g.setColor(Color.black);
				g.drawRect(PosX, PosY, SizeX, SizeY);
				g.setColor(Color.blue);
				g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
			}
			else
			{
				g.setColor(Color.black);
				g.drawRect(PosX, PosY, SizeX, SizeY);
				g.setColor(Color.gray);
				g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
			}
		}
	}
}