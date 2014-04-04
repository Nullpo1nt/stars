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
    
    public UniverseMediator(Universe u) {
        universe = u;
    }
    
    public void register(UniversePanel up) {
        panel = up;
    }
    
    public void register(UniverseSettings us) {
        settings = us;
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
        }

        settings.isRunning(false);
        thread = null;
    }
}
