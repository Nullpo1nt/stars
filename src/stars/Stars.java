package stars;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Stars extends JFrame {
	DrawableUniverse uni = new DrawableUniverse();
	UniversePanel uniP;
    JPanel universeSettings;
    JPanel contentPane;
    JMenuBar menuBar = new JMenuBar();
    
    
    public Stars() {
        try {
            initWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void initWindow() throws Exception {
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        
        addWindowListener(l);
		
        uniP = new UniversePanel(uni);
        uni.setPanel(uniP);
        universeSettings = uni.getSettingsPanel();
        
        // Content Pane...
        contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(uniP, BorderLayout.CENTER);
        contentPane.add(universeSettings, BorderLayout.SOUTH);
        this.pack();

        this.setSize(800,600);
        this.setTitle("StarZ");

        JMenuItem i;
        
        JMenu simMenu = new JMenu("Simulation");
        i = new JMenuItem("Start");
        i.addActionListener(uni);
        simMenu.add(i);
        i = new JMenuItem("Step");
        i.addActionListener(uni);
        simMenu.add(i);
        i = new JMenuItem("Pause");
        i.addActionListener(uni);
        simMenu.add(i);

        menuBar.add(simMenu);
        
        this.setJMenuBar(menuBar);
        
        validate();
        setVisible(true);
    }
       
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            setDefaultLookAndFeelDecorated(true);
        } catch(Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               new Stars();
           }
        });
    }
}
