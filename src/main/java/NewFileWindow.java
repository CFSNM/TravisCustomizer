import Panels.BeforeInstallPanel;
import Panels.InstallPanel;
import Panels.PreStepsPanel;
import Panels.StepPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;


/**
 * Created by Cesar on 28/07/2017.
 */
public class NewFileWindow extends JFrame implements MouseListener {


    public static String TITLE = "Creating new Travis File";

    private DefaultMutableTreeNode travis, previousSteps, lifeCycle, beforeInstall, install, beforeScript,
            script, afterSuccess, afterFailure, beforeDeploy, deploy, afterDeploy, afterScript, mailNotifications;

    private TreeModel treeModel;
    private JTree lifeCycleTree;

    private JPanel lifeCyclePanel, currentStepPanel;
    private List<StepPanel> panelList;

    private PreStepsPanel preStepsPanel;
    private BeforeInstallPanel beforeInstallPanel;
    private InstallPanel installPanel;

    private CardLayout cl;

    private static String beforeInstallPath = "[YAML File, Life Cycle, Before Install]";
    private static String installPath = "[YAML File, Life Cycle, Install]";
    private static String beforeScriptPath = "[YAML File, Life Cycle, Before Script]";
    private static String scriptPath = "[YAML File, Life Cycle, Script]";
    private static String afterSuccessPath = "[YAML File, Life Cycle, After Success]";
    private static String afterFailurePath = "[YAML File, Life Cycle, After Failure]";
    private static String beforeDeployPath = "[YAML File, Life Cycle, Before Deploy]";
    private static String deployPath = "[YAML File, Life Cycle, Deploy]";
    private static String afterDeployPath = "[YAML File, Life Cycle, After Deploy]";
    private static String afterScriptPath = "[YAML File, Life Cycle, After Script]";

    private static String previousStepsPath = "[YAML File, Previous Steps]";
    private static String lifeCyclePath = "[YAML File, Life Cycle]";
    private static String mailNotificationsPath = "[YAML File, Mail Notifications]";


    public NewFileWindow()
    {
        super(TITLE);
        initializeComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void initializeComponents()
    {
        panelList = new ArrayList<StepPanel>();
        currentStepPanel = new JPanel();
        lifeCyclePanel = new JPanel();

        preStepsPanel = new PreStepsPanel();
        beforeInstallPanel = new BeforeInstallPanel();
        installPanel = new InstallPanel();

        panelList.add(preStepsPanel);
        panelList.add(beforeInstallPanel);
        panelList.add(installPanel);


        travis = new DefaultMutableTreeNode("YAML File");
        previousSteps = new DefaultMutableTreeNode("Previous Steps");
        lifeCycle = new DefaultMutableTreeNode("Life Cycle");
        beforeInstall = new DefaultMutableTreeNode("Before Install");
        install = new DefaultMutableTreeNode("Install");
        beforeScript = new DefaultMutableTreeNode("Before Script");
        script = new DefaultMutableTreeNode("Script");
        afterSuccess = new DefaultMutableTreeNode("After Success");
        afterFailure = new DefaultMutableTreeNode("After Failure");
        beforeDeploy = new DefaultMutableTreeNode("Before Deploy");
        deploy = new DefaultMutableTreeNode("Deploy");
        afterDeploy = new DefaultMutableTreeNode("After Deploy");
        afterScript = new DefaultMutableTreeNode("After Script");
        mailNotifications = new DefaultMutableTreeNode("Mail Notifications");

        lifeCycle.add(beforeInstall);
        lifeCycle.add(install);
        lifeCycle.add(beforeScript);
        lifeCycle.add(script);
        lifeCycle.add(afterSuccess);
        lifeCycle.add(afterFailure);
        lifeCycle.add(beforeDeploy);
        lifeCycle.add(deploy);
        lifeCycle.add(afterDeploy);
        lifeCycle.add(afterScript);

        travis.add(previousSteps);
        travis.add(lifeCycle);
        travis.add(mailNotifications);

        treeModel = new DefaultTreeModel(travis);
        lifeCycleTree = new JTree(treeModel);

        lifeCycleTree.addMouseListener(this);


        this.setLayout(new BorderLayout());
        currentStepPanel.setLayout(new CardLayout());
        lifeCyclePanel.add(lifeCycleTree);
        this.add(lifeCyclePanel, BorderLayout.WEST);
        currentStepPanel.add(new JPanel(), "Default Panel");
        currentStepPanel.add(preStepsPanel, "Pre Steps Panel");
        currentStepPanel.add(beforeInstallPanel, "Before Install Panel");
        currentStepPanel.add(installPanel, "Install Panel");
        this.add(currentStepPanel, BorderLayout.CENTER);

    }


    public void mouseClicked(MouseEvent e)
    {
        TreePath tp = lifeCycleTree.getPathForLocation(e.getX(), e.getY());

        if (tp != null)
        {
            String step = tp.toString();
            cl = (CardLayout)(currentStepPanel.getLayout());

            if (step.equals(previousStepsPath))
                cl.show(currentStepPanel, "Pre Steps Panel");

            else if (step.equals(beforeInstallPath))
                cl.show(currentStepPanel, "Before Install Panel");

            else if (step.equals(installPath))
                cl.show(currentStepPanel, "Install Panel");

            else if (step.equals(beforeScriptPath))
                System.out.println("BEFORE SCRIPT");

            else if (step.equals(scriptPath))
                System.out.println("SCRIPT");

            else if (step.equals(afterSuccessPath))
                System.out.println("AFTER SUCCESS");

            else if (step.equals(afterFailurePath))
                System.out.println("AFTER FAILURE");

            else if (step.equals(beforeDeployPath))
                System.out.println("BEFORE DEPLOY");

            else if (step.equals(deployPath))
                System.out.println("DEPLOY");

            else if (step.equals(afterDeployPath))
                System.out.println("AFTER DEPLOY");

            else if (step.equals(afterScriptPath))
                System.out.println("AFTER SCRIPT");

            else if (step.equals(mailNotificationsPath))
                System.out.println("MAIL NOTIFICATIONS");

        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
