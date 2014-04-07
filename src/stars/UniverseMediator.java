package stars;

import stars.physics.Universe;
import stars.ui.UniversePanel;
import stars.ui.UniverseSettings;

public class UniverseMediator implements Runnable {

    private Universe universe;
    
    private UniversePanel panel;
    
    private UniverseSettings settings;
    
    Thread thread;
    
    boolean running = false;
    
    long rate = 500;
    
    public UniverseMediator(Universe u) {
        universe = u;
    }
    
    public void register(UniversePanel up) {
        panel = up;
    }
    
    public void register(UniverseSettings us) {
        settings = us;
    }
    
    public void updateRate(long value) {
        rate = value;
    }
    
    public void updateStepSize(double value) {
        universe.setStep(value);
    }
    
    public void step() {
        universe.step();
        panel.repaint();
    }
    
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
        settings.isRunning(true);
    }
    
    public void stop() {
        running = false;
    }
    
    @Override
    public void run() {
        while (running) {
            step();
            
            try {
                Thread.sleep(rate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        settings.isRunning(false);
        thread = null;
    }
}
