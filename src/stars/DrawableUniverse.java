package stars;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JPanel;
import stars.DrawableParticle;
import stars.physics.Universe;
import stars.physics.actions.*;
import stars.physics.particles.*;
import stars.ui.UniverseSettings;

public class DrawableUniverse extends Universe implements ActionListener {
    private static DrawableParticle dp = new DrawableParticle();
    protected boolean _drawStatistics = true,
                      _drawRings = false,
                      _drawBoundRadius = true,
                      _drawOrigin = true,
                      _drawParticles = true,
                      _drawScale = true;
    protected JPanel panel;
    protected double _scale = 0.015;
    protected int _xOffset = 0,
                  _yOffset = 0;
    Gravity g;

    public DrawableUniverse(JPanel panel) {
        super(new Vector<IParticleOld>(), new Vector<IActionOld>());
        g = new Gravity();

//        addParticle(new Blackhole(1e21));
        
//        actions.add(new Boundry(this));
        actions.add(new BounceBoundry(this,-100));
        
        actions.add(new ParticleGenerator(PlanetParticle.class, 30,10));
//        actions.add(g);
//        actions.add(new BounceBoundryEliptical(this, -100.0d));
        
        this.panel = panel;
    }

    public DrawableUniverse() {
    	this(null);
    }

    public void setPanel(JPanel panel) {
    	this.panel = panel;
    }

    public double getScale() { return _scale; }
    public void setScale(double scale) { _scale = scale; }

    /**
     * Paint the universe after each step.
     */
    public void step() {
        super.step();

        if(panel != null)
        if (!panel.isPaintingTile()) {
            panel.repaint();
        }
    }
    
    public void draw(Graphics2D g, int width, int height) {
    	g.setColor(Color.BLACK);
        g.fillRect(1,1,width,height);
    	
        if (_drawScale) {
            g.setColor(Color.GRAY);
            
            char[] c = new String("" + (1 / _scale) * 50 + " m").toCharArray();
            g.drawChars(c, 0, c.length, 70, height - 5);
            g.drawLine(10, height-10, 60, height-10);
            g.drawLine(10, height-5, 10, height-15);
            g.drawLine(60, height-5, 60, height-15);
        }
        
        if (_drawStatistics) {
            g.setColor(Color.GRAY);
            
            char[] c = new String("Cycles: " + totalTime + " s").toCharArray();
            g.drawChars(c, 0, c.length, 150, 15);
             
            int size = particles.size();
             
            c = new String("Particles: " + size).toCharArray();
            g.drawChars(c, 0, c.length, 10, 15);
        }

        g.drawLine(10, 100, 10, 130);
        g.drawLine(30, 100, 30, 130);
        g.drawLine(50, 100, 50, 130);
        
        g.setColor(Color.GREEN);
        g.drawLine(30, 105, 30 + (int)(this.g.sx * 20), 105);
        g.drawLine(30, 115, 30 + (int)(this.g.sy * 20), 115);
        g.drawLine(30, 125, 30 + (int)(this.g.sz * 20), 125);
        
        //g.drawLine(0, 0, (int)(200 * this.g.sx), (int)(200 * this.g.sy));
        
        g.translate((width / 2) + _xOffset, (height / 2) + _yOffset);

        

        
        g.scale(_scale, _scale);

        if (_drawBoundRadius) {
        	double radius    = getBoundRadius();
        	double diameter  =  2 * radius;
            double negRadius = -1 * radius;
            
            g.setColor(new Color(1.0f, 0f, 0f));
            g.drawRect((int)negRadius, (int)negRadius, (int)diameter, (int)diameter);
            g.setColor(new Color(0.5f, 0f, 0f));
            g.drawOval((int)negRadius+50, (int)negRadius+50, (int)diameter-100, (int)diameter-100);
        }
    
        if (_drawRings) {
            g.setColor(new Color(0.125f, 0.125f, 0.125f));
            g.drawOval((int) (-5000), (int) (-5000),
                    (int) (10000), (int) (10000));
        
            g.setColor(new Color(0.20f, 0.20f, 0.20f));
            g.drawOval((int) (-2500), (int) (-2500),
                     (int) (5000), (int) (5000));
        
            g.drawLine(0, -5000, 0, 5000);
            g.drawLine(-5000, 0, 5000, 0);
        
            g.setColor(new Color(0.3f, 0.3f, 0.3f));
            g.drawOval((int) (-1250), (int) (-1250),
                    (int) (2500), (int) (2500));
        }
        
        if (_drawParticles) {
            for (int i = 0; i < particles.size(); i++) {
                try {
                    dp.draw(g, 1, particles.get(i));
                } catch(IndexOutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }    	
    }
    
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if (cmd.equals("Step"))  { step(); }
        if (cmd.equals("Start")) { start(); }
        if (cmd.equals("Pause")) { stop(); }
    }
    
    public JPanel getSettingsPanel() {
        return new UniverseSettings(this);
    }
}
