package stars.ui;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import stars.galaxy.Star;

public class StarInfo extends JPanel {
    public static final int TEXTFIELD_SIZE = 18;
    Star _star;
    
    //JTextField star = new JTextField(TEXTFIELD_SIZE);
    JLabel star = new JLabel();
    JTextField mass = new JTextField(TEXTFIELD_SIZE);
    
    JTextField positionX = new JTextField(TEXTFIELD_SIZE);
    JTextField positionY = new JTextField(TEXTFIELD_SIZE);
    JTextField positionZ = new JTextField(TEXTFIELD_SIZE);
    
    JTextField velocityX = new JTextField(TEXTFIELD_SIZE);
    JTextField velocityY = new JTextField(TEXTFIELD_SIZE);
    JTextField velocityZ = new JTextField(TEXTFIELD_SIZE);
    
    JTextField accelerationX = new JTextField(TEXTFIELD_SIZE);
    JTextField accelerationY = new JTextField(TEXTFIELD_SIZE);
    JTextField accelerationZ = new JTextField(TEXTFIELD_SIZE);
    
    //JTextField X;
    //JTextField Y;
    //JTextField Z;
    
    JCheckBox collisionCheck = new JCheckBox("Collision Detection");
    
    public StarInfo(boolean modifiable) {
        initPanel(modifiable);
    }
    
    public void setStar(Star star) {
        _star = star;
        
        if(_star != null) {
            this.star.setText("Star #" + star.hashCode());
        } else {
            this.star.setText("Star #");
            mass.setText("");
            positionX.setText("");
            positionY.setText("");
            positionZ.setText("");
            velocityX.setText("");
            velocityY.setText("");
            velocityZ.setText("");
            accelerationX.setText("");
            accelerationY.setText("");
            accelerationZ.setText("");

        }
        
        update();
    }
    
    public void update() {
        if(_star != null) {
            mass.setText(""+_star.getMass());

            positionX.setText(""+_star.position.getX());
            positionY.setText(""+_star.position.getY());
            positionZ.setText(""+_star.position.getZ());

            velocityX.setText(""+_star.velocity.getX());
            velocityY.setText(""+_star.velocity.getY());
            velocityZ.setText(""+_star.velocity.getZ());

            accelerationX.setText(""+_star.accel.getX());
            accelerationY.setText(""+_star.accel.getY());
            accelerationZ.setText(""+_star.accel.getZ());
        }
    }
    
    protected void initPanel(boolean modifiable) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        //this.add(createLine("Star", star));
        this.add(star);
        this.add(createLine("Mass (kg)", mass));
        
        this.add(new JLabel("Position (m)"));
        this.add(createLine("X", positionX));
        this.add(createLine("Y", positionY));
        this.add(createLine("Z", positionZ));
        
        this.add(new JLabel("Velocity (m/s)"));
        this.add(createLine("X", velocityX));
        this.add(createLine("Y", velocityY));
        this.add(createLine("Z", velocityZ));
        
        this.add(new JLabel("Acceleration (m/s)"));
        this.add(createLine("X", accelerationX));
        this.add(createLine("Y", accelerationY));
        this.add(createLine("Z", accelerationZ));
        
        this.add(Box.createVerticalGlue());
        
        this.add(collisionCheck);
    }
    
    private JPanel createLine(String label, JTextField field) {
        JPanel panel = new JPanel();

        field.setEditable(false);
        
        panel.add(new JLabel(label));
        panel.add(field);
        panel.add(Box.createHorizontalGlue());
        
        return panel;
    }
}
