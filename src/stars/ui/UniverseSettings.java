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
import stars.UniverseMediator;

@SuppressWarnings("serial")
public class UniverseSettings extends JPanel implements ActionListener {
	UniverseMediator universeMediator;
	
    JButton startstop = new JButton("Start");
    JButton step = new JButton("Step");
    
    JButton addParticle = new JButton("Add Particle");
    JButton addAction = new JButton("Add Action");
    
    JButton addGenerator = new JButton("+Generator");
    
    JTextField stepSize = new JTextField(3);
    
    JSlider scaleSlider = new JSlider();
    JSpinner scale = new JSpinner();
    
    
    public UniverseSettings(UniverseMediator um) {
        universeMediator = um;
        universeMediator.register(this);
        
        startstop.addActionListener(this);
        step.addActionListener(this);
        
        addGenerator.addActionListener(this);
        
        init();
    }
    
    public void isRunning(boolean b) {
        if (b) {
            startstop.setActionCommand("Stop");
            startstop.setText("Stop");
        } else {
            startstop.setActionCommand("Start");
            startstop.setText("Start");
        }
    }
    
    public void init() {
        this.setLayout(new FlowLayout());
        this.add(startstop);
        this.add(step);
        this.add(new JLabel("Step:"));
        this.add(stepSize);
        
        this.add(new JLabel("Scale:"));
        this.add(scaleSlider);
        this.add(scale);
        
        this.add(addGenerator);
        
        //scale.setValue(universeMediator.getScale());
        
        //stepSize.setText("" + universe.getStep() + " s");
        stepSize.setToolTipText("Time delta between steps in seconds.");
        stepSize.setEditable(false);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Step")) {
            universeMediator.step();
        }
        
        if (e.getActionCommand().equals("Start")) {
            universeMediator.start();
        }
        
        if (e.getActionCommand().equals("Stop")) {
            universeMediator.stop();
        }
    }
}
