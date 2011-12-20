package stars;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import stars.ui.PaintSurface;
import stars.ui.StarInfo;
import stars.galaxy.*;
import stars.logging.Logger;
import stars.ui.LoggerPane;

/*
 * F = m * a
 * F = G (m1 * m2) / d^2
 * G = 6.67x10^-11 N*m^2/kg^2
 */

public class Stars extends JFrame {
    Logger logger;
    
    JMenuBar menuBar = new JMenuBar();
    JPanel contentPane;
    JSplitPane splitPane;
    JTabbedPane tabPane;
    
    JPanel paramPanel = new JPanel();
    
    LoggerPane logPane = new LoggerPane();
    PaintSurface paintSurface = new PaintSurface();
    StarList starList = new StarList();
    public Universe uni = new Universe(starList, paintSurface);
    
/*    Timer timer = new Timer(250,new ActionListener() {
        public void actionPerformed(ActionEvent e) {
//            paintSurface.repaint();
        }
    });*/
    
    
    public Stars() {
        logger = Logger.getInstance();
        
        //logger.addLogger(new FileLogger("./logs/"));
        
        uni.setTargetObjectCount(15);
        
        try {
            initWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        starList.add(new Star(1e15, 6000));
        starList.add(new GravityWell(1e15));

 /*       //a = v^2/r  < Circlular motion
        Star a = new Star(1000);
        a.position.set(0,1000,0);
        a.velocity.set(8.16700679564,0,0);
        starList.add(a);
        // 66.7 N
        // 0.0667 m/s^2
        
        a = new Star(1000);
        a.position.set(1250,0,0);
        a.velocity.set(0,-7.3047929471,0);
        starList.add(a);
        // 42.688 N
        // 0.042688 m/s^2
        // 7.3047929471 m/s

        a = new Star(1000);
        a.position.set(0,-2500,0);
        a.velocity.set(-5.16526862806,0,0);
        starList.add(a);
        // 10.672 N
        // 0.010672 m/s^2
        // 5.16526862806 m/s
        
        a = new Star(1000);
        a.position.set(-5000,0,0);
        a.velocity.set(0,3.65239647355,0);
        starList.add(a);
        // 2.668 N
        // 0.002668 m/s^2
        // 3.65239647355 m/s*/
        
//        timer.start();
        Thread universeThread = new Thread(uni);
        universeThread.start();
    }
    
    
    public void initWindow() throws Exception {
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        new JButton();
        StarInfo starInfo = uni.infoPanel; 
        paramPanel = starInfo;
            
        tabPane = new JTabbedPane();
        tabPane.setTabPlacement(JTabbedPane.BOTTOM);

        JScrollPane scrollPane = new JScrollPane(logPane);
        scrollPane.setName("Log");
        logger.addLogger(logPane);
        tabPane.add(scrollPane);
        
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, paintSurface, tabPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(1);
        
        paintSurface.addItem(uni);
        
        // Content Pane...
        contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(paramPanel, BorderLayout.WEST);
        contentPane.add(splitPane, BorderLayout.CENTER);
        this.pack();

                
        //this.setSize(400,300);
        this.setSize(800,600);
        this.setTitle("StarZ");

        
        JMenu fileMenu = new JMenu("Program");
        JMenuItem i = new JMenuItem("Exit");
        fileMenu.add(i);
        
        JMenu objectsMenu = new JMenu("Objects");
        JMenu addSubMenu = new JMenu("Add");
        i = new JMenuItem("Star");
        addSubMenu.add(i);
        i = new JMenuItem("Gravity Well");
        addSubMenu.add(i);
        objectsMenu.add(addSubMenu);
        i = new JMenuItem("Remove");
        objectsMenu.add(i);
        i = new JMenuItem("Get");
        objectsMenu.add(i);
        
               
        menuBar.add(fileMenu);
        menuBar.add(objectsMenu);
        
        //this.setJMenuBar(menuBar);
        //this.setJMenuBar(ExtendableMenuBar());
        
        //pack();
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
               Stars starz = new Stars();
           }
        });
    }
}
