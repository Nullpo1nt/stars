package stars.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import stars.UniverseMediator;
import stars.physics.Universe;

@SuppressWarnings("serial")
public class UniversePanel extends JPanel implements MouseListener,
        MouseMotionListener, MouseWheelListener {
    
    protected UniverseMediator _drawableUniverse;
    
    // FIXME Remove, replace with what is needed
    public Universe universe;

    protected boolean _drawStatistics = true, 
                      _drawRings = false,
                      _drawBoundRadius = true, 
                      _drawOrigin = true, 
                      _drawParticles = true,
                      _drawScale = true;

    protected double _scale = 0.015;
    
    protected int _xOffset = 0, 
                  _yOffset = 0;

    private static DrawableParticle particleRenderer = new DrawableParticle();

    public UniversePanel(UniverseMediator um, Universe u) {
        _drawableUniverse = um;
        _drawableUniverse.register(this);

        universe = u;
        
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public double getScale() {
        return _scale;
    }

    public void setScale(double scale) {
        _scale = scale;
    }
    
    /**
     * Since we're always going to fill our entire bounds, allow Swing to
     * optimize painting of us
     */
    @Override
    public boolean isOpaque() {
        return true;
    }

    /**
     * All components of the surface using the provided graphics object.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

        draw(g, getWidth(), getHeight());
    }

    protected void draw(Graphics2D g, int width, int height) {
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, width, height);

        if (_drawScale) {
            g.setColor(Color.GRAY);

            char[] c = new String("" + (1 / _scale) * 50 + " m").toCharArray();
            g.drawChars(c, 0, c.length, 70, height - 5);
            g.drawLine(10, height - 10, 60, height - 10);
            g.drawLine(10, height - 5, 10, height - 15);
            g.drawLine(60, height - 5, 60, height - 15);
        }

        if (_drawStatistics) {
            g.setColor(Color.GRAY);

            char[] c = new String("Cycles: " + universe.getTotalTime() + " s")
                    .toCharArray();
            g.drawChars(c, 0, c.length, 150, 15);

            int size = universe.getParticles().size();

            c = new String("Particles: " + size).toCharArray();
            g.drawChars(c, 0, c.length, 10, 15);
        }

        // Gravity forces...
        // g.drawLine(10, 100, 10, 130);
        // g.drawLine(30, 100, 30, 130);
        // g.drawLine(50, 100, 50, 130);
        //
        // g.setColor(Color.GREEN);
        // g.drawLine(30, 105, 30 + (int)(this.g.sx * 20), 105);
        // g.drawLine(30, 115, 30 + (int)(this.g.sy * 20), 115);
        // g.drawLine(30, 125, 30 + (int)(this.g.sz * 20), 125);

        // g.drawLine(0, 0, (int)(200 * this.g.sx), (int)(200 * this.g.sy));

        g.translate((width / 2) + _xOffset, (height / 2) + _yOffset);
        g.scale(_scale, _scale);

        if (_drawBoundRadius) {
            double radius = universe.getBoundRadius();
            double diameter = 2 * radius;
            double negRadius = -1 * radius;

            g.setColor(new Color(1.0f, 0f, 0f));
            g.drawRect((int) negRadius, (int) negRadius, (int) diameter,
                    (int) diameter);
            g.setColor(new Color(0.5f, 0f, 0f));
            g.drawOval((int) negRadius + 50, (int) negRadius + 50,
                    (int) diameter - 100, (int) diameter - 100);
        }

        if (_drawRings) {
            g.setColor(new Color(0.125f, 0.125f, 0.125f));
            g.drawOval((-5000), (-5000), (10000), (10000));

            g.setColor(new Color(0.20f, 0.20f, 0.20f));
            g.drawOval((-2500), (-2500), (5000), (5000));

            g.drawLine(0, -5000, 0, 5000);
            g.drawLine(-5000, 0, 5000, 0);

            g.setColor(new Color(0.3f, 0.3f, 0.3f));
            g.drawOval((-1250), (-1250), (2500), (2500));
        }

        if (_drawParticles) {
            particleRenderer.draw(g, 1, universe.getParticles());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println(arg0);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println(arg0);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e);
        if (e.getWheelRotation() == 1) {
            setScale(getScale() * 2);
        } else {
            setScale(getScale() / 2);
        }

        System.out.println(getScale());
        // System.out.println("" + (_universe.getScale() + (e.getWheelRotation()
        // * e.getClickCount() * 0.005)));
        // _universe.setScale(_universe.getScale() + (e.getWheelRotation() *
        // e.getClickCount() * 0.0005));

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println(arg0);
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }
}