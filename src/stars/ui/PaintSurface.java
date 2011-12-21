package stars.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author Bryant Martin
 *
 */
@SuppressWarnings("serial")
public class PaintSurface extends JPanel {
    protected ArrayList<IDrawable> _items = new ArrayList<IDrawable>();
    protected double _scale = 0.015,
                     _xOffset = 0,
                     _yOffset = 0;

    public void setScale(double scale) { _scale = scale; }
    
    /**
     * Add a drawable object to the paint surface.
     * 
     * @param item
     */
    public void addItem(IDrawable item) {
        _items.add(item);
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
    	
    	g.scale(_scale, _scale);
    	
        for (int x = 0; x < _items.size(); x++) {
            //(_items.get(x)).draw(g, _scale);
        	(_items.get(x)).draw(g);
        }
    }
}
