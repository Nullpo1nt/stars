package stars;

import java.util.Date;

import stars.physics.Universe;
import stars.physics.actions.ParticleGenerator;
import stars.physics.particles.TestParticle;
import stars.ui.UniversePanel;
import stars.ui.UniverseSettings;

public class UniverseMediator implements Runnable {

    // Simulation objects
    private Universe          universe;

    private ParticleGenerator generator;

    // GUI Components
    private UniversePanel     panel;

    private UniverseSettings  settings;

    Thread                    thread;

    volatile boolean          running = false;

    long                      rate    = 1;

    public float              itPerS  = 0L;

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
        if (generator != null) {
            generator.execute(universe.getStepSize(), universe.getParticles());
        }

        universe.step();

        if (panel != null && !panel.isPainting) {
            panel.repaint();
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
        if (settings != null) {
            settings.isRunning(true);
        }
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
        long time = new Date().getTime();
        long it = 0L;

        while (running) {
            it++;

            step();

            long current = new Date().getTime();

            if (current - time >= 1000L) {
                itPerS = ((float) it / (float) (current - time)) * 1000f;
                time = current;
                it = 0L;
            }
        }

        settings.isRunning(false);
        thread = null;
    }

    public static void main(String[] argv) throws InterruptedException {
        Universe uni = new Universe();
        uni.setStep(1d);

        UniverseMediator uniM = new UniverseMediator(uni);

        uniM.toggleGenerator();
        uniM.step();
        uniM.toggleGenerator();

        uniM.start();

        while (true) {
            Thread.sleep(1000);
            System.out.println(uniM.itPerS + " it/s, "
                    + uni.getParticles().size());
        }
    }
}
