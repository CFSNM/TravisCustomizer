import Panels.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cesar on 26/07/2017.
 */
public class TravisCustomizer extends JFrame implements ActionListener
{

    public static String TITLE = "Travis CI Customizer";

    public static String ABOUT = "<html><p>Travis Customizer "+TCInformation.TCVersion+"</p></html>";

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newFileItem, loadFileItem, closeItem;
    private NewFileWindow nfWindow;
    private ImagePanel imagePanel;
    private JLabel aboutLabel;


    public TravisCustomizer()
    {
        super(TITLE);
        initializeComponents();
        setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private void initializeComponents()
    {
        this.setLayout(new BorderLayout());
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newFileItem = new JMenuItem("New File");
        loadFileItem = new JMenuItem("Load File");
        closeItem = new JMenuItem("Close");

        nfWindow = new NewFileWindow();

        aboutLabel = new JLabel(ABOUT);
        imagePanel = new ImagePanel("src/main/resources/TravisCustomizerLogo.png");

        newFileItem.addActionListener(this);
        loadFileItem.addActionListener(this);
        closeItem.addActionListener(this);

        fileMenu.add(newFileItem);
        fileMenu.add(loadFileItem);
        fileMenu.add(closeItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(aboutLabel, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == closeItem)
            askForClose();
        if(e.getSource() == newFileItem)
            nfWindow.setVisible(true);
    }


    public static void askForClose() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit Travis CI Customizer?", "Exit from Travis CI Customizer", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) System.exit(0);
    }
}
