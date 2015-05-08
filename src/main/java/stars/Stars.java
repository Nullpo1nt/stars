package stars;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import stars.physics.Universe;
import stars.ui.UniversePanel;
import stars.ui.UniverseSettings;

@SuppressWarnings("serial")
public class Stars extends JFrame {
    UniverseMediator universeMediator;

    UniversePanel universePanel;
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
        
        Universe uni = new Universe();
        
        universeMediator = new UniverseMediator(uni);
        
        universePanel = new UniversePanel(universeMediator, uni);
        universeSettings = new UniverseSettings(universeMediator);
        
        // Content Pane...
        contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(universePanel, BorderLayout.CENTER);
        contentPane.add(universeSettings, BorderLayout.SOUTH);
        this.pack();

        this.setSize(800,600);
        this.setTitle("StarZ");

//        JMenuItem i;
        
//        JMenu simMenu = new JMenu("Simulation");
//        i = new JMenuItem("Start");
//        i.addActionListener(universeDrawable);
//        simMenu.add(i);
//        i = new JMenuItem("Step");
//        i.addActionListener(universeDrawable);
//        simMenu.add(i);
//        i = new JMenuItem("Pause");
//        i.addActionListener(universeDrawable);
//        simMenu.add(i);

//        menuBar.add(simMenu);
//        this.setJMenuBar(menuBar);
        
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
