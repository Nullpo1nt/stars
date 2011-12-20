package stars.galaxy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import stars.ui.DrawableI;
import stars.physics.NewtonObject;
import stars.physics.NewtonObject;
import stars.physics.Vector3;

//public class Star extends NewtonObject implements DrawableI {
public class Star extends NewtonObject implements DrawableI {
    boolean _drawTail = true;
    int _maxTailSize = 250;
    
    LinkedList tail = new LinkedList();
    
    public Star() {  }
    
    public Star(double mass) {
        super(mass);
    }
    
    public Star(double mass, double radius) {
        super(mass, radius);
    }

    public void draw(Graphics g, double scale) {
        int x = (int)(position.x * scale),
            y = (int)(position.y * scale);
        
        if(_drawTail) {
            float colorInc = 128 / (float)_maxTailSize;
            float color = colorInc;
            
            tail.add(new Vector3(position));
            if(tail.size() > _maxTailSize) {
                tail.removeFirst();
            }
            
            Iterator i = tail.iterator();
            Vector3 start = null, 
                    end = null;
            while(i.hasNext()) {
                start = (Vector3)i.next();
                if(end != null) {
                    g.setColor(new Color((int)color, (int)color, (int)color));
                    g.drawLine((int)(start.x * scale), 
                               (int)(start.y * scale),
                               (int)(end.x * scale),
                               (int)(end.y * scale));
                }
                end = start;
                color += colorInc;
            }
        }
        
/*
        g.setColor(Color.GREEN);
        g.drawLine(x,y,x+(int)(velocity.x * scale),y+(int)(velocity.y * scale));
        
        g.setColor(Color.MAGENTA);
        g.drawLine(x,y,x+(int)(accel.x * scale),y+(int)(accel.y * scale));
*/
        
        g.setColor(Color.DARK_GRAY);
        int radius = (int) (_radius * scale);
        int diameter = radius * 2;
        g.drawOval(x - radius, y - radius, diameter, diameter);
        
        g.setColor(Color.WHITE);
        g.drawLine(x,y,x,y);
        
        
        
    }
    
    public String toString() {
        return "Star("+super.toString()+")";
    }
}
