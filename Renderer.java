import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public class Renderer extends Thread
{
	private RenderObject First, Last;
	private Window CurrentWindow;
	private Image BackBuffer;
	private Graphics BufferGraphics;
	private boolean Run;
	
	public Renderer(Window CurrentWindow)
	{
		setName("Thread_Render");
		this.CurrentWindow = CurrentWindow;
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
	
	public void run()
	{
		Run = true;
		BackBuffer = CurrentWindow.createImage(CurrentWindow.SizeX, CurrentWindow.SizeY);
		BufferGraphics = BackBuffer.getGraphics();
		
		while(Run)
		{			
			BufferGraphics.setColor(Color.white);
			BufferGraphics.fillRect(0, 0, CurrentWindow.SizeX, CurrentWindow.SizeY);
			
			RenderObject CurObject = First;
			while(CurObject != null)
			{
				CurObject.paint(BufferGraphics);
				CurObject = CurObject.After;
			}
			CurrentWindow.getGraphics().drawImage(BackBuffer, Main.XOffset, Main.YOffset, null);
		}
	}
}