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
import stars.math.Tuple3;
import stars.physics.CenterOfMass;
import stars.physics.Universe;
import stars.physics.nbody.space.BarnesHut;
import stars.physics.nbody.space.TreeSpace;
import stars.ui.space.BarnesHutRenderer;
import stars.ui.space.TreeSpaceRenderer;

@SuppressWarnings("serial")
public class UniversePanel extends JPanel implements MouseListener,
        MouseMotionListener, MouseWheelListener {

    protected UniverseMediator      _drawableUniverse;

    public Universe                 universe;

    protected boolean               _drawStatistics  = true,
                                    _drawRings = false,
                                    _drawBoundRadius = true,
                                    _drawOrigin = true,
                                    _drawParticles = true,
                                    _drawScale = true,
                                    _drawCoM = true;

    /** meters per pixel */
    protected double                _scale           = 1d / 50d;

    protected int                   _xOffset         = 0, _yOffset = 0;

    private static DrawableParticle particleRenderer = new DrawableParticle();
    private ISpaceRenderer spaceRenderer;

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

    public volatile boolean isPainting = false;

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

        if (!isPainting) {
            isPainting = true;
            draw(g, getWidth(), getHeight());
            isPainting = false;
        }
    }

    protected void draw(Graphics2D g, int width, int height) {
        GraphicsWrapper gw = new GraphicsWrapper(g, width, height, _scale);

        g.setColor(Color.BLACK);
        g.fillRect(1, 1, width, height);

        if (_drawScale) {
            g.setColor(Color.GRAY);

            char[] c = new String("" + 1 / _scale * 50 + " m").toCharArray();
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

            c = new String(_drawableUniverse.itPerS + " it/s").toCharArray();
            g.drawChars(c, 0, c.length, 400, 15);
        }

        if (_drawBoundRadius) {
            g.setColor(new Color(0.5f, 0f, 0f));
            gw.drawCircle(0, 0, universe.getBoundRadius());
        }

        if (_drawCoM && !universe.getParticles().isEmpty()) {
            g.setColor(Color.BLUE);
            CenterOfMass com = CenterOfMass.calculate(universe.getParticles());
            gw.drawCross(com.getX(), com.getY(), 20);
        }

        spaceRenderer = new BarnesHutRenderer((BarnesHut) universe.getSpace());
        spaceRenderer.draw(gw);

        if (_drawParticles) {
            particleRenderer.draw(gw, universe.getParticles());
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // System.out.println(arg0);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // System.out.println(arg0);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // System.out.println(e);
        if (e.getWheelRotation() >= 1) {
            setScale(getScale() * 2);
        } else {
            setScale(getScale() / 2);
        }

        // System.out.println(getScale());
        // System.out.println("" + (_universe.getScale() + (e.getWheelRotation()
        // * e.getClickCount() * 0.005)));
        // _universe.setScale(_universe.getScale() + (e.getWheelRotation() *
        // e.getClickCount() * 0.0005));

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // System.out.println(arg0);
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
}
