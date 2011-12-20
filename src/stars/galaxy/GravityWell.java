package stars.galaxy;

import java.awt.Color;
import java.awt.Graphics;

import stars.physics.NewtonObject;
import stars.physics.Vector3;
import stars.ui.DrawableI;

public class GravityWell extends NewtonObject implements DrawableI {
    Vector3 meh = new Vector3();


    public GravityWell(double mass) {
        super(mass, 1);
    }


    public void setRadius(double radius) {  }

    public void handleCollision(NewtonObject s) {  }

    public void setCollided(boolean b) {  }

    public Vector3 getTempforce() {
        return meh;
    }
    
    public void draw(Graphics g, double scale) {
        int x = (int)(position.x * scale),
            y = (int)(position.y * scale);
        g.setColor(Color.WHITE);
        g.drawLine(x,y,x,y);
    }
}
