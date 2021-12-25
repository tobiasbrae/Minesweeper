import java.awt.Graphics;
import java.awt.Color;

public class Button extends RenderObject
{
	private int DrawingID;
	
	public Button(int PosX, int PosY, int SizeX, int SizeY, int DrawingID)
	{
		super(PosX, PosY, SizeX, SizeY);
		this.DrawingID = DrawingID;
	}
	
	public void setDrawingID(int DrawingID)
	{
		this.DrawingID = DrawingID;
	}
	
	public void paint(Graphics g)
	{
		if(Down)
		{
			g.setColor(Color.black);
			g.drawRect(PosX, PosY, SizeX, SizeY);
			g.setColor(Color.gray);
			g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
		}
		else
		{
			g.setColor(Color.black);
			g.drawRect(PosX, PosY, SizeX, SizeY);
			g.setColor(Color.green);
			g.fillRect(PosX + 1, PosY + 1, SizeX - 2, SizeY - 2);
		}
	}
}