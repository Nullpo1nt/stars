package stars.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 * 
 * 
 * @author Bryant Martin
 *
 */
public class PaintSurface extends JPanel {
    protected ArrayList _items = new ArrayList();
    protected double _scale = 1,
                     _xOffset = 0,
                     _yOffset = 0;

    public void setScale(double scale) { _scale = scale; }
    
    /**
     * Add a drawable object to the paint surface.
     * 
     * @param item
     */
    public void addItem(DrawableI item) {
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
    protected void paintComponent(Graphics g) {
        for(int x = 0; x < _items.size(); x++) {
            ((DrawableI)_items.get(x)).draw(g, _scale);
        }
    }
}
