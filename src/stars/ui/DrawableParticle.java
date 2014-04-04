package stars.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.AbstractCollection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import stars.physics.Vector1x3;
import stars.physics.particles.IParticle;

public class DrawableParticle {
    boolean _drawTail = true, 
            _drawVelocity = false, 
            _drawAccel = false,
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
    
    public void draw(Graphics2D g, double scale, AbstractCollection<IParticle> particles) {
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
    
    public void draw(Graphics2D g, double scale, IParticle particle) {
        LinkedList<Vector1x3> tail = getTail(particle);
        
        Vector1x3 position = particle.position();
        int x = (int) position.x, y = (int) position.y;

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

        // if (_drawAccel) {
        // g.setColor(Color.MAGENTA);
        // g.drawLine(x,y,x+(int)(particle.getAcceleration().x *
        // 20),y+(int)(particle.getAcceleration().y * 20));
        // }

        if (_drawRadius) {
            g.setColor(Color.DARK_GRAY);
             int radius = (int) (10);
            // int radius = (int) ((Math.abs(particle.position().z + 20000) / 40000) * particle.getRadius());
            int diameter = radius * 2;
            g.drawOval(x - radius, y - radius, diameter, diameter);
        }

        g.setColor(Color.WHITE);
        g.drawLine(x, y, x, y);
    }
}
