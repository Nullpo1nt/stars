package stars.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import stars.DrawableUniverse;

@SuppressWarnings("serial")
public class UniverseSettings extends JPanel implements ActionListener {
	DrawableUniverse universe;
    JButton start = new JButton("Start");
    JButton pause = new JButton("Pause");
    JButton step = new JButton("Step");
    
    JButton addParticle = new JButton("Add Particle");
    JButton addAction = new JButton("Add Action");
    
    JButton addGenerator = new JButton("+Generator");
    
    JTextField stepSize = new JTextField(3);
    
    JSlider scaleSlider = new JSlider();
    JSpinner scale = new JSpinner();
    
    
    public UniverseSettings(DrawableUniverse u) {
        start.addActionListener(this);
        pause.addActionListener(this);
        step.addActionListener(this);
        
        addGenerator.addActionListener(this);
        
        universe = u;
        
        init();
    }
    
    
    public void init() {
        this.setLayout(new FlowLayout());
        this.add(start);
        this.add(step);
        this.add(pause);
        this.add(new JLabel("Step:"));
        this.add(stepSize);
        
        this.add(new JLabel("Scale:"));
        this.add(scaleSlider);
        this.add(scale);
        
        this.add(addGenerator);
        
        scale.setValue(universe.getScale());
        
        //stepSize.setText("" + universe.getStep() + " s");
        stepSize.setToolTipText("Time delta between steps in seconds.");
        stepSize.setEditable(false);
    }
    
    public void actionPerformed(ActionEvent e) {
        ((ActionListener)universe).actionPerformed(e);
    }
}
