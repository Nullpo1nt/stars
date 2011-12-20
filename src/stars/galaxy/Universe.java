package stars.galaxy;

import java.awt.Color;
import java.awt.Graphics;
import stars.physics.Plane;
import stars.ui.DrawableI;
import stars.ui.PaintSurface;
import stars.ui.StarInfo;

public class Universe extends Plane implements DrawableI {
    protected boolean _drawStatistics = true,
                      _drawRings = false,
                      _drawBoundRadius = true,
                      _drawOrigin = true,
                      _drawStars = true;
    protected PaintSurface panel;
    public StarInfo infoPanel = new StarInfo(false);
    
    
    public Universe(StarList list, PaintSurface panel) {
        super(list);
        
        this.panel = panel;
    }

    
    public void step() {
        super.step();

        if(!panel.isPaintingTile())
            panel.repaint();
    }
    
    public void addRandom() {
        Star aStar = new Star();
        double ran = Math.random();
        
        aStar.setMass(ran * (1e11));
        //aStar.setMass((1e15));
        aStar.position.setRandom(10000);
        aStar.position.z = 0;
        aStar.velocity.setRandom(1e1);
        aStar.velocity.z = 0;
        aStar.setRadius(Math.random() * 100);
                
        objects.add(aStar);
        infoPanel.setStar(aStar);
    }
    
    public void draw(Graphics g, double scale) {
        int width  = (int)g.getClipBounds().getWidth(),
            height = (int)g.getClipBounds().getHeight();
        double pScale = Math.min(width,height) / (20000 * scale);
         
        //g.setColor(new Color(0.25f,0.25f,0.25f));
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
    
        g.translate((int) width / 2, (int) height / 2);

        if(_drawBoundRadius) {
            double negBound = _boundRadius * -1;
            
            g.setColor(new Color(1f, 0f, 0f));
            g.drawOval((int) (negBound * pScale), (int) (negBound * pScale),
                    (int) (_boundDiameter * pScale), (int) (_boundDiameter * pScale));
        }
    
        if(_drawRings) {
            g.setColor(new Color(0.125f, 0.125f, 0.125f));
            g.drawOval((int) (-5000 * pScale), (int) (-5000 * pScale),
                    (int) (10000 * pScale), (int) (10000 * pScale));
        
            g.setColor(new Color(0.20f, 0.20f, 0.20f));
            g.drawOval((int) (-2500 * pScale), (int) (-2500 * pScale),
                     (int) (5000 * pScale), (int) (5000 * pScale));
        
            g.drawLine(0, -5000, 0, 5000);
            g.drawLine(-5000, 0, 5000, 0);
        
            g.setColor(new Color(0.3f, 0.3f, 0.3f));
            g.drawOval((int) (-1250 * pScale), (int) (-1250 * pScale),
                    (int) (2500 * pScale), (int) (2500 * pScale));
        }
        
        if(_drawStars) {
            g.setColor(Color.WHITE);
        
            for (int x = 0; x < objects.size(); x++) {
                ((DrawableI) objects.get(x)).draw(g, pScale);
            }
            
            infoPanel.update();
        }
        
        if(_drawStatistics) {
            g.translate(-(int) width / 2, -(int) height / 2);
            g.setColor(Color.GRAY);
            
            char[] asdf = new String("Cycles: " + _totalTime + " s").toCharArray();
            g.drawChars(asdf, 0, asdf.length, 10, 15);
             
            int size = objects.size();
             
            asdf = new String("Objects: " + size + " (" + ( ((size * size) - size) / 2 ) + " iterations)").toCharArray();
            g.drawChars(asdf, 0, asdf.length, 10, 27);
        }
    
        System.out.flush();
        maxP.set(0, 0, 0);
        maxV.set(0, 0, 0);
        maxA.set(0, 0, 0);
        maxF.set(0, 0, 0);
     
    }
}
