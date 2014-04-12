package stars;

import stars.physics.Universe;
import stars.physics.actions.ParticleGenerator;
import stars.physics.particles.TestParticle;
import stars.ui.UniversePanel;
import stars.ui.UniverseSettings;

public class UniverseMediator implements Runnable {

    // Simulation objects
    private Universe universe;
    
    private ParticleGenerator generator;
    
    // GUI Components
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
        if (!panel.isPainting) {
            if (generator != null) {
                generator.execute(universe.getStepSize(), universe.getParticles());
            }
        
            universe.step();
            panel.repaint();
        } else {
            System.out.println("!!! Painting, skiping update.");
        }
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
    
    public void toggleGenerator() {
        if (generator == null) {
            generator = new ParticleGenerator(TestParticle.class, 100, 100);
        } else {
            generator = null;
        }
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
