package stars.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.AbstractCollection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import stars.physics.Vector1x3;
import stars.physics.particles.IParticle;
import stars.physics.particles.TestParticle;

public class DrawableParticle {
    boolean _drawTail = false, 
            _drawVelocity = false, 
            _drawAccel = true,
            _drawRadius = true;
    
    int _maxTailSize = 100;

    Hashtable<IParticle,LinkedList<Vector1x3>> particleReg = new Hashtable<IParticle, LinkedList<Vector1x3>>();
    

    public DrawableParticle() {
    }

    protected LinkedList<Vector1x3> getTail(IParticle particle) {
        LinkedList<Vector1x3> tail = particleReg.get(particle);

        if (tail == null) {
            tail = new LinkedList<>();
            particleReg.put(particle, tail);
        }

        return tail;
    }
    
    int _width;
    int _height;
    
    public void draw(Graphics2D g, int width, int height, double scale, 
            AbstractCollection<IParticle> particles) {
        _width = width;
        _height = height;
        
        for (IParticle particle : particles) {
            draw(g, scale, particle);
        }
        
//        // Prune tail collection
//        for (LinkedList<Vector1x3> tail : particleReg.values()) {
//            if (tail.size() > _maxTailSize) {
//                tail.removeFirst();
//                
//            }
//        }
    }
    
    Vector1x3 target = new Vector1x3();
    
    private int scaleX(double x, double z, double scale) {
        return (int)((x - target.x) * scale) + (_width / 2);
    }
    
    private int scaleY(double y, double z, double scale) {
        return (int)((y - target.y) * scale) + (_height / 2);
    }
    
    public void draw(Graphics2D g, double scale, IParticle particle) {
        LinkedList<Vector1x3> tail = getTail(particle);
        
        Vector1x3 position = particle.position();
        int x = scaleX(position.x, position.z, scale); 
        int y = scaleY(position.y, position.z, scale);

        if (_drawTail) {
            float colorInc = 128 / (float) _maxTailSize;
            float color = colorInc;

            tail.add(new Vector1x3(position));
            if (tail.size() > _maxTailSize) {
                tail.removeFirst();
            }

            Iterator<Vector1x3> i = tail.iterator();
            Vector1x3 start = null, end = null;
            while (i.hasNext()) {
                start = i.next();
                if (end != null) {
                    g.setColor(new Color((int) color, (int) color, (int) color));
                    g.drawLine((int) (start.x), (int) (start.y), (int) (end.x),
                            (int) (end.y));
                }
                end = start;
                color += colorInc;
            }
        }

        if (_drawVelocity) {
            g.setColor(Color.GREEN);
            g.drawLine(x, y, x + (int) (particle.velocity().x), y
                    + (int) (particle.velocity().y));
        }

         if (_drawAccel) {
             g.setColor(Color.MAGENTA);
             g.drawLine(x,y,x+(int)(((TestParticle)particle).accel.x *
                     10000d),y+(int)(((TestParticle)particle).accel.y * 10000d));
         }

        if (_drawRadius) {
            g.setColor(Color.WHITE);
            
            int radius = (int)(particle.radius() * scale);
            //int radius = (int) ((Math.abs(particle.position().z + 1e6d) / 1e12) * particle.radius()*2);
            int diameter = (radius * 2);
            g.drawOval(x - radius, y - radius, diameter, diameter);
        }

        g.setColor(Color.WHITE);
        g.drawLine(x, y, x, y);
    }
}
