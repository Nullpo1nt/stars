package stars;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UniversePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    protected DrawableUniverse _universe;

    
    public UniversePanel(DrawableUniverse universe) {
    	_universe = universe;
    	addMouseListener(this);
    	addMouseMotionListener(this);
    	addMouseWheelListener(this);
    }
    
   
    /**
     * Since we're always going to fill our entire
     * bounds, allow Swing to optimize painting of us 
     */
    public boolean isOpaque() {
        return true;
    }

    /**
     * All components of the surface using the provided 
     * graphics object.
     */
    protected void paintComponent(Graphics graphics) {
    	Graphics2D g = (Graphics2D) graphics;

    	_universe.draw(g, getWidth(), getHeight());
    }


	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0);
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0);
	}


	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e);
		if(e.getWheelRotation() == 1) {
			_universe.setScale(_universe.getScale() * 2);
		} else {
			_universe.setScale(_universe.getScale() / 2);
		}
		
		System.out.println(_universe.getScale());
		//System.out.println("" + (_universe.getScale() + (e.getWheelRotation() * e.getClickCount() * 0.005)));
		//_universe.setScale(_universe.getScale() + (e.getWheelRotation() * e.getClickCount() * 0.0005));
		
		repaint();
	}


	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0);
	}


	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
