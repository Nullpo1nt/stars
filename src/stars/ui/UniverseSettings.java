package stars.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import stars.UniverseMediator;

@SuppressWarnings("serial")
public class UniverseSettings extends JPanel implements ActionListener, ChangeListener {
	UniverseMediator universeMediator;
	
    JButton startstop = new JButton("Start");
    JButton step = new JButton("Step");
    
    SpinnerNumberModel rateModel = new SpinnerNumberModel(10, 0, 1000, 1);
    JSpinner rate = new JSpinner(rateModel);
    
    SpinnerNumberModel stepModel = new SpinnerNumberModel(0.01d, -1d, 1d, 0.0001d);
    JSpinner stepSize = new JSpinner(stepModel);
    
    JSlider scaleSlider = new JSlider();
    
    JSpinner scale = new JSpinner();
    
    
    public UniverseSettings(UniverseMediator um) {
        universeMediator = um;
        universeMediator.register(this);
        
        startstop.addActionListener(this);
        step.addActionListener(this);
        
        rate.addChangeListener(this);
        stepSize.addChangeListener(this);
        stepSize.setEditor(new JSpinner.NumberEditor(stepSize, "0.0000"));
        
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
        
        this.add(new JSeparator(JSeparator.VERTICAL));
        
        this.add(new JLabel("Step:"));
        this.add(stepSize);
        this.add(new JLabel("Rate:"));
        this.add(rate);
        
        this.add(new JLabel("Scale:"));
        this.add(scaleSlider);
        this.add(scale);
        
        //scale.setValue(universeMediator.getScale());
        
        //stepSize.setText("" + universe.getStep() + " s");
        stepSize.setToolTipText("Time delta between steps in seconds.");
        //stepSize.setEditable(false);
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

    @Override
    public void stateChanged(ChangeEvent e) {
        universeMediator.updateRate(rateModel.getNumber().longValue());
        universeMediator.updateStepSize(stepModel.getNumber().doubleValue());
    }
}
