import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class UserInput implements MouseListener, MouseMotionListener
{
	private MouseHandler MHandler;
	
	public UserInput(MouseHandler MHandler)
	{
		this.MHandler = MHandler;
	}
	
	public void mouseClicked(MouseEvent e)
	{
	
	}
	
	public void mouseEntered(MouseEvent e)
	{
	
	}
	
	public void mouseExited(MouseEvent e)
	{
		MHandler.mouseExited();
	}
	
	public void mousePressed(MouseEvent e)
	{
		if(e.getButton()  == MouseEvent.BUTTON1)
		{
			MHandler.mousePressed(Main.LeftButton);
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			MHandler.mousePressed(Main.RightButton);
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(e.getButton()  == MouseEvent.BUTTON1)
		{
			MHandler.mouseReleased(Main.LeftButton);
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			MHandler.mouseReleased(Main.RightButton);
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		MHandler.mouseDragged(e.getXOnScreen(), e.getYOnScreen());
	}
	
	public void mouseMoved(MouseEvent e)
	{
		MHandler.mouseMoved(e.getXOnScreen(), e.getYOnScreen());
	}
}