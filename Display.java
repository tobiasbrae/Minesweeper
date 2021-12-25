import java.awt.Graphics;
import java.awt.Color;

public class Display extends RenderObject
{
	private String Text;
	
	public Display(int PosX, int PosY, int SizeX, int SizeY, String Text)
	{
		super(PosX, PosY, SizeX, SizeY);
		this.Text = Text;
	}
	
	public void setText(String Text)
	{
		this.Text = Text;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(PosX, PosY, SizeX, SizeY);
		g.setColor(Color.red);
		g.drawString(Text, PosX + 5, PosY + SizeY - 3);
	}
}