package stars.ui.space;

import java.awt.Color;

import stars.physics.nbody.space.BarnesHut;
import stars.physics.nbody.space.BarnesHutNode;
import stars.physics.nbody.space.Boundary;
import stars.ui.GraphicsWrapper;
import stars.ui.ISpaceRenderer;

public class BarnesHutRenderer implements ISpaceRenderer {

    private BarnesHut space;

    private boolean show = true;
    private boolean showInternal = false;
    private boolean showExternal = true;
    private boolean showEmpty = false;
    private boolean showCoM = false;

    public BarnesHutRenderer(BarnesHut space) {
        if (space == null) {
            throw new IllegalArgumentException();
        }

        this.space = space;
    }

    private void drawNode(GraphicsWrapper g, BarnesHutNode node) {
        if (show && node != null) {
            if (node.isExternal()) {
                g.g().setColor(Color.GRAY);
            } else {
                for (BarnesHutNode n : node.nodes) {
                    drawNode(g,n);
                }

                g.g().setColor(Color.DARK_GRAY);
            }

            if ((showEmpty || !node.isEmpty()) && 
                (node.isInternal() && showInternal || node.isExternal() && showExternal)) {
                Boundary center = node.getBoundary();

                g.drawRect(center.boundX.getX(), center.boundY.getX(), 
                    center.getWidthX(), center.getWidthY());
                
                if (showCoM && !node.isExternal() && !node.isEmpty()) {
                    g.g().setColor(Color.DARK_GRAY);
                    g.drawCross(node.centerOfMass.getX(), node.centerOfMass.getY(), 5);
                }
            }
        }
    }

    public void draw(GraphicsWrapper g) {
        BarnesHutNode node = space.getNode();

        drawNode(g, node);
    }
}
