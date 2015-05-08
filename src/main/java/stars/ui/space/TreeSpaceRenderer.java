package stars.ui.space;

import java.awt.Color;

import stars.math.Tuple3;
import stars.physics.nbody.space.OctTreeNode;
import stars.physics.nbody.space.TreeSpace;
import stars.physics.particles.IParticle;
import stars.physics.particles.IParticleState;
import stars.ui.GraphicsWrapper;
import stars.ui.ISpaceRenderer;

public class TreeSpaceRenderer implements ISpaceRenderer {

    private TreeSpace space;

    public TreeSpaceRenderer(TreeSpace space) {
        if (space == null) {
            throw new IllegalArgumentException();
        }

        this.space = space;
    }

    private void drawNode(GraphicsWrapper g, OctTreeNode node, String s, int x) {

        if (node != null) {
            
            OctTreeNode parent = node.getParent();
        
            
            if (parent != null) {
                Tuple3 center = node.getCenter();

                // int w = g.scale(Math.abs(parent.getCenter().getX() - center.getX()));
                // int h = g.scale(Math.abs(parent.getCenter().getY() - center.getY()));

                //if (x > 1 && x < 3)
                //g.drawRect(g.scale(center.getX()) - w + g.halfWidth, g.scale(center.getY()) -h+ g.halfHeight, w*2, h*2);
                // if (x == 1)
                if (node.isLeaf()) { 
                    g.g().setColor(Color.GREEN);
                } else {
                    g.g().setColor(Color.MAGENTA);
                }

                g.g().drawRect(g.scale(center.getX()) - x + g.halfWidth(), 
                           g.scale(center.getY()) - x + g.halfHeight(), 
                           x+x, 
                           x+x);

                g.g().setColor(Color.DARK_GRAY);
                g.drawLine(center.getX(), center.getY(), parent.getCenter().getX(), parent.getCenter().getY());

                if (node.isLeaf()) {
                    // g.g().setColor(Color.GRAY);
                    for (IParticle p : node.getParticles()) {
                        IParticleState ss = p.getCurrentState();

                        g.drawLine(center.getX(), center.getY(), 
                            ss.position().getX(), ss.position().getY());
                        // g.drawLine(g.scale(ss.position().getX()) + g.halfWidth - 5, g.scale(ss.position().getY()) + g.halfHeight - 5,
                        //     g.scale(ss.position().getX()) + g.halfWidth + 5, g.scale(ss.position().getY()) + g.halfHeight + 5);

                    }
                }
            }

            drawNode(g, node.getQuadrant1(), s + ",1",x+1);
            drawNode(g, node.getQuadrant2(), s + ",2",x+1);
            drawNode(g, node.getQuadrant3(), s + ",3",x+1);
            drawNode(g, node.getQuadrant4(), s + ",4",x+1);
            drawNode(g, node.getQuadrant5(), s + ",5",x+1);
            drawNode(g, node.getQuadrant6(), s + ",6",x+1);
            drawNode(g, node.getQuadrant7(), s + ",7",x+1);
            drawNode(g, node.getQuadrant8(), s + ",8",x+1);
        }
    }

    @Override
    public void draw(GraphicsWrapper g) {
        OctTreeNode node = space.getNode();

        drawNode(g, node, "p", 0);
    }
}
